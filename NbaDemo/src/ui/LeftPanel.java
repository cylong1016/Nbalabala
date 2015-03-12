package ui;

import javax.swing.JFrame;

public class LeftPanel extends FatherPanel {

	private int secPanelX = -205, secPanelY = 0, secPanelWidth = 205, secPanelHeight = 555;
	private ThreadIn threadIn;
	private ThreadOut threadOut;
	private int speed = 5;
	
	public LeftPanel(JFrame frame, String url, UIController controller) {
		super(frame, url, controller);
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(secPanelX, secPanelY, secPanelWidth, secPanelHeight);
	}

	public void moveIn(){
		threadIn=new ThreadIn();
		threadIn.start();
	}
	
	public void moveOut(){
		threadOut = new ThreadOut();
		threadOut.start();
	}
	
	class ThreadIn extends Thread{
		
		public void run(){
			while(true){
				try {
					secPanelX = secPanelX + speed;
					LeftPanel.this.setLocation(secPanelX, 0);
					Thread.sleep(5);
					LeftPanel.this.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(secPanelX >= 0){
					break;
				}
			}
		}
	}
	
	class ThreadOut extends Thread{

		public void run(){
			while(true){
				try {
					secPanelX = secPanelX - speed;
					LeftPanel.this.setLocation(secPanelX, 0);
					LeftPanel.this.repaint();
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(secPanelX <= -205){
					break;
				}
			}
		}
	}
	
}
