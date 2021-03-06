package util;

import java.io.*;
import java.util.*;

public class Generator
{
	private String headerHTML(Projet projet) 
	{
		return "<html>\n" +
					"\t<head>\n" +
					"\t\t<title>" + projet.getNom() + "</title>\n" +
					"\t\t<link rel=\"stylesheet\" href=\"./content/css/" + projet.getStyle() + ".css\" />\n" + 
					"\t</head>\n" +
					"\t<body>\n";
	}
	
	private String header(String titre)
	{
		String code = "\t\t<div id=\"Wrapper\">\n" +
				"\t\t\t<header>\n" +
				"\t\t\t\t<!-- Haut de page -->\n";
		
		code += "\t\t\t\t" + titre + "\n";
		
		code +=
				"\t\t\t</header>\n" +
				"\t\t\t<section>\n";
		
		return code;
	}
	
	private String menu(ArrayList<Page> alP)
	{
		 String code = "\t\t\t\t<aside>\n" +
				"\t\t\t\t\t<ul>\n";
		
		// On parcours l'arraylist de projet				
		for (Page p : alP)
			code += "\t\t\t\t\t\t<li><a href=\" ./" + p.getFile().getName() + "\">" + p.getNom() + "</a></li>\n";
				
		code += "\t\t\t\t\t</ul>\n" +
				"\t\t\t\t</aside>\n" +
				"\t\t\t\t<article>\n";
		
		return code;
	}
	
	private String footer(Projet projet)
	{
		String code = "\t\t\t\t</article>\n" +
				"\t\t\t</section>\n" +
				"\t\t\t<footer>\n" +
				"\t\t\t\t<!-- Bas de page -->\n";
		
		code += "\t\t\t\t&copy;" + projet.getAuteur() + " - " + projet.getAnnee() + " - Tous Droits Reserves.\n";
		code +=	"\t\t\t</footer>\n" +
				"\t\t</div>\n";
		
		return code;
	}
	
	private String footerHTML()
	{
		return "\t</body>\n" +
				"</html>\n";
	}
	
	private String generateCode(Projet projet, Page page)
	{
		if(page == null)
			return "";
		
		String code = headerHTML(projet);
		code += header(page.getNom());
		code += menu(projet.getAlPage());
				
		ArrayList<String> alS = page.getAlOrdre();
		ArrayList<String> alTitre = page.getAlTitre();
		ArrayList<String> alParagraphe = page.getAlParagrapheHTML();
		ArrayList<String> alImage = page.getAlImageHTML();
		
		if (alS != null && alS.size() != 0)
		{
			// on parcours l'ArrayList pour avoir l'ordre des elements
			for (String s : alS)
			{
				Scanner sc = new Scanner(s);
				sc.useDelimiter(" ");
				
				String type = sc.next();
				int ind = Integer.parseInt(sc.next())-1;
				
				if (type.equals("Titre"))
					code += "\t\t\t\t\t<div class=\"title\">\n"+alTitre.get(ind)+"\n</div>\n";
				
				if (type.equals("Paragraphe"))
				{
					Scanner scan = new Scanner(alParagraphe.get(ind)).useDelimiter("\n");
				    String str = "";
				    
				    while (scan.hasNext())
				    	str += scan.next() + "\n";
				    
					code += "\t\t\t\t<p>\n" + str + "\t\t\t\t</p>\n";
				}
			
				if (type.equals("Image"))
					code += "\t\t\t\t\t<img src=\""+alImage.get(ind)+"\">\n";	
			}
		}
		
		code += footer(projet);
		code += footerHTML();
		return code;
	}
	
	public void generateFile(Projet projet, Page page)
	{
		String code = generateCode(projet, page);
		File file = page.getFile();
		
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
	}
}