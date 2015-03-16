package ui.controller;

import ui.common.frame.Frame;
import ui.panel.main.MainPanel;

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

	/**
	 * 初始化主界面
	 * @author cylong
	 * @version 2015年3月16日 下午8:13:55
	 */
	public MainController() {
		frame = new Frame();
		mainPanel = new MainPanel();
		frame.add(mainPanel);

		frame.setVisible(true);
		frame.start();
	}

}
