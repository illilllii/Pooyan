package pooyangame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PooyanApp extends JFrame implements Initable {

	private PooyanApp pooyanApp = this;

	private static final String TAG = "PooyanApp : ";
	public int floor = 0;
	public int count = 0;

	private JLabel laBackground;
	private Wolf wolf;
	private Pooyan pooyan;
	ArrayList<Wolf> wolves;

	public JLabel laRemainWolf;
	public int remainWolf = 32;

	public int randTime; // 늑대 생성 간격 랜덤 시간
	public int randWolf; // 늑대 생성 수 랜덤

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
		wolves = new ArrayList<Wolf>();
		//wolf = new Wolf(pooyanApp, pooyan);
		pooyan = new Pooyan(pooyanApp, wolf);
		laRemainWolf = new JLabel();

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
		laRemainWolf.setText("" + remainWolf);
		laRemainWolf.setSize(30, 30);
		laRemainWolf.setLocation(10, 10);
		laRemainWolf.setFont(new Font("Serif", Font.BOLD, 30));
		laRemainWolf.setForeground(Color.WHITE);
	}

	@Override
	public void batch() {
		add(pooyan);
		wolfAdd(); // 늑대 생성
		//wolfDie();
		getContentPane().add(laRemainWolf);
	}

	@Override
	public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					pooyan.moveUp();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					pooyan.moveDown();
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					pooyan.isShoot = true;
					pooyan.shoot();

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					pooyan.isUp = false;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					pooyan.isDown = false;
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					System.out.println("keyReleased");
					pooyan.isShoot = false;
//						pooyan.isItem = false;
					pooyan.shoot();

				}
			}

		});

	}

	public void wolfAdd() {
		new Thread(new Runnable() {
			public void run() {
				while (remainWolf > 0) {
					try {
						randWolf = (int) (Math.random() * 4) + 1;
						System.out.println(randWolf);
						for (int i = 0; i < randWolf; i++) {
							wolves.add(new Wolf(pooyanApp, pooyan));
							getContentPane().add(wolves.get(count));
							count = wolves.size();
//								System.out.println("늑대 " + count);
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

	public void wolfDie() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					//System.out.println("dfasf");
					System.out.println(wolves.size());

				}


			}
		}).start();
	}
}