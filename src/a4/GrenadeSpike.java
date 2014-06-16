package a4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class GrenadeSpike {

	private Point top, bottomLeft, bottomRight;
	private Color spikeColor;
	
	private AffineTransform spikeTranslation, spikeRotation, spikeScale;
	
	public GrenadeSpike()
	{
		top = new Point(0,2);
		bottomLeft = new Point(-1,-2);
		bottomRight = new Point(1,-2);
		
		spikeTranslation = new AffineTransform();
		spikeRotation = new AffineTransform();
		spikeScale = new AffineTransform();
	}
	
	public void rotate(double degrees)
	{
		spikeRotation.rotate(Math.toRadians(degrees));
	}
	
	public void scale(double sx, double sy)
	{
		spikeScale.scale(sx, sy);
	}
	
	public void translate(double dx, double dy)
	{
		spikeTranslation.translate(dx, dy);
	}
	
	public void draw(Graphics2D g)
	{
		AffineTransform saveAT = g.getTransform();
		
		g.transform(spikeRotation);
		g.transform(spikeScale);
		g.transform(spikeTranslation);
		
		g.setColor(Color.yellow);
		
		int [] xPts = new int [] {(int)top.getX(),(int)bottomLeft.getX(),(int)bottomRight.getX()};
		int [] yPts = new int [] {(int)top.getY(),(int)bottomLeft.getY(),(int)bottomRight.getY()};
		g.fillPolygon(xPts, yPts, 3);
		
		g.setTransform(saveAT);
	}

	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.spikeColor = color;
	}
}
