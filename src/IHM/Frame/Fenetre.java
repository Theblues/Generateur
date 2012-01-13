package IHM.Frame;

import java.awt.*;
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
	private JPanel				panelDroite;
	private JTextPane			jEditor;
	private PanelListeAction listeAction;
	
	public Fenetre()
	{
		setTitle("Generateur de site");
		setSize(1024, 700);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		menu = new PanelMenu();
		menu.addMenuInFrame(this);
		
		arborescence = new PanelArbre(this);
		listeAction = new PanelListeAction();
		
		panelDroite = new JPanel();
		panelDroite.setLayout(new BorderLayout());
		jEditor = new JTextPane();
		jEditor.setEditable(false);
		jEditor.setSize(800,800);
		JScrollPane scroller = new JScrollPane( jEditor,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		panelDroite.add(scroller);
		
		splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, listeAction ,panelDroite );
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

	public PanelMenu getMenu()					{		return menu;			}
	public PanelArbre getArborescence()			{		return arborescence;	}
	public JTextPane getjEditor()			{		return jEditor;	}
	
	public void previsualisation(String contenu)
	{
		jEditor.setText(contenu);
	}
	
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
