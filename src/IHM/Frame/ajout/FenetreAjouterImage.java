package IHM.Frame.ajout;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import org.apache.commons.io.IOUtils;

import Main.*;
import Utilitaire.*;
import IHM.Panel.*;

public class FenetreAjouterImage extends JFrame implements ActionListener
{
	private PanelImage image;
	
	private JButton	valider;
	private JButton	annuler;
	
	private String nom;
	private String chemin;
	
	private int statue;

	public FenetreAjouterImage(int statue)
	{
		this.statue = statue;
		
		choisirImage();
		if (chemin == null)
			return;
		
		setTitle("Ajouter Image");
		image = new PanelImage(chemin);

		add(image);

		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(1, 3));

		// TODO (pour sarah) modifier les boutons
		annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		pan.add(annuler);
		
		valider = new JButton("Valider");
		valider.addActionListener(this);
		pan.add(valider);

		add(pan, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Projet projet = Controleur.metier.getProjetSelectionne();
		Page page = projet.getPageSelectionne();
		
		JButton b = (JButton) e.getSource();
		if (b.equals(annuler))
			dispose();
		
		if ( statue == 0 )
		{
			if (b.equals(valider))
			{
				enregistrerImage(chemin);
				page.ajouterImage(chemin);
				int cpt = page.getAlImage().size();
				page.ajouterOrdre("Image " + cpt);
				Controleur.fenetre.getArborescence().ajoutFils("element", "Image " + cpt);
				
				dispose();
			}
		}
	}
	
	private void choisirImage()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Users"));
		chooser.changeToParentDirectory();
		// on peut selectionner qu'une image
		chooser.setMultiSelectionEnabled(false);
		// on ajoute un filtre
		chooser.setFileFilter(new MonFiltre(new String[] { "gif", "tif", "jpeg", "jpg" }, 
							"les fichiers image (*.gif, *.jpg,*.jpeg)"));
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

	private void enregistrerImage(String chemin)
	{
		Projet projet = Controleur.metier.getProjetSelectionne();
		String cheminArr = "./" + projet.getNom() +"/content/IMG/";

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
		this.chemin = "./content/IMG/" + nom;
	}
}