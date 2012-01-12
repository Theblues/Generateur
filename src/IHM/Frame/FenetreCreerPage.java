package IHM.Frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import Main.*;
import Utilitaire.*;

public class FenetreCreerPage extends JFrame implements ActionListener
{
	private JTextField	txTitre;

	public FenetreCreerPage()
	{
		setTitle("Creation d'une nouvelle page");
		setLocation(200, 200);

		JLabel labelDescription = new JLabel("Creer une nouvelle page !");
		add(labelDescription, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Nom de la page");

		panel.add(label);
		txTitre = new JTextField(30);

		panel.add(txTitre);

		add(panel);

		JButton button = new JButton("Valider");
		button.addActionListener(this);
		add(button, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		String nomFichier = "site/"	+ txTitre.getText() + ".html";
		File f = new File(nomFichier);
		try
		{
			// on recree le fichier lorsqu'on genere
			f.createNewFile();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		Generateur.alProjet.get(0).ajouterPage(new Page(f.getName()));
		Generateur.fenetre.getArborescence().ajoutFils("fichier", f.getName(), "");
		dispose();
	}
}
