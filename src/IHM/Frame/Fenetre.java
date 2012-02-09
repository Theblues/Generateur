package IHM.Frame;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;

import IHM.Panel.*;
import Main.Controleur;

public class Fenetre extends JFrame
{
	/*
	 * 		  Composition de la Fenetre
	 * 	 ___________________________________
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
		setSize(1024, 400);
		
		menu = new PanelMenu();
		menu.addMenuInFrame(this);
		
		arborescence = new PanelArbre(this);
		listeAction = new PanelListeAction();
		
		panelVisu = new PanelVisu();

		splitPaneTotal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, arborescence, panelVisu);

		add(listeAction, BorderLayout.NORTH);
		add(splitPaneTotal);
		
		// permet l'action de la croix rouge
		addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {
            	Controleur.fermerFenetre();
            }
        });
		
		//Ajout d'un theme a notre fenetre
		try {
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			   SwingUtilities.updateComponentTreeUI(this);
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		} catch (UnsupportedLookAndFeelException e) {
		} catch (IllegalAccessException e) {}

		setVisible(true);
	}

	public PanelMenu getMenu()						{	return menu;			}
	public PanelListeAction getPanelListeAction()	{	return listeAction;		}
	public PanelArbre getArborescence()				{	return arborescence;	}
	public PanelVisu getPanelVisu()					{	return panelVisu;		}
}