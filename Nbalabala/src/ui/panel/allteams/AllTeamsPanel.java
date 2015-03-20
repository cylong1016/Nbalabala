package ui.panel.allteams;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;

/**
 * 全部球队信息的主界面
 * 
 * @author cylong
 * @version 2015年3月19日 上午3:20:03
 */
public class AllTeamsPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2125340581988541306L;
	TeamButton[] buttonArr = new TeamButton[30];
	/** 横纵坐标 间隔 */
	private int x1 = 220, x2 = 668, y1 = 117, y2 = 271, y3 = 426, inter = 27;
	private String url = UIConfig.IMG_PATH + "teams/mouseOn.png";
	private String beforeURL = UIConfig.IMG_PATH + "teams/tran.png";
	MouListener mou = new MouListener();

	/**
	 * @param url
	 *            全部球队信息背景图的url
	 */
	public AllTeamsPanel(MainController controller, String url) {
		super(controller, url);
		setButton();
		addButton();
	}

	public void setButton() {
		for (int i = 0; i < 5; i++) {
			buttonArr[i] = new TeamButton(beforeURL, x1, y1 + i * inter, url, "");
		}
		for (int i = 5; i < 10; i++) {
			buttonArr[i] = new TeamButton(beforeURL, x1, y2 + (i - 5) * inter, url, url);
		}
		for (int i = 10; i < 15; i++) {
			buttonArr[i] = new TeamButton(beforeURL, x1, y3 + (i - 10) * inter, url, url);
		}
		for (int i = 15; i < 20; i++) {
			buttonArr[i] = new TeamButton(beforeURL, x2, y1 + (i - 15) * inter, url, url);
		}
		for (int i = 20; i < 25; i++) {
			buttonArr[i] = new TeamButton(beforeURL, x2, y2 + (i - 20) * inter, url, url);
		}
		for (int i = 25; i < 30; i++) {
			buttonArr[i] = new TeamButton(beforeURL, x2, y3 + (i - 25) * inter, url, url);
		}
	}

	public void addButton() {
		for (int i = 0; i < 30; i++) {
			this.add(buttonArr[i]);
			buttonArr[i].which=i;
			buttonArr[i].addMouseListener(mou);
		}
	}

	class MouListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
		}

	}

}
