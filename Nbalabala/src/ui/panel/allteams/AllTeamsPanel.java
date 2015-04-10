package ui.panel.allteams;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import utility.Constants;

/**
 * 全部球队信息的主界面
 * 
 * @author cylong
 * @version 2015年3月19日 上午3:20:03
 */
public class AllTeamsPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2125340581988541306L;
	/** 横纵坐标 间隔 */
	private static final int X1 = 220, X2 = 668, Y1 = 117, Y2 = 271, Y3 = 426, INTER = 27;
	
	private TeamButton[] buttonArr = new TeamButton[30];
	private MouListener mou = new MouListener();
	
	/**
	 * @param url
	 *            全部球队信息背景图的url
	 */
	public AllTeamsPanel(String url) {
		super(url);
		setButton();
		addButton();
	}

	public void setButton() {
		for (int i = 0; i < 5; i++) {
			buttonArr[i] = new TeamButton(X1, Y1 + i * INTER);
		}
		for (int i = 5; i < 10; i++) {
			buttonArr[i] = new TeamButton(X1, Y2 + (i - 5) * INTER);
		}
		for (int i = 10; i < 15; i++) {
			buttonArr[i] = new TeamButton(X1, Y3 + (i - 10) * INTER);
		}
		for (int i = 15; i < 20; i++) {
			buttonArr[i] = new TeamButton(X2, Y1 + (i - 15) * INTER);
		}
		for (int i = 20; i < 25; i++) {
			buttonArr[i] = new TeamButton(X2, Y2 + (i - 20) * INTER);
		}
		for (int i = 25; i < 30; i++) {
			buttonArr[i] = new TeamButton(X2, Y3 + (i - 25) * INTER);
		}
	}

	public void addButton() {
		for (int i = 0; i < 30; i++) {
			this.add(buttonArr[i]);
			buttonArr[i].team=Constants.TEAM_ABBR[i];
			buttonArr[i].addMouseListener(mou);
		}
	}

	class MouListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			TeamButton buttonSelect = (TeamButton) e.getSource();
			MainController.toTeamSeasonPanel(AllTeamsPanel.this,AllTeamsPanel.this, buttonSelect.team,0);
		}

	}

}
