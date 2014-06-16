package a4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ScoreView extends JPanel implements IObserver, ITickObserver {

	private String switchSound = "OFF";
	private GameWorldProxy gw;
	private JLabel elapsedTimeLabel;
	private JLabel livesLabel;
	private JLabel scoreLabel;
	private JLabel sound;
	
	public ScoreView(GameWorldProxy gw)
	{
		this.gw = gw;
		setInitialScoreView();
	}
	
	public JPanel getScoreView()
	{
		return this;
	}
	
	private void setInitialScoreView()
	{
		this.setBorder(new LineBorder(new Color(204,102,0),1));
		this.setLayout(new FlowLayout());
		
		elapsedTimeLabel = new JLabel("Elapsed Time: " + gw.getElapsedTime());
		livesLabel = new JLabel("Lives Left: " + gw.getLivesRemaining());
		scoreLabel = new JLabel("Score: " + gw.getCurrentScore());
		sound = new JLabel("Sound: " + switchSound);
		
		this.add(elapsedTimeLabel);
		this.add(livesLabel);
		this.add(scoreLabel);
		this.add(sound);
		
	}
	
	
	
	private void consoleDisplay()
	{
		System.out.println("\t  Displaying the game state values");
		System.out.println("\t  **********************************************************");
		System.out.println("\t  *********************GAME STATE***************************");
		System.out.println("\t  **********************************************************");
		System.out.println("\t  The current game clock value: " + gw.getElapsedTime());
		System.out.println("\t  The current score: " + gw.getCurrentScore());
		System.out.println("\t  The number of lives left: " + gw.getLivesRemaining());
		System.out.println("\t  **********************************************************");
	}
	
	@Override
	public void update(IObservable observable, Object args) {
		// TODO Auto-generated method stub
		//consoleDisplay();
		
		this.setBackground(Color.ORANGE);
		this.setLayout(new FlowLayout());
		
		//System.out.println("\n\n\t  The sound in score view after being notified : "+gw.getSound());
		if(gw.getSound() == true) switchSound = "ON";
		else switchSound = "OFF";
		
		sound.setText(String.valueOf("Sound: " + switchSound));
	}

	@Override
	public void register(IObservable o)
	{
		o.addObserver(this);
	}

	@Override
	public void update(ITickObservable observable, Object args, long tick) {
		// TODO Auto-generated method stub
		if(tick%50==0)
			this.elapsedTimeLabel.setText(String.valueOf("Elapsed Time: " + Double.toString(tick/50.0)+" seconds"));
	}
}
