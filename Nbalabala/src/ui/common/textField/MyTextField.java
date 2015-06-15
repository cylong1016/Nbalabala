package ui.common.textField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.border.Border;

import ui.MyFont;
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
	
	public void setSettingField(){
		this.setOpaque(true);
		this.setBackground(Color.LIGHT_GRAY);
		this.setForeground(MyFont.DARK_GRAY);
//		this.setBorder(new );
		
		
	}
}

