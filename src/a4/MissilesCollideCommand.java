package a4;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Random;

import javax.swing.AbstractAction;

public class MissilesCollideCommand extends AbstractAction {

	private GameWorldProxy gwp;
	
	public MissilesCollideCommand(GameWorldProxy gwp)
	{
		super("Missiles Collide");
		this.gwp = gwp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  When the missiles collide");
		System.out.println("\t  Collision occured between two missiles and they have to be removed from the game");
		int missileCount=0;
		Iterator theGameObjects = gwp.getGameObjectCollection().iterator();
		int i=0;
		while(theGameObjects.hasNext())
		{
			GameObject go = (GameObject)theGameObjects.next();
			if(go instanceof Missile)
				missileCount++;
			i++;
		}
		if(missileCount<2)
			System.out.println("\t  Error: There are not more than one missile in the game to collide");
		else
		{
			Random random = new Random();
			int missileOneIndex = random.nextInt(missileCount);
			if(missileOneIndex==0) missileOneIndex++;
			
			missileCount = 0;
			theGameObjects = gwp.getGameObjectCollection().iterator();
			i=0;
			while(theGameObjects.hasNext())
			{
				GameObject go = (GameObject)theGameObjects.next();
				if(go instanceof Missile)
				{
					missileCount++;
					if(missileCount == missileOneIndex)
					{
						System.out.println("\t  The first missile is removed from the game");
						Missile selected = (Missile)go;
						selected.kill();
						gwp.getGameObjectCollection().remove(selected);
					}
				}
				i++;
			}
			int missileTwoIndex = random.nextInt(missileCount);
			if(missileTwoIndex==0) missileTwoIndex++;
			missileCount=0;
			theGameObjects = gwp.getGameObjectCollection().iterator();
			i=0;
			while(theGameObjects.hasNext())
			{
				GameObject go = (GameObject)theGameObjects.next();
				if(go instanceof Missile)
				{
					missileCount++;
					if(missileCount == missileTwoIndex)
					{
						Missile selected = (Missile)go;
						selected.kill();
						gwp.getGameObjectCollection().remove(selected);
						System.out.println("\t  The second missile is removed from the game");
					}
				}
				i++;
			}
			
		}
	}
	
}
