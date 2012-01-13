package IHM.Frame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Main.*;
import Utilitaire.*;

public class FenetreAjouterParagraphe extends JFrame implements ActionListener
{
	private JEditorPane editorPane;
	private JButton button;
	
	private Page page;
	private int statue;
	private int indiceParagraphe;
	
	public FenetreAjouterParagraphe(Page page, int statue, String paragraphe, int indiceParagraphe)
	{
		this.page = page;
		this.statue = statue;
		this.indiceParagraphe = indiceParagraphe;
		
		setSize(500,500);
		setLayout(new BorderLayout());
		setTitle("Entrer un paragraphe");
		
		editorPane = new JEditorPane();
		editorPane.setEditable(true);
		editorPane.setContentType("text/plain");
		
		editorPane.setText(paragraphe);

		add(editorPane,BorderLayout.CENTER);
		
		button = new JButton("Valider");
		button.addActionListener(this);
		add(button,BorderLayout.SOUTH);	
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (statue == 0) 
		{
			Generateur.metier.getAlProjet().get(0).getPage(page).ajouterParagraphe(editorPane.getText());
			int cpt = Generateur.metier.getAlProjet().get(0).getPage(page).getAlParagraphe().size();
			Generateur.fenetre.getArborescence().ajoutFils("element", "Paragraphe " + cpt);
		}
		else
			Generateur.metier.getAlProjet().get(0).getPage(page).modParagraphe(editorPane.getText(), indiceParagraphe);
		
		this.dispose();
	}
	
	
}
