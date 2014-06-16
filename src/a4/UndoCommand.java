package a4;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class UndoCommand extends AbstractAction {

	public UndoCommand()
	{
		super("Undo");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  Prompted for a command undo");
	}

	
}
