package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class HelpCommand extends AbstractAction {

	private GameWorldProxy gwp;
	
	public HelpCommand(GameWorldProxy gwp)
	{
		super("Hep");
		this.gwp = gwp;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\n");
		System.out.println("\n\tCOMMAND \t\t DESCRIPTION");
		System.out.println("\t-> \t\t\t Turn right");
		System.out.println("\t<- \t\t\t Turn left");
		System.out.println("\ti \t\t\t Increase speed");
		System.out.println("\tk \t\t\t Decrease speed");
		System.out.println("\tf \t\t\t Fire");
		System.out.println("\te \t\t\t Enemy fires");
		System.out.println("\t1 \t\t\t Tank has been hit");
		System.out.println("\t2 \t\t\t Two missiles collided");
		System.out.println("\t3 \t\t\t Tank has collided with the landscape");
		System.out.println("\tt \t\t\t Tell the game world that the game clock has ticked");
		System.out.println("\td \t\t\t Display current game state values");
		System.out.println("\tm \t\t\t Output the map of the current world");
		System.out.println("\t? \t\t\t Help on commands");
		System.out.println("\tq \t\t\t Quit from the game\n");
	}
}
