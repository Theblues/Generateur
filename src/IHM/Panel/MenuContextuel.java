package IHM.Panel;

import java.awt.event.*;
import javax.swing.*;

import Main.*;
import Utilitaire.*;

public class MenuContextuel implements ActionListener
{
	private JTree arbre;
	private Object[] noeud;
	private int location;
	
	private JMenuItem nouveau;
	private JMenuItem projet;
	private JMenuItem page;
	private JMenuItem propriete;
	private JMenuItem supprimer;
	
	public MenuContextuel(MouseEvent me, int location, Object[] obj)
	{
		arbre = (JTree) me.getSource();
		this.location = location;
		noeud = obj;
		
		JPopupMenu jpm = new JPopupMenu();		
		
		
		nouveau = new JMenuItem("Nouveau");
		nouveau.addActionListener(this);
		supprimer = new JMenuItem("Effacer");
		supprimer.addActionListener(this);
		propriete = new JMenuItem("Proprietes");
		propriete.addActionListener(this);
		
		jpm.add(nouveau);
		jpm.add(supprimer);
		jpm.add(propriete);
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
			
			// propriétés
			if (mi.equals(propriete))
			{
				Controleur.FenetrePropriete(projet);
			}
			// Ajout d'un projet
			if (mi.equals(nouveau)){
				Controleur.creerFenetreCreerProjet();
			}
		}
		// si c'est un page
		else if (location == 3)
		{
			Projet projet = Controleur.metier.getProjet(noeud[1].toString());
			Page page = projet.getPage(noeud[2].toString());
			
			// Propriétés
			if (mi.equals(propriete))
			{
				Controleur.FenetrePropriete(page);
			}
			
			// TODO ajout d'une page
			if (mi.equals(nouveau)){
				Controleur.creerFenetreCreerPage();
			}
		}
		// si c'est un element
		else if (location >= 3)
		{
			Projet projet = Controleur.metier.getProjet(noeud[1].toString());
			Page page = projet.getPage(noeud[2].toString());
			
			// TODO le reste
		}

	}
}
