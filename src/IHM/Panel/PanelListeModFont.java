package IHM.Panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PanelListeModFont extends JPanel implements ActionListener 
{
	private JEditorPane editorPane;

	private JButton boutonDroite;
	private JButton boutonCentre;
	private JButton boutonGauche;
	private JButton boutonGras;
	private JButton boutonItalique;
	private JButton boutonLien;

	public PanelListeModFont(JEditorPane editorPane) 
	{
		this.editorPane = editorPane;
		
		JPanel p = new JPanel();

		boutonGras = new JButton();
		boutonGras.addActionListener(this);
		boutonGras.setBorder(null);
		boutonGras.setPreferredSize(new Dimension(35, 35));
		boutonGras.setIcon(new ImageIcon("images/gras.jpg"));
		boutonGras.setToolTipText("Gras");

		boutonItalique = new JButton();
		boutonItalique.addActionListener(this);
		boutonItalique.setBorder(null);
		boutonItalique.setPreferredSize(new Dimension(35, 35));
		boutonItalique.setIcon(new ImageIcon("images/italique.jpg"));
		boutonItalique.setToolTipText("Italique");

		boutonGauche = new JButton();
		boutonGauche.addActionListener(this);
		boutonGauche.setBorder(null);
		boutonGauche.setPreferredSize(new Dimension(35, 35));
		boutonGauche.setIcon(new ImageIcon("images/gauche.jpg"));
		boutonGauche.setToolTipText("Aligne a gauche");

		boutonCentre = new JButton();
		boutonCentre.addActionListener(this);
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

		boutonLien = new JButton();
		boutonLien.setBorder(null);
		boutonLien.setPreferredSize(new Dimension(35, 35));
		boutonLien.setIcon(new ImageIcon("images/lien.jpg"));
		boutonLien.setToolTipText("Hyperlien");

		p.add(boutonGras);
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
		/*
		String texte = textPane.getSelectedText();		
		if (e.getSource() instanceof JButton)
		{
			JButton b = (JButton) e.getSource();
			
			if (b.equals(boutonGras))
			{
				ajouterGras(texte);
			}
			
			// TODO autre bouton
		}
		// TODO comboBox
		*/
	}
}