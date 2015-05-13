package ui.panel.gamedata;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ui.UIConfig;
import ui.common.UserMouseAdapter;
import ui.common.button.ImgButton;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.panel.ScorePanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchDetailVO;
import vo.MatchPlayerVO;
import vo.MatchProfileVO;
import bl.matchquerybl.MatchQuery;
import bl.playerquerybl.PlayerQuery;
import bl.teamquerybl.TeamQuery;
import blservice.MatchQueryBLService;
import blservice.PlayerQueryBLService;
import blservice.TeamQueryBLService;

/**
 * 某场比赛数据界面
 * 
 * @author lsy
 * @version 2015年3月21日 下午4:01:02
 */
public class GamePanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5789708998830911573L;
	String url = UIConfig.IMG_PATH + "game/";
	/** 时间地址 */
	String timeURL;
	ImgButton timeImg;
	BottomPanel gameData;
	MatchProfileVO matchPro;
	MatchQueryBLService matchQuery;
	PlayerQueryBLService playerQuery;
	GameDetailButton teamName1, teamName2;
	int teamX1 = 736, teamX2 = 828, teamY = 190, width = 74, height = 24;

	String[] teamPlace = { "波士顿", "布鲁克林", "纽约", "费城", "多伦多", "芝加哥", "克利夫兰", "底特律", "印第安纳", "密尔沃基", "亚特兰大", "夏洛特",
			"迈阿密", "奥兰多", "华盛顿", "金洲", "洛杉矶", "洛杉矶", "菲尼克斯", "萨克拉门托", "丹佛", "明尼苏达", "俄克拉荷马", "波特兰", "犹他", "达拉斯",
			"休斯敦", "孟菲斯", "新奥尔良", "圣安东尼奥" };

	/** 球队中文全称 */
	String teamStr1, teamStr2;
	/** 球队所处位置 */
	String place1, place2;
	/** 球队英文缩写 */
	String teamShort1, teamShort2;
	/** 上方显示的球队名称和城市 */
	Font labelFont = new Font("微软雅黑", 0, 17);
	String signurl = "NBAdata/teams/";
	/** 球队队标 */
	TeamQueryBLService teamQuery;
	MatchDetailVO matchVO;
	MyLabel[] lb_1, lb_2;
	ImgButton back;
	ScorePanel scPanel;

	public GamePanel(String url, MatchProfileVO matchProfile,BottomPanel gameData) {
		super(url);
		this.gameData = gameData;
		this.matchPro = matchProfile;
		teamQuery = new TeamQuery();
		matchQuery = new MatchQuery();
		playerQuery = new PlayerQuery();
		getScore();
		getTeam();
		addButton();
		initSetTabel();
		addBack();
		checkMatchValid();
		scPanel = new ScorePanel(matchPro);
		this.add(scPanel);
		scPanel.setLocation(137, 15);
		this.repaint();
	}

	/**
	 * 添加返回按钮
	 * @author lsy
	 * @version 2015年3月23日 下午6:20:43
	 */
	public void addBack() {
		back = new ImgButton(UIConfig.IMG_PATH + "back.png", 50, 50, UIConfig.IMG_PATH + "backOn.png", UIConfig.IMG_PATH + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				MainController.backToOnePanel(gameData);
			}

		});
	}
	
	private void checkMatchValid() {
		if (!matchVO.isMatchValid()) {
			ImgLabel imgLabel = new ImgLabel(430, 60, 232, 178, new ImageIcon("images/game/wrong.png").getImage());
			this.add(imgLabel);
		}
	}


	public void initSetTabel() {
//		matchVO = matchQuery.getMatchDetail(matchPro.getSeason(), matchPro.getTime(), teamShort1, teamShort2);
//		ArrayList<MatchPlayerVO> homePlayers = matchVO.getHomePlayers();
//		setTable(homePlayers);
	}

	public void addButton() {
		teamName1 = new GameDetailButton(teamX1, teamY, width, height, teamStr1);
		teamName1.setOpaque(true);
		teamName1.setBackground(UIConfig.BUTTON_COLOR);
		teamName1.setForeground(Color.white);
		teamName2 = new GameDetailButton(teamX2, teamY, width, height, teamStr2);
		this.add(teamName1);
		this.add(teamName2);
		teamName1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				teamName2.back();
				GamePanel.this.remove(scroll);
				ArrayList<MatchPlayerVO> homePlayers = matchVO.getHomePlayers();
				setTable(homePlayers);
			}
		});
		teamName2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				teamName1.back();
				GamePanel.this.remove(scroll);
				ArrayList<MatchPlayerVO> roadPlayers = matchVO.getRoadPlayers();
				setTable(roadPlayers);
			}
		});
	}

	String[] columns;
	String[][] rowData;
	BottomScrollPane scroll;

	// BottomTable table=new BottomTable(rowData,columns);
	public void setTable(ArrayList<MatchPlayerVO> players) {
		columns = new String[] { "球员名", "位置", "在场时间", "投篮命中数", "投篮出手数", "三分命中数", "三分出手数", "罚球命中数", "罚球出手数",
				"进攻篮板数", "防守篮板数", "总篮板数", "助攻数", "抢断数", "盖帽数", "失误数", "犯规数", "个人得分" };
		int size = players.size();
		int lth = columns.length;
		rowData = new String[size][lth];
		for (int i = 0; i < size; i++) {
			MatchPlayerVO mpVO = players.get(i);
			rowData[i][0] = mpVO.getName();
			rowData[i][1] = mpVO.getPosition();
			rowData[i][2] = mpVO.getTime();
			rowData[i][3] = mpVO.getFieldGoal() + "";
			rowData[i][4] = mpVO.getFieldAttempt() + "";
			rowData[i][5] = mpVO.getThreePointGoal() + "";
			rowData[i][6] = mpVO.getThreePointAttempt() + "";
			rowData[i][7] = mpVO.getFreethrowGoal() + "";
			rowData[i][8] = mpVO.getFieldAttempt() + "";
			rowData[i][9] = mpVO.getOffensiveRebound() + "";
			rowData[i][10] = mpVO.getDefensiveRebound() + "";
			rowData[i][11] = mpVO.getTotalRebound() + "";
			rowData[i][12] = mpVO.getAssist() + "";
			rowData[i][13] = mpVO.getSteal() + "";
			rowData[i][14] = mpVO.getBlock() + "";
			rowData[i][15] = mpVO.getTurnover() + "";
			rowData[i][16] = mpVO.getFoul() + "";
			rowData[i][17] = mpVO.getPersonalGoal() + "";
		}
		BottomTable table = new BottomTable(rowData, columns);
		table.getColumnModel().getColumn(0).setPreferredWidth(170);
		addListener(table,players);
		scroll = new BottomScrollPane(table);
		scroll.setBounds(58,257,888, 276);
		this.add(scroll);

	}

	public void addListener(final BottomTable table,final ArrayList<MatchPlayerVO> players) {
		try {
			table.addMouseListener(new UserMouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(players.get(rowI).getName(), GamePanel.this);
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	String[] scoreAll, eachScore, score1, score2;

	public void getScore() {
		scoreAll = matchPro.getScore().split("-");// 两支球队比赛总分
		eachScore = matchPro.getEachSectionScore().split(";");

		int eachlth = eachScore.length;
		score1 = new String[eachlth];
		score2 = new String[eachlth];
		for (int i = 0; i < eachlth; i++) {
			String[] scoreTemp = eachScore[i].split("-");
			score1[i] = scoreTemp[0];
			score2[i] = scoreTemp[1];
		}
	}

	/**
	 * 获得球队名称
	 * 
	 * @author lsy
	 * @version 2015年3月22日 下午5:29:22
	 */
	public void getTeam() {
		String[] teamTemp = matchPro.getTeam().split("-");
		teamShort1 = teamTemp[0];
		teamShort2 = teamTemp[1];
		int team_1_Order = match(teamShort1);
		int team_2_Order = match(teamShort2);
		teamStr1 = Constants.TEAM_NAMES[team_1_Order];
		teamStr2 = Constants.TEAM_NAMES[team_2_Order];
		place1 = teamPlace[team_1_Order];
		place2 = teamPlace[team_2_Order];
	}

	int order;// 球队在数组中的位置

	public int match(String str) {
		for (order = 0; order < 30; order++) {
			if (Constants.TEAM_ABBR[order].equals(str)) {
				return order;
			}
		}
		return 0;
	}

}
