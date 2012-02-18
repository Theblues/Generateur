package IHM.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import util.*;

public class FenetrePropriete extends JFrame implements ActionListener
{
	private Object type;
	private JButton quitter;
	private JPanel info;

	
	public FenetrePropriete(Object type)
	{
		// Configuration fenetres
		setLocation(400, 400);
		setLayout(new BorderLayout());
		setSize(350,200);

		
		// Attribut 
		this.type = type;
		
		
		if (type instanceof Projet ) {
			
			setTitle("Proprietes Projet");
			
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
			
	
		}else if ( type instanceof Page){
			
			setTitle("Proprietes Page");
			
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

		add(info);
		
		quitter = new JButton("Quitter");
		quitter.addActionListener(this);
		add (quitter, "South");

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		JButton b = (JButton) e.getSource();
		if (b.equals(quitter))
			this.dispose();
	}
}