package Main;

import IHM.Frame.*;
import IHM.Frame.creation.*;
import IHM.Panel.ajout.*;
import IHM.OptionPane.*;
import Metier.*;
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
	
	public static void creerFenetreCreerProjet()
	{
		new FenetreCreerProjet();
	}

	public static void creerFenetreCreerPage()			
	{		
		new FenetreCreerPage();				
	}
	
	public static void FenetrePropriete( Object type)
	{		
		new FenetrePropriete(type);		
	}
	
	public static void creerPanelAjouterParagraphe(int statue, String titre, int indiceParagraphe)
	{
		fenetre.getPanelAjout().supprimerPanel();
		fenetre.getPanelAjout().ajouterPanel(new PanelAjouterParagraphe(statue, titre, indiceParagraphe));		
	}
	
	public static void creerPanelAjouterTitre(int statue, String titre, int indiceTitre)		
	{
		fenetre.getPanelAjout().supprimerPanel();
		fenetre.getPanelAjout().ajouterPanel(new PanelAjouterTitre(statue, titre, indiceTitre));
	}
	
	public static void creerPanelAjouterImage(int statue)		
	{
		fenetre.getPanelAjout().supprimerPanel();
		fenetre.getPanelAjout().ajouterPanel(new PanelAjouterImage(statue));			
	}
	
	public static void CreerOptionPane(String type, String texte)
	{
		OptionPane op = new OptionPane();
		if (type.equals("info"))
			op.optionPaneInfo(texte);
		else if (type.equals("warning"))
			op.optionPaneAlerte(texte);
		else if (type.equals("error"))
			op.optionPaneErreur(texte);
	}
	
	public static int CreerOptionPaneConfirm(String titre, String texte)
	{
		OptionPane op = new OptionPane();
		return op.optionPaneConfirmation(titre, texte);
	}
	
	public static void fermerFenetre()
	{
		fenetre.getArborescence().enregistrerArbre();
    	metier.enregistrerContenu();
        System.exit(0);
	}
	
	public static void main(String[] argv)
	{
		initialiser();
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
}