package a4;

import java.io.File;

public class EveryTickFireStrategy implements IStrategy {

	private TankProxy tank;
	private static String soundDir = "sounds" + File.separator;
	private static String soundFile = "tankCollides.wav";
	private static String soundPath = soundDir+soundFile;
	private static Sound fireSound = new Sound(soundPath);;
	
	public EveryTickFireStrategy(TankProxy t)
	{
		this.tank = t;
	}
	
	@Override
	public void fire()
	{
		//System.out.println("\n");
		//System.out.println("\t  Every tick fire strategy");
		Missile m = new Missile(tank.getLocation(), tank.getDirection(), 10, tank.getGameWorldProxy());
		tank.getGameWorldProxy().addGameObjects(m);
		tank.getGameWorldProxy().getTickCommand().addTickObserver(m);
		tank.decrementMissileCount();
		if(tank.getGameWorldProxy().getSound() == true)
			fireSound.play();
	}
}
