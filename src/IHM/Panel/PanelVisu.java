package IHM.Panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.html.*;

import Main.Controleur;
import Utilitaire.*;

public class PanelVisu extends JPanel
{
	private JEditorPane editor;

	public PanelVisu()
	{
		setLayout(new BorderLayout());
		editor = new JEditorPane();
		editor.setEditable(false);
		editor.setEditorKit(new HTMLEditorKit());
		
		JScrollPane scroller = new JScrollPane(editor,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		// on ajoute une bordure
		scroller.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Previsualisation de votre site"),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)),
						scroller.getBorder()));
		add(scroller);
	}
	
	public void previsualisation(Projet projet, Page page)
	{
		String contenu = Controleur.metier.getGenerator().generateCode("previsu", projet, page);
		editor.setText(contenu);
	}
}