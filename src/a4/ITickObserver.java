package a4;

public interface ITickObserver {
	public void update(ITickObservable observable, Object args, long tick);
	
}
