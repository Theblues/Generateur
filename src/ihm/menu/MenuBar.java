package ihm.menu;

import java.awt.Event;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.tree.TreePath;

import main.*;
import util.*;

/*
 * TODO	- Ajouter les images
 * 		- Ajouter les actions manquantes
 */
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
	private JMenuItem itemSave;
	
	private JMenu menuGenerer;
	private JMenuItem itemGenererProjet;
	private JMenuItem itemGenererPage;

	// item pour le menu Edition
	private JMenuItem itemClose;
	private JMenuItem itemUndo;
	private JMenuItem itemRedo;
	private JMenuItem copier;
	private JMenuItem coller;
	private JMenuItem couper;

	
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
		itemNewProject.setIcon(new ImageIcon("images/project-new.png"));
		itemNewProject.setToolTipText("Cree un nouveau projet");
		itemNewProject.addActionListener(this);
		
		itemNewPage = new JMenuItem("Nouvelle Page");
		itemNewPage.setIcon(new ImageIcon("images/page-new.png"));
		itemNewPage.setToolTipText("Cree une nouvelle page");
		itemNewPage.addActionListener(this);
		
		itemOpenProject = new JMenuItem("Ouvrir un Projet");
		itemOpenProject.setIcon(new ImageIcon("images/folder-open.png"));
		itemOpenProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK));
		itemOpenProject.setToolTipText("Ouvre un projet");
		itemOpenProject.addActionListener(this);
		
		itemSave= new JMenuItem("Enregistrer Sous");
		itemSave.setIcon(new ImageIcon("images/filesaveas.png"));
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
		itemSave.setToolTipText("Sauvegarde les ressources");
		itemSave.addActionListener(this);
		
		menuGenerer = new JMenu("Generer");
		menuGenerer.setIcon(new ImageIcon("images/generate.png"));
		
		itemGenererProjet = new JMenuItem("Generer le projet");
		itemGenererProjet.setToolTipText("Genere un projet");
		itemGenererProjet.addActionListener(this);
	
		itemGenererPage = new JMenuItem("Generer la page");
		itemGenererPage.setToolTipText("Genere une page");
		itemGenererPage.addActionListener(this);
		
		menuGenerer.add(itemGenererProjet);
		menuGenerer.add(itemGenererPage);
		
		itemClose = new JMenuItem("Quitter");
		itemClose.setIcon(new ImageIcon("images/application-exit.png"));
		itemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK));
		itemClose.setToolTipText("Quitte l'application");
		itemClose.addActionListener(this);

		// initialisation des items pour le menu Edition
		itemUndo = new JMenuItem("Annuler");
		itemUndo.setIcon(new ImageIcon("images/edit-undo.png"));
		itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,Event.CTRL_MASK));
		itemUndo.addActionListener(this);
		
		itemRedo = new JMenuItem("Retablir");
		itemRedo.setIcon(new ImageIcon("images/edit-redo.png"));
		itemRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,Event.CTRL_MASK));
		itemRedo.addActionListener(this);
		
		couper = new JMenuItem(new DefaultEditorKit.CutAction());
		couper.setText("Couper");
		couper.setIcon(new ImageIcon("images/edit-cut.png"));
		couper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,Event.CTRL_MASK));
		couper.setToolTipText("Permet l'action de Couper");

		coller = new JMenuItem(new DefaultEditorKit.PasteAction());
		coller.setText("Coller");
		coller.setIcon(new ImageIcon("images/edit-paste.png"));
		coller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,Event.CTRL_MASK));
		coller.setToolTipText("Permet l'action de Coller");

		copier = new JMenuItem(new DefaultEditorKit.CopyAction());
		copier.setText("Copier");
		copier.setIcon(new ImageIcon("images/edit-copy.png"));
		copier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.CTRL_MASK));
		copier.setToolTipText("Permet l'action de Copier");

		// Initialisation des items pour le menu ajouter
		itemTitre = new JMenuItem("Ajouter un titre");
		itemTitre.setIcon(new ImageIcon("images/add-title.png"));
		itemTitre.addActionListener(this);
		
		itemParagraphe = new JMenuItem("Ajouter un paragraphe");
		itemParagraphe.setIcon(new ImageIcon("images/Text-Editor.png"));
		itemParagraphe.addActionListener(this);
		
		itemImage = new JMenuItem("Ajouter une image");
		itemImage.setIcon(new ImageIcon("images/picture_add.jpg"));
		itemImage.addActionListener(this);
		
		itemAide = new JMenuItem("Aide");
		itemAide.setIcon(new ImageIcon("images/info.gif"));
		itemAide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
		itemAide.setToolTipText("Aide");
		itemAide.addActionListener(this);
		
		// ajout des items dans le menu Fichier
		menuFile.add(itemNewProject);
		menuFile.add(itemNewPage);
		menuFile.addSeparator();
		menuFile.add(itemOpenProject);
		menuFile.addSeparator();
		menuFile.add(itemSave);
		menuFile.add(menuGenerer);
		menuFile.addSeparator();
		menuFile.add(itemClose);

		// ajout des items dans le menu Edition
		menuEdit.add(itemUndo);
		menuEdit.add(itemRedo);
		menuEdit.addSeparator();
		menuEdit.add(copier);
		menuEdit.add(couper);
		menuEdit.add(coller);
		
		// ajout des items dans le menu Ajouter
		menuAjouter.add(itemTitre);
		menuAjouter.addSeparator();
		menuAjouter.add(itemParagraphe);
		menuAjouter.addSeparator();
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
		// on recupere le treepath pour savoir quel noeud on utilise
		TreePath path = Controleur.fenetre.getArborescence().getPath();
		
		if (mi.equals(itemClose))
		{
			int option = Controleur.creerOptionPaneConfirm("Sauvegarder", "Voulez-vous sauvegarder avant de quitter ?", JOptionPane.YES_NO_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION)
				Controleur.enregistrer();
			if (option != JOptionPane.CANCEL_OPTION)
				System.exit(0);
		}
		
		// actions pour la creation de projet et de page
		if (mi.equals(itemNewProject))
			Controleur.creerPanelCreerProjet();
        if (mi.equals(itemNewPage))
        	if (path != null && path.getPathCount() > 1)
        		Controleur.creerPanelCreerPage();
        
        // Actions pour ajouter titre/paragraphe/image
		if (mi.equals(itemTitre))
			if (path != null && path.getPathCount() == 3)
				Controleur.creerPanelAjouterTitre(0,"");
		if (mi.equals(itemParagraphe))
			if (path != null && path.getPathCount() == 3)
				Controleur.creerPanelAjouterParagraphe(0,"");
		if (mi.equals(itemImage))
			if (path != null && path.getPathCount() == 3)
				Controleur.creerPanelAjouterImage(0);
		
		// actions pour Generer
		if (mi.equals(itemGenererPage))
		{
			Projet projet = Controleur.metier.getProjetSelectionne();
			Page page = projet.getPageSelectionne();
			Controleur.metier.getGenerator().generateFile(projet, page);
		}
		if (mi.equals(itemGenererProjet))
		{
			Projet projet = Controleur.metier.getProjetSelectionne();
			if (projet == null)
				return;
			for (Page page : projet.getAlPage())
			{
				if (page == null)
					continue;
				Controleur.metier.getGenerator().generateFile(projet, page);
			}
		}
		// action pour sauvegarder l'arbre et les listes
		if (mi.equals(itemSave))
			Controleur.enregistrer();
		if (mi.equals(itemAide))
			Controleur.creerFenetreAide();
	}
}