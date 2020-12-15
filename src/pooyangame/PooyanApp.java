package pooyangame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PooyanApp extends JFrame implements Initable{

		private PooyanApp pooyanApp = this;

		private static final String TAG = "PooyanApp : ";
		public int floor=0;
		public int count = 0;
		
		private JLabel laBackground;
		private Wolf wolf;
		private Pooyan pooyan; 
		ArrayList<Wolf> wolves;
		public JPanel viewPanel;
		private JPanel gameOverPanel;
		public boolean isAddWolf = false;
		public JLabel laRemainWolf;
		private JLabel laLife;
		public int remainWolf = 34;
		private JLabel laGameOver;
		public boolean isReset = false;
		public int randTime; // 늑대 생성 간격 랜덤 시간
		public int randWolf;  // 늑대 생성 수 랜덤
		private Clip clip;
		private JLabel laScore;

		public void Play(String fileName) {
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void Stop() {
			clip.stop();
			clip.close();
		}
		public PooyanApp() {
			init();
			setting();
			batch();
			listener();

			setVisible(true);
		}

		@Override
		public void init() {
			laBackground = new JLabel(new ImageIcon("images/background.png"));
			wolves = new ArrayList<Wolf>();
			pooyan = new Pooyan(pooyanApp, wolf);
			laRemainWolf = new JLabel();
			laLife = new JLabel();
			viewPanel = new JPanel();
			gameOverPanel = new JPanel();
			laGameOver = new JLabel();
			laScore = new JLabel();
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
			laRemainWolf.setText(""+remainWolf);
			laRemainWolf.setSize(30,30);
			laRemainWolf.setLocation(10,10);
			laRemainWolf.setFont(new Font("Serif", Font.BOLD, 30));
			laRemainWolf.setForeground(Color.WHITE);
			
			laLife.setText("❤ ❤");
			laLife.setSize(70,30);
			laLife.setLocation(520, 30);
			laLife.setFont(new Font("Serif", Font.BOLD, 30));
			laLife.setForeground(Color.WHITE);
			
			laScore.setText("0");
			laScore.setSize(70,30);
			laScore.setLocation(500,200);
			laScore.setFont(new Font("Serif", Font.BOLD, 30));
			laScore.setForeground(Color.WHITE);
			viewPanel.setSize(620,630);
			viewPanel.setLayout(null);
			viewPanel.setOpaque(false);
			
			gameOverPanel.setSize(620,630);
			gameOverPanel.setLayout(null);
			gameOverPanel.setOpaque(false);
			
			laGameOver.setText("GAME OVER");
			laGameOver.setSize(200,30);
			laGameOver.setLocation(300,300);
			laGameOver.setFont(new Font("Serif", Font.BOLD, 30));
			laGameOver.setForeground(Color.WHITE);
		}

		@Override
		public void batch() {
			viewPanel.add(pooyan);
			wolfAdd(); // 늑대 생성
			viewPanel.add(laRemainWolf);
			viewPanel.add(laLife);
			viewPanel.add(laScore);
			add(viewPanel);
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
						pooyan.isShoot = false;

						pooyan.shoot();
						
					}
				}
				
				
			});
			
		}
		public void score() {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						laScore.setText(Integer.toString(pooyan.score));
						break;
					}
					
				}
			}).start();
		}
		public void wolfAdd() {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						//if(isReset) break;
						try {
							isReset = false;
							randWolf = (int) (Math.random() * 4) + 1;
							System.out.println(randWolf);
							for (int i = 0; i < randWolf; i++) {
								wolves.add(new Wolf(pooyanApp, pooyan));
								viewPanel.add(wolves.get(count));
								count = wolves.size();
								System.out.println("늑대 " + count);
								Thread.sleep(1000);
							}
							randTime = (int) (Math.random() * (4000 - 1000 + 1)) + 1000;
//							System.out.println(randTime);
							Thread.sleep(randTime);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}).start();
		}
		// 플레이어가 죽었을때 리셋
		public void reset() {
			isReset = true;
			wolves.clear();
			
			count=0;
			floor=0;
			viewPanel.removeAll();
			
			viewPanel.add(pooyan);
			viewPanel.add(laRemainWolf);
			viewPanel.add(laLife);
			viewPanel.add(laScore);
			
			repaint();
			//wolves.removeAll();
			
			pooyan.reset();
			
			if(pooyan.life == 1) {
				laLife.setText("❤");
				
			} else if(pooyan.life == 0) {
				remove(viewPanel);
				add(gameOverPanel);
				gameOverPanel.add(laGameOver);

				
			}

		}
}