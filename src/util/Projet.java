package util;

import java.io.*;
import java.text.*;
import java.util.*;

import Main.Controleur;

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
	
	public void setPageSelectionne(Page p)
	{
		pageSelectionne = p;
	}
	
	public void setNom(String nom)
	{
		File file = new File(cheminDossier + "/"+ this.nom);
		
		this.nom = nom;
		
		Controleur.fenetre.getArborescence().renommerProjet(nom);
		
		file.renameTo(new File(cheminDossier + "/" + nom));
		
		for (Page p : alPage)
			p.setFile(new File(cheminDossier + "/" + nom + "/" + p.getNom() + ".html"));
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