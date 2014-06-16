package a4;

import java.io.File;

public class EveryTwoTicksFireStrategy implements IStrategy {

	private TankProxy tank;
	private GameWorldProxy gwp;
	private static String soundDir = "sounds" + File.separator;
	private static String soundFile = "tankCollides.wav";
	private static String soundPath = soundDir+soundFile;
	private static Sound fireSound = new Sound(soundPath);
	
	public EveryTwoTicksFireStrategy(TankProxy t, GameWorldProxy gwp)
	{
		this.tank = t;
		this.gwp = gwp;
	}
	
	@Override
	public void fire()
	{
		//System.out.println("\n");
		if(tank.getTicks() % 2 == 0)
		{
			//System.out.println("\t  Using every two ticks fire strategy the missile is fired");
			Missile m = new Missile(tank.getLocation(), tank.getDirection(), 40, gwp);
			tank.getGameWorldProxy().addGameObjects(m);
			tank.getGameWorldProxy().getTickCommand().addTickObserver(m);
			tank.decrementMissileCount();
			if(tank.getGameWorldProxy().getSound() == true)
				fireSound.play();
		}
		else
		{
			//System.out.println("\t  As the tank is using every two ticks strategy this time it didnot fire");
		}
	}

}
