package Utilitaire;

import java.io.*;
import java.util.*;

import Main.*;

public class Generator
{
	private String headerHTML() 
	{
		return "<html>\n" +
					"\t<head>\n" +
					"\t\t<title>Page 1</title>\n" +
					"\t\t<link rel=\"stylesheet\" href=\"style.css\" />" +
					"\t</head>\n" +
					"\t<body>\n";
	}
	
	private String header()
	{
		return "<header>\n" +
				"\t<!-- Haut de page -->\n" +
				"</header>\n" +
				"<section>\n";
	}
	
	private String menu(Projet projet)
	{
		 String code = "\t\t<aside>\n" +
				"\t\t<ul>\n";
		
		// On parcours l'arraylist de projet
		ArrayList<Page> alP= projet.getAlPage();
				
		for (Page p : alP)
			code += "\t\t\t\t<li>" + p.getNom() + "</li>\n";
				
		code += "\t\t</ul>\n" +
				"\t</aside>\n";
		
		return code;
	}
	
	private String footer()
	{
		return "</section>\n" +
				"<footer>\n" +
				"\t<!-- Bas de page -->\n" +
				"</footer>\n";
	}
	
	private String footerHTML()
	{
		return "\n\t</body>\n" +
				"</html>\n";
	}
	

	public String generateCode(Projet projet, Page page)
	{
		if(page == null)
			return "";
		
		String code = headerHTML();
		code += header();
		code += menu(projet);
				
		ArrayList<String> alS = page.getAlOrdre();
		ArrayList<String> alTitre = page.getAlTitre();
		ArrayList<String> alParagraphe = page.getAlParagraphe();
		ArrayList<String> alImage = page.getAlImage();
		
		if (alS != null && alS.size() != 0)
		{
			// on parcours l'ArrayList pour avoir l'ordre des elements
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
		}
		
		code += footer();
		code += footerHTML();
		return code;
	}
	
	public void generateFile(Projet projet, Page page)
	{
		String code = generateCode(projet, page);
		
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