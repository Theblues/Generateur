package IHM.Panel;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

public class PanelListeModFont extends JPanel implements ActionListener {

	private JEditorPane editorPane;
	
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

	public PanelListeModFont(JEditorPane editorPane) 
	{
		this.editorPane = editorPane;
		
		JPanel p = new JPanel();
		JPanel tail = new JPanel();

		/*
		 * titre = new JLabel("Outils"); Font font = new
		 * Font("Arial",Font.BOLD,16); titre.setFont(font); p.add(titre,
		 * "West");
		 */


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
		boutonGauche.setToolTipText("Aligné à gauche");

		boutonCentre = new JButton();
		boutonCentre.addActionListener(this);
		boutonCentre.setBorder(null);
		boutonCentre.setPreferredSize(new Dimension(35, 35));
		boutonCentre.setIcon(new ImageIcon("images/centre.jpg"));
		boutonCentre.setToolTipText("Centré");

		boutonDroite = new JButton();
		boutonDroite.addActionListener(this);
		boutonDroite.setBorder(null);
		boutonDroite.setPreferredSize(new Dimension(35, 35));
		boutonDroite.setIcon(new ImageIcon("images/droite.jpg"));
		boutonDroite.setToolTipText("Aligné à droite");

		boutonLien = new JButton();
		boutonLien.setBorder(null);
		boutonLien.setPreferredSize(new Dimension(35, 35));
		boutonLien.setIcon(new ImageIcon("images/lien.jpg"));
		boutonLien.setToolTipText("Hyperlien");

		/*couleur.setBackground(Color.white);
		couleur.setLayout(new BorderLayout());
		JPanel coul = new JPanel();
		coul.add(combo2);

		combo2.setPreferredSize(new Dimension(75, 30));
		combo2.addItem("noir");
		combo2.addItem("vert");
		combo2.addItem("bleu");
		combo2.addItem("rouge");
		couleur.add(coul, BorderLayout.NORTH);*/

		//p.add(couleur);
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