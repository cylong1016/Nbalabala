package ui.panel.gamedata;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.MatchPlayerPO;
import ui.Images;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.button.TabButton;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchDetailVO;

/**
 * 某场比赛数据界面
 * @author lsy
 * @version 2015年3月21日 下午4:01:02
 */
public class GamePanel extends GameFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5789708998830911573L;
	private  MatchDetailVO matchDetail;

	/** 球队中文全称 */
	private TabButton teambt1,teambt2,contrastbt;
	private ConPanel conPanel;
	private Boolean isScroll = true;
	private Panel gameData;
	
	public GamePanel(int matchID, Panel gameData) {
		super(UIConfig.IMG_PATH_2 + "games/gamesBG.png");
		this.gameData = gameData;
		this.matchDetail = matchQuery.getMatchDetailByID(matchID);
		initiate();
		addBack();
	}

	public GamePanel(String url, MatchDetailVO matchDetail, Panel gameData) {
		super(url,matchDetail);
		this.gameData = gameData;
		this.matchDetail = matchDetail;
		initiate();
		addBack();
	}
	
	private void initiate() {
		matchPro = matchDetail.getProfile();
		conPanel = new ConPanel(matchDetail);
		addButton();
		initSetTabel();
	}
	

	public void addBack() {
		back = new ImgButton(UIConfig.IMG_PATH + "back.png", 70, 150, UIConfig.IMG_PATH + "backOn.png", UIConfig.IMG_PATH + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				MainController.backToOnePanel(gameData);
			}

		});
	}
	

	public void initSetTabel() {
		ArrayList<MatchPlayerPO> homePlayers = matchDetail.getHomePlayers();
		setTable(homePlayers);
	}

	public void addButton() {
		teambt1 = new TabButton(teamStr1,Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		teambt2 = new TabButton(teamStr2,Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		contrastbt = new TabButton(Constants.contrastText,Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		teambt1.setLocation(btx, bty);
		teambt2.setLocation(btx + 2 * inter, bty);
		contrastbt.setLocation(btx + inter, bty);
		teambt1.setOn();
		this.add(teambt1);
		this.add(teambt2);
		this.add(contrastbt);
		teambt1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				teambt1.setOn();
				teambt2.setOff();
				contrastbt.setOff();
				if(isScroll) {
					GamePanel.this.remove(scroll);
				}else{
					GamePanel.this.remove(conPanel);
				}
				isScroll = true;
				ArrayList<MatchPlayerPO> homePlayers = matchDetail.getHomePlayers();
				setTable(homePlayers);
				GamePanel.this.repaint();
			}
		});
		teambt2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				teambt1.setOff();
				teambt2.setOn();
				contrastbt.setOff();
				if(isScroll) {
					GamePanel.this.remove(scroll);
				}else{
					GamePanel.this.remove(conPanel);
				}
				isScroll = true;
				ArrayList<MatchPlayerPO> roadPlayers = matchDetail.getRoadPlayers();
				setTable(roadPlayers);
				GamePanel.this.repaint();
			}
		});
		contrastbt.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				teambt1.setOff();
				teambt2.setOff();
				contrastbt.setOn();
				if(isScroll) {
					GamePanel.this.remove(scroll);
				}else{
					GamePanel.this.remove(conPanel);
				}
				isScroll = false;
				GamePanel.this.add(conPanel);
				GamePanel.this.repaint();
			}
		});
		
	}

	private BottomScrollPane scroll;

	public void setTable(ArrayList<MatchPlayerPO> players) {
		TechTable table = new TechTable(players,GamePanel.this);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		scroll = new BottomScrollPane(table);
		scroll.setBounds(25,300,940, 300);
		this.add(scroll);

	}
}
