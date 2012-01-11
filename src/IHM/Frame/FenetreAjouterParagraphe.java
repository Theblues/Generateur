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
	private String paragraphe;
	private int indiceParagraphe;
	
	public FenetreAjouterParagraphe(int statue, String paragraphe, int indice, int indiceParagraphe)
	{
		this.statue = statue;
		this.paragraphe = paragraphe;
		this.indice = indice;
		this.indiceParagraphe = indiceParagraphe;
		
		setSize(500,500);
		setLayout(new BorderLayout());
		setTitle("Entrer un paragraphe");
		
		
		// 0 = ajout, 1 = modif
		if (statue == 0) {
			editorPane = new JEditorPane();
			editorPane.setEditable(true);
			editorPane.setContentType("text/plain");
			button = new JButton("Valider");
			button.addActionListener(this);
			add(editorPane,BorderLayout.CENTER);
			add(button,BorderLayout.SOUTH);
			
		}else {
			editorPane = new JEditorPane();
			editorPane.setEditable(true);
			editorPane.setContentType("text/plain");
			editorPane.setText(paragraphe);
			button = new JButton("Valider");
			button.addActionListener(this);
			add(editorPane,BorderLayout.CENTER);
			add(button,BorderLayout.SOUTH);	
		}
		
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if (statue == 0) {
			Generateur.getGenerator().addParagraphe(editorPane.getText());
			Generateur.getFenetre().getArborescence().ajoutFils("Paragraphe " + cpt, editorPane.getText());
			cpt++;
			this.dispose();
		}else{
			Generateur.getGenerator().modParagraphe(editorPane.getText(), indiceParagraphe);
			Generateur.getFenetre().getArborescence().setAlS(indice, editorPane.getText());
			this.dispose();
		}
	}
	
	
}
