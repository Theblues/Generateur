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

	private JButton		annuler;
	private JButton		valider;

	public PanelAjouterTitre(int statue, String titre, int indiceTitre)
	{		
		this.statue = statue;
		this.indiceTitre = indiceTitre;
		
		JLabel l = new JLabel("Entrer un titre :");
		add(l);
		
		tf = new JTextField(titre, 20);
		
		add(tf);

		annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		add(annuler);

		valider = new JButton("Valider");
		valider.addActionListener(this);
		add(valider);

		this.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Votre titre"),
								BorderFactory.createEmptyBorder(5, 5, 5, 5)),
								this.getBorder()));
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton b = (JButton) e.getSource();
			if (annuler.equals(b))
			{
				Controleur.fenetre.getPanelAjout().supprimerPanel();
				return;
			}
		}
		Projet projet = Controleur.metier.getProjetSelectionne();
		Page page = projet.getPageSelectionne();

		if (tf.getText().length() == 0)
			return;

		if (statue == 0)
		{
			page.ajouterTitre(tf.getText());
			int cpt = page.getAlTitre().size();
			page.ajouterOrdre("Titre " + cpt);
			Controleur.fenetre.getArborescence().ajoutFils(null, "element", "Titre " + cpt);
		}
		else
			page.modTitre(tf.getText(), indiceTitre);

		Controleur.fenetre.getPanelAjout().supprimerPanel();
	}
}