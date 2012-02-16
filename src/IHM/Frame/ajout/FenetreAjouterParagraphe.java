package IHM.Frame.ajout;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.*;

import IHM.Panel.PanelListeModFont;
import Main.*;
import Utilitaire.*;

public class FenetreAjouterParagraphe extends JFrame implements ActionListener
{
	private PanelListeModFont listeActionFont;
	private JEditorPane editorPane;
	private RTFEditorKit rtf;
	
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
		
		editorPane = new JTextPane();
		editorPane.setEditable(true);
		editorPane.setContentType("text/rtf");
		
		rtf = new RTFEditorKit();
		editorPane.setEditorKit(rtf);
		
		editorPane.setText(paragraphe);

		JScrollPane scroller = new JScrollPane(editorPane,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		
		listeActionFont = new PanelListeModFont(editorPane);
		
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
			
			Document doc = editorPane.getDocument();
			if (doc.getLength() == 0)
				return;
			
			try
			{
				FileOutputStream fichier = new FileOutputStream("temp/doc.txt");
				
				rtf.write(fichier, doc, 0, doc.getLength());
			}
			catch (IOException ioe){}
			catch (BadLocationException ble) {}
			/*
			if (editorPane.getText().length() == 0)
				return;
			
			if (statue == 0) 
			{
				page.ajouterParagraphe(editorPane.getText());
				int cpt = page.getAlParagraphe().size();
				page.ajouterOrdre("Paragraphe " + cpt);
				Controleur.fenetre.getArborescence().ajoutFils(null, "element", "Paragraphe " + cpt);
			}
			else
				page.modParagraphe(editorPane.getText(), indiceParagraphe);
				*/
		}
		
		this.dispose();
	}
}