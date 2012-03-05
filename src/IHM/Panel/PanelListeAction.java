package IHM.Panel;

import javax.swing.*;
import javax.swing.tree.TreePath;

import IHM.util.PopupButton;
import Main.Controleur;
import util.*;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;

public class PanelListeAction extends JPanel implements ActionListener
{
	// bouton creation
	private JButton boutonCreerProjet;
	private JButton boutonCreerPage;

	// bouton ajouts
	private JButton boutonAjouterTitre;
	private JButton boutonAjouterParagraphe;
	private JButton boutonAjouterImage;

	// bouton temporaire !!
	private JButton boutonMonter;
	private JButton boutonDescendre;

	public PanelListeAction()
	{
		// tous mettre a gauche
		setLayout(new FlowLayout(FlowLayout.LEFT));

		MyPopupButton popupGenerer = new MyPopupButton("", new String[] {"Generer le projet", "Generer la page" }, this);
		popupGenerer.setIcon(new ImageIcon("images/generate.png"));
		popupGenerer.setToolTipText("Generer");
		
		// bouton Creation Projet
		boutonCreerProjet = new JButton();
		boutonCreerProjet.setIcon(new ImageIcon("images/project-new.png"));
		boutonCreerProjet.setToolTipText("Creer un nouveau projet");
		boutonCreerProjet.addActionListener(this);
		
		// bouton Creation Page
		boutonCreerPage = new JButton();
		boutonCreerPage.setIcon(new ImageIcon("images/page-new.png"));
		boutonCreerPage.setToolTipText("Creer une nouvelle page");
		boutonCreerPage.addActionListener(this);

		// bouton Ajout Titre
		boutonAjouterTitre = new JButton();
		boutonAjouterTitre.setIcon(new ImageIcon("images/add-title.png"));
		boutonAjouterTitre.setToolTipText("Ajouter un titre a la page");
		boutonAjouterTitre.addActionListener(this);

		// bouton Ajout Paragraphe
		boutonAjouterParagraphe = new JButton();
		boutonAjouterParagraphe.setIcon(new ImageIcon("images/Text-Editor.png"));
		boutonAjouterParagraphe.setToolTipText("Ajouter un paragraphe a la page");
		boutonAjouterParagraphe.addActionListener(this);

		// bouton Ajout Image
		boutonAjouterImage = new JButton();
		boutonAjouterImage.setIcon(new ImageIcon("images/picture_add.jpg"));
		boutonAjouterImage.setToolTipText("Ajouter une image a la page");
		boutonAjouterImage.addActionListener(this);
		
		// bouton Monter Selection
		boutonMonter = new JButton();
		boutonMonter.setIcon(new ImageIcon("images/select-up.png"));
		boutonMonter.setToolTipText("Monter la selection");
		boutonMonter.addActionListener(this);
		
		// bouton Descendre Selection
		boutonDescendre = new JButton();
		boutonDescendre.setIcon(new ImageIcon("images/select-down.png"));
		boutonDescendre.setToolTipText("Descendre la selection");
		boutonDescendre.addActionListener(this);

		add(popupGenerer);
		add(boutonCreerProjet);
		add(boutonCreerPage);
		add(boutonAjouterTitre);
		add(boutonAjouterParagraphe);
		add(boutonAjouterImage);
		add(boutonMonter);
		add(boutonDescendre);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();
		// on recupere le treepath pour savoir quel noeud on utilise
		TreePath path = Controleur.fenetre.getArborescence().getPath();
		
		if (b.equals(boutonCreerProjet))
			Controleur.creerPanelCreerProjet();
		if (b.equals(boutonCreerPage))
			if (path != null && path.getPathCount() > 1)
				Controleur.creerPanelCreerPage();
		if (b.equals(boutonAjouterTitre))
			if (path != null && path.getPathCount() == 3)
				Controleur.creerPanelAjouterTitre(0, "");
		if (b.equals(boutonAjouterParagraphe))
			if (path != null && path.getPathCount() == 3)
				Controleur.creerPanelAjouterParagraphe(0, "");
		if (b.equals(boutonAjouterImage))
			if (path != null && path.getPathCount() == 3)
				Controleur.creerPanelAjouterImage(0);
		if (b.equals(boutonDescendre))
		{
			// si la taille de path est de 3 c'est une page
			if (path != null && path.getPathCount() == 3)
				Controleur.descendrePage();
			// si la taille de path est de 4 c'est un element
			if (path != null && path.getPathCount() == 4)
				Controleur.descendreElement();
		}
		if (b.equals(boutonMonter))
		{
			// si la taille de path est de 3 c'est une page
			if (path != null && path.getPathCount() == 3)
				Controleur.monterPage();
			// si la taille de path est de 4 c'est un element
			if (path != null && path.getPathCount() == 4)
				Controleur.monterElement();
		}			
	}

	class MyPopupButton extends PopupButton
	{
		public MyPopupButton(String label, String[] items, Container parent)
		{
			super(label, items, parent);
		}

		public void actionPerformed(ActionEvent evt)
		{
			JMenuItem item = (JMenuItem) evt.getSource();
			
			if (item.getText().equals("Generer le projet"))
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
			else if (item.getText().equals("Generer la page"))
			{
				Projet projet = Controleur.metier.getProjetSelectionne();
				if (projet == null)
					return;
				Page page = projet.getPageSelectionne();
				if (page == null)
					return;
				Controleur.metier.getGenerator().generateFile(projet, page);
			}
		}
	}
}