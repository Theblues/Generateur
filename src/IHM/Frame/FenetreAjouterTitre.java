package IHM.Frame;

import java.awt.event.*;

import javax.swing.*;

import Main.Generateur;
import Utilitaire.*;

public class FenetreAjouterTitre extends JFrame implements ActionListener
{
	private JTextField	tf;
	private int			statue;
	private int			indiceTitre;

	public FenetreAjouterTitre(int statue, String titre, int indiceTitre)
	{
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

		setVisible(true);
		pack();
	}

	public void actionPerformed(ActionEvent e)
	{
		Projet projet = Generateur.metier.getProjetSelectionne();
		Page page = projet.getPageSelectionne();
		
		if (statue == 0)
		{
			projet.getPage(page).ajouterTitre(tf.getText());
			int cpt = projet.getPage(page).getAlTitre().size();
			Generateur.fenetre.getArborescence().ajoutFils("element", "Titre " + cpt);
		}
		else
			projet.getPage(page).modTitre(tf.getText(), indiceTitre);

		this.dispose();
	}

}
