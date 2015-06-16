package ui.panel.allteams;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import po.TeamSeasonPO;
import ui.Images;
import ui.MyFont;
import ui.UIConfig;
import ui.common.SeasonInputPanel;
import ui.common.button.ImgButton;
import ui.common.button.TabButton;
import ui.common.frame.Frame;
import ui.common.label.ImgLabel;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.controller.MainController;
import utility.Constants;
import utility.Utility;
import vo.TeamDetailVO;
import vo.TeamProfileVO;
import bl.teamquerybl.TeamQuery;
import blservice.TeamQueryBLService;
import data.teamdata.TeamLogoCache;

/**
 * 球队赛季数据 阵容 赛程界面的父类
 * @author lsy
 * @version 2015年4月13日  下午8:22:30
 */
public class TeamBottomPanel extends BottomPanel{
	/** serialVersionUID */
	private static final long serialVersionUID = 4901174711583565164L;
	private TeamQueryBLService teamQuery = new TeamQuery();
	
	private ImgButton back;
	private BottomPanel fromPanel;

	/** 球队详细信息 */
	private TeamDetailVO teamDetail;
	
	/**
	 * TODO
	 * 下面这些坐标是在原来的设计下的，为了便于对齐。现在应该部分能用。_2015/6/7
	 * 
	 * */
	
	/** 中间部分开始的横坐标 */
	private static final int MID_LABEL_COLUMN_X = 476;
	/** 右边一列三行开始的横坐标 */
	private static final int RIGHT_LABEL_COLUMN_X = 785;
	/** 两列label最上面一行的纵坐标 */
	private static final int FIRST_LABEL_ROW_Y = 10;
	/** 联盟排名纵坐标*/
	private static final int LEAGUE_LABEL_ROW_Y = 28;
	/** 中间label的纵坐标 */
	private static final int MID_LABEL_ROW_Y = 53;
	/** 得分篮板助攻三个排名label的纵坐标 */
	private static final int SCORE_REBOUND_ASSIST_Y = 97;
	/** 选项卡按钮的纵坐标 */
	private static final int TABS_Y = 198;
	/** 选项卡按钮的横坐标之差*/
	private static final int TABS_INTER_X = 238;
	/** 标题中球队名字的大字体 */
	private static final Font TITLE_NAME_FONT = new Font("微软雅黑",Font.PLAIN,30);	//TODO 标题中球队名字的大字体
	/** 
	 * ！！！！！
	 * ！！！！！
	 * 四个子界面添加到坐标25,237即为最左边选项卡下沿和左边框的交点 */
	private static final Rectangle SUBPANEL_BOUNDS = new Rectangle(25,237,947,363);
	
	private String abbr;
	private JLabel rankLabel;
	private JLabel[] profileLabels;
	private TeamWinsLosesLabel winsLosesLabel;	
	private TeamScoreReboundAssistLabel scoreLabel;
	private TeamScoreReboundAssistLabel reboundLabel;
	private TeamScoreReboundAssistLabel assistLabel;
	
	private TabButton kingTab;	//球队数据王
	private TabButton lineupTab;	//阵容
	private TabButton seasonTab;	//赛季数据
	private TabButton matchTab;		//赛程
	
	private Panel currentPanel;	//当前子界面
	private TeamKingPanel kingPanel;
	private TeamLineupPanel lineupPanel;
	private TeamSeasonPanel seasonPanel;
	private TeamMatchPanel matchPanel;
	
	private TeamProfileVO profileVO;
	
	private SeasonInputPanel seasonChooser = new SeasonInputPanel(this);
	
	public TeamBottomPanel(BottomPanel panelFrom, String abbr) {
		super(Images.TEAM_INFO_BG);
		this.abbr = abbr;
		this.setLayout(null);
		this.fromPanel = panelFrom;
		seasonChooser.setLocation(793,122);//TODO 日期选择器坐标
		this.add(seasonChooser);
		
		addBack();	//返回按钮
		
		teamDetail = teamQuery.getTeamDetailByAbbr(abbr, Constants.LATEST_SEASON_REGULAR);
		profileVO = teamDetail.getProfile();
		
		addTitles();	//	标题，包括队名、联盟、几胜几负
		addRanks();	//	胜率、得分、篮板、助攻的联盟内排名
		addProfileLabels();		//简况label，包括所属赛区、主场、建队时间
		addLogo();	
		addTabButtons();	
		
		initiatePanel();
	}
	
