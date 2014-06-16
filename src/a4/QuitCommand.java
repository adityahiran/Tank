package a4;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

public class QuitCommand extends AbstractAction {

	public QuitCommand()
	{
		super("Quit");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		
		// display the source of the request
		System.out.println ("\t  Quit requested from " + e.getActionCommand() + " " + e.getSource().getClass() );
		
		// verify intent before exiting by displaying a Yes/No dialog
		int result = JOptionPane.showConfirmDialog(null, 								// source of event
													"Are you sure you want to exit ?", 	// display message
													"Confirm Exit", 					// Title bar text
													JOptionPane.YES_NO_OPTION, 			// button choices
													JOptionPane.QUESTION_MESSAGE); 		// prompt icon
		if (result == JOptionPane.YES_OPTION) 
		{
			System.exit(0);
		}
		return; 									// we get here if "No" was chosen
	}
}
