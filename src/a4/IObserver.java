package a4;

public interface IObserver {
	
	public void update(IObservable observable, Object args);
	public void register(IObservable observable);

}
