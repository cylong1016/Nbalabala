package ui.common.panel;

import java.awt.Color;
import java.awt.Font;

import ui.MyFont;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import utility.Constants;
import vo.MatchDetailVO;
import vo.MatchProfileVO;
import blservice.TeamQueryBLService;

/**
 * 显示球队比赛分数的panel
 * 
 * @author lsy
 * @version 2015年4月22日 上午12:14:54
 */
public class ScorePanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1683422262550183367L;

	String[] teamPlace = { "波士顿", "布鲁克林", "纽约", "费城", "多伦多", "芝加哥", "克利夫兰", "底特律", "印第安纳", "密尔沃基", "亚特兰大", "夏洛特",
			"迈阿密", "奥兰多", "华盛顿", "金洲", "洛杉矶", "洛杉矶", "菲尼克斯", "萨克拉门托", "丹佛", "明尼苏达", "俄克拉荷马", "波特兰", "犹他", "达拉斯",
			"休斯敦", "孟菲斯", "新奥尔良", "圣安东尼奥" };

	int teamX1 = 736, teamX2 = 828, teamY = 190, width = 74, height = 24;
	/** 球队中文全称 */
	String teamStr1, teamStr2;
	/** 球队所处位置 */
	String place1, place2;
	/** 球队英文缩写 */
	String teamShort1, teamShort2;
	/** 上方显示的球队名称和城市 */
	MyLabel teamLabel1, teamLabel2, placeLabel1, placeLabel2;
	private int labelX = 60, labelY_1 = 23, labelY_2 = 50;
	Font labelFont = new Font("微软雅黑", 0, 14);
	String signurl = "NBAdata/teams/";
	/** 球队队标 */
	ImgLabel sign1, sign2;
	TeamQueryBLService teamQuery;
	MatchDetailVO matchVO;
	MyLabel[] lb_1, lb_2,lb_time;
	String url = UIConfig.IMG_PATH + "game/";
	/** 时间地址 */
	String timeURL;
	ImgButton timeImg;
	MatchProfileVO matchPro;

	/**
	 * @param url
	 * @author lsy
	 * @version 2015年4月22日 上午10:49:01
	 */
	public ScorePanel(MatchDetailVO matchDetailVO) {
		this.matchVO = matchDetailVO;
		this.matchPro = matchVO.getProfile();
		getTeam();
		getScore();
		addLabel();
		addScore();
//		addTime();
		this.setSize(620, 90);
	}

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

	public void addLabel() {
		teamLabel1 = new MyLabel(labelX, labelY_1, width, height, teamStr1);
		teamLabel2 = new MyLabel(labelX, labelY_2, width, height, teamStr2);
		teamLabel1.setFont(labelFont);
		teamLabel2.setFont(labelFont);
//		placeLabel1 = new MyLabel(labelX, labelY_1, width, height, place1);
//		placeLabel2 = new MyLabel(labelX, labelY_3, width, height, place2);
		this.add(teamLabel1);
		this.add(teamLabel2);
//		this.add(placeLabel1);
//		this.add(placeLabel2);
//		sign1 = new ImgLabel(28, -5, 80, 80, SVGHandler.getTeamLogo(teamShort1));
//		sign2 = new ImgLabel(28, 80, 80, 80, SVGHandler.getTeamLogo(teamShort2));
//		this.add(sign1);
//		this.add(sign2);
	}

//	public void addTime() {
//		timeURL = url + "time" + (analyzeSection(matchPro) - 4) + ".png";
//		timeImg = new ImgButton(timeURL, 123, 65, timeURL, timeURL);
//		this.add(timeImg);
//	}

	public int analyzeSection(MatchDetailVO pro) {
		String gameInfo = pro.getEachSectionScore();
		String[] eachSection = gameInfo.split(";");
		return eachSection.length;
	}

	String[] scoreAll, eachScore, score1, score2;

	public void getScore() {
		scoreAll = matchPro.getScore().split("-");// 两支球队比赛总分
		eachScore = matchVO.getEachSectionScore().split(";");

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
	int scoreX_1 = 180, inter = 48,labelY_0 = 0;
	int totalScoreX = 442, totalScoreY_1 = 20, totalScoreY_2 = 101, totalInter = 80;
	int addX = 428, addInterX = 80;
	int scoreWidth = 40, scoreHeight = 25;

	public void addScore() {
		int lth = score1.length;
		lb_1 = new MyLabel[lth + 1];
		lb_2 = new MyLabel[lth + 1];
		lb_time = new MyLabel[lth + 1];
		for (int i = 0; i < 4; i++) {
			lb_1[i] = new MyLabel(scoreX_1 + i * inter, labelY_1, scoreWidth, scoreHeight, score1[i]);
			lb_2[i] = new MyLabel(scoreX_1 + i * inter, labelY_2, scoreWidth, scoreHeight, score2[i]);
			lb_time[i] = new MyLabel(scoreX_1 + i * inter, labelY_0, scoreWidth, scoreHeight, (1+i)+"");
			lb_time[i].setForeground(MyFont.LIGHT_GRAY);
			this.add(lb_1[i]);
			this.add(lb_2[i]);
			this.add(lb_time[i]);
			setRed(lb_1[i], lb_2[i]);
		}
		if (lth > 4) {
			for (int i = 4; i < lth; i++) {
				lb_1[i] = new MyLabel(addX + (i - 4) * 80, labelY_1, scoreWidth, scoreHeight, score1[i]);
				lb_2[i] = new MyLabel(addX + (i - 4) * 80, labelY_2, scoreWidth, scoreHeight, score2[i]);
				lb_time[i] = new MyLabel(scoreX_1 + i * inter, labelY_0, scoreWidth, scoreHeight, (1+i)+"");
				lb_time[i].setForeground(Color.gray);
				this.add(lb_1[i]);
				this.add(lb_2[i]);
				this.add(lb_time[i]);
				setRed(lb_1[i], lb_2[i]);
			}
		}
		lb_1[lth] = new MyLabel(totalScoreX + totalInter * (lth - 4), labelY_1, scoreWidth, scoreHeight, scoreAll[0]);
		lb_2[lth] = new MyLabel(totalScoreX + totalInter * (lth - 4), labelY_2, scoreWidth, scoreHeight,
				scoreAll[1]);
		lb_time[lth] = new MyLabel(totalScoreX + totalInter * (lth - 4), labelY_0, scoreWidth, scoreHeight,"总分");
		Font all =MyFont.YT_S;
		lb_1[lth].setFont(all);
		lb_2[lth].setFont(all);
		setRed(lb_1[lth], lb_2[lth]);
		this.add(lb_1[lth]);
		this.add(lb_2[lth]);
		this.add(lb_time[lth]);
	}

	public void setRed(MyLabel l1, MyLabel l2) {
		if (Integer.parseInt(l1.text) > Integer.parseInt(l2.text)) {
			l1.setForeground(UIConfig.RED_WIN_COLOR);
		} else if (Integer.parseInt(l1.text) < Integer.parseInt(l2.text)) {
			l2.setForeground(UIConfig.RED_WIN_COLOR);
		} else {
		}
	}

}
