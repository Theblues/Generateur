package Fenetre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;

import javax.swing.*;
import javax.swing.tree.*;

public class PanelArbre extends JPanel 
{
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	private JScrollPane editeurScrollHorizontal;
	private JScrollPane editeurScrollVertical;
	
	public PanelArbre(JFrame f) {
		
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

	private void listRoot(JFrame f) 
	{
		this.racine = new DefaultMutableTreeNode();
		File file = new File("site/");

		DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getPath());
		try {
			for (File nom : file.listFiles()) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName() + "\\");
				lecteur.add(this.listFile(nom, node));
			}
		} catch (NullPointerException e) {
		}

		this.racine.add(lecteur);

		arbre = new JTree(this.racine);

		f.getContentPane().add(new JScrollPane(arbre));
	}

	private DefaultMutableTreeNode listFile(File file,
			DefaultMutableTreeNode node) {
		int count = 0;

		if (file.isFile())
			return new DefaultMutableTreeNode(file.getName());
		else {
			for (File nom : file.listFiles()) {
				count++;
				// pas plus de 5 enfants par noeud
				if (count < 5) {
					DefaultMutableTreeNode subNode;
					if (nom.isDirectory()) {
						subNode = new DefaultMutableTreeNode(nom.getName()
								+ "\\");
						node.add(this.listFile(nom, subNode));
					} else {
						subNode = new DefaultMutableTreeNode(nom.getName());
					}
					node.add(subNode);
				}
			}
			return node;
		}
	}
}
