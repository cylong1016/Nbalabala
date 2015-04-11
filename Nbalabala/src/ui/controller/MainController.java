package ui.controller;

import javax.swing.JPanel;

import ui.UIConfig;
import ui.common.frame.Frame;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.panel.allplayers.AllPlayersPanel;
import ui.panel.allplayers.PlayerInfoPanel;
import ui.panel.allplayers.PlayerSeasonPanel;
import ui.panel.allteams.AllTeamsPanel;
import ui.panel.allteams.TeamGamePanel;
import ui.panel.allteams.TeamSeasonPanel;
import ui.panel.gamedata.GameDataPanel;
import ui.panel.gamedata.GamePanel;
import ui.panel.hot.HotFastPanel;
import ui.panel.hot.HotSeasonPlayerPanel;
import ui.panel.hot.HotSeasonTeamPanel;
import ui.panel.hot.HotTodayPlayerPanel;
import ui.panel.main.MainPanel;
import ui.panel.playerData.PlayerDataPanel;
import ui.panel.teamdata.TeamDataPanel;
import vo.MatchProfileVO;

/**
 * 界面跳转控制
 * @author cylong
 * @version 2015年3月16日 下午7:21:56
 */
public class MainController {

	/** 主Frame */
	private static Frame frame;
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
	/** 球队赛季数据 */
	private static TeamSeasonPanel teamSeasonpanel;
	/** 球队比赛数据 */
	private static TeamGamePanel teamGamePanel;
	/** 某场比赛数据 */
	private static GamePanel gamePanel;
	/** 球员详细信息 */
	private static PlayerInfoPanel playerInfoPanel;
	/** 当天热点球员界面 */
	private static HotTodayPlayerPanel hotTodayPlayerPanel;
	/** 赛季热点球员界面 */
	private static HotSeasonPlayerPanel hotSeasonPlayerPanel;
	/** 赛季热点球队界面 */
	private static HotSeasonTeamPanel hotSeasonTeamPanel;
	/** 进步最快球员界面 */
	private static HotFastPanel hotFastPanel;
	/**
	 * 初始化主界面
	 * @author cylong
	 * @version 2015年3月16日 下午8:13:55
	 */
	public static void launch() {
		frame = new Frame();
		mainPanel = new MainPanel();
		frame.setPanel(mainPanel);
		frame.start();
	}
	
	public MainController(){
		
	}

	public static void toMainPanel(Panel panel) {
		frame.remove(panel);
		mainPanel = new MainPanel();
		frame.setPanel(mainPanel);
	}

	/**
	 * 进入球队数据界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:38
	 */
	public static void toTeamPanel(Panel panel) {
		frame.remove(panel);
		teamDataPanel = new TeamDataPanel(UIConfig.IMG_PATH + "teamData/teamDataBG.png");
		frame.setPanel(teamDataPanel);
	}

	/**
	 * 进入球员数据界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:46
	 */
	public static void toPlayerPanel(Panel panel) {
		frame.remove(panel);
		playerDataPanel = new PlayerDataPanel(UIConfig.IMG_PATH + "playerData/playerDataBG.png");
		frame.setPanel(playerDataPanel);
	}

	/**
	 * 进入比赛数据界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:51
	 */
	public static void toGamePanel(Panel panel) {
		frame.remove(panel);
		gameDataPanel = new GameDataPanel(UIConfig.IMG_PATH + "gameData/gameDataBG.png");
		frame.setPanel(gameDataPanel);
	}

	/**
	 * 进入所有球员界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:55
	 */
	public static void toAllPlayersPanel(Panel panel) {
		frame.remove(panel);
		allPlayersPanel = new AllPlayersPanel(UIConfig.IMG_PATH + "players/allPlayersBG.png");
		frame.setPanel(allPlayersPanel);
	}

	/**
	 * 进入所有球队界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:58
	 */
	public static void toAllTeamsPanel(Panel panel) {
		frame.remove(panel);
		allTeamsPanel = new AllTeamsPanel(UIConfig.IMG_PATH + "teams/allTeam.png");
		frame.setPanel(allTeamsPanel);
	}

