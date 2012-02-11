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
		if (mi.equals(propriete))
		{
			if (location == 2)
			{
				Projet projet = Controleur.metier.getProjet(noeud[1].toString());
				// TODO Faire une fenetre propriete
				System.out.println(projet);
			}
			else if (location == 3)
			{
				Page page = Controleur.metier.getProjet(noeud[1].toString()).getPage(noeud[2].toString());
				// TODO Faire une fenetre propriete
				System.out.println(page);
			}
		}

	}
}
