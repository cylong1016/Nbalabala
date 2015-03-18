package ui.common.button;

import java.awt.Color;
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

	/* 没用的代码，删掉 */
	//	/** 横坐标 纵坐标 宽度 高度 */
	//	private int x, y, width, height;
	//
	//	/** button上显示的文字 */
	//	private String text;

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
		//		this.x = x;
		//		this.y = y;
		//		this.height = height;
		//		this.width = width;
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setBounds(x, y, width, height);
		this.setText(text);
		this.setFont(UIConfig.FONT);
		this.addMouseListener(new MouListener());

	}

	// TODO 继承 MouseAdapter更好
	class MouListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			TextButton.this.setBackground(new Color(15, 24, 44));
			TextButton.this.setForeground(Color.WHITE);
		}

	}

}
