package Utilitaire;

import java.io.*;
import java.util.*;

public class Page implements Serializable
{
	private String nom;
	// ArrayList qui permet d'avoir les titres/paragraphes dans l'ordre
	private ArrayList<String> alOrdre;
	// ArrayList ou il y a les titres
	private ArrayList<String> alTitre;
	// ArrayList ou il y a les paragraphes
	private ArrayList<String> alParagraphe;
	// ArrayList ou il y a les "images"
	private ArrayList<String> alImage;
	
	public Page(String nom)
	{
		this.nom = nom;
		alOrdre = new ArrayList<String>();
		alTitre = new ArrayList<String>();
		alParagraphe = new ArrayList<String>();
		alImage = new ArrayList<String>();
	}
	
	public String getNom()						{	return nom;				}
	public ArrayList<String> getAlOrdre()		{	return alOrdre;			}
	public ArrayList<String> getAlTitre()		{	return alTitre;			}
	public ArrayList<String> getAlParagraphe()	{	return alParagraphe;	}
	public ArrayList<String> getAlImage()		{	return alImage;			}

	public void ajouterOrdre(String texte)
	{
		alOrdre.add(texte);
	}
	
	public void ajouterTitre(String titre)
	{
		alTitre.add(titre);
	}
	
	public void modTitre(String titre, int indice) 
	{
		alTitre.remove(indice-1);
		alTitre.add(indice-1, titre);
	}
	
	public void ajouterParagraphe(String s)
	{
	    alParagraphe.add(s);
	}
	
	public void modParagraphe(String s, int indice)
	{
	    alParagraphe.remove(indice-1);
		alParagraphe.add(indice-1, s);
	}
	
	public void ajouterImage(String s)
	{
	    alImage.add(s);
	}
	
	public void modImage(String s, int indice)
	{
	    alImage.remove(indice-1);
		alImage.add(indice-1, s);
	}
	
	@Override
	public String toString()
	{
		return "Page [nom=" + nom + ", alTitre=" + alTitre + ", alParagraphe="
				+ alParagraphe + ", alImage=" + alImage + "]";
	}
}