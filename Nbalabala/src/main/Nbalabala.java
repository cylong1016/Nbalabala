package main;

import data.playerdata.PlayerPortraitCache;
import ui.controller.MainController;

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
		new PlayerPortraitCache().loadPortrait();
		MainController.launch();
	}
}
