package ui.common.label;

import javax.swing.JLabel;

import ui.MyFont;
import ui.UIConfig;

/**
 * 带有文字的label
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
		this.setHorizontalAlignment(CENTER);
		this.setForeground(MyFont.BLACK_GRAY);
		this.text = text;
		setlbText(text);
	}
	
	public void setlbText(String text){
		this.setText(text);
	}
	
	public MyLabel() {
		this.setOpaque(false);
		this.setFont(UIConfig.FONT);
		this.setHorizontalAlignment(LEFT);
	}
	
	public MyLabel(String text) {
		this();
		setText(text);
	}
	
	public void setLeft(){
		this.setHorizontalAlignment(LEFT);
	}
	
	public void setRight(){
		this.setHorizontalAlignment(RIGHT);
	}
	
	public void setCenter(){
		this.setHorizontalAlignment(CENTER);
	}
}
