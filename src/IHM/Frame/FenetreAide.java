package IHM.Frame;

import javax.swing.*;

/*
 * Composition Fenetre
 * 
 *    ______________________________
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |	liste	|	  Content		|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |			|					|
 *   |__________|___________________|
 * 
 */
public class FenetreAide extends JFrame
{
	public FenetreAide()
	{
		setTitle("Aide");
		setSize(500, 500);
		setLocation(250, 250);
		
		
		setVisible(true);
	}
}
