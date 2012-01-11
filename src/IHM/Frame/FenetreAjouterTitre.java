package IHM.Frame;

import java.awt.event.*;

import javax.swing.*;

import IHM.Panel.PanelArbre;
import Main.Generateur;

public class FenetreAjouterTitre extends JFrame implements ActionListener
{
	private JTextField	tf;
	private static int	cpt	= 1;
	private int			statue;
	private int			indice;
	private int			indiceTitre;

	public FenetreAjouterTitre(int statue, String titre, int indice, int indiceTitre)
	{
		this.statue = statue;
		this.indice = indice;
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
		if (statue == 0)
		{
			Generateur.getGenerator().addTitre(tf.getText());
			Generateur.getFenetre().getArborescence().ajoutFils("element", "Titre " + cpt, tf.getText());
			cpt++;
		}
		else
		{
			Generateur.getGenerator().modTitre(tf.getText(), indiceTitre);
			Generateur.getFenetre().getArborescence().setAlS(indice, tf.getText());
		}
		this.dispose();
	}

}
