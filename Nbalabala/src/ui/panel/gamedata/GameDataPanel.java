package ui.panel.gamedata;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ui.DateChooser;
import ui.UIConfig;
import ui.common.UserMouseAdapter;
import ui.common.button.ImgButton;
import ui.common.comboBox.MyComboBox;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.common.table.MatchInfoTable;
import ui.controller.MainController;
import vo.MatchProfileVO;
import bl.matchquerybl.MatchQuery;
import blservice.MatchQueryBLService;

/**
 * 比赛数据的主界面
 * @author cylong
 * @version 2015年3月19日 上午3:55:49
 */
public class GameDataPanel extends BottomPanel {

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

	String[] team = new String[]{"凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂", "热火",
									"魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛",
									"火箭", "灰熊", "鹈鹕", "马刺"};
	String[] teamArr = new String[]{"BOS", "BKN", "NYK", "PHI", "TOR", "CHI", "CLE", "DET", "IND", "MIL", "ATL", "CHA",
									"MIA", "ORL", "WAS", "GSW", "LAC", "LAL", "PHX", "SAC", "DEN", "MIN", "OKC", "POR",
									"UTA", "DAL", "HOU", "MEM", "NOP", "SAS"};

	/** 下拉框的坐标 宽高 */
	int box1X = 629, box2X = 818, box1Y = 44, box2Y = 80, boxWidth = 153, boxHeight = 30;
	int teamY_1 = 280, teamY_2 = 308, inter = 54;
	int teamX_1 = 123, score_1 = 249, score_2 = 305, score_3 = 361, score_4 = 417, addTime_1 = 478, addTime_2 = 562,
			addTime_3 = 646, score = 730;
	/** 技术统计 */
	int analyX = 825, analyY = 293;

	MatchQueryBLService matchQuery = new MatchQuery();
	DateChooser dateChooser;
	MainController controller;
	ArrayList<MatchProfileVO> matchProfile;
	/** 画线 */
	GameDataButton[] detailImg;
	/** 显示数据的panel */
	DataPanel dataPanel;
	int dataPanelX = 58, dataPanelY = 238, dataPanelWidth = 888, dataPanelHeight = 292;
//	JScrollPane scroll;
	/** 比赛场数 */
	int gameSum;

	/**
	 * @param url
	 *            背景图片的url
	 */
	public GameDataPanel(MainController controller, String url) {
		super(controller, url);
		this.controller = controller;
//		addDataPanel();
		addComboBox();
		addDateChooser();
		addConfirmBtn();
	}
	
	public GameDataPanel(MainController controller, String url,int i) {
		super(controller, url);
	}


	/**
	 * 添加确认按钮
	 * @author lsy
	 * @version 2015年3月21日 下午4:29:48
	 */
	int clickTime = 0;

	public void addConfirmBtn() {
		confirmBtn1 = new ImgButton(confirmPath, 917, 123, confirmClickPath, confirmOnPath);
		confirmBtn2 = new ImgButton(confirmPath, 450, box1Y, confirmClickPath, confirmOnPath);
		this.add(confirmBtn1);
		this.add(confirmBtn2);
		confirmBtn1.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (clickTime != 0) {
					GameDataPanel.this.remove(scroll);
				}
				clickTime++;
				int team1 = box1.getSelectedIndex();
				int team2 = box2.getSelectedIndex();
				matchProfile = matchQuery.screenMatchByTeam(teamArr[team1], teamArr[team2]);
				gameSum = matchProfile.size();
				setTable(matchProfile,GameDataPanel.this,gameSum,controller);
			}
		});
		confirmBtn2.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (clickTime != 0) {
					GameDataPanel.this.remove(scroll);
				}
				clickTime++;
				Date date = dateChooser.getDate();
				matchProfile = matchQuery.screenMatchByDate(date);
				gameSum = matchProfile.size();
				setTable(matchProfile,GameDataPanel.this,gameSum,controller);
			}
		});
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	/** 两个队伍每节的比分 */
	String[] score1 = {"0", "0", "0", "0", "0", "0", "0"};
	String[] score2 = {"0", "0", "0", "0", "0", "0", "0"};
	/** 两支球队缩写 */
	String[] teamShort;
	/** 两支球队比赛总分 */
	String[] scoreAll;
	/** 每节比分 */
	String[] eachScore;
	/** 球队全称 */
	String[] teamLong;

	/**
	 * 拆解传回来的vo
	 * @author lsy
	 * @version 2015年3月22日 上午12:04:52
	 */
	public void analyzeVO(MatchProfileVO proVOArray) {
		teamLong = new String[2];
		teamShort = proVOArray.getTeam().split("-");
		teamLong[0] = match(teamShort[0]);
		teamLong[1] = match(teamShort[1]);
		scoreAll = proVOArray.getScore().split("-");// 两支球队比赛总分
		eachScore = proVOArray.getEachSectionScore().split(";");
		int eachlth = eachScore.length;
		for(int i = 0; i < eachlth; i++) {
			String[] scoreTemp = eachScore[i].split("-");
			score1[i] = scoreTemp[0];
			score2[i] = scoreTemp[1];
		}
	}
	
	protected BottomScrollPane scroll;
	protected MatchInfoTable table;
	public void setTable(final ArrayList<MatchProfileVO> matchProfile,final BottomPanel panel,int gameSum,final MainController controller) {
		String[][] rowData = new String[2 * gameSum][MatchInfoTable.COLUMN_LENGTH];
		for(int j = 0; j < gameSum * 2 ; j = j + 2) {
			MatchProfileVO pro = matchProfile.get(j / 2);
			score1 = new String[]{"0", "0", "0", "0", "0", "0", "0"};
			score2 = new String[]{"0", "0", "0", "0", "0", "0", "0"};
			analyzeVO(pro);
			rowData[j][0] = teamLong[0];
			rowData[j+1][0] = teamLong[1];
			rowData[j][8] = scoreAll[0];
			rowData[j + 1][8] = scoreAll[1];
			rowData[j][9]="数据统计";
			addScore(rowData,j / 2);
		}
		table = new MatchInfoTable(rowData);
		try{
			table.addMouseListener(new UserMouseAdapter(){
				
				public void mouseClicked(MouseEvent e){
					int rowI  = table.rowAtPoint(e.getPoint());// 得到table的行号
					if ( rowI > -1){
						controller.toOneGamePanel(panel, matchProfile.get(rowI/2), panel);
					}
					
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		scroll = new BottomScrollPane(table);
		scroll.setLocation(57, 285);
		panel.add(scroll);
	}

	public void addScore(String[][] rowData,int line) {
		for(int i = 0; i < 7; i++) {
			rowData[2 * line ][i + 1] = score1[i];
			rowData[2 * line + 1][i + 1] = score2[i];
		}
	}

	/**
	 * 根据缩写返回球队全称
	 * @author lsy
	 * @version 2015年3月22日 上午12:01:45
	 */
	public String match(String abbr) {
		for(int i = 0; i < 30; i++) {
			if (teamArr[i].equals(abbr)) {
				return team[i];
			}
		}
		return null;
	}

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
		controller.addDateChooserPanel(this, dateChooser, 257, box1Y);
	}

	/**
	 * 下拉框
	 * @author lsy
	 * @version 2015年3月21日 下午4:30:04
	 */
	public void addComboBox() {
		box1 = new MyComboBox(team, box1X, box1Y, boxWidth, boxHeight);
		box2 = new MyComboBox(team, box2X, box2Y, boxWidth, boxHeight);
		this.add(box1);
		this.add(box2);
	}

}
