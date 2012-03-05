package IHM.Panel.creation;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

import org.apache.commons.io.IOUtils;

import Main.*;
import util.*;

public class PanelCreerProjet extends JPanel implements ActionListener
{	
	private JTextField 	txNom;
	private JTextField 	txAuteur;
	private JTextField 	txChemin;
	private JComboBox 	combo;
	
	private JButton parcourir;
	
	private JButton annuler;
	private JButton valider;
	
	public PanelCreerProjet()
	{
		setLayout(new BorderLayout());
		
		// Panel Nom
		JPanel panelNom = new JPanel();
		panelNom.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		JLabel label = new JLabel("Nom du projet :");
		txNom = new JTextField(20);
		
		panelNom.add(label);
		panelNom.add(txNom);
		
		// Panel auteur
		JPanel panelAuteur = new JPanel();
		panelAuteur.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		label = new JLabel("Nom de l'auteur :");
		txAuteur = new JTextField(20);
		
		panelAuteur.add(label);
		panelAuteur.add(txAuteur);
		
		// Panel Theme
		JPanel panelTheme = new JPanel();
		panelTheme.setLayout(new FlowLayout(FlowLayout.LEFT, 50 ,10));
		label = new JLabel("Choisissez un theme");
		combo = new JComboBox();
		combo.setPreferredSize(new Dimension(100, 30));
		combo.addItem("Theme 1");
		combo.addItem("Theme 2");
		combo.addItem("Theme 3");
		
		panelTheme.add(label);
		panelTheme.add(combo);
		
		// Panel Dossier
		JPanel panelDossier = new JPanel();
		panelDossier.setLayout(new FlowLayout(FlowLayout.LEFT, 65, 10));
		
		label = new JLabel("Dossier :");
		txChemin = new JTextField(20);
		txChemin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if(me.getButton() == MouseEvent.BUTTON1)
					choisirDossier();
			}
		});;
		txChemin.setEditable(false);
		
		// bouton pour recuperer le dossier
		parcourir = new JButton("Parcourir");
		parcourir.addActionListener(this);
		
		panelDossier.add(label, BorderLayout.WEST);
		panelDossier.add(txChemin);
		panelDossier.add(parcourir, BorderLayout.EAST);
		
		// Panel englobant le tout
		JPanel panelCentre = new JPanel();
		panelCentre.setSize(800, 500);
		panelCentre.setLayout(new GridLayout(4, 1));
		//panelCentre.setLayout(new BorderLayout(3,1));
		panelCentre.add(panelNom);
		panelCentre.add(panelAuteur);
		panelCentre.add(panelTheme);
		panelCentre.add(panelDossier);
		
		add(panelCentre);
		
		JPanel panSud = new JPanel();
		panSud.setLayout(new BorderLayout());
		
		JPanel panBouton = new JPanel();
		annuler = new JButton("Annuler");
		panBouton.add(annuler);
		
		valider = new JButton("Valider");
		valider.addActionListener(this);
		panBouton.add(valider);
		
		panSud.add(panBouton, BorderLayout.EAST);
		add(panSud, BorderLayout.SOUTH);
		
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Creation d'un nouveau projet"),
						BorderFactory.createEmptyBorder(2, 2, 2, 2)),
						this.getBorder()));
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();
		if (b.equals(valider))
		{
			if (creerProjet())
			{
				Controleur.fenetre.getPanelAjout().supprimerPanel();
				return;
			}
		}
		else if (b.equals(annuler))
		{
			Controleur.fenetre.getPanelAjout().supprimerPanel();
			return;
		}
		else if (b.equals(parcourir))
			choisirDossier();			
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
		String auteur = txAuteur.getText();
		String chemin = txChemin.getText();
		
		if (nomProjet.length() == 0  || chemin.length() == 0 || auteur.length() == 0)
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
		
		Projet projet = new Projet(nomProjet, auteur,style, chemin);
		Controleur.metier.ajouterProjet(projet);
		Controleur.metier.setProjetSelectionne(projet);
		Controleur.fenetre.getArborescence().ajoutFils(null, "projet", nomProjet);
		
		return true;
	}
}