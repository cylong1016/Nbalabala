package ui.common.panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import ui.UIConfig;
import utility.Sleep;

/**
 * 带有左边边框和底层的panel，其他具体功能panel的父类
 * @author lsy
 * @version 2015年3月17日 下午6:21:08
 */
public class BottomPanel extends Panel {

	private static final long serialVersionUID = 7108387309547483359L;
	/** 背景图片 */
	protected Image bgImage;
	
	/** 鼠标移动到左边出现边框的提示 */ // 不需要了
	// private Image slider = new ImageIcon(UIConfig.IMG_PATH + "sidebar/slider.png").getImage();

	/** 左边导航栏 */
	private RightPanel RightPanel;

	/** 画笔透明度 */
	protected float hyaline = 1.0f;
	
	public BottomPanel(String url) {
		bgImage = new ImageIcon(url).getImage();
		this.addRightPanel(this);
		this.setBackground(Color.black);
		// this.addMouseMotionListener(new MouListener()); // 不需要移出的效果
		// new Opacity().start(); // 透明渐变效果
	}

	public void paint(Graphics g) {
		Graphics2D g2d = getPaintbrush(g);
		g2d.drawImage(bgImage, 0, 0, this);
		// g2d.drawImage(slider, 0, 0, this);
		super.paint(g2d);
	}

	public Graphics2D getPaintbrush(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D)g;
		if (hyaline > 1) {
			hyaline = 1;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
		return g2;
	}

	/**
	 * 增加画笔的透明度
	 * @author cylong
	 * @version 2015年3月18日 下午12:03:30
	 */
	public void addHyaline() {
		hyaline += 0.025f;
		if (hyaline > 1) {
			hyaline = 1;
		}
	}

	protected class Opacity extends Thread {
		@Override
		public void run() {
			while(true) {
				Sleep.sleep(10);
				BottomPanel.this.addHyaline();
				BottomPanel.this.repaint();
				if (BottomPanel.this.hyaline == 1) {
					break;
				}
			}
		}
	}

	class MouListener extends MouseAdapter {

		public void mouseMoved(MouseEvent e) {
			if (e.getX() > 0 && e.getX() < UIConfig.PROMPT_WIDTH) {
				toLeftPanel();
			} else if (e.getX() > UIConfig.RIGHT_WIDTH && e.getX() < UIConfig.WIDTH) {
				outLeftPanel();
			}

		}

	}

	public void addRightPanel(BottomPanel panel) {
		RightPanel = new RightPanel(panel);
		this.add(RightPanel);
	}

	public void toLeftPanel() {
		RightPanel.moveIn();
		this.repaint();
	}

	public void outLeftPanel() {
		RightPanel.moveOut();
		this.repaint();
	}

}
