package pooyangame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pooyan extends JPanel {
	public Pooyan pooyan = this;
	private ArrayList<Arrow> listArrow;
	private Meat meat;
	private final static String TAG = "Pooyan : ";
	private Color transparency;

	private ImageIcon icElevator, icAttackBow, icAttackPy, icAttackMeatPy;
	private JLabel laElevator, laAttackBow, laAttackPy, laAttackMeatPy;
	private JPanel jpPlayer;

	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isShoot = false;
	public boolean isArrow = false;
	public boolean isItem = false;
	public boolean isMeat = false;

	public int x = 486;
	public int y = 130;

	public int arrowX = 486;
	public int arrowY = 130;
	public int meatX = 0;
	public int meatY = 0;

	private void init() {

		icElevator = new ImageIcon("images/elevator.png");
		laElevator = new JLabel();

		icAttackBow = new ImageIcon("images/attackBowPy.png");
		laAttackBow = new JLabel();

		icAttackPy = new ImageIcon("images/attackPy.png");
		laAttackPy = new JLabel();

		icAttackMeatPy = new ImageIcon("images/attackMeatPy.png");
		laAttackMeatPy = new JLabel();

		transparency = new Color(255, 0, 0, 0);
		jpPlayer = new JPanel();

		listArrow = new ArrayList<Arrow>();
		meat = new Meat();

	}

	private void setting() {

		setSize(620, 630);
		setLayout(null);
		setOpaque(false);
		setBackground(transparency);

		laElevator.setSize(50, 80);
		laElevator.setIcon(icElevator);
		laElevator.setBounds(10, 0, 50, 80);

		laAttackBow.setIcon(icAttackBow);
		laAttackBow.setBounds(0, 20, 50, 50);

		laAttackPy.setIcon(icAttackPy);
		laAttackPy.setBounds(0, 20, 50, 50);
		laAttackPy.setVisible(false);

		laAttackMeatPy.setIcon(icAttackMeatPy);
		laAttackMeatPy.setBounds(0, 20, 50, 50);
		laAttackMeatPy.setVisible(false);

		jpPlayer.setLayout(null);
		jpPlayer.setSize(80, 80);
		jpPlayer.setOpaque(false);
		jpPlayer.setBackground(transparency);
		jpPlayer.setLocation(x, y);

		

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					if (isItem == false) {
//						if(isMeat == false) {
//							meat.setLocation(496, 70);
//						}
//						
//						if (jpPlayer.getLocation().x == meat.getLocation().x - 10
//								&& jpPlayer.getLocation().y == meat.getLocation().y + 30) {
//							isItem = true;
//
//							meat.x = jpPlayer.getLocation().x;
//							meat.y = jpPlayer.getLocation().y;
//							meat.setLocation(meat.x, meat.y);
//
//							if (meat.isVisible() == true) {
//								meat.setVisible(false);
//							}
//
//							laAttackBow.setVisible(false);
//							laAttackMeatPy.setVisible(true);
//						}
//						try {
//							if (isItem == false) {
//								meat.setVisible(false);
//								Thread.sleep(800);
//								meat.setVisible(true);
//								Thread.sleep(1000);
//							}
//
//						} catch (InterruptedException e) {
//
//							e.printStackTrace();
//						}
//					}
//
//				}
//
//			}
//		}).start();

	}

	private void batch() {
		jpPlayer.add(laElevator);
		jpPlayer.add(laAttackBow);
		jpPlayer.add(laAttackPy);
		jpPlayer.add(laAttackMeatPy);
		add(jpPlayer);
		add(meat);
	}

	public Pooyan() {
		init();
		setting();
		batch();

	}

	public void moveUp() {
		if (isUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isUp = true;
					while (isUp) {
						y--;
						if (y < 100) {
							y++;
							isUp = false;
						}
						jpPlayer.setLocation(x, y);
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}).start();
		}

	}

	public void moveDown() {
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					while (isDown) {
						y++;
						if (y > 413) {
							y--;
							isDown = false;

						}
						jpPlayer.setLocation(x, y);
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void shoot() {
		if (isShoot == true) {
//			if (isItem == true) {
//
//				if (isMeat == false) {
//					isMeat = true;
//					meat.x = jpPlayer.getLocation().x;
//					meat.y = jpPlayer.getLocation().y;
//
//					meatX = meat.x;
//					meatY = meat.y;
//
//					meat.setLocation(meatX, meatY);
//					meat.setVisible(true);
//					laAttackMeatPy.setVisible(false);
//
//					new Thread(new Runnable() {
//
//						@Override
//						public void run() {
//
//							while (true) {
//								meatX--;
//
//								meat.setLocation(meatX, meatY);
//								if (meatX < 50) {
//									//isItem = false;
//									isMeat = false;
//								}
//								try {
//									Thread.sleep(5);
//								} catch (InterruptedException e) {
//									e.printStackTrace();
//								}
//							}
//
//						}
//					}).start();
//				}
//
//			}

			if (isArrow == false) {
				listArrow.add(new Arrow());
				if (isItem == true) {
					isArrow = false;

				} else {

					isArrow = true;
				}
				if (isArrow == true) {
					for (int i = 0; i < listArrow.size(); i++) {
						if (listArrow.get(i).isIn == false) {
							listArrow.get(i).x = jpPlayer.getLocation().x - 60;
							listArrow.get(i).y = jpPlayer.getLocation().y + 50;

							arrowX = listArrow.get(i).x;
							arrowY = listArrow.get(i).y;

							listArrow.get(i).setLocation(arrowX, arrowY);

							add(listArrow.get(i));

						}
						listArrow.get(i).isIn = true;

					}
					new Thread(new Runnable() {
						@Override
						public void run() {
							while (true) {
								for (int i = 0; i < listArrow.size(); i++) {
									listArrow.get(i).x--;
									listArrow.get(i).setLocation(listArrow.get(i).x, listArrow.get(i).y);
									if (listArrow.get(i).x < -2) {
										remove(listArrow.get(i));
									}
									try {
										Thread.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}

						}
					}).start();
					laAttackBow.setVisible(false);
					laAttackPy.setVisible(true);
					repaint();
				}

			} else {

				laAttackBow.setVisible(true);
				laAttackPy.setVisible(false);
				repaint();

			}

		} else {

			laAttackBow.setVisible(true);
			laAttackPy.setVisible(false);
			isArrow = false;
			repaint();

		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 216, 225));
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(jpPlayer.getLocation().x + 35, 100, jpPlayer.getLocation().x + 35, jpPlayer.getLocation().y);
	}
}
