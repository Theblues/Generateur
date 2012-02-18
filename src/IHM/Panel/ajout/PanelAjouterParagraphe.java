package IHM.Panel.ajout;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.*;

import java.io.*;
import java.util.*;

import IHM.Panel.*;
import Main.*;
import Utilitaire.*;

public class PanelAjouterParagraphe extends JPanel implements ActionListener
{
	private PanelListeModFont listeActionFont;
	private JEditorPane editorPane;
	private RTFEditorKit rtf;
	
	private JButton annuler;
	private JButton valider;
	
	private int statue;
	private int indiceParagraphe;
	
	public PanelAjouterParagraphe(int statue, String paragraphe, int indiceParagraphe)
	{
		this.statue = statue;
		this.indiceParagraphe = indiceParagraphe;
		
		setSize(500,500);
		setLayout(new BorderLayout());
		
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

	public void actionPerformed(ActionEvent ae) 
	{
		JButton b = (JButton) ae.getSource();
		if (valider.equals(b))
		{
			Projet projet = Controleur.metier.getProjetSelectionne();
			Page page = projet.getPageSelectionne();
			
			Document doc = editorPane.getDocument();
			if (doc.getLength() == 0)
				return;
			
			try
			{
				// mise en place du rtf dans un fichier temporaire
				FileOutputStream fichier = new FileOutputStream("temp/doc.txt");
				rtf.write(fichier, doc, 0, doc.getLength());
			}
			catch (IOException e){}
			catch (BadLocationException e) {}
			
			// permet de traiter le rtf
			ArrayList<String> alS = new ArrayList<String>();
			
			try
			{
				// lecture du fichier rtf
				FileReader fr = new FileReader("temp/doc.txt");
				Scanner sc = new Scanner (fr);
				while (sc.hasNext())
					alS.add(sc.next());
				
				fr.close();
			}catch(IOException e){}
			
			alS = traitementRTF(alS);
			
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
		Controleur.fenetre.getPanelAjout().supprimerPanel();
	}

	private ArrayList<String> traitementRTF(ArrayList<String> alS)
	{
		// les six premieres lignes sont inutiles
		/*for (int i = 0; i < 6; i++)
			alS.remove(0);*/
		
		for (String s: alS)
			System.out.println(s);
		return alS;
	}
}