package ihm.panel;

import java.awt.*;

import javax.swing.*;

public class PanelAjout<T extends JPanel> extends JPanel
{
	public PanelAjout()
	{
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(""),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)),
						this.getBorder()));
		setVisible(true);
	}
	
	public void ajouterPanel(T panel)
	{
		setBorder(null);
		add(panel);
		validate();
	}
	
	public void supprimerPanel()
	{
		removeAll();
		validate();
	}
}