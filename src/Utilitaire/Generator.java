package Utilitaire;

import java.io.*;
import java.util.*;

import Main.*;

public class Generator 
{
	private String code;
	
	private void header() 
	{
		code = "<html>\n" +
					"\t<head>\n" +
					"\t\t<title>Page 1</title>\n" +
					"\t</head>\n" +
					"\t<body>\n";
	}
	
	private void footer()
	{
		code += "\n\t</body>\n" +
				"</html>\n";
	}
	

	public void generate() 
	{
		Projet projet = Controleur.metier.getProjetSelectionne();
		if (projet == null)
			return;
		
		Page page = projet.getPageSelectionne();
		if(page == null)
			return;
		
		ArrayList<String> alS = Controleur.fenetre.getArborescence().getOrdreElement();
		ArrayList<String> alTitre = projet.getPage(page).getAlTitre();
		ArrayList<String> alParagraphe = projet.getPage(page).getAlParagraphe();
		ArrayList<String> alImage = projet.getPage(page).getAlImage();
		
		if (alS.size() == 0)
			return;
		
		header();
		for (String s : alS )
		{
			Scanner sc = new Scanner(s);
			sc.useDelimiter(" ");
			
			String type = sc.next();
			int ind = Integer.parseInt(sc.next())-1;
			
			if (type.equals("Titre"))
				code += "\t\t<div class=\"title\">"+alTitre.get(ind)+"</div>\n";
			
			if (type.equals("Paragraphe"))
			{
				Scanner scan = new Scanner(alParagraphe.get(ind)).useDelimiter("\n");
			    String str = "";
			    
			    while (scan.hasNext())
			    	str += "\n\t\t\t" + scan.next()+"<br />\n\t\t\t";
			    
				code += "\t\t<p>" + str + "</p>\n";
			}
			
			if (type.equals("Image"))
				code += "\t\t<img src=\""+alImage.get(ind)+"\">\n";	
		}
		footer();
			
		File file = new File("projet/" + projet.getNom() + "/" + page.getNom());
		
		try
		{
			// on ecris le code dedans
			BufferedWriter fichier = new BufferedWriter(new FileWriter(file));
			fichier.write(code);
			
			fichier.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(code);
	}
}
