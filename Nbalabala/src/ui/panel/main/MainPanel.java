package ui.panel.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import ui.UIConfig;
import ui.common.panel.Panel;
import ui.controller.MainController;

/**
 * 主界面的Panel
 * @author cylong
 * @version 2015年3月16日 下午7:20:41
 */
public class MainPanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 主界面的图片url */
	private static String imgUrl = UIConfig.imgPath + "main/";

	/** 背景图片 */
	private static Image bgImg = new ImageIcon(imgUrl + "main.png").getImage();
	/** 主界面5个按钮的图片 */
	private Image teamDataImg = new ImageIcon(imgUrl + "teamData.png").getImage();
	private Image playerDataImg = new ImageIcon(imgUrl + "playerData.png").getImage();
	private Image gameDataImg = new ImageIcon(imgUrl + "gameData.png").getImage();
	private Image allPlayersImg = new ImageIcon(imgUrl + "allPlayers.png").getImage();
	private Image allTeamsImg = new ImageIcon(imgUrl + "allTeams.png").getImage();

	/** 球队数据六边形按钮 */
	private Polygon tdPolygon;
	/** 球员数据六边形按钮 */
	private Polygon pdPolygon;
	/** 球比赛数据六边形按钮 */
	private Polygon gdPolygon;
	/** 全部球员数据六边形按钮 */
	private Polygon apPolygon;
	/** 全部球队数据六边形按钮 */
	private Polygon atPolygon;

	/** 六边形的顶点数 */
	private int npoints = 6;
	/** 鼠标坐标,开始为(0, 0) */
	private Point mousePoint = new Point();

	private MainController controller;

	public MainPanel(MainController controller) {
		this.controller = controller;
		MyMouseListener listener = new MyMouseListener();

		// 球队数据按钮
		int[] tdxpoints = {488, 568, 568, 488, 407, 407};
		int[] tdypoints = {400, 447, 539, 586, 539, 447};
		tdPolygon = new Polygon(tdxpoints, tdypoints, npoints);

		// 球员数据按钮
		int[] pdxpoints = {651, 731, 731, 652, 571, 571};
		int[] pdypoints = {401, 447, 539, 586, 539, 447};
		pdPolygon = new Polygon(pdxpoints, pdypoints, npoints);

		// 比赛数据按钮
		int[] gdxpoints = {732, 813, 813, 732, 652, 652};
		int[] gdypoints = {259, 306, 399, 445, 399, 306};
		gdPolygon = new Polygon(gdxpoints, gdypoints, npoints);

		// 全部球员数据按钮
		int[] apxpoints = {814, 895, 895, 814, 734, 734};
		int[] apypoints = {119, 165, 257, 303, 257, 165};
		apPolygon = new Polygon(apxpoints, apypoints, npoints);

		// 全部球队数据按钮
		int[] atxpoints = {897, 977, 977, 897, 816, 816};
		int[] atypoints = {260, 306, 399, 445, 399, 306};
		atPolygon = new Polygon(atxpoints, atypoints, npoints);

		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(bgImg, 0, 0, this);
		super.paint(g);
		if (tdPolygon.contains(mousePoint)) {
			g.drawImage(teamDataImg, 408, 400, this);
		} else if (pdPolygon.contains(mousePoint)) {
			g.drawImage(playerDataImg, 571, 401, this);
		} else if (gdPolygon.contains(mousePoint)) {
			g.drawImage(gameDataImg, 652, 260, this);
		} else if (apPolygon.contains(mousePoint)) {
			g.drawImage(allPlayersImg, 734, 118, this);
		} else if (atPolygon.contains(mousePoint)) {
			g.drawImage(allTeamsImg, 816, 260, this);
		}
	}

	/**
	 * 主界面的鼠标监听，实现界面跳转和按钮效果
	 * @author cylong
	 * @version 2015年3月18日 上午10:48:36
	 */
	class MyMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (tdPolygon.contains(mousePoint)) {
				controller.toTeamPanel(MainPanel.this);
			} else if (pdPolygon.contains(mousePoint)) {
				controller.toPlayerPanel(MainPanel.this);
			} else if (gdPolygon.contains(mousePoint)) {
				controller.toGamePanel(MainPanel.this);
			} else if (apPolygon.contains(mousePoint)) {
				controller.toAllPlayersPanel(MainPanel.this);
			} else if (atPolygon.contains(mousePoint)) {
				controller.toAllTeamsPanel(MainPanel.this);
			}
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
