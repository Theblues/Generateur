package util;

import java.io.*;
import java.text.*;
import java.util.*;

import main.Controleur;

public class Projet implements Serializable
{
	private String nom;
	private String auteur;
	private String annee;
	private ArrayList<Page> alPage;
	private Page pageSelectionne;
	private String cheminDossier;
	private String style;
	
	public Projet(String nom, String auteur, String style, String chemin)
	{
		this.nom = nom;
		this.auteur = auteur;
		
		this.style = style;
		this.cheminDossier = chemin;
		alPage = new ArrayList<Page>();
		
		String format = "yyyy";
		SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
		Date date = new java.util.Date();
		annee = formater.format(date);
	}

	public String getNom()				{	return nom;					}
	public String getAuteur()			{	return auteur;				}
	public String getAnnee()			{	return annee;				}
	public String getStyle()			{	return style;				}
	public ArrayList<Page> getAlPage()	{	return alPage;				}
	public String getCheminDossier()	{	return cheminDossier;		}
	public Page getPageSelectionne()	{	return pageSelectionne;		}
	
	public Page getPage(String nom)
	{
		for (Page p : alPage)
			if (nom.equals(p.getNom()))
				return p;
		
		return null;
	}
	
	public int getIndicePage(Page page)
	{
		int i = 0;
		for (Page p : alPage)
		{
			if (p.equals(page))
				return i;
			
			i++;
		}
		
		return -1;
	}
	
	public void setPageSelectionne(Page p)
	{
		pageSelectionne = p;
	}
	
	public void setNom(String nom)
	{
		// on modifie le nom du dossier
		this.nom = nom;
		
		// on recupere le fichier du dossier
		File file = new File(cheminDossier + "/"+ nom);
		
		// on modifie l'arbre
		Controleur.fenetre.getArborescence().renommerNoeud("projet", nom);
		
		// on renomme le nom
		Scanner sc = new Scanner(nom);
		sc.useDelimiter(" ");
		String nameProjet = sc.next();
		while (sc.hasNext())
			nameProjet += "_" + sc.next();
		
		// on renomme le fichier
		file.renameTo(new File(cheminDossier + "/" + nameProjet));
		
		// on modifie toutes les pages
		for (Page p : alPage)
			p.setFile(new File(cheminDossier + "/" + nameProjet + "/" + p.getNom() + ".html"));
	}
	
	public void setAuteur(String auteur)
	{
		this.auteur = auteur;
	}	

	public void ajouterPage(Page p)
	{
		alPage.add(p);
	}
	
	public void modOrdrePage(String type, String niveau)
	{
		int i = 0;
		Page page = null;
		for (Page p : alPage)
		{
			if (p.getNom().equals(type))
			{
				page = p;
				break;
			}
			i++;
		}
		alPage.remove(i);
		if (niveau.equals("monter"))
			alPage.add(i-1, page);
		if (niveau.equals("descendre"))
			alPage.add(i+1, page);	
	}
	
	@Override
	public String toString()
	{
		return "Projet [nom=" + nom + ", alPage=" + alPage
				+ ", pageSelectionne=" + pageSelectionne + "]";
	}
}