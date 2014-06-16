package a4;

public interface IGameWorld {
	
	public boolean getSound();
	public void setSound(boolean b);
	public void play();
	public long getElapsedTime();
	public int getLivesRemaining();
	public long getCurrentScore();
	public GameObjectCollection getGameObjectCollection();
	public void addGameObjects(GameObject go);
	public void decrementLivesRemaining();
	public void incrementElapsedTime();
	public TickCommand getTickCommand();
	public void garbageCollect();
	public void setIsPlayMode(boolean b);
	public boolean getIsPlayMode();
	public Tank getPlayerTank();
}
