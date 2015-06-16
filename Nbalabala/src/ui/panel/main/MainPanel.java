package ui.panel.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import ui.Images;
import ui.MyFont;
import ui.common.button.ImgButton;
import ui.common.button.TabButton;
import ui.common.panel.Panel;
import ui.controller.MainController;
import utility.Constants;

/**
 * 主界面的Panel
 * @author cylong
 * @version 2015年3月16日 下午7:20:41
 */
public class MainPanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 背景图片 */
	private static Image bgImg = Images.HOME_BG;
	
	private ImgButton settingButton = new ImgButton(Images.SETTING_BUTTON, Images.SETTING_BUTTON_ON);

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
	/** 热点六边形按钮 */
	private Polygon hotPolygon;

	/** 六边形的顶点数 */
//	private int npoints = 6;
	/** 鼠标坐标,开始为(0, 0) */
	private Point mousePoint = new Point();
	
	private TabButton allPlayersBtn;
	private TabButton allTeamsBtn;
	private TabButton gamesDataBtn;
	private TabButton playersDataBtn;
	private TabButton teamsDadaBtn;
	private TabButton hotBtn;
	private TabButton analysisBtn;
	private TabButton liveBtn;
	
	private static final int LEFT_BTN_X = 40;
	private static final int RIGHT_BTN_X = 284;

	public MainPanel() {
//		MyMouseListener listener = new MyMouseListener();

		
		addBtn();

//		addFileSelect();

	}

	/**
	 * 添加页面按钮
	 */
	private void addBtn() {
		String blank = Constants.blank;
		String bigBlank = blank + "     ";
		allPlayersBtn = new TabButton(blank + Constants.allPlayers, Images.HOME_BTN_ON, Images.HOME_BTN_CLICK);
		allPlayersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.toAllPlayersPanel();
				
			}
		});	
		allPlayersBtn.setLocation(LEFT_BTN_X, 298);
		allPlayersBtn.setFont(MyFont.YH_L);
		this.add(allPlayersBtn);
		
		allTeamsBtn = new TabButton(blank + Constants.allTeams, Images.HOME_BTN_ON, Images.HOME_BTN_CLICK);
		allTeamsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.toAllTeamsPanel();				
			}
		});
		allTeamsBtn.setLocation(LEFT_BTN_X, 357);
		allTeamsBtn.setFont(MyFont.YH_L);
		this.add(allTeamsBtn);
		
		gamesDataBtn = new TabButton(Constants.gamesData + bigBlank, Images.HOME_BTN_R_ON, Images.HOME_BTN_R_CLICK);
		gamesDataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.toGamePanel();
			}
		});
		gamesDataBtn.setLocation(RIGHT_BTN_X, 357);
		gamesDataBtn.setFont(MyFont.YH_L);
		this.add(gamesDataBtn);
		
		playersDataBtn = new TabButton(blank + Constants.playersData, Images.HOME_BTN_ON, Images.HOME_BTN_CLICK);
		playersDataBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.toPlayerPanel();
			}
		});
		playersDataBtn.setLocation(LEFT_BTN_X, 416);
		playersDataBtn.setFont(MyFont.YH_L);
		this.add(playersDataBtn);
		
		teamsDadaBtn = new TabButton(blank + Constants.teamsData, Images.HOME_BTN_ON, Images.HOME_BTN_CLICK);
		teamsDadaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.toTeamPanel();
			}
		});
		teamsDadaBtn.setLocation(LEFT_BTN_X, 475);
		teamsDadaBtn.setFont(MyFont.YH_L);
		this.add(teamsDadaBtn);
		
		hotBtn = new TabButton(Constants.hot + bigBlank, Images.HOME_BTN_R_ON, Images.HOME_BTN_R_CLICK);
		hotBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.toHotPanel();
			}
		});
		hotBtn.setLocation(RIGHT_BTN_X, 416);
		hotBtn.setFont(MyFont.YH_L);
		this.add(hotBtn);
		
		analysisBtn = new TabButton(Constants.analysis + blank, Images.HOME_BTN_R_ON, Images.HOME_BTN_R_CLICK);
		analysisBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.toAnalysePanel();
			}
		});
		analysisBtn.setLocation(RIGHT_BTN_X, 475);
		analysisBtn.setFont(MyFont.YH_L);
		this.add(analysisBtn);
		
		liveBtn = new TabButton(Constants.live + bigBlank, Images.HOME_BTN_R_ON, Images.HOME_BTN_R_CLICK);
		liveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.toLiveInPanel();
			}
		});
		liveBtn.setLocation(RIGHT_BTN_X, 298);
		liveBtn.setFont(MyFont.YH_L);
		this.add(liveBtn);
		
		settingButton.setBounds(530, 495, 32, 32);
		settingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Point location = getLocationOnScreen();
				JDialog dialog= new JDialog(MainController.frame, true);
			
				SettingPanel settingPanel = new SettingPanel(dialog);
				int x = location.x + getWidth() / 2 - settingPanel.getWidth()
						/ 2;
				int y = location.y + getHeight() / 2
						- settingPanel.getHeight() / 2;
				dialog.getContentPane().add(settingPanel);
				dialog.setUndecorated(true);
				dialog.setLayout(null);
				dialog.setSize(settingPanel.getSize());
				dialog.setLocation(x, y);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}
		});
		this.add(settingButton);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(bgImg, 0, 0, this);
		super.paint(g);

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
				MainController.toTeamPanel();
			} else if (pdPolygon.contains(mousePoint)) {
				MainController.toPlayerPanel();
			} else if (gdPolygon.contains(mousePoint)) {
				MainController.toGamePanel();
			} else if (apPolygon.contains(mousePoint)) {
				MainController.toAllPlayersPanel();
			} else if (atPolygon.contains(mousePoint)) {
				MainController.toAllTeamsPanel();
			} else if (hotPolygon.contains(mousePoint)) {
				MainController.toHotPanel();
			} else if (analysisBtn.contains(mousePoint)) {
				MainController.toAnalysePanel();
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
