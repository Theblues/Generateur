package Main;

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
	
	public static void creerFenetreAjouterParagraphe(Page page, int statue, String titre, int indiceParagraphe)
	{		
		new FenetreAjouterParagraphe(page, statue, titre, indiceParagraphe);		
	}
	
	public static void creerFenetreAjouterTitre(Page page, int statue, String titre, int indiceTitre)		
	{		
		new FenetreAjouterTitre(page, statue, titre, indiceTitre);
	}
	
	public static void creerFenetreAjouterImage(Page page, int statue, String titre, int indiceImage)		
	{		
		new FenetreAjouterImage(page, statue, titre, indiceImage);			
	}
	
	public static void main(String[] argv)
	{
		new Generateur();
	}

}
