package a4;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;

public class LeftTurnCommand extends AbstractAction {

	private Tank playerTank;
	
	public LeftTurnCommand(Tank playerTank)
	{
		super("Left Turn");
		//this.gwp = gwp;
		this.playerTank = playerTank;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println("\n");
		//System.out.println("\t  The player tank took left turn");
		//Tank playerTank = new Tank(false, gwp);
		
		//Iterator theGameObjects = gwp.getGameObjectCollection().iterator();
		
		//if(theGameObjects.hasNext())
		//{
			//playerTank = (Tank)theGameObjects.next();
		//}
		
		int d = playerTank.getDirection();
		//System.out.println("\t  Originally the direction of the player tank was: " + d);
		
		ISteerable iPlayerTank = playerTank;
		iPlayerTank.changeDirection((d-5)%360);
		//playerTank.movePlayerTank();
		//System.out.println("\t  Direction of the player tank after right turn is: " + playerTank.getDirection());
	}
}
