package ui.panel.gamedata;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.Images;
import ui.common.button.TabButton;
import ui.common.panel.Panel;
import utility.Constants;
import vo.MatchDetailVO;

/**
 * 直播界面
 * @author lsy
 * @version 2015年6月8日  下午9:07:28
 */
public class LivePanel extends GameFatherPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -1777953751279202293L;
	private TabButton tech,live,contrastbt;
	private LiveBelowPanel liveBelow;
	private MatchDetailVO matchDetail;
	
	public LivePanel(String url, MatchDetailVO matchDetail, Panel gameData) {
		super(url, matchDetail, gameData);
		addButton();
		this.matchDetail = matchDetail;
		liveBelow = new LiveBelowPanel(Images.LIVE_BELOW,matchDetail);
	}

	
	public void addButton() {
		tech = new TabButton(Constants.LIVE[0],Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		live = new TabButton(Constants.LIVE[1],Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		contrastbt = new TabButton(Constants.LIVE[2],Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		tech.setLocation(btx, bty);
		live.setLocation(btx +inter, bty);
		contrastbt.setLocation(btx + 2*inter, bty);
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
				LivePanel.this.repaint();
			}
		});
		live.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				tech.setOff();
				live.setOn();
				contrastbt.setOff();
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
				LivePanel.this.repaint();
			}
		});
	}
}
	

