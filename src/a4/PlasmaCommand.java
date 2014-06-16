package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class PlasmaCommand extends AbstractAction {
	
private Tank playerTank;
	
	public PlasmaCommand(Tank playerTank)
	{
		super("Plasma");
		this.playerTank = playerTank;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		playerTank.firePlasma();
	}

}
