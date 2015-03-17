package ui.FatherPanel;


/**
 * 左边边框
 * @author lsy
 * @version 2015年3月17日  下午6:19:16
 */
public class LeftPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int secPanelX = -189, secPanelY = 0, secPanelWidth = 189, secPanelHeight = 600;
	private ThreadIn threadIn;
	private ThreadOut threadOut;
	private int speed = 5;
	
	public LeftPanel(String url) {
		super(url);
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
