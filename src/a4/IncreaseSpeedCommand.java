package a4;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;

public class IncreaseSpeedCommand extends AbstractAction {

private GameWorldProxy gwp;
	
	public IncreaseSpeedCommand(GameWorldProxy gwp)
	{
		super("Increase Speed");
		this.gwp = gwp;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  The player tank increased its speed by one unit");
		Tank playerTank = new Tank(false, gwp);
		
		Iterator theGameObjects = gwp.getGameObjectCollection().iterator();
		
		if(theGameObjects.hasNext())
		{
			playerTank = (Tank)theGameObjects.next();
		}
		
		if(!playerTank.isBlocked())
		{
			float s = playerTank.getSpeed();
			System.out.println("\t  Original speed: " + s);
			playerTank.setSpeed(s+1.0f);
			System.out.println("\t  Increased speed: " + playerTank.getSpeed());
		}
	}
	
}
