package a4;
import java.util.*;

public class GameObjectCollection implements Collection {
	
	private ArrayList<GameObject> gameObjects;
	private GameWorldProxy gwp;
	
	public GameObjectCollection(GameWorldProxy gwp)
	{
		this.gwp = gwp;
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(new Tank(false, this.gwp));
	}
	
	public Tank getPlayerTank()
	{
		return (Tank)gameObjects.get(0);
	}
	
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new GameObjectIterator();
	}
	
	@Override
	public boolean add(Object newGameObject) {
		try
		{
			if(newGameObject instanceof Tank && ((Tank) newGameObject).getIsEnemy()==false)
			{
				gameObjects.set(0, (GameObject)newGameObject);
			}
			else
			{
				gameObjects.add((GameObject)newGameObject);
			}
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	private class GameObjectIterator implements Iterator
	{
		int currentIndex;
		
		public GameObjectIterator()
		{
			currentIndex = -1;
		}
		
		@Override
		public boolean hasNext() {
			if(gameObjects.size() <= 0) 
				return false;
			if(currentIndex == gameObjects.size()-1) 
				return false;
			
			return true;
		}

		@Override
		public GameObject next() {
			currentIndex++;
			return gameObjects.get(currentIndex);
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}

	public void addPlayerTank(Tank newPlayerTank) {
		// TODO Auto-generated method stub
		gameObjects.set(0, newPlayerTank);
	}
}
