package Main;

import java.util.Scanner;

import IHM.Frame.*;
import IHM.Frame.creation.*;
import IHM.Frame.ajout.*;
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
		else if (type.equals("error"))
			op.optionPaneErreur(texte);
	}
	
	public static void fermerFenetre()
	{
		fenetre.getArborescence().enregistrerArbre();
    	metier.enregistrerContenu();
        System.exit(0);
	}
	
	public static boolean verificationDesNom(String nom1, String nom2)
	{
		Scanner sc1 = new Scanner(nom1);
		Scanner sc2 = new Scanner(nom2);
		
		sc1.useDelimiter(" ");
		sc2.useDelimiter(" ");
		
		String type1 = sc1.next();
		String type2 = sc2.next();
		
		if (type1.equals(type2))
			return true;
		
		return false;
	}
	
	public static void main(String[] argv)
	{
		initialiser();
	}
}