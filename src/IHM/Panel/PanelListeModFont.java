package IHM.Panel;

import java.awt.*;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.apache.commons.io.IOUtils;

import Main.*;
import Utilitaire.*;

public class PanelListeModFont extends JPanel implements ActionListener {

	private JPanel taille = new JPanel();
	private JComboBox combo1 = new JComboBox();

	private JPanel couleur = new JPanel();
	private JComboBox combo2 = new JComboBox();

	private JButton boutonDroite;
	private JButton boutonCentre;
	private JButton boutonGauche;
	private JButton boutonGras;
	private JButton boutonItalique;
	private JButton boutonSouligne;
	private JButton boutonLien;

	// private JLabel titre;

	public PanelListeModFont() 
	{
		JPanel p = new JPanel();
		JPanel tail = new JPanel();

		taille.setBackground(Color.white);
		taille.setLayout(new BorderLayout());

		/*
		 * titre = new JLabel("Outils"); Font font = new
		 * Font("Arial",Font.BOLD,16); titre.setFont(font); p.add(titre,
		 * "West");
		 */

		tail.add(combo1);
		combo1.setPreferredSize(new Dimension(75, 30));
		combo1.addItem("12 px");
		combo1.addItem("14 px");
		combo1.addItem("16 px");
		combo1.addItem("20 px");
		taille.add(tail, BorderLayout.NORTH);

		boutonGras = new JButton();
		boutonGras.setBorder(null);
		boutonGras.setPreferredSize(new Dimension(35, 35));
		boutonGras.setIcon(new ImageIcon("images/gras.jpg"));

		boutonItalique = new JButton();
		boutonItalique.setBorder(null);
		boutonItalique.setPreferredSize(new Dimension(35, 35));
		boutonItalique.setIcon(new ImageIcon("images/italique.jpg"));

		boutonSouligne = new JButton();
		boutonSouligne.setBorder(null);
		boutonSouligne.setPreferredSize(new Dimension(35, 35));
		boutonSouligne.setIcon(new ImageIcon("images/souligne.jpg"));

		boutonGauche = new JButton();

		boutonGauche.setBorder(null);
		boutonGauche.setPreferredSize(new Dimension(35, 35));
		boutonGauche.setIcon(new ImageIcon("images/gauche.jpg"));

		boutonCentre = new JButton();
		boutonCentre.setBorder(null);
		boutonCentre.setPreferredSize(new Dimension(35, 35));
		boutonCentre.setIcon(new ImageIcon("images/centre.jpg"));

		boutonDroite = new JButton();
		boutonDroite.setBorder(null);
		boutonDroite.setPreferredSize(new Dimension(35, 35));
		boutonDroite.setIcon(new ImageIcon("images/droite.jpg"));

		boutonLien = new JButton();
		boutonLien.setBorder(null);
		boutonLien.setPreferredSize(new Dimension(35, 35));
		boutonLien.setIcon(new ImageIcon("images/lien.jpg"));

		couleur.setBackground(Color.white);
		couleur.setLayout(new BorderLayout());
		JPanel coul = new JPanel();
		coul.add(combo2);

		combo2.setPreferredSize(new Dimension(75, 30));
		combo2.addItem("noir");
		combo2.addItem("vert");
		combo2.addItem("bleu");
		combo2.addItem("rouge");
		couleur.add(coul, BorderLayout.NORTH);

		p.add(taille);
		p.add(couleur);
		p.add(boutonGras);
		p.add(boutonSouligne);
		p.add(boutonItalique);
		p.add(boutonGauche);
		p.add(boutonCentre);
		p.add(boutonDroite);
		p.add(boutonLien);

		add(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Tous
		if ( e.getSource() == boutonGras ){
			
			//Generateur.ajouterStyleParagraphe();
		}
		
	}

	
}
