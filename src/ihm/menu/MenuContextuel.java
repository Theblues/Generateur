package ihm.menu;

import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;

import main.Controleur;
import util.*;

/*
 * Composition du Menu Contextuel
 * 
 * Projet						Page					Element
 *  ________________		 _______________		 _______________
 * | Nouveau ->		|		| Nouveau ->	|		| Nouveau ->	|
 * | Generer Projet	|		| Generer ->	|		| Generer ->	|
 * | Renommer		|		| Ajouter ->	|		| Monter		|
 * | Supprimer		|		| Monter 		|		| Descendre		|
 * | Propriete		|		| Descendre		|		| Modifier		|
 * |________________|		| Renommer		|		| Supprimer		|
 * 							| Supprimer		|		|_______________|
 * 							| Propriete		|		
 * 							|_______________|		
 * 
 * 
 * Nouveau -> Projet
 * 			  Page
 * 
 * Generer -> Projet
 * 			  Page
 * 
 * Ajouter -> Titre
 * 			  Paragraphe
 * 			  Image
 * 
 */

public class MenuContextuel implements ActionListener
{
	private JTree arbre;
	private Object[] noeud;
	private int location;
	
	// item pour le menu Nouveau
	private JMenuItem itemNouveauProjet;
	private JMenuItem itemNouvellePage;
	
	// item pour le menu Generer
	private JMenuItem itemGenererProjet;
	private JMenuItem itemGenererPage;
	
	// item pour le menu Ajouter
	private JMenuItem itemAjoutTitre;
	private JMenuItem itemAjoutParagraphe;
	private JMenuItem itemAjoutImage;
	
	// item pour monter/descendre une page/un element
	private JMenuItem itemMonter;
	private JMenuItem itemDescendre;
	
	// autre item
	private JMenuItem itemRenommer;
	private JMenuItem itemModifier;
	private JMenuItem itemSupprimer;
	private JMenuItem itemPropriete;
	
