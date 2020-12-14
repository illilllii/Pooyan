package pooyangame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Meat extends JLabel{
	private ImageIcon icMeat;
	public boolean isIn = false;
	public int x=496;
	public int y=70;
	public Meat() {
		icMeat = new ImageIcon("images/meat.png");
		setIcon(icMeat);
		setSize(160,90);
		setLocation(x,y);
	}
}
