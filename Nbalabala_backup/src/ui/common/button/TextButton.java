package ui.common.button;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import ui.UIConfig;

/**
 * 显示文字的button
 * @author lsy
 * @version 2015年3月18日 下午10:42:39
 */
public class TextButton extends JButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -5390884444460444968L;

	/**
	 * @param x 横坐标
	 * @param y 纵坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param text button显示的文字
	 * @author cylong
	 * @version 2015年3月19日 上午2:48:41
	 */
	public TextButton(int x, int y, int width, int height, String text) {
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBounds(x, y, width, height);
		this.setText(text);
		this.setMargin(new Insets(0,0,0,0));
		this.setFont(UIConfig.FONT);
		addListener();
	}
	
	public void addListener(){
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				TextButton.this.setOpaque(true);
				TextButton.this.setForeground(Color.white);
				TextButton.this.setBackground(UIConfig.BUTTON_COLOR);
			}
		});
	}

	public void back() {
		this.setOpaque(false);
		this.setForeground(Color.black);
	}
}
