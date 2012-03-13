package IHM.Panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import util.*;

public class PanelPropriete extends JPanel implements ActionListener
{
	private Object type;
	private JButton modifier;
	private JPanel info;

	
	public PanelPropriete(Object type)
	{
		// Configuration fenetres
		setLayout(new BorderLayout());

		// Attribut 
		this.type = type;
		String title = "";
		if (type instanceof Projet ) 
		{	
			title = "Proprietes Projet";
			
			Projet p = (Projet) type;
			info = new JPanel(new GridLayout(4,1));
			
			JLabel nom = new JLabel("Nom du Projet :   " + p.getNom());
			info.add (nom);
			JLabel style = new JLabel("Nom du Style :   " + p.getStyle());
			info.add (style);
			JLabel nbPages = new JLabel("Nombre de Pages contenu : " + p.getAlPage().size());
			info.add (nbPages);
			JLabel chemin = new JLabel("Chemin du Projet  :   " + p.getCheminDossier());
			info.add (chemin);
		}
		else if ( type instanceof Page)
		{	
			title = "Proprietes Page";
			
			Page p = ( Page ) type;
			info = new JPanel(new GridLayout(4,1));		
			
			JLabel nom = new JLabel("Nom  :   " + p.getNom());			
			info.add (nom);
			JLabel test = new JLabel("Nombre d'element : " + p.getAlOrdre().size());
			info.add(test);
			JLabel nbTitre = new JLabel("Nombre de Titres : " + p.getAlTitre().size());
			info.add (nbTitre);
			JLabel nbPara = new JLabel("Nombre de Paragraphes  :   " + p.getAlParagraphe().size());
			info.add (nbPara);
		}

		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(title),
						BorderFactory.createEmptyBorder(2, 2, 2, 2)),
						this.getBorder()));
		add(info);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new BorderLayout());
		
		modifier = new JButton("Modifier");
		modifier.addActionListener(this);
		panelSouth.add(modifier, BorderLayout.EAST);
		
		add(panelSouth, BorderLayout.SOUTH);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		JButton b = (JButton) e.getSource();
	}
}