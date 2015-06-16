package ui.common.textField;

import java.awt.Color;

import javax.swing.JTextField;

import ui.UIConfig;

/**
 * 文本框
 * @author lsy
 * @version 2015年3月20日  下午6:32:34
 */
public class MyTextField extends JTextField{

	/** serialVersionUID */
	private static final long serialVersionUID = -3458532595643157676L;

	public MyTextField(int x, int y,int width,int height) {
		this.setLocation(x, y);
		this.setSize(width, height);
		this.setOpaque(false);
		
		this.setFont(UIConfig.FONT);
		this.setForeground(Color.white);
		this.setBackground(null);
		this.setBorder(null);
	}
}

