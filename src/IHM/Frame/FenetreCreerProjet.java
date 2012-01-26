package IHM.Frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import Main.*;
import Utilitaire.*;

public class FenetreCreerProjet extends JFrame implements ActionListener
{
	private JTextField txNom;
	private JTextField txChemin;
	private JComboBox combo;
	
	private JButton choixDossier;
	
	private JButton annuler;
	private JButton valider;
	
	public FenetreCreerProjet()
	{
		setTitle("Nouveau Projet");
		setLocation(100, 100);
		setSize(500, 400);
		
		JLabel labelDescription = new JLabel("Creer votre projet");
		add(labelDescription, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Nom du projet");
		
		panel.add(label);
		
		txNom = new JTextField(20);
		panel.add(txNom);
		
		label = new JLabel("Choisissez un theme");
		panel.add(label);
		
		combo = new JComboBox();
		combo.setPreferredSize(new Dimension(100, 30));
		combo.addItem("Theme 1");
		combo.addItem("Theme 2");
		combo.addItem("Theme 3");
		panel.add(combo);
		
		label = new JLabel("Choisissez un dossier");
		panel.add(label);
		txChemin = new JTextField(20);
		panel.add(txChemin);
		
		
		
		add(panel);
		
		// TODO (pour sarah) Faire comme ca
		JPanel panSud = new JPanel();
		panSud.setLayout(new BorderLayout());
		
		JPanel panBouton = new JPanel();
		annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		panBouton.add(annuler);
		
		valider = new JButton("Valider");
		valider.addActionListener(this);
		panBouton.add(valider);
		
		panSud.add(panBouton, BorderLayout.EAST);
		add(panSud, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String nomProjet = txNom.getText();
		File file = new File("projet" + nomProjet);
		file.mkdir();
		Controleur.metier.ajouterProjet(new Projet(nomProjet));
		Controleur.fenetre.getArborescence().ajoutFils("projet", nomProjet);
		
		File content = new File ("projet/" + nomProjet + "/content");
		File css = new File ("projet/" + nomProjet + "/content/CSS");
		File img = new File ("projet/" + nomProjet + "/content/IMG");
		
		content.mkdir();
		css.mkdir();
		img.mkdir();
		
		File cssFile;
		
		if (combo.getSelectedItem().equals("Style 1"))
			cssFile = new File ("projet/" + nomProjet + "/content/CSS/style1.css");
		else if (combo.getSelectedItem().equals("Style 2"))
			cssFile = new File ("projet/" + nomProjet + "/content/CSS/style2.css");
		else
			cssFile = new File ("projet/" + nomProjet + "/content/CSS/style3.css");		
		
		this.dispose();
	}
}
