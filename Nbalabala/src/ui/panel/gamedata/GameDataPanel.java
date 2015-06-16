package ui.panel.gamedata;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import ui.Images;
import ui.MyFont;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.comboBox.MyComboBox;
import ui.common.date.DateChooser;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchDetailVO;
import bl.matchquerybl.MatchQuery;
import blservice.MatchQueryBLService;

/**
 * 比赛数据的主界面
 * 
 * @author cylong
 * @author lsy
 * @version 2015年5月14日 上午0:05:49
 */
public class GameDataPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -6986506405843542454L;

	private MyLabel number1, number2;
	/** 下拉框 */
	private MyComboBox box1, box2;
	private ImgButton up, down;
	private ProfilePanel[] proPanel;
	/** 下拉框的坐标 宽高 */
	private int box1X = 722, box2X = 863, box1Y = 9, box2Y = 9, boxWidth = 110, boxHeight = 34;

	private MatchQueryBLService matchQuery = new MatchQuery();
	private DateChooser dateChooser;
	private ArrayList<MatchDetailVO> matchDetailfile;
	private int gameNum, pageNum;
	private Boolean isInit = true;
	private ImgButton back;
	private BottomPanel panel;

	/**
	 * @param url
	 *            背景图片的url
	 */
	public GameDataPanel(String url) {
		super(url);
		matchDetailfile = matchQuery.getLatestMatches();
//		matchDetailfile = matchQuery.screenMatchByTeam("PHI", "BOS");
		addComboBox();
		addDateChooser();
		addArray();
		addLabel();
		addButton();
//		addBack();
	}
	
	public GameDataPanel(BottomPanel panel,String url,String teamAbbr1,String teamAbbr2) {
		super(url);
		this.panel = panel;
		matchDetailfile = matchQuery.screenMatchByTeam(teamAbbr1, teamAbbr2);
		addComboBox();
		addDateChooser();
		addArray();
		addLabel();
		addButton();
		addBack();
	}
	
		public void addBack() {
			back = new ImgButton(Images.RETURN_BTN, UIConfig.RETURN_X, UIConfig.RETURN_Y - 10, Images.RETURN_BTN_ON);
			this.add(back);
			back.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					MainController.backToOnePanel(panel);
				}

			});
		}
	

	/**
	 * 设置pageNum和gameNum,添加panel到arrayList中
	 * 
	 * @author lsy
	 * @version 2015年5月14日 上午12:15:00
	 */
	public void addArray() {
		if (!isInit) {
			for (int i = (clickNum - 1) * 3; i < clickNum * 3; i++) {
				this.remove(proPanel[i]);
			}
		}
		clickNum = 1;
		gameNum = matchDetailfile.size();
		pageNum = (int) Math.ceil((double) gameNum / 3);
		proPanel = new ProfilePanel[gameNum];
		if (gameNum % 3 == 1) {
			proPanel = new ProfilePanel[gameNum + 2];
		} else if (gameNum % 3 == 2) {
			proPanel = new ProfilePanel[gameNum + 1];
		} else if (gameNum == 0) {
			proPanel = new ProfilePanel[3];
		}
		for (int j = 0; j < proPanel.length; j++) {
			proPanel[j] = new ProfilePanel();
		}
		int i = 0;
		if (gameNum >= 3) {
			for (i = 0; i < 3; i++) {
				proPanel[i] = (new ProfilePanel(matchDetailfile.get(i),GameDataPanel.this));
			}
		} else if (gameNum == 0) {
			proPanel[0] = new ProfilePanel();
			proPanel[1] = new ProfilePanel();
			proPanel[2] = new ProfilePanel();
		} else{
			for (i = 0; i < gameNum; i++) {
				proPanel[i] = (new ProfilePanel(matchDetailfile.get(i),GameDataPanel.this));
			}
		}
		for (i = 0; i < 3; i++) {
			proPanel[i].setLocation(20, 70 + i * 180);
			this.add(proPanel[i]);
		}
	}

	/**
	 * 添加一些label
	 * 
	 * @author lsy
	 * @version 2015年5月13日 下午6:33:22
	 */
	public void addLabel() {
		number2 = new MyLabel(953, 315, 50, 50, pageNum + "");
		number2.setForeground(Color.white);
		number2.setFont(MyFont.YT_M);
		this.add(number2);

		number1 = new MyLabel(940, 285, 50, 50, "1");
		number1.setForeground(Color.white);
		number1.setFont(MyFont.YT_M);
		this.add(number1);
	}

	public GameDataPanel(String url, int i) {
		super(url);
	}

	public void addPanel(int pageOld, int pageNew) {
		for (int i = (pageOld - 1) * 3; i < pageOld * 3; i++) {
			this.remove(proPanel[i]);
		}
		for (int i = (pageNew - 1) * 3; i < pageNew * 3; i++) {
			proPanel[i].setLocation(20, 70 + (i - (pageNew - 1) * 3) * 180);
			this.add(proPanel[i]);
		}
		this.repaint();
	}

	/**
	 * 添加上下翻页按钮
	 * 
	 * @author lsy
	 * @version 2015年5月13日 下午4:36:29
	 */
	private int upX = 955, upY = 240, downX = 952, downY = 365;
	private int clickNum = 1;

	public void addButton() {
		up = new ImgButton("images2.0/gameData/UpButton.png", upX, upY, "images2.0/gameData/UpButtonOn.png",
				"images2.0/gameData/UpButtonOn.png");
		down = new ImgButton("images2.0/gameData/DownButton.png", downX, downY,
				"images2.0/gameData/DownButtonOn.png", "images2.0/gameData/DownButtonOn.png");
		this.add(up);
		this.add(down);

		down.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int temp = clickNum;
				int i = 0;
				for (i = 3 * clickNum; i < 3 * clickNum + 3 && i < gameNum; i++) {
					if (proPanel[i].isIni == true) {
						proPanel[i] = (new ProfilePanel(matchDetailfile.get(i),GameDataPanel.this));
					}
				}
				clickNum++;
				if (clickNum == pageNum + 1) {
					clickNum = 1;
				}
				addPanel(temp, clickNum);
				number1.setText(clickNum + "");
				GameDataPanel.this.repaint();

			}
		});

		up.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int temp = clickNum;
				int i = 0;

				clickNum--;
				if (clickNum == 0) {
					clickNum = pageNum;
				}
				for (i = 3 * (clickNum - 1); i < 3 * clickNum && i < gameNum; i++) {
					if (proPanel[i].isIni == true) {
						proPanel[i] = (new ProfilePanel(matchDetailfile.get(i),GameDataPanel.this));
					}
				}
				addPanel(temp, clickNum);
				number1.setText(clickNum + "");
				GameDataPanel.this.repaint();
			}
		});

	}

	/**
	 * 添加确认按钮
	 * 
	 * @author lsy
	 * @version 2015年3月21日 下午4:29:48
	 */

	public void paint(Graphics g) {
		super.paint(g);
	}

