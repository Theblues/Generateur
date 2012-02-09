package IHM.Panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;

import java.io.*;
import java.util.*;

import Main.*;
import Utilitaire.*;

public class PanelArbre extends JPanel implements Serializable
{
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	private JScrollPane editeurScrollHorizontal;
	private JScrollPane editeurScrollVertical;

	private Object parentNodeFichier = null;
	private Object parentNodeProjet = null;
	private Object parentNodeElement = null;
	
	private Page pageSelectionnee;
	private Projet projetSelectionne;
	
	private int locationRow;
	
	public PanelArbre(JFrame f) 
	{		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(175, 100));

		init();
		
		if(arbre == null)
		{
			racine = new DefaultMutableTreeNode(new File("projet"));
			arbre = new JTree(racine);
			f.getContentPane().add(new JScrollPane(arbre));
		}
		
		// ajoute l'action clic a l'arbre
		arbre.addMouseListener(new MouseAdapter() {
		      public void mouseClicked(MouseEvent me) {
		    	  if (me.getClickCount() == 2)
		    		  doMouseDoubleClicked(me);
		    	  else
		    		  doMouseSimpleClicked(me);
		      }
		    });

		editeurScrollHorizontal = new JScrollPane(arbre);
		editeurScrollHorizontal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		editeurScrollHorizontal.setPreferredSize(new Dimension(250, 145));
		
		editeurScrollVertical = new JScrollPane(arbre);
		editeurScrollVertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		editeurScrollVertical.setPreferredSize(new Dimension(250, 145));
		
