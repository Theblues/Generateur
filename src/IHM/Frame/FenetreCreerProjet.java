package IHM.Frame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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
		
		JButton button = new JButton("Valider");
		button.addActionListener(this);
		add(button, BorderLayout.SOUTH);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{	
		String nomProjet = txNom.getText();
		File file = new File(nomProjet);
		file.mkdir();
		Controleur.metier.ajouterProjet(new Projet(nomProjet));
		Controleur.fenetre.getArborescence().ajoutFils("projet", nomProjet);
		this.dispose();
	}
}
