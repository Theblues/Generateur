package Main;

import Utilitaire.*;
import IHM.Frame.*;

public class Generateur
{
	private static Fenetre fenetre;
	private static Generator generator;

	public Generateur()
	{
		fenetre = new Fenetre();
		generator = new Generator();
	}
	
	public static Fenetre getFenetre()		{	return fenetre;		}
	public static Generator getGenerator()	{	return generator; 	}
	
	public static void creerFenetreCreerPage()			{		new FenetreCreerPage();				}
	public static void creerFenetreAjouterParagraphe()	{		new FenetreAjouterParagraphe();		}
	public static void creerFenetreAjouterTitre()		{		new FenetreAjouterTitre();			}
	public static void creerFenetreAjouterImage()		{		new FenetreAjouterImage();			}
	
	public static void ajouterParagraphe(String text)
	{
		// TODO Auto-generated method stub
		generator.addParagraphe(text);
	}
	
	public static void ajouterTitre(String text)
	{
		// TODO Auto-generated method stub
		generator.addTitre(text);
	}
	
	public static void generer()
	{
		// TODO Auto-generated method stub
		generator.generate();
	}
	
	public static void main(String[] argv)
	{
		new Generateur();
	}	
}
