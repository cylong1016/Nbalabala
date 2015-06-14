package ui.controller;

import javax.swing.JPanel;

import ui.Images;
import ui.UIConfig;
import ui.common.frame.Frame;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.panel.allplayers.AllPlayersPanel;
import ui.panel.allplayers.PlayerInfoBottomPanel;
import ui.panel.allteams.AllTeamsPanel;
import ui.panel.allteams.TeamBottomPanel;
import ui.panel.gamedata.GameDataPanel;
import ui.panel.gamedata.GamePanel;
import ui.panel.gamedata.live.LiveInPanel;
import ui.panel.gamedata.live.LivePanel;
import ui.panel.hot.hotFast.HotFastPanel;
import ui.panel.hot.hotSeason.HotSeasonPlayerPanel;
import ui.panel.hot.hotSeason.HotSeasonTeamPanel;
import ui.panel.hot.hotTodayPlayer.HotTodayPlayerPanel;
import ui.panel.main.MainPanel;
import ui.panel.playerData.PlayerDataPanel;
import ui.panel.teamdata.TeamDataPanel;
import utility.Utility;
import vo.MatchDetailVO;

/**
 * 界面跳转控制
 * @author cylong
 * @version 2015年3月16日 下午7:21:56
 */
public class MainController {

	/** 主Frame */
	public static Frame frame;
	/** 主界面 */
	private static MainPanel mainPanel;
	/** 球队数据界面 */
	private static TeamDataPanel teamDataPanel;
	/** 球员数据界面 */
	private static PlayerDataPanel playerDataPanel;
	/** 全部球员界面 */
	private static AllPlayersPanel allPlayersPanel;
	/** 全部球队界面 */
	private static AllTeamsPanel allTeamsPanel;
	/** 比赛数据界面 */
	private static GameDataPanel gameDataPanel;
	/** 某场比赛数据 */
	private static GamePanel gamePanel;
	/** 球员详细信息 */
	private static PlayerInfoBottomPanel playerInfoPanel;
	/** 当天热点球员界面 */
	private static HotTodayPlayerPanel hotTodayPlayerPanel;
	/** 赛季热点球员界面 */
	private static HotSeasonPlayerPanel hotSeasonPlayerPanel;
	/** 赛季热点球队界面 */
	private static HotSeasonTeamPanel hotSeasonTeamPanel;
	/** 进步最快球员界面 */
	private static HotFastPanel hotFastPanel;
	/** 球队信息界面 */
	private static TeamBottomPanel teamBottomPanel;	
	/** 进入直播界面 */
	private static LiveInPanel liveInPanel;
	/** 正在进行直播的界面 */
	private static LivePanel livePanel;
	/**
	 * 初始化主界面
	 * @author cylong
	 * @version 2015年3月16日 下午8:13:55
	 */
	public static void launch() {
		frame = new Frame();
		mainPanel = new MainPanel();
		frame.setPanel(mainPanel);
//		frame.setPanel(new AnalysePanel("images2.0/analysis/analysisBG.png","LAK","Kobe Byrant"));
//		frame.setPanel(new LivePanel("images2.0/games/gamesBG.png"));
//		frame.setPanel(new LiveInPanel("images2.0/games/liveinBG.png"));
		frame.start();
	}
	
	public MainController(){
		
	}

	public static void toMainPanel() {
		mainPanel = new MainPanel();
		frame.setPanel(mainPanel);
	}

	/**
	 * 进入球队数据界面
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:38
	 */
	public static void toTeamPanel() {
		teamDataPanel = new TeamDataPanel("images2.0/teamsData/TeamsDataBG.png");
		frame.setPanel(teamDataPanel);
	}

	/**
	 * 进入球员数据界面
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:46
	 */
	public static void toPlayerPanel() {
		playerDataPanel = new PlayerDataPanel("images2.0/playersData/PlayersDataBG.png");
		frame.setPanel(playerDataPanel);
	}

	/**
	 * 进入比赛数据界面
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:51
	 */
	public static void toGamePanel() {
		gameDataPanel = new GameDataPanel(UIConfig.IMG_PATH_2 + "gameData/GameDataBG.png");
		frame.setPanel(gameDataPanel);
	}
	
