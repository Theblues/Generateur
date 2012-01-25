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
	private JComboBox combo = new JComboBox();
	
	public FenetreCreerProjet()
	{
		setTitle("Nouveau Projet");
		setLocation(100, 100);
		setSize(500, 400);
		
		JLabel labelDescription = new JLabel("Creer votre projet");
		add(labelDescription, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new FlowLayout());
		JLabel label = new JLabel("Nom du projet");
		
		panel.add(label);
		
		txNom = new JTextField(30);
		panel.add(txNom);
		
		combo.setPreferredSize(new Dimension(75, 30));
		combo.addItem("Style 1");
		combo.addItem("Style 2");
		combo.addItem("Style 3");
		
		panel.add(combo);
		
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
