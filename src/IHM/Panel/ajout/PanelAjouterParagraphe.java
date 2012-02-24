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
import util.*;

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
		
		setLayout(new BorderLayout());
		
		editorPane = new JTextPane();
		editorPane.setEditable(true);
		editorPane.setContentType("text/html");
		
		/*
		 * Test html
		 */
		String s = "";
		try
		{
			// lecture du fichier rtf
			FileReader fr = new FileReader("temp/doc.txt");
			Scanner sc = new Scanner (fr);
			sc.useDelimiter("\n");
			while (sc.hasNext())
				s += sc.next() + "\n";
			
			fr.close();
		}catch(IOException e){}
		editorPane.setText(s);

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
		
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Votre paragraphe"),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)),
						this.getBorder()));
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) 
	{
		JButton b = (JButton) ae.getSource();
		if (valider.equals(b))
		{
			Projet projet = Controleur.metier.getProjetSelectionne();
			Page page = projet.getPageSelectionne();
			
			String paragrapheHTML = editorPane.getText();
			if (paragrapheHTML.length() == 0)
				return;
			
			
			// permet de traiter le html
			ArrayList<String> alS = new ArrayList<String>();
			
			Scanner sc = new Scanner (paragrapheHTML);
			sc.useDelimiter("\n");
			while (sc.hasNext())
				alS.add(sc.next());
				
			
			String paragraphe = traitementHTML(alS);
			
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

	private String traitementHTML(ArrayList<String> alS)
	{
		String s = "";
		// les six premieres lignes sont inutiles
		for (int i = 0; i < 5; i++)
			alS.remove(0);
		
		for (String ligne: alS)
		{
			if (ligne.contains("<p "))
				continue;
			if(ligne.contains("</p>"))
			{
				s += "\n";
				continue;
			}
			if (ligne.contains("<u>"))
			{
				s = ligne.replace("<u>", "<a href=\"");
			}
			if (ligne.contains("</body>"))
				break;
			
			s += ligne;
		}
		System.out.println(s);
		return s;
	}
}