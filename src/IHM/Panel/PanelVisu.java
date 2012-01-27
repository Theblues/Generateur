package IHM.Panel;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import Main.Controleur;
import Utilitaire.*;

public class PanelVisu extends JPanel
{
	private JTextPane jEditor;

	public PanelVisu()
	{
		setLayout(new BorderLayout());
		jEditor = new JTextPane();
		jEditor.setEditable(false);
		jEditor.setSize(800,800);
		JScrollPane scroller = new JScrollPane( jEditor,
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
	
	public void previsualisation()
	{
		String contenu = "";
		jEditor.setText(contenu);
		
		ArrayList<String> alS = Controleur.fenetre.getArborescence().getOrdreElement();
		Projet projet = Controleur.metier.getProjetSelectionne();
		if (projet == null)
			return;
		Page page = projet.getPageSelectionne();
		if(page == null)
			return;
		ArrayList<String> alTitre = projet.getPage(page).getAlTitre();
		ArrayList<String> alParagraphe = projet.getPage(page).getAlParagraphe();
		ArrayList<String> alImage = projet.getPage(page).getAlImage();
		
		if (alS == null || alS.size() == 0)
			return;
		
		for (String s : alS )
		{
			Scanner sc = new Scanner(s);
			sc.useDelimiter(" ");
			
			String type = sc.next();
			
			//S'il n'y a pas de suivant, il y a une erreur
			if (!sc.hasNext())
			{
				Controleur.CreerOptionPane("error", "Une erreur est survenue");
				return;
			}
			
			String indice = sc.next();
			// on vérifie que le deuxieme argument est un nombre
			for (int i = 0; i < indice.length(); i++)
			{
				if (!Character.isDigit(indice.charAt(0)))
				{
					Controleur.CreerOptionPane("error", "Previsualisation impossible");
					return;
				}
			}
			int ind = Integer.parseInt(indice)-1;
			
			if (type.equals("Titre"))
				contenu += alTitre.get(ind)+"\n\n";
			
			if (type.equals("Paragraphe"))
			{
				Scanner scan = new Scanner(alParagraphe.get(ind)).useDelimiter("\n");
			    while (scan.hasNext())
			    	contenu += "\t"+scan.next() + "\n";
			}
			
			if (type.equals("Image"))
				contenu += "<img src=\""+alImage.get(ind)+"\">\n";	
		}
		jEditor.setText(contenu);
	}
}