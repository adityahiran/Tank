package a4;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.AbstractAction;

public class EnemyFireCommand extends AbstractAction {
	
	private GameWorldProxy gwp;
	
	public EnemyFireCommand(GameWorldProxy gwp)
	{
		super("Enemy Fire");
		this.gwp = gwp;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  When enemy fires");
		System.out.println("\t  A random enemy tank fired a missile");
		ArrayList<Tank> enemyTanks = new ArrayList<Tank>();
		
		//Collect only enemy tanks. Since the first element of the zero based collection is the playerTank, exclude that.
		Iterator theGameObjects = gwp.getGameObjectCollection().iterator();
		while(theGameObjects.hasNext())
		{
			GameObject gameObject = (GameObject)theGameObjects.next(); 
			if(gameObject instanceof Tank && ((Tank) gameObject).getIsEnemy()==true)
				enemyTanks.add((Tank)gameObject);
		}
		
		Random random = new Random();
		int index = random.nextInt(enemyTanks.size());
		Tank selectedEnemyTank = enemyTanks.get(index);
		
		if(selectedEnemyTank.getMissileCount()>0)
		{
			System.out.println("\n");
			System.out.println("\t  The enemy tank has missiles to fire");
			System.out.println("\t  Originally the number of missiles in the enemy tank: " + selectedEnemyTank.getMissileCount());
			selectedEnemyTank.fire();
			System.out.println("\t  After firing the number of missiles in the enemy tank: " + selectedEnemyTank.getMissileCount());
			
			//The fired missile has the same location, the same direction and a speed of 5 units greater than the tank that fired it 
			Missile missileFired = new Missile(selectedEnemyTank.getLocation(), selectedEnemyTank.getDirection(), selectedEnemyTank.getSpeed()+5.0f, gwp);
			gwp.addGameObjects(missileFired);
			System.out.println("\t  The fired missile is added to the game world");
		}
		else
			System.out.println("\t  Error: The enemy tank has no missiles to fire.");
	}
}
