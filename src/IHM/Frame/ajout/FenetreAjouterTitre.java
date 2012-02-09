package IHM.Frame.ajout;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Main.*;
import Utilitaire.*;

public class FenetreAjouterTitre extends JFrame implements ActionListener
{
	private JTextField	tf;
	private int			statue;
	private int			indiceTitre;
	
	private JButton annuler;
	private JButton valider;

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

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton b = (JButton) e.getSource();
			if (valider.equals(b))
			{
				Projet projet = Controleur.metier.getProjetSelectionne();
				Page page = projet.getPageSelectionne();
				
				if (tf.getText().length() == 0)
					return;
				
				if (statue == 0)
				{
					page.ajouterTitre(tf.getText());
					int cpt = page.getAlTitre().size();
					page.ajouterOrdre("Titre " + cpt);
					Controleur.fenetre.getArborescence().ajoutFils("element", "Titre " + cpt);
				}
				else
					page.modTitre(tf.getText(), indiceTitre);
			}
		}
		this.dispose();	
	}
}