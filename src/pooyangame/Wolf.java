package pooyangame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;

import lombok.Data;

@Data
public class Wolf extends JLabel {
	private static final long serialVersionUID = 1L;
	public Wolf wolf = this;
	private final static String TAG = "Wolf : ";

	private PooyanApp pooyanApp;
	private Pooyan pooyan;
	private int floor = 0;
	private ImageIcon iconWolfM4, iconWolfM5, iconWalkWolfR, iconAttackStayWolf, iconAttackStayWolfR, iconAttackWolf1,
			iconAttackWolf2, iconBallonMint, iconBallonMintPop1, iconBallonMintPop2, iconBallonMintPop3,
			iconFallingWolf1, iconFallingWolf2, iconDieWolf;
	private JLabel laScoreWolf;
	public int x = 0;
	public int y = -30;
	public boolean isDown = false;
	public boolean isRight = false;
	public boolean isRightGround = false;
	public boolean isUp = false;
	public boolean isAttack = false;
	public boolean isAttackColision = false;
	public boolean isDie = false;
	public boolean isBomb = false;
	public Bomb bomb;
	public int bombX = 0;
	public int bombY = 0;
	public boolean wolfStatus = true;
	public boolean isAttackBomb = false;

	public int bombVx = 30;
	public int bombVy = 0;
	public int g = 1;
	public int dieCount;
	public int rand;
	private int randBomb;
	private int randBombLocation;
	public int timer = 0;

	public JLabel laBallonMint;

	public Wolf(PooyanApp pooyanApp, Pooyan pooyan) {
		this.pooyanApp = pooyanApp;
		this.pooyan = pooyan;
		bomb = new Bomb();
		iconWolfM4 = new ImageIcon("images/WolfMint4.png");
		iconWolfM5 = new ImageIcon("images/WolfMint5.png");
		iconWalkWolfR = new ImageIcon("images/walkWolfR.gif");
		iconAttackStayWolf = new ImageIcon("images/attackStayWolf.gif");
		iconAttackStayWolfR = new ImageIcon("images/attackStayWolfR.png");
		iconAttackWolf1 = new ImageIcon("images/attackWolf1.png");
		iconAttackWolf2 = new ImageIcon("images/attackWolf2.png");
		iconBallonMint = new ImageIcon("images/ballonMint.png");
		iconBallonMintPop1 = new ImageIcon("images/ballonMintPop1.png");
		iconBallonMintPop2 = new ImageIcon("images/ballonMintPop2.png");
		iconBallonMintPop3 = new ImageIcon("images/ballonMintPop3.png");
		iconFallingWolf1 = new ImageIcon("images/fallingWolf1.png");
		iconFallingWolf2 = new ImageIcon("images/fallingWolf2.png");
		iconDieWolf = new ImageIcon("images/dieWolf.png");
		laBallonMint = new JLabel(iconBallonMint);
		laScoreWolf = new JLabel();
		setIcon(iconWolfM4);
		setSize(130, 130);
		setLocation(x, y);
		laScoreWolf.setText(" ");
		laScoreWolf.setSize(70,30);
		laScoreWolf.setLocation(x, y);
		laScoreWolf.setFont(new Font("Serif", Font.BOLD, 30));
		laScoreWolf.setForeground(Color.WHITE);
		//laScoreWolf.setVisible(false);
		rand = (int) (Math.random() * 300) + 20;
		moveRight();
	}