	/**
	 * 根据球队缩写获得比赛记录
	 * @param teamAbbr1
	 * @param teamAbbr2
	 * @author cylong
	 * @version 2015年6月14日  下午3:42:23
	 */
	public static void toGameLivePanel(BottomPanel panel,String teamAbbr1,String teamAbbr2) {
		gameDataPanel = new GameDataPanel(panel,UIConfig.IMG_PATH_2 + "gameData/GameDataBG.png", teamAbbr1, teamAbbr2);
		frame.setPanel(gameDataPanel);
	}
	

	/**
	 * 进入所有球员界面
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:55
	 */
	public static void toAllPlayersPanel() {
		allPlayersPanel = new AllPlayersPanel(Images.ALL_PLAYERS_BG_STR);
		frame.setPanel(allPlayersPanel);
	}

	/**
	 * 进入所有球队界面
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:58
	 */
	public static void toAllTeamsPanel() {
		allTeamsPanel = new AllTeamsPanel(Images.ALL_TEAMS_BG_STR);
		frame.setPanel(allTeamsPanel);
	}

	public static void toTeamBottomPanel(BottomPanel from, String abbr) {
		teamBottomPanel = new TeamBottomPanel(from, Utility.getCurrentAbbr(abbr));
		frame.setPanel(teamBottomPanel);
	}
	
	/**
	 * 添加日期选择器
	 * @author lsy
	 * @version 2015年3月21日 下午1:53:17
	 */
	public static void addDateChooserPanel(JPanel panelBottom, JPanel panelAdd, int x, int y,int width,int height) {
		panelAdd.setVisible(true);
		panelAdd.setBounds(x, y, width, height);
		panelBottom.add(panelAdd);
	}

	/**
	 * 某场比赛数据界面
	 * @author lsy
	 * @version 2015年3月21日 下午5:03:21
	 */
	public static void toOneGamePanel(MatchDetailVO matchDetail,Panel panel) {
		gamePanel = new GamePanel("images2.0/games/gamesBG.png", matchDetail,panel);
//		LivePanel gamePanel = new LivePanel("images2.0/games/gamesBG.png",gameData);
		frame.setPanel(gamePanel);
	}

	/**
	 * 返回到某一界面
	 * @author lsy
	 * @version 2015年3月24日 上午10:28:19
	 */
	public static void backToOnePanel(Panel panelTo) {
		frame.setPanel(panelTo);
		panelTo.refresh();
		frame.repaint();
	}

	/**
	 * 跳到球员详细信息
	 * @author lsy
	 * @version 2015年3月24日 上午11:19:21
	 */
	public static void toPlayerInfoPanel(String name, BottomPanel allPanel) {
		playerInfoPanel = new PlayerInfoBottomPanel(name, allPanel);
		frame.setPanel(playerInfoPanel);
	}
	
	
	public static void refreshUI() {
		Frame.currentPanel.refresh();
	}
	
	public static void toHotPanel(){
		hotTodayPlayerPanel = new HotTodayPlayerPanel(UIConfig.IMG_PATH_2 + "hot/hotTodayBG.png");
		frame.setPanel(hotTodayPlayerPanel);
		frame.repaint();
	}

	public static void toHotSeasonPlayerPanel(){
		hotSeasonPlayerPanel = new HotSeasonPlayerPanel(UIConfig.IMG_PATH_2 + "hot/hotSeasonBG.png");
		frame.setPanel(hotSeasonPlayerPanel);
		frame.repaint();
	}
	
	public static void toHotSeasonTeamPanel(){
		hotSeasonTeamPanel = new HotSeasonTeamPanel(UIConfig.IMG_PATH_2 + "hot/hotSeasonBG.png");
		frame.setPanel(hotSeasonTeamPanel);
		frame.repaint();
	}
	
	public static void toHotFastPanel(){
		hotFastPanel = new HotFastPanel(UIConfig.IMG_PATH_2 + "hot/hotFastestBG.png");
		frame.setPanel(hotFastPanel);
		frame.repaint();
	}
	
	public static void toGameDetailPanel(int matchID, Panel fromPanel) {
		gamePanel = new GamePanel(matchID, fromPanel);
		frame.setPanel(gamePanel);
		frame.repaint();
	}
	
	public static void toLiveInPanel() {
		liveInPanel = new LiveInPanel("images2.0/games/liveinBG.png");
		frame.setPanel(liveInPanel);
		frame.repaint();
	}
	
	public static void toLivePanel() {
		livePanel = new LivePanel("images2.0/games/gamesBG.png");
		frame.setPanel(livePanel);
		frame.repaint();
	}

}

