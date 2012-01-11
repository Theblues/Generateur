package IHM.Frame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

import IHM.Panel.PanelArbre;
import Main.*;

public class FenetreAjouterParagraphe extends JFrame implements ActionListener
{
	private JEditorPane editorPane;
	private JButton button;
	private static int cpt=1;
	private int statue;
	private int indice;
	private int indiceParagraphe;
	
	public FenetreAjouterParagraphe(int statue, String paragraphe, int indice, int indiceParagraphe)
	{
		this.statue = statue;
		this.indice = indice;
		this.indiceParagraphe = indiceParagraphe;
		
		setSize(500,500);
		setLayout(new BorderLayout());
		setTitle("Entrer un paragraphe");
		
		editorPane = new JEditorPane();
		editorPane.setEditable(true);
		editorPane.setContentType("text/plain");
		
		// si le statue est different de 0 on modif le texte
		if (statue != 0)
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
			Generateur.getGenerator().addParagraphe(editorPane.getText());
			Generateur.getFenetre().getArborescence().ajoutFils("element", "Paragraphe " + cpt, editorPane.getText());
			cpt++;
		}
		else
		{
			Generateur.getGenerator().modParagraphe(editorPane.getText(), indiceParagraphe);
			Generateur.getFenetre().getArborescence().setAlS(indice, editorPane.getText());
		}
		this.dispose();
	}
	
	
}
