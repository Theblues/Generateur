package ihm.panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import util.*;

public class PanelPropriete extends JPanel implements ActionListener
{
	private JButton modifier;
	private JPanel info;

	// element pour les modifications
	private JTextField txNom;
	
	// Permet la modification
	private Projet projet = null;
	private Page page = null;
	
	public PanelPropriete(Object type)
	{		
		String title = "";
		
		if (type instanceof Projet) 
		{	
			
			title = "Proprietes du Projet";
			
			this.projet = (Projet) type;
			info = new JPanel(new GridLayout(4,2));
			
			JLabel nom = new JLabel("Nom du Projet : ");
			txNom = new JTextField(projet.getNom());
			info.add (nom);
			info.add(txNom);
			
			JLabel style = new JLabel("Nom du Style : ");
			JTextField tx1 = new JTextField(projet.getStyle());
			tx1.setEditable(false);
			info.add (style);
			info.add(tx1);
			
			JLabel nbPages = new JLabel("Nombre de Pages contenu : ");
			JTextField tx2 = new JTextField("" + projet.getAlPage().size());
			tx2.setEditable(false);
			info.add (nbPages);
			info.add(tx2);
			
			JLabel chemin = new JLabel("Chemin du Projet  : ");
			JTextField tx3 = new JTextField(projet.getCheminDossier());
			tx3.setEditable(false);
			info.add (chemin);
			info.add(tx3);
		}
		else if (type instanceof Page)
		{	
			title = "Proprietes de la Page";
			
			page = (Page) type;
			info = new JPanel(new GridLayout(5 ,2));		
			
			JLabel nom = new JLabel("Nom  : ");
			txNom = new JTextField(page.getNom());
			info.add (nom);
			info.add (txNom);
			
			JLabel test = new JLabel("Nombre d'element : ");
			JTextField tx1 = new JTextField("" + page.getAlOrdre().size());
			tx1.setEditable(false);
			info.add(test);
			info.add(tx1);
			
			JLabel nbTitre = new JLabel("Nombre de Titres : ");
			JTextField tx2 = new JTextField("" + page.getAlTitre().size());
			tx2.setEditable(false);
			info.add (nbTitre);
			info.add(tx2);
			
			JLabel nbPara = new JLabel("Nombre de Paragraphes  : ");
			JTextField tx3 = new JTextField("" + page.getAlParagraphe().size());
			tx3.setEditable(false);
			info.add (nbPara);
			info.add(tx3);
			
			JLabel nbImage = new JLabel("Nombre d'Images : ");
			JTextField tx4 = new JTextField("" + page.getAlImage().size());
			tx4.setEditable(false);
			info.add (nbImage);
			info.add(tx4);
			
		}

		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(title),
						BorderFactory.createEmptyBorder(2, 2, 2, 2)),
						this.getBorder()));
		add(info);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new BorderLayout());
		
		modifier = new JButton("Modifier");
		modifier.addActionListener(this);
		panelSouth.add(modifier, BorderLayout.EAST);
		
		add(panelSouth, BorderLayout.SOUTH);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		JButton b = (JButton) e.getSource();
		if (b.equals(modifier))
		{
			if (projet != null)
				projet.setNom(txNom.getText());
			else if (page != null)
				page.setNom(txNom.getText());
		}
	}
}