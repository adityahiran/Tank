package a4;

public interface ITickObservable {
	public void addTickObserver(ITickObserver observer);
	public void notifyTickObservers();
}
