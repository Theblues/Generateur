package IHM.Panel;

import javax.swing.*;

import Main.Controleur;
import Utilitaire.Page;
import Utilitaire.Projet;

import java.awt.GridLayout;
import java.awt.event.*;

public class PanelListeAction extends JPanel implements ActionListener
{
	private JButton boutonAjouterProjet;
	private JButton boutonGenererProjet;
	private JButton boutonGenerer;
	private JButton boutonAjouterPage;
	private JButton boutonAjouterTitre;
	private JButton boutonAjouterParagraphe;
	private JButton boutonAjouterImage;
	
	// bouton temporaire !!
	private JButton boutonAugmenterNiveau;
	private JButton boutonDiminuerNiveau;
	
	public PanelListeAction()
	{
		// setLayout(new GridLayout(1, 6));
		setLayout(new GridLayout(2, 1));
		
		JPanel panelNord = new JPanel();
		panelNord.setLayout(new GridLayout(1, 6));
		
		boutonGenererProjet = new JButton("Generer le projet");
		// creer un info bulle
		boutonGenererProjet.setToolTipText("Generer le projet");
		boutonGenererProjet.addActionListener(this);
		
		boutonGenerer = new JButton("Generer");
		boutonGenerer.addActionListener(this);
		
		boutonAjouterProjet = new JButton("Nouveau projet");
		boutonAjouterProjet.addActionListener(this);
		
		boutonAjouterPage = new JButton("Nouvelle page");
		
		boutonAjouterTitre = new JButton("Ajouter un titre");
		
		boutonAjouterParagraphe = new JButton("Ajouter un paragraphe");
		
		boutonAjouterImage = new JButton("Ajouter une image");
		
		panelNord.add(boutonGenererProjet);
		panelNord.add(boutonGenerer);
		panelNord.add(boutonAjouterProjet);
		panelNord.add(boutonAjouterPage);
		panelNord.add(boutonAjouterTitre);
		panelNord.add(boutonAjouterParagraphe);
		panelNord.add(boutonAjouterImage);
		
		JPanel panelSud = new JPanel();
		panelSud.setLayout(new GridLayout(1, 2));
		
		boutonAugmenterNiveau = new JButton("Monter la selection");
		boutonAugmenterNiveau.addActionListener(this);
		
		boutonDiminuerNiveau = new JButton("Descendre la selection");
		boutonDiminuerNiveau.addActionListener(this);
		
		panelSud.add(boutonDiminuerNiveau);
		panelSud.add(boutonAugmenterNiveau);
		
		add(panelNord);
		add(panelSud);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();
		if (b.equals(boutonGenererProjet))
		{
			Projet projet = Controleur.metier.getProjetSelectionne();
			if (projet == null)
				return;
			for (Page page : projet.getAlPage())
			{
				if (page == null)
					continue;
				Controleur.metier.getGenerator().generate(projet, page);
			}
		}
		if (b.equals(boutonGenerer))
		{
			Projet projet = Controleur.metier.getProjetSelectionne();
			if (projet == null)
				return;
			Page page = projet.getPageSelectionne();
			if (page == null)
				return;
			Controleur.metier.getGenerator().generate(projet, page);
		}
		if (b.equals(boutonAjouterProjet))
			Controleur.creerFenetreCreerProjet();
        if (b.equals(boutonAjouterPage))
        	Controleur.creerFenetreCreerPage();
		if (b.equals(boutonAjouterTitre))
			Controleur.creerFenetreAjouterTitre(0,"",0);
		if (b.equals(boutonAjouterParagraphe))
			Controleur.creerFenetreAjouterParagraphe(0,"",0);
		if (b.equals(boutonAjouterImage))
			Controleur.creerFenetreAjouterImage(0);
		if (b.equals(boutonDiminuerNiveau))
		{
			if (Controleur.fenetre.getArborescence().diminuerNiveau())
			{
				String type = Controleur.fenetre.getArborescence().getNomElement();
				Projet projet = Controleur.metier.getProjetSelectionne();
				if (projet == null)
					return;
				Page page = projet.getPageSelectionne();
				if (page == null)
					return;
				page.modOrdreElement(type, "diminuer");
			}
		}
		if (b.equals(boutonAugmenterNiveau))
		{
			if (Controleur.fenetre.getArborescence().augmenterNiveau())
			{
				String type = Controleur.fenetre.getArborescence().getNomElement();
				Projet projet = Controleur.metier.getProjetSelectionne();
				if (projet == null)
					return;
				Page page = projet.getPageSelectionne();
				if (page == null)
					return;
				page.modOrdreElement(type, "monter");
			}
		}
	}
	
	public void activerCreationPage()
	{
		boutonAjouterPage.removeActionListener(this);
		boutonAjouterPage.addActionListener(this);
	}
	
	public void activerAjout()
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

	public void desactiveAjout()
	{
		boutonAjouterTitre.removeActionListener(this);
		boutonAjouterParagraphe.removeActionListener(this);
		boutonAjouterImage.removeActionListener(this);
	}
}