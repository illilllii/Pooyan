package pooyangame;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Wolf extends JLabel {
	private static final long serialVersionUID = 1L;
	public Wolf wolf = this;
	private final static String TAG = "Wolf : ";

	private PooyanApp pooyanApp;
	private int floor = 0;
	private ImageIcon iconWolfM4, iconWolfM5, iconWalkWolfR, iconAttackStayWolf, iconAttackStayWolfR, iconAttackWolf1, iconAttackWolf2;
	private int x = 50;
	private int y = 50;
	public int count = 0;
	public boolean isDown = false;
	public boolean isRight = false;
	public boolean isRightGround = false;
	public boolean isUp = false;
	public boolean isAttack = false;

	public Random random = new Random();
	
	public Wolf() {
		iconWolfM4 = new ImageIcon("images/WolfMint4.png");
		iconWolfM5 = new ImageIcon("images/WolfMint5.png");
		iconWalkWolfR = new ImageIcon("images/walkWolfR.gif");
		iconAttackStayWolf = new ImageIcon("images/attackStayWolf.gif");
		iconAttackStayWolfR = new ImageIcon("images/attackStayWolfR.png");
		iconAttackWolf1 = new ImageIcon("images/attackWolf1.png");
		iconAttackWolf2 = new ImageIcon("images/attackWolf2.png");
		
		setIcon(iconWolfM4);
		setSize(130, 130);
		setLocation(x, y);
	}

	
	public void moveFall() { 
		System.out.println(TAG + "moveFall");

		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					while (isDown) {
						if (y > 490) {
							isDown = false;
							isRightGround = true;
							wolf.moveRightGround();
							setIcon(iconWalkWolfR);
							break;
						}
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
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

	public void moveRightGround() {
		System.out.println(TAG + "moveRightGround");

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRightGround) {
					if (x > 540) {
						isRightGround = false;
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
						e.printStackTrace();
					}
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
						isAttack = true;
						wolf.attack();
						break;
					}
					if (floor == 2 && y < 320) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					if (floor == 3 && y < 230) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					if (floor == 4 && y < 150) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					y--;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	public void attack() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				int num = random.nextInt(10000);
				while (isAttack) {
					System.out.println(num);
					count++;
					if (count == num) {
						setIcon(iconAttackWolf1);
						setLocation(510, getY());
						try {
							Thread.sleep( random.nextInt(2000)+500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						setIcon(iconAttackWolf2);
						try {
							Thread.sleep(800);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						setIcon(iconAttackWolf1);
						setIcon(iconAttackStayWolfR);
						
						setLocation(x, getY());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						num = random.nextInt(10000);
						count=0;
						
					}
				}
			}
		}).start();
	}
}
