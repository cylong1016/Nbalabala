package ui;

public class UIController {

	private MyFrame frame;
	private MainPanel mainPanel;
	
	public UIController(){
		this.frame = new MyFrame();
		this.mainPanel();
	}

	private void mainPanel() {
		mainPanel = new MainPanel(frame,"Image/0.png",this);
		frame.setPanel(mainPanel);
	}
	
	
}
