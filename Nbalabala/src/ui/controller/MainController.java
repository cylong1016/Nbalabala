package ui.controller;

import ui.UIConfig;
import ui.common.frame.Frame;
import ui.common.panel.Panel;
import ui.panel.main.MainPanel;
import ui.panel.playerPanel.PlayerDataPanel;
import ui.panel.teamdata.TeamDataPanel;

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

	/**
	 * 进入球队数据界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:38
	 */
	public void toTeamPanel(Panel panel) {
		frame.remove(panel);
		teamDataPanel = new TeamDataPanel(UIConfig.imgPath + "teamData/teamDataBG.png");
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
		playerDataPanel = new PlayerDataPanel(UIConfig.imgPath + "playerData/playerDataBG.png");
		frame.setPanel(playerDataPanel);
	}

	/**
	 * 进入比赛数据界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:51
	 */
	public void toGamePanel(Panel panel) {
		System.out.println("进入比赛数据界面");
	}

	/**
	 * 进入所有球员界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:55
	 */
	public void toAllPlayersPanel(Panel panel) {
		System.out.println("进入所有球员界面");
	}

	/**
	 * 进入所有球队界面
	 * @param panel 从哪一界面跳转
	 * @author cylong
	 * @version 2015年3月18日 上午11:11:58
	 */
	public void toAllTeamsPanel(MainPanel panel) {
		System.out.println("进入所有球队界面");
	}

}
