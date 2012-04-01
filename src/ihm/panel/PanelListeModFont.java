package ihm.panel;

import ihm.frame.FenetreLien;
import ihm.panel.ajout.PanelAjouterParagraphe;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;

public class PanelListeModFont extends JPanel implements ActionListener
{
	private JButton boutonGras;
	private JButton boutonItalique;
	private JButton boutonLien;
	private PanelAjouterParagraphe panAjoutParagraphe;

	public PanelListeModFont(PanelAjouterParagraphe panAjoutParagraphe) 
	{		
		JPanel p = new JPanel();
		this.panAjoutParagraphe = panAjoutParagraphe;

		Action boldAction = new StyledEditorKit.BoldAction();
		
		boutonGras = new JButton();
		boutonGras.setBorder(null);
		boutonGras.setPreferredSize(new Dimension(35, 35));
		boutonGras.setIcon(new ImageIcon("images/gras.jpg"));
		boutonGras.setToolTipText("Gras");
		boutonGras.addActionListener(boldAction);
		
		Action italicAction = new StyledEditorKit.ItalicAction();
		boutonItalique = new JButton();
		boutonItalique.setBorder(null);
		boutonItalique.setPreferredSize(new Dimension(35, 35));
		boutonItalique.setIcon(new ImageIcon("images/italique.jpg"));
		boutonItalique.setToolTipText("Italique");
		boutonItalique.addActionListener(italicAction);

		boutonLien = new JButton();
		boutonLien.setBorder(null);
		boutonLien.setPreferredSize(new Dimension(35, 35));
		boutonLien.setIcon(new ImageIcon("images/lien.jpg"));
		boutonLien.setToolTipText("Hyperlien");
		boutonLien.addActionListener(this);

		p.add(boutonGras);
		p.add(boutonItalique);
		p.add(boutonLien);

		add(p);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		new FenetreLien(panAjoutParagraphe);
	}
}