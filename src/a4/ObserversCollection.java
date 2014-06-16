package a4;

import java.util.*;

public class ObserversCollection implements Collection {

	private ArrayList<IObserver> observers = new ArrayList<IObserver>();
	
	@Override
	public Iterator<IObserver> iterator()
	{
		return new ObserversIterator();
	}
	
	private class ObserversIterator implements Iterator
	{
		private int currentIndex;
		
		public ObserversIterator()
		{
			currentIndex = -1;
		}
		
		@Override
		public boolean hasNext()
		{
			if(observers.size()<=0)
				return false;
			if(currentIndex==observers.size()-1)
				return false;
			
			return true;
		}
		
		@Override
		public IObserver next()
		{
			currentIndex++;
			return observers.get(currentIndex);
		}
		
		@Override
		public void remove()
		{
			//Do nothing
		}
	}

	@Override
	public boolean add(Object obj) {
		try
		{
			this.observers.add((IObserver)obj);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	@Override
	public boolean addAll(Collection arg0) {
		// Do nothing
		return false;
	}

	@Override
	public void clear() {
		// Do nothing
		
	}

	@Override
	public boolean contains(Object arg0) {
		// Do nothing
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// Do nothing
		return false;
	}

	@Override
	public boolean isEmpty() {
		// Do nothing
		return false;
	}

	@Override
	public boolean remove(Object arg0) {
		// Do nothing
		return false;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// Do nothing
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// Do nothing
		return false;
	}

	@Override
	public int size() {
		// Do nothing
		return 0;
	}

	@Override
	public Object[] toArray() {
		// Do nothing
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// Do nothing
		return null;
	}
	
}