	/** 进入球队界面的初始子界面是数据王 */
	private void initiatePanel() {
		kingTab.setOn();
		lineupTab.setOff();
		seasonTab.setOff();
		matchTab.setOff();
		
		kingPanel = new TeamKingPanel(teamQuery, abbr);
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
		back = new ImgButton(Images.RETURN_BTN, UIConfig.RETURN_X, UIConfig.RETURN_Y, Images.RETURN_BTN_ON);
		this.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				MainController.backToOnePanel(fromPanel);
			}
		});
	}


	/** 标题，包括队名、所属联盟、几胜几负 */		//TODO 现在这些Label的Location应该都要变一变了
	private void addTitles() {
		JLabel nameLabel = new JLabel(Constants.translateTeamAbbrToLocation(abbr) + " " +
				Constants.translateTeamAbbr(abbr));
		nameLabel.setFont(TITLE_NAME_FONT);
		nameLabel.setBounds(MID_LABEL_COLUMN_X, FIRST_LABEL_ROW_Y, 300, 50);	
								//TODO 300 50这样的size是为了留出足够大的空间，好像没什么负面影响
		this.add(nameLabel);
		
		JLabel leagueLabel = new JLabel(Constants.translateLeague(profileVO.getArea()));
		leagueLabel.setBounds(372, LEAGUE_LABEL_ROW_Y, 60, 20);	//TODO "东部联盟"这四个字
		leagueLabel.setFont(MyFont.YH_S);	//TODO 这个字体用在这种地方，放在UIConfig里面是为了和其他界面上类似的内容保持一致
		leagueLabel.setForeground(MyFont.DARK_GRAY);
		this.add(leagueLabel);

		TeamSeasonPO seasonVO = teamDetail.getSeasonRecord();
		winsLosesLabel = new TeamWinsLosesLabel(seasonVO.getWins(), (seasonVO.matchCount - seasonVO.wins));
		winsLosesLabel.setBounds(330, MID_LABEL_ROW_Y,300,50);
//		winsLosesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(winsLosesLabel);	
	}
	
	/** 添加所属赛区、主场、建队时间labels */
	private void addProfileLabels() {
		profileLabels = new JLabel[3];
		TeamProfileVO profileVO = teamDetail.getProfile();
		String [] profileStr = new String[] {Constants.homeText + profileVO.getHome(),
				Constants.divisionText + profileVO.getDivisionString(),
				Constants.sinceText + profileVO.getSince()};
		for (int i=0;i<3;i++) {
			profileLabels[i] = new JLabel(profileStr[i]);
			profileLabels[i].setFont(UIConfig.LABEL_SMALL_FONT);	//TODO 这个字体用在这种地方，放在UIConfig里面是为了和其他界面上类似的内容保持一致
			profileLabels[i].setForeground(MyFont.LIGHT_GRAY);
			if (i == 0) {
				profileLabels[i].setBounds(MID_LABEL_COLUMN_X, MID_LABEL_ROW_Y, 300, 30);
			}
			else{
				profileLabels[i].setBounds(RIGHT_LABEL_COLUMN_X, MID_LABEL_ROW_Y + UIConfig.PROFILE_LABEL_INTER_Y * (i-1),
					300, 30);
			}
			this.add(profileLabels[i]);
		}
	}
	
	/** 添加联盟内排名、得分、篮板、助攻排名的label */
	private void addRanks() {
		int [] ranks = teamQuery.getRanks(abbr, Constants.LATEST_SEASON_REGULAR);
		int interX = 80;
		
		rankLabel = new JLabel(Utility.getRankStr(ranks[0]));
		rankLabel.setFont(UIConfig.LABEL_PLAIN_FONT);	//TODO 这个字体跟总排名的字体是一样的吧
		rankLabel.setForeground(UIConfig.BLUE_TEXT_COLOR);	//TODO这个颜色我把它命名为“排名蓝”
		rankLabel.setBounds(440, LEAGUE_LABEL_ROW_Y-3, 40, 30);
		this.add(rankLabel);
		
		//TODO 三个Label的位置。至于每个Label内部的“场均得分”，得分数值，和"nd"三个字符串的位置，见TeamScoreReboundAssistLabel.java

		scoreLabel = new TeamScoreReboundAssistLabel(Constants.scoreAvgText, ranks[1]);
		scoreLabel.setLocation(MID_LABEL_COLUMN_X, SCORE_REBOUND_ASSIST_Y);
		this.add(scoreLabel);
		
		reboundLabel = new TeamScoreReboundAssistLabel(Constants.reboundAvgText, ranks[2]);
		reboundLabel.setLocation(MID_LABEL_COLUMN_X + interX, SCORE_REBOUND_ASSIST_Y);
		this.add(reboundLabel);
		
		assistLabel = new TeamScoreReboundAssistLabel(Constants.assistAvgText, ranks[3]);
		assistLabel.setLocation(MID_LABEL_COLUMN_X + interX * 2, SCORE_REBOUND_ASSIST_Y);
		this.add(assistLabel);
	}
	
	private void addLogo() {	//TODO logo的位置
		ImgLabel logoLabel = new ImgLabel(100,-5,180,180, TeamLogoCache.getTeamLogo(abbr));
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
		lineupTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kingTab.setOff();
				lineupTab.setOn();
				seasonTab.setOff();
				matchTab.setOff();
				if (lineupPanel == null) {
					lineupPanel = new TeamLineupPanel(teamDetail.getPlayers());
				}
				switchPanel(lineupPanel);
			}
		});
		lineupTab.setLocation(25 + TABS_INTER_X, TABS_Y);
		this.add(lineupTab);
		
		seasonTab = new TabButton(Constants.seasonDataText, Images.TEAM_FIRST_LEVEL_TAB_MOVE_ON, Images.TEAM_FIRST_LEVEL_TAB_CHOSEN);
		seasonTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kingTab.setOff();
				lineupTab.setOff();
				seasonTab.setOn();
				matchTab.setOff();
				if (seasonPanel == null) {
					String season = seasonChooser.getSeason();
					seasonPanel = new TeamSeasonPanel();
					switchPanel(seasonPanel);
					seasonPanel.updateContent(season, teamDetail.getSeasonRecord(), 
							teamQuery.getFiveArgsAvg(season), teamQuery.getHighestScoreReboundAssist(season));
				}else {
					switchPanel(seasonPanel);
				}
				
			}
		});
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
					matchPanel = new TeamMatchPanel(abbr);
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
		String season = seasonChooser.getSeason();
		teamDetail = teamQuery.getTeamDetailByAbbr(abbr, season);
		if (teamDetail == null) return;
		TeamSeasonPO seasonPO = teamDetail.getSeasonRecord();
		
		if (kingPanel != null)
			kingPanel.updateContent(season);
		if (lineupPanel != null)
			lineupPanel.updateContent(teamDetail.getPlayers());
		if (seasonPanel != null) seasonPanel.updateContent(season, teamDetail.getSeasonRecord(), 
					teamQuery.getFiveArgsAvg(season), teamQuery.getHighestScoreReboundAssist(season));
		if (matchPanel != null) matchPanel.updateContent(teamDetail.getMatchRecords());
		
		int[] ranks = teamQuery.getRanks(abbr, season);
		rankLabel.setText(Utility.getRankStr(ranks[0]));
		scoreLabel.update(ranks[1]);
		reboundLabel.update(ranks[2]);
		assistLabel.update(ranks[3]);
		winsLosesLabel.update(seasonPO.getWins(), seasonPO.getMatchCount() - seasonPO.getWins());
	}
	
	//TODO 测试代码
		public static void main(String[]args) {
			Frame frame = new Frame();
			MainController.frame = frame;
			new TeamLogoCache().loadLogos();
			frame.setPanel(new TeamBottomPanel(null, "OKC"));
			frame.start();
		}
}
