package Utilitaire;

import java.awt.*;

public class AffichageImage extends Canvas
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
			getParent().getParent().getParent().getParent().setBounds((largeurEcran - l) / 2, (hauteurEcran - h) / 2, l + 20, h + 60);
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