package Main;

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
