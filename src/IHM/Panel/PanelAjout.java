package IHM.Panel;

import java.awt.*;

import javax.swing.*;

public class PanelAjout<T extends JPanel> extends JPanel
{
	public PanelAjout()
	{
		setLayout(new BorderLayout());
		setVisible(true);
	}
	
	public void ajouterPanel(T panel)
	{
		this.add(panel);
		this.validate();
	}
	
	public void supprimerPanel()
	{
		this.removeAll();
		this.validate();
	}
}