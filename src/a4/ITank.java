package a4;

public interface ITank {

	public void setCurrentStrategy();
	public void decrementMissileCount();
	public boolean getIsEnemy();
	public void getArmorStrength();
	public int getMissileCount();
	public void changeDirection(int d);
	public void decrementArmorStrength();
	public boolean isBlocked();
	public void setIsBlocked(boolean b);
	public void fire();
	public long getTicks();
	public int getDirection();
	public Point getLocation();
	public GameWorldProxy getGameWorldProxy();
}
