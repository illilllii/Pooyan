package pooyangame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Wolf extends JLabel {
	public Wolf wolf = this;
	private final static String TAG = "Wolf : ";

	private PooyanApp pooyanApp;
	private int floor = 0;
	private ImageIcon iconWolfM4, iconWolfM5, iconWalkWolfR1, iconAttackStayWolf;
	private int x = 50;
	private int y = 50;
	public boolean isDown = false;
	public boolean isRight = false;
	public boolean isUp = false;

	public Wolf() {
		iconWolfM4 = new ImageIcon("images/WolfMint4.png");
		iconWolfM5 = new ImageIcon("images/WolfMint5.png");
		iconWalkWolfR1 = new ImageIcon("images/walkWolfR1.png");
		iconAttackStayWolf = new ImageIcon("images/attackStayWolfL.png");
		setIcon(iconWolfM4);
		setSize(130, 130);
		setLocation(x, y);

	}

	public void moveFall() { // Ç³¼±Å¸°í ¶¥À¸·Î ³»·Á°¨
		//System.out.println(TAG + "moveFall");

		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					while (isDown) {
						if (y > 480) {
							isDown = false;
							isRight = true;
							wolf.moveRight();
							setIcon(iconWalkWolfR1);
							break;
						}
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	public void moveRight() {
		//System.out.println(TAG + "moveRight");

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRight) {
					if (x > 540) {
						isRight = false;
						isUp = true;
						pooyanApp.floor = pooyanApp.floor + 1;
						floor = pooyanApp.floor;
						wolf.moveUP();
						setIcon(iconAttackStayWolf);
						break;
					}
					x++;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void moveUP() {
		//System.out.println(TAG + "moveUp");

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isUp) {

					if (floor == 1 && y < 400) {
						isUp = false;
						break;
					}
					if (floor == 2 && y < 300) {
						isUp = false;
						break;
					}
					if (floor == 3 && y < 200) {
						isUp = false;
						break;
					}
					if (floor == 4 && y < 100) {
						isUp = false;
						break;
					}
					y--;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
