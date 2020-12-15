package pooyangame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel{
	private ImageIcon icBomb;
	public boolean isIn = false;
	public int x=0;
	public int y=0;
	public PooyanApp pooyanApp;
	public Pooyan pooyan;
	public Wolf wolf;
	public boolean isKill = false;
	public Bomb(PooyanApp pooyanApp, Pooyan pooyan, Wolf wolf) {
		this.pooyanApp = pooyanApp;
		this.pooyan = pooyan;
		this.wolf = wolf;
		icBomb = new ImageIcon("images/bomb.png");
		setIcon(icBomb);
		setSize(20,20);
		setLocation(0,0);
	}
	public void kill() {
		//System.out.println("pooyan.x:"+pooyan.x);
		if(isKill == false) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					isKill = true;
					while(isKill) {
//						if(pooyan.x==x) {
//							System.out.println("°°À½");
//						} else {
//							System.out.println("dafd");
//						}
					}
					
				}
			}).start();
		}
		
	}
	/*
	 * if(pooyan.x == wolf.bomb.x
	 * 
	 * */
}
