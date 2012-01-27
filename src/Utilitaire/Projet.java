package Utilitaire;

import java.io.Serializable;
import java.util.*;

public class Projet implements Serializable
{
	private String nom;
	private ArrayList<Page> alPage;
	private Page pageSelectionne;
	private String cheminDossier;
	
	public Projet(String nom, String chemin)
	{
		this.nom = nom;
		this.cheminDossier = chemin;
		alPage = new ArrayList<Page>();
	}

	public String getNom()				{	return nom;					}
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
	
	public Page getPage(Page page)
	{
		for (Page p : alPage)
			if (page.equals(p))
				return p;
		
		return null;
	}
	
	public void setPageSelectionne(Page p)
	{
		pageSelectionne = p;
	}

	public void ajouterPage(Page p)
	{
		alPage.add(p);
	}
	
	@Override
	public String toString()
	{
		return "Projet [nom=" + nom + ", alPage=" + alPage
				+ ", pageSelectionne=" + pageSelectionne + "]";
	}
}