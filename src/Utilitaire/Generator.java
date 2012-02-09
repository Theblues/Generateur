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
					"\t\t<link rel=\"stylesheet\" href=\"style.css\" />" +
					"\t</head>\n" +
					"\t<body>\n";
	}
	
	private void footer()
	{
		code += "\n\t</body>\n" +
				"</html>\n";
	}
	

	public void generate(Projet projet, Page page)
	{
		if(page == null)
			return;

		ArrayList<String> alS = page.getAlOrdre();
		ArrayList<String> alTitre = page.getAlTitre();
		ArrayList<String> alParagraphe = page.getAlParagraphe();
		ArrayList<String> alImage = page.getAlImage();
		
		if (alS == null || alS.size() == 0)
			return;
		
		header();
		
		code += "<header>\n" +
				"\t<!-- Haut de page -->\n" +
				"</header>\n" +
				"<section>\n" +
				"\t\t<aside>\n" +
				"\t\t<!-- Menu -->\n" +
				"\t</aside>\n" +
				"\t<article>\n";
		
		// on parcours l'ArrayList pour avoir l'ordre des elements
		for (String s : alS )
		{
			Scanner sc = new Scanner(s);
			sc.useDelimiter(" ");
			
			String type = sc.next();
			
			if (!sc.hasNext())
			{
				Controleur.CreerOptionPane("error", "Une erreur est survenue");
				return;
			}
			String indice = sc.next();
			for (int i = 0; i < indice.length(); i++)
			{
				if (!Character.isDigit(indice.charAt(0)))
				{
					Controleur.CreerOptionPane("error", "Une erreur est survenue");
					return;
				}
			}
			int ind = Integer.parseInt(indice)-1;
			
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
		
		code += "</section>\n" +
				"<footer>\n" +
				"\t<!-- Bas de page -->\n" +
				"</footer>\n";
		
		footer();
			
		File file = new File(projet.getCheminDossier() + "/" + projet.getNom() + "/" + page.getNom());
		
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
		Controleur.CreerOptionPane("info", "Generation accomplie");
	}
}