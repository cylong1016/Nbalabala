package ui.panel.gamedata.live;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.Images;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.button.TabButton;
import ui.common.panel.ScorePanel;
import ui.controller.MainController;
import ui.panel.gamedata.ConPanel;
import ui.panel.gamedata.GameFatherPanel;
import utility.Constants;
import vo.LivePlayerVO;
import bl.livebl.Live;
import blservice.LiveBLService;

/**
 * 直播界面
 * @author lsy
 * @version 2015年6月8日 下午9:07:28
 */
public class LivePanel extends GameFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -1777953751279202293L;
	private TabButton tech, live, contrastbt;
	private LiveBelowPanel liveBelow;
	private LiveBLService liveService;
	private ConPanel conPanel;
	private TechPanel techPanel;
	private ArrayList<LivePlayerVO> vo1, vo2;
	private int currentI;
	private ArrayList<String> text;
	private double[] homePlayersArgs, roadPlayersArgs;
	private String teamAbbr1, teamAbbr2;

	public LivePanel(String url) {
		super(url);
		liveService = new Live();
		liveService.refresh();
		init();
		text = liveService.getTextLive();
		vo1 = liveService.getHomePlayerRecords();
		vo2 = liveService.getRoadPlayerRecords();
		homePlayersArgs = liveService.getHomeFiveArgs();
		roadPlayersArgs = liveService.getRoadFiveArgs();
		teamAbbr1 = liveService.getHomeAbbr();
		teamAbbr2 = liveService.getRoadAbbr();
		techPanel = new TechPanel(teamAbbr1, teamAbbr2, vo1, vo2, LivePanel.this);
		liveBelow = new LiveBelowPanel(teamAbbr1, teamAbbr2, text, Images.LIVE_BELOW);
		conPanel = new ConPanel(homePlayersArgs, roadPlayersArgs);
		this.add(liveBelow);
		currentI = 1;
		addButton();
		addBack();
		ThreadDis thread = new ThreadDis();
		thread.start();
	}

	private ImgButton back;
	public void addBack() {
		back = new ImgButton(UIConfig.IMG_PATH + "back.png", 70, 150, UIConfig.IMG_PATH + "backOn.png", UIConfig.IMG_PATH + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				MainController.toLiveInPanel();
			}

		});
	}
	
	public void init() {
		ArrayList<Integer> roadScores = liveService.getRoadScores();
		ArrayList<Integer> homeScores = liveService.getHomeScores();
		int size = homeScores.size();
		if (size > 0) {
			while(size < 5) {
				roadScores.add(size - 1, 0);
				homeScores.add(size - 1, 0);
				size++;
			}
			String homeName = liveService.getHomeAbbr();
			String roadName = liveService.getRoadAbbr();
			System.out.println(homeName);
			String total = homeScores.get(size - 1).toString() + "-" + roadScores.get(size - 1).toString();
			String each = "";
			for(int i = 0; i < size - 1; i++) {
				each = each + homeScores.get(i) + "-" + roadScores.get(i) + ";";
			}

			String[] totalScore = new String[]{homeScores.get(size - 1).toString(), roadScores.get(size - 1).toString()};
			addLabel(homeName, roadName, totalScore);
			scPanel = new ScorePanel(homeName, roadName, total, each);
			this.add(scPanel);
			scPanel.setLocation(188, 110);
			this.repaint();
		}

	}

	public void changelbText() {
		ArrayList<Integer> roadScores = liveService.getRoadScores();
		ArrayList<Integer> homeScores = liveService.getHomeScores();
		int size = homeScores.size();
		while(size < 5) {
			roadScores.add(size - 1, 0);
			;
			homeScores.add(size - 1, 0);
			size++;
		}
		String[] totalScore = new String[]{roadScores.get(size - 1).toString(), homeScores.get(size - 1).toString()};
		String total = roadScores.get(size - 1).toString() + "-" + homeScores.get(size - 1).toString();
		String each = "";
		for(int i = 0; i < size - 1; i++) {
			each = each + homeScores.get(i) + "-" + roadScores.get(i) + ";";
		}
		setLabel(totalScore);
		setScorelb(total, each);

		//		setLabel(new String[]{"200","100"});
		//		setScorelb("170-153","24-35;3-13;12-25;43-30;45-46");
	}

	public void addButton() {
		tech = new TabButton(Constants.LIVE[0], Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		live = new TabButton(Constants.LIVE[1], Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		contrastbt = new TabButton(Constants.LIVE[2], Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		tech.setLocation(btx, bty);
		live.setLocation(btx + inter, bty);
		contrastbt.setLocation(btx + 2 * inter, bty);
		live.setOn();
		this.add(tech);
		this.add(live);
		this.add(contrastbt);
		tech.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				tech.setOn();
				live.setOff();
				contrastbt.setOff();
				currentI = 0;
				LivePanel.this.remove(liveBelow);
				LivePanel.this.remove(conPanel);
				LivePanel.this.add(techPanel);
				LivePanel.this.repaint();
			}
		});
		live.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				tech.setOff();
				live.setOn();
				contrastbt.setOff();
				currentI = 1;
				LivePanel.this.remove(conPanel);
				LivePanel.this.remove(techPanel);
				LivePanel.this.add(liveBelow);
				LivePanel.this.repaint();
			}
		});

		contrastbt.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				tech.setOff();
				live.setOff();
				contrastbt.setOn();
				currentI = 2;
				LivePanel.this.remove(liveBelow);
				LivePanel.this.remove(techPanel);
				LivePanel.this.add(conPanel);
				LivePanel.this.repaint();
			}
		});
	}

	int index = 0;

	public void refresh() {
		changelbText();
		if (currentI == 0) {
			this.remove(techPanel);
		} else if (currentI == 1) {
			this.remove(liveBelow);
		} else {
			this.remove(conPanel);
		}
		index++;
		conPanel = new ConPanel(liveService.getHomeFiveArgs(), liveService.getRoadFiveArgs());
		ArrayList<LivePlayerVO> homeplayers = liveService.getHomePlayerRecords();
		ArrayList<LivePlayerVO> roadplayers = liveService.getRoadPlayerRecords();
		if (TechPanel.CURRENTI == 0) {
			techPanel.updateHomeTable(homeplayers);
		} else {
			techPanel.updateRoadTable(roadplayers);
		}
		ArrayList<String> text = liveService.getTextLive();
		liveBelow.updateTable(text);
		if (currentI == 0) {
			this.add(techPanel);
		} else if (currentI == 1) {
			this.add(liveBelow);
		} else {
			this.add(conPanel);
		}
		this.repaint();
	}

	class ThreadDis extends Thread {

		public void run() {
			while(true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				liveService.refresh();
				refresh();
			}
		}
	}

}
