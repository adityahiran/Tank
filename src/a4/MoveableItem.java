package a4;

public abstract class MoveableItem extends GameObject implements ITickObserver {

	private int direction = 0;
	private float speed = 0.0f;
	
	public void checkSteerableSetDirection(int d)
	{
		if(this instanceof ISteerable)
			setDirection(d);
	}
	
	public void checkNewMissileSetDirection(int d, int lifetime)
	{
		if(lifetime == 5)		//Condition for a new missile
			setDirection(d);
	}

	public abstract void move(long elapsedTime);
	
	public void updateLocation(Point newLocation)
	{
		checkMoveableSetLocation(newLocation);
	}
	
	public void kill()
	{
		killAndSetLocation();
	}
	
	public int getDirection()
	{
		return this.direction;
	}
	
	private void setDirection(int d)
	{
		if(isValidDirection(d))
			this.direction = d;
		else
			System.out.println("Error: Set direction failed!! Invalid new direction");
	}
	
	private boolean isValidDirection(int d)
	{
		boolean ret = false;
		if(d%5 == 0 && d>=-360 && d<=360) 
			ret = true;
		return ret;
	}
	
	public float getSpeed()
	{
		return this.speed;
	}
	
	public void setSpeed(float s)
	{
		if(isValidSpeed(s) == true)
			this.speed = s;
		else 
			this.speed = 0.0f;
	}
	
	private boolean isValidSpeed(float s)
	{
		if(s>0.0f) 
			return true;
		else 
			return false;
	}
	
	@Override
	public void update(ITickObservable observable, Object args, long tick) {
		// TODO Auto-generated method stub
		move(tick);
	}
}