 	public void bombAttack() {
		if (isAttackBomb == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
//					bomb.x = 0;
//					bomb.y = 0;
					isAttackBomb = true;

					bomb.x = wolf.x + 20;
					bomb.y = wolf.y + 40;

					bombX = bomb.x;
					bombY = bomb.y;

					bomb.setLocation(bombX, bombY);

					randBomb = (int) (Math.random() * 4) + 1;
					System.out.println("randBomb" + randBomb);

					if (randBomb == 2 || randBomb == 1) {
						pooyanApp.viewPanel.add(bomb);
						setIcon(iconWalkWolfR);
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						isBomb = true;
						while (isAttackBomb) {
							if (isBomb) {
								setIcon(iconWolfM4);
								isBomb = false;
							}

							bombVy = bombVy + g;
							bombX = bombX + bombVx;
							bombY = bombY + bombVy;
							//bombX++;
							bomb.setLocation(bombX, bombY);
							
							if (bombX >= 490) {
								if(bombY-50<=pooyan.y && bombY+50>=pooyan.y) {
									//reset();
									pooyanApp.viewPanel.remove(bomb);
									pooyan.die();
//									if(pooyan.isDie) {
//										
//									}
									//pooyanApp.viewPanel.remove(bomb);
									
									break;
								} 
								
								
							}
							
							if (bombX >= 600) {
								pooyanApp.viewPanel.remove(bomb);
								break;
							}

							
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// bomb.kill();

						}
					}

				}
			}).start();
		}
	}

	public void moveFall() {

		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					setIcon(iconWolfM4);

					while (isDown) {
						
						if (pooyan.y == wolf.y+30) {
							bombAttack();
						}

						if (y > 490) {
							isDown = false;
							isRightGround = true;
							// pooyanApp.remove(bomb);
							wolf.moveRight();
							setIcon(iconWalkWolfR);

							break;
						}
						if (wolfStatus == false) {
							break;

						}
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(5);
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

	public void moveRight() {
//		System.out.println(TAG + "moveRight");
		new Thread(new Runnable() {
			@Override
			public void run() {
				isRight = true;
				setIcon(iconWalkWolfR);

				while (isRight) {
					if (y == -30) { // 위에서 이동
						//wolfStatus = false;
						if (x >= rand) {
							isRight = false;
							//wolfStatus = false;
							moveFall();
							break;
						}
						x++;
						setLocation(x, y);

						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (y > 490 && wolfStatus == true) { // 밑에서 이동
						if (x > 540) {
							isRight = false;
							if (pooyanApp.floor < 4) {
								isUp = true;
								pooyanApp.floor = pooyanApp.floor + 1;
								floor = pooyanApp.floor;
								wolf.moveUP();
								break;
							} else {
								pooyanApp.viewPanel.remove(wolf);
								pooyanApp.wolves.remove(wolf);
								pooyanApp.viewPanel.repaint();
								pooyanApp.count--;
								System.out.println("늑대 " + pooyanApp.wolves.size());
								break;
							}

						}
						x++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	public void moveUP() {
		System.out.println(TAG + "moveUp");

		new Thread(new Runnable() {
			@Override
			public void run() {
				setIcon(iconAttackStayWolf);
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

	public void attackedFall() {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				laScoreWolf.setLocation(wolf.x, wolf.y);
				while (true) {
					try {
						timer++;
						System.out.println(TAG+"timer"+timer);
						laScoreWolf.setText(Integer.toString(pooyan.score));
						 
						pooyanApp.viewPanel.add(laScoreWolf);
						if(timer >= 20) pooyanApp.viewPanel.remove(laScoreWolf);;

						setIcon(iconFallingWolf1);
						y = y + 3;
						Thread.sleep(20);
						setIcon(iconFallingWolf2);
						y = y + 3;
						Thread.sleep(20);
						setLocation(x, y);
						if (y > 490) {
							setIcon(iconDieWolf);
							Thread.sleep(1000);
							pooyanApp.viewPanel.remove(wolf);
							pooyanApp.viewPanel.repaint();
							pooyanApp.wolves.remove(wolf);
							pooyanApp.count--;
							pooyanApp.remainWolf--;
							System.out.println(TAG + " " + pooyanApp.count);
							pooyanApp.laRemainWolf.setText("" + pooyanApp.remainWolf);
							break;
						}
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
				setIcon(iconAttackStayWolfR);
				while (isAttack) {
					try {
						
						System.out.println(TAG + "공격");
						Thread.sleep(5000); // 5초마다 공격
						setIcon(iconAttackWolf1);
						x = 500;
						setLocation(x, y);
						if (x <= pooyan.x + 50) {
							if (y + 30 >= pooyan.y && y + 30 <= pooyan.y + 50) {
								pooyan.die();
								wolfStatus = false;
//								if(pooyan.isDie==true) {
//									pooyanApp.reset();
//								}
								// pooyanApp.reset();
								// break;
							}
						}
						Thread.sleep(800); // 공격 모션 딜레이
						setIcon(iconAttackWolf2);
						// 플레이어 좌표가 울프의 근접공격 좌표와 같아지면 플레이어 죽음

						if (wolfStatus == false) {
							break;
						}
						Thread.sleep(800); // 공격 모션 딜레이
						setIcon(iconAttackStayWolfR);
						x = 541;
						setLocation(x, y);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}).start();
	}

}