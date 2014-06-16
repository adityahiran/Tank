package a4;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class SoundCommand extends AbstractAction {

	private GameWorldProxy gwp;
	
	public SoundCommand(GameWorldProxy gw)
	{
		super("Sound");
		gwp = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\t  Prompted to switch the sound for the game");
		if(gwp.getSound() == true)
		{
			System.out.println("\t  Sound initially: " + gwp.getSound());
			gwp.setSound(false);
			System.out.println("\t  Sound after switching: " + gwp.getSound());
		}
		else
		{
			System.out.println("\t  Sound initially: " + gwp.getSound());
			gwp.setSound(true);
			System.out.println("\t  Sound after switching: " + gwp.getSound());
		}
	}
}
