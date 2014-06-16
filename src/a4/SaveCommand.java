package a4;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class SaveCommand extends AbstractAction {

	public SaveCommand()
	{
		super("Save");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  Prompted to save a new game");
	}
	
}
