package Main;

import Fenetre.*;

public class Generateur
{
	private static Fenetre fenetre;

	public Generateur()
	{
		fenetre = new Fenetre();
	}
	
	public static Fenetre getFenetre()	{		return fenetre;		}
	
	public static void FenetreCreerPage()
	{
		new FenetreCreerPage();
	}
	
	public static void main(String[] argv)
	{
		new Generateur();
	}
}
