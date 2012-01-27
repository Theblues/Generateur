package IHM.Panel;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

public class PanelListeModFont extends JPanel implements ActionListener {

	private JTextPane textPane;
	
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

	public PanelListeModFont(JTextPane textPane) 
	{
		this.textPane = textPane;
		
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
		boutonGras.addActionListener(this);
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
		
	}
	
	private void ajouterGras(String text) 
	{
		initStylesForTextPane(textPane);

		Document doc = textPane.getDocument();
		try {
			doc.insertString(textPane.getSelectionStart(), text, textPane.getStyle("gras"));
			doc.remove(textPane.getSelectionStart(), text.length());
		} catch (BadLocationException ble) {
			System.err.println("Couldn't insert initial text.");
		}
	}
	
	private void initStylesForTextPane(JTextPane textPanel) 
	{
		// Initialize some styles
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

		Style regular = textPanel.addStyle("regular", def);

		Style s;

		s = textPanel.addStyle("gras", regular);
		StyleConstants.setBold(s, true);
		
		// TODO autre style
	}	
}