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
	
	private JMenuItem propriete;
	private JMenuItem eraseMenu;
	
	public MenuContextuel(MouseEvent me, int location, Object[] obj)
	{
		arbre = (JTree) me.getSource();
		this.location = location;
		noeud = obj;
		
		JPopupMenu jpm = new JPopupMenu();		
		
		propriete = new JMenuItem("Proprietes");
		propriete.addActionListener(this);
		
		eraseMenu = new JMenuItem("Effacer ce noeud");
		eraseMenu.addActionListener(this);
		jpm.add(eraseMenu);
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
			if (mi.equals(propriete))
			{
				
				Projet projet = Controleur.metier.getProjet(noeud[1].toString());
				// TODO Faire une fenetre propriete
				System.out.println(projet);
			}
			//if (mi.equals("autrebouton"))
		}
		// si c'est un page
		else if (location == 3)
		{
			if (mi.equals(propriete))
			{
				Page page = Controleur.metier.getProjet(noeud[1].toString()).getPage(noeud[2].toString());
				// TODO Faire une fenetre propriete
				System.out.println(page);
			}
		}
		// si c'est un element
		else if (location >= 3)
		{
			
		}

	}
}
