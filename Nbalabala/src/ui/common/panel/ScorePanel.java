package ui.common.panel;

import java.awt.Color;
import java.awt.Font;

import ui.MyFont;
import ui.UIConfig;
import ui.common.label.MyLabel;
import utility.Constants;
import vo.MatchDetailVO;
import vo.MatchProfileVO;

/**
 * 显示球队比赛分数的panel
 * 
 * @author lsy
 * @version 2015年4月22日 上午12:14:54
 */
public class ScorePanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1683422262550183367L;

	private int  width = 74, height = 24;
	/** 球队中文全称 */
	private String teamStr1, teamStr2;
	/** 球队英文缩写 */
	private String teamShort1, teamShort2;
	/** 上方显示的球队名称和城市 */
	private MyLabel teamLabel1, teamLabel2;
	private int labelX = 60, labelY_1 = 23, labelY_2 = 50;
	private Font labelFont = new Font("微软雅黑", 0, 14);
	private MatchDetailVO matchVO;
	private MyLabel[] lb_1, lb_2,lb_time;
	private MatchProfileVO matchPro;
	private String[] teamTemp;
	private String[] scoreAll, eachScore, score1, score2;

	/**
	 * @param url
	 * @author lsy
	 * @version 2015年4月22日 上午10:49:01
	 */
	public ScorePanel(MatchDetailVO matchDetailVO) {
		this.matchVO = matchDetailVO;
		this.matchPro = matchVO.getProfile();
		teamTemp = matchPro.getTeam().split("-");
		teamShort1 = teamTemp[0];
		teamShort2 = teamTemp[1];
		scoreAll = matchPro.getScore().split("-");// 两支球队比赛总分
		eachScore = matchVO.getEachSectionScore().split(";");
		getTeam(teamShort1,teamShort2);
		getScore(scoreAll,eachScore);
		addLabel();
		addScore();
		addTime(eachScore.length);
		this.setSize(620, 90);
	}
	
	/**eachScore 每节比分 ，格式为“27-25;29-31;13-25;16-31;”*/
	public ScorePanel(String teamShort1,String teamShort2,String score,String eachSecScore){
		scoreAll = score.split("-");// 两支球队比赛总分
		eachScore = eachSecScore.split(";");
		getTeam(teamShort1,teamShort2);
		getScore(scoreAll,eachScore);
		addLabel();
		addScore();
		addTime(4);
		this.setSize(620, 90);
	}

	public void getTeam(String teamShort1,String teamShort2) {
		teamStr1 = Constants.translateTeamAbbr(teamShort1);
		teamStr2 = Constants.translateTeamAbbr(teamShort2);
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
		this.add(teamLabel1);
		this.add(teamLabel2);
	}


	public void getScore(String[] scoreAll,String[] eachScore) {

		int eachlth = eachScore.length;
		score1 = new String[eachlth];
		score2 = new String[eachlth];
		for (int i = 0; i < eachlth; i++) {
			String[] scoreTemp = eachScore[i].split("-");
			score1[i] = scoreTemp[0];
			score2[i] = scoreTemp[1];
		}
	}
	
	public void setlbText(String score,String eachSecScore) {
		scoreAll = score.split("-");// 两支球队比赛总分
		eachScore = eachSecScore.split(";");
		int lth = eachScore.length;
		getScore(scoreAll,eachScore);
		if(lth == 4) {
			for (int i = 0; i < lth; i++) {
				lb_1[i].setlbText(score1[i]);
				lb_2[i].setlbText(score2[i]);
				setRed(lb_1[i],lb_2[i]);
			}
			lb_1[lth].setlbText(scoreAll[0]);
			lb_2[lth].setlbText(scoreAll[1]);
			setRed(lb_1[lth],lb_2[lth]);
		} else{
			int i = 0;
			for (i = 0; i < 4; i++) {
				lb_1[i].setlbText(score1[i]);
				lb_2[i].setlbText(score2[i]);
				setRed(lb_1[i],lb_2[i]);
			}
//			while(lth > 4){
//				lb_1[i] = new 
//			}
//			lb_1[lth+1].setlbText(scoreAll[0]);
//			lb_2[lth+1].setlbText(scoreAll[1]);
			addExtraTime(lth);
		}
	}
	
	public void addExtraTime(int lth){
		this.remove(lb_1[4]);
		this.remove(lb_2[4]);
		if (lth > 4) {
			int i = 4;
			for (i = 4; i < lth; i++) {
				lb_time[i] = new MyLabel(scoreX_1 + i * inter, labelY_0, scoreWidth, scoreHeight, ("OT"+(i-3))+"");
				lb_time[i].setForeground(Color.gray);
				this.add(lb_time[i]);
				lb_1[i] = new MyLabel(scoreX_1 + i * inter, labelY_1, scoreWidth, scoreHeight, score1[i]);
				lb_2[i] = new MyLabel(scoreX_1 + i * inter, labelY_2, scoreWidth, scoreHeight, score2[i]);
				this.add(lb_1[i]);
				this.add(lb_2[i]);
				setRed(lb_1[i], lb_2[i]);
			}
			lb_1[i] = new MyLabel(totalScoreX + totalInter * (lth - 5), labelY_1, scoreWidth, scoreHeight, scoreAll[0]);
			lb_2[i] = new MyLabel(totalScoreX + totalInter * (lth - 5), labelY_2, scoreWidth, scoreHeight, scoreAll[1]);
			setRed(lb_1[i], lb_2[i]);
			this.add(lb_1[i]);
			this.add(lb_2[i]);
			this.repaint();
		}
	}
	
	public void addTime(int lth) {
		lb_time = new MyLabel[10];
		for (int i = 0; i < 4; i++) {
			lb_time[i] = new MyLabel(scoreX_1 + i * inter, labelY_0, scoreWidth, scoreHeight, (1+i)+"");
			lb_time[i].setForeground(MyFont.LIGHT_GRAY);
			this.add(lb_time[i]);
		}
		if (lth > 4) {
			for (int i = 4; i < lth; i++) {
				lb_time[i] = new MyLabel(scoreX_1 + i * inter, labelY_0, scoreWidth, scoreHeight, ("OT"+(i-3))+"");
				lb_time[i].setForeground(Color.gray);
				this.add(lb_time[i]);
			}
		}
		lb_time[lth] = new MyLabel(totalScoreX + totalInter * (lth - 4), labelY_0, scoreWidth, scoreHeight,"总分");
		this.add(lb_time[lth]);
	}

	/** 分数 */
	int scoreX_1 = 180, inter = 48,labelY_0 = 0;
	int totalScoreX = 442, totalScoreY_1 = 20, totalScoreY_2 = 101, totalInter = 80;
	int addX = 428, addInterX = 80;
	int scoreWidth = 40, scoreHeight = 25;

	public void addScore() {
		int lth = score1.length;
		lb_1 = new MyLabel[10];
		lb_2 = new MyLabel[10];
		for (int i = 0; i < lth; i++) {
			lb_1[i] = new MyLabel(scoreX_1 + i * inter, labelY_1, scoreWidth, scoreHeight, score1[i]);
			lb_2[i] = new MyLabel(scoreX_1 + i * inter, labelY_2, scoreWidth, scoreHeight, score2[i]);
			this.add(lb_1[i]);
			this.add(lb_2[i]);
			setRed(lb_1[i], lb_2[i]);
		}

		lb_1[lth] = new MyLabel(totalScoreX + totalInter * (lth - 4), labelY_1, scoreWidth, scoreHeight, scoreAll[0]);
		lb_2[lth] = new MyLabel(totalScoreX + totalInter * (lth - 4), labelY_2, scoreWidth, scoreHeight,
				scoreAll[1]);
		Font all =MyFont.YT_S;
		lb_1[lth].setFont(all);
		lb_2[lth].setFont(all);
		setRed(lb_1[lth], lb_2[lth]);
		this.add(lb_1[lth]);
		this.add(lb_2[lth]);
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
