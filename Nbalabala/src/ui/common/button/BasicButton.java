package ui.common.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * 显示文字的button
 * 
 * @author lsy
 * @version 2015年3月18日 下午10:42:39
 */
public class BasicButton extends JButton {
	/** 横坐标 纵坐标 宽度 高度 */
	private int x, y, width, height;

	/** button上显示的文字 */
	private String text;

	public BasicButton(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;

		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setBounds(x, y, width, height);
		this.setText(text);
		this.setFont(new Font("微软雅黑", 0, 14));
		this.addMouseListener(new MouListener());

	}

	class MouListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			BasicButton.this.setBackground(new Color(15, 24, 44));
			BasicButton.this.setForeground(Color.WHITE);
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

	}

//	public void paintComponent(Graphics gr) {
//		gr.setColor(getBackground());// 设置当前颜色为背景色
//		gr.fillRect(0, 0, getWidth(), getHeight());// 填充
//	}

}
