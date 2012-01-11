package IHM.Frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import org.apache.commons.io.IOUtils;

import Main.Generateur;
import Utilitaire.MonFiltre;

public class FenetreAjouterImage extends JFrame implements ActionListener
{
	private AffichageImage image;
	
	private JButton	valider;
	private JButton	annuler;
	
	private String nom;
	private String chemin;

	public FenetreAjouterImage()
	{
		choisirImage();
		if (chemin == null)
			return;
		
		setTitle("Ajouter Image");
		image = new AffichageImage(chemin);

		add(image);

		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(1, 3));

		annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		pan.add(annuler);
		
		valider = new JButton("Valider");
		valider.addActionListener(this);
		pan.add(valider);

		add(pan, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
	private void choisirImage()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Users"));
		chooser.changeToParentDirectory();
		// on peut selectionner qu'une image
		chooser.setMultiSelectionEnabled(false);
		// on ajoute un filtre
		chooser.setFileFilter(new MonFiltre(new String[] { "gif", "tif", "jpeg", "jpg" }, 
							"les fichiers image (*.gif, *.jpg,*.jpeg)"));
		// on ouvre la fenetre de selection
		int fichier = chooser.showOpenDialog(null);
		
		// s'il a choisit un fichier
		if (fichier == JFileChooser.FILES_ONLY)
		{			
			nom = chooser.getSelectedFile().getName();
			// chemin absolu du fichier choisi
			chemin =  chooser.getSelectedFile().getAbsolutePath();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b.equals(annuler))
			dispose();
		if (b.equals(valider))
		{
			enregistrerImage(chemin);
			Generateur.getGenerator().ajouterImage(chemin);
			dispose();
		}
	}

	private void enregistrerImage(String chemin)
	{
		String cheminArr = "./site/content/IMG/";

		InputStream input;
		OutputStream output;

		try
		{
			input = new FileInputStream(chemin);
			output = new FileOutputStream(cheminArr + nom);
			IOUtils.copy(input, output);	
		}
		catch (FileNotFoundException e1){	e1.printStackTrace();	}
		catch (IOException e)			{	e.printStackTrace();	}
		
	}
}

class AffichageImage extends Canvas
{
	Dimension	screenSize		= Toolkit.getDefaultToolkit().getScreenSize();
	int			largeurEcran	= screenSize.width;
	int			hauteurEcran	= screenSize.height;
	Image		image;

	public AffichageImage(String url)
	{
		image = getToolkit().getImage(url);
		prepareImage(image, this);
	}

	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, this);
	}

	public boolean imageUpdate(Image image, int info, int x, int y, int l, int h)
	{
		if ((info & (WIDTH | HEIGHT)) != 0)
		{
			setSize(l, h);
			getParent().getParent().getParent().getParent().setBounds((largeurEcran - l) / 2, (hauteurEcran - h) / 2, l + 8, h + 32);
		}

		if ((info & (ALLBITS)) != 0)
		{
			repaint();
			return false;
		}
		else
		{
			return true;
		}
	}
}
