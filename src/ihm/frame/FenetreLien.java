package ihm.frame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.Controleur;
import ihm.panel.ajout.PanelAjouterParagraphe;

import java.util.Scanner;

public class FenetreLien extends JFrame implements ActionListener
{
	private JTextField txLien;
	private JTextField txUrl;
	private JButton buttonOk;
	private JButton buttonAnnuler;
	private PanelAjouterParagraphe panAjoutParagraphe;
	
	public FenetreLien(PanelAjouterParagraphe panAjoutParagraphe)
	{
		setLayout(new FlowLayout());
		setTitle("Entrer votre lien");
		setLocation(200,200);
		setSize(330, 150);
		
		this.panAjoutParagraphe = panAjoutParagraphe;
		
		JLabel nomLien = new JLabel("Nom du lien");
		JLabel nomUrl = new JLabel("Url du lien");
		
		txLien = new JTextField(10);
		txUrl = new JTextField(10);
		
		buttonOk = new JButton("Ok");
		buttonAnnuler = new JButton("Annuler");
		
		buttonOk.addActionListener(this);
		buttonAnnuler.addActionListener(this);
		
		JPanel p = new JPanel(new GridLayout(2,2));
		p.add(nomLien);
		p.add(txLien);
		p.add(nomUrl);
		p.add(txUrl);
		
		add(p);
		add(buttonAnnuler);
		add(buttonOk);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent a) 
	{
		JButton button = (JButton) a.getSource();
		
		if (button.equals(buttonOk))
		{
			String url = txUrl.getText();
			String lien = txLien.getText();
			if (url == null || url.length() == 0 || lien == null || lien.length() == 0)
			{
				Controleur.creerOptionPane("error", "Veuillez entrer toutes les informations");
				return;
			}
			// on recupere le texte de l'editeur
			String texte = panAjoutParagraphe.getJEditorPane().getText();
			String texteFinal = "";
			
			// on le traite ligne par ligne
			Scanner sc = new Scanner (texte);
			sc.useDelimiter("\n");
			while (sc.hasNext())
			{
				String ligne = sc.next();
				// si la ligne contient la balise de fin de paragraphe, on ajoute le lien
				if (ligne.contains("</p>"))
					texteFinal += 	"<a href=\"" + url + "\">" + lien + "</a>&nbsp;\n";
				else	
					texteFinal += ligne+"\n"; // on ajoute la ligne au texte
			}
			panAjoutParagraphe.getJEditorPane().setText(texteFinal);
		}

		this.dispose();
	}
}