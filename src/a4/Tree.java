package a4;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Tree extends LandscapeItem {
	
	private int diameter;
	private AffineTransform goTranslation, goRotation, goScale;
	
	public Tree(int d)
	{
		super();
		this.diameter = d;
		goTranslation = new AffineTransform();
		goRotation = new AffineTransform();
		goScale = new AffineTransform();
	}
	
	public int getDiameter()
	{
		return this.diameter;
	}
	
	public void setDiameter(int d)
	{
		if(validateDiameter(d))
			this.diameter = d;
	}
	
	private boolean validateDiameter(int d)
	{
		if(d>=1 && d<=20) return true;
		else return false;
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
			g2d.drawOval(getX(), getY(), getDiameter()/2, getDiameter()/2);
		else
			g2d.fillOval(getX(), getY(), getDiameter()/2, getDiameter()/2);
		g2d.setTransform(saveAT);
	}

	private int getX()
	{
		return (int)super.getLocation().getX()-getDiameter()/2;
	}
	
	private int getY()
	{
		return (int)super.getLocation().getY()-getDiameter()/2;
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
		//Using a bounding rectangle
		float px = p.getX();
		float py = p.getY();
		float xLoc = getX();
		float yLoc = getY();
		if((px>=xLoc)&&(px<=xLoc+getDiameter())&&(py>=yLoc)&&(py<=yLoc+getDiameter()))
			return true;
		else
			return false;
	}
}
