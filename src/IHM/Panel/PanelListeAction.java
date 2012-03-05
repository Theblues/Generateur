package IHM.Panel;

import javax.swing.*;

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
	private JButton boutonMonterElement;
	private JButton boutonDescendreElement;

	private JButton boutonMonterPage;
	private JButton boutonDescendrePage;

	public PanelListeAction()
	{
		// tous mettre a gauche
		setLayout(new FlowLayout(FlowLayout.LEFT));

		MyPopupButton popupGenerer = new MyPopupButton("", new String[] {"Generer un projet", "Generer une page" }, this);
		popupGenerer.setIcon(new ImageIcon("images/generate.png"));
		popupGenerer.setToolTipText("Generer");
		
		boutonCreerProjet = new JButton();
		boutonCreerProjet.setIcon(new ImageIcon("images/project-new.png"));
		boutonCreerProjet.setToolTipText("Creer un nouveau projet");
		boutonCreerProjet.addActionListener(this);

		boutonCreerPage = new JButton();
		boutonCreerPage.setIcon(new ImageIcon("images/page-new.png"));
		boutonCreerPage.setToolTipText("Creer une nouvelle page");

		boutonAjouterTitre = new JButton();
		boutonAjouterTitre.setIcon(new ImageIcon("images/add-title.png"));
		boutonAjouterTitre.setToolTipText("Ajouter un titre a la page");

		boutonAjouterParagraphe = new JButton();
		boutonAjouterParagraphe.setIcon(new ImageIcon("images/Text-Editor.png"));
		boutonAjouterParagraphe.setToolTipText("Ajouter un paragraphe a la page");

		boutonAjouterImage = new JButton();
		boutonAjouterImage.setIcon(new ImageIcon("images/picture_add.jpg"));
		boutonAjouterImage.setToolTipText("Ajouter une image a la page");

		add(popupGenerer);
		add(boutonCreerProjet);
		add(boutonCreerPage);
		add(boutonAjouterTitre);
		add(boutonAjouterParagraphe);
		add(boutonAjouterImage);

		boutonMonterElement = new JButton("Monter l'element");
		boutonDescendreElement = new JButton("Descendre l'element");
		boutonMonterPage = new JButton("Monter la page");
		boutonDescendrePage = new JButton("Descendre la page");

		add(boutonDescendreElement);
		add(boutonMonterElement);
		add(boutonDescendrePage);
		add(boutonMonterPage);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();
		if (b.equals(boutonCreerProjet))
			Controleur.creerPanelCreerProjet();
		if (b.equals(boutonCreerPage))
			Controleur.creerPanelCreerPage();
		if (b.equals(boutonAjouterTitre))
			Controleur.creerPanelAjouterTitre(0, "");
		if (b.equals(boutonAjouterParagraphe))
			Controleur.creerPanelAjouterParagraphe(0, "");
		if (b.equals(boutonAjouterImage))
			Controleur.creerPanelAjouterImage(0);
		if (b.equals(boutonDescendreElement))
			Controleur.descendreElement();
		if (b.equals(boutonMonterElement))
			Controleur.monterElement();
		if (b.equals(boutonDescendrePage))
			Controleur.descendrePage();
		if (b.equals(boutonMonterPage))
			Controleur.monterPage();
	}

	public void activerBoutonAjoutPage()
	{
		boutonCreerPage.removeActionListener(this);
		boutonCreerPage.addActionListener(this);
	}

	public void activerBoutonModPage()
	{
		boutonMonterPage.removeActionListener(this);
		boutonMonterPage.addActionListener(this);

		boutonDescendrePage.removeActionListener(this);
		boutonDescendrePage.addActionListener(this);
	}

	public void desactiverBoutonModPage()
	{
		boutonMonterPage.removeActionListener(this);
		boutonDescendrePage.removeActionListener(this);
	}

	public void activerBoutonAjoutElement()
	{
		// permet de ne pas ouvrir n fenetres
		boutonAjouterTitre.removeActionListener(this);
		boutonAjouterTitre.addActionListener(this);

		// permet de ne pas ouvrir n fenetres
		boutonAjouterParagraphe.removeActionListener(this);
		boutonAjouterParagraphe.addActionListener(this);

		// permet de ne pas ouvrir n fenetres
		boutonAjouterImage.removeActionListener(this);
		boutonAjouterImage.addActionListener(this);
	}

	public void desactiverBoutonAjoutElement()
	{
		boutonAjouterTitre.removeActionListener(this);
		boutonAjouterParagraphe.removeActionListener(this);
		boutonAjouterImage.removeActionListener(this);
	}

	public void activerBoutonModElement()
	{
		boutonMonterElement.removeActionListener(this);
		boutonMonterElement.addActionListener(this);

		boutonDescendreElement.removeActionListener(this);
		boutonDescendreElement.addActionListener(this);
	}

	public void desactiverBoutonModElement()
	{
		boutonMonterElement.removeActionListener(this);
		boutonDescendreElement.removeActionListener(this);
	}

	class MyPopupButton extends PopupButton
	{
		public MyPopupButton(String label, String[] items, Container parent)
		{
			super(label, items, parent);
		}

		public void actionPerformed(ActionEvent evt)
		{
			System.out.println(evt.getSource().toString());
			JMenuItem item = (JMenuItem) evt.getSource();
			
			if (item.getText().equals("Generer un projet"))
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
			else if (item.getText().equals("Generer une page"))
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