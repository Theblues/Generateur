package IHM.Frame;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

public class FenetreChoixDossier extends JFrame implements ActionListener, ItemListener, Serializable
{
	boolean bon = false;
	
	private JButton bAnnuler;
	private JButton bValider;
	
	public FenetreChoixDossier()
	{
		setTitle("Choisissez un dossier");
		setLocation(200, 200);
		
		JLabel l = new JLabel("Choississez un dossier pour mettre les fichiers des projets");
		add(l, BorderLayout.NORTH);
		
		/*JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);*/
		
		JPanel pan = new JPanel();
		bAnnuler = new JButton("Annuler");
		bAnnuler.addActionListener(this);
		pan.add(bAnnuler);
		
		bValider = new JButton("Valider");
		bValider.addActionListener(this);
		pan.add(bValider);
		
		add(pan, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		bon = true;
		dispose();
	}
	public boolean getBon()
	{
		// TODO Auto-generated method stub
		return bon;
	}

}
