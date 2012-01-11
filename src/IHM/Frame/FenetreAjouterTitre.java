package IHM.Frame;

import java.awt.event.*;

import javax.swing.*;

import IHM.Panel.PanelArbre;
import Main.Generateur;

public class FenetreAjouterTitre extends JFrame implements ActionListener
{
	private JTextField tf;
	
	public FenetreAjouterTitre()
	{
		setTitle("Entrer un titre");
		tf = new JTextField("",20);
		tf.addActionListener(this);
		add(tf);
		setVisible(true);
		pack();
	}

	public void actionPerformed(ActionEvent arg0) {
		Generateur.ajouterTitre(tf.getText());
		PanelArbre.ajoutFils(tf.getText(), tf.getText());
		this.dispose();
	}
	
	
}
