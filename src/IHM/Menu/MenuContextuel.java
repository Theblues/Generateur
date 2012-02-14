package IHM.Menu;

import java.awt.event.*;
import javax.swing.*;

import Main.*;
import Utilitaire.*;

public class MenuContextuel implements ActionListener
{
	private JTree arbre;
	private Object[] noeud;
	private int location;
	
	private JMenu nouveau;
	private JMenuItem nouveauProjet;
	private JMenuItem nouvellePage;
	private JMenuItem propriete;
	private JMenuItem supprimer;
	
	public MenuContextuel(MouseEvent me, int location, Object[] obj)
	{
		arbre = (me != null) ? (JTree) me.getSource(): null;
		this.location = location;
		noeud = (obj != null) ? obj : null;
		
		JPopupMenu jpm = new JPopupMenu();
		
		nouveau = new JMenu("Nouveau");
		
		// creation des item du menu Nouveau
		nouveauProjet = new JMenuItem("Nouveau Projet");
		nouveauProjet.addActionListener(this);
		nouvellePage = new JMenuItem("Nouvelle Page");
		nouvellePage.addActionListener(this);
		
		nouveau.add(nouveauProjet);
		nouveau.add(nouvellePage);
		
		supprimer = new JMenuItem("Supprimer");
		supprimer.addActionListener(this);
		propriete = new JMenuItem("Proprietes");
		propriete.addActionListener(this);
		
		jpm.add(nouveau);
		jpm.add(supprimer);
		jpm.add(propriete);
		// on affiche le menuContextuel a la l'arbre
		jpm.show(arbre, me.getX(), me.getY());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem mi = (JMenuItem) e.getSource();
		
		// si c'est un projet
		if (location == 2)
		{
			Projet projet = Controleur.metier.getProjet(noeud[1].toString());
			
			// proprietes
			if (mi.equals(propriete))
				Controleur.FenetrePropriete(projet);

			if (mi.equals(supprimer))
			{
				int option = Controleur.CreerOptionPaneConfirm("Supprimez le projet", "Voulez-vous supprimez le projet ?");
				if (option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION)
				{
					// TODO code pour supprimer l'element
				}
			}
		}
		// si c'est un page
		else if (location == 3)
		{
			Projet projet = Controleur.metier.getProjet(noeud[1].toString());
			Page page = projet.getPage(noeud[2].toString());
			
			// Proprietes
			if (mi.equals(propriete))
				Controleur.FenetrePropriete(page);
		}
		// si c'est un element
		else if (location >= 3)
		{
			Projet projet = Controleur.metier.getProjet(noeud[1].toString());
			Page page = projet.getPage(noeud[2].toString());
			
			// TODO le reste
		}
		
		if (mi.equals(nouveauProjet))
			Controleur.creerFenetreCreerProjet();
		else if (mi.equals(nouvellePage) && noeud[1] != null)
			Controleur.creerFenetreCreerPage();
	}
}
