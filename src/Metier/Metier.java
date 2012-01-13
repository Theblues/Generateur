package Metier;

import java.util.*;

import Utilitaire.*;

public class Metier
{
	private Generator generator;
	private ArrayList<Projet> alProjet;
	
	public Metier()
	{
		alProjet = new ArrayList<Projet>();
		generator = new Generator();
	}

	public Generator getGenerator()			{		return generator;	}
	public ArrayList<Projet> getAlProjet()	{		return alProjet;	}
	
	public void ajouterProjet(Projet p)
	{
		alProjet.add(p);
	}
}
