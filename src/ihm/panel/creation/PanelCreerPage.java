package ihm.panel.creation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;

import main.*;
import util.*;

public class PanelCreerPage extends JPanel implements ActionListener
{
	private JTextField	txNom;

	private JButton valider; 

	public PanelCreerPage()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT, 250, 10));
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		// Choix du projet
		JLabel label = new JLabel("Projet selectionne :");		
		panel.add(label);
		
		label = new JLabel(Controleur.metier.getProjetSelectionne().getNom());		
		panel.add(label);
		
		label = new JLabel("Nom de la page");
		panel.add(label);
		
		txNom = new JTextField(30);
		panel.add(txNom);
		
		valider = new JButton("Valider");
		valider.addActionListener(this);
		
		add(panel);
		add(valider);

		// on ajoute une bordure
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Creation d'une nouvelle page"),
						BorderFactory.createEmptyBorder(2, 2, 2, 2)),
						this.getBorder()));
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Page page = null;
		if (e.getSource() instanceof JButton)
		{
			JButton b = (JButton) e.getSource();
			if (valider.equals(b))
			{
				String nomPage = txNom.getText();
				if (nomPage.length() == 0)
					return;
				
				Projet p = Controleur.metier.getProjetSelectionne();
				String nomFichier = p.getNom() + "/" + nomPage;
				
				// on remplace les espaces par "_"
				Scanner sc = new Scanner(nomFichier);
				sc.useDelimiter(" ");
				
				nomFichier = sc.next();
				while (sc.hasNext())
					nomFichier += "_" + sc.next();


				File f = new File(p.getCheminDossier() + "/" + nomFichier + ".html");
				
				try
				{
					// on cree le fichier
					f.createNewFile();
				} catch (IOException ex)
				{
					ex.printStackTrace();
				}
				
				Controleur.fenetre.getArborescence().ajoutFils(null, "fichier", nomPage);
				page = new Page(f, nomPage);
				p.ajouterPage(page);
				Controleur.creerPanelPropriete(page);
			}
		}
	}
}