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

import common.Sleep;

/**
 * 带有左边边框和底层的panel，其他具体功能panel的父类
 * @author lsy
 * @version 2015年3月17日 下午6:21:08
 */
public class BottomPanel extends Panel {

	private static final long serialVersionUID = 7108387309547483359L;
	/** 背景图片 */
	protected Image bgImage;

	/** 左边导航栏 */
	private LeftPanel leftPanel;

	/** 画笔透明度 */
	protected float hyaline = 0.4f;

	public BottomPanel(String url) {
		bgImage = new ImageIcon(url).getImage();
		this.addLeftPanel();
		this.addMouseMotionListener(new MouListener());
		new Opacity().start();
	}

	public void paint(Graphics g) {
		Graphics2D g2d = getPaintbrush(g);
		g2d.drawImage(bgImage, 0, 0, this);
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

	private class Opacity extends Thread {

		@Override
		public void run() {
			while(true) {
				Sleep.sleep(20);
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
			} else if (e.getX() > UIConfig.LEFT_WIDTH && e.getX() < UIConfig.WIDTH) {
				outLeftPanel();
			}

		}

	}

	public void addLeftPanel() {
		//TODO 图片待换
		leftPanel = new LeftPanel();
		this.add(leftPanel);
	}

	public void toLeftPanel() {
		leftPanel.moveIn();
		this.repaint();
	}

	public void outLeftPanel() {
		leftPanel.moveOut();
		this.repaint();
	}

}
