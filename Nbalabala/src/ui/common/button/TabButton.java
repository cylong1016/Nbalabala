package ui.common.button;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import ui.UIConfig;

/**
 * 选项卡标签按钮
 * @author Issac Ding
 * @version 2015年4月27日  上午9:29:47
 */
@SuppressWarnings("serial")
public class TabButton extends JButton{
	
	private Color offColor;
	private Color onColor;
	private boolean isActive = true;
	
	
	/**
	 * @param text 标签名
	 * @param off	非当前选项卡时的背景色
	 * @param on	当前选项卡的背景色
	 * @author Issac Ding
	 * @version 2015年4月27日  上午9:31:37
	 */
	public TabButton(String text, Color off, Color on) {
		this.offColor = off;
		this.onColor = on;
		this.setText(text);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setOpaque(true);
		this.setFocusPainted(false);
		this.setText(text);
		this.setMargin(new Insets(0,0,0,0));
		this.setFont(UIConfig.FONT);
		this.setForeground(Color.white);
		this.setBackground(off);
		this.addMouseListener(new MouseHandler());
	}
	
	public void setOff() {
		setBackground(offColor);
		setEnabled(true);
		isActive = true;
		repaint();
	}
	
	public void setOn() {
		setBackground(onColor);
		setEnabled(false);
		isActive = false;
		repaint();
	}
	
	
    class MouseHandler extends MouseAdapter  {
        public void mouseExited(MouseEvent e){
        	if (isActive) {
        		setBackground(offColor);
                repaint();
        	}
        }
        public void mouseEntered(MouseEvent e){
        	if (isActive) {
        		setBackground(onColor);
                repaint();
        	}
        }
    }
}
