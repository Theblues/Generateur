package ihm.panel;

import javax.swing.*;
import javax.swing.tree.TreePath;

import ihm.util.PopupButton;
import main.Controleur;
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

	public PanelListeAction()
	{
		// tous mettre a gauche
		setLayout(new FlowLayout(FlowLayout.LEFT));

		MyPopupButton popupGenerer = new MyPopupButton("Generer", new String[] {
				"Generer le projet", "Generer la page" }, this);
		popupGenerer.setIcon(new ImageIcon("images/generate.png"));
		popupGenerer.setToolTipText("Generer");

		// bouton Creation Projet
		boutonCreerProjet = new JButton("Nouveau projet");
		boutonCreerProjet.setIcon(new ImageIcon("images/project-new.png"));
		boutonCreerProjet.setToolTipText("Creer un nouveau projet");
		boutonCreerProjet.addActionListener(this);

		// bouton Creation Page
		boutonCreerPage = new JButton("Nouvelle page");
		boutonCreerPage.setIcon(new ImageIcon("images/page-new.png"));
		boutonCreerPage.setToolTipText("Creer une nouvelle page");
		boutonCreerPage.addActionListener(this);

		// bouton Ajout Titre
		boutonAjouterTitre = new JButton("Ajouter un titre");
		boutonAjouterTitre.setIcon(new ImageIcon("images/add-title.png"));
		boutonAjouterTitre.setToolTipText("Ajouter un titre a la page");
		boutonAjouterTitre.addActionListener(this);

		// bouton Ajout Paragraphe
		boutonAjouterParagraphe = new JButton("Ajouter un paragraphe");
		boutonAjouterParagraphe.setIcon(new ImageIcon(
				"images/add-paragraph.png"));
		boutonAjouterParagraphe
				.setToolTipText("Ajouter un paragraphe a la page");
		boutonAjouterParagraphe.addActionListener(this);

		// bouton Ajout Image
		boutonAjouterImage = new JButton("Ajouter une image");
		boutonAjouterImage.setIcon(new ImageIcon("images/add-image.png"));
		boutonAjouterImage.setToolTipText("Ajouter une image a la page");
		boutonAjouterImage.addActionListener(this);

		add(popupGenerer);
		add(boutonCreerProjet);
		add(boutonCreerPage);
		add(boutonAjouterTitre);
		add(boutonAjouterParagraphe);
		add(boutonAjouterImage);
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
			else
				Controleur.creerOptionPane("warning", "Action Impossible");

		if (b.equals(boutonAjouterTitre))
			if (path != null && path.getPathCount() >= 3)
				Controleur.creerPanelAjouterTitre(0, "");
			else
				Controleur.creerOptionPane("warning", "Action Impossible");

		if (b.equals(boutonAjouterParagraphe))
			if (path != null && path.getPathCount() >= 3)
				Controleur.creerPanelAjouterParagraphe(0, "");
			else
				Controleur.creerOptionPane("warning", "Action Impossible");

		if (b.equals(boutonAjouterImage))
			if (path != null && path.getPathCount() >= 3)
				Controleur.creerPanelAjouterImage(0, "");
			else
				Controleur.creerOptionPane("warning", "Action Impossible");
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
					Controleur.creerOptionPane("warning", "Action Impossible");
				else
				{
					for (Page page : projet.getAlPage())
					{
						if (page == null)
							continue;
						Controleur.metier.getGenerator().generateFile(projet,
								page);
					}

					Controleur.creerOptionPane("info", "Generation du projet "
							+ projet.getNom() + " accompli");
				}
			}
			else if (item.getText().equals("Generer la page"))
			{
				Projet projet = Controleur.metier.getProjetSelectionne();

				Page page = null;
				if (projet == null)
				{
					Controleur.creerOptionPane("warning", "Action Impossible");
				}
				else
				{
					page = projet.getPageSelectionne();

					if (page == null)
						Controleur.creerOptionPane("warning", "Action Impossible");

					else
					{
						Controleur.metier.getGenerator().generateFile(projet, page);
						Controleur.creerOptionPane("info",
								"Generation de la page " + page.getNom()
										+ " accomplie");
					}
				}
			}
		}
	}
}