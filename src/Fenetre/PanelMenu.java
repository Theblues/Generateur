package Fenetre;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import Main.Generateur;

public class PanelMenu extends JPanel implements ActionListener
{
	private JMenuBar menuBar;

	private JMenu menuFile;
	private JMenu menuEdit;
	private JMenu menuElement;

	// item pour le menu Fichiers
	private JMenuItem itemNewProject;
	private JMenuItem itemOpenProject;
	private JMenuItem itemSaveAs;
	private JMenuItem itemSave;

	// item pour le menu Edition
	private JMenuItem itemClose;
	private JMenuItem itemUndo;
	private JMenuItem itemRedo;
	
	// item pour le menu Element
	private JMenuItem itemNewPage;
	private JMenuItem itemNewElement;

	public PanelMenu()
	{
		// initialisation de la bar de Menu
		menuBar = new JMenuBar();
		// initialisation des Menus
		menuFile = new JMenu("Fichier");
		menuEdit = new JMenu("Edition");
		menuElement = new JMenu("Element");

		// initialisation des items pour le menu Fichier
		itemNewProject = new JMenuItem("Nouveau Projet");
		itemNewProject.addActionListener(this);
		itemOpenProject = new JMenuItem("Ouvrir un Projet");
		itemOpenProject.addActionListener(this);
		itemSaveAs = new JMenuItem("Enregistrer Sous");
		itemSaveAs.addActionListener(this);
		itemSave = new JMenuItem("Enregistrer");
		itemSave.addActionListener(this);
		itemClose = new JMenuItem("Quitter");
		itemClose.addActionListener(this);

		// initialisation des items pour le menu Edition
		itemUndo = new JMenuItem("Annuler");
		itemUndo.addActionListener(this);
		itemRedo = new JMenuItem("Retablir");
		itemRedo.addActionListener(this);

		// initialisation des items pour le menu Element
		itemNewPage = new JMenuItem("Nouvelle page");
		itemNewPage.addActionListener(this);
		itemNewElement = new JMenuItem("Nouvelle Element");
		
		// ajout des items dans le menu Fichier
		menuFile.add(itemNewProject);
		menuFile.add(itemOpenProject);
		menuFile.addSeparator();
		menuFile.add(itemSaveAs);
		menuFile.add(itemSave);
		menuFile.addSeparator();
		menuFile.add(itemClose);

		// ajout des items dans le menu Edition
		menuEdit.add(itemUndo);
		menuEdit.add(itemRedo);
		
		// ajout des items dans le menu Element
		menuElement.add(itemNewPage);
		menuElement.add(itemNewElement);
		
		// Ajout des menus dans la bar de Menu
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuElement);
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
			newPage();
	}

	private void newPage()
	{
		Generateur.FenetreCreerPage();
	}

	private void closeFrame()
	{
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
