package Main;

import java.io.*;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import IHM.Frame.*;
import IHM.OptionPane.OptionPane;
import Metier.*;

public class Controleur
{
	public static Fenetre fenetre;
	public static Metier metier;

	public static void initialiser()
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
	
	public static void creerFenetreAjouterImage(int statue)		
	{		
		new FenetreAjouterImage(statue);			
	}
	
	public static void CreerOptionPane(String type, String texte)
	{
		OptionPane op = new OptionPane();
		if (type.equals("info"))
			op.optionPaneInfo(texte);
		else if (type.equals("warning"))
			op.optionPaneAlerte(texte);
		else
			op.optionPaneErreur(texte);
	}
	
	public static void enregistrer()
	{
		JTree arbre = fenetre.getArborescence().getArbre();
		DefaultMutableTreeNode racine = fenetre.getArborescence().getRacine();
		
		try {
			FileOutputStream fichier = new FileOutputStream("arbre.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(arbre);
			oos.writeObject(racine);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] argv)
	{
		initialiser();
	}

	
}
