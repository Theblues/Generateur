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
	
	public FenetreAjouterParagraphe()
	{
		setSize(500,500);
		setLayout(new BorderLayout());
		setTitle("Entrer un titre");
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
		Generateur.ajouterParagraphe(editorPane.getText());
		Generateur.getFenetre().getArborescence().ajoutFils("Paragraphe", editorPane.getText());
		this.dispose();
	}
	
	
}
