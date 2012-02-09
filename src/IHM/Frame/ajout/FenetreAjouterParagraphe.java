package IHM.Frame.ajout;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import IHM.Panel.PanelListeModFont;
import Main.*;
import Utilitaire.*;

public class FenetreAjouterParagraphe extends JFrame implements ActionListener
{
	private PanelListeModFont listeActionFont;
	private JTextPane textPane;
	
	private JButton annuler;
	private JButton valider;
	
	private int statue;
	private int indiceParagraphe;
	
	public FenetreAjouterParagraphe(int statue, String paragraphe, int indiceParagraphe)
	{
		this.statue = statue;
		this.indiceParagraphe = indiceParagraphe;
		
		setSize(500,500);
		setLayout(new BorderLayout());
		setTitle("Entrer un paragraphe");
		
		textPane = new JTextPane();
		textPane.setEditable(true);
		textPane.setContentType("text/plain");
		
		textPane.setText(paragraphe);

		JScrollPane scroller = new JScrollPane(textPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		
		listeActionFont = new PanelListeModFont(textPane);
		
		add(listeActionFont, BorderLayout.NORTH);
		add(scroller);
		
		JPanel panSud = new JPanel();
		panSud.setLayout(new BorderLayout());
		
		JPanel panBouton = new JPanel();
		annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		panBouton.add(annuler);
		
		valider = new JButton("Valider");
		valider.addActionListener(this);
		panBouton.add(valider);
		
		panSud.add(panBouton, BorderLayout.EAST);
		add(panSud, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		JButton b = (JButton) e.getSource();
		if (valider.equals(b))
		{
			Projet projet = Controleur.metier.getProjetSelectionne();
			Page page = projet.getPageSelectionne();
			
			if (textPane.getText().length() == 0)
				return;
			
			if (statue == 0) 
			{
				page.ajouterParagraphe(textPane.getText());
				int cpt = page.getAlParagraphe().size();
				page.ajouterOrdre("Paragraphe " + cpt);
				Controleur.fenetre.getArborescence().ajoutFils("element", "Paragraphe " + cpt);
			}
			else
				page.modParagraphe(textPane.getText(), indiceParagraphe);
		}
		
		this.dispose();
	}
}