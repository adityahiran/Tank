package a4;

public class Point {
	
	private float x;
	private float y;
	
	public Point(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}

	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}

	public boolean comparePoint(Point newLocation) {
		// TODO Auto-generated method stub
		return (Math.sqrt((double)((newLocation.getX()-this.x)*(newLocation.getX()-this.x) + (newLocation.getY()-this.y)*(newLocation.getY()-this.y)))>190);
	}
}
