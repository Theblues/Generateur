package IHM.Frame.ajout;

import java.awt.event.*;

import javax.swing.*;

import Main.*;
import Utilitaire.*;

public class FenetreAjouterTitre extends JFrame implements ActionListener
{
	private JTextField	tf;
	private int			statue;
	private int			indiceTitre;

	public FenetreAjouterTitre(int statue, String titre, int indiceTitre)
	{
		setLocation(250, 250);
		this.statue = statue;
		this.indiceTitre = indiceTitre;

		// 0 = ajout, 1 = modif
		if (statue == 0)
		{
			setTitle("Entrer un titre");
			tf = new JTextField("", 20);
		}
		else
		{
			setTitle("Modifier un titre");
			tf = new JTextField(titre, 20);
		}
		tf.addActionListener(this);
		add(tf);

		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		Projet projet = Controleur.metier.getProjetSelectionne();
		Page page = projet.getPageSelectionne();
		
		if (statue == 0)
		{
			projet.getPage(page).ajouterTitre(tf.getText());
			int cpt = projet.getPage(page).getAlTitre().size();
			Controleur.fenetre.getArborescence().ajoutFils("element", "Titre " + cpt);
		}
		else
			projet.getPage(page).modTitre(tf.getText(), indiceTitre);

		this.dispose();	
	}
}