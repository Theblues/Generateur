package Metier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import Utilitaire.*;

public class Metier
{
	private Generator generator;
	private ArrayList<Projet> alProjet;
	private Projet projetSelectionne;
	
	public Metier()
	{
		alProjet = init();
		generator = new Generator();		
	}

	private ArrayList<Projet> init()
	{
		try {
			FileInputStream fichier = new FileInputStream("temp/metier.dat");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			alProjet = (ArrayList<Projet>) ois.readObject();
		}
		catch (IOException ignored) {}            // probleme de lecture
		catch (ClassNotFoundException e)	{}
		
		return (alProjet != null) ? alProjet : new ArrayList<Projet>();
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
	
	public void enregistrerContenu()
	{
		try {
			FileOutputStream fichier = new FileOutputStream("temp/metier.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(alProjet);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) 
		{
			e.printStackTrace();
		}
	}
}
