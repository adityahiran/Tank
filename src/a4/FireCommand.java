package a4;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;

public class FireCommand extends AbstractAction {

	//private GameWorldProxy gwp;
	private Tank playerTank;
	
	public FireCommand(Tank playerTank)
	{
		super("Fire");
		//this.gwp = gwp;
		this.playerTank = playerTank;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		playerTank.fire();
		/*
		//System.out.println("\n");
		System.out.println("\t  The player tank fired a missile");
		Tank playerTank = new Tank(false, gwp);
	
		Iterator theGameObjects = gwp.getGameObjectCollection().iterator();
	
		if(theGameObjects.hasNext())
		{
			playerTank = (Tank)theGameObjects.next();
		}
		
		//Two levels of validation provided. One at the controller level and the other at the model level (in terms of the MVC architecture)
		if(playerTank.getMissileCount()>0)
		{
			System.out.println("\t  The player tank has missiles to fire");
			System.out.println("\t  Originally the number of missiles in the tank: " + playerTank.getMissileCount());
			playerTank.fire();
			System.out.println("\t  After firing the number of missiles in the tank: " + playerTank.getMissileCount());
			Missile missileFired = new Missile(playerTank.getLocation(), playerTank.getDirection(), playerTank.getSpeed()+5.0f, gwp);
			gwp.addGameObjects(missileFired);
			//System.out.println("\t  The fired missile is added to the game world");
		}
		else
			System.out.println("\t  ERROR: The tank has no missiles to fire.");
		*/
	}
}
