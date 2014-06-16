package a4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Tank extends Vehicle  {

	private int armorStrength;
	private int missileCount;
	private int grenadeCount;
	private int plasmaCount;
	private boolean isEnemy;			//Set to true for enemy tanks, otherwise set to false
	private boolean isBlocked = false;
	private IStrategy currentStrategy;
	private GameWorldProxy gwp;
	private TankProxy tp = new TankProxy(this);
	private long currentTick;
	private int imgSize = 40; 			//size = one side of the tank
	private int countEdgeHits = 1;
	private int incx=1,incy=1;
	private Point spawningLocation;
	private static String soundDir ="sounds" + File.separator; 
	private static String soundFile = "tankCollides.wav";
	private static String soundPath = soundDir+soundFile;
	private static Sound tankCollidingSound= new Sound(soundPath);
	private AffineTransform goTranslation, goRotation, goScale;
	
	public Tank(boolean isEnemy, GameWorldProxy gwp)
	{
		super();
		this.plasmaCount = 3;
		this.grenadeCount=5;
		this.armorStrength = 150;
		this.missileCount = 500;
		this.isEnemy = isEnemy;
		this.gwp = gwp;
		setCurrentStrategy();
		this.spawningLocation = this.getLocation();
		goTranslation = new AffineTransform();
		goRotation = new AffineTransform();
		goScale = new AffineTransform();
	}
	
	public void setCurrentStrategy()
	{
		Random random = new Random();
		int randomStrategyPicker = 0;//random.nextInt(2);
		if(randomStrategyPicker == 0)
		{
			currentStrategy = new EveryTickFireStrategy(tp);
		}
		else
		{
			currentStrategy = new EveryTwoTicksFireStrategy(tp, gwp);
		}
	}
	
	@Override
	public void update(ITickObservable observable, Object args, long tick)
	{
		move(tick);
		//if(isEnemy == false)
		//fire();
		this.currentTick = tick;
	}
	
	public boolean getIsEnemy()
	{
		return this.isEnemy;
	}
	
	public int getArmorStrength()
	{
		return this.armorStrength;
	}
	
	public int getMissileCount()
	{
		return this.missileCount;
	}
	
	public void changeDirection(int d)
	{
		super.checkSteerableSetDirection(d);
	}
	
	public void decrementArmorStrength()
	{
		this.armorStrength--;
	}
	
	public void decrementMissileCount()
	{
		this.missileCount--;
	}
	
	public void setIncx()
	{
		this.incx = -this.incx;
	}
	
	public boolean isBlocked()
	{
		if(((this.getDirection()==0||this.getDirection()==360) && this.getLocation().getY()==1023.0f)			//Going upward and reached the top border
				||
				((this.getDirection()==90||this.getDirection()==-270) && this.getLocation().getX()==1023.0f)	//Going rightward and reached the border
				||
				((this.getDirection()==180||this.getDirection()==-180) && this.getLocation().getY()==0.0f)		//Going downward and reached the border
				||
				((this.getDirection()==-90||this.getDirection()==270) && this.getLocation().getX()==1023.0f))	//Going leftward and reached the border
					this.isBlocked = true;
		return this.isBlocked;
	}
	
	public void setIsBlocked(boolean b)
	{
		this.isBlocked = b;
	}
	
	public void fire()
	{
		if(hasMissiles() == true)
		{
			currentStrategy.fire();
		}
		else
		{
			//System.out.println("Error: The tank has no missiles to fire.");
			JOptionPane.showMessageDialog(null,"Game Over - Due to exhaustion of missiles.","Tank Attack - CSC 133",1);
			System.exit(0);
		}
	}
	
	public boolean hasGrenades()
	{
		if(this.grenadeCount > 0)
			return true;
		else
			return false;
	}
	
	public boolean hasPlasma()
	{
		if(this.plasmaCount > 0)
			return true;
		else
			return false;
	}
	
	public GameWorldProxy getGameWorldProxy()
	{
		return this.gwp;
	}
	
	private boolean hasMissiles()
	{
		if(this.missileCount > 0) 
			return true;
		else
			return false;
	}
	
	public long getTicks()
	{
		return this.currentTick;
	}
	
	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(goRotation);
		g2d.transform(goScale);
		g2d.transform(goTranslation);
		
		if(isSelected())
		{
			if(getIsEnemy())
			{
				g2d.setColor(super.getColor());
				g2d.drawRect(getX(), getY(), 40, 40);
			}
			else
			{
				g2d.setColor(new Color(153,0,0));
				g2d.drawRect(getX(), getY(), 40, 40);
			}
		}
		else
		{
			if(getIsEnemy())
			{
				g2d.setColor(super.getColor());
				g2d.fillRect(getX(), getY(), 40, 40);
			}
			else
			{
				g2d.setColor(new Color(153,0,0));
				g2d.fillRect(getX(), getY(), 40, 40);
			}
		}
		g2d.setTransform(saveAT);
	}
	
	private int getX()
	{
		return (int)(super.getLocation().getX()-Math.sqrt(800));
	}
	
	private int getY()
	{
		return (int)(super.getLocation().getY()-Math.sqrt(800));
	}
	
	public int getImgSize()
	{
		return imgSize;
	}
	
	@Override
	public void move(long elapsedTime)
	{
		//Enemy Tanks move - implemented as animation
		float x=this.getLocation().getX(),y=this.getLocation().getY();
		//if(this.isEnemy)
		//{
			x = this.getLocation().getX() + (float)(Math.cos(90-Math.toRadians(getDirection()))*getSpeed());
			y = this.getLocation().getY() + (float)(Math.sin(90-Math.toRadians(getDirection()))*getSpeed());
			x += incx*5;
			y += incy*5;
		
		
			//Reversing the direction on colliding with the edge
			if(x >= 879 || x<20) 
			{
				incx=-incx;	
			}
			if(y >= 725 || y<20) 
			{
				incy=-incy;	
			}
		Point newLocation = new Point(x,y);
		updateLocation(newLocation);
	}
	
	@Override
	public void handleCollision(ICollider otherCollidingObj) {
		
		if(otherCollidingObj instanceof Missile)
		{
			int indexOfMissile = 1, missilesCount = 0, indexOfTank = 0, tanksCount = 0;
			ArrayList<Missile> missilesInGame = new ArrayList<Missile>();
			
			//Decrement the strength of the armor after the tank is hit only if the tank has some armor left
			if(this.getArmorStrength() > 0)
			{
				//System.out.println("\t  The armor before being hit: " + this.getArmorStrength());
				this.decrementArmorStrength();
				//System.out.println("\t  The armor after being hit: " + this.getArmorStrength());
			}
			else if(this.getArmorStrength() == 0)
			{
				//The tank has no armor and it has to die
				if(this.getIsEnemy() == false)
				{
					//The player tank is killed
					if(gwp.getLivesRemaining() > 0)
					{
						//System.out.println("\t  The lives for the player tank before it was dead: " + gwp.getLivesRemaining());
						//System.out.println("\t  The player tank is hit and is dead. So a new player tank is spawned");
						gwp.decrementLivesRemaining();
						//System.out.println("\t  The lives remaining for the player tank: " + gwp.getLivesRemaining());
						spawningLocation = this.getLocation();
						
						//The selected tank is killed i.e. removed from the game board by setting garbage coordinates
						this.kill();
						
						//A new tank is spawned and is replaced in the game world
						//this.updateLocation(spawningLocation);
						Tank newPlayerTank = new Tank(false, gwp);
						gwp.addPlayerTank(newPlayerTank);
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
					this.kill();
					Tank spawningEnemy = new Tank(true, gwp);
					gwp.getGameObjectCollection().add(this);
				}		
			}
		}
		
	}

	@Override
	public boolean collidesWith(ICollider otherCollidingObj) {
		// TODO Auto-generated method stub
		
		Point center = getLocation();
		Point otherCenter = ((GameObject)otherCollidingObj).getLocation();
		
		//Square of distance between their centers
		double dist = ((center.getX()-otherCenter.getX())*(center.getX()-otherCenter.getX()) + (center.getY()-otherCenter.getY())*(center.getY()-otherCenter.getY()));
		int otherSize=0;
		if(otherCollidingObj instanceof Rock) otherSize = ((Rock)otherCollidingObj).getWidth()/2;
		else if(otherCollidingObj instanceof Tree) otherSize = ((Tree)otherCollidingObj).getDiameter()/2;
		else if(otherCollidingObj instanceof Tank) otherSize = ((Tank)otherCollidingObj).getImgSize();
		else if(otherCollidingObj instanceof Missile) otherSize = ((Missile)otherCollidingObj).getImgSize();
		
		if((dist < (imgSize/2+otherSize/2)*(imgSize/2+otherSize/2)) && (center.getX()!=1025 && center.getY()!=1025))
		{
			if(gwp.getSound())
				tankCollidingSound.play();
			return true;
		}	
		else
			return false;
	}

	@Override
	public void setSelected(boolean yesNo) {
		// TODO Auto-generated method stub
		super.setIsSelected(yesNo);
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return super.getIsSelected();
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		float px = p.getX();
		float py = p.getY();
		float xLoc = getX();
		float yLoc = getY();
		if((px>=xLoc)&&(px<=xLoc+imgSize)&&(py>=yLoc)&&(py<=yLoc+imgSize))
			return true;
		else
			return false;
	}

	public void grenade() {
		// TODO Auto-generated method stub
		SpikedGrenade g = new SpikedGrenade(getLocation(), getDirection(), 40, getGameWorldProxy());
		g.scale(10,10);
	
		gwp.addGameObjects(g);
		gwp.getTickCommand().addTickObserver(g);
		decrementGrenadeCount();
	}
	
	public void decrementGrenadeCount()
	{
		this.grenadeCount--;
	}

	public void firePlasma() {
		// TODO Auto-generated method stub
		Plasma p = new Plasma(getLocation(), getDirection(), 40, getGameWorldProxy());
		gwp.addGameObjects(p);
		gwp.getTickCommand().addTickObserver(p);
		decrementPlasmaCount();
	}
	
	public void decrementPlasmaCount()
	{
		this.plasmaCount--;
	}
}
