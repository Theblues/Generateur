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
	private static int cpt;
	
	public FenetreAjouterParagraphe()
	{
		setSize(500,500);
		setLayout(new BorderLayout());
		setTitle("Entrer un paragraphe");
		
		cpt =1;
		
		editorPane = new JEditorPane();
		editorPane.setEditable(true);
		editorPane.setContentType("text/plain");
		button = new JButton("Valider");
		button.addActionListener(this);
		add(editorPane,BorderLayout.CENTER);
		add(button,BorderLayout.SOUTH);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		Generateur.getGenerator().addParagraphe(editorPane.getText());
		Generateur.getFenetre().getArborescence().ajoutFils("Paragraphe" + cpt, editorPane.getText());
		this.dispose();
	}
	
	
}
