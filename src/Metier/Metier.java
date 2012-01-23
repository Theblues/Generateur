package Metier;

import java.io.Serializable;
import java.util.*;

import Utilitaire.*;

public class Metier implements Serializable
{
	private Generator generator;
	private ArrayList<Projet> alProjet;
	private Projet projetSelectionne;
	
	public Metier()
	{
		alProjet = new ArrayList<Projet>();
		generator = new Generator();
	}

	public Generator getGenerator()			{	return generator;			}
	public Projet getProjetSelectionne()	{	return projetSelectionne;	}
	public ArrayList<Projet> getAlProjet()	{	return alProjet;			}
	
	public void ajouterProjet(Projet p)
	{
		alProjet.add(p);
	}

	public Projet getProjet(String nom)
	{
		for (Projet p : alProjet)
			if (nom.equals(p.getNom()))
				return p;
		
		return null;
	}
	
	public void setProjetSelectionne(Projet p)
	{
		projetSelectionne = p;
	}
}
