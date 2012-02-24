package IHM.Frame.creation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;

import Main.*;
import util.*;

public class FenetreCreerPage extends JFrame implements ActionListener
{
	private JTextField	txNom;
	
	private JButton annuler;
	private JButton valider; 

	public FenetreCreerPage()
	{
		setTitle("Creation d'une nouvelle page");
		setLocation(200, 200);

		JLabel labelDescription = new JLabel("Creer une nouvelle page !");
		add(labelDescription, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("Nom de la page");
		panel.add(label);
		
		txNom = new JTextField(30);
		panel.add(txNom);
		
		add(panel);
		
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

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton b = (JButton) e.getSource();
			if (valider.equals(b))
			{
				String nomPage = txNom.getText();
				Projet p = Controleur.metier.getProjetSelectionne();
				String nomFichier = p.getNom() + "/" + nomPage;
				
				// on coupe s'il y a des espaces
				Scanner sc = new Scanner(nomFichier);
				sc.useDelimiter(" ");
				
				nomFichier = sc.next();
				while (sc.hasNext())
					nomFichier += "_" + sc.next();


				File f = new File(p.getCheminDossier() + "/" + nomFichier + ".html");
				System.out.println(f);
				try
				{
					// on recree le fichier lorsqu'on genere
					f.createNewFile();
				} catch (IOException ex)
				{
					ex.printStackTrace();
				}
				
				Controleur.fenetre.getArborescence().ajoutFils(null, "fichier", nomPage);
				Controleur.metier.getProjetSelectionne().ajouterPage(new Page(f, nomPage));
			}
		}
		
		dispose();
	}
}