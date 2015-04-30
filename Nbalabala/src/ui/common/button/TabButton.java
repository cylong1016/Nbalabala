package ui.common.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import ui.UIConfig;

/**
 * 选项卡标签按钮
 * @author Issac Ding
 * @version 2015年4月27日  上午9:29:47
 */
@SuppressWarnings("serial")
public class TabButton extends JButton{
	
	
	private Image moveOn;
	private Image chosen;
	private String text;
	private int width;
	private int height;
	private boolean isActive = true;
	private boolean isMouseOn = false;
	private int textX;
	private int textY;
	
	
	/**
	 * @param text 标签名
	 * @param off	非当前选项卡时的背景色
	 * @param on	当前选项卡的背景色
	 * @author Issac Ding
	 * @version 2015年4月27日  上午9:31:37
	 */
	public TabButton(String text, Image moveOn, Image chosen) {
		this.text = text;
		this.width = moveOn.getWidth(null);
		this.height = moveOn.getHeight(null);
		this.moveOn = moveOn;
		this.chosen = chosen;
		
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setContentAreaFilled(true);
		this.setOpaque(false);
		this.setFocusPainted(false);
		this.setMargin(new Insets(0,0,0,0));
		this.setFont(UIConfig.FONT);
		this.setForeground(Color.white);
		this.setSize(width, height);
		
		JLabel label = new JLabel(text);
		label.setFont(UIConfig.FONT);
		Dimension textSize = label.getPreferredSize();
		textX = (int)(width - textSize.getWidth()) / 2;
		textY = (int)(height - textSize.getHeight() / 2) - 3;
		
		this.addMouseListener(new MouseHandler());
	}
	
	public void setOff() {
		isActive = true;
		setEnabled(true);
		repaint();
	}
	
	public void setOn() {
		isActive = false;
		setEnabled(false);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		if (!isActive) {
			g.drawImage(chosen,0,0,width,height,null);
		}else if(isMouseOn){
			g.drawImage(moveOn,0,0,width,height,null);
		}
		g.setFont(UIConfig.FIRST_LEVEL_TAB_FONT);
		g.setColor(Color.WHITE);
		g.drawString(text, textX, textY);
	}
	
	class MouseHandler extends MouseAdapter{
		public void mouseEntered(MouseEvent e) {
			isMouseOn = true;
			repaint();
		}
		public void mouseExited(MouseEvent e) {
			isMouseOn = false;
			repaint();
		}
	}
	
	
	
}
