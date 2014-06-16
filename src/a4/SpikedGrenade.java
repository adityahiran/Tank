package a4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.Timer;

public class SpikedGrenade extends Projectile {

	private GrenadeBody grenadeBody;
	private GrenadeSpike [] spikes;
	private AffineTransform grenadeTranslation, grenadeRotation, grenadeScale;
	private double spikeOffset =0;
	private double spikeIncrement = 0.05;
	private double maxSpikeOffset = 0.5;
	private int incx=1,incy=1;
	private GameWorldProxy gwp;
	private int lifetime;
	
	public SpikedGrenade(Point location, int direction, float speed, GameWorldProxy gwp)
	{
		this.checkMoveableSetLocation(location);
		this.setSpeed(speed);
		this.checkNewMissileSetDirection(direction, getLifetime());
		this.gwp = gwp;
		this.lifetime = 5;
		
		grenadeTranslation = new AffineTransform();
		grenadeRotation = new AffineTransform();
		grenadeScale = new AffineTransform();
		
		grenadeBody = new GrenadeBody();
		grenadeBody.scale(2.5, 1.5);
		
		spikes = new GrenadeSpike[4];
		
		GrenadeSpike s0 = new GrenadeSpike();
		s0.translate(0, 4);
		s0.scale(0.5,0.8);
		spikes[0]=s0;
		spikes[0].setColor(Color.yellow);
		
		GrenadeSpike s1 = new GrenadeSpike();
		s1.translate(0, 7);
		s1.rotate(-90);
		s1.scale(0.5,0.5);
		spikes[1]=s1;
		spikes[1].setColor(Color.yellow);
		
		GrenadeSpike s2 = new GrenadeSpike();
		s2.translate(0, 4);
		s2.rotate(180);
		s2.scale(0.5,0.8);
		spikes[2]=s2;
		spikes[2].setColor(Color.yellow);
		
		GrenadeSpike s3 = new GrenadeSpike();
		s3.translate(0, 7);
		s3.rotate(90);
		s3.scale(0.5,0.5);
		spikes[3]=s3;
		spikes[3].setColor(Color.yellow);
	}

	public int getLifetime()
	{
		return this.lifetime;
	}
	
	public void decrementLifetime()
	{
		this.lifetime--;
	}
	
	public boolean isGrenadeDead()
	{
		return this.lifetime==0;
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
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(grenadeRotation);
		g2d.transform(grenadeScale);
		g2d.transform(grenadeTranslation);
		
		grenadeBody.draw(g2d);
		
		for(GrenadeSpike s : spikes)
		{
			s.draw(g2d);
		}
		
		g2d.setTransform(saveAT);
	}

	public void update() {
		// TODO Auto-generated method stub
		grenadeTranslation.translate(0.5, 0.5);
		
		spikeOffset += spikeIncrement;
		for(GrenadeSpike s : spikes)
		{
			s.translate(0,spikeOffset);
		}
		
		if(Math.abs(spikeOffset) >= maxSpikeOffset)
		{
			spikeIncrement *= -1;
		}
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
		//float x,y;
		update();/*
		//Missiles don't bounce back the edge of the game board. It only appears like that because of continuous firing of the missiles.
		x = this.getLocation().getX() + (float)Math.cos(90-Math.toRadians(getDirection()))*getSpeed();
		y = this.getLocation().getY() + (float)Math.sin(90-Math.toRadians(getDirection()))*getSpeed();
		x += incx*20;
		y += incy*20;
		
		Point newLocation = new Point(x,y);
		updateLocation(newLocation);*/
	}
}
