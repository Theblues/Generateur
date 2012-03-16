package ihm.util;

import javax.swing.JOptionPane;

public class OptionPane
{
	JOptionPane jop;
	
	public OptionPane()
	{
		jop = new JOptionPane();
	}
	
	public void optionPaneInfo(String texte)
	{
		jop.showMessageDialog(null, texte, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void optionPaneErreur(String texte)
	{
		jop.showMessageDialog(null, texte, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	public void optionPaneAlerte(String texte)
	{
		jop.showMessageDialog(null, texte, "Attention", JOptionPane.WARNING_MESSAGE);
	}
	
	public int optionPaneConfirmation(String titreFenetre, String texte, int option)
	{
		return jop.showConfirmDialog(null, texte, titreFenetre, option, JOptionPane.QUESTION_MESSAGE);
	}
}