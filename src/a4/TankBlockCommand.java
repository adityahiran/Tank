package a4;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.swing.AbstractAction;

public class TankBlockCommand extends AbstractAction {

	private GameWorldProxy gw;
	
	public TankBlockCommand(GameWorldProxy gwp)
	{
		super("Tank Block");
		this.gw = gwp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  When the tank got blocked");
		System.out.println("\t  The player tank has collided with the landscape");
		int tanksCount=0;
		int indexOfTank=0;
		Iterator theGameObjects = gw.getGameObjectCollection().iterator();
		
		while(theGameObjects.hasNext())
		{
			GameObject go = (GameObject)theGameObjects.next();
			if(go instanceof Tank)
				tanksCount++;
		}
		Random random = new Random();
		int i=0;
		int index = random.nextInt(tanksCount);
		tanksCount=0;
		theGameObjects = gw.getGameObjectCollection().iterator();
		while(theGameObjects.hasNext())
		{
			GameObject go = (GameObject)theGameObjects.next();
			if(go instanceof Tank)
				if(tanksCount==index)
					indexOfTank=i;
			i++;
		}
		Tank selected = null;
		theGameObjects = gw.getGameObjectCollection().iterator();
		i=0;
		while(theGameObjects.hasNext())
		{
			GameObject go = (GameObject)theGameObjects.next();
			if(i==indexOfTank)
			{
				selected = (Tank)go;
				selected.setSpeed(0.0f);
				selected.setIsBlocked(true);
				break;
			}
			i++;
		}
		
		System.out.println("\t  The player tank is blocked due to a collision with a landscape item");
		System.out.println("\t  Enter <- or -> to change direction for the player tank to resume its movement");
		
	}
}
