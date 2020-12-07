package pooyangame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Arrow extends JLabel{
	private ImageIcon icBow;
	
	public Arrow() {
		icBow = new ImageIcon("images/bow.png");
		setIcon(icBow);
		setSize(130, 50);
	}
}