//	/**
//	 * 分析打了几节
//	 * 
//	 * @author lsy
//	 * @version 2015年3月21日 下午5:15:29
//	 */
//	/** 每节比分 ，格式为“27-25;29-31;13-25;16-31;” */
//	public int analyzeSection(MatchProfileVO pro) {
//		String gameInfo = pro.getEachSectionScore();
//		String[] eachSection = gameInfo.split(";");
//		return eachSection.length;
//	}

	public void refresh() {
		Date date = dateChooser.getDate();
		matchDetailfile = matchQuery.screenMatchByDate(date);
		isInit = false;
		addArray();
		if (pageNum == 0) {
			number2.setlbText("1");
		} else {
			number2.setlbText(pageNum + "");
		}
		number1.setlbText("1");
		GameDataPanel.this.repaint();
	}

	/**
	 * 日期选择器
	 * 
	 * @author lsy
	 * @version 2015年3月21日 下午4:29:57
	 */
	public void addDateChooser() {
		dateChooser = new DateChooser(this);
		MainController.addDateChooserPanel(this, dateChooser, 565, 10, 153, 30);
	}

	/**
	 * 下拉框
	 * 
	 * @author lsy
	 * @version 2015年3月21日 下午4:30:04
	 */
	public void addComboBox() {
		box1 = new MyComboBox(Constants.ALL_TEAM_NAMES, box1X, box1Y, boxWidth, boxHeight);
		box2 = new MyComboBox(Constants.ALL_TEAM_NAMES, box2X, box2Y, boxWidth, boxHeight);
		box1.setBGColor(UIConfig.DARK_BUTTON_COLOR);
		box2.setBGColor(UIConfig.DARK_BUTTON_COLOR);
		this.add(box1);
		this.add(box2);
		AcListener acLis = new AcListener();
		box1.addActionListener(acLis);
		box2.addActionListener(acLis);
	}

	class AcListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String teamAbbr_1 = "", teamAbbr_2 = "";
			int team1 = box1.getSelectedIndex();
			int team2 = box2.getSelectedIndex();
			if (team1 != 0) {
				teamAbbr_1 = Constants.TEAM_ABBR[team1 - 1];
			}
			if (team2 != 0) {
				teamAbbr_2 = Constants.TEAM_ABBR[team2 - 1];
			}
			if(team1 != 0 && team2 != 0) {
			matchDetailfile = matchQuery.screenMatchByTeam(teamAbbr_1, teamAbbr_2);
			isInit = false;
			addArray();
			if (pageNum == 0) {
				number2.setlbText("1");
			} else {
				number2.setlbText(pageNum + "");
			}
			number1.setlbText("1");
			GameDataPanel.this.repaint();
			}
		}

	}
	

}
