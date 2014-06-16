package a4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MapView extends JPanel implements IObserver, MouseListener, MouseMotionListener, ActionListener {

	private GameWorldProxy gw;
	private Point startPoint = null;
	private Point currentPoint = null;
	private AffineTransform worldToND, ndToScreen, theVTM;
	
	public MapView(GameWorldProxy gwp)
	{
		gw = gwp;
		
		this.setBorder(new LineBorder(new Color(204,102,0), 1));
		this.setBackground(new Color(255,178,102));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private void consoleDisplay()
	{
		ArrayList<Tank> tankGameObjects = new ArrayList<Tank>();
		ArrayList<Missile> missileGameObjects = new ArrayList<Missile>();
		ArrayList<Rock> rockGameObjects = new ArrayList<Rock>();
		ArrayList<Tree> treeGameObjects = new ArrayList<Tree>();
		
		Iterator theGameObjects = gw.getGameObjectCollection().iterator();
		int i=0;
		while(theGameObjects.hasNext())
		{
			GameObject go = (GameObject)theGameObjects.next();
			if(go instanceof Tank)
			{
				Tank tank = (Tank)go;
				tankGameObjects.add(tank);
			}
			else if(go instanceof Missile)
			{
				Missile missile = (Missile)go;
				missileGameObjects.add(missile);
			}
			else if(go instanceof Rock)
			{
				Rock rock = (Rock)go;
				rockGameObjects.add(rock);
			}
			else if(go instanceof Tree)
			{
				Tree tree = (Tree)go;
				treeGameObjects.add(tree);
			}
			i++;
		}
		
		//System.out.println("\t  ****************************************************************************************************");
		//System.out.println("\t  *******************************************GAME MAP*************************************************");
		//System.out.println("\t  ****************************************************************************************************");
		
		
		for(i=0; i<tankGameObjects.size(); i++)
		{
			Tank tank = tankGameObjects.get(i);
			//System.out.println("\t  Tank: loc = " + tank.getLocation().getX() + ", " + tank.getLocation().getY() + " Color = [" + tank.getColor().getRed() + ", " + tank.getColor().getGreen() + ", " + tank.getColor().getBlue() + "] Speed = " + tank.getSpeed() + " Heading = " + tank.getDirection() + " Armor = " + tank.getArmorStrength() + " Missiles = " + tank.getMissileCount());
		}
		
		for(i=0; i<missileGameObjects.size(); i++)
		{
			Missile missile = missileGameObjects.get(i);
			//System.out.println("\t  Missile: loc = " + missile.getLocation().getX() + ", " + missile.getLocation().getY() + " Color = [" + missile.getColor().getRed() + ", " + missile.getColor().getGreen() + ", " + missile.getColor().getBlue() + "] Speed = " + missile.getSpeed() + " Heading = " + missile.getDirection() + "Lifetime = " + missile.getLifetime());
		}
		
		for(i=0; i<rockGameObjects.size(); i++)
		{
			Rock rock = rockGameObjects.get(i);
			//System.out.println("\t  Rock: loc = " + rock.getLocation().getX() + ", " + rock.getLocation().getY() + " Color = [" + rock.getColor().getRed() + ", " + rock.getColor().getGreen() + ", " + rock.getColor().getBlue() + "] Width = " + rock.getWidth() + " Height= " + rock.getheight());
		}
		
		for(i=0; i<treeGameObjects.size(); i++)
		{
			Tree tree = treeGameObjects.get(i);
			//System.out.println("\t  Tree: loc = " + tree.getLocation().getX() + ", " + tree.getLocation().getY() + " Color = [" + tree.getColor().getRed() + ", " + tree.getColor().getGreen() + ", " + tree.getColor().getBlue() + "] Diameter = " + tree.getDiameter() );
		}
		//System.out.println("\t  ****************************************************************************************************");
	}
	
	@Override
	public void update(IObservable observable, Object args) {
		// TODO Auto-generated method stub
		//consoleDisplay();
		this.repaint();
	}
	
	public void paintAfterTick()
	{
		this.repaint();
	}
	
	@Override
	public void register(IObservable o)
	{
		o.addObserver(this);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform saveAT = g2d.getTransform();
		
		Iterator<GameObject> iterator = gw.getGameObjectCollection().iterator();
		
		while(iterator.hasNext())
		{
			GameObject go = iterator.next();
			//worldToND = buildWorldToNDXform(this.getWidth(),this.getHeight(),0,this.getHeight());
			if(startPoint != null && currentPoint != null)
			{
				g.drawRect((int)startPoint.getX(), (int)startPoint.getY(), (int)(currentPoint.getX()-startPoint.getX()), (int)(currentPoint.getY()-startPoint.getY()));
				if(go.getLocation().getX()>startPoint.getX() && go.getLocation().getY()>startPoint.getY()
						&& go.getLocation().getX()<currentPoint.getX() && go.getLocation().getY()<currentPoint.getY())
					go.setSelected(true);
				else
					go.setSelected(false);
			}
			
			go.draw(g);
		}
	}

	private AffineTransform buildWorldToNDXform(int width, int height, int winLeft, int winBottom) {
		// TODO Auto-generated method stub
		AffineTransform t = new AffineTransform();
		t.scale(width, height);
		t.translate(winLeft, winBottom);
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = new Point((float)e.getX(),(float)e.getY());
		Iterator iter = gw.getGameObjectCollection().iterator();
		while(iter.hasNext())
		{
			GameObject go = (GameObject)iter.next();
			if(go.contains(p))
				go.setSelected(true);
		}
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		startPoint = new Point(me.getX(),me.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO Auto-generated method stub
		currentPoint = new Point(me.getX(),me.getY());
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//grenade.update();
		this.repaint();
	}
}
