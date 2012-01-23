package Main;

import IHM.Frame.*;
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
	
	public static void creerFenetreAjouterImage(int statue, String titre, int indiceImage)		
	{		
		new FenetreAjouterImage(statue, titre, indiceImage);			
	}
	
	public static void main(String[] argv)
	{
		initialiser();
	}
}
