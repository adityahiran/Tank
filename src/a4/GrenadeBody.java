package a4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class GrenadeBody {

	private int grenadeRadius;
	private Color grenadeColor=Color.red;
	private AffineTransform grenadeTranslation, grenadeRotation, grenadeScale;
	
	public GrenadeBody()
	{
		grenadeRadius=1;
		grenadeTranslation = new AffineTransform();
		grenadeRotation = new AffineTransform();
		grenadeScale = new AffineTransform();
	}
	
	public void rotate(double degrees)
	{
		grenadeRotation.rotate(Math.toRadians(degrees));
	}
	
	public void scale(double sx, double sy)
	{
		grenadeScale.scale(sx, sy);
	}
	
	public void translate(double dx, double dy)
	{
		grenadeTranslation.translate(dx, dy);
	}
	
	public void draw(Graphics2D g)
	{
		AffineTransform saveAT = g.getTransform();
		
		g.transform(grenadeRotation);
		g.transform(grenadeScale);
		g.transform(grenadeTranslation);
		
		g.setColor(Color.yellow);
		
		Point upperLeft = new Point(-grenadeRadius, -grenadeRadius);
		g.fillOval((int)upperLeft.getX(), (int)upperLeft.getY(), grenadeRadius*2, grenadeRadius*2);
		g.setTransform(saveAT);
	}
}
