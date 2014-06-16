package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class GrenadeCommand extends AbstractAction {

private Tank playerTank;
	
	public GrenadeCommand(Tank playerTank)
	{
		super("Grenade");
		//this.gwp = gwp;
		this.playerTank = playerTank;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		playerTank.grenade();
	}
	
	
}
