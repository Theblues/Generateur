package IHM.Panel.ajout;

import java.awt.event.*;

import javax.swing.*;

import Main.*;
import util.*;

public class PanelAjouterTitre extends JPanel implements ActionListener
{
	private JTextField	tf;
	private int			statue;
	private int			indiceTitre;

	private JButton		modifier;
	private JButton		valider;

	public PanelAjouterTitre(int statue, String titre, int indiceTitre)
	{		
		this.statue = statue;
		this.indiceTitre = indiceTitre;
		
		JLabel l = new JLabel("Entrer un titre :");
		add(l);
		
		tf = new JTextField(titre, 20);
		
		add(tf);

		if (statue == 0)
		{
			valider = new JButton("Valider");
			valider.addActionListener(this);
			add(valider);
		}
		else
		{
			modifier = new JButton("Modifier");
			modifier.addActionListener(this);
			add(modifier);
		}
		
		this.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Votre titre"),
								BorderFactory.createEmptyBorder(2, 2, 2, 2)),
								this.getBorder()));
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		int cpt=0;
		
		Projet projet = Controleur.metier.getProjetSelectionne();
		Page page = projet.getPageSelectionne();

		if (tf.getText().length() == 0)
			return;
		
		if (statue == 0) // Si on ajoute un titre
		{
			page.ajouterTitre(tf.getText());
			cpt = page.getAlTitre().size();
			page.ajouterOrdre("Titre " + cpt);
			Controleur.fenetre.getArborescence().ajoutFils(null, "element", "Titre " + cpt);
		}
		else // Si on modifie un titre
		{
			for (cpt=0; cpt < page.getAlTitre().size(); cpt++)
			{
				if (page.getAlTitre().get(cpt).equals(tf.getText()))
					break;
			}
			page.modTitre(tf.getText(), cpt);
		}
		Controleur.creerPanelAjouterTitre(1, tf.getText(), cpt);
	}
}