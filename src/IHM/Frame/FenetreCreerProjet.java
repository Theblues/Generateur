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
		txNom = new JTextField(30);
		
		panel.add(txNom);
		
		add(panel);
		
		// TODO (pour sarah) modifier les boutons
		JButton button = new JButton("Valider");
		button.addActionListener(this);
		add(button, BorderLayout.SOUTH);
		
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
		
		this.dispose();
	}
}
