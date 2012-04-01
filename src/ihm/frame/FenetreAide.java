package ihm.frame;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Composition Fenetre
 * 
 *    ______________________________
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |	liste	|	  Content		|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |__________|___________________|
 * 
 */
public class FenetreAide extends JFrame
{
	JEditorPane affichageAide;
	JList choix;

	public FenetreAide() {

		setTitle("Aide");
		setSize(500, 350);
		setLocation(250, 250);
		setLayout(new BorderLayout(10, 10));

		final String[] chapitres = { "Premiers pas", "Nouveau projet",
				"Nouvelle page", "Ajouter titre", "Ajouter paragraphe",
				"Ajouter image" };

		choix = new JList(chapitres);
		choix.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		final JEditorPane affichageAide = new JEditorPane();
		affichageAide.setContentType("text/html");
		affichageAide.setEditable(false);

		choix.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				if (chapitres[choix.getSelectedIndex()].equals("Premiers pas")) {
					affichageAide
							.setText("<center><b>Bienvenue dans le menu d'aide du logiciel x.</b></center><br>"
									+ "Grace � ce logiciel, vous pourrez ais�ment g�n�rer un site web.");

				}

				if (chapitres[choix.getSelectedIndex()]
						.equals("Nouveau projet")) {
					affichageAide
							.setText("<center><b>Cr�er un nouveau projet</b></center><br> - Cliquez sur \"Fichier\" > \"Nouveau projet\" "
									+ " ou cliquez sur le bouton \"Nouveau projet\" se trouvant sur l'en t�te.<br><br>"
									+ " - Saisissez le nom de votre projet"
									+ " puis le nom de l'auteur du projet.<br><br>"
									+ " - Choisissez le theme que vous d�sirez.<br><br>"
									+ " - Cliquez sur le bouton \"Parcourir\" afin de choisir le dossier o� sera stock� votre site.<br><br>"
									+ " - Cliquez sur \"Valider\" pour confirmer la cr�ation de votre projet.");
				}

				if (chapitres[choix.getSelectedIndex()].equals("Nouvelle page")) {
					affichageAide
							.setText("<center><b>Cr�er une nouvelle page</b></center><br><br>"
									+ " - S�lectionnez dans l'arborescence le projet auquel vous souhaitez ajouter une page.<br><br>"
									+ " - Cliquez sur \"Fichier\" > \"Nouvelle page\" ou cliquez sur le bouton \"Nouvelle page\".<br><br>"
									+ " - Saisissez le nom que vous souhaitez donner � votre page puis cliquez sur \"Valider\".");
				}

				if (chapitres[choix.getSelectedIndex()].equals("Ajouter titre")) {
					affichageAide
							.setText("<center><b>Ajouter un titre � votre page</b></center><br><br> - Double-cliquez sur votre projet ou se trouve votre page.<br><br>"
									+ " - S�lectionnez la page o� vous souhaitez ajouter un titre et cliquez sur le bouton \"Ajouter un titre\".<br><br>"
									+ " - Saisissez le titre que vous voulez ajouter � votre page et validez.");
				}

				if (chapitres[choix.getSelectedIndex()]
						.equals("Ajouter paragraphe")) {
					affichageAide
							.setText("<center><b>Ajouter un paragraphe</b></center><br><br> - Cliquez sur le projet o� vous souhaitez ajouter le paragraphe et cliquez sur le bouton \"Ajouter un paragraphe\".<br><br>"
									+ " - Ecrivez votre paragraphe et validez.");
				}

				if (chapitres[choix.getSelectedIndex()].equals("Ajouter image")) {
					affichageAide
							.setText("<center><b>Ajouter une image</b></center><br><br> - Cliquez sur le projet o� vous souhaitez ajouter l'image et cliquez sur le bouton ajouter une image.<br><br>"
									+ " - Choisissez l'image que vous voulez dans votre repertoire aux formats, .png, .gif, .jpg ou .jpeg). Une fois l'image choisie, validez si vous souhaitez la conserver sinon annulez.");
				}

			}
		});

		add(choix, "West");
		add(affichageAide);

		setVisible(true);
	}
}
