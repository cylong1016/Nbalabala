package ui.FatherPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import ui.UIConfig;

/**
 * 带有左边边框和底层的panel，其他具体功能panel的父类
 * 
 * @author lsy
 * @version 2015年3月17日 下午6:21:08
 */
public class FatherPanel extends BottomPanel {
	private static final long serialVersionUID = 7108387309547483359L;
	private LeftPanel leftPanel;

	public FatherPanel(String url) {
		super(url);
		this.addLeftPanel();
		this.addMouseMotionListener(new MouListener());

	}

	class MouListener implements MouseMotionListener {

		public void mouseDragged(MouseEvent e) {

		}

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
		leftPanel = new LeftPanel("Image/2.png");
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
