package main;

import ui.controller.MainController;
import data.playerdata.PlayerImageCache;
import data.teamdata.TeamLogoCache;

/**
 * Nbalabala程序主入口
 * @author dxh
 * @author zj
 * @author cylong
 * @author lsy
 * @version 2015年3月16日 上午12:46:54
 */
public class Nbalabala {

	public static void main(String[] args) {
		MainController.launch();
		new PlayerImageCache().loadPortrait();
		new TeamLogoCache().loadLogos();

//		Constants.setEnglish();
	}
}
