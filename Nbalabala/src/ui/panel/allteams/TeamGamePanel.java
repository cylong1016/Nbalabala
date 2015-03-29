package ui.panel.allteams;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

import ui.DateChooser;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import ui.panel.gamedata.GameDataPanel;
import vo.MatchProfileVO;
import vo.TeamDetailVO;
import bl.teamquerybl.TeamQuery;
import blservice.TeamQueryBLService;
import data.seasondata.TeamSeasonRecord;

/**
 * 球队赛程数据界面
 * 
 * @author lsy
 * @version 2015年3月21日 上午12:28:47
 */
public class TeamGamePanel extends TeamSeasonPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 5247981003029326464L;
	ImgButton imgButton;
	MainController controller;
	DateChooser dateChooser;
	TeamQueryBLService teamQuery = new TeamQuery();
	TeamButton teamButton;
	TeamDetailVO teamDetail;
	GameDataPanel gameData;
	ArrayList<MatchProfileVO> matchProfile;

	public TeamGamePanel(AllTeamsPanel allteams, MainController controller, String url, TeamButton teamButton,
			int x) {
		super(allteams, controller, url, teamButton, x);
		this.controller = controller;
		this.teamButton = teamButton;
		addFindButton();
		addDateChooser();
		teamDetail = teamQuery.getTeamDetailByAbbr(teamButton.team);
		matchProfile = teamDetail.getMatchRecords();
		gameData = new GameDataPanel(controller,"",1); 
		gameData.setTable(matchProfile,this,matchProfile.size(),controller);
		iniTable(x);
	}
	
	/**
	 * 覆盖父类的方法
	 * @see ui.panel.allteams.TeamSeasonPanel#iniTable(int)
	 */
	public void iniTable(int X){
		
	}

	String[] columns;
	String[][] rowData;
	BottomScrollPane scroll;
	ImageIcon icon;
	ArrayList<Image> imgArr = new ArrayList<Image>();
	BottomTable table;
	DecimalFormat df = new DecimalFormat("0.000");
	/** 两个队伍每节的比分 */
	String[] score1 = { "0", "0", "0", "0", "0", "0", "0" };
	String[] score2 = { "0", "0", "0", "0", "0", "0", "0" };
	/** 两支球队缩写 */
	String[] teamShort;
	/** 两支球队比赛总分 */
	String[] scoreAll;
	/** 每节比分 */
	String[] eachScore;
	/** 球队全称 */
	String[] teamLong;
	String[] team = new String[] { "凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂",
			"热火", "魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛", "火箭", "灰熊",
			"鹈鹕", "马刺" };
	String[] teamArr = new String[] { "BOS", "BKN", "NYK", "PHI", "TOR", "CHI", "CLE", "DET", "IND", "MIL", "ATL",
			"CHA", "MIA", "ORL", "WAS", "GSW", "LAC", "LAL", "PHX", "SAC", "DEN", "MIN", "OKC", "POR", "UTA",
			"DAL", "HOU", "MEM", "NOP", "SAS" };

	/**
	 * 拆解传回来的vo
	 * 
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
		for (int i = 0; i < eachlth; i++) {
			String[] scoreTemp = eachScore[i].split("-");
			score1[i] = scoreTemp[0];
			score2[i] = scoreTemp[1];
		}
	}

	/**
	 * 根据缩写返回球队全称
	 * 
	 * @author lsy
	 * @version 2015年3月22日 上午12:01:45
	 */
	public String match(String abbr) {
		for (int i = 0; i < 30; i++) {
			if (teamArr[i].equals(abbr)) {
				return team[i];
			}
		}
		return null;
	}

	public void addScore(int line) {
		for(int i = 0; i < 7; i++) {
			rowData[2 * line ][i + 1] = score1[i];
			rowData[2 * line + 1][i + 1] = score2[i];
		}
	}
	

	/**
	 * 覆盖父类的方法，让父类的表格不显示
	 */
	public void addSeasonTable(TeamSeasonRecord record) {
	}

	public void addBack() {
		ImgButton back = new ImgButton(UIConfig.IMG_PATH + "back.png", 50, 50, UIConfig.IMG_PATH + "back.png",
				UIConfig.IMG_PATH + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				controller.backToGameDataPanel(TeamGamePanel.this, allteams);
			}

		});
	}

	public void addDateChooser() {
		dateChooser = new DateChooser();
		controller.addDateChooserPanel(this, dateChooser, 722, 248);
	}

	public void addFindButton() {
		imgButton = new ImgButton(url + "search.png", 893, 250, url + "searchOn.png", url + "searchClick.png");
		this.add(imgButton);
		imgButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Date date = dateChooser.getDate();
				SimpleDateFormat  sd = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
				String str = sd.format(date);
				String[] dateArr = str.split("  ");
				String[] dateTemp = dateArr[0].split("-");
				String dateStr = dateTemp[1]+"-"+dateTemp[2];
				for (int i = 0; i < matchProfile.size(); i++) {
					if(dateStr.equals(matchProfile.get(i).getTime())){
						ArrayList<MatchProfileVO> pro = new ArrayList<MatchProfileVO>();
						pro.add(matchProfile.get(i));
						TeamGamePanel.this.remove(scroll);
						gameData.setTable(pro,TeamGamePanel.this,pro.size(),controller);
					}
				}
			}
		});
	}

	public void getGame() {

	}

	public void setEffect() {
		button[2].setOpaque(true);
		button[2].setBackground(UIConfig.BUTTON_COLOR);
		button[2].setForeground(Color.white);
	}

	public void addListener() {
		MouListener mou1 = new MouListener();
		for (int i = 0; i < 3; i++) {
			button[i].addMouseListener(mou1);
		}
	}

	class MouListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == button[0]) {
				controller.toTeamSeasonPanel(allteams, TeamGamePanel.this, teamButton, 0);
			} else if (e.getSource() == button[1]) {
				controller.toTeamSeasonPanel(allteams, TeamGamePanel.this, teamButton, 1);
			} else if (e.getSource() == button[2]) {
				return;
			}
		}
	}

}
