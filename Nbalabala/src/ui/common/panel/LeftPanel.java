package ui.common.panel;

import ui.UIConfig;

/**
 * 左边边框
 * @author lsy
 * @version 2015年3月17日 下午6:19:16
 */
public class LeftPanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private int secPanelX = UIConfig.LEFT_WIDTH * (-1);
	private ThreadIn threadIn;
	private ThreadOut threadOut;
	/** 边框移动速度 */
	private int speed = 5;

	public LeftPanel() {
		this.setBounds(secPanelX, 0, UIConfig.LEFT_WIDTH, UIConfig.WIDTH);
	}

	public void moveIn() {
		threadIn = new ThreadIn();
		threadIn.start();
	}

	public void moveOut() {
		threadOut = new ThreadOut();
		threadOut.start();
	}

	class ThreadIn extends Thread {

		public void run() {
			while(true) {
				if (secPanelX >= 0) {
					break;
				}
				try {
					secPanelX = secPanelX + speed;
					LeftPanel.this.setLocation(secPanelX, 0);
					Thread.sleep(5);
					LeftPanel.this.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class ThreadOut extends Thread {

		public void run() {
			while(true) {
				if (secPanelX <= secPanelX) {
					break;
				}
				try {
					secPanelX = secPanelX - speed;
					LeftPanel.this.setLocation(secPanelX, 0);
					LeftPanel.this.repaint();
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
