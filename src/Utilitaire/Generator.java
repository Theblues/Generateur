package Utilitaire;

import java.io.*;
import java.util.*;

import Main.Generateur;

public class Generator 
{
	private String code;
	private File file;
	private ArrayList<String> alTitre,alParagraphe,alImage;
	
	public Generator(File file)
	{
		this.file = file;
		code = header();
		alTitre =  new ArrayList<String>();
		alParagraphe =  new ArrayList<String>();
		alImage =  new ArrayList<String>();
	}
	
	public void setFichier(File file)
	{
		this.file = file;
	}
	
	private String header() 
	{
		return "<html>\n" +
					"\t<head>\n" +
					"\t\t<title>Page 1</title>\n" +
					"\t</head>\n" +
					"\t<body>\n";
	}
	
	public void addTitre(String s) 
	{
		alTitre.add(s);
	}
	
	public void modTitre(String s, int indice) 
	{
		alTitre.remove(indice-1);
		alTitre.add(indice-1, s);
	}
	
	public void addParagraphe(String s)
	{
	    Scanner sc = new Scanner(s).useDelimiter("\n");
	    String str = "";
	    
	    while (sc.hasNext())
	    	str += "\n\t\t\t" + sc.next()+"<br />\n";

	    alParagraphe.add(str);
	}
	
	
	public void modParagraphe(String s, int indice)
	{
	    Scanner sc = new Scanner(s).useDelimiter("\n");
	    String str = "";
	    
	    while (sc.hasNext())
	    	str += "\n\t\t\t" + sc.next()+"<br />\n"; 
	    
	    alParagraphe.remove(indice-1);
		alParagraphe.add(indice-1, str);
	}
	
	public void ajouterImage(String chemin)
	{
		// TODO Auto-generated method stub
		alImage.add("<img src='" + chemin + "' />\n");
	}
	
	public void generate() 
	{
		ArrayList<String> alS = Generateur.getFenetre().getArborescence().getOrdreElement();
		
		for (String s : alS )
		{
			Scanner sc = new Scanner(s);
			sc.useDelimiter(" ");
			
			String type = sc.next();
			int ind = Integer.parseInt(sc.next())-1;
			
			if (type.equals("Titre"))
				code += "\t\t<div class=\"title\">"+alTitre.get(ind)+"</div>\n";
			
			if (type.equals("Paragraphe"))
				code += "\t\t<p>" + alParagraphe.get(ind) + "\t\t</p>\n";
		}
		
		code += "\n\t</body>\n" +
				"</html>\n";
		
		File content,css,img;
		
		content = new File ("site/content");
		css = new File ("site/content/CSS");
		img = new File ("site/content/IMG");
		
		if ( !content.exists())
		{
			content.mkdir();
			if ( !css.exists())
				css.mkdir();
			if ( !img.exists())
				img.mkdir();
		}
		
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
