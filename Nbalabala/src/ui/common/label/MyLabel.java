package ui.common.label;

import javax.swing.JLabel;

import ui.UIConfig;

/**
 * @author lsy
 * @version 2015年3月22日 下午5:47:50
 */
public class MyLabel extends JLabel {

	/** serialVersionUID */
	private static final long serialVersionUID = 3349797377173717184L;
	public String text;

	public MyLabel(int x, int y, int width, int height, String text) {
		this.setBounds(x, y, width, height);
		this.setOpaque(false);
		this.setFont(UIConfig.FONT);
		this.setText(text);
		this.setHorizontalAlignment(CENTER);
		this.text = text;
	}
}
