package a4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

public class Game extends JFrame implements ActionListener
{	
	private GameWorld gw;
	private GameWorldProxy gwp;
	private MapView mv;
	private ScoreView sv;
	private TickCommand tickCommand;
	private Timer gameTimer = new Timer(20, this);
		
	public Game()
	{
		try
		{
		System.out.print("\n\tThe number of Enemy tanks: 4");
		int noOfEnemyTanks = 4;
		
		System.out.print("\tThe number of rocks: 4");
		int noOfRocks = 4;
		
		System.out.print("\tThe number of trees: 4");
		int noOfTrees = 4;
		
		// Construct all the components of the game
		gw = new GameWorld(noOfEnemyTanks, noOfRocks, noOfTrees);
		System.out.println("\n\tThe game world has been created with the input given");
		
		gwp = new GameWorldProxy(gw);
		
		this.tickCommand = gw.getTickCommand();
		mv = new MapView(gwp);
		sv = new ScoreView(gwp);
		
		tickCommand.addTickObserver(sv);
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		buildGUI();
		gameTimer.start();
		}
		catch(Exception ex)
		{
			System.out.println("Program exited due to Invalid entry "+"error: "+ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private void buildGUI()
	{
		//Setting the JFrame characteristics
		setTitle("Tank Attack");
		setSize(gw.getWidth(), gw.getLength());
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
			//Designing GUI for the Menu and the Score
			JPanel menuAndScore = new JPanel();
			menuAndScore.setLayout(new BorderLayout());
		
				//Menu Bar - containing all the menus
				JMenuBar menuBar = new JMenuBar();
				menuBar.setBackground(Color.orange);
					//Menu - File
					JMenu fileMenu = new JMenu("File");
		
						//MenuItems:
						//Menu Item - New
						JMenuItem newMenuItem = new JMenuItem("New");
							NewCommand newCommand = new NewCommand();
							newMenuItem.setAction(newCommand);
		
						//Menu Item - Save
						JMenuItem saveMenuItem = new JMenuItem("Save");
							SaveCommand saveCommand = new SaveCommand();
							saveMenuItem.setAction(saveCommand);
		
						//Menu Item - Undo
						JMenuItem undoMenuItem = new JMenuItem("Undo");
							UndoCommand undoCommand = new UndoCommand();
							undoMenuItem.setAction(undoCommand);
		
						//Menu Item - Sound
						JCheckBoxMenuItem soundMenuItem = new JCheckBoxMenuItem("Sound", gw.getSound());
							SoundCommand soundCommand = new SoundCommand(gwp);
							soundMenuItem.setAction(soundCommand);
		
						//Menu Item - About
						JMenuItem aboutMenuItem = new JMenuItem("About");
							AboutCommand aboutCommand = new AboutCommand();
							aboutMenuItem.setAction(aboutCommand);
		
						//Menu Item - Quit
						JMenuItem quitMenuItem = new JMenuItem("Quit");
							QuitCommand quitCommand = new QuitCommand();
							quitMenuItem.setAction(quitCommand);
		
					fileMenu.add(newMenuItem);
					fileMenu.add(saveMenuItem);
					fileMenu.add(undoMenuItem);
					fileMenu.add(soundMenuItem);
					fileMenu.add(aboutMenuItem);
					fileMenu.add(quitMenuItem);
		
					//Menu - Command
					JMenu cmdMenu = new JMenu("Command");
		
						//Menu items
						//Menu Item - Enemy fire
						JMenuItem eMenuItem = new JMenuItem("Enemy fire");
							EnemyFireCommand enemyFire = new EnemyFireCommand(gwp);
							eMenuItem.setAction(enemyFire);
		
						//Menu Item - Tank Hit
						JMenuItem oneMenuItem = new JMenuItem("Tank Hit");
							PlayerTankHitCommand playerTankHit = new PlayerTankHitCommand(gwp);
							oneMenuItem.setAction(playerTankHit);
		
						//Menu Item - Missiles Collision
						JMenuItem twoMenuItem = new JMenuItem("Missiles collision");
							MissilesCollideCommand missilesCollide = new MissilesCollideCommand(gwp);
							twoMenuItem.setAction(missilesCollide);
		
						//Menu Item - Tank Blocked
						JMenuItem threeMenuItem = new JMenuItem("Tank Blocked");
							TankBlockCommand tankBlock = new TankBlockCommand(gwp);
							threeMenuItem.setAction(tankBlock);
		
					cmdMenu.add(eMenuItem);
					cmdMenu.add(oneMenuItem);
					cmdMenu.add(twoMenuItem);
					cmdMenu.add(threeMenuItem);
					//End of Menus
				
				menuBar.add(fileMenu);
				menuBar.add(cmdMenu);
		
			//Menu bar is added to the JPanel
			menuAndScore.add(menuBar, BorderLayout.NORTH);
		
			//The score view jpanel is added here
			menuAndScore.add(sv.getScoreView(), BorderLayout.CENTER);
			//End of JPanel containing menu and score
				
		
			//Designing the GUI for the Commands
			JPanel cmd = new JPanel();
			cmd.setBorder(new TitledBorder("Commands"));
			cmd.setBackground(Color.orange);
			cmd.setLayout(new GridLayout(10,1));
		
			//JLabel pause = new JLabel("Pause");
			ReverseCommand reverse = new ReverseCommand(gwp);
			GameModeCommand gameMode = new GameModeCommand(gameTimer,gwp,reverse);
				JButton cmdPauseButton = new JButton("Pause");
					cmdPauseButton.setBackground(new Color(255,153,51));
					cmdPauseButton.setAction(gameMode);
			
					JButton cmdReverseButton = new JButton("Reverse");
						cmdReverseButton.setBackground(new Color(255,153,51));
						cmdReverseButton.setAction(reverse);		
		
			cmd.add(cmdPauseButton);
			cmd.add(cmdReverseButton);
			//End of cmd JPanel containing JButtons
		
		//Declaring the remaining commands
		FireCommand fireCommand = new FireCommand(gwp.getPlayerTank());
		GrenadeCommand grenadeCommand = new GrenadeCommand(gwp.getPlayerTank());
		PlasmaCommand plasmaCommand = new PlasmaCommand(gwp.getPlayerTank());
		LeftTurnCommand leftCommand = new LeftTurnCommand(gwp.getPlayerTank());
		RightTurnCommand rightCommand = new RightTurnCommand(gwp.getPlayerTank());
		IncreaseSpeedCommand increaseSpeedCommand = new IncreaseSpeedCommand(gwp);	
		DecreaseSpeedCommand decreaseSpeedCommand = new DecreaseSpeedCommand(gwp);
		
		HelpCommand helpCommand = new HelpCommand(gwp);
		
		//Set Key Bindings for the Map JPanel
		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap imap = mv.getInputMap(mapName);
		
		KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
		KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
		KeyStroke iKey = KeyStroke.getKeyStroke('i');
		KeyStroke kKey = KeyStroke.getKeyStroke('k');
		KeyStroke fKey = KeyStroke.getKeyStroke('f');
		KeyStroke eKey = KeyStroke.getKeyStroke('e');
		KeyStroke oneKey = KeyStroke.getKeyStroke("1");
		KeyStroke twoKey = KeyStroke.getKeyStroke("2");
		KeyStroke threeKey = KeyStroke.getKeyStroke("3");
		KeyStroke tKey = KeyStroke.getKeyStroke('t');
		KeyStroke helpKey = KeyStroke.getKeyStroke('?');
		KeyStroke qKey = KeyStroke.getKeyStroke('q');
		KeyStroke gKey = KeyStroke.getKeyStroke('g');
		KeyStroke pKey = KeyStroke.getKeyStroke('p');
		
		imap.put(leftKey, "Take Left");
		imap.put(rightKey, "Take Right");
		imap.put(iKey, "Increase Speed");
		imap.put(kKey, "Decrease Speed");
		imap.put(fKey, "Fire");
		imap.put(gKey, "Grenade");
		imap.put(pKey, "Plasma");
		imap.put(eKey, "Enemy Fire");
		imap.put(oneKey, "Player Tank Hit");
		imap.put(twoKey, "Missiles Collide");
		imap.put(threeKey, "Tank Block");
		imap.put(tKey, "Tick");
		imap.put(helpKey, "Help");
		imap.put(qKey, "Quit");
		
		ActionMap amap = mv.getActionMap();
		
		amap.put("Take Left", leftCommand);
		amap.put("Take Right", rightCommand);
		amap.put("Increase Speed", increaseSpeedCommand);
		amap.put("Decrease Speed", decreaseSpeedCommand);
		amap.put("Fire", fireCommand);
		amap.put("Grenade", grenadeCommand);
		amap.put("Plasma", plasmaCommand);
		amap.put("Enemy Fire", enemyFire);
		amap.put("Player Tank Hit", playerTankHit);
		amap.put("Missiles Collide", missilesCollide);
		amap.put("Tank Block", tankBlock);
		amap.put("Tick", tickCommand);
		amap.put("Help", helpCommand);
		amap.put("Quit", quitCommand);
		
		this.requestFocus();
		
		//Add the various JPanels to the JFrame
		this.add(menuAndScore, BorderLayout.NORTH);
		this.add(cmd, BorderLayout.WEST);
		this.add(mv, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public TickCommand getTickCommand()
	{
		return this.tickCommand;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		tickCommand.handleTickEvent();
		gwp.setElapsedTime(tickCommand.getTicks()/50);
		gw.detectAndHandleCollision();
		mv.paintAfterTick();
	}
}
