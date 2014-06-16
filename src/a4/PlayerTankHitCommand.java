package a4;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.AbstractAction;

public class PlayerTankHitCommand extends AbstractAction {

	private GameWorldProxy gw;
	
	public PlayerTankHitCommand(GameWorldProxy gwp)
	{
		super("Player Tank Hit");
		this.gw = gwp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  When player tank is hit");
		System.out.println("\t  A tank is hit");
		int indexOfMissile = 1, missilesCount = 0, indexOfTank = 0, tanksCount = 0;
		ArrayList<Missile> missilesInGame = new ArrayList<Missile>();
		
		Iterator theGameObjects = gw.getGameObjectCollection().iterator();
		
		while(theGameObjects.hasNext())
		{
			if(theGameObjects.next() instanceof Missile) 
			{
				missilesCount++;
				continue;
			}
		}
		System.out.println("\t  Total number of missiles in the game world: " + missilesCount);
		
		if(missilesCount > 0)
		{	
			Random random = new Random();
			int index = random.nextInt(missilesCount);
			if(index == 0) index++;
			theGameObjects = gw.getGameObjectCollection().iterator();
			int i=0;
			while(theGameObjects.hasNext())
			{
				GameObject go = (GameObject)theGameObjects.next();
				if(go instanceof Missile)
				{
					missilesInGame.add((Missile)go);
					if(missilesInGame.size() != index)
						continue;
					else
						indexOfMissile = i;
				}
				i++;
			}
			
			//System.out.println("\t  The number of game objects before removing the missile: " + gw.getGameObjectCollection().size());
			
			//Remove missile from the game
			theGameObjects = gw.getGameObjectCollection().iterator();
			i=0;
			while(theGameObjects.hasNext())
			{
				GameObject go = (GameObject)theGameObjects.next();
				if(i==indexOfMissile)
				{
					Missile selected = (Missile)go;
			
					//Removes the missile from the game board by setting garbage location of (1025,1025) which is outside the board
					selected.kill();
				}
				i++;
			}
			
			//Removes the missile from the game world
			theGameObjects = gw.getGameObjectCollection().iterator();
			i=0;
			while(theGameObjects.hasNext())
			{
				GameObject go = (GameObject)theGameObjects.next();
				if(i==indexOfMissile)
				{
					gw.getGameObjectCollection().remove(go);
					break;
				}
				i++;
			}
			//System.out.println("\t  The number of game objects after removing the missile: " + gw.getGameObjectCollection().size());
		}
		else
			System.out.println("\t  Error: Tank cannot be hit as there are no missiles in the game currently");
		
		//Choose a tank randomly
		ArrayList<Tank> tanksInGame = new ArrayList<Tank>();
		theGameObjects = gw.getGameObjectCollection().iterator();
		int i=0;
		while(theGameObjects.hasNext())
		{
			GameObject go = (GameObject)theGameObjects.next();
			if(go instanceof Tank) 
			{
				tanksCount++;
				continue;
			}
			i++;
		}
		theGameObjects = gw.getGameObjectCollection().iterator();
		Tank selectedTank = new Tank(false, gw);
		if(theGameObjects.hasNext())
		{
			selectedTank = (Tank)theGameObjects.next();
		}
		Random random = new Random();
		int index = random.nextInt(tanksCount);
		if(index == 0)
		{
			indexOfTank = 0;
			theGameObjects = gw.getGameObjectCollection().iterator();
			selectedTank = new Tank(false, gw);
			if(theGameObjects.hasNext())
			{
				selectedTank = (Tank)theGameObjects.next();
			}
			
			System.out.println("\t  The player tank is hit ");
		}
		else
		{
			theGameObjects = gw.getGameObjectCollection().iterator();
			i=0;
			while(theGameObjects.hasNext())
			{
				GameObject go = (GameObject)theGameObjects.next();
				if(go instanceof Tank) 
				{
					tanksInGame.add((Tank)go);
					if(index == tanksInGame.size())
					{
						indexOfTank = i;
						selectedTank = (Tank)go;
						System.out.println("\t  The enemy tank is hit ");
					}
				}
				i++;
			}
		}
		
		if(selectedTank.getArmorStrength() > 0)
		{
			System.out.println("\t  The armor before being hit: " + selectedTank.getArmorStrength());
			selectedTank.decrementArmorStrength();
			System.out.println("\t  The armor after being hit: " + selectedTank.getArmorStrength());
		}
		else if(selectedTank.getArmorStrength() == 0)
		{
			if(selectedTank.getIsEnemy() == false)
			{
				if(gw.getLivesRemaining() > 0)
				{
					System.out.println("\t  The lives for the player tank before it was dead: " + gw.getLivesRemaining());
					System.out.println("\t  The player tank is hit and is dead. So a new player tank is spawned");
					gw.decrementLivesRemaining();
					System.out.println("\t  The lives remaining for the player tank: " + gw.getLivesRemaining());
					
					//The selected tank is killed i.e. removed from the game board by setting garbage coordinates
					selectedTank.kill();
					
					//A new tank is spawned and is replaced in the game world
					selectedTank = new Tank(false, gw);
					gw.getGameObjectCollection().add(selectedTank);
				}
				else
				{
					System.out.println("\n\nGAME OVER");
					System.exit(0);
				}
			}
			else
			{
				//The enemy tank is killed and a new enemy tank is spawned in its place with a random location
				selectedTank.kill();
				gw.getGameObjectCollection().remove(selectedTank);
				selectedTank = new Tank(true, gw);
				gw.getGameObjectCollection().add(selectedTank);
			}		
		}
	}
}
