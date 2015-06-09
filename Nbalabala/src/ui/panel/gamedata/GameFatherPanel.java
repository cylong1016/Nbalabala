package ui.panel.gamedata;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.MyFont;
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
	protected int btx = 26,bty = 211, inter = 317;

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
	/** 左边队伍三列右对齐的x坐标 */
	private static final int LEFT_THREE_X = 192;
	/** 右边队伍三列左对齐的x坐标 */
	private static final int RIGHT_THREE_X = 804;
	/** 第一行的y坐标 */
	private static final int FIRST_Y = 7;
	/** 第二行的y坐标 */
	private static final int SECOND_Y = 32;
	/** 第三行的y坐标 */
	private static final int THIRD_Y = 52;

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
		scPanel.setLocation(188, 110);
		this.repaint();
	}
	
	public GameFatherPanel(String url){
		super(url);
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
			lb1.setForeground(UIConfig.RED_WIN_COLOR);
			lb2.setForeground(MyFont.BLACK_GRAY);
		}else{
			lb2.setForeground(UIConfig.RED_WIN_COLOR);
			lb1.setForeground(MyFont.BLACK_GRAY);
		}
	}
	
	public void addLabel(){
		int[] winLose = matchQuery.getTeamWinsLosesByAbbr(teamShort1);
		int win = winLose[0];
		int lose = winLose[1];
		sign1 = new ImgLabel(310, -5, 100, 100, TeamLogoCache.getTeamLogo(teamShort1));
		this.add(sign1);
		
		sign2 = new ImgLabel(560, -5, 100, 100, TeamLogoCache.getTeamLogo(teamShort2));
		this.add(sign2);
		
		scorelb_1 = new MyLabel(215,SECOND_Y,100,30,scoreAll[0]);
		scorelb_1.setFont(MyFont.YT_XL);
		this.add(scorelb_1);
		
		scorelb_2 = new MyLabel(680,SECOND_Y,100,30,scoreAll[1]);
		scorelb_2.setFont(MyFont.YT_XL);
		this.add(scorelb_2);
		
		setRed(scorelb_1,scorelb_2);
		
		// 左边的战绩
		recordlb_1 = new MyLabel(0,THIRD_Y,LEFT_THREE_X,30,(Constants.recordText+" "+win+" - "+lose));
		recordlb_1.setForeground(Color.gray);
		recordlb_1.setRight();
		this.add(recordlb_1);
		
		name_1 = new MyLabel(0,FIRST_Y,LEFT_THREE_X,30,place1+" "+teamStr1);
		name_1.setRight();
		name_1.setFont(MyFont.YH_XL);
		this.add(name_1);
		
		name_2 = new MyLabel(RIGHT_THREE_X,FIRST_Y,180,30,place2+" "+teamStr2);
		name_2.setLeft();
		name_2.setFont(MyFont.YH_XL);
		this.add(name_2);
		
		winLose = matchQuery.getTeamWinsLosesByAbbr(teamShort2);
		win = winLose[0];
		lose = winLose[1];
		
		// 右边的战绩
		recordlb_2 = new MyLabel(RIGHT_THREE_X,THIRD_Y,100,30,(Constants.recordText+" "+win+" - "+lose));//战绩
		recordlb_2.setLeft();
		recordlb_2.setForeground(Color.gray);
		this.add(recordlb_2);
		
		int rank1 = matchQuery.getTeamRamkByAbbr(teamShort1);
		int rank2 = matchQuery.getTeamRamkByAbbr(teamShort2);
		ranklb_1 = new MyLabel(LEFT_THREE_X-21, SECOND_Y, 100, 30, getRank(rank1));//排名
		ranklb_1.setForeground(UIConfig.BLUE_TEXT_COLOR);
		ranklb_1.setFont(MyFont.YH_XS);
		ranklb_1.setLeft();
		this.add(ranklb_1);
		
		ranklb_2 = new MyLabel(RIGHT_THREE_X+70, SECOND_Y, 100, 30, getRank(rank2));
		ranklb_2.setForeground(UIConfig.BLUE_TEXT_COLOR);
		ranklb_2.setFont(MyFont.YH_XS);
		ranklb_2.setLeft();
		this.add(ranklb_2);
		
		ScreenDivision area_1 = Constants.getAreaByAbbr(teamShort1);
		ScreenDivision area_2 = Constants.getAreaByAbbr(teamShort2);

		area1 = new MyLabel(0,SECOND_Y,LEFT_THREE_X-38,30,Constants.getAreaByEnglish(area_1));//东西部
		area1.setRight();
		this.add(area1);
		area2 = new MyLabel(RIGHT_THREE_X,SECOND_Y,100,30,Constants.getAreaByEnglish(area_2));
		area2.setLeft();
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
