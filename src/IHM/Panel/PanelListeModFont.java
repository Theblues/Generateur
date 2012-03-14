package IHM.Panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;

public class PanelListeModFont extends JPanel
{
	private JButton boutonDroite;
	private JButton boutonCentre;
	private JButton boutonGauche;
	private JButton boutonGras;
	private JButton boutonItalique;
	private JButton boutonLien;

	public PanelListeModFont() 
	{		
		JPanel p = new JPanel();

		Action boldAction = new StyledEditorKit.BoldAction();
		
		boutonGras = new JButton();
		boutonGras.setBorder(null);
		boutonGras.setPreferredSize(new Dimension(35, 35));
		boutonGras.setIcon(new ImageIcon("images/gras.jpg"));
		boutonGras.setToolTipText("Gras");
		boutonGras.addActionListener(boldAction);
		
		Action italicAction = new StyledEditorKit.ItalicAction();
		boutonItalique = new JButton();
		boutonItalique.setBorder(null);
		boutonItalique.setPreferredSize(new Dimension(35, 35));
		boutonItalique.setIcon(new ImageIcon("images/italique.jpg"));
		boutonItalique.setToolTipText("Italique");
		boutonItalique.addActionListener(italicAction);
/*
 	Le prof a dit que ne le faisait pas je crois
 	
		boutonGauche = new JButton();
		boutonGauche.setBorder(null);
		boutonGauche.setPreferredSize(new Dimension(35, 35));
		boutonGauche.setIcon(new ImageIcon("images/gauche.jpg"));
		boutonGauche.setToolTipText("Aligne a gauche");

		boutonCentre = new JButton();
		boutonCentre.setBorder(null);
		boutonCentre.setPreferredSize(new Dimension(35, 35));
		boutonCentre.setIcon(new ImageIcon("images/centre.jpg"));
		boutonCentre.setToolTipText("Centre");

		boutonDroite = new JButton();
		boutonDroite.addActionListener(this);
		boutonDroite.setBorder(null);
		boutonDroite.setPreferredSize(new Dimension(35, 35));
		boutonDroite.setIcon(new ImageIcon("images/droite.jpg"));
		boutonDroite.setToolTipText("Aligne a droite");
*/
		Action underlineAction = new StyledEditorKit.UnderlineAction();
		Action fontAction = new StyledEditorKit.ForegroundAction("blue", Color.BLUE);
		boutonLien = new JButton();
		boutonLien.setBorder(null);
		boutonLien.setPreferredSize(new Dimension(35, 35));
		boutonLien.setIcon(new ImageIcon("images/lien.jpg"));
		boutonLien.setToolTipText("Hyperlien");
		boutonLien.addActionListener(underlineAction);
		boutonLien.addActionListener(fontAction);

		p.add(boutonGras);
		p.add(boutonItalique);
		/*p.add(boutonGauche);
		p.add(boutonCentre);
		p.add(boutonDroite);*/
		p.add(boutonLien);

		add(p);
	}
}