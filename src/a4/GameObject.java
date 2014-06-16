package a4;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public abstract class GameObject implements IDrawable, ICollider, ISelectable {

	private Point location;
	private Color color; 
	private boolean isGarbageCollectible = false;
	private ArrayList<Point> usedLocations = new ArrayList<Point>();
	private boolean isSelected = false;
	public GameObject()
	{
		/* The code below makes sure that there is no overlap between game objects
		 * at the time of setting their location 
		 */
		int i,x,y;
		boolean flag = true;
		Random random = new Random();
		Point newLocation = new Point(0.0f,0.0f);
		while(flag)
		{
			x = random.nextInt(699) + 100;
			y = random.nextInt(499) + 100;
		
			newLocation = new Point(x*1.0f,y*1.0f);
			for(i=0; i<usedLocations.size(); i++)
			{
				if(usedLocations.get(i).comparePoint(newLocation)==true) break;
			}
			if(i==usedLocations.size())
			{
				//This new location was not used previously to place any game object 
				flag = false;
			}
		}
		// End of Overlap avoiding code to set the location for the game objects
		setLocation(newLocation);
		usedLocations.add(newLocation);
		setColor();	
	}
	
	private void setColor()
	{
		if(this instanceof Rock)
		{
			this.color = new Color(153,76,0);
		}
		else if(this instanceof Tree)
		{
			this.color = new Color(0,102,0);
		}
		else if(this instanceof Missile)
		{
			this.color = Color.RED;
		}
		else if(this instanceof Tank)
		{
			this.color = new Color(153,0,76);
		}
		else
		{
			this.color = Color.GRAY;
		}
	}
	
	public void setIsSelected(boolean b)
	{
		this.isSelected = b;
	}
	
	public boolean getIsSelected()
	{
		return this.isSelected;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public void setIsGarbageCollectible(boolean b)
	{
		this.isGarbageCollectible = b;
	}
	
	public boolean getIsGarbageCollectible()
	{
		return this.isGarbageCollectible;
	}
	
	public Point getLocation()
	{
		return this.location;
	}
	
	public void checkMoveableSetLocation(Point newLocation)
	{
		if(this instanceof MoveableItem) setLocation(newLocation);
	}
	
	public void killAndSetLocation()
	{
		setGarbageLocation();
		setIsGarbageCollectible(true);
	}
	
	private void setGarbageLocation()
	{
		Point p = new Point(1025, 1025);
		location = p;
	}
	
	private void setLocation(Point p)
	{
		if(isValidLocation(p) == true)
			location = p;
	}
	
	private boolean isValidLocation(Point p)
	{
		if(p.getX() >= -50.0f && p.getY() >= -50.0f && p.getX() <= 999.0f && p.getY() <= 799.0f) 
			return true;
		else 
			return false;
	}
}
