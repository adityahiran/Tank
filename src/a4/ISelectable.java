package a4;

import java.awt.Graphics;

public interface ISelectable {
	public void setSelected(boolean yesNo);
	public boolean isSelected();
	public boolean contains(Point p);
	public void draw(Graphics g);
}
