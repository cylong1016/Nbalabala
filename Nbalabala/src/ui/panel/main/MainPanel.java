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
import javax.swing.JFileChooser;

import ui.Images;
import ui.MyFont;
import ui.UIConfig;
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

	/** 主界面的图片url */
	private static String imgUrl = UIConfig.IMG_PATH + "main/";

	/** 背景图片 */
	private static Image bgImg = Images.HOME_BG;

	private ImgButton fileSelect;
	
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
	private int npoints = 6;
	/** 鼠标坐标,开始为(0, 0) */
	private Point mousePoint = new Point();
	
	private TabButton allPlayersBtn;
	private TabButton allTeamsBtn;
	private TabButton gamesDataBtn;
	private TabButton playersDataBtn;
	private TabButton teamsDadaBtn;
	private TabButton hotBtn;
	private TabButton analysisBtn;
	
	private static final int LEFT_BTN_X = 40;
	private static final int RIGHT_BTN_X = 284;

	public MainPanel() {
//		MyMouseListener listener = new MyMouseListener();

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

		//热点按钮
		int[] hotxpoints = {244, 244, 324, 404, 404, 324};
		int[] hotypoints = {540, 446, 401, 446, 540, 586};
		hotPolygon = new Polygon(hotxpoints, hotypoints, npoints);

//		this.addMouseListener(listener);
//		this.addMouseMotionListener(listener);
		
		addBtn();

		addFileSelect();
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
				// TODO
			}
		});
		analysisBtn.setLocation(RIGHT_BTN_X, 475);
		analysisBtn.setFont(MyFont.YH_L);
		this.add(analysisBtn);
		
		settingButton.setLocation(444, 444); //TODO 坐标放哪呀
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
	}

	/**
	 * 添加文件夹选择按钮
	 * @author lsy
	 * @version 2015年4月11日 下午8:44:18
	 */
	public void addFileSelect() {
		fileSelect = new ImgButton(imgUrl + "fileSelect.png", 911, 461, imgUrl + "fileSelectOn.png", imgUrl + "fileSelect.png");
		this.add(fileSelect);
		fileSelect.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setDialogTitle("选择数据文件夹");
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getAbsolutePath();
					Constants.changeDataSourcePath(path);
				}
			}
			
		});
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
