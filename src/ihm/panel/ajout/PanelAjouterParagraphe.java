package ihm.panel.ajout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

import ihm.panel.*;
import main.*;
import util.*;

public class PanelAjouterParagraphe extends JPanel implements ActionListener
{
	private PanelListeModFont listeActionFont;
	private JEditorPane editorPane;

	private JButton modifier;
	private JButton valider;

	private int statue;
	private String oldText;

	public PanelAjouterParagraphe(int statue, String paragraphe)
	{
		this.statue = statue;

		setLayout(new BorderLayout());

		editorPane = new JTextPane();
		editorPane.setEditable(true);
		editorPane.setContentType("text/html");

		editorPane.setText(paragraphe);

		JScrollPane scroller = new JScrollPane(editorPane,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		listeActionFont = new PanelListeModFont(this);

		add(listeActionFont, BorderLayout.NORTH);
		add(scroller);

		JPanel panSud = new JPanel();
		panSud.setLayout(new BorderLayout());

		JPanel panBouton = new JPanel();

		if (statue == 0)
		{
			valider = new JButton("Valider");
			valider.addActionListener(this);
			panBouton.add(valider);
		} else
		{
			oldText = editorPane.getText();
			modifier = new JButton("Modifier");
			modifier.addActionListener(this);
			panBouton.add(modifier);
		}

		panSud.add(panBouton, BorderLayout.EAST);
		add(panSud, BorderLayout.SOUTH);

		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createCompoundBorder(
						BorderFactory.createTitledBorder("Votre paragraphe"),
						BorderFactory.createEmptyBorder(2, 2, 2, 2)), this
				.getBorder()));

		setVisible(true);
	}

	public JEditorPane getJEditorPane()
	{
		return editorPane;
	}

	public void actionPerformed(ActionEvent ae)
	{
		Projet projet = Controleur.metier.getProjetSelectionne();
		Page page = projet.getPageSelectionne();

		String paragrapheHTML = editorPane.getText();
		if (paragrapheHTML.length() == 0)
			return;

		// permet de traiter le html
		ArrayList<String> alS = new ArrayList<String>();

		Scanner sc = new Scanner(paragrapheHTML);
		sc.useDelimiter("\n");
		while (sc.hasNext())
			alS.add(sc.next());

		String paragraphe = traitementHTML(alS);

		int cpt;

		if (statue == 0)
		{
			page.ajouterParagrapheHTML(paragraphe);
			cpt = page.getAlParagrapheHTML().size();
			page.ajouterOrdre("Paragraphe " + cpt);
			Controleur.fenetre.getArborescence().ajoutFils(null, "element",
					"Paragraphe " + cpt);
		} else
		{
			for (cpt = 0; cpt < page.getAlParagrapheHTML().size(); cpt++)
			{
				if (page.getAlParagrapheHTML().get(cpt).equals(oldText))
					break;
			}
			page.modParagrapheHTML(paragraphe, cpt);
		}

		Controleur.creerPanelAjouterParagraphe(1, paragraphe);
	}

	private String traitementHTML(ArrayList<String> alS)
	{
		String s = "";
		// les 5 premieres lignes sont inutiles
		for (int i = 0; i < 5; i++)
			alS.remove(0);

		for (String ligne : alS)
		{
			if (ligne.length() == 0)
				continue;
			if (ligne.contains("<p "))
				continue;
			if (ligne.contains("</p>") && !(ligne.contains("</i>") || ligne.contains("</b>")))
				continue;
			if (ligne.contains("</p>") && ligne.contains("</i>"))
			{
				s += "</i>\n";
				continue;
			}
			if (ligne.contains("</p>") && ligne.contains("</b>"))
			{
				s += "</b>\n";
				continue;
			}
			
			if (ligne.contains("</body>"))
				break;

			// l'editeur met 6 espaces au debut des lignes
			ligne = ligne.replace("      ", "");
			
			if (ligne.length() > 74)
			{
				String ligneDebut = ligne.substring(0, 74);
				String ligneFin = ligne.substring(74);
				s += "\t\t\t\t\t" + ligneDebut + "\n\t\t\t\t\t" + ligneFin + "<br />\n";
			}
			else
				s += "\t\t\t\t\t" + ligne + "<br />\n";
		}
		return s;
	}
}