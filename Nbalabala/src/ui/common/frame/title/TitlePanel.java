package ui.common.frame.title;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.common.button.ImgButton;
import ui.common.frame.Frame;

/**
 * frame上的标题栏
 * @author cylong
 * @version 2015年3月16日 上午12:49:52
 */
public class TitlePanel extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5595756092214353675L;

	/*----------------------------标题配置--------------------------------*/
	/** 标题背景颜色 */
	public static Color TITLE_BACK_COLOR = new Color(0x2D2D30);
	/** 按钮与按钮之间、按钮与边框的间距 */
	private int interval = 8;
	/*----------------------------标题配置--------------------------------*/

	/** 关闭按钮 */
	private ImgButton exit;
	/** 最小化按钮 */
	private ImgButton min;
	/** 主frame，主要为了最小化使用其对象 */
	private Frame frame;

	public TitlePanel(Frame frame) {
		this.frame = frame;
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, interval, interval));
		this.setOpaque(true);
		this.setBackground(TITLE_BACK_COLOR);
		addTitleButton(); // 添加最大化最小化按钮
	}

	/**
	 * paintComponent 在里面画背景图片的时候会很方便，不会覆盖掉关闭按钮
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2d);
	}

	private void addTitleButton() {
		// 按钮监听
		TitleButtonListener listener = new TitleButtonListener();
		// 最小化按钮
		min = new ImgButton("images/mini.png", "images/miniOn.png");
		min.addMouseListener(listener);
		min.setPreferredSize(new Dimension(15,15));
		this.add(min);
		// 关闭按钮
		exit = new ImgButton("images/close.png", "images/closeOn.png");
		exit.addMouseListener(listener);
		exit.setPreferredSize(new Dimension(15,15));
		this.add(exit);
	}

	private class TitleButtonListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == exit) {
				System.exit(0);
			} else if (e.getSource() == min) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		}

	}

}
