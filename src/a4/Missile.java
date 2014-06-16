package a4;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.io.File;

public class Missile extends Projectile {
	
	private int lifetime;
	private int imgSize = 2;
	private int incx=1,incy=1;
	private static String soundDir = "."+ File.separator + "sounds"+File.separator;
	private static String soundFile = "missileCollides.wav";
	private static String soundPath = soundDir+soundFile;
	private static Sound missileCollidingSound = new Sound(soundPath);
	private GameWorldProxy gwp;
	private AffineTransform goTranslation, goRotation, goScale;
	
	public Missile(Point location, int direction, float speed, GameWorldProxy gwp)
	{
		super();
		this.lifetime = 5;
		this.checkMoveableSetLocation(location);
		this.setSpeed(speed);
		this.checkNewMissileSetDirection(direction, getLifetime());
		this.gwp = gwp;
		goTranslation = new AffineTransform();
		goRotation = new AffineTransform();
		//goRotation.rotate(direction);
		goScale = new AffineTransform();
		//goScale.scale(0.5,1);
	}
	
	public void rotate(double degrees)
	{
		goRotation.rotate(Math.toRadians(degrees));
	}
	
	public void scale(double sx, double sy)
	{
		goScale.scale(sx, sy);
	}
	
	public void translate(double dx, double dy)
	{
		goTranslation.translate(dx, dy);
	}
	
	public int getLifetime()
	{
		return this.lifetime;
	}
	
	public void decrementLifetime()
	{
		this.lifetime--;
	}
	
	public boolean isMissileDead()
	{
		return this.lifetime==0;
	}

	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(goRotation);
		g2d.transform(goScale);
		g2d.transform(goTranslation);
		
		int xPoints[] = {(int)super.getLocation().getX(), (int)super.getLocation().getX()-8, (int)super.getLocation().getX()+8};
		int yPoints[] = {(int)super.getLocation().getY()-8, (int)super.getLocation().getY()+8, (int)super.getLocation().getY()+8};
		Polygon p = new Polygon(xPoints, yPoints, 3);
		g2d.setColor(super.getColor());
		g2d.fillPolygon(p);
		
		g2d.setTransform(saveAT);
	}

	@Override
	public void move(long elapsedTime) {
		float x,y;
		
		//Missiles don't bounce back the edge of the game board. It only appears like that because of continuous firing of the missiles.
		x = this.getLocation().getX() + (float)Math.cos(90-Math.toRadians(getDirection()))*getSpeed();
		y = this.getLocation().getY() + (float)Math.sin(90-Math.toRadians(getDirection()))*getSpeed();
		x += incx*20;
		y += incy*20;
		
		Point newLocation = new Point(x,y);
		updateLocation(newLocation);
	}

	@Override
	public void handleCollision(ICollider obj)
	{
		this.kill();	//setLocation(new Point(1025.0f, 1025.0f));
	}

	@Override
	public boolean collidesWith(ICollider otherCollidingObj) {
		
		Point center = getLocation();
		Point otherCenter = ((GameObject)otherCollidingObj).getLocation();
				
		//Square of distance between their centers
		float dist = (center.getX()-otherCenter.getX())*(center.getX()-otherCenter.getX()) + (center.getY()-otherCenter.getY())*(center.getY()-otherCenter.getY());
		int otherSize=0;
		if(otherCollidingObj instanceof Rock) otherSize = ((Rock)otherCollidingObj).getWidth();
		else if(otherCollidingObj instanceof Tree) otherSize = ((Tree)otherCollidingObj).getDiameter()/2;
		else if(otherCollidingObj instanceof Tank) otherSize = ((Tank)otherCollidingObj).getImgSize();
		else if(otherCollidingObj instanceof Missile) otherSize = this.imgSize;
				
		if(dist < ((imgSize/2+otherSize/2)*(imgSize/2+otherSize/2)) && (center.getX()!=1025 && center.getY()!=1025))
		{
			if(gwp.getSound())
				missileCollidingSound.play();
			return true;
		}
		else
			return false;
	}

	public int getImgSize() {
		return this.imgSize;
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
		//Using a bounding rectangle
		float px = p.getX();
		float py = p.getY();
		float xLoc = getLocation().getX();
		float yLoc = getLocation().getY();
		if((px>=xLoc)&&(px<=xLoc+imgSize)&&(py>=yLoc)&&(py<=yLoc+imgSize))
			return true;
		else
			return false;
	}
}
