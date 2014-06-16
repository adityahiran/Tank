package a4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TickObserversCollection implements Collection {

	private ArrayList<ITickObserver> observers = new ArrayList<ITickObserver>();
	
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new TickObserversIterator();
	}
	
	private class TickObserversIterator implements Iterator {
		
		private int currentIndex;
		
		public TickObserversIterator()
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
		public ITickObserver next()
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
			this.observers.add((ITickObserver)obj);
			return true;
		}
		catch(Exception ex)
		{
			System.out.println("Caught an exception");
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
