package a4;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;

public class ReverseCommand extends AbstractAction  {

	private GameWorldProxy gwp;
	
	public ReverseCommand(GameWorldProxy gwp)
	{
		super("Reverse");
		this.gwp = gwp;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(gwp.getIsPlayMode()) 
		setEnabled(false);
		else
			setEnabled(true);
		Iterator iter = gwp.getGameObjectCollection().iterator();
		while(iter.hasNext())
		{
			GameObject go = (GameObject)iter.next();
			if(go.getIsSelected() && go instanceof Tank)
			{
				//((MoveableItem)go).setSpeed(-1*((MoveableItem)go).getSpeed());
				((Tank)go).setIncx();
			}
		}
	}
	
	public void disableButton()
	{
		if(gwp.getIsPlayMode()) super.setEnabled(false);
	}

	public void enableButton() {
		// TODO Auto-generated method stub
		if(gwp.getIsPlayMode()==false) super.setEnabled(true);
	}

}
