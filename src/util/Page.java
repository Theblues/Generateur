package util;

import java.io.*;
import java.util.*;

import main.Controleur;

public class Page implements Serializable
{
	// fichier de la page
	private File file;
	// nom de la page
	private String nom;
	// List qui permet d'avoir les titres/paragraphes dans l'ordre
	private ArrayList<String> alOrdre;
	// List ou il y a les titres
	private ArrayList<String> alTitre;
	// List ou il y a les paragraphes qui sera dans le code HTML
	private ArrayList<String> alParagrapheHTML;
	// List ou il y a le chemin des images qui sera dans le code HTML
	private ArrayList<String> alImageHTML;
	// List ou il y a le chemin de l'image
	private ArrayList<String> alImage;
	
	public Page(File file, String nom)
	{
		this.file = file;
		this.nom = nom;
		alOrdre = new ArrayList<String>();
		alTitre = new ArrayList<String>();
		alParagrapheHTML = new ArrayList<String>();
		alImageHTML = new ArrayList<String>();
		alImage = new ArrayList<String>();
	}
	
	/*
	 * Accesseurs
	 */
	public File getFile()							{	return file;				}
	public String getNom()							{	return nom;					}
	public ArrayList<String> getAlOrdre()			{	return alOrdre;				}
	public ArrayList<String> getAlTitre()			{	return alTitre;				}
	public ArrayList<String> getAlParagrapheHTML()	{	return alParagrapheHTML;	}
	public ArrayList<String> getAlImageHTML()		{	return alImageHTML;			}
	public ArrayList<String> getAlImage()			{	return alImage;				}

	/*
	 * @return int
	 * retourne l'indice de l'element
	 */
	public int getIndiceElement(String nomElement) 
	{
		int i = 0;
		for (String s : alOrdre)
		{
			if (s.equals(nomElement))
				return i;
			
			i++;
		}
				
		return -1;
	}
	
	
	/*
	 * Modificateurs
	 */
	public void setNom(String nom)
	{
		this.nom = nom;
		
		// on modifie le nom dans l'arbre
		Controleur.fenetre.getArborescence().renommerNoeud("page", nom);
		
		// on renomme le nom
		Scanner sc = new Scanner(nom);
		sc.useDelimiter(" ");
		String namePage = sc.next();
		while (sc.hasNext())
			namePage += "_" + sc.next();
		
		String chemin = file.getAbsolutePath();
		
		// on recupere le chemin sans le nom de la page
		String [] tabString = chemin.split("/");
		chemin = "";
		for (int i = 0; i < tabString.length-1; i++)
			chemin += "/" + tabString[i];
		
		// on modifie le fichier
		setFile(new File(chemin + "/" + namePage + ".html"));
	}
	
	public void setFile(File file)
	{
		// on renomme le fichier en vrai
		this.file.renameTo(file);
		// on remplace le fichier dans le programme
		this.file = file;
	}
	
	
	/*
	 * Ajout aux listes des elements
	 */
	
	public void ajouterOrdre(String texte)
	{
		alOrdre.add(texte);
	}
	
	public void ajouterTitre(String titre)
	{
		alTitre.add(titre);
	}
	
	public void ajouterParagrapheHTML(String s)
	{
	    alParagrapheHTML.add(s);
	}
	
	public void ajouterImage(String s)
	{
	    alImage.add(s);
	}
	
	public void ajouterImageHTML(String s)
	{
	    alImageHTML.add(s);
	}
	
	
	/*
	 * Modification des elements
	 */
	public void modTitre(String titre, int indice) 
	{
		alTitre.remove(indice);
		alTitre.add(indice, titre);
	}
	
	public void modImageHTML(String chemin, int indice)
	{
		alImageHTML.remove(indice-1);
		alImageHTML.add(indice-1, chemin);
	}
	
	public void modParagrapheHTML(String paragrapheHTML, int indice)
	{
		alParagrapheHTML.remove(indice-1);
		alParagrapheHTML.add(indice-1, paragrapheHTML);
	}
	
	public void modImage(String s, int indice)
	{
	    alImage.remove(indice-1);
		alImage.add(indice-1, s);
	}
	
	
	/*
	 * Modification de l'ordre des elements
	 */
	
	public void modOrdreElement(String type, String niveau)
	{
		// on parcours la liste pour savoir l'indice de l'element que l'on recherche
		int i = 0;
		for (String s : alOrdre)
		{
			if (s.equals(type))
				break;
			i++;
		}
		
		// On modifie l'ordre des elements dans la liste
		alOrdre.remove(i);
		if (niveau.equals("monter"))
			alOrdre.add(i-1, type);
		if (niveau.equals("descendre"))
			alOrdre.add(i+1, type);
	}
}