package a4;

public class TankProxy implements ITank {

	private Tank tank;
	
	public TankProxy(Tank tank)
	{
		this.tank = tank;
	}
	
	@Override
	public GameWorldProxy getGameWorldProxy()
	{
		return tank.getGameWorldProxy();
	}
	
	@Override
	public Point getLocation()
	{
		return tank.getLocation();
	}
	
	@Override
	public int getDirection()
	{
		return tank.getDirection();
	}
	
	@Override
	public void decrementMissileCount()
	{
		tank.decrementMissileCount();
	}

	@Override
	public void setCurrentStrategy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsEnemy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getArmorStrength() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMissileCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changeDirection(int d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrementArmorStrength() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsBlocked(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public long getTicks()
	{
		return tank.getTicks();
	}
}