	public MenuContextuel(MouseEvent me, int location, Object[] obj)
	{
		arbre = (me != null) ? (JTree) me.getSource(): null;
		this.location = location;
		noeud = (obj != null) ? obj : null;
		
		JPopupMenu jpm = new JPopupMenu();
		
		//Menu nouveau
		JMenu menuNouveau = new JMenu("Nouveau");
		menuNouveau.setIcon(new ImageIcon("images/filenew.png"));
		
		itemNouveauProjet = new JMenuItem("Nouveau Projet");
		itemNouveauProjet.setIcon(new ImageIcon("images/project-new.png"));
		itemNouveauProjet.addActionListener(this);
		itemNouvellePage = new JMenuItem("Nouvelle Page");
		itemNouvellePage.setIcon(new ImageIcon("images/page-new.png"));
		itemNouvellePage.addActionListener(this);
		
		menuNouveau.add(itemNouveauProjet);
		menuNouveau.add(itemNouvellePage);
		
		// item Generer
		itemGenererProjet = new JMenuItem("Generer le projet");
		itemGenererProjet.setIcon(new ImageIcon("images/generate.png"));
		itemGenererProjet.addActionListener(this);
		itemGenererPage = new JMenuItem("Generer la page");
		itemGenererPage.setIcon(new ImageIcon("images/generate.png"));
		itemGenererPage.addActionListener(this);
		
		// item ajouts
		JMenu menuAjout = new JMenu("Ajouter");
		menuAjout.setIcon(new ImageIcon("images/add.png"));
		
		itemAjoutTitre = new JMenuItem("Ajouter un titre");
		itemAjoutTitre.addActionListener(this);
		itemAjoutParagraphe = new JMenuItem("Ajouter un paragraphe");
		itemAjoutParagraphe.addActionListener(this);
		itemAjoutImage = new JMenuItem("Ajouter une image");
		itemAjoutTitre.addActionListener(this);
		
		menuAjout.add(itemAjoutTitre);
		menuAjout.add(itemAjoutParagraphe);
		menuAjout.add(itemAjoutImage);
		
		// item monter
		itemMonter = new JMenuItem("Monter");
		itemMonter.setIcon(new ImageIcon("images/select-up.png"));
		itemMonter.addActionListener(this);
		
		// item descendre
		itemDescendre = new JMenuItem("Descendre");
		itemDescendre.setIcon(new ImageIcon("images/select-down.png"));
		itemDescendre.addActionListener(this);
		
		// item Renommer
		itemRenommer = new JMenuItem("Renommer");
		itemRenommer.setIcon(new ImageIcon("images/rename.jpeg"));
		itemRenommer.addActionListener(this);
		
		// item Modifier
		itemModifier = new JMenuItem("Modifier");
		itemModifier.setIcon(new ImageIcon("images/edit.png"));
		itemModifier.addActionListener(this);
		
		// item Supprimer
		itemSupprimer = new JMenuItem("Supprimer");
		itemSupprimer.setIcon(new ImageIcon("images/delete.png"));
		itemSupprimer.addActionListener(this);
		
		// item Propriete
		itemPropriete = new JMenuItem("Proprietes");
		itemPropriete.setIcon(new ImageIcon("images/properties.png"));
		itemPropriete.addActionListener(this);
		
		/*
		 * Menu pour un projet
		 */
		if (location == 2)
		{
			jpm.add(menuNouveau);
			jpm.add(itemGenererProjet);
			jpm.add(itemRenommer);
			jpm.add(itemSupprimer);
			jpm.add(itemPropriete);
		}
		/*
		 * Menu pour une page
		 */
		else if (location == 3)
		{
			JMenu menuGenerer = new JMenu("Generer");
			menuGenerer.setIcon(new ImageIcon("images/generate.png"));
			itemGenererProjet.setIcon(null);
			itemGenererPage.setIcon(null);
			menuGenerer.add(itemGenererProjet);
			menuGenerer.add(itemGenererPage);
			
			jpm.add(menuNouveau);
			jpm.add(menuGenerer);
			jpm.add(menuAjout);
			jpm.add(itemMonter);
			jpm.add(itemDescendre);
			jpm.add(itemRenommer);
			jpm.add(itemSupprimer);
			jpm.add(itemPropriete);
		}
		/*
		 * Menu pour un element
		 */
		else if (location >= 3)
		{
			JMenu menuGenerer = new JMenu("Generer");
			menuGenerer.setIcon(new ImageIcon("images/generate.png"));
			itemGenererProjet.setIcon(null);
			itemGenererPage.setIcon(null);
			menuGenerer.add(itemGenererProjet);
			menuGenerer.add(itemGenererPage);
			
			jpm.add(menuNouveau);
			jpm.add(menuGenerer);
			jpm.add(itemMonter);
			jpm.add(itemDescendre);
			jpm.add(itemModifier);
			jpm.add(itemSupprimer);
		}
		
		// on affiche le menuContextuel a la l'arbre
		jpm.show(arbre, me.getX(), me.getY());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem mi = (JMenuItem) e.getSource();
		
		// Menu nouveau
		if (mi.equals(itemNouveauProjet))
			Controleur.creerPanelCreerProjet();
		if (mi.equals(itemNouvellePage) && noeud[1] != null)
			Controleur.creerPanelCreerPage();
		
		// si c'est un projet
		if (location == 2)
		{
			Projet projet = Controleur.metier.getProjet(noeud[1].toString());
			
			// Generer
			if (mi.equals(itemGenererProjet))
			{
				for (Page page : projet.getAlPage())
				{
					if (page == null)
						continue;
					Controleur.metier.getGenerator().generateFile(projet, page);
				}
			}
			
			// supprimer
			if (mi.equals(itemSupprimer))
			{
				int option = Controleur.creerOptionPaneConfirm("Supprimer", "Etes-vous sur de vouloir supprimer le " + projet.getNom() + " ?", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
					int ind = Controleur.metier.getIndiceProjet(projet);
					
					if (ind != -1)
					{
						// on supprime le noeud a l'arbre
						Controleur.fenetre.getArborescence().supprimerNoeud(noeud[0], ind);
						// on supprime le projet de la liste
						Controleur.metier.getAlProjet().remove(ind);
						
						// supprimer le fichier
						String nom = projet.getNom();
						Scanner sc = new Scanner(nom);
						sc.useDelimiter(" ");
						String nameProjet = sc.next();
						while (sc.hasNext())
							nameProjet += "_" + sc.next();
						
						File file = new File (projet.getCheminDossier() + "/" + nameProjet);
						file.delete();
					}
				}
			}
			
			// proprietes
			if (mi.equals(itemPropriete))
				Controleur.creerPanelPropriete(projet);
		}
		// si c'est un page
		else if (location == 3)
		{
			Projet projet = Controleur.metier.getProjet(noeud[1].toString());
			Page page = projet.getPage(noeud[2].toString());
			
			// Menu generer
			if (mi.equals(itemGenererProjet))
			{
				for (Page p : projet.getAlPage())
				{
					if (p == null)
						continue;
					Controleur.metier.getGenerator().generateFile(projet, p);
				}
			}
			if (mi.equals(itemGenererPage))
				Controleur.metier.getGenerator().generateFile(projet, page);
			
			// Menu ajouter
			if (mi.equals(itemAjoutTitre))
				Controleur.creerPanelAjouterTitre(0, "");
			if (mi.equals(itemAjoutParagraphe))
				Controleur.creerPanelAjouterParagraphe(0, "");
			if (mi.equals(itemAjoutImage))
				Controleur.creerPanelAjouterImage(0);
			
			// Monter/Descendre
			if (mi.equals(itemMonter))
				Controleur.monterPage();
			if (mi.equals(itemDescendre))
				Controleur.descendrePage();
			
			// Supprimer
			if (mi.equals(itemSupprimer))
			{
				int option = Controleur.creerOptionPaneConfirm("Supprimer", "Etes-vous sur de vouloir supprimer la page " + page.getNom() + " ?", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{					
					int ind = projet.getIndicePage(page);
					
					if (ind != -1)
					{
						// on supprime le noeud a l'arbre
						Controleur.fenetre.getArborescence().supprimerNoeud(noeud[1], ind);
						// on supprime le projet de la liste
						projet.getAlPage().remove(ind);
						
						// supprimer le fichier
						File file = page.getFile();
						file.delete();
					}
				}
			}
			// Proprietes
			if (mi.equals(itemPropriete))
				Controleur.creerPanelPropriete(page);
		}
		// si c'est un element
		else if (location >= 3)
		{
			Projet projet = Controleur.metier.getProjet(noeud[1].toString());
			Page page = projet.getPage(noeud[2].toString());
			
			// Menu generer
			if (mi.equals(itemGenererProjet))
			{
				for (Page p : projet.getAlPage())
				{
					if (p == null)
						continue;
					Controleur.metier.getGenerator().generateFile(projet, p);
				}
			}
			if (mi.equals(itemGenererPage))
				Controleur.metier.getGenerator().generateFile(projet, page);
			
			// Monter/Descendre
			if (mi.equals(itemMonter))
				Controleur.monterElement();
			if (mi.equals(itemDescendre))
				Controleur.descendreElement();
			
			// on recupere le type et l'indice de l'element
			Scanner sc = new Scanner(noeud[3].toString()).useDelimiter(" ");
			String type = sc.next();
			int indice = Integer.parseInt(sc.next());
						
			// Modifier
			if (mi.equals(itemModifier))
			{
				if (type.equals("Titre"))
				{
					String ancienTitre = page.getAlTitre().get(indice-1);
					Controleur.creerPanelAjouterTitre(1, ancienTitre);
				}
				if (type.equals("Paragraphe"))
				{
					String ancienParagraphe = page.getAlParagrapheHTML().get(indice-1);
					Controleur.creerPanelAjouterParagraphe(1, ancienParagraphe);
				}
				if (type.equals("Image"))
					Controleur.creerPanelAjouterImage(1);
			}
			
			//Supprimer
			if (mi.equals(itemSupprimer))
			{
				int option = Controleur.creerOptionPaneConfirm("Supprimer", "Etes-vous sur de vouloir supprimer l'element ?", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
					String nomElement = noeud[3].toString();
	
					int ind = page.getIndiceElement(nomElement);
					
					if (ind != -1)
						Controleur.fenetre.getArborescence().supprimerNoeud(noeud[2], ind);
					
					if (type.equals("Titre"))
					{
						page.getAlTitre().remove(indice-1);
					}
					if (type.equals("Paragraphe"))
					{
						page.getAlParagraphe().remove(indice-1);
						page.getAlParagrapheHTML().remove(indice-1);
					}
					if (type.equals("Image"))
					{
						page.getAlImage().remove(indice-1);
					}
					page.getAlOrdre().remove(ind);
				}
			}
		}		
	}
}
