package ui.panel.gamedata;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.Images;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.Panel;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchDetailVO;
import vo.MatchProfileVO;
import bl.matchquerybl.MatchQuery;
import blservice.MatchQueryBLService;
import data.teamdata.TeamLogoCache;

/**
 * 比赛简况panel
 * 
 * @author lsy
 * @version 2015年5月12日 下午11:54:40
 */
public class ProfilePanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = 8001991883273314717L;

	MatchDetailVO matchDetail;

	private int width = 74, height = 24;
	/** 分数 */
	private int scoreX_1 = 211, scoreY = 25, scoreY_2 = 95, scoreY_mid = 60, inter = 40;
	private int totalScoreX = 420;
	private int scoreWidth = 40, scoreHeight = 25;
	/** 球队中文全称 */
	private String teamStr1, teamStr2;
	/** 球队所处位置 */
	private String place1, place2;
	/** 球队英文缩写 */
	private String teamShort1, teamShort2;
	/** 上方显示的球队名称和城市 */
	private MyLabel teamLabel1, teamLabel2, placeLabel1, placeLabel2, winlb, loselb, interlb
	,teamlb1,teamlb2,score,rebound,assist,season,date;
	private MyLabel tech;// 技术统计按钮
	private int labelX = 103, labelY_1 = 10, labelY_2 = 32, labelY_3 = 85, labelY_4 = 107, labelX_wl = 90,
			labelY_above = 54, labelY_below = 129;
	private int rightX_Team1 = 580,rightX_Team2 = 740,rightX = 490,rightY_1 = 10,rightY_2 = 35; 
	private Font labelFont = new Font("微软雅黑", 0, 17);
	/** 球队队标 */
	private ImgLabel sign1, sign2;
	private MatchQueryBLService matchQuery;
	private MyLabel[] lb_1, lb_2, lb_3,lb_king,lb_result;
	private MatchProfileVO matchPro;
	private Panel bottomPanel;
	public boolean isIni;

	public ProfilePanel(MatchDetailVO matchDetail,Panel bottomPanel) {
		this.matchDetail = matchDetail;
		this.bottomPanel = bottomPanel;
		matchPro = matchDetail.getProfile();
		matchQuery = new MatchQuery();
		isIni = false;
		getTeam();
		getScore();
		addLabel();
		addScore();
		addButton();
		addWinLose();
		addKing();
		this.setSize(916, 161);
	}
	
	public ProfilePanel(){
		isIni = true;
		this.setSize(916, 161);
	}
	
	/**
	 * 添加胜负场数
	 * @author lsy
	 * @version 2015年5月13日 下午2:20:02
	 */
	public void addWinLose() {
		int[] winLose = matchQuery.getTeamWinsLosesByAbbr(teamShort1);
		int win = winLose[0];
		int lose = winLose[1];
		winlb = new MyLabel(labelX_wl, labelY_above, width, height, win + "");
		loselb = new MyLabel(labelX_wl + 30, labelY_above, width, height, lose + "");
		winlb.setForeground(Color.red);
		loselb.setForeground(Color.gray);
		this.add(winlb);
		this.add(loselb);
		winLose = matchQuery.getTeamWinsLosesByAbbr(teamShort2);
		win = winLose[0];
		lose = winLose[1];
		winlb = new MyLabel(labelX_wl, labelY_below, width, height, win + "");
		loselb = new MyLabel(labelX_wl + 30, labelY_below, width, height, lose + "");
		winlb.setForeground(Color.red);
		loselb.setForeground(Color.gray);
		this.add(winlb);
		this.add(loselb);
		interlb = new MyLabel(labelX_wl + 15, labelY_above, width, height, "-");
		this.add(interlb);
		interlb = new MyLabel(labelX_wl + 15, labelY_below, width, height, "-");
		this.add(interlb);
	}

	public void getTeam() {
		String[] teamTemp = matchPro.getTeam().split("-");
		teamShort1 = teamTemp[0];
		teamShort2 = teamTemp[1];
		int team_1_Order = match(teamShort1);
		int team_2_Order = match(teamShort2);
		teamStr1 = Constants.TEAM_NAMES[team_1_Order];
		teamStr2 = Constants.TEAM_NAMES[team_2_Order];
		place1 = Constants.TEAM_PLACES[team_1_Order];
		place2 = Constants.TEAM_PLACES[team_2_Order];
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

	String[] scoreAll, eachScore, score1, score2;

	public void getScore() {
		scoreAll = matchPro.getScore().split("-");// 两支球队比赛总分
		eachScore = matchDetail.getEachSectionScore().split(";");

		int eachlth = eachScore.length;
		score1 = new String[eachlth];
		score2 = new String[eachlth];
		for (int i = 0; i < eachlth; i++) {
			String[] scoreTemp = eachScore[i].split("-");
			score1[i] = scoreTemp[0];
			score2[i] = scoreTemp[1];
		}
	}
	
//private int rightX_Team1 = 550,rightX_Team2 = 750,rightX = 510,rightY_1 = 20,rightY_2 = 35,right_inter = 20;
	public void addLabel() {
		teamLabel1 = new MyLabel(labelX, labelY_2, width, height, teamStr1);
		teamLabel2 = new MyLabel(labelX, labelY_4, width, height, teamStr2);
		teamLabel1.setFont(labelFont);
		teamLabel2.setFont(labelFont);
		placeLabel1 = new MyLabel(labelX, labelY_1, width, height, place1);
		placeLabel2 = new MyLabel(labelX, labelY_3, width, height, place2);
		sign1 = new ImgLabel(25, 0, 70, 70, TeamLogoCache.getTeamLogo(teamShort1));
		sign2 = new ImgLabel(25, 75, 70, 70, TeamLogoCache.getTeamLogo(teamShort2));
		teamlb1 = new MyLabel(rightX_Team1,rightY_1,width,height,teamStr1);
		teamlb2 = new MyLabel(rightX_Team2,rightY_1,width,height,teamStr2);
		teamlb1.setFont(labelFont);
		teamlb2.setFont(labelFont);
		score = new MyLabel(rightX,rightY_2,width,height,Constants.scoreKingText);
		rebound = new MyLabel(rightX,rightY_2+inter,width,height,Constants.reboundKingText);
		assist = new MyLabel(rightX,rightY_2+2*inter,width,height,Constants.assistKingText);
		season = new MyLabel(0,-5,2*width,height,matchPro.getSeason());
		season.setLeft();
		season.setFont(new Font("微软雅黑",0,10));
		date = new MyLabel(0,10,2*width,height,matchPro.getTime());
		date.setLeft();
		date.setFont(new Font("微软雅黑",0,10));
		this.add(date);
		this.add(teamLabel1);
		this.add(teamLabel2);
		this.add(placeLabel1);
		this.add(placeLabel2);
		this.add(sign1);
		this.add(sign2);
		this.add(teamlb1);
		this.add(teamlb2);
		this.add(score);
		this.add(rebound);
		this.add(assist);
		this.add(season);
	}
	
	/**
	 * 添加和数据王有关的label
	 * @author lsy
	 * @version 2015年5月13日  下午3:54:52
	 */
	private int kingX_1 = 560,kingX_2 = 740,resultX_1 = 674,resultX_2 = 850;
	public void addKing(){
		lb_king = new MyLabel[6];
		lb_result = new MyLabel[6];
		int[] homeValue = matchDetail.gethomeHighestValues();
		int[] roadValue = matchDetail.getRoadHighestValues();
		String[] homeKing = matchDetail.gethomeHighestNames();
		String[] roadKing = matchDetail.getRoadHighestNames();
		for(int i = 0;i<3;i++){
			lb_king[i] = new MyLabel(kingX_1,rightY_2+i*inter,3*width,height,homeKing[i]);
			lb_king[i+3] = new MyLabel(kingX_2,rightY_2+i*inter,3*width,height,roadKing[i]);
			lb_result[i] =  new MyLabel(resultX_1,rightY_2+i*inter,width,height,homeValue[i]+"");
			lb_result[i+3] = new MyLabel(resultX_2,rightY_2+i*inter,width,height,roadValue[i]+"");
			if(homeKing[i].length() > 20){
				lb_king[i].setFont(new Font("微软雅黑",0,10));
			}
			this.add(lb_king[i]);
			this.add(lb_king[i+3]);
			this.add(lb_result[i]);
			this.add(lb_result[i+3]);
		}
		for(int i = 0 ;i < 6; i++){
			lb_king[i].setLeft();
			lb_king[i].setForeground(Color.blue);
		}
	}

	public void addButton() {
		tech = new MyLabel(830, 138, 80, 20, Constants.techText);
		tech.setForeground(Color.white);
		this.add(tech);
		tech.addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tech.setForeground(Color.gray);
				 MainController.toOneGamePanel(matchDetail,bottomPanel);;
			 }
		});
	}

	public void addScore() {
		int lth = score1.length;
		lb_1 = new MyLabel[lth + 1];
		lb_2 = new MyLabel[lth + 1];
		lb_3 = new MyLabel[lth + 1];
		scoreX_1 = scoreX_1 - (lth - 4) * 5;
		inter = inter - (lth - 4) * 5;
		for (int i = 0; i < lth; i++) {
			lb_3[i] = new MyLabel(scoreX_1 + i * inter, scoreY_mid, scoreWidth, scoreHeight, (i + 1) + "");
			lb_3[i].setForeground(Color.gray);
			this.add(lb_3[i]);
		}
		for (int i = 0; i < lth; i++) {
			lb_1[i] = new MyLabel(scoreX_1 + i * inter, scoreY, scoreWidth, scoreHeight, score1[i]);
			lb_2[i] = new MyLabel(scoreX_1 + i * inter, scoreY_2, scoreWidth, scoreHeight, score2[i]);
			this.add(lb_1[i]);
			this.add(lb_2[i]);
			setRed(lb_1[i], lb_2[i]);
		}

		lb_1[lth] = new MyLabel(totalScoreX, scoreY, scoreWidth, scoreHeight, scoreAll[0]);
		lb_2[lth] = new MyLabel(totalScoreX, scoreY_2, scoreWidth, scoreHeight, scoreAll[1]);
		lb_3[lth] = new MyLabel(totalScoreX, scoreY_mid, scoreWidth, scoreHeight, Constants.totalScoreText);
		Font all = new Font("微软雅黑", 1, 20);
		lb_1[lth].setFont(all);
		lb_2[lth].setFont(all);
		lb_3[lth].setForeground(Color.gray);
		setRed(lb_1[lth], lb_2[lth]);
		this.add(lb_1[lth]);
		this.add(lb_2[lth]);
		this.add(lb_3[lth]);
	}

	public void setRed(MyLabel l1, MyLabel l2) {
		if (Integer.parseInt(l1.text) > Integer.parseInt(l2.text)) {
			l1.setForeground(Color.red);
		} else if (Integer.parseInt(l1.text) < Integer.parseInt(l2.text)) {
			l2.setForeground(Color.red);
		} else {
		}
	}

	public void paint(Graphics g) {
		g.drawImage(Images.GAME_LABEL_BG, 0, 0, this);
		super.paint(g);
	}
}
