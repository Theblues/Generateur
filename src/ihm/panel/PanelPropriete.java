package ihm.panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.TreePath;

import main.Controleur;

import util.*;

public class PanelPropriete extends JPanel implements ActionListener
{
	private JButton modifier;

	// element pour les modifications
	private JTextField txNom;
	private JTextField txAuteur;
	
	// Permet la modification
	private Projet projet = null;
	private Page page = null;
	
	public PanelPropriete(Object type)
	{
		setLayout(new FlowLayout(FlowLayout.LEFT, 325,10));
		
		String title = "";
		JPanel info = null;
		
		if (type instanceof Projet) 
		{
			title = "Proprietes du Projet";
			
			this.projet = (Projet) type;
			info = new JPanel(new GridLayout(5,2));
			
			JLabel nom = new JLabel("Nom du Projet : ");
			txNom = new JTextField(projet.getNom());
			info.add (nom);
			info.add(txNom);
			
			JLabel auteur = new JLabel("Auteur du Projet : ");
			txAuteur = new JTextField(projet.getAuteur());
			info.add (auteur);
			info.add(txAuteur);
			
			JLabel style1 = new JLabel("Nom du Style : ");
			JLabel style2 = new JLabel(projet.getStyle());
			info.add (style1);
			info.add(style2);
			
			JLabel nbPages1 = new JLabel("Nombre de Pages contenu : ");
			JLabel nbPages2 = new JLabel("" + projet.getAlPage().size());
			info.add (nbPages1);
			info.add(nbPages2);
			
			JLabel chemin1 = new JLabel("Chemin du Projet  : ");
			JLabel chemin2 = new JLabel(projet.getCheminDossier());
			info.add (chemin1);
			info.add(chemin2);
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
			
			JLabel nbElement1 = new JLabel("Nombre d'element : ");
			JLabel nbElement2 = new JLabel("" + page.getAlOrdre().size());
			info.add(nbElement1);
			info.add(nbElement2);
			
			JLabel nbTitre1 = new JLabel("Nombre de Titres : ");
			JLabel nbTitre2 = new JLabel("" + page.getAlTitre().size());
			info.add (nbTitre1);
			info.add(nbTitre2);
			
			JLabel nbPara1 = new JLabel("Nombre de Paragraphes  : ");
			JLabel nbPara2 = new JLabel("" + page.getAlParagrapheHTML().size());
			info.add (nbPara1);
			info.add(nbPara2);
			
			JLabel nbImage1 = new JLabel("Nombre d'Images : ");
			JLabel nbImage2 = new JLabel("" + page.getAlImage().size());
			info.add (nbImage1);
			info.add(nbImage2);
			
		}
				
		modifier = new JButton("Modifier");
		modifier.addActionListener(this);

		add(info);
		add(modifier);

		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(title),
						BorderFactory.createEmptyBorder(2, 2, 2, 2)),
						this.getBorder()));
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		JButton b = (JButton) e.getSource();
		if (b.equals(modifier))
		{
			TreePath path = Controleur.fenetre.getArborescence().getPath();
			if (projet != null && path != null && path.getPathCount() == 2)
			{
				projet.setNom(txNom.getText());
				projet.setAuteur(txAuteur.getText());
			}
			else if (page != null && path != null && path.getPathCount() == 3)
				page.setNom(txNom.getText());
		}
	}
}