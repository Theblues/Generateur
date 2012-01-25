package IHM.Frame;

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
	private JButton button;
	
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
		
		// TODO (pour sarah) modifier les boutons
		button = new JButton("Valider");
		button.addActionListener(this);
		add(button,BorderLayout.SOUTH);	
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		Projet projet = Controleur.metier.getProjetSelectionne();
		Page page = projet.getPageSelectionne();
		
		if (textPane.getText() == "")
			return;
		
		if (statue == 0) 
		{
			projet.getPage(page).ajouterParagraphe(textPane.getText());
			int cpt = projet.getPage(page).getAlParagraphe().size();
			Controleur.fenetre.getArborescence().ajoutFils("element", "Paragraphe " + cpt);
		}
		else
			projet.getPage(page).modParagraphe(textPane.getText(), indiceParagraphe);
		
		this.dispose();
	}
}
