package a4;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;

public class RightTurnCommand extends AbstractAction {

	//private GameWorldProxy gwp;
	private Tank playerTank;
	public RightTurnCommand(Tank playerTank)
	{
		super("Right Turn");
		this.playerTank = playerTank;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println("\n");
		//System.out.println("\t  The player tank took right turn");
		
		//Tank playerTank = new Tank(false, gwp);
		//Iterator theGameObjects = gwp.getGameObjectCollection().iterator();
		
		//Get only the first element of the collection. This will be the player tank.
		//if(theGameObjects.hasNext())
		//{
			//playerTank = (Tank)theGameObjects.next();
		//}
		
		int d = playerTank.getDirection();
		//System.out.println("\t  Originally the direction of the player tank was: " + d);
		
		ISteerable iPlayerTank = playerTank;
		iPlayerTank.changeDirection((d+5)%360);
		//System.out.println("\t  Direction of the player tank after right turn is: " + playerTank.getDirection());
	}
}
