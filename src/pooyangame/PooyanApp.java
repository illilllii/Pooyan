package pooyangame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PooyanApp extends JFrame implements Initable{

		private PooyanApp pooyanApp = this;

		private static final String TAG = "PooyanApp : ";
		public static int floor=0;
		private JLabel laBackground;
		private Wolf wolf;
		private Pooyan pooyan; 
		private boolean isPressed = false;
		ArrayList<Wolf> wolves;
		
		public PooyanApp() {
			init();
			setting();
			batch();
			listener();

			setVisible(true);
		}

		public static void main(String[] args) {
			new PooyanApp();
		}

		@Override
		public void init() {
			laBackground = new JLabel(new ImageIcon("images/background.png"));
			wolf = new Wolf();
			wolves = new ArrayList<Wolf>();
			pooyan = new Pooyan();

			
			new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < 4; i++) {
						//wolf = new Wolf();
						wolves.add(new Wolf());
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}).start();
		}

		@Override
		public void setting() {
			setTitle("Pooyan");
			setSize(620, 630);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setLocationRelativeTo(null);
			getContentPane().setLayout(null);
			setContentPane(laBackground);

		}

		@Override
		public void batch() {
			add(pooyan);
			new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < wolves.size(); i++) {
						wolves.get(i).moveFall();
						getContentPane().add(wolves.get(i));
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			
			
		}
		@Override
		public void listener() {
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					
					if(e.getKeyCode() == KeyEvent.VK_UP) {
						pooyan.moveUp();
					} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {			
						pooyan.moveDown();	
					} else if(e.getKeyCode() == KeyEvent.VK_SPACE) {						
						isPressed = true;
						pooyan.isShoot = true;
						pooyan.shoot();
						
					}
					
				}
				@Override
				public void keyReleased(KeyEvent e) {
					
					if(e.getKeyCode() == KeyEvent.VK_UP) {
						pooyan.isUp = false;
					} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
						pooyan.isDown = false;
					} else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
						System.out.println("keyReleased");
						isPressed = false;
						pooyan.isShoot = false;
						pooyan.shoot();
						
						//if(isPressed) {
							
							
						//}
						
					}
				}
				
				
			});
			
		}

}
