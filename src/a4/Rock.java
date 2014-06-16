package a4;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Rock extends LandscapeItem {
	
	private int height;
	private int width;
	private AffineTransform goTranslation, goRotation, goScale;
	
	public Rock(int h, int w)
	{
		super();
		if(validateDimensions(h,w))
		{
			setWidth(w);
			setHeight(h);
		}
		goTranslation = new AffineTransform();
		goRotation = new AffineTransform();
		goScale = new AffineTransform();
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
	
	public int getheight()
	{
		return this.height;
	}
	
	public void setHeight(int h)
	{
		this.height = h;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public void setWidth(int w)
	{
		this.width = w;
	}
	
	private boolean validateDimensions(int h, int w)
	{
		if(h>=1 && w>=1 && h<=50 && w<=50) 
			return true;
		else 
			return false;
	}

	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(goRotation);
		g2d.transform(goScale);
		g2d.transform(goTranslation);
		
		g2d.setColor(super.getColor());
		if(isSelected())
			g2d.drawRect(getX(), getY(), width, height);
		else
			g2d.fillRect(getX(), getY(), width, height);
		
		g2d.setTransform(saveAT);
	}
	
	private int getX()
	{
		return (int)super.getLocation().getX()-width/2;
	}
	
	private int getY()
	{
		return (int)super.getLocation().getY()-height/2;
	}

	@Override
	public boolean collidesWith(ICollider go) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(ICollider go) {
		// TODO Auto-generated method stub
		
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
		if((px>=xLoc)&&(px<=xLoc+width)&&(py>=yLoc)&&(py<=yLoc+height))
			return true;
		else
			return false;
	}
}
