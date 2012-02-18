package IHM.Menu;

import java.awt.event.*;
import javax.swing.*;

import Main.*;
import util.*;

public class MenuBar implements ActionListener
{
	private JMenuBar menuBar;

	private JMenu menuFile;
	private JMenu menuEdit;
	private JMenu menuAjouter;
	private JMenu menuAide;

	// item pour le menu Fichiers
	private JMenuItem itemNewProject;
	private JMenuItem itemNewPage;
	private JMenuItem itemOpenProject;
	private JMenuItem itemSaveAs;
	private JMenuItem itemGenerer;

	// item pour le menu Edition
	private JMenuItem itemClose;
	private JMenuItem itemUndo;
	private JMenuItem itemRedo;
	
	// item pour le menu Ajouter
	private JMenuItem itemTitre;
	private JMenuItem itemParagraphe;
	private JMenuItem itemImage;
	
	private JMenuItem itemAide;

	public MenuBar()
	{
		// initialisation de la bar de Menu
		menuBar = new JMenuBar();
		// initialisation des Menus
		menuFile = new JMenu("Fichier");
		menuEdit = new JMenu("Editer");
		menuAjouter = new JMenu("Ajouter");
		menuAide = new JMenu("Aide");

		// initialisation des items pour le menu Fichier
		itemNewProject = new JMenuItem("Nouveau Projet");
		itemNewProject.addActionListener(this);
		itemNewPage = new JMenuItem("Nouvelle page");
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
		itemTitre = new JMenuItem("Ajouter un titre");
		itemParagraphe = new JMenuItem("Ajouter un paragraphe");
		itemImage = new JMenuItem("Ajouter une image");
		
		itemAide = new JMenuItem("?");
		
		// ajout des items dans le menu Fichier
		menuFile.add(itemNewProject);
		menuFile.add(itemNewPage);
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
		menuAjouter.add(itemTitre);
		menuAjouter.add(itemParagraphe);
		menuAjouter.add(itemImage);
		
		menuAide.add(itemAide);
		
		// Ajout des menus dans la bar de Menu
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuAjouter);
		menuBar.add(menuAide);
	}

	// permet d'ajouter la bar de menu dans la frame
	public void addMenuInFrame(JFrame f)
	{
		f.setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e)
	{		
		JMenuItem mi = (JMenuItem) e.getSource();
		if (mi.equals(itemClose))
			Controleur.fermerFenetre();
		if (mi.equals(itemNewProject))
			Controleur.creerFenetreCreerProjet();
        if (mi.equals(itemNewPage))
        	Controleur.creerFenetreCreerPage();
		if (mi.equals(itemTitre))
			Controleur.creerPanelAjouterTitre(0,"",0);
		if (mi.equals(itemParagraphe))
			Controleur.creerPanelAjouterParagraphe(0,"",0);
		if (mi.equals(itemImage))
			Controleur.creerPanelAjouterImage(0);
		if (mi.equals(itemGenerer))
		{
			Projet projet = Controleur.metier.getProjetSelectionne();
			Page page = projet.getPageSelectionne();
			Controleur.metier.getGenerator().generateFile(projet, page);
		}
	}
	
	public void activerCreationPage()
	{
		itemNewPage.removeActionListener(this);
		itemNewPage.addActionListener(this);
	}
	
	public void activerAjout()
	{
		// permet de ne pas ouvrir 5 fenetres
		itemTitre.removeActionListener(this);
		itemTitre.addActionListener(this);
		
		// permet de ne pas ouvrir 5 fenetres
		itemParagraphe.removeActionListener(this);
		itemParagraphe.addActionListener(this);
		
		// permet de ne pas ouvrir 5 fenetres
		itemImage.removeActionListener(this);
		itemImage.addActionListener(this);
	}

	public void desactiveAjout()
	{
		itemTitre.removeActionListener(this);
		itemParagraphe.removeActionListener(this);
		itemImage.removeActionListener(this);
	}
}