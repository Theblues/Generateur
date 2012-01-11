package IHM.Frame;

import java.awt.*;

import javax.swing.*;

public class FenetreAjouterImage extends JFrame
{
	private JButton	valider;
	private JButton	annuler;
	private JButton	changImage;

	public FenetreAjouterImage(String chemin, String nom)
	{
		setTitle("Ajouter Image");
		AffichageImage image = new AffichageImage(chemin);

		add(image);

		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(1, 3));

		annuler = new JButton("Annuler");
		pan.add(annuler);
		changImage = new JButton("Changer l'image");
		pan.add(changImage);
		valider = new JButton("Valider");
		pan.add(valider);

		add(pan, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
}

class AffichageImage extends Canvas
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
			getParent()
					.getParent()
					.getParent()
					.getParent()
					.setBounds((largeurEcran - l) / 2, (hauteurEcran - h) / 2,
							l + 8, h + 32);
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
