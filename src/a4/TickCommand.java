package a4;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractAction;

public class TickCommand extends AbstractAction implements ITickObservable {

	private GameWorldProxy gwp;
	TickObserversCollection observersCollection;
	private long tick=0;
	
	public TickCommand(GameWorldProxy gwp)
	{
		super("Tick");
		this.gwp = gwp;
		observersCollection = new TickObserversCollection();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		handleTickEvent();
	}
	
	public void handleTickEvent()
	{
		//System.out.println("\n");
		//System.out.println("\t  The game clock has ticked");
		//System.out.println("\t  Ticks prior to the tick command: "+ this.tick);
		incrementTick();
		if(tick % 50 == 0) 
		{
			gwp.garbageCollect();
			gwp.incrementElapsedTime();
		}
		/*
		System.out.println("\t  Ticks after the tick command: "+ this.tick);
		ArrayList<MoveableItem> m = new ArrayList<MoveableItem>();
		MoveableItem item;
		Iterator theGameObjects = gwp.getGameObjectCollection().iterator();
		int i=0;
		while(theGameObjects.hasNext())
		{
			GameObject go = (GameObject)theGameObjects.next();
			if(go instanceof MoveableItem)
			{
				item = (MoveableItem)go;
				
				//Note: For few locations there is no change in the location, this can happen because of precision one being considered
				System.out.println("\t  The location of the moveable item prior to the tick: (" + item.getLocation().getX() + "," + item.getLocation().getY() + ")");
				item.move(this.tick*20);
				System.out.println("\t  The location of the moveable item after the tick: (" + item.getLocation().getX() + "," + item.getLocation().getY() + ")");
				m.add(item);
			}
			i++;
		}
		
		System.out.println("\t  The elapsed time prior to the tick: " + gwp.getElapsedTime());
		gwp.incrementElapsedTime();
		System.out.println("\t  The elapsed time after the tick: " + gwp.getElapsedTime());
		*/
		/*
		Missile selected;
		theGameObjects = gwp.getGameObjectCollection().iterator();
		i=0;
		while(theGameObjects.hasNext())
		{
			GameObject go = (GameObject)theGameObjects.next();
			if(go instanceof Missile)
			{
				selected = (Missile)go;
				System.out.println("\t  The missile lifetime prior to the tick: " + selected.getLifetime());
				selected.decrementLifetime();
				System.out.println("\t  The missile lifetime after the tick: " + selected.getLifetime());
				if(selected.getLifetime() == 0)
				{
					selected.kill();
					gwp.getGameObjectCollection().remove(selected);
					System.out.println("\t  The expired missile is removed from the game ");
				}
			}
			i++;
		}
		*/
	}
	
	public void incrementTick()
	{
		tick++;
		notifyTickObservers();
	}

	@Override
	public void addTickObserver(ITickObserver observer) {
		// TODO Auto-generated method stub
		observersCollection.add(observer);
	}
	
	public long getTicks()
	{
		return this.tick;
	}

	@Override
	public void notifyTickObservers() {
		// TODO Auto-generated method stub
		Iterator<ITickObserver> iterator = this.observersCollection.iterator();
		
		while(iterator.hasNext())
		{
			ITickObserver observer = (ITickObserver)iterator.next();
			observer.update((ITickObservable)this, null, tick);		//20 instead of tick will make it run as required
		}
	}

}
