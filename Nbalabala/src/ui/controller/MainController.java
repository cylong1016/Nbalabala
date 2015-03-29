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
import ui.panel.allteams.TeamButton;
import ui.panel.allteams.TeamGamePanel;
import ui.panel.allteams.TeamSeasonPanel;
import ui.panel.gamedata.GameDataPanel;
import ui.panel.gamedata.GamePanel;
import ui.panel.main.MainPanel;
import ui.panel.playerData.PlayerDataPanel;
import ui.panel.teamdata.TeamDataPanel;
import vo.MatchProfileVO;
import vo.PlayerProfileVO;

/**
 * 界面跳转控制
 * @author cylong
 * @version 2015年3月16日 下午7:21:56
 */
public class MainController {

	/** 主Frame */
	private Frame frame;
	/** 主界面 */
	private MainPanel mainPanel;
	/** 球队数据界面 */
	private TeamDataPanel teamDataPanel;
	/** 球员数据界面 */
	private PlayerDataPanel playerDataPanel;
	/** 全部球员界面 */
	private AllPlayersPanel allPlayersPanel;
	/** 全部球队界面 */
	private AllTeamsPanel allTeamsPanel;
	/** 比赛数据界面 */
	private GameDataPanel gameDataPanel;
	/** 球队赛季数据 */
	private TeamSeasonPanel teamSeasonpanel;
	/** 球队比赛数据 */
	private TeamGamePanel teamGamePanel;
	/** 某场比赛数据 */
	private GamePanel gamePanel;
	/** 球员详细信息 */
	private PlayerInfoPanel playerInfoPanel;

	/**
	 * 初始化主界面
	 * @author cylong
	 * @version 2015年3月16日 下午8:13:55
	 */
	public MainController() {
		frame = new Frame();
		mainPanel = new MainPanel(this);
		frame.setPanel(mainPanel);
		frame.start();
	}
	
	public void toMainPanel(Panel panel){
		frame.remove(panel);
		mainPanel = new MainPanel(this);
		frame.setPanel(mainPanel);
	}

	/**
	 * 进入球队数据界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:38
	 */
	public void toTeamPanel(Panel panel) {
		frame.remove(panel);
		teamDataPanel = new TeamDataPanel(this,UIConfig.IMG_PATH + "teamData/teamDataBG.png");
		frame.setPanel(teamDataPanel);
	}

	/**
	 * 进入球员数据界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:46
	 */
	public void toPlayerPanel(Panel panel) {
		frame.remove(panel);
		playerDataPanel = new PlayerDataPanel(this,UIConfig.IMG_PATH + "playerData/playerDataBG.png");
		frame.setPanel(playerDataPanel);
	}

	/**
	 * 进入比赛数据界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:51
	 */
	public void toGamePanel(Panel panel) {
		frame.remove(panel);
		gameDataPanel = new GameDataPanel(this,UIConfig.IMG_PATH + "gameData/gameDataBG.png");
		frame.setPanel(gameDataPanel);
	}

	/**
	 * 进入所有球员界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:55
	 */
	public void toAllPlayersPanel(Panel panel) {
		frame.remove(panel);
		allPlayersPanel = new AllPlayersPanel(this,UIConfig.IMG_PATH + "players/allPlayersBG.png");
		frame.setPanel(allPlayersPanel);
	}

	/**
	 * 进入所有球队界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:58
	 */
	public void toAllTeamsPanel(Panel panel) {
		frame.remove(panel);
		allTeamsPanel = new AllTeamsPanel(this,UIConfig.IMG_PATH + "teams/allTeam.png");
		frame.setPanel(allTeamsPanel);
	}
	
	/**
	 * 进入球队赛季情况界面
	 * @param panel 从哪一界面跳转 
	 * @param button 选择的球队按钮
	 * @author lsy
	 * @version 2015年3月20日  下午11:45:39
	 */
	public void toTeamSeasonPanel(AllTeamsPanel allteams,Panel panel,TeamButton button,int x){
		frame.remove(panel);
		teamSeasonpanel = new TeamSeasonPanel(allteams,this,"images/teams/teamSeasonBG.png",button,x);
		frame.setPanel(teamSeasonpanel);
	}
	
	/**
	 * 球队赛季数据界面
	 * @param panel
	 * @author lsy
	 * @version 2015年3月21日  上午12:31:15
	 */
	public void toTeamGamePanel(AllTeamsPanel allteams,Panel panel,TeamButton button) {
		frame.remove(panel);
		teamGamePanel = new TeamGamePanel(allteams,this,UIConfig.IMG_PATH + "teams/teamSeasonBG.png",button,2);
		frame.setPanel(teamGamePanel);
	}
	
	/**
	 * 添加日期选择器
	 * @param panel
	 * @author lsy
	 * @version 2015年3月21日  下午1:53:17
	 */
	public void addDateChooserPanel(JPanel panelBottom,JPanel panelAdd,int x,int y){
		panelAdd.setVisible(true);
		panelAdd.setBounds(x,y, 153,30);
		panelBottom.add(panelAdd);
	}

	/**
	 * 某场比赛数据图
	 * @author lsy
	 * @version 2015年3月21日  下午5:03:21
	 */
	public void toOneGamePanel(Panel panel,MatchProfileVO matchProfile,BottomPanel gameData){
		frame.remove(panel);
		gamePanel = new GamePanel(this,UIConfig.IMG_PATH+"game/gameBG.png",matchProfile,gameData);
		frame.setPanel(gamePanel);
	}
	
	/**
	 * 返回到某一界面
	 * @author lsy
	 * @version 2015年3月24日  上午10:28:19
	 */
	public void backToGameDataPanel(Panel panelNow,Panel panelTo){
		frame.remove(panelNow);
		frame.setPanel(panelTo);
		frame.repaint();
	}
	
	/**
	 * 球员详细信息界面
	 * @author lsy
	 * @version 2015年3月24日  上午11:19:21
	 */
	public void toPlayerInfoPanel(Panel panel,PlayerProfileVO vo,BottomPanel allPanel){
		frame.remove(panel);
		playerInfoPanel = new PlayerInfoPanel(this,UIConfig.IMG_PATH+"players/playerInfoBG.png",vo,allPanel);
		frame.setPanel(playerInfoPanel);
	}
	
	public void toPlayerSeasonInfoPanel(Panel panel,PlayerProfileVO vo,BottomPanel allPanel){
		frame.remove(panel);
		playerInfoPanel = new PlayerSeasonPanel(this,UIConfig.IMG_PATH+"players/playerGameBG.png",vo,allPanel);
		frame.setPanel(playerInfoPanel);
	}
	
}
