package a4;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;

public class DecreaseSpeedCommand extends AbstractAction {

private GameWorldProxy gwp;
	
	public DecreaseSpeedCommand(GameWorldProxy gwp)
	{
		super("Decrease Speed");
		this.gwp = gwp;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  The player tank decreased its speed by one unit");
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
			if(s>0)
				playerTank.setSpeed(s-1.0f);
			System.out.println("\t  Decreased speed: " + playerTank.getSpeed());
		}
	}
	
}
