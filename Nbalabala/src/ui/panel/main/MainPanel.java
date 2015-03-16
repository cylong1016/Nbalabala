package ui.panel.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import ui.common.panel.Panel;

/**
 * 主界面的Panel
 * @author cylong
 * @version 2015年3月16日 下午7:20:41
 */
public class MainPanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 六边形按钮 */
	private Polygon polygon;
	/** 鼠标坐标,开始为(0, 0) */
	Point mousePoint = new Point();

	public MainPanel() {
		int len = (int)(30 * Math.pow(2, 0.5));
		int[] xpoints = {30, 30 + len, 60 + len, 30 + len, 30, 0};
		int[] ypoints = {0, 0, 30, (int)(30 * Math.pow(6, 0.5)), (int)(30 * Math.pow(6, 0.5)), 30};
		int npoints = 6;
		polygon = new Polygon(xpoints, ypoints, npoints);
		this.addMouseMotionListener(new MyMouseListener());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (polygon.contains(mousePoint)) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.BLACK);
		}
		g.drawPolygon(polygon);
	}

	class MyMouseListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			mousePoint = new Point(x, y);
			repaint();
		}

	}

}
