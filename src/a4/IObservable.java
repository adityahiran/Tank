package a4;

public interface IObservable {
	
	public void addObserver(IObserver observer);
	public void notifyObservers();
}
