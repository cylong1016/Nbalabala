package ui.panel.gamedata;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import bl.matchquerybl.MatchQuery;
import po.MatchPlayerPO;
import ui.Images;
import ui.common.UserMouseAdapter;
import ui.common.button.TabButton;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import utility.Utility;
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

	public GamePanel(String url, MatchDetailVO matchDetail,Panel gameData) {
		super(url,matchDetail,gameData);
		this.gameData = gameData;
		this.matchDetail = matchDetail;
		matchPro = matchDetail.getProfile();
		matchQuery = new MatchQuery();
		conPanel = new ConPanel(Images.GAME_CON,matchDetail);
		addButton();
		initSetTabel();
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

	String[] columns;
	String[][] rowData;
	BottomScrollPane scroll;

	// BottomTable table=new BottomTable(rowData,columns);
	public void setTable(ArrayList<MatchPlayerPO> players) {
		columns = new String[] { "球员名", "首发", "在场时间", "投篮命中数", "投篮出手数", "三分命中数", "三分出手数", "罚球命中数", "罚球出手数",
				"进攻篮板数", "防守篮板数", "总篮板数", "助攻数", "抢断数", "盖帽数", "失误数", "犯规数", "个人得分" };
		int size = players.size();
		int lth = columns.length;
		rowData = new String[size][lth];
		for (int i = 0; i < size; i++) {
			MatchPlayerPO mpVO = players.get(i);
			rowData[i][0] = Utility.trimName(mpVO.getPlayerName());
			if (mpVO.isStarter()) {
				rowData[i][1] = "Y";
			}else {
				rowData[i][1] = "N";
			}
			rowData[i][2] = mpVO.getTimePlayed();
			rowData[i][3] = mpVO.getFieldMade() + "";
			rowData[i][4] = mpVO.getFieldAttempt() + "";
			rowData[i][5] = mpVO.getThreepointMade() + "";
			rowData[i][6] = mpVO.getThreepointAttempt() + "";
			rowData[i][7] = mpVO.getFreethrowMade() + "";
			rowData[i][8] = mpVO.getFieldAttempt() + "";
			rowData[i][9] = mpVO.getOffensiveRebound() + "";
			rowData[i][10] = mpVO.getDefensiveRebound() + "";
			rowData[i][11] = mpVO.getTotalRebound() + "";
			rowData[i][12] = mpVO.getAssist() + "";
			rowData[i][13] = mpVO.getSteal() + "";
			rowData[i][14] = mpVO.getBlock() + "";
			rowData[i][15] = mpVO.getTurnover() + "";
			rowData[i][16] = mpVO.getFoul() + "";
			rowData[i][17] = mpVO.getScore() + "";
		}
		BottomTable table = new BottomTable(rowData, columns);
		table.getColumnModel().getColumn(0).setPreferredWidth(170);
		addListener(table,players);
		scroll = new BottomScrollPane(table);
		scroll.setBounds(25,300,940, 300);
		this.add(scroll);

	}

	public void addListener(final BottomTable table,final ArrayList<MatchPlayerPO> players) {
		try {
			table.addMouseListener(new UserMouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(players.get(rowI).getPlayerName(), GamePanel.this);
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
