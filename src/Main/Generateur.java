package Main;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.tree.TreePath;
import javax.swing.*;

import Utilitaire.*;
import IHM.Frame.*;
import Metier.*;

public class Generateur
{
	public static Fenetre fenetre;
	public static Metier metier;

	public Generateur()
	{
		metier = new Metier();
		fenetre = new Fenetre();
	}
	
	public static void creerFenetreCreerProjet()
	{
		new FenetreCreerProjet();
	}

	public static void creerFenetreCreerPage()			
	{		
		new FenetreCreerPage();				
	}
	
	public static void creerFenetreAjouterParagraphe(int statue, String titre, int indiceParagraphe)
	{		
		new FenetreAjouterParagraphe(statue, titre, indiceParagraphe);		
	}
	
	public static void creerFenetreAjouterTitre(int statue, String titre, int indiceTitre)		
	{		
		new FenetreAjouterTitre(statue, titre, indiceTitre);
	}
	
	public static void creerFenetreAjouterImage(int statue, String titre, int indiceImage)		
	{		
		new FenetreAjouterImage(statue, titre, indiceImage);			
	}
	
	public static void main(String[] argv)
	{
		new Generateur();
	}

	public static void previsualisation()
	{
		String contenu = metier.getGenerator().previsualisation();
		
		fenetre.previsualisation(contenu);
	}
	
	public static void ajouterStyleParagraphe() {
		
		JTextPane textPanel = fenetre.getjEditor();
		
		fenetre.initStylesForTextPane(textPanel, null, "paragraphe");

		Document doc = textPanel.getDocument();
		try {
			doc.insertString(textPanel.getSelectionStart(), " ",textPanel.getStyle("paragraphe"));
		} catch (BadLocationException ble) {
			System.err.println("Couldn't insert initial text.");
		}
	}

}
