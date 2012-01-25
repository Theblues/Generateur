package IHM.Frame;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

import IHM.Panel.*;
import Main.Controleur;

public class Fenetre extends JFrame
{
	/*
	 * 		  Composition de la Fenetre
	 * 	____________________________________
	 *  |	 menu							|
	 *  |___________________________________|
	 *  | 			listeAction				|
	 *  |___________________________________|
	 *  |		|							|
	 *  |		|							|
	 *  |		|							|
	 *  |Arbo	|		Visu				|
	 *  |		|							|
	 *  |		|							|
	 *  |		|							|
	 *  |		|							|
	 *  |_______|___________________________|
	 *  
	 */
	
	private PanelMenu 			menu;
	private PanelListeAction 	listeAction;
	private PanelArbre			arborescence;
	private PanelVisu			panelVisu;
	
	private JSplitPane 			splitPaneTotal;
	
	public Fenetre()
	{
		setTitle("Generateur de site");
		setSize(1024, 700);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		menu = new PanelMenu();
		menu.addMenuInFrame(this);
		
		arborescence = new PanelArbre(this);
		listeAction = new PanelListeAction();
		
		panelVisu = new PanelVisu();

		splitPaneTotal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, arborescence, panelVisu);
		splitPaneTotal.setOneTouchExpandable(true);
		
		add(listeAction, BorderLayout.NORTH);
		add(splitPaneTotal);
		// permet l'action de la croix rouge
		addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {
            	Controleur.fenetre.getArborescence().enregistrerArbre();
            	Controleur.metier.enregistrerContenu();
                System.exit(0);
            }
        });

		setVisible(true);
	}

	public PanelMenu getMenu()						{	return menu;			}
	public PanelListeAction getPanelListeAction()	{	return listeAction;		}
	public PanelArbre getArborescence()				{	return arborescence;	}
	public PanelVisu getPanelVisu()					{	return panelVisu;		}
}