	/**
	 * 进入球队赛季情况界面
	 * @param panel 从哪一界面跳转
	 * @param button 选择的球队按钮
	 * @author lsy
	 * @version 2015年3月20日 下午11:45:39
	 */
	public static void toTeamSeasonPanel(BottomPanel allteams, Panel panel, String abbr, int x) {
		frame.remove(panel);
		teamSeasonpanel = new TeamSeasonPanel(allteams, "images/teams/teamSeasonBG.png", abbr, x);
		frame.setPanel(teamSeasonpanel);
	}

	/**
	 * 球队赛季数据界面
	 * @param panel
	 * @author lsy
	 * @version 2015年3月21日 上午12:31:15
	 */
	public static void toTeamGamePanel(BottomPanel allteams, Panel panel,String abbr) {
		frame.remove(panel);
		teamGamePanel = new TeamGamePanel(allteams, UIConfig.IMG_PATH + "teams/teamSeasonBG.png", abbr, 2);
		frame.setPanel(teamGamePanel);
	}

	/**
	 * 添加日期选择器
	 * @param panel
	 * @author lsy
	 * @version 2015年3月21日 下午1:53:17
	 */
	public static void addDateChooserPanel(JPanel panelBottom, JPanel panelAdd, int x, int y,int width,int height) {
		panelAdd.setVisible(true);
		panelAdd.setBounds(x, y, width, height);
		panelBottom.add(panelAdd);
	}

	/**
	 * 某场比赛数据图
	 * @author lsy
	 * @version 2015年3月21日 下午5:03:21
	 */
	public static void toOneGamePanel(Panel panel, MatchProfileVO matchProfile, BottomPanel gameData) {
		frame.remove(panel);
		gamePanel = new GamePanel(UIConfig.IMG_PATH + "game/gameBG.png", matchProfile, gameData);
		frame.setPanel(gamePanel);
	}

	/**
	 * 返回到某一界面
	 * @author lsy
	 * @version 2015年3月24日 上午10:28:19
	 */
	public static void backToOnePanel(Panel panelNow, Panel panelTo) {
		frame.remove(panelNow);
		frame.setPanel(panelTo);
		panelTo.refresh();
		frame.repaint();
	}

	/**
	 * 球员详细信息界面
	 * @author lsy
	 * @version 2015年3月24日 上午11:19:21
	 */
	public static void toPlayerInfoPanel(Panel panel, String name, BottomPanel allPanel) {
		frame.remove(panel);
		playerInfoPanel = new PlayerInfoPanel(UIConfig.IMG_PATH + "players/playerInfoBG.png", name, allPanel);
		frame.setPanel(playerInfoPanel);
	}

	public static void toPlayerSeasonInfoPanel(Panel panel, String name, BottomPanel allPanel) {
		frame.remove(panel);
		playerInfoPanel = new PlayerSeasonPanel(UIConfig.IMG_PATH + "players/playerGameBG.png", name, allPanel);
		frame.setPanel(playerInfoPanel);
	}
	
	public static void refreshUI() {
		System.out.println("UI refreshed!");
	}
	
	public static void toHotPanel(Panel panel){
		frame.remove(panel);
		hotTodayPlayerPanel = new HotTodayPlayerPanel(UIConfig.IMG_PATH + "Hot/hotTodayBG.png");
		frame.setPanel(hotTodayPlayerPanel);
		frame.repaint();
	}

	public static void toHotSeasonPlayerPanel(Panel panel){
		frame.remove(panel);
		hotSeasonPlayerPanel = new HotSeasonPlayerPanel(UIConfig.IMG_PATH + "Hot/hotSeasonPlayerBG.png");
		frame.setPanel(hotSeasonPlayerPanel);
		frame.repaint();
	}
	
	public static void toHotSeasonTeamPanel(Panel panel){
		frame.remove(panel);
		hotSeasonTeamPanel = new HotSeasonTeamPanel(UIConfig.IMG_PATH + "Hot/hotSeasonTeamBG.png");
		frame.setPanel(hotSeasonTeamPanel);
		frame.repaint();
	}
	
	public static void toHotFastPanel(Panel panel){
		frame.remove(panel);
		hotFastPanel = new HotFastPanel(UIConfig.IMG_PATH + "Hot/hotFastestBG.png");
		frame.setPanel(hotFastPanel);
		frame.repaint();
	}
}

