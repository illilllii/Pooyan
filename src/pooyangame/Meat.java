package pooyangame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Meat extends JLabel{
	private ImageIcon icMeat;
	public Meat() {
		icMeat = new ImageIcon("images/meat.png");
		setIcon(icMeat);
		setSize(160,90);
		setLocation(496,70);
	}
}