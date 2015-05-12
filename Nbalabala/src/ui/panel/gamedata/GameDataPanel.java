package ui.panel.gamedata;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.comboBox.MyComboBox;
import ui.common.date.DateChooser;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.MatchInfoTable;
import ui.common.table.MatchInfoTableFactory;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchProfileVO;
import bl.matchquerybl.MatchQuery;
import blservice.MatchQueryBLService;

/**
 * 比赛数据的主界面
 * @author cylong
 * @version 2015年3月19日 上午3:55:49
 */
public class GameDataPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -6986506405843542454L;

	private String gameImgPath = UIConfig.IMG_PATH + "gameData/";

	/** 确认按钮 */
	private ImgButton confirmBtn1, confirmBtn2;
	/** 确认按钮图片路径 */
	private String confirmPath = gameImgPath + "confirm.png";
	/** 移动到确认按钮上的图片路径 */
	private String confirmOnPath = gameImgPath + "confirmOn.png";
	/** 点击确认按钮的图片路径 */
	private String confirmClickPath = gameImgPath + "confirmClick.png";
	/** 下拉框 */
	private MyComboBox box1, box2;
	
	private BottomScrollPane scrollPane;


	/** 下拉框的坐标 宽高 */
	int box1X = 722, box2X = 863, box1Y = 9, box2Y = 9, boxWidth = 110, boxHeight = 34;
	int teamY_1 = 280, teamY_2 = 308, inter = 54;
	int teamX_1 = 123, score_1 = 249, score_2 = 305, score_3 = 361, score_4 = 417, addTime_1 = 478, addTime_2 = 562,
			addTime_3 = 646, score = 730;
	/** 技术统计 */
	int analyX = 825, analyY = 293;

	MatchQueryBLService matchQuery = new MatchQuery();
	DateChooser dateChooser;
	ArrayList<MatchProfileVO> matchProfile;
	/** 画线 */
	GameDataButton[] detailImg;
	/** 显示数据的panel */
	DataPanel dataPanel;
	int dataPanelX = 58, dataPanelY = 238, dataPanelWidth = 888, dataPanelHeight = 292;
//	JScrollPane scroll;
	int gameSum;

	/**
	 * @param url
	 *            背景图片的url
	 */
	public GameDataPanel(String url) {
		super(url);
//		addDataPanel();
		addComboBox();
		addDateChooser();
//		addConfirmBtn();
//		scrollPane = new MatchInfoTableFactory(new ArrayList<MatchProfileVO>(), this)
//			.getTableScrollPanel();
//		add(scrollPane);
	}
	
	public GameDataPanel(String url,int i) {
		super(url);
	}


	/**
	 * 添加确认按钮
	 * @author lsy
	 * @version 2015年3月21日 下午4:29:48
	 */
	int clickTime = 0;

	public void addConfirmBtn() {
		confirmBtn1 = new ImgButton(confirmPath, 917, 123, confirmClickPath, confirmOnPath);
		confirmBtn2 = new ImgButton(confirmPath, 550, 123, confirmClickPath, confirmOnPath);
		this.add(confirmBtn1);
		this.add(confirmBtn2);
		confirmBtn1.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (clickTime != 0) {
					GameDataPanel.this.remove(scrollPane);
				}
				clickTime++;
				int team1 = box1.getSelectedIndex();
				int team2 = box2.getSelectedIndex();
				matchProfile = matchQuery.screenMatchByTeam(Constants.TEAM_ABBR[team1], Constants.TEAM_ABBR[team2]);
				gameSum = matchProfile.size();
				remove(scrollPane);
				scrollPane = new MatchInfoTableFactory(matchProfile,GameDataPanel.this)
					.getTableScrollPanel();
				GameDataPanel.this.add(scrollPane);
			}
		});
		confirmBtn2.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (clickTime != 0) {
					GameDataPanel.this.remove(scrollPane);
				}
				clickTime++;
				Date date = dateChooser.getDate();
				matchProfile = matchQuery.screenMatchByDate(date);
				remove(scrollPane);
				scrollPane = new MatchInfoTableFactory(matchProfile,GameDataPanel.this)
					.getTableScrollPanel();
				GameDataPanel.this.add(scrollPane);
			}
		});
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	protected MatchInfoTable table;
	

	/**
	 * 分析打了几节
	 * @author lsy
	 * @version 2015年3月21日 下午5:15:29
	 */
	/** 每节比分 ，格式为“27-25;29-31;13-25;16-31;” */
	public int analyzeSection(MatchProfileVO pro) {
		String gameInfo = pro.getEachSectionScore();
		String[] eachSection = gameInfo.split(";");
		return eachSection.length;
	}

	/**
	 * 日期选择器
	 * @author lsy
	 * @version 2015年3月21日 下午4:29:57
	 */
	public void addDateChooser() {
		dateChooser = new DateChooser();
		MainController.addDateChooserPanel(this, dateChooser, 565, 10,153,30);
	}

	/**
	 * 下拉框
	 * @author lsy
	 * @version 2015年3月21日 下午4:30:04
	 */
	public void addComboBox() {
		box1 = new MyComboBox(Constants.ALL_TEAM_NAMES, box1X, box1Y, boxWidth, boxHeight);
		box2 = new MyComboBox(Constants.ALL_TEAM_NAMES, box2X, box2Y, boxWidth, boxHeight);
		this.add(box1);
		this.add(box2);
		AcListener acLis = new AcListener();
		box1.addActionListener(acLis);
		box2.addActionListener(acLis);
	}
	
	class AcListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int team1 = box1.getSelectedIndex();
			int team2 = box2.getSelectedIndex();
			String teamAbbr_1 = Constants.TEAM_ABBR[team1+1];
			String teamAbbr_2 = Constants.TEAM_ABBR[team2+1];
			if(team1 == 0){
				teamAbbr_1 = "";
			}
			if(team2 == 0){
				teamAbbr_2 = "";
			}
			matchProfile = matchQuery.screenMatchByTeam(teamAbbr_1, teamAbbr_2);
			gameSum = matchProfile.size();
		}
		
	}

}
