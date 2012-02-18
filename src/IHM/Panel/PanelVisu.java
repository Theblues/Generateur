package IHM.Panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.html.*;

import IHM.Frame.ajout.FenetreAjouterParagraphe;
import IHM.Frame.ajout.FenetreAjouterTitre;
import Main.Controleur;
import Utilitaire.*;

public class PanelVisu<T extends JPanel> extends JPanel
{
	public PanelVisu()
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