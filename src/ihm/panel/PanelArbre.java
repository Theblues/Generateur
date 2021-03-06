package ihm.panel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import java.io.*;
import java.util.*;

import ihm.menu.MenuContextuel;
import main.*;
import util.*;

public class PanelArbre extends JPanel implements Serializable
{
	private JTree arbre;
	private DefaultMutableTreeNode racine;

	// ...
	private Object parentNodeProjet = null;
	private Object parentNodePage = null;
	private Object parentNodeElement = null;

	private TreePath path = null;
	private String nomNode = null;

	private Page pageSelectionnee;
	private Projet projetSelectionne;

	private int locationRow;

	public PanelArbre(JFrame f)
	{
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(175, 100));

		init();

		if (arbre == null)
		{
			racine = new DefaultMutableTreeNode(new File("projet"));
			arbre = new JTree(racine);
			f.getContentPane().add(new JScrollPane(arbre));
		}
		// on cache la racine
		arbre.setRootVisible(false);

		// ajoute l'action clic a l'arbre
		arbre.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{ // clic droit
				if (me.getButton() == MouseEvent.BUTTON3)
					doMouseRightClick(me);
			}
		});

		JScrollPane editeurScrollHorizontal = new JScrollPane(arbre);
		editeurScrollHorizontal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		editeurScrollHorizontal.setPreferredSize(new Dimension(250, 145));

		JScrollPane editeurScrollVertical = new JScrollPane(arbre);
		editeurScrollVertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		editeurScrollVertical.setPreferredSize(new Dimension(250, 145));

		add(editeurScrollHorizontal);
		add(editeurScrollVertical);

		arbre.addTreeSelectionListener(new TreeSelectionListener()
		{
			public void valueChanged(TreeSelectionEvent e)
			{
				path = arbre.getSelectionPath();
				locationRow = arbre.getRowForPath(path);

				if (path != null)
				{
					Object[] tabObj = path.getPath();
					int location = path.getPathCount();
					/*
					 * Exemple Location : 1 2 3 4 /home site test1.html Titre 1
					 * Paragraphe 1 test2.html Titre 1 Paragraphe 1
					 */
					if (location == 2)
					{
						// on recupere le projet grace a path
						parentNodeProjet = tabObj[1];
						parentNodePage = null;
						parentNodeElement = null;

						// on recupere le projet selectionne
						projetSelectionne = Controleur.metier.getProjet(parentNodeProjet.toString());

						// on modifie le projet selectionne
						Controleur.metier.setProjetSelectionne(projetSelectionne);

						Controleur.creerPanelPropriete(projetSelectionne);

					}
					else if (location >= 3)
					{

						// on recupere le projet et la page grace a path
						parentNodeProjet = tabObj[1];
						parentNodePage = tabObj[2];

						// on recupere le projet et la page selectionnee
						projetSelectionne = Controleur.metier.getProjet(parentNodeProjet.toString());
						pageSelectionnee = projetSelectionne.getPage(parentNodePage.toString());

						// on modifie le projet et la page selectionnee
						Controleur.metier.setProjetSelectionne(projetSelectionne);
						projetSelectionne.setPageSelectionne(pageSelectionnee);

						if (location > 3)
						{
							parentNodeElement = tabObj[3];

							Scanner sc = new Scanner(path.getLastPathComponent().toString()).useDelimiter(" ");
							String str = sc.next();
							int indice = Integer.parseInt(sc.next());

							if (str.equals("Titre"))
							{
								String ancienTitre = pageSelectionnee.getAlTitre().get(indice - 1);
								Controleur.creerPanelAjouterTitre(1,ancienTitre);
							}
							if (str.equals("Paragraphe"))
							{
								String ancienParagraphe = pageSelectionnee.getAlParagrapheHTML().get(indice - 1);
								Controleur.creerPanelAjouterParagraphe(1,ancienParagraphe);
							}
							if (str.equals("Image"))
							{
								String chemin = pageSelectionnee.getAlImage().get(indice - 1);
								Controleur.creerPanelAjouterImage(1, chemin);
							}

						}
						else
						{
							parentNodeElement = null;
							Controleur.creerPanelPropriete(pageSelectionnee);
						}
					}
				}
			}
		});
	}

	private void init()
	{
		try
		{
			FileInputStream fichier = new FileInputStream("temp/arbre.dat");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			arbre = (JTree) ois.readObject();
			racine = (DefaultMutableTreeNode) ois.readObject();
		} 
		catch (IOException ignored)	{} 
		catch (ClassNotFoundException e)	{}
		if (arbre != null && racine != null)
			updateTree(racine);
	}

	/*
	 * Accesseur
	 */
	public JTree getArbre()
	{
		return arbre;
	}

	public DefaultMutableTreeNode getRacine()
	{
		return racine;
	}

	public TreePath getPath()
	{
		return path;
	}

	public String getNomNode()
	{
		return nomNode;
	}

	public Object getParentNodeProjet()
	{
		return parentNodeProjet;
	}

	public Object getParentNodePage()
	{
		return parentNodePage;
	}

	/*
	 * Modificateur
	 */
	public void setPath(TreePath path)
	{
		this.path = path;
	}

	private void doMouseRightClick(MouseEvent me)
	{
		if (path != null)
		{
			int location = path.getPathCount();
			Object[] tabObj = path.getPath();

			new MenuContextuel(me, location, tabObj);
		}
	}

	public boolean ajoutFils(Object node, String type, String nom)
	{
		DefaultTreeModel dtm = new DefaultTreeModel(racine);
		DefaultMutableTreeNode mtn = new DefaultMutableTreeNode(nom);
		if (type.equals("projet"))
		{
			// on insert un noeud a la racine
			dtm.insertNodeInto(mtn, racine, racine.getChildCount());
			// on rafraichis l'arbre
			updateTree(racine);
			return true;
		}

		if (type.equals("fichier"))
		{
			// on recupere le noeud ou inserer le nouveau noeud
			MutableTreeNode parent = (MutableTreeNode) ((node == null) ? parentNodeProjet
					: node);
			// on insert au noeud
			dtm.insertNodeInto(mtn, parent, parent.getChildCount());
			// on rafraichis l'arbre
			updateTree(parent);
			return true;
		}

		if (type.equals("element"))
		{
			MutableTreeNode parent = (MutableTreeNode) ((node == null) ? parentNodePage
					: node);
			dtm.insertNodeInto(mtn, parent, parent.getChildCount());
			updateTree(parent);
			return true;
		}
		return false;
	}

	public boolean monterNode(String type)
	{
		TreePath tpath = null;
		if (type.equals("page"))
		{
			Object[] tabOjt = path.getPath();

			// on recupere le projet et la page
			Projet projet = Controleur.metier.getProjet(tabOjt[1].toString());
			Page page = projet.getPage(tabOjt[2].toString());

			// on recupere l'indice de la page
			int indice = projet.getIndicePage(page);
			// on recupere le nombre d'element de la page du dessus
			int nbElement = projet.getAlPage().get(indice - 1).getAlOrdre()
					.size();
			// on recupere la ligne de la page du dessus
			int locationDessus = locationRow - nbElement - 1;

			// on verifie que le noeud n'est pas deploye avec de selectionne le
			// path
			if (arbre.isExpanded(locationDessus))
				tpath = arbre.getPathForRow(locationDessus);
			else
				tpath = arbre.getPathForRow(locationRow - 1);
		}
		else if (type.equals("element"))
			tpath = arbre.getPathForRow(locationRow - 1);

		if (tpath != null)
		{
			// si le type est un element et que le path est superieur a 3
			if (type.equals("element") && tpath.getPath().length > 3)
			{
				if (modifierNoeudElement(tpath))
					return true;
			}
			else if (type.equals("page"))
				if (modifierNoeudPage(tpath))
					return true;
		}
		Controleur.creerOptionPane("error", "Impossible de monter la selection");
		return false;
	}

	public boolean descendreNode(String type)
	{
		TreePath tpath = null;
		if (type.equals("page"))
		{
			Object[] tabOjt = path.getPath();

			// on recupere le projet et la page
			Projet projet = Controleur.metier.getProjet(tabOjt[1].toString());
			Page page = projet.getPage(tabOjt[2].toString());

			// on recupere le nombre d'element de la page
			int nbElement = page.getAlOrdre().size();
			// on recupere la ligne de la page du dessus
			int locationDessous = locationRow + nbElement + 1;

			// on verifie que le noeud n'est pas deploye avec de selectionne le
			// path
			if (arbre.isExpanded(locationRow))
				tpath = arbre.getPathForRow(locationDessous);
			else
				tpath = arbre.getPathForRow(locationRow + 1);
		}
		else if (type.equals("element"))
			tpath = arbre.getPathForRow(locationRow + 1);

		if (tpath != null)
		{
			// si le type est un element et que le path est superieur a 3
			if (type.equals("element") && tpath.getPath().length > 3)
			{
				if (modifierNoeudElement(tpath))
					return true;
			}
			else if (type.equals("page"))
				if (modifierNoeudPage(tpath))
					return true;
		}

		Controleur.creerOptionPane("error",	"Impossible de descendre la selection");
		return false;
	}

	private boolean modifierNoeudElement(TreePath path)
	{
		Object[] tabObj = path.getPath();
		if (tabObj.length < 4)
			return false;

		// on selectionne le nom du noeud
		String nom1 = tabObj[3].toString();
		// on recupere le nom du noeud que l'on veut bouger
		String nom2 = parentNodeElement.toString();

		// permet a la liste de savoir quel element on bouge
		nomNode = nom2;

		DefaultMutableTreeNode noeud = (DefaultMutableTreeNode) parentNodeElement;
		// on modifie notre nom
		noeud.setUserObject(nom1);

		noeud = (DefaultMutableTreeNode) tabObj[3];
		// on modifie l'autre nom
		noeud.setUserObject(nom2);

		// on rafraichis l'arbre
		updateTree(parentNodePage);
		return true;
	}

	private boolean modifierNoeudPage(TreePath path)
	{
		Object[] tabObj = path.getPath();

		if (tabObj.length < 3)
			return false;

		// on recupere le nom du noeud que l'on veut bouger
		String nom1 = parentNodePage.toString();
		// on selectionne le nom du noeud
		String nom2 = tabObj[2].toString();

		modifElementPage(parentNodePage, tabObj[2]);

		// permet a la liste de savoir quel element on bouge
		nomNode = nom1;

		DefaultMutableTreeNode noeud = (DefaultMutableTreeNode) parentNodePage;
		// on modifie notre nom
		noeud.setUserObject(nom2);

		noeud = (DefaultMutableTreeNode) tabObj[2];
		// on modifie l'autre nom
		noeud.setUserObject(nom1);

		// on rafraichis l'arbre
		updateTree(parentNodeProjet);
		return true;
	}

	private void modifElementPage(Object oPage1, Object oPage2)
	{
		// on supprime tous les enfants des noeuds
		((DefaultMutableTreeNode) oPage1).removeAllChildren();
		((DefaultMutableTreeNode) oPage2).removeAllChildren();

		// on recupere les 2 pages
		Page page1 = Controleur.metier.getProjetSelectionne().getPage(oPage1.toString());
		Page page2 = Controleur.metier.getProjetSelectionne().getPage(oPage2.toString());

		// on ajoute les elements de la 1ere page, au 2e noeud
		for (String s : page1.getAlOrdre())
			ajoutFils(oPage2, "element", s);
		// on ajoute les elements de la 2e page, au 1er noeud
		for (String s : page2.getAlOrdre())
			ajoutFils(oPage1, "element", s);

	}

	public void renommerNoeud(String type, String nom)
	{
		// on recupere le noeud
		DefaultMutableTreeNode noeud = null;
		if (type.equals("projet"))
			noeud = (DefaultMutableTreeNode) parentNodeProjet;
		else if (type.equals("page"))
			noeud = (DefaultMutableTreeNode) parentNodePage;
		// on modifie le noeud par le nouveau nom
		noeud.setUserObject(nom);
		// on rafraichis l'arbre
		updateTree(parentNodeProjet);
	}

	private void updateTree(Object o)
	{
		((DefaultTreeModel) arbre.getModel()).reload((TreeNode) o);
	}

	public void enregistrerArbre()
	{
		try
		{
			FileOutputStream fichier = new FileOutputStream("temp/arbre.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(arbre);
			oos.writeObject(racine);
			oos.flush();
			oos.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void supprimerNoeud(Object object, int indice)
	{
		((DefaultMutableTreeNode) object).remove(indice);
		updateTree(object);
		path = null;
	}
}