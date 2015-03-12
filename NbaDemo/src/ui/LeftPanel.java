package ui;

import javax.swing.JFrame;

public class LeftPanel extends FatherPanel{

	private int secPanelX = 0, secPanelY = 0, secPanelWidth = 205, secPanelHeight = 555;

	public LeftPanel(JFrame frame,String url,UIController controller) {
		super(frame,url,controller);
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(secPanelX, secPanelY, secPanelWidth, secPanelHeight);
	}
}
