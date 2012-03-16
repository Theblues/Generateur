package ihm.frame;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;

import ihm.menu.MenuBar;
import ihm.panel.*;
import main.Controleur;

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
	 *  |Arbo	|		Ajout				|
	 *  |		|							|
	 *  |		|							|
	 *  |		|							|
	 *  |		|							|
	 *  |_______|___________________________|
	 *  
	 */
	
	private MenuBar 				menu;
	private PanelListeAction 		listeAction;
	private PanelArbre				arborescence;
	private PanelAjout<JPanel>		panelAjout;
	
	public Fenetre()
	{
		setTitle("Generateur de site");
		// cette taille va servir lorsque l'on veut reduire la taille
		setSize(1024, 768);
		// ouverture de la fenetre en plein ecran
		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
		
		menu = new MenuBar();
		menu.addMenuInFrame(this);
		
		arborescence = new PanelArbre(this);
		listeAction = new PanelListeAction();
		
		panelAjout = new PanelAjout<JPanel>();

		JSplitPane splitPaneTotal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, arborescence, panelAjout);

		add(listeAction, BorderLayout.NORTH);
		add(splitPaneTotal);
		
		// permet de ne pas fermer la fenetre si on clic sur la croix rouge
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// permet l'action de la croix rouge
		addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {
            	int option = Controleur.creerOptionPaneConfirm("Sauvegarder", "Voulez-vous sauvegarder avant de quitter ?", JOptionPane.YES_NO_CANCEL_OPTION);
            	if (option == JOptionPane.OK_OPTION)
    				Controleur.enregistrer();
            	if (option != JOptionPane.CANCEL_OPTION)
            		System.exit(0);
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

	public MenuBar getMenu()						{	return menu;			}
	public PanelListeAction getPanelListeAction()	{	return listeAction;		}
	public PanelArbre getArborescence()				{	return arborescence;	}
	public PanelAjout getPanelAjout()				{	return panelAjout;		}
}