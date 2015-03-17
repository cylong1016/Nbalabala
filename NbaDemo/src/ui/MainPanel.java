package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author lsy
 * @version 2015年3月12日下午3:32:56
 */
public class MainPanel extends FatherPanel {

	private static final long serialVersionUID = 7108387309547483359L;
	private JButton button;
	private LeftPanel leftPanel;
	private JFrame frame;
	private UIController controller;
	private int x, y;

	public MainPanel(JFrame frame, String url, UIController controller) {
		super(frame, url, controller);
		this.frame = frame;
		this.controller = controller;
		this.addLeftPanel();
		this.addMouseMotionListener(new MouListener());
	}

	class MouListener implements MouseMotionListener {

		int i = 0;

		/**
		 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
		 */
		public void mouseDragged(MouseEvent e) {

		}

		/**
		 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
		 */
		public void mouseMoved(MouseEvent e) {
			if (e.getX() > 0 && e.getX() < 60) {
				toLeftPanel();
			}else if(e.getX()>205 && e.getX()<600){
				outLeftPanel();
			}

		}

	}

	public void addLeftPanel() {
		leftPanel = new LeftPanel(frame, "Image/2.png", controller);
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
