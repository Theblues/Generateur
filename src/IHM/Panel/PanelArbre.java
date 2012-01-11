package IHM.Panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.tree.*;

import IHM.Frame.FenetreAjouterParagraphe;
import IHM.Frame.FenetreAjouterTitre;

public class PanelArbre extends JPanel 
{
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	private JScrollPane editeurScrollHorizontal;
	private JScrollPane editeurScrollVertical;
	private ArrayList<String> alS;

	private static int cpt;
	
	public PanelArbre(JFrame f) 
	{
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(150, 100));
		
		alS = new ArrayList<String>();
		cpt=0;
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
	
	
	public String getAlS( int ind ) {
		return alS.get(ind);
	}

	private void listRoot(JFrame f) 
	{
		racine = new DefaultMutableTreeNode();
		File file = new File("site/");

		DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getPath());
		try {
			for (File nom : file.listFiles()) {
				if (!nom.getName().equals("content"))
				{
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName() + "\\");
					lecteur.add(this.listFile(nom, node));
				}
			}
		} catch (NullPointerException e) {
		}

		racine.add(lecteur);

		arbre = new JTree(racine);
		
		f.getContentPane().add(new JScrollPane(arbre));
		
		arbre.addMouseListener(new MouseAdapter() {
		      public void mouseClicked(MouseEvent me) {
		        doMouseClicked(me);
		      }
		    });
	}
	
	
	void doMouseClicked(MouseEvent me) {

		// 3 correspond au nombre de parents depuis le dossier site
		int tp = (arbre.getClosestRowForLocation(me.getX(), me.getY())) - 3;

		TreePath path = arbre.getPathForLocation(me.getX(), me.getY());
		
		if (path != null) 
		{
			if (tp>= 0)
			{
				Scanner sc = new Scanner(path.getLastPathComponent().toString()).useDelimiter(" ");
				String str = sc.next();
				int indice = Integer.parseInt(sc.next());
	
				if ( str.equals("Titre")) {
					new FenetreAjouterTitre(1, getAlS(tp), tp, indice);
					
				}
				if ( str.equals("Paragraphe")) {
					new FenetreAjouterParagraphe(1, getAlS(tp), tp, indice);
				}
			}
		}
		getOrdreElement();
	}
	
	public ArrayList<String> getOrdreElement()
	{
		ArrayList<String> alS = new ArrayList<String>();
		
		// On recupere le nombre de noeud du fichier
		int nbNoeud = arbre.getRowCount();
		
		for (int i = 3; i < nbNoeud; i++)
		{
			TreePath tp = arbre.getPathForRow(i);
			alS.add(tp.getLastPathComponent().toString());
		}
		
		return alS;
	}
	
	public void ajoutFils(String s, String value) {
		DefaultTreeModel dtm = new DefaultTreeModel(racine);
		Object parent = dtm.getChild(racine,0);
		Object parent2 = dtm.getChild(parent,0);

		DefaultMutableTreeNode mtn = new DefaultMutableTreeNode(new File(s));
		dtm.insertNodeInto(mtn,(MutableTreeNode) parent2,cpt);
		cpt++;
		updateTree(parent2);
		addToAls(value);
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


	public void setAlS(int indice, String text) {
		alS.remove(indice);
		alS.add(indice,text);
	}
}
