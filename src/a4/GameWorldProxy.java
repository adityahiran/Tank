package a4;

public class GameWorldProxy implements IObservable, IGameWorld {
	
	private GameWorld realGameWorld;
	
	public GameWorldProxy(GameWorld gw)
	{
		realGameWorld = gw;
	}
	
	@Override
	public Tank getPlayerTank()
	{
		return realGameWorld.getPlayerTank();
	}
	
	@Override
	public void addObserver(IObserver observer)
	{
		//Do nothing - protecting access
		realGameWorld.addObserver(observer);
	}
	
	@Override
	public void notifyObservers()
	{
		//Do Nothing - Protecting access
		System.out.println("entered proxy - notify observers method");
		realGameWorld.notifyObservers();
	}
	
	@Override
	public boolean getSound()
	{
		return realGameWorld.getSound();
	}
	
	@Override
	public void setSound(boolean b)
	{
		realGameWorld.setSound(b);
	}
	
	@Override
	public void play()
	{
		//Do Nothing
	}
	
	@Override
	public void incrementElapsedTime()
	{
		realGameWorld.incrementElapsedTime();
	}
	
	@Override
	public long getElapsedTime()
	{
		return realGameWorld.getElapsedTime();
	}
	
	@Override
	public long getCurrentScore()
	{
		return realGameWorld.getCurrentScore();
	}
	
	@Override
	public int getLivesRemaining()
	{
		return realGameWorld.getLivesRemaining();
	}
	
	@Override
	public GameObjectCollection getGameObjectCollection()
	{
		return realGameWorld.getGameObjectCollection();
	}
	
	@Override
	public void addGameObjects(GameObject go)
	{
		realGameWorld.addGameObjects(go);
	}
	
	@Override
	public void decrementLivesRemaining()
	{
		realGameWorld.decrementLivesRemaining();
	}
	
	@Override
	public TickCommand getTickCommand()
	{
		return realGameWorld.getTickCommand();
	}

	@Override
	public void garbageCollect() {
		// TODO Auto-generated method stub
		realGameWorld.garbageCollect();
	}

	public void addPlayerTank(Tank newPlayerTank) {
		// TODO Auto-generated method stub
		realGameWorld.addPlayerTank(newPlayerTank);
	}

	@Override
	public void setIsPlayMode(boolean b) {
		// TODO Auto-generated method stub
		realGameWorld.setIsPlayMode(b);
	}

	@Override
	public boolean getIsPlayMode() {
		// TODO Auto-generated method stub
		return realGameWorld.getIsPlayMode();
	}

	public void setElapsedTime(long time) {
		// TODO Auto-generated method stub
		realGameWorld.setElapsedTime(time);
	}
}
