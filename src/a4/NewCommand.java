package a4;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class NewCommand extends AbstractAction {

	public NewCommand()
	{
		super("New");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  Prompted to start a new game");
	}
}
