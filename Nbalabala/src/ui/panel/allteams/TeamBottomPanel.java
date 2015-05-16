package ui.panel.allteams;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import ui.Images;
import ui.UIConfig;
import ui.common.SeasonInputPanel;
import ui.common.button.ImgButton;
import ui.common.button.TabButton;
import ui.common.frame.Frame;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.controller.MainController;
import utility.Constants;
import utility.Utility;
import vo.TeamDetailVO;
import vo.TeamProfileVO;
import vo.TeamSeasonVO;
import bl.matchquerybl.MatchQuery;
import bl.teamquerybl.TeamQuery;
import blservice.MatchQueryBLService;
import blservice.TeamQueryBLService;
import data.teamdata.SVGHandler;

/**
 * 球队赛季数据 阵容 赛程界面的父类
 * @author lsy
 * @version 2015年4月13日  下午8:22:30
 */
public class TeamBottomPanel extends BottomPanel{
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
	private static final int SCORE_REBOUND_ASSIST_Y = 102;
	/** 选项卡按钮的纵坐标 */
	private static final int TABS_Y = 197;
	/** 选项卡按钮的横坐标之差*/
	private static final int TABS_INTER_X = 236;
	/** 标题中球队名字的大字体 */
	private static final Font TITLE_NAME_FONT = UIConfig.FONT;	//TODO 标题中球队名字的大字体
	/** 四个界面添加到坐标26,237即为最左边选项卡下沿和左边框的交点 */
	private static final Rectangle SUBPANEL_BOUNDS = new Rectangle(26,237,946,363);
	
	private String abbr;
	private JLabel rankLabel;
	private JLabel[] profileLabels;
	private TeamWinsLosesLabel winsLosesLabel;	//TODO 为什么胜场数和负场数字体不一样！我不知道！
	private TeamScoreReboundAssistLabel scoreLabel;
	private TeamScoreReboundAssistLabel reboundLabel;
	private TeamScoreReboundAssistLabel assistLabel;
	
	private TabButton kingTab;	//球队数据王
	private TabButton lineupTab;	//阵容
	private TabButton seasonTab;	//赛季数据
	private TabButton matchTab;		//赛程
	
	private Panel currentPanel;	//当前子界面
	private TeamKingPanel kingPanel;
	private Panel lineupPanel;
	private Panel seasonPanel;
	private TeamMatchPanel matchPanel;
	
	private TeamProfileVO profileVO;
	
	private TeamQueryBLService service;
	
	private SeasonInputPanel seasonChooser = new SeasonInputPanel(this);
	
	public TeamBottomPanel(BottomPanel panelFrom, String abbr) {
		super(Images.TEAM_INFO_BG);
		this.abbr = abbr;
		this.service = new TeamQuery();
		this.setLayout(null);
		
		addBack();	//TODO 返回按钮怎么整
		
		teamDetail = teamQuery.getTeamDetailByAbbr(abbr,Utility.getDefaultSeason());
		profileVO = teamDetail.getProfile();
		
		addTitles();	//	标题，包括队名、联盟、几胜几负
		addRanks();	//	胜率、得分、篮板、助攻的联盟内排名
		addProfileLabels();		//简况label，包括所属赛区、主场、建队时间
		addLogo();	
		addTabButtons();	// 四个选项卡按钮 //TODO还没设置监听
			//TODO 还没初始化初始界面（数据王）
		
		
		initiatePanel();
	}
	
	/** 进入球队界面的初始子界面是数据王 */
	private void initiatePanel() {
		kingTab.setOn();
		lineupTab.setOff();
		seasonTab.setOff();
		matchTab.setOff();
		
		kingPanel = new TeamKingPanel(service, abbr);
		currentPanel = kingPanel;
		kingPanel.setBounds(SUBPANEL_BOUNDS);
		this.add(kingPanel);
		
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


	/** 标题，包括队名、所属联盟、几胜几负 */
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
		winsLosesLabel.setBounds(LEFT_LABEL_COLUMN_X, 78,300,50);	
		this.add(winsLosesLabel);
	}
	
