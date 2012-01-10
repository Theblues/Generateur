package Fenetre;

import java.io.*;

import javax.swing.*;
import javax.swing.tree.*;

public class PanelArbre extends JPanel 
{
	private JTree arbre;
	private DefaultMutableTreeNode racine;

	public PanelArbre(JFrame f) {
		listRoot(f);
		add(arbre);
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
