package ui.panel.gamedata;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.common.panel.ScorePanel;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchDetailVO;
import vo.MatchProfileVO;
import bl.matchquerybl.MatchQuery;
import blservice.MatchQueryBLService;
import data.teamdata.TeamLogoCache;
import enums.ScreenDivision;

/**
 * 某场比赛数据界面
 * @author lsy
 * @version 2015年3月21日 下午4:01:02
 */
public class GameFatherPanel extends BottomPanel {

	/** serialVersionUID */
	protected static final long serialVersionUID = -5789708998830911573L;
	protected Panel gameData;
	protected  MatchDetailVO matchDetail;
	protected  MatchProfileVO matchPro;
	protected MatchQueryBLService matchQuery;
	protected int btx = 24,bty = 253, inter = 315;

	/** 球队中文全称 */
	protected String teamStr1, teamStr2;
	/** 球队所处位置 */
	protected String place1, place2;
	/** 球队英文缩写 */
	protected String teamShort1, teamShort2;
	/** 上方显示的球队名称和城市 */
	protected Font labelFont = new Font("微软雅黑", 0, 23);
	protected ImgButton back;
	protected ScorePanel scPanel;
	protected ImgLabel sign1,sign2;
	protected MyLabel scorelb_1,scorelb_2,ranklb_1,ranklb_2,recordlb_1,recordlb_2,
	name_1,name_2,area1,area2;

	public GameFatherPanel(String url, MatchDetailVO matchDetail,Panel gameData) {
		super(url);
		this.gameData = gameData;
		this.matchDetail = matchDetail;
		matchPro = matchDetail.getProfile();
		matchQuery = new MatchQuery();
		getScore();
		getTeam();
		addBack();
		addLabel();
		scPanel = new ScorePanel(matchDetail);
		this.add(scPanel);
		scPanel.setLocation(188, 140);
		this.repaint();
	}
	
	public String getRank(int i){
		if(i == 1){
			return "1st";
		}else if(i == 2){
			return "2nd";
		}else if(i == 3){
			return "3rd";
		}else{
			return i+"th";
		}
	}
	
	public void setRed(MyLabel lb1,MyLabel lb2){
		String score1 = lb1.getText();
		String score2 = lb2.getText();
		if(Integer.parseInt(score1)>Integer.parseInt(score2)){
			lb1.setForeground(Color.red);
		}else{
			lb2.setForeground(Color.red);
		}
	}
	
	private int area_x = 800,rank_y = 18,record_y = 65,score_y = 50; 
	public void addLabel(){
		int[] winLose = matchQuery.getTeamWinsLosesByAbbr(teamShort1);
		int win = winLose[0];
		int lose = winLose[1];
		sign1 = new ImgLabel(280, -5, 130, 130, TeamLogoCache.getTeamLogo(teamShort1));
		sign2 = new ImgLabel(550, -5, 130, 130, TeamLogoCache.getTeamLogo(teamShort2));
		scorelb_1 = new MyLabel(210,score_y,100,50,scoreAll[0]);
		scorelb_2 = new MyLabel(680,score_y,100,50,scoreAll[1]);
		setRed(scorelb_1,scorelb_2);
		scorelb_1.setFont(new Font("微软雅黑",0,28));
		scorelb_2.setFont(new Font("微软雅黑",0,28));
		recordlb_1 = new MyLabel(116,record_y,100,50,(Constants.recordText+" "+win+" - "+lose));
		recordlb_1.setForeground(Color.gray);
		name_1 = new MyLabel(25,10,180,50,place1+" "+teamStr1);
		name_1.setRight();
		name_2 = new MyLabel(area_x,10,180,50,place2+" "+teamStr2);
		name_2.setLeft();
		name_1.setFont(labelFont);
		name_2.setFont(labelFont);
		winLose = matchQuery.getTeamWinsLosesByAbbr(teamShort2);
		win = winLose[0];
		lose = winLose[1];
		recordlb_2 = new MyLabel(area_x,record_y,100,50,(Constants.recordText+" "+win+" - "+lose));//战绩
		recordlb_2.setLeft();
		recordlb_2.setForeground(Color.gray);
		int rank1 = matchQuery.getTeamRamkByAbbr(teamShort1);
		int rank2 = matchQuery.getTeamRamkByAbbr(teamShort2);
		ranklb_1 = new MyLabel(140,rank_y,100,100,getRank(rank1));//排名
		ranklb_2 = new MyLabel(840,rank_y,100,100,getRank(rank2));
		ranklb_1.setForeground(Color.blue);
		ranklb_2.setForeground(Color.blue);
		ScreenDivision area_1 = Constants.getAreaByAbbr(teamShort1);
		ScreenDivision area_2 = Constants.getAreaByAbbr(teamShort2);

		area1 = new MyLabel(80,rank_y,100,100,Constants.getAreaByEnglish(area_1));//东西部
		area2 = new MyLabel(area_x,rank_y,100,100,Constants.getAreaByEnglish(area_2));
		area2.setLeft();
		this.add(sign1);
		this.add(sign2);
		this.add(ranklb_1);
		this.add(ranklb_2);
		this.add(recordlb_1);
		this.add(recordlb_2);
		this.add(scorelb_1);
		this.add(scorelb_2);
		this.add(name_1);
		this.add(name_2);
		this.add(area1);
		this.add(area2);
	}
	/**
	 * 添加返回按钮
	 * @author lsy
	 * @version 2015年3月23日 下午6:20:43
	 */
	public void addBack() {
		back = new ImgButton(UIConfig.IMG_PATH + "back.png", 70, 150, UIConfig.IMG_PATH + "backOn.png", UIConfig.IMG_PATH + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				MainController.backToOnePanel(gameData);
			}

		});
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

}
