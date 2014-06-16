package a4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Plasma extends MoveableItem  {

	private AffineTransform plasmaTranslation, plasmaRotation, plasmaScale;
	private Point controlPointArray[] = new Point[4];
	private GameWorldProxy gwp;
	private int lifetime; 
	
	public Plasma(Point location, int direction, float speed, GameWorldProxy gwp)
	{
		this.checkMoveableSetLocation(location);
		this.setSpeed(speed);
		this.checkNewMissileSetDirection(direction, getLifetime());
		this.gwp = gwp;
		this.lifetime = 15;
		
		controlPointArray[0] = new Point(location.getX()+10,location.getY()+10);
		controlPointArray[1] = new Point(location.getX()+25,location.getY()+20);
		controlPointArray[2] = new Point(location.getX(),location.getY()+17);
		controlPointArray[3] = new Point(location.getX()+50,location.getY());
		
		plasmaTranslation = new AffineTransform();
		plasmaRotation = new AffineTransform();
		plasmaScale = new AffineTransform();
	}
	
	public void rotate(double degrees)
	{
		plasmaRotation.rotate(Math.toRadians(degrees));
	}
	
	public void scale(double sx, double sy)
	{
		plasmaScale.scale(sx, sy);
	}
	
	public void translate(double dx, double dy)
	{
		plasmaTranslation.translate(dx, dy);
	}
	
	
	public int getLifetime()
	{
		return this.lifetime;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(plasmaTranslation);
		g2d.transform(plasmaScale);
		g2d.transform(plasmaRotation);

		g2d.setColor(new Color(0,0,255));
		for (int i=0; i<=2; i++) {
		Point thisPoint = controlPointArray[i];
		Point nextPoint = controlPointArray[i+1];
		//g2d.drawLine((int)thisPoint.getX(),(int)thisPoint.getY(),(int)nextPoint.getX(),(int)nextPoint.getY());
		}
		drawBezierCurve(controlPointArray,g2d,0);

		g2d.setTransform(saveAT);
		}

		private void drawBezierCurve(Point [] points, Graphics2D g2d, int level) {
		Point [] leftArray = new Point[4];
		Point [] rightArray = new Point[4];
		if (level > 10) {
		Point p1 = points[0];
		Point p2 = points[3];
		int p1X = (int)p1.getX();
		int p1Y = (int)p1.getY();
		int p2X = (int)p2.getX();
		int p2Y = (int)p2.getY();
		g2d.drawLine(p1X,p1Y,p2X,p2Y);
		}
		else {
		subdivideCurve(points, leftArray, rightArray);
		drawBezierCurve(leftArray,g2d,level+1);
		drawBezierCurve(rightArray,g2d,level+1);
		}
		}

		private void subdivideCurve(Point [] Q, Point [] R, Point [] S) { 
		int x;
		int y;

		x = (int)Q[0].getX();
		y = (int)Q[0].getY();
		R[0] = new Point(x,y);

		x = (int)(Q[0].getX()+Q[1].getX())/2;
		y = (int)(Q[0].getY()+Q[1].getY())/2;
		R[1] = new Point(x,y);

		x = (int)((R[1].getX()/2)+((Q[1].getX()+Q[2].getX())/4));
		y = (int)((R[1].getY()/2)+((Q[1].getY()+Q[2].getY())/4));
		R[2] = new Point(x,y);

		x = (int)Q[3].getX();
		y = (int)Q[3].getY();
		S[3] = new Point(x,y);

		x = (int)(Q[2].getX()+Q[3].getX())/2;
		y = (int)(Q[2].getY()+Q[3].getY())/2;
		S[2] = new Point(x,y);

		x = (int)(((Q[1].getX()+Q[2].getX())/4)+(S[2].getX()/2));
		y = (int)(((Q[1].getY()+Q[2].getY())/4)+(S[2].getY()/2));
		S[1] = new Point(x,y);

		x = (int)(R[2].getX()+S[1].getX())/2;
		y = (int)(R[2].getY()+S[1].getY())/2;
		R[3] = new Point(x,y);

		x = (int)R[3].getX();
		y = (int)R[3].getY();
		S[0] = new Point(x,y);
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
		
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void move(long elapsedTime) {
		// TODO Auto-generated method stub
		plasmaTranslation.translate(20, 20);
	}
}
