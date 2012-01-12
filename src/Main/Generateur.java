package Main;

import java.io.*;
import java.util.*;

import Utilitaire.*;
import IHM.Frame.*;

public class Generateur
{
	public static Fenetre fenetre;
	public static Generator generator;
	public static ArrayList<Projet> alProjet;

	public Generateur()
	{
		alProjet = new ArrayList<Projet>();
		fenetre = new Fenetre();
		generator = new Generator();
	}

	public static void creerFenetreCreerPage()			
	{		
		new FenetreCreerPage();				
	}
	
	public static void creerFenetreAjouterParagraphe(Page page, int statue, String titre, int indice, int indiceParagraphe)
	{		
		new FenetreAjouterParagraphe(page, statue, titre, indice, indiceParagraphe);		
	}
	
	public static void creerFenetreAjouterTitre(Page page, int statue, String titre, int indice, int indiceTitre)		
	{		
		new FenetreAjouterTitre(page, statue, titre, indice, indiceTitre);
	}
	
	public static void creerFenetreAjouterImage()		
	{		
		new FenetreAjouterImage();			
	}
	
	public static void main(String[] argv)
	{
		new Generateur();
	}

}
