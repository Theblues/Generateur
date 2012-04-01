package util;

import ihm.panel.PanelArbre;

import java.io.*;

import javax.swing.*;
import javax.swing.tree.*;

import main.Controleur;

public class ImportPage 
{
	private String chemin;
	
	public ImportPage()
	{
		choisirFichier();
		traitement();
	}
	
	private void choisirFichier()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Users"));
		chooser.changeToParentDirectory();

		chooser.setMultiSelectionEnabled(false);
		// on ajoute le filtre
		chooser.setFileFilter(new MonFiltre(new String[] {"html"},"Les pages web (*.html)"));
		int state = chooser.showOpenDialog(null);

		if (state == JFileChooser.FILES_ONLY)
			chemin = chooser.getSelectedFile().getAbsolutePath();
	}

	private void traitement()
	{
		String ligne = "";
		try 
		{
			FileReader fr = new FileReader(new File(chemin));
			BufferedReader br = new BufferedReader(fr);
			
			String nomPage="";
			String titre;
			String paragraphe;
			Page page = null;
			Projet projet = Controleur.metier.getProjetSelectionne();
			
			while((ligne = br.readLine())!=null)
			{
				// Titre de la page
				if (ligne.contains("<!-- Haut de page -->"))
				{
					ligne = br.readLine();
					nomPage = ligne;
					nomPage = nomPage.replace("\t", "");
					page = new Page(new File(chemin), nomPage);
				}
				
				// Un titre
				if (ligne.contains("<div class=\"title\">") && page != null)
				{
					ligne = br.readLine();
					titre = ligne;
					titre = titre.replace("\t", "");
					page.ajouterTitre(titre);
					int cptTitre = page.getAlTitre().size();
					page.ajouterOrdre("Titre " + cptTitre);
				}
				
				// Un paragraphe
				if (ligne.contains("<p>") && page != null)
				{
					paragraphe = "";
					while (! (ligne.contains("</p>")))
					{
						paragraphe += ligne;
						ligne = br.readLine()+"\n";
					}
					paragraphe += "</p>";
					page.ajouterParagrapheHTML(paragraphe);
					int cptParagraphe = page.getAlParagrapheHTML().size();
					page.ajouterOrdre("Paragraphe " + cptParagraphe);
				}
				
				// Une image
				if (ligne.contains("<img") && page != null)
				{
					boolean ajout = false;
					String image = "";  
					for (int i = 0; i < ligne.length(); i++)
					{
						char car = ligne.charAt(i);
						if (car == '"' && ajout == false)
							ajout = true;
						else if (car == '"' && ajout == true)
							ajout = false;
						if (ajout && car != '"')
							image += car;
					}
					page.ajouterImage(image);
					int cptImage = page.getAlImage().size();
					page.ajouterOrdre("Image " + cptImage);
				}
			}
			if (page == null)
			{
				Controleur.creerOptionPane("error", "Importation de la page impossible");
			}
			projet.ajouterPage(page);
			
			afficherArbre(projet, page);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	private void afficherArbre(Projet projet, Page page)
	{
		// on selectionne l'arbre
		PanelArbre parbre = Controleur.fenetre.getArborescence();
		// on ajoute la page au projet
		parbre.ajoutFils(null, "fichier", page.getNom());

		// on recupere l'object du projet de l'arbre
		Object obj = parbre.getParentNodeProjet();
		DefaultMutableTreeNode noeud = (DefaultMutableTreeNode) obj;
		
		// on recupere l'object page du projet
		TreeNode tn = noeud.getLastChild();
		for (String s : page.getAlOrdre())
			// on ajoute l'element a la page
			parbre.ajoutFils(tn, "element", s);
	}
}