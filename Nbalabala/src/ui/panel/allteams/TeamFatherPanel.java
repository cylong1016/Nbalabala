package ui.panel.allteams;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import vo.TeamDetailVO;
import bl.matchquerybl.MatchQuery;
import bl.teamquerybl.TeamQuery;
import blservice.MatchQueryBLService;
import blservice.TeamQueryBLService;

/**
 * 球队赛季数据 阵容 赛程界面的父类
 * @author lsy
 * @version 2015年4月13日  下午8:22:30
 */
public class TeamFatherPanel extends BottomPanel{
	/** serialVersionUID */
	private static final long serialVersionUID = 4901174711583565164L;
	int seasonX = 672, lineupX = 782, gameX = 857, y = 190;
	int width1 = 83, width2 = 48, height = 25;
	TeamSeasonButton[] button = new TeamSeasonButton[3];
	TeamQueryBLService teamQuery = new TeamQuery();
	String abbr;
	MatchQueryBLService matchQuery = new MatchQuery();
	protected ImgButton back;
	protected String url = UIConfig.IMG_PATH + "players/";
	protected static BottomPanel FROM_PANEL;

	ImgLabel logo;
	MyLabel teamName;
	String[] teamPlace = { "波士顿", "布鲁克林", "纽约", "费城", "多伦多", "芝加哥", "克利夫兰", "底特律", "印第安纳", "密尔沃基", "亚特兰大", "夏洛特",
			"迈阿密", "奥兰多", "华盛顿", "金洲", "洛杉矶", "洛杉矶", "菲尼克斯", "萨克拉门托", "丹佛", "明尼苏达", "俄克拉荷马", "波特兰", "犹他", "达拉斯",
			"休斯敦", "孟菲斯", "新奥尔良", "圣安东尼奥" };
	String[] teamShort = new String[] { "BOS", "BKN", "NYK", "PHI", "TOR", "CHI", "CLE", "DET", "IND", "MIL",
			"ATL", "CHA", "MIA", "ORL", "WAS", "GSW", "LAC", "LAL", "PHX", "SAC", "DEN", "MIN", "OKC", "POR",
			"UTA", "DAL", "HOU", "MEM", "NOP", "SAS" };
	String[] team = new String[] { "凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂",
			"热火", "魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛", "火箭", "灰熊",
			"鹈鹕", "马刺" };
	/** 球队详细信息 */
	protected TeamDetailVO teamDetail;
	int order = 0;

	public TeamFatherPanel(BottomPanel panelFrom,String url, String abbr) {
		super(url);
		this.abbr = abbr;
		setButton();
		addButton();
		addListener();
		addBack();
		teamDetail = teamQuery.getTeamDetailByAbbr(abbr,"13-14");
		addLabel(teamDetail.getLogo());
	}
	
	MyLabel[] teamInfo = new MyLabel[5];
	int lbWidth = 100,lbHeight = 50, lbx = 450, lby = 50;
	/**
	 * 添加logo及队名
	 * @author lsy
	 * @version 2015年3月25日  上午11:43:29
	 */
	public void addLabel(Image img){
		logo = new ImgLabel(130,6,150,150,img);
		teamName = new MyLabel(240,50,300,50,match());
		teamName.setForeground(UIConfig.BUTTON_COLOR);
		teamName.setFont(new Font("微软雅黑",0,30));
		this.add(logo);
		this.add(teamName);
		int label_x = 530;
		teamInfo[0] = new MyLabel(310,90,lbWidth,lbHeight,teamDetail.getProfile().getAbbr());
		teamInfo[1] = new MyLabel(label_x,15,lbWidth*4,lbHeight,"所在地:  "+teamDetail.getProfile().getLocation());
		teamInfo[2] = new MyLabel(label_x,50,lbWidth*4,lbHeight, "所属赛区： "+teamDetail.getProfile().getDivisionString());
		teamInfo[3] = new MyLabel(label_x,85,lbWidth*4,lbHeight,"主场:  "+teamDetail.getProfile().getHome());
		teamInfo[4] = new MyLabel(label_x,120,lbWidth*4,lbHeight,"建队时间:  "+teamDetail.getProfile().getSince());

		for(int i = 0;i<teamInfo.length;i++){
			teamInfo[i].setFont(new Font("微软雅黑",0,20));
			this.add(teamInfo[i]);
			if(i!=0){
			teamInfo[i].setHorizontalAlignment(JLabel.LEFT);
			}
		}
		teamInfo[0].setFont(new Font("微软雅黑",0,25));
	}
	
	public String match(){
		for(order = 0;order<30;order++){
			if(teamDetail.getProfile().getAbbr().equals(teamShort[order])){
				return teamPlace[order]+" "+team[order];
			}
		}
		return "";
	}

	/**
	 * 添加返回按钮
	 * @author lsy
	 * @version 2015年3月25日  上午11:02:11
	 */
	public void addBack() {
		back = new ImgButton(url + "back.png", 50, 50, url + "backOn.png", url + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				MainController.backToOnePanel(FROM_PANEL);
			}

		});
	}

	public void setButton() {
		button[0] = new TeamSeasonButton(seasonX, y, width1, height, "赛季数据");
		button[1] = new TeamSeasonButton(lineupX, y, width2, height, "阵容");
		button[2] = new TeamSeasonButton(gameX, y, width1, height, "赛程数据");
	}

	public void addButton() {
		for (int i = 0; i < 3; i++) {
			this.add(button[i]);
		}
	}

	public void addListener() {
		MouListener mou1 = new MouListener();
		for (int i = 0; i < 3; i++) {
			button[i].addMouseListener(mou1);
		}
	}

	class MouListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == TeamSeasonButton.current) {
				return;
			}
			TeamSeasonButton.current.back();
			
			TeamSeasonButton.current = (TeamSeasonButton) e.getSource();
			if (e.getSource() == button[0]) {
				MainController.toTeamSeasonPanel(abbr);
			} else if (e.getSource() == button[1]) {
				MainController.toTeamPlayerPanel(abbr);
			} else if (e.getSource() == button[2]) {
				MainController.toTeamGamePanel(abbr);
			}
		}
	}
	
	public void setEffect(int i) {
		button[i].setOpaque(true);
		button[i].setBackground(UIConfig.BUTTON_COLOR);
		button[i].setForeground(Color.white);
		TeamSeasonButton.current = button[i];
	}
	
}
