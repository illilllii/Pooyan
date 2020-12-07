package pooyangame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pooyan extends JPanel{
	public Pooyan pooyan = this;
	private Arrow arrow;
	private final static String TAG = "Pooyan : ";
	private Color transparency;
	
	private ImageIcon icElevator, icAttackBow, icAttackPy;
	private JLabel laElevator, laAttackBow, laAttackPy;
	private JPanel jpPlayer;
	
	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isShoot = false;
	
	public int x = 486;
	public int y = 100;
	
	public int arrowX = x;
	public int arrowY = y;
	
	
	private void init() {
		arrow = new Arrow();
		
		icElevator = new ImageIcon("images/elevator.png");
		laElevator = new JLabel();
		
		icAttackBow = new ImageIcon("images/attackBowPy.png");
		laAttackBow = new JLabel();
		
		icAttackPy = new ImageIcon("images/attackPy.png");
		laAttackPy = new JLabel();
		
		transparency = new Color(255,0,0,0);
		jpPlayer = new JPanel();
		
		
	}
	
	private void setting() {
		
		setSize(620,630);
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
		
		
		jpPlayer.setLayout(null);
		jpPlayer.setSize(80, 80);
		jpPlayer.setOpaque(false);
		jpPlayer.setBackground(transparency);
		jpPlayer.setLocation(x, y);

		arrow.setLocation(arrowX,arrowY);
		arrow.setOpaque(false);
	}
	
	private void batch() {
		jpPlayer.add(laElevator);
		jpPlayer.add(laAttackBow);
		jpPlayer.add(laAttackPy);
		add(jpPlayer);
		add(arrow);

	}
	
	public Pooyan() {
		init();
		setting();
		batch();
		
	}
	
	
	public void moveUp() {
		if(isUp == false) {
			new Thread(new Runnable() {	
				@Override
				public void run() {
					isUp = true;
					while(isUp) {
						System.out.println(y);
						y--;
						//arrowY--;
						if(y<100) {	
							y++;
							isUp = false;
						}
						jpPlayer.setLocation(x,y);
						arrow.setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}).start();
		}
		
	}
	
	public void moveDown() {
		if(isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					while(isDown) {
						System.out.println(y);
						y++;
						//arrowY++;
						if(y > 413) {
							y--;
							isDown = false;
							
						}
						jpPlayer.setLocation(x, y);
						arrow.setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	
	public void shoot() {
		if(isShoot) {
			laAttackBow.setVisible(false);
			laAttackPy.setVisible(true);
			repaint();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						arrowX--;
						if(arrowX<-10) {
							pooyan.remove(arrow);
						}
						arrow.setLocation(arrowX, arrowY);
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}).start();
		} else {
			laAttackBow.setVisible(true);
			laAttackPy.setVisible(false);
			repaint();
			
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(jpPlayer.getLocation().x + 35, 100, jpPlayer.getLocation().x + 35, jpPlayer.getLocation().y);
	}
}
