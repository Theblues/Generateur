package IHM.OptionPane;

import javax.swing.JOptionPane;

public class OptionPane
{
	JOptionPane jop1;
	
	public OptionPane()
	{
		jop1 = new JOptionPane();
	}
	
	public void optionPaneInfo(String texte)
	{
		jop1.showMessageDialog(null, texte, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void optionPaneErreur(String texte)
	{
		jop1.showMessageDialog(null, texte, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	public void optionPaneAlerte(String texte)
	{
		jop1.showMessageDialog(null, texte, "Attention", JOptionPane.WARNING_MESSAGE);
	}
}
