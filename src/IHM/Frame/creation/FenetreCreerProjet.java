package IHM.Frame.creation;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

import org.apache.commons.io.IOUtils;

import Main.*;
import util.*;

public class FenetreCreerProjet extends JFrame implements ActionListener
{	
	private JTextField txNom;
	private JTextField txChemin;
	private JComboBox combo;
	
	private JButton parcourir;
	
	private JButton annuler;
	private JButton valider;
	
	public FenetreCreerProjet()
	{
		setTitle("Nouveau Projet");
		setLocation(100, 100);
		setSize(500, 400);
		
		JLabel labelDescription = new JLabel("Creer votre projet");
		add(labelDescription, BorderLayout.NORTH);
		
		// Panel Nom
		JPanel panelNom = new JPanel();
		panelNom.setLayout(new FlowLayout(2, 100, 0));
		JLabel label = new JLabel("Nom du projet");
		txNom = new JTextField(20);
		
		panelNom.add(label);	
		panelNom.add(txNom);
		
		// Panel Theme
		JPanel panelTheme = new JPanel();
		panelTheme.setLayout(new FlowLayout(2, 175, 0));
		label = new JLabel("Choisissez un theme");
		combo = new JComboBox();
		combo.setPreferredSize(new Dimension(100, 30));
		combo.addItem("Theme 1");
		combo.addItem("Theme 2");
		combo.addItem("Theme 3");
		
		panelTheme.add(label, BorderLayout.WEST);
		panelTheme.add(combo);
		
		// Panel Dossier
		JPanel panelDossier = new JPanel();
		panelDossier.setLayout(new BorderLayout());
		label = new JLabel("Dossier");
		txChemin = new JTextField(20);
		txChemin.setEditable(false);
		parcourir = new JButton("Parcourir");
		parcourir.addActionListener(this);
		panelDossier.add(label, BorderLayout.WEST);
		panelDossier.add(txChemin);
		panelDossier.add(parcourir, BorderLayout.EAST);
		
		// Panel englobant le tout
		JPanel panelCentre = new JPanel();
		panelCentre.add(panelNom);
		panelCentre.add(panelTheme);
		panelCentre.add(panelDossier);
		
		add(panelCentre);
		
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
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();
		if (b.equals(valider))
		{
			if (creerProjet())
				this.dispose();
		}
		else if (b.equals(annuler))
			this.dispose();
		else if (b.equals(parcourir))
		{
			choisirDossier();
		}
	}

	private void choisirDossier()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Users"));
		chooser.changeToParentDirectory();
		// on peut selectionner qu'un dossier
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int fichier = chooser.showOpenDialog(null);

		if (fichier == 0)
			txChemin.setText(chooser.getSelectedFile().getAbsolutePath());
	}

	private boolean creerProjet()
	{
		String nomProjet = txNom.getText();
		String chemin = txChemin.getText();
		if (nomProjet.length() == 0  || chemin.length() == 0)
		{
			Controleur.CreerOptionPane("warning", "Veuillez saisir toutes les informations");
			return false;
		}
		
		Scanner sc = new Scanner(nomProjet);
		sc.useDelimiter(" ");
		
		String nameProjet = sc.next();
		while (sc.hasNext())
			nameProjet += "_" + sc.next();
		
		File file = new File(chemin + "/" + nameProjet);
		file.mkdir();
		
		File content = new File (chemin + "/" + nameProjet + "/content");
		File css = new File (chemin + "/" + nameProjet + "/content/CSS");
		File img = new File (chemin + "/" + nameProjet + "/content/IMG");
		
		content.mkdir();
		css.mkdir();
		img.mkdir();
		
		InputStream input = null;
		OutputStream output = null;
		
		String style = "";
		try
		{
			if (combo.getSelectedItem().equals("Theme 1"))
			{
				style = "style1";
				input = new FileInputStream("styles/style1.css");
				output = new FileOutputStream(chemin + "/" + nameProjet + "/content/CSS/style1.css");
			}
			else if (combo.getSelectedItem().equals("Theme 2"))
			{
				style = "style2";
				input = new FileInputStream("styles/style2.css");
				output = new FileOutputStream(chemin + "/" + nameProjet + "/content/CSS/style2.css");
			}
			else if (combo.getSelectedItem().equals("Theme 3"))
			{
				style = "style3";
				input = new FileInputStream("styles/style3.css");
				output = new FileOutputStream(chemin + "/" + nameProjet + "/content/CSS/style3.css");
			}
			IOUtils.copy(input, output);	
		}
		catch (FileNotFoundException e1){	e1.printStackTrace();	}
		catch (IOException e)			{	e.printStackTrace();	}
		
		Projet projet = new Projet(nomProjet, style, chemin);
		Controleur.metier.ajouterProjet(projet);
		Controleur.metier.setProjetSelectionne(projet);
		Controleur.fenetre.getArborescence().ajoutFils(null, "projet", nomProjet);
		Controleur.fenetre.getMenu().activerCreationPage();
		
		return true;
	}
}