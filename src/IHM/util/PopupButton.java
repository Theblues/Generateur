package IHM.util;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public abstract class PopupButton extends JButton implements ActionListener
{
	private JPopupMenu popup;

	protected PopupButton(String[] items, Container parent)
	{
		this("", items, parent);
	}

	protected PopupButton(String label, String[] items, Container parent)
	{
		super(label);

		popup = new JPopupMenu();

		JMenuItem menuItems[] = new JMenuItem[items.length];

		for (int i = 0; i < items.length; i++)
		{
			menuItems[i] = new JMenuItem(items[i]);
			menuItems[i].addActionListener(this);
			popup.add(menuItems[i]);
		}

		parent.add(popup);

		addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				popup.show(PopupButton.this, 0,
						PopupButton.this.getSize().height);
			}
		});
	}
}
