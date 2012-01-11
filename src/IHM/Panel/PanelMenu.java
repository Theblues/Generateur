package IHM.Panel;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import org.apache.commons.io.IOUtils;

import Main.*;
import Utilitaire.MonFiltre;

public class PanelMenu extends JPanel implements ActionListener
{
	private JMenuBar menuBar;

	private JMenu menuFile;
	private JMenu menuEdit;
	private JMenu menuAjouter;

	// item pour le menu Fichiers
	private JMenuItem itemNewProject;
	private JMenuItem itemOpenProject;
	private JMenuItem itemSaveAs;
	private JMenuItem itemGenerer;

	// item pour le menu Edition
	private JMenuItem itemClose;
	private JMenuItem itemUndo;
	private JMenuItem itemRedo;
	
	// item pour le menu Ajouter
        private JMenuItem itemNewPage;
	private JMenuItem itemTitre;
	private JMenuItem itemParagraphe;
	private JMenuItem itemImage;

	public PanelMenu()
	{
		// initialisation de la bar de Menu
		menuBar = new JMenuBar();
		// initialisation des Menus
		menuFile = new JMenu("Fichier");
		menuEdit = new JMenu("Edition");
		menuAjouter = new JMenu("Ajouter");

		// initialisation des items pour le menu Fichier
		itemNewProject = new JMenuItem("Nouveau Projet");
		itemNewProject.addActionListener(this);
		itemOpenProject = new JMenuItem("Ouvrir un Projet");
		itemOpenProject.addActionListener(this);
		itemSaveAs = new JMenuItem("Enregistrer Sous");
		itemSaveAs.addActionListener(this);
		itemGenerer = new JMenuItem("Generer");
		itemGenerer.addActionListener(this);
		itemClose = new JMenuItem("Quitter");
		itemClose.addActionListener(this);

		// initialisation des items pour le menu Edition
		itemUndo = new JMenuItem("Annuler");
		itemUndo.addActionListener(this);
		itemRedo = new JMenuItem("Retablir");
		itemRedo.addActionListener(this);

		// Initialisation des items pour le menu ajouter
                itemNewPage = new JMenuItem("Nouvelle page");
		itemNewPage.addActionListener(this);
		itemTitre = new JMenuItem("Ajouter un titre");
		itemTitre.addActionListener(this);
		itemParagraphe = new JMenuItem("Ajouter un paragraphe");
		itemParagraphe.addActionListener(this);
		itemImage = new JMenuItem("Ajouter une image");
		itemImage.addActionListener(this);
		
		// ajout des items dans le menu Fichier
		menuFile.add(itemNewProject);
		menuFile.add(itemOpenProject);
		menuFile.addSeparator();
		menuFile.add(itemSaveAs);
		menuFile.add(itemGenerer);
		menuFile.addSeparator();
		menuFile.add(itemClose);

		// ajout des items dans le menu Edition
		menuEdit.add(itemUndo);
		menuEdit.add(itemRedo);
		
		// ajout des items dans le menu Ajouter
		menuAjouter.add(itemNewPage);
		menuAjouter.add(itemTitre);
		menuAjouter.add(itemParagraphe);
		menuAjouter.add(itemImage);
		
		// Ajout des menus dans la bar de Menu
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuAjouter);
	}

	// permet d'ajouter la bar de menu dans la frame
	public void addMenuInFrame(JFrame f)
	{
		f.setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		JMenuItem mi = (JMenuItem) e.getSource();
		if (mi.equals(itemClose))
			closeFrame();
        if (mi.equals(itemNewPage))
        	Generateur.creerFenetreCreerPage();
		if (mi.equals(itemTitre))
			Generateur.creerFenetreAjouterTitre();
		if (mi.equals(itemParagraphe))
			Generateur.creerFenetreAjouterParagraphe();
		if (mi.equals(itemGenerer))
			Generateur.generer();
		if (mi.equals(itemImage))
			ajouterImage();
	}

	private void ajouterImage()
	{
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Users"));
		chooser.changeToParentDirectory();
		// on peut selectionner qu'une image
		chooser.setMultiSelectionEnabled(false);
		// on ajoute un filtre
		chooser.setFileFilter(new MonFiltre(new String[] { "gif", "tif", "jpeg", "jpg" }, 
							"les fichiers image (*.gif, *.jpg,*.jpeg)"));
		// on ouvre la fenetre de s�lection
		int fichier = chooser.showOpenDialog(null);
		
		// s'il a choisit un fichier
		if (fichier == JFileChooser.FILES_ONLY)
		{
			// nom du fichier choisi
			String nom = chooser.getSelectedFile().getName();

			// chemin absolu du fichier choisi
			String cheminDep = chooser.getSelectedFile().getAbsolutePath();
			String cheminArr = "./site/content/IMG/";

			InputStream input;
			OutputStream output;

			try
			{
				input = new FileInputStream(cheminDep);
				output = new FileOutputStream(cheminArr + nom);
				IOUtils.copy(input, output);	
			}
			catch (FileNotFoundException e1){	e1.printStackTrace();	}
			catch (IOException e)			{	e.printStackTrace();	}

			Generateur.creerFenetreAjouterImage(cheminArr + nom, nom);
		}
	}

	private void closeFrame()
	{
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
