package IHM.Frame;

import java.awt.event.*;

import javax.swing.*;

import IHM.Panel.PanelArbre;
import Main.Generateur;

public class FenetreAjouterTitre extends JFrame implements ActionListener {
	private JTextField tf;
	private static int cpt=1;
	private int statue;
	private int indice;
	private String titre;

	public FenetreAjouterTitre(int statue, String titre, int indice) {

		this.statue = statue;
		this.titre = titre;
		this.indice = indice;

		// 0 = ajout, 1 = modif
		if (statue == 0) {

			setTitle("Entrer un titre");
			tf = new JTextField("", 20);
			tf.addActionListener(this);
			add(tf);

		} else {

			setTitle("Modifier un titre");
			tf = new JTextField(titre, 20);
			tf.addActionListener(this);
			add(tf);
		}

		setVisible(true);
		pack();
	}

	public void actionPerformed(ActionEvent arg0) {

		if (statue == 0) {
			Generateur.getGenerator().addTitre(tf.getText());
			Generateur.getFenetre().getArborescence()
					.ajoutFils("titre" + cpt, tf.getText());
			this.dispose();
		} else {

			Generateur.getGenerator().modTitre(tf.getText(), indice);
			this.dispose();

		}
		cpt++;
	}

}
