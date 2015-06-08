package ui.panel.gamedata.live;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.Images;
import ui.common.button.TabButton;
import ui.common.panel.Panel;
import ui.panel.gamedata.GameFatherPanel;
import utility.Constants;
import vo.MatchDetailVO;
import blservice.LiveBLService;

/**
 * 直播界面
 * 
 * @author lsy
 * @version 2015年6月8日 下午9:07:28
 */
public class LivePanel extends GameFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -1777953751279202293L;
	private TabButton tech, live, contrastbt;
	private LiveBelowPanel liveBelow;
	private MatchDetailVO matchDetail;
	private LiveBLService liveService;
	private ConPanel conPanel;
	private TechPanel techPanel;

	public LivePanel(String url, Panel gameData) {
		super(url);
		addButton();
//		liveService = new Live();
		liveBelow = new LiveBelowPanel(Images.LIVE_BELOW, matchDetail);
		conPanel = new ConPanel();
		techPanel = new TechPanel(liveService.getHomePlayerRecords(),liveService.getRoadPlayerRecords(),
				LivePanel.this);
	}

	public void addButton() {
		tech = new TabButton(Constants.LIVE[0], Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		live = new TabButton(Constants.LIVE[1], Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		contrastbt = new TabButton(Constants.LIVE[2], Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		tech.setLocation(btx, bty);
		live.setLocation(btx + inter, bty);
		contrastbt.setLocation(btx + 2 * inter, bty);
		tech.setOn();
		this.add(tech);
		this.add(live);
		this.add(contrastbt);
		tech.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				tech.setOn();
				live.setOff();
				contrastbt.setOff();
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
				LivePanel.this.remove(liveBelow);
				LivePanel.this.remove(techPanel);
				LivePanel.this.add(conPanel);
				LivePanel.this.repaint();
			}
		});
	}
	
	public void refresh(){
		
	}

	class ThreadDis extends Thread {

		public void run() {
			while (true) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(liveService.isAnythingNew()){
					refresh();
				}
			}
		}
	}

}
