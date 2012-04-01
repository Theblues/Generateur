package main;

import ihm.frame.*;
import ihm.panel.*;
import ihm.panel.creation.*;
import ihm.panel.ajout.*;
import ihm.util.*;
import metier.*;
import util.*;

public class Controleur
{
	public static Fenetre fenetre;
	public static Metier metier;

	public static void initialiser()
	{
		metier = new Metier();
		fenetre = new Fenetre();
	}
	
	public static void creerPanelCreerProjet()
	{
		fenetre.getPanelAjout().supprimerPanel();
		fenetre.getPanelAjout().ajouterPanel(new PanelCreerProjet());
	}

	public static void creerPanelCreerPage()			
	{
		fenetre.getPanelAjout().supprimerPanel();
		fenetre.getPanelAjout().ajouterPanel(new PanelCreerPage());
	}
	
	public static void creerPanelAjouterParagraphe(int statue, String titre)
	{
		fenetre.getPanelAjout().supprimerPanel();
		fenetre.getPanelAjout().ajouterPanel(new PanelAjouterParagraphe(statue, titre));		
	}
	
	public static void creerPanelAjouterTitre(int statue, String titre)		
	{
		fenetre.getPanelAjout().supprimerPanel();
		fenetre.getPanelAjout().ajouterPanel(new PanelAjouterTitre(statue, titre));
	}
	
	public static void creerPanelAjouterImage(int statue, String chemin)		
	{
		fenetre.getPanelAjout().supprimerPanel();
		fenetre.getPanelAjout().ajouterPanel(new PanelAjouterImage(statue, chemin));			
	}
	
	public static void creerPanelPropriete(Object type)
	{		
		fenetre.getPanelAjout().supprimerPanel();
		fenetre.getPanelAjout().ajouterPanel(new PanelPropriete(type));
	}
	
	public static void creerFenetreAide()
	{
		new FenetreAide();
	}
	
	public static void creerOptionPane(String type, String texte)
	{
		OptionPane op = new OptionPane();
		if (type.equals("info"))
			op.optionPaneInfo(texte);
		else if (type.equals("warning"))
			op.optionPaneAlerte(texte);
		else if (type.equals("error"))
			op.optionPaneErreur(texte);
	}
	
	public static int creerOptionPaneConfirm(String titre, String texte, int option)
	{
		OptionPane op = new OptionPane();
		return op.optionPaneConfirmation(titre, texte, option);
	}
	
	public static void enregistrer()
	{
		fenetre.getArborescence().enregistrerArbre();
    	metier.enregistrerContenu();
    	creerOptionPane("info", "Sauvegarde effectuee avec succes");
	}

	public static void monterElement()
	{
		if (fenetre.getArborescence().monterNode("element"))
		{
			String type = fenetre.getArborescence().getNomNode();
			Projet projet = metier.getProjetSelectionne();
			if (projet == null)
				return;
			Page page = projet.getPageSelectionne();
			if (page == null)
				return;
			page.modOrdreElement(type, "monter");
		}
	}

	public static void descendreElement()
	{
		if (fenetre.getArborescence().descendreNode("element"))
		{
			String type = fenetre.getArborescence().getNomNode();
			Projet projet = metier.getProjetSelectionne();
			if (projet == null)
				return;
			Page page = projet.getPageSelectionne();
			if (page == null)
				return;
			page.modOrdreElement(type, "descendre");
		}
	}
	
	public static void monterPage()
	{
		if (fenetre.getArborescence().monterNode("page"))
		{
			String type = fenetre.getArborescence().getNomNode();
			Projet projet = metier.getProjetSelectionne();
			if (projet == null)
				return;
			projet.modOrdrePage(type, "monter");
		}
	}
	
	public static void descendrePage()
	{
		if (fenetre.getArborescence().descendreNode("page"))
		{
			String type = fenetre.getArborescence().getNomNode();
			Projet projet = metier.getProjetSelectionne();
			if (projet == null)
				return;
			projet.modOrdrePage(type, "descendre");
		}
	}
	
	public static void main(String[] argv)
	{
		initialiser();
	}
}