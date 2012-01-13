package IHM.Frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import IHM.Panel.*;

public class Fenetre extends JFrame
{
	private PanelMenu 			menu;
	private PanelArbre			arborescence;
	private JSplitPane 			splitPaneTotal;
	private JPanel				panelDroite;
	private JEditorPane			jEditor;
	
	public Fenetre()
	{
		setTitle("Generateur de site");
		setSize(1024, 700);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		menu = new PanelMenu();
		menu.addMenuInFrame(this);
		
		arborescence = new PanelArbre(this);
		
		panelDroite = new JPanel();
		panelDroite.setLayout(new BorderLayout());
		jEditor = new JEditorPane();
		jEditor.setEditable(false);
		jEditor.setSize(800,800);
		JScrollPane scroller = new JScrollPane( jEditor,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		panelDroite.add(scroller);
		
		splitPaneTotal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, arborescence, panelDroite);
		splitPaneTotal.setOneTouchExpandable(true);
		
		add(splitPaneTotal);
		// permet l'action de la croix rouge
		addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        });

		setVisible(true);
	}

	public PanelMenu getMenu()					{		return menu;			}
	public PanelArbre getArborescence()			{		return arborescence;	}
	
	public void previsualisation(String contenu)
	{
		jEditor.setText(contenu);
	}
}
