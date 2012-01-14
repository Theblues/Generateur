package IHM.Frame;

import javax.swing.*;

import Main.Controleur;

import java.awt.GridLayout;
import java.awt.event.*;

public class PanelListeAction extends JPanel implements ActionListener
{
	private JButton boutonAjouterProjet;
	private JButton boutonGenerer;
	private JButton boutonAjouterPage;
	private JButton boutonAjouterTitre;
	private JButton boutonAjouterParagraphe;
	private JButton boutonAjouterImage;
	
	public PanelListeAction()
	{
		setLayout(new GridLayout(1, 5));
		
		boutonGenerer = new JButton("Generer");
		boutonGenerer.addActionListener(this);
		
		boutonAjouterProjet = new JButton("Ajouter un projet");
		boutonAjouterProjet.addActionListener(this);
		
		boutonAjouterPage = new JButton("Ajouter une page");
		boutonAjouterPage.addActionListener(this);
		
		boutonAjouterTitre = new JButton("Ajouter un titre");
		
		boutonAjouterParagraphe = new JButton("Ajouter un paragraphe");
		
		boutonAjouterImage = new JButton("Ajouter une image");
		
		add(boutonGenerer);
		add(boutonAjouterProjet);
		add(boutonAjouterPage);
		add(boutonAjouterTitre);
		add(boutonAjouterParagraphe);
		add(boutonAjouterImage);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();
		if (b.equals(boutonGenerer))
			Controleur.metier.getGenerator().generate();
		if (b.equals(boutonAjouterProjet))
			Controleur.creerFenetreCreerProjet();
        if (b.equals(boutonAjouterPage))
        	Controleur.creerFenetreCreerPage();
		if (b.equals(boutonAjouterTitre))
			Controleur.creerFenetreAjouterTitre(0,"",0);
		if (b.equals(boutonAjouterParagraphe))
			Controleur.creerFenetreAjouterParagraphe(0,"",0);
		if (b.equals(boutonAjouterImage))
			Controleur.creerFenetreAjouterImage(0, "", 0);
	}
	
	public void activerAjout()
	{
		// permet de ne pas ouvrir 5 fenetres
		boutonAjouterTitre.removeActionListener(this);
		boutonAjouterTitre.addActionListener(this);
		
		// permet de ne pas ouvrir 5 fenetres
		boutonAjouterParagraphe.removeActionListener(this);
		boutonAjouterParagraphe.addActionListener(this);
		
		// permet de ne pas ouvrir 5 fenetres
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
