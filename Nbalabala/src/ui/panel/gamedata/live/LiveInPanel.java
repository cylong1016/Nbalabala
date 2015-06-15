package ui.panel.gamedata.live;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import ui.UIConfig;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import utility.Constants;
import bl.livebl.Live;
import blservice.LiveBLService;

/**
 * 直播初始界面
 * 
 * @author lsy
 * @version 2015年6月13日 上午10:59:47
 */
public class LiveInPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -4213351061854735529L;
	private LiveBLService live;
	private MyLabel date, time;
	private MyLabel[] gameDetail;
	private int[] gameDetail_x = { 205, 325, 405, 480, 620, 750 };
	private Iterator<String> iter;
	private HashMap<String, String> liveList;

	private static final Font PLAIN_FONT = new Font("微软雅黑", Font.PLAIN, 16);
	private static final Font BOLD_FONT = new Font("微软雅黑", Font.BOLD, 16);

	// 06月15日 08：00=季后赛 勇士-骑士, 06月17日 09：00=季后赛 骑士-勇士
	public LiveInPanel(String url) {
		super(url);
		live = new LiveMock();
		gameDetail = new MyLabel[12];
		live.refresh();
		readHash();
	}

	public void addLabel(final int index) {
		String key = iter.next();
		String value = liveList.get(key);
		String[] keyArr = key.split(" ");
		String[] valueArr = value.split(" ");
		final String[] team = valueArr[1].split("-");
		String[] line2 = { valueArr[0], team[0], "VS", team[1], Constants.history, Constants.waitlive };
		for (int i = 0; i < 6; i++) {
			gameDetail[i + 6 * index] = new MyLabel(gameDetail_x[i], 140 + 81 * index, 100, 50, line2[i]);
			this.add(gameDetail[i + 6 * index]);
			gameDetail[i + 6 * index].setFont(PLAIN_FONT);
		}
		gameDetail[1 + 6 * index].setForeground(UIConfig.BLUE_TEXT_COLOR);
		gameDetail[3 + 6 * index].setForeground(UIConfig.BLUE_TEXT_COLOR);
		gameDetail[4 + 6 * index].setForeground(Color.orange);
		gameDetail[5 + 6 * index].setForeground(Color.gray);

		gameDetail[1 + 6 * index].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MainController.toTeamBottomPanel(LiveInPanel.this, Constants.getAbbrByName(team[0]));
				;
			}

			public void mouseEntered(MouseEvent e) {
				gameDetail[1 + 6 * index].setFont(BOLD_FONT);
			}

			public void mouseExited(MouseEvent e) {
				gameDetail[1 + 6 * index].setFont(PLAIN_FONT);
			}
		});
		gameDetail[3 + 6 * index].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MainController.toTeamBottomPanel(LiveInPanel.this, Constants.getAbbrByName(team[1]));
			}

			public void mouseEntered(MouseEvent e) {
				gameDetail[3 + 6 * index].setFont(BOLD_FONT);
			}

			public void mouseExited(MouseEvent e) {
				gameDetail[3 + 6 * index].setFont(PLAIN_FONT);
			}
		});

		gameDetail[4 + 6 * index].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MainController.toGameLivePanel(LiveInPanel.this, Constants.getAbbrByName(team[0]),
						Constants.getAbbrByName(team[1]));
			}

			public void mouseEntered(MouseEvent e) {
				gameDetail[4 + 6 * index].setFont(BOLD_FONT);
			}

			public void mouseExited(MouseEvent e) {
				gameDetail[4 + 6 * index].setFont(PLAIN_FONT);
			}
		});

		date = new MyLabel(25, 110 + 81 * index, 100, 30, keyArr[0]);
		time = new MyLabel(25, 140 + 81 * index, 100, 50, keyArr[1]);
		this.add(date);
		this.add(time);

		if (live.hasMatchStarted() && index == 0) {
			gameDetail[5].setlbText(Constants.tolive);
			gameDetail[5].setForeground(Color.orange);
			gameDetail[5].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					MainController.toLivePanel();
				}

				public void mouseEntered(MouseEvent e) {
					gameDetail[5].setFont(BOLD_FONT);
				}

				public void mouseExited(MouseEvent e) {
					gameDetail[5].setFont(PLAIN_FONT);
				}
			});
		}
	}

	public void readHash() {
		liveList = live.getLiveList();
		Set<String> keySet = liveList.keySet();
		iter = keySet.iterator();
		addLabel(0);
		addLabel(1);
	}
}
