package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.Timer;

public class GameModeCommand extends AbstractAction {

	private Timer gameTimer;
	private GameWorldProxy gwp;
	private ReverseCommand reverse;
	public GameModeCommand(Timer gameTimer, GameWorldProxy gwp, ReverseCommand reverse)
	{
		super("Pause");
		this.gameTimer = gameTimer;
		this.gwp = gwp;
		this.reverse = reverse;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(e.getActionCommand().equals("Pause"))
		{	
			if (source instanceof JButton) 
        	{
        		JButton btn = (JButton)source;
        		btn.setText("Play");
        		gameTimer.stop();
        		gwp.setIsPlayMode(false);
        		reverse.disableButton();
        	}
		}
		else
		{
			if (source instanceof JButton) 
        	{
        		JButton btn = (JButton)source;
        		btn.setText("Pause");
        		gameTimer.start();
        		gwp.setIsPlayMode(true);
        		reverse.enableButton();
        	}
		}
			
		
		
		
	}

}
