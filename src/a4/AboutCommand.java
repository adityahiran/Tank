package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class AboutCommand extends AbstractAction {

	public AboutCommand()
	{
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null,"Aditya Hiran Pilla","csc 133",1);
	}
}
