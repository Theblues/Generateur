package ihm.panel;

import java.awt.*;

public class PanelImage extends Canvas
{
	Dimension	screenSize		= Toolkit.getDefaultToolkit().getScreenSize();
	int			largeurEcran	= screenSize.width;
	int			hauteurEcran	= screenSize.height;
	Image		image;

	public PanelImage(String url)
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
			if (l > largeurEcran || h > hauteurEcran)
			{
				// trouver en essayant de dormir dans le car ;)
				l /= l % largeurEcran;
				h /= h % hauteurEcran;
			}
		}
		
		if ((info & (ALLBITS)) != 0)
		{
			repaint();
			return false;
		}
		else
			return true;
	}
}
