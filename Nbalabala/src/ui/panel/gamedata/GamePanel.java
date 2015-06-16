package ui.panel.gamedata;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.MatchPlayerPO;
import ui.Images;
import ui.UIConfig;
import ui.common.button.TabButton;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
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
	private TabButton teambt1,live,contrastbt;
	private ConPanel conPanel;
	private Panel gameData;
	private GameLivePanel liveBelow;
	private TechPanel techPanel;
	
	public GamePanel(int matchID, Panel gameData) {
		super(UIConfig.IMG_PATH_2 + "games/gamesBG.png");
		this.gameData = gameData;
		this.matchDetail = matchQuery.getMatchDetailByID(matchID);
		
		techPanel = new TechPanel(matchDetail,GamePanel.this);
		conPanel = new ConPanel(matchDetail);
		liveBelow = new GameLivePanel(matchDetail);
		this.add(techPanel);
		initiate();
		addBack();
	}

	public GamePanel(String url, MatchDetailVO matchDetail, Panel gameData) {
		super(url,matchDetail);
		this.gameData = gameData;
		this.matchDetail = matchDetail;
		
		techPanel = new TechPanel(matchDetail,GamePanel.this);
		conPanel = new ConPanel(matchDetail);
		liveBelow = new GameLivePanel(matchDetail);		
		this.add(techPanel);
		initiate();
		addBack();
	}
	
	private void initiate() {
		matchPro = matchDetail.getProfile();
		addButton();
	}
	

	public void addBack() {
//		back = new ImgButton(UIConfig.IMG_PATH + "back.png", 70, 150, UIConfig.IMG_PATH + "backOn.png", UIConfig.IMG_PATH + "back.png");
//		this.add(back);
//		back.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				MainController.backToOnePanel(gameData);
//			}
//
//		});
	}


	public void addButton() {
		teambt1 = new TabButton(Constants.GAME_LIVE[0],Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		live = new TabButton(Constants.GAME_LIVE[1],Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		contrastbt = new TabButton(Constants.GAME_LIVE[2],Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		teambt1.setLocation(btx, bty);
		live.setLocation(btx + inter, bty);
		contrastbt.setLocation(btx + 2* inter, bty);
		teambt1.setOn();
		this.add(teambt1);
		this.add(live);
		this.add(contrastbt);
		teambt1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				teambt1.setOn();
				live.setOff();
				contrastbt.setOff();
				GamePanel.this.remove(liveBelow);
				GamePanel.this.remove(conPanel);
				GamePanel.this.add(techPanel);
				GamePanel.this.repaint();
			}
		});
		live.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				teambt1.setOff();
				live.setOn();
				contrastbt.setOff();
				GamePanel.this.remove(conPanel);
				GamePanel.this.remove(techPanel);
				GamePanel.this.add(liveBelow);
				GamePanel.this.repaint();
			}
		});
		contrastbt.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				teambt1.setOff();
				live.setOff();
				contrastbt.setOn();
				GamePanel.this.remove(liveBelow);
				GamePanel.this.remove(techPanel);
				GamePanel.this.add(conPanel);
				GamePanel.this.repaint();
			}
		});
		
	}

	private BottomScrollPane scroll;

	public void setTable(ArrayList<MatchPlayerPO> players) {
		TechTable table = new TechTable(players,GamePanel.this);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.setHeaderColorAndFont();
		table.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		table.setWidth(new int[] {33, 170, 43, 43, 43, 43, 43, 54, 54, 50, 43, 43, 43, 43, 43, 54, 43});
		
		scroll = new BottomScrollPane(table);
		scroll.setBounds(55,260,table.setTableWidth(330, table.getRowCount()), 330);
		this.add(scroll);

	}
}
