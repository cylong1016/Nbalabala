package ui.panel.allplayers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import ui.panel.gamedata.GameDetailButton;
import vo.MatchPlayerVO;
import vo.PlayerMatchPerformanceVO;
import vo.PlayerProfileVO;

/**
 * 球员赛季数据
 * @author lsy
 * @version 2015年3月24日  下午6:53:52
 */
public class PlayerSeasonPanel extends PlayerInfoPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -1936824766623215286L;

	public PlayerSeasonPanel(MainController controller, String url, PlayerProfileVO vo, BottomPanel allPlayers) {
		super(controller, url, vo, allPlayers);
	}
	
	public void addButton() {
		total = new GameDetailButton(totalX, y, totalWidth, height, "总数据");
		game = new GameDetailButton(gameX, y, gameWidth, height, "比赛数据");
		game.setOpaque(true);
		game.setBackground(UIConfig.BUTTON_COLOR);
		game.setForeground(Color.white);
		this.add(total);
		this.add(game);
		total.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				controller.toPlayerInfoPanel(PlayerSeasonPanel.this, vo, allPlayers);
			}
		});
		game.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			}
		});
	}

	public void setTable() {
		ArrayList<PlayerMatchPerformanceVO> playerMatch = detailVO.getMatchRecords();
		int lth = playerMatch.size();
		columns=new String[]{"赛季","日期","比赛球队","位置","上场时间","投篮命中"," 投篮出手","三分命中",
				"三分出手","罚球命中","罚球出手","进攻篮板","防守篮板","总篮板","助攻","抢断","盖帽","失误",
				"犯规","个人得分"};
		rowData = new String[lth][columns.length];
		for(int i = 0; i<lth; i++){
			PlayerMatchPerformanceVO vo = playerMatch.get(i);
			MatchPlayerVO player = vo.getMatchPlayerRecord();
			rowData[i][0] = vo.getSeason();
			rowData[i][1] = vo.getDate();
			rowData[i][2] = vo.getTwoTeams();
			rowData[i][3] = player.getPosition();
			rowData[i][4] = player.getTime();
			rowData[i][5] = player.getFieldGoal()+"";
			rowData[i][6] = player.getFieldAttempt()+"";
			rowData[i][7] = player.getThreePointGoal()+"";
			rowData[i][8] = player.getThreePointAttempt()+"";
			rowData[i][9] = player.getFreethrowGoal()+"";
			rowData[i][10] = player.getFreethrowAttempt()+"";
			rowData[i][11] = player.getOffensiveRebound()+"";
			rowData[i][12] = player.getDefensiveRebound()+"";
			rowData[i][13] = player.getTotalRebound()+"";
			rowData[i][14] = player.getAssist()+"";
			rowData[i][15] = player.getSteal()+"";
			rowData[i][16] = player.getBlock()+"";
			rowData[i][17] = player.getTurnover()+"";
			rowData[i][18] = player.getFoul()+"";
			rowData[i][19] = player.getPersonalGoal()+"";
		}
		table = new BottomTable(rowData, columns);
		scroll = new BottomScrollPane(table);
		scroll.setBounds(57, 260, 888, 265); // 表格的位置
		this.add(scroll);
	}
}
