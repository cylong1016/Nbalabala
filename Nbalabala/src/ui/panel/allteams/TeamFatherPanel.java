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
import utility.Constants;
import utility.Utility;
import vo.PlayerSeasonVO;
import vo.TeamDetailVO;
import vo.TeamProfileVO;
import vo.TeamSeasonVO;
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
	
	MatchQueryBLService matchQuery = new MatchQuery();
	protected ImgButton back;
	protected String url = UIConfig.IMG_PATH + "players/";
	protected static BottomPanel FROM_PANEL;

	ImgLabel logo;
	MyLabel teamName;
	/** 球队详细信息 */
	protected TeamDetailVO teamDetail;
	int order = 0;
	
	/** 左边一列三行开始的横坐标 */
	private static final int LEFT_LABEL_COLUMN_X = 350;	
	/** 右边一列三行开始的横坐标 */
	private static final int RIGHT_LABEL_COLUMN_X = 655;	
	/** 两列label最上面一行的纵坐标 */
	private static final int FIRST_LABEL_ROW_Y = 10;
	/** 左边一列“东部联盟1st这一行的Y坐标” */
	private static final int LEFT_LABEL_SECOND_ROW_Y = 46;
	/** 得分篮板助攻三个排名label的纵坐标 */
	private static final int SCORE_REBOUND_ASSIST_Y = 90;
	/** 标题中球队名字的大字体 */
	private static final Font TITLE_NAME_FONT = UIConfig.FONT;	//TODO 标题中球队名字的大字体
	
	private String abbr;
	private JLabel rankLabel;
	private JLabel[] profileLabels;
	private TeamWinsLosesLabel winsLosesLabel;
	private TeamScoreReboundAssistLabel scoreLabel;
	private TeamScoreReboundAssistLabel reboundLabel;
	private TeamScoreReboundAssistLabel assistLabel;
	
	private TeamProfileVO profileVO;
	

	public TeamFatherPanel(BottomPanel panelFrom,String url, String abbr) {
		super(url);
		this.abbr = abbr;
		setButton();
		addButton();
		addListener();
		addBack();
		teamDetail = teamQuery.getTeamDetailByAbbr(abbr,"13-14");
		addLabel(teamDetail.getLogo());
		
		addTitles();	//	标题，包括队名、联盟、几胜几负
		addRanks();	//	胜率、得分、篮板、助攻的联盟内排名
		addProfileLabels();		//简况label，包括所属赛区、主场、建队时间
		addLogo();	
		addTabButtons();	// 四个选项卡按钮
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
	
	/** 标题，包括队名、联盟、几胜几负 */
	private void addTitles() {
		JLabel nameLabel = new JLabel(Constants.translateTeamAbbrToLocation(abbr) + " " +
				Constants.translateTeamAbbr(abbr));
		nameLabel.setFont(TITLE_NAME_FONT);
		nameLabel.setBounds(LEFT_LABEL_COLUMN_X, FIRST_LABEL_ROW_Y, 300, 50);
		this.add(nameLabel);
		
		JLabel leagueLabel = new JLabel(Constants.translateLeague(profileVO.getArea()));
		leagueLabel.setBounds(LEFT_LABEL_COLUMN_X, LEFT_LABEL_SECOND_ROW_Y, 60, 20);
		leagueLabel.setFont(UIConfig.LABEL_PLAIN_FONT);
		this.add(leagueLabel);

		TeamSeasonVO seasonVO = teamDetail.getSeasonRecord();
		winsLosesLabel = new TeamWinsLosesLabel(seasonVO.getWins(), seasonVO.getLoses());
		winsLosesLabel.setBounds(LEFT_LABEL_COLUMN_X, 78,150,50);
		this.add(winsLosesLabel);
	}
	
	/** 添加所属赛区、主场、建队时间labels */
	private void addProfileLabels() {
		TeamProfileVO profileVO = teamDetail.getProfile();
		String [] profileStr = new String[] {Constants.divisionText + profileVO.getDivisionString(),
				Constants.homeText + profileVO.getHome(), Constants.sinceText + profileVO.getSince()};
		for (int i=0;i<3;i++) {
			profileLabels[i] = new JLabel(profileStr[i]);
			profileLabels[i].setFont(UIConfig.LABEL_SMALL_FONT);
			profileLabels[i].setBounds(RIGHT_LABEL_COLUMN_X, UIConfig.PROFILE_LABEL_INTER_Y * i,
					300, 30);
			this.add(profileLabels[i]);
		}
	}
	
	/** 添加联盟内胜率、得分、篮板、助攻排名的label */
	private void addRanks() {
		int [] ranks = teamQuery.getRanks(abbr, Utility.getDefaultSeason());
		int interX = 80;
		
		rankLabel = new JLabel(Utility.getRankStr(ranks[0]));
		rankLabel.setFont(UIConfig.LABEL_PLAIN_FONT);
		rankLabel.setBounds(419, LEFT_LABEL_SECOND_ROW_Y, 40, 40);
		this.add(rankLabel);
		

		scoreLabel = new TeamScoreReboundAssistLabel(Constants.scoreAvgText, ranks[0]);
		scoreLabel.setLocation(660, SCORE_REBOUND_ASSIST_Y);
		this.add(scoreLabel);
		
		reboundLabel = new TeamScoreReboundAssistLabel(Constants.reboundAvgText, ranks[1]);
		reboundLabel.setLocation(660, SCORE_REBOUND_ASSIST_Y + interX);
		this.add(reboundLabel);
		
		assistLabel = new TeamScoreReboundAssistLabel(Constants.assistAvgText, ranks[2]);
		assistLabel.setLocation(660, SCORE_REBOUND_ASSIST_Y + interX * 2);
		this.add(assistLabel);
	}
	
	public void update() {
		
	}
	
}