		add(editeurScrollHorizontal);
		add(editeurScrollVertical);
	}
	
	private void init() 
	{
		try {
			FileInputStream fichier = new FileInputStream("temp/arbre.dat");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			arbre = (JTree) ois.readObject();
			racine = (DefaultMutableTreeNode) ois.readObject();
		}
		catch (IOException ignored) 		{}
		catch (ClassNotFoundException e)	{}
		if (arbre != null && racine != null)
			updateTree(racine);
	}
	
	private void doMouseDoubleClicked(MouseEvent me) 
	{
		TreePath path = arbre.getPathForLocation(me.getX(), me.getY());
		if (path != null)
		{
			int location = path.getPathCount();
			
			if (location == 3)
				Controleur.fenetre.getPanelVisu().previsualisation();
			else if (location > 3)
			{
				Scanner sc = new Scanner(path.getLastPathComponent().toString()).useDelimiter(" ");
				String str = sc.next();
				int indice = Integer.parseInt(sc.next());
				
				if (str.equals("Titre"))
				{
					String ancienTitre = projetSelectionne.getPage(pageSelectionnee).getAlTitre().get(indice-1);
					Controleur.creerFenetreAjouterTitre(1, ancienTitre, indice);
				}
				if (str.equals("Paragraphe"))
				{
					String ancienParagraphe = projetSelectionne.getPage(pageSelectionnee).getAlParagraphe().get(indice-1);
					Controleur.creerFenetreAjouterParagraphe(1, ancienParagraphe, indice);
				}
				if (str.equals("Image"))
					Controleur.creerFenetreAjouterImage(1);
			}			
		}
	}
	
	private void doMouseSimpleClicked(MouseEvent me) 
	{
		TreePath path = arbre.getPathForLocation(me.getX(), me.getY());
		locationRow = arbre.getClosestRowForLocation(me.getX(), me.getY());
		/*
		 * Exemple path
		 * [null, site, test.html, titre 1]
		 */
		if (path != null)
		{
			Object[] tabObj = path.getPath();
			int location = path.getPathCount();
			/*
			 * Exemple Location :
			 * 1	2	3	4
			 * /home
			 * 		site
			 * 			test1.html
			 * 				Titre 1
			 * 				Paragraphe 1
			 * 			test2.html
			 * 				Titre 1
			 * 				Paragraphe 1
			 */
			if (location == 2)
			{
				Controleur.fenetre.getMenu().activerCreationPage();
				Controleur.fenetre.getPanelListeAction().activerCreationPage();
				// on desactive les ajouts de titre/paragraphe/image
				Controleur.fenetre.getMenu().desactiveAjout();
				Controleur.fenetre.getPanelListeAction().desactiveAjout();
				
				// on recupere le projet grace a path
				parentNodeProjet = tabObj[1];
				
				// on recupere le projet selectionne
				projetSelectionne = Controleur.metier.getProjet(parentNodeProjet.toString());
				
				// on modifie le projet selectionne
				Controleur.metier.setProjetSelectionne(projetSelectionne);
			}
			else if (location == 3)
			{
				// on recupere le projet et la page grace a path
				parentNodeProjet = tabObj[1];
				parentNodeFichier = tabObj[2];
				
				// on active les boutons/items
				Controleur.fenetre.getMenu().activerAjout();
				Controleur.fenetre.getPanelListeAction().activerAjout();
				
				// on recupere le projet et la page selectionnee
				projetSelectionne = Controleur.metier.getProjet(parentNodeProjet.toString());
				pageSelectionnee = projetSelectionne.getPage(parentNodeFichier.toString());
				
				// on modifie le projet et la page selectionnee
				Controleur.metier.setProjetSelectionne(projetSelectionne);
				projetSelectionne.setPageSelectionne(pageSelectionnee);
			}
			else if (location > 3)
			{
				// on recupere le projet, la page et l'element grace a path
				parentNodeProjet = tabObj[1];
				parentNodeFichier = tabObj[2];
				parentNodeElement = tabObj[3];
				
				// on recupere le projet et la page selectionnee
				projetSelectionne = Controleur.metier.getProjet(parentNodeProjet.toString());
				pageSelectionnee = projetSelectionne.getPage(parentNodeFichier.toString());
				
				// on modifie le projet et la page selectionnee
				Controleur.metier.setProjetSelectionne(projetSelectionne);
				projetSelectionne.setPageSelectionne(pageSelectionnee);
			}
		}
	}
	
	public JTree getArbre() 					{	return arbre;	}
	public DefaultMutableTreeNode getRacine()	{	return racine;	}
	
	public boolean ajoutFils(String type, String s) 
	{
		DefaultTreeModel dtm = new DefaultTreeModel(racine);
		DefaultMutableTreeNode mtn = new DefaultMutableTreeNode(new File(s));
		if (type.equals("projet"))
		{
			// on insert un noeud a la racine
			dtm.insertNodeInto(mtn, racine, racine.getChildCount());
			// on rafraichis l'arbre
			updateTree(racine);
			return true;
		}
		
		MutableTreeNode parent  = (MutableTreeNode) ((parentNodeProjet == null) ?  dtm.getChild(racine, 0) : parentNodeProjet);
		if (type.equals("fichier"))
		{
			dtm.insertNodeInto(mtn, parent, parent.getChildCount());
			updateTree(parent);
			return true;
		}
		
		MutableTreeNode parent2 = (MutableTreeNode) ((parentNodeFichier == null) ?  dtm.getChild(parent, 0) : parentNodeFichier);
		if (type.equals("element"))
		{
			dtm.insertNodeInto(mtn, parent2, parent2.getChildCount());
			updateTree(parent2);
			return true;
		}
		return false;
	}
	
	public void modifierNoeudSuivant(String nouveauTitre)
	{
		// on recupere path du noeud suivant
		TreePath path = arbre.getPathForRow(locationRow+1);
		if (path != null)
		{
			Object[] tabObj = path.getPath();
			// on selectionne le nom du noeud
			String nouveauDuSuivant = tabObj[3].toString();
			//on recupere le nom du noeud que l'on veut bouger
			String monNom = parentNodeElement.toString();
			
			// si le type des nom est le meme on arrete sinon on modifie
			if(verificationDesNom(monNom, nouveauDuSuivant))
				return;
			
			DefaultMutableTreeNode noeud = (DefaultMutableTreeNode) parentNodeElement;
			// on modifie notre nom
			noeud.setUserObject(nouveauDuSuivant);
			
			noeud = (DefaultMutableTreeNode) tabObj[3];
			// on modifie l'autre nom
			noeud.setUserObject(monNom);
			
			// on rafraichis l'arbre
			updateTree(parentNodeFichier);
		}
	}
	
	private boolean verificationDesNom(String monNom, String nouveauDuSuivant)
	{
		Scanner sc1 = new Scanner(monNom);
		Scanner sc2 = new Scanner(nouveauDuSuivant);
		
		sc1.useDelimiter(" ");
		sc2.useDelimiter(" ");
		
		String type1 = sc1.next();
		String type2 = sc2.next();
		
		if (type1.equals(type2))
			return true;
		
		return false;
	}

	private void updateTree(Object o)
	{
		((DefaultTreeModel) arbre.getModel()).reload((TreeNode) o);
	}
	
	public void enregistrerArbre()
	{
		try {
			FileOutputStream fichier = new FileOutputStream("temp/arbre.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(arbre);
			oos.writeObject(racine);
			oos.flush();
			oos.close();
		}
		catch (java.io.IOException e) 
		{
			e.printStackTrace();
		}
	}
}