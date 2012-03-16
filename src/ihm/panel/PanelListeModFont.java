package ihm.panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;

public class PanelListeModFont extends JPanel
{
	private JButton boutonGras;
	private JButton boutonItalique;
	private JButton boutonLien;

	public PanelListeModFont() 
	{		
		JPanel p = new JPanel();

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

		Action underlineAction = new StyledEditorKit.UnderlineAction();
		Action fontAction = new StyledEditorKit.ForegroundAction("blue", Color.BLUE);
		boutonLien = new JButton();
		boutonLien.setBorder(null);
		boutonLien.setPreferredSize(new Dimension(35, 35));
		boutonLien.setIcon(new ImageIcon("images/lien.jpg"));
		boutonLien.setToolTipText("Hyperlien");
		boutonLien.addActionListener(underlineAction);
		boutonLien.addActionListener(fontAction);

		p.add(boutonGras);
		p.add(boutonItalique);
		p.add(boutonLien);

		add(p);
	}
}