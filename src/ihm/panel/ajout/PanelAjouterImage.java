package ihm.panel.ajout;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import org.apache.commons.io.IOUtils;

import main.*;
import util.*;
import ihm.panel.*;

public class PanelAjouterImage extends JPanel implements ActionListener
{
	private PanelImage image;
	
	private JButton	valider;
	private JButton	annuler;
	
	private String nom;
	private String chemin;
	private String cheminHTML;
	
	private int statue;

	public PanelAjouterImage(int statue, String chemin)
	{
		setLayout(new BorderLayout());
		
		this.statue = statue;
		this.chemin = chemin;
		
		if (statue == 0)
			choisirImage();	
		
		if (chemin == null)
			return;
		
		// on creer le panel avec le chemin de l'image
		image = new PanelImage(this.chemin);
		
		add(image);
		
		JPanel pan = new JPanel();
	
		if (statue == 0)
		{
			annuler = new JButton("Annuler");
			annuler.addActionListener(this);
			pan.add(annuler);
		}
	
		valider = new JButton("Valider");
		valider.addActionListener(this);
		pan.add(valider);
	
		add(pan, BorderLayout.SOUTH);
		
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Votre image"),
						BorderFactory.createEmptyBorder(2, 2, 2, 2)),
						this.getBorder()));
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Projet projet = Controleur.metier.getProjetSelectionne();
		Page page = projet.getPageSelectionne();
		
		JButton b = (JButton) e.getSource();
		
		if (statue == 0 )
		{
			if (b.equals(valider))
			{
				// on enregistre l'image dans le dossier du projet
				enregistrerImage();
				// on ajoute les chemin dans les listes
				page.ajouterImage(chemin);
				page.ajouterImageHTML(cheminHTML);
				int cpt = page.getAlImage().size();
				page.ajouterOrdre("Image " + cpt);
				// on ajoute l'element dans l'arbre
				Controleur.fenetre.getArborescence().ajoutFils(null, "element", "Image " + cpt);
				Controleur.creerPanelAjouterImage(1, chemin);
			}
		}
		else
			Controleur.creerPanelPropriete(page);
	}
	
	private void choisirImage()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Users"));
		chooser.changeToParentDirectory();
		// on peut selectionner qu'une image
		chooser.setMultiSelectionEnabled(false);
		// on ajoute un filtre
		chooser.setFileFilter(new MonFiltre(new String[] { "gif", "png", "jpeg", "jpg" }, 
							"Les fichiers images (*.gif, *.png, *.jpg,*.jpeg)"));
		// on ouvre la fenetre de selection
		int fichier = chooser.showOpenDialog(null);
		
		// s'il a choisit un fichier
		if (fichier == JFileChooser.FILES_ONLY)
		{
			nom = chooser.getSelectedFile().getName();
			// chemin absolu du fichier choisi
			chemin =  chooser.getSelectedFile().getAbsolutePath();
		}
	}

	private void enregistrerImage()
	{
		Projet projet = Controleur.metier.getProjetSelectionne();
		String cheminArr = projet.getCheminDossier() +  "/" + projet.getNom() + "/content/img/";

		InputStream input;
		OutputStream output;

		try
		{
			input = new FileInputStream(chemin);
			output = new FileOutputStream(cheminArr + nom);
			IOUtils.copy(input, output);
		}
		catch (FileNotFoundException e1){	e1.printStackTrace();	}
		catch (IOException e)			{	e.printStackTrace();	}
		cheminHTML = "./content/img/" + nom;
	}
}
