package ui.panel.gamedata;

import java.awt.Font;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import vo.MatchProfileVO;

/**
 * 某场比赛数据界面
 * @author lsy
 * @version 2015年3月21日  下午4:01:02
 */
public class GamePanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5789708998830911573L;
	String url = UIConfig.IMG_PATH+"game/";
	/** 时间地址 */
	String timeURL;
	ImgButton timeImg;
	GameDataPanel gameData;
	MatchProfileVO matchPro;
	GameDetailButton teamName1,teamName2;
	int teamX1=736,teamX2=828,teamY=190,width=74,height=24;
	
	String[] teamPlace={ "波士顿", "布鲁克林", "纽约", "费城", "多伦多", "芝加哥", "克利夫兰", "底特律", "印第安纳", "密尔沃基", "亚特兰大", "夏洛特",
			"迈阿密", "奥兰多", "华盛顿", "金洲", "洛杉矶", "洛杉矶", "菲尼克斯", "萨克拉门托", "丹佛", "明尼苏达", "俄克拉荷马", "波特兰", "犹他", 
			"达拉斯", "休斯敦", "孟菲斯",
			"新奥尔良", "圣安东尼奥"};
	String[] teamShort= new String[] { "BOS", "BKN", "NYK", "PHI", "TOR", "CHI", "CLE", "DET", "IND", "MIL", "ATL",
			"CHA", "MIA", "ORL", "WAS", "GSW", "LAC", "LAL", "PHX", "SAC", "DEN", "MIN", "OKC", "POR", "UTA",
			"DAL", "HOU", "MEM", "NOP", "SAS" };
	String[] team = new String[] { "凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂",
			"热火", "魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛", "火箭", "灰熊",
			"鹈鹕", "马刺" };
	/** 球队中文全称 */
	String teamStr1,teamStr2;
	/** 球队所处位置 */
	String place1,place2;
	/** 球队英文缩写 */
	String teamShort1,teamShort2;
	/** 上方显示的球队名称和城市 */
	MyLabel teamLabel1,teamLabel2,placeLabel1,placeLabel2;
	int labelX=240,labelY_1=32,labelY_2=54,labelY_3=114,labelY_4=136;
	Font labelFont = new Font("微软雅黑",0,17);
	String signurl = "NBAdata/teams/";
	/** 球队队标 */
	ImgLabel sign1,sing2;
	
	public GamePanel(MainController controller, String url,MatchProfileVO matchProfile,GameDataPanel gameData) {
		super(controller, url);
		this.gameData = gameData;
		this.matchPro = matchProfile;
		addTime();
		getScore();
		getTeam();
		addButton();
		addLabel();
	}
	
	public void addLabel(){
		teamLabel1=new MyLabel(labelX,labelY_2,width,height,teamStr1);
		teamLabel2=new MyLabel(labelX,labelY_4,width,height,teamStr2);
		teamLabel1.setFont(labelFont);
		teamLabel2.setFont(labelFont);
		placeLabel1=new MyLabel(labelX,labelY_1,width,height,place1);
		placeLabel2=new MyLabel(labelX,labelY_3,width,height,place2);
		sign1=new ImgLabel(0,0,signurl+teamShort1+".svg");
		System.out.println(signurl+teamShort1+".svg");
		this.add(teamLabel1);
		this.add(teamLabel2);
		this.add(placeLabel1);
		this.add(placeLabel2);
		this.add(sign1);
	}
	
	public void addButton(){
		teamName1 = new GameDetailButton(teamX1,teamY,width,height,teamStr1);
		teamName2 = new GameDetailButton(teamX2,teamY,width,height,teamStr2);
		this.add(teamName1);
		this.add(teamName2);
	}
	
	/**
	 * 两支球队的分割线
	 * @author lsy
	 * @version 2015年3月22日  下午5:16:21
	 */
	public void addTime(){
		timeURL = url+"time"+(gameData.analyzeSection(matchPro)-4)+".png";
		timeImg = new ImgButton(timeURL,260,80,timeURL,timeURL);
		this.add(timeImg);
	}
	
	String[] scoreAll,eachScore,score1,score2;
	public void getScore(){
			scoreAll = matchPro.getScore().split("-");//两支球队比赛总分
			eachScore = matchPro.getEachSectionScore().split(";");

			int eachlth=eachScore.length;
			score1=new String[eachlth];
			score2=new String[eachlth];
			for(int i = 0;i<eachlth;i++){
				String[]scoreTemp=eachScore[i].split("-");
				score1[i]=scoreTemp[0];
				score2[i]=scoreTemp[1];
		}
	}
	
	public void addScore(){
		
	}
	
	/**
	 * 获得球队名称
	 * @author lsy
	 * @version 2015年3月22日  下午5:29:22
	 */
	public void getTeam(){
		String []teamTemp = matchPro.getTeam().split("-");
		teamShort1=teamTemp[0];
		teamShort2=teamTemp[1];
		int team_1_Order=match(teamShort1);
		int team_2_Order=match(teamShort2);
		teamStr1=team[team_1_Order];
		teamStr2=team[team_2_Order];
		place1=teamPlace[team_1_Order];
		place2=teamPlace[team_2_Order];
		
	}
	
	int order;//球队在数组中的位置
	public int match(String str){
		for(order = 0;order<30;order++){
			if(teamShort[order].equals(str)){
				return order;
			}
		}
		return 0;
	}

}
