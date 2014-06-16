package a4;

import java.io.File;
import java.util.*;

public class GameWorld implements IObservable {
	
	private boolean sound;
	private int length;
	private int width;
	private Point origin;
	private int livesRemaining;
	private long currentScore;
	private long elapsedTime;
	GameWorldProxy gwp;
	GameObjectCollection gameObjectCollection;
	ObserversCollection observersCollection;
	TickCommand tickCommand;
	private boolean isPlayMode = false;
	private static String soundDir = "sounds" + File.separator;
	private static String soundFile = "background.wav";
	private static String soundPath = soundDir+soundFile;
	private static Sound gameSound = new Sound(soundPath);
		
	public GameWorld(int noOfEnemyTanks, int noOfRocks, int noOfTrees)
	{
		this.sound = false;
		this.livesRemaining = 5;
		this.currentScore = 0;
		this.elapsedTime = 0;
		this.length = 800;
		this.width = 1000;
				
		Point p = new Point(0.0f,0.0f);
		origin = p;
		gwp = new GameWorldProxy(this);
		this.tickCommand = new TickCommand(gwp);
		
		gameObjectCollection = new GameObjectCollection(gwp);
		observersCollection = new ObserversCollection();
		
		constructPlayerTank();
		constructRocks(noOfRocks);
		constructTrees(noOfTrees);
		constructEnemyTanks(noOfEnemyTanks);			
	}

		
	@Override
	public void addObserver(IObserver observer)
	{
		observersCollection.add(observer);
	}
	
	@Override
	public void notifyObservers()
	{
		IObservable proxy = new GameWorldProxy(this);
		Iterator<IObserver> iterator = this.observersCollection.iterator();
		
		while(iterator.hasNext())
		{
			IObserver observer = (IObserver)iterator.next();
			observer.update((IObservable)proxy, null);
		}
	}
	
	public Tank getPlayerTank()
	{
		return this.gameObjectCollection.getPlayerTank();
	}
	
	public void setIsPlayMode(boolean b)
	{
		this.isPlayMode = b;
	}
	
	public boolean getIsPlayMode()
	{
		return this.isPlayMode;
	}
	
	public TickCommand getTickCommand()
	{
		return this.tickCommand;
	}
	
	public boolean getSound()
	{
		return this.sound;
	}
	
	public void setSound(boolean b)
	{
		this.sound = b;
		if(this.sound == true)
		{
			gameSound.loop();
		}
		else
		{
			gameSound.stop();
		}
		//System.out.println("\t  sound has been set to: "+this.sound);
		notifyObservers();
	}
	
	public int getLength()
	{
		return this.length;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public Point getOrigin()
	{
		return this.origin;
	}
	
	public long getElapsedTime()
	{
		return this.elapsedTime;
	}
	
	public long getCurrentScore()
	{
		return this.currentScore;
	}
	
	public GameObjectCollection getGameObjectCollection()
	{
		return this.gameObjectCollection;
	}
	
	public int getLivesRemaining()
	{
		return this.livesRemaining;
	}
	
	public void addGameObjects(GameObject go)
	{
		gameObjectCollection.add(go);
		notifyObservers();
	}
	
	public void garbageCollect()
	{
		Iterator iter = gameObjectCollection.iterator();
		GameObjectCollection temp = new GameObjectCollection(gwp);
		while(iter.hasNext())
		{
			GameObject go = (GameObject)iter.next();
			if(!go.getIsGarbageCollectible())
				temp.add(go);
		}
		gameObjectCollection = temp;
	}
	
	public int getNumberOfGameObjects()
	{
		return this.gameObjectCollection.size();
	}
	
	public void decrementLivesRemaining()
	{
		this.livesRemaining--;
		notifyObservers();
	}
	
	public void incrementElapsedTime()
	{
		this.elapsedTime++;
		notifyObservers();
	}
	
	private void constructRocks(int noOfRocks)
	{
		Random random = new Random();
		
		//Construct the required no. of rocks
		for(int i=0; i<noOfRocks; i++)
		{
			//Generating random numbers from 0 to 19 and adding 1 so make it 1 to 20
			int h = random.nextInt(15) + 25;
			int w = random.nextInt(15) + 25;
			Rock rock = new Rock(h, w);
			
			//Add the constructed rock to the collection of game objects
			gameObjectCollection.add(rock);
		}
	}
	
	private void constructTrees(int noOfTrees)
	{
		Random random = new Random();
		
		//Construct the required no. of rocks
		for(int i=0; i<noOfTrees; i++)
		{
			//Generating random numbers from 0 to 19 and adding 1 so make it 1 to 20
			int d = random.nextInt(30) + 30;
			
			Tree tree = new Tree(d);
			
			//Add the constructed tree to the collection of game objects
			gameObjectCollection.add(tree);
		}
	}
	
	private void constructEnemyTanks(int noOfEnemyTanks)
	{
		//Construct the required no. of rocks
		for(int i=0; i<noOfEnemyTanks; i++)
		{
			Tank tank = new Tank(true, gwp);
			
			//Add the constructed enemy tank to the collection of game objects
			gameObjectCollection.add(tank);
			this.tickCommand.addTickObserver(tank);
		}
	}
	
	private void constructPlayerTank()
	{
		Tank playerTank = new Tank(false, gwp);
		gameObjectCollection.add(playerTank);
		this.tickCommand.addTickObserver(playerTank);	
	}

	
	public void detectAndHandleCollision() {
		// TODO Auto-generated method stub
		Iterator iter = gameObjectCollection.iterator();
		while(iter.hasNext())
		{
			ICollider collidingObj = (ICollider)iter.next();
			
			Iterator iter2 = gameObjectCollection.iterator();
			while(iter2.hasNext())
			{
				ICollider otherCollidingObj = (ICollider)iter2.next();
				if(collidingObj != otherCollidingObj)
				{
					//Check for collision
					if(collidingObj.collidesWith(otherCollidingObj))
					{
						collidingObj.handleCollision(otherCollidingObj);
					}
				}
			}
		}
	}


	public void addPlayerTank(Tank newPlayerTank) {
		// TODO Auto-generated method stub
		gameObjectCollection.addPlayerTank(newPlayerTank);
	}


	public void setElapsedTime(long time) {
		// TODO Auto-generated method stub
		this.elapsedTime = time;
	}
}
