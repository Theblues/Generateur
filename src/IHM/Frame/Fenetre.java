package IHM.Frame;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import IHM.Panel.*;

public class Fenetre extends JFrame
{
	private PanelMenu 			menu;
	private PanelArbre			arborescence;
	private JSplitPane 			splitPaneVertical;
	private JSplitPane 			splitPaneTotal;
	private PanelVisu			panelVisu;
	
	private PanelListeAction 	listeAction;
	
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
		
		splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, listeAction , panelVisu);
		splitPaneVertical.setOneTouchExpandable(true);

		
		splitPaneTotal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, arborescence, splitPaneVertical);
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

	public PanelMenu getMenu()				{		return menu;			}
	public PanelArbre getArborescence()		{		return arborescence;	}
	public PanelVisu getPanelVisu()			{		return panelVisu;		}
	
	public void initStylesForTextPane(JTextPane textPanel, String chemin, String style) {
		// Initialize some styles
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

		Style regular = textPanel.addStyle("regular", def);

		Style s;

		if (style.equals("gras")) {
			s = textPanel.addStyle("gras", regular);
			StyleConstants.setBold(s, true);
			
		}
	}

	
}
