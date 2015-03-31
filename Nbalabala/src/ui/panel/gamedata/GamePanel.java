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
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchDetailVO;
import vo.MatchPlayerVO;
import vo.MatchProfileVO;
import vo.TeamDetailVO;
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
	MyLabel teamLabel1, teamLabel2, placeLabel1, placeLabel2;
	int labelX = 240, labelY_1 = 32, labelY_2 = 54, labelY_3 = 114, labelY_4 = 136;
	Font labelFont = new Font("微软雅黑", 0, 17);
	String signurl = "NBAdata/teams/";
	/** 球队队标 */
	ImgLabel sign1, sign2;
	TeamQueryBLService teamQuery;
	TeamDetailVO teamVO_1, teamVO_2;
	MatchDetailVO matchVO;
	MyLabel[] lb_1, lb_2;
	ImgButton back;
	MainController mainController;

	public GamePanel(MainController controller, String url, MatchProfileVO matchProfile,BottomPanel gameData) {
		super(controller, url);
		this.gameData = gameData;
		this.matchPro = matchProfile;
		this.mainController = controller;
		teamQuery = new TeamQuery();
		matchQuery = new MatchQuery();
		playerQuery = new PlayerQuery();
		getScore();
		getTeam();
		getDetailVO();
		addButton();
		addLabel();
		addTime();
		addScore();
		initSetTabel();
		addBack();
		checkMatchValid();
	}

	/**
	 * 添加返回按钮
	 * @author lsy
	 * @version 2015年3月23日 下午6:20:43
	 */
	public void addBack() {
		back = new ImgButton(url + "back.png", 50, 50, url + "back.png", url + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mainController.backToGameDataPanel(GamePanel.this, gameData);
			}

		});
	}
	
	private void checkMatchValid() {
		if (!matchVO.isMatchValid()) {
			ImgLabel imgLabel = new ImgLabel(5, 95, 232, 178, new ImageIcon("images/game/wrong.png").getImage());
			this.add(imgLabel);
		}
	}

	/**
	 * 获得详细的vo
	 * 
	 * @author lsy
	 * @version 2015年3月22日 下午6:44:44
	 */
	public void getDetailVO() {
		teamVO_1 = teamQuery.getTeamDetailByAbbr(teamShort1);
		teamVO_2 = teamQuery.getTeamDetailByAbbr(teamShort2);
	}

	public void initSetTabel() {
		matchVO = matchQuery.getMatchDetail(matchPro.getSeason(), matchPro.getTime(), teamShort1, teamShort2);
		ArrayList<MatchPlayerVO> homePlayers = matchVO.getHomePlayers();
		setTable(homePlayers);
	}

	public void addLabel() {
		teamLabel1 = new MyLabel(labelX, labelY_2, width, height, teamStr1);
		teamLabel2 = new MyLabel(labelX, labelY_4, width, height, teamStr2);
		teamLabel1.setFont(labelFont);
		teamLabel2.setFont(labelFont);
		placeLabel1 = new MyLabel(labelX, labelY_1, width, height, place1);
		placeLabel2 = new MyLabel(labelX, labelY_3, width, height, place2);
		sign1 = new ImgLabel(165, 10,80,80, teamVO_1.getLogo());
		sign2 = new ImgLabel(165, 95, 80,80,teamVO_2.getLogo());
		this.add(teamLabel1);
		this.add(teamLabel2);
		this.add(placeLabel1);
		this.add(placeLabel2);
		this.add(sign1);
		this.add(sign2);
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
		addListener(table,players);
		scroll = new BottomScrollPane(table);
		scroll.setBounds(58,318,888, 247);
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
						mainController.toPlayerInfoPanel(GamePanel.this,playerQuery.searchPlayers(players.get(rowI).getName()).get(rowI), 
								GamePanel.this);
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 两支球队的分割线
	 * 
	 * @author lsy
	 * @version 2015年3月22日 下午5:16:21
	 */
	public void addTime() {
		timeURL = url + "time" + (analyzeSection(matchPro) - 4) + ".png";
		timeImg = new ImgButton(timeURL, 260, 80, timeURL, timeURL);
		this.add(timeImg);
	}

	public int analyzeSection(MatchProfileVO pro) {
		String gameInfo = pro.getEachSectionScore();
		String[] eachSection = gameInfo.split(";");
		return eachSection.length;
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

	/** 分数 */
	int scoreX_1 = 355, scoreY = 44, scoreY_2 = 120, inter = 48;
	int totalScoreX = 579, totalScoreY_1 = 35, totalScoreY_2 = 116, totalInter = 80;
	int addX = 565, addInterX = 80;
	int scoreWidth = 40, scoreHeight = 25;

	public void addScore() {
		int lth = score1.length;
		lb_1 = new MyLabel[lth + 1];
		lb_2 = new MyLabel[lth + 1];
		for (int i = 0; i < 4; i++) {
			lb_1[i] = new MyLabel(scoreX_1 + i * inter, scoreY, scoreWidth, scoreHeight, score1[i]);
			lb_2[i] = new MyLabel(scoreX_1 + i * inter, scoreY_2, scoreWidth, scoreHeight, score2[i]);
			this.add(lb_1[i]);
			this.add(lb_2[i]);
			setRed(lb_1[i], lb_2[i]);
		}
		if (lth > 4) {
			for (int i = 4; i < lth; i++) {
				lb_1[i] = new MyLabel(addX + (i - 4) * 80, scoreY, scoreWidth, scoreHeight, score1[i]);
				lb_2[i] = new MyLabel(addX + (i - 4) * 80, scoreY_2, scoreWidth, scoreHeight, score2[i]);
				this.add(lb_1[i]);
				this.add(lb_2[i]);
				setRed(lb_1[i], lb_2[i]);
			}
		}
		lb_1[lth] = new MyLabel(totalScoreX + totalInter * (lth - 4), scoreY, scoreWidth, scoreHeight, scoreAll[0]);
		lb_2[lth] = new MyLabel(totalScoreX + totalInter * (lth - 4), scoreY_2, scoreWidth, scoreHeight,
				scoreAll[1]);
		Font all = new Font("微软雅黑", 1, 20);
		lb_1[lth].setFont(all);
		lb_2[lth].setFont(all);
		setRed(lb_1[lth], lb_2[lth]);
		this.add(lb_1[lth]);
		this.add(lb_2[lth]);
	}

	public void setRed(MyLabel l1, MyLabel l2) {
		if (Integer.parseInt(l1.text) > Integer.parseInt(l2.text)) {
			l1.setForeground(Color.red);
		} else if (Integer.parseInt(l1.text) < Integer.parseInt(l2.text)) {
			l2.setForeground(Color.red);
		} else {
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