	/** 添加所属赛区、主场、建队时间labels */
	private void addProfileLabels() {
		profileLabels = new JLabel[3];
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
		int [] ranks = teamQuery.getRanks(abbr);
		int interX = 80;
		
		rankLabel = new JLabel(Utility.getRankStr(ranks[0]));
		rankLabel.setFont(UIConfig.LABEL_PLAIN_FONT);
		rankLabel.setForeground(UIConfig.BLUE_TEXT_COLOR);
		rankLabel.setBounds(419, LEFT_LABEL_SECOND_ROW_Y, 40, 40);
		this.add(rankLabel);
		

		scoreLabel = new TeamScoreReboundAssistLabel(Constants.scoreAvgText, ranks[1]);
		scoreLabel.setLocation(660, SCORE_REBOUND_ASSIST_Y);
		this.add(scoreLabel);
		
		reboundLabel = new TeamScoreReboundAssistLabel(Constants.reboundAvgText, ranks[2]);
		reboundLabel.setLocation(660 + interX, SCORE_REBOUND_ASSIST_Y);
		this.add(reboundLabel);
		
		assistLabel = new TeamScoreReboundAssistLabel(Constants.assistAvgText, ranks[3]);
		assistLabel.setLocation(660 + interX * 2, SCORE_REBOUND_ASSIST_Y);
		this.add(assistLabel);
	}
	
	private void addLogo() {
		ImgLabel logoLabel = new ImgLabel(53, 0, 280, 160, teamDetail.getLogo());
		this.add(logoLabel);
	}
	
	private void addTabButtons() {
		kingTab = new TabButton(Constants.kingText, Images.TEAM_FIRST_LEVEL_TAB_MOVE_ON, Images.TEAM_FIRST_LEVEL_TAB_CHOSEN);
		kingTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kingTab.setOn();
				lineupTab.setOff();
				seasonTab.setOff();
				matchTab.setOff();
				switchPanel(kingPanel);
			}
		});
		kingTab.setLocation(25, TABS_Y);
		this.add(kingTab);
		
		lineupTab = new TabButton(Constants.lineupText, Images.TEAM_FIRST_LEVEL_TAB_MOVE_ON, Images.TEAM_FIRST_LEVEL_TAB_CHOSEN);
		lineupTab.setLocation(25 + TABS_INTER_X, TABS_Y);
		this.add(lineupTab);
		
		seasonTab = new TabButton(Constants.seasonDataText, Images.TEAM_FIRST_LEVEL_TAB_MOVE_ON, Images.TEAM_FIRST_LEVEL_TAB_CHOSEN);
		seasonTab.setLocation(25 + 2 * TABS_INTER_X, TABS_Y);
		this.add(seasonTab);
		
		matchTab = new TabButton(Constants.matchesDataText, Images.TEAM_FIRST_LEVEL_TAB_MOVE_ON, Images.TEAM_FIRST_LEVEL_TAB_CHOSEN);
		matchTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kingTab.setOff();
				lineupTab.setOff();
				seasonTab.setOff();
				matchTab.setOn();
				if (matchPanel == null) {
					matchPanel = new TeamMatchPanel(seasonChooser);
					matchPanel.updateContent(teamDetail.getMatchRecords());
				}
				switchPanel(matchPanel);
			}
		});
		matchTab.setLocation(25 + 3 * TABS_INTER_X, TABS_Y);
		this.add(matchTab);
	}
	
	private void switchPanel(Panel nextPanel) {
		remove(currentPanel);
		currentPanel = nextPanel;
		currentPanel.setBounds(SUBPANEL_BOUNDS);
		this.add(currentPanel);
		repaint();
	}
	
	//当改变赛季，获得新的detailVO，刷新所有已经存在的子Panel
	public void refresh() {
		
	}
	
	//TODO 测试代码
		public static void main(String[]args) {
			Frame frame = new Frame();
			MainController.frame = frame;
			new SVGHandler().loadLogos();
			frame.setPanel(new TeamBottomPanel(null, "SAS"));
			frame.start();
		}
	

	
}
