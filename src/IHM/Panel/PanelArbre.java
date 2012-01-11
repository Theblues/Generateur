package IHM.Panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;

import java.io.*;
import java.util.*;

import IHM.Frame.*;
import Main.Generateur;

public class PanelArbre extends JPanel 
{
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	private JScrollPane editeurScrollHorizontal;
	private JScrollPane editeurScrollVertical;
	private ArrayList<String> alS;

	private Object parentNodeFichier = null;
	private Object parentNodeProjet = null;
	
	public PanelArbre(JFrame f) 
	{
		alS = new ArrayList<String>();
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(150, 100));

		listRoot(f);

		editeurScrollHorizontal = new JScrollPane(arbre);
		editeurScrollHorizontal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		editeurScrollHorizontal.setPreferredSize(new Dimension(250, 145));
		
		editeurScrollVertical = new JScrollPane(arbre);
		editeurScrollVertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		editeurScrollVertical.setPreferredSize(new Dimension(250, 145));
		
		add(editeurScrollHorizontal);
		add(editeurScrollVertical);
	}
	
	
	public String getAlS(int ind) 
	{
		return alS.get(ind);
	}

	private void listRoot(JFrame f) 
	{
		racine = new DefaultMutableTreeNode();
		File file = new File("site/");

		DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getPath());
		try 
		{
			for (File nom : file.listFiles()) 
			{
				if (!nom.getName().equals("content"))
				{
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName() + "\\");
					lecteur.add(this.listFile(nom, node));
				}
			}
		} catch (NullPointerException e) {}

		racine.add(lecteur);

		arbre = new JTree(racine);
		
		f.getContentPane().add(new JScrollPane(arbre));
		
		arbre.addMouseListener(new MouseAdapter() {
		      public void mouseClicked(MouseEvent me) {
		        doMouseClicked(me);
		      }
		    });
	}
	
	void doMouseClicked(MouseEvent me) 
	{		
		// 3 correspond au nombre de parents depuis le dossier site
		int tp = (arbre.getClosestRowForLocation(me.getX(), me.getY())) - 3;

		TreePath path = arbre.getPathForLocation(me.getX(), me.getY());

		if (path != null)
		{
			int location = path.getPathCount();
			if (location > 3)
			{
				Scanner sc = new Scanner(path.getLastPathComponent().toString()).useDelimiter(" ");
				String str = sc.next();
				int indice = Integer.parseInt(sc.next());
	
				if ( str.equals("Titre")) 
					new FenetreAjouterTitre(1, getAlS(tp), tp, indice);
				if ( str.equals("Paragraphe"))
					new FenetreAjouterParagraphe(1, getAlS(tp), tp, indice);
			}
			if (location == 3)
			{
				System.out.println("salut je clic !");
				// on selectionne le fichier pour mettre nos modifs
				Generateur.getFenetre().getMenu().activerAjout();
				// PAS BON ! 
				if (parentNodeFichier == null || parentNodeFichier != arbre.getLastSelectedPathComponent())
						Generateur.reinitiliserGenerator(new File(path.getLastPathComponent().toString()));
				parentNodeFichier = arbre.getLastSelectedPathComponent();
			}
			if (location == 2)
			{
				parentNodeProjet = arbre.getLastSelectedPathComponent();
			}
		}
	}
	
	public ArrayList<String> getOrdreElement()
	{
		ArrayList<String> alS = new ArrayList<String>();
		
		// i n'est pas egale a 3
		TreeNode tn = (TreeNode) parentNodeFichier;
		for (int i = 0; i < tn.getChildCount(); i++)
		{
			TreePath tp = arbre.getPathForRow(i+arbre.getLeadSelectionRow()+1);
			alS.add(tp.getLastPathComponent().toString());
		}
		return alS;
	}
	
	public void ajoutFils(String type, String s, String value) 
	{
		DefaultTreeModel dtm = new DefaultTreeModel(racine);
		MutableTreeNode parent  = (MutableTreeNode) ((parentNodeProjet == null) ? dtm.getChild(racine, 0) : parentNodeProjet);
		MutableTreeNode parent2 = (MutableTreeNode) ((parentNodeFichier == null) ? dtm.getChild(parent, 0) : parentNodeFichier);

		
		DefaultMutableTreeNode mtn = new DefaultMutableTreeNode(new File(s));
		if (type.equals("element"))
		{
			dtm.insertNodeInto(mtn, parent2, parent2.getChildCount());
			updateTree(parent2);
			addToAls(value);
		}
		else if (type.equals("fichier"))
		{
			dtm.insertNodeInto(mtn, parent, parent.getChildCount());
			updateTree(parent);
		}
		else if (type.equals("projet"))
		{
			dtm.insertNodeInto(mtn, racine, racine.getChildCount());
			updateTree(racine);
		}
	}
	
	private void updateTree(Object o)
	{
		((DefaultTreeModel) arbre.getModel()).reload((TreeNode) o);
	}
	
	private void addToAls(String s)
	{
		alS.add(s);
	}

	private DefaultMutableTreeNode listFile(File file,DefaultMutableTreeNode node) 
	{
		if (file.isFile())
			return new DefaultMutableTreeNode(file.getName());
		else 
		{
			for (File nom : file.listFiles()) 
			{
				DefaultMutableTreeNode subNode;
				if (nom.isDirectory()) {
					subNode = new DefaultMutableTreeNode(nom.getName() + "\\");
					node.add(this.listFile(nom, subNode));
				} else {
					subNode = new DefaultMutableTreeNode(nom.getName());
				}
				node.add(subNode);
			}
			return node;
		}
	}


	public void setAlS(int indice, String text) 
	{
		alS.remove(indice);
		alS.add(indice,text);
	}
}
