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
	
	private int statue;
	private int indiceParagraphe;
	
	public FenetreAjouterParagraphe(int statue, String paragraphe, int indiceParagraphe)
	{
		this.statue = statue;
		this.indiceParagraphe = indiceParagraphe;
		
		setSize(500,500);
		setLayout(new BorderLayout());
		setTitle("Entrer un paragraphe");
		
		editorPane = new JEditorPane();
		editorPane.setEditable(true);
		editorPane.setContentType("text/plain");
		
		editorPane.setText(paragraphe);

		JScrollPane scroller = new JScrollPane( editorPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		
		add(scroller,BorderLayout.CENTER);
		
		button = new JButton("Valider");
		button.addActionListener(this);
		add(button,BorderLayout.SOUTH);	
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		Projet projet = Generateur.metier.getProjetSelectionne();
		Page page = projet.getPageSelectionne();
		
		if (statue == 0) 
		{
			projet.getPage(page).ajouterParagraphe(editorPane.getText());
			int cpt = projet.getPage(page).getAlParagraphe().size();
			Generateur.fenetre.getArborescence().ajoutFils("element", "Paragraphe " + cpt);
		}
		else
			projet.getPage(page).modParagraphe(editorPane.getText(), indiceParagraphe);
		
		this.dispose();
		
	}
	
	
}
