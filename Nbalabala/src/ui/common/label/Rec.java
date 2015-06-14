package ui.common.label;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * 矩形label
 * @author lsy
 * @version 2015年5月17日  下午12:25:23
 */
public class Rec extends JLabel{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int height = 40;
	private int width,x,y;
	private double length;
	
	public Rec(int x, int y,double length){
		this.length = length;
		this.x = x;
		this.y = y;
		width = (int) length;
		this.setBounds(x, y, 4 * width, height);
		this.setOpaque(true);
		this.setBackground(Color.blue);
	}
	
	public void setLength(int width) {
		this.setBounds(x,y,(int)4*width,height);
	}

	public double getLength() {
		return length;
	}
	
}
