package Utilitaire;

import java.io.*;
import java.util.*;

public class Generator 
{
	private String code;
	private File file;
	private ArrayList<String> alTitre,alParagraphe,alImage;
	
	public Generator()
	{
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
		//alTitre.add("\t\t<div class=\"titre\">"+ s +"</div><br />\n\t\t");
		alTitre.add(s);
	}
	
	public void modTitre(String s, int indice) 
	{
		alTitre.remove(indice);
		alTitre.add(indice, s);
		
		System.out.println(alTitre);
	}
	
	
	
	public void addParagraphe(String s)
	{
	    Scanner sc = new Scanner(s).useDelimiter("\n");
	    String str = "";
	    
	    str +="<p>";
	    while (sc.hasNext())
	    	
	    	str += sc.next()+"<br />";
	    
	    str +="</p>\n\t\t";
	    
	    alParagraphe.add(str);
	}
	
	public void ajouterImage(String chemin)
	{
		// TODO Auto-generated method stub
		alImage.add("<img src='" + chemin + "' />\n");
	}
	
	public void generate() 
	{
		
		String str="";
		
		code += "\n\t</body>\n" +
				"</html>\n";
		
		File file, content,css,img;
		
		file = new File ("site/a1.html");
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
			// on recree le fichier lorsqu'on genere
			file.createNewFile();
			
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
