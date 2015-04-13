package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import ui.UIConfig;
import ui.common.SeasonInputPanel;
import ui.common.button.ImgButton;
import ui.common.button.TextButton;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import ui.panel.gamedata.GameDetailButton;
import utility.Constants;
import vo.MatchPlayerVO;
import vo.PlayerDetailVO;
import vo.PlayerMatchPerformanceVO;
import vo.PlayerProfileVO;
import vo.PlayerSeasonVO;
import bl.playerquerybl.PlayerQuery;
import blservice.PlayerQueryBLService;

/**
 * 具体球员信息界面
 * @author lsy
 * @version 2015年3月24日 上午10:26:48
 */
public class PlayerInfoPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2506795997614982399L;
	protected static final int TOTAL_X = 676, GAME_X = 765, GAME_Y = 190, TOTAL_WIDTH = 63, GAME_WIDTH = 83,
			HEIGHT = 25;
	private static final String IMG_URL = UIConfig.IMG_PATH + "players/";
	private static final String BACK_BUTTON_OFF = IMG_URL + "back.png";
	private static final String BACK_BUTTON_ON = IMG_URL + "backOn.png";
	private static final String BACK_BUTTON_CLICK = IMG_URL + "back.png";
	private static final int LABEL_X = 350, LABEL_Y = 30, INTER_X = 220, INTER_Y = 30, LABEL_WIDTH = 200,
			LABEL_HEIGHT = 35;
	private static final String[] COLUMN_NAMES = new String[]{"", "参赛场数", "先发场数", "在场时间", "投篮命中数", "投篮出手数", "投篮命中率",
																"三分命中数", "三分出手数", "三分命中率", "罚球命中数", "罚球出手数", "罚球命中率",
																"进攻篮板", "防守篮板", "总篮板", "助攻数", "抢断数", "盖帽数", "失误数",
																"犯规数", "得分", "两双", "得分/篮板/助攻", "效率", "GmSc 效率值",
																"真实命中率", "投篮效率", "进攻篮板率", "防守篮板率", "篮板率", "助攻率", "抢断率",
																"盖帽率", "失误率", "使用率"};
	private static final String[] GAME_COLUMN_NAMES = new String[]{"赛季", "日期", "对阵", "首发位置","在场时间", "投篮命中数", "投篮出手数",
		"三分命中数", "三分出手数", "罚球命中数", "罚球出手数", 
		"进攻篮板", "防守篮板", "总篮板", "助攻数", "抢断数", "盖帽数", "失误数",
		"犯规数", "得分"};

	protected TextButton totalButton, gameButton;
	protected String name;
	protected PlayerProfileVO profileVO;
	private Image portraitImg;
	private PlayerQueryBLService playerQuery;
	protected PlayerDetailVO detailVO;
	private ImgButton backButton;
	private ContrastDiagram cd;

	protected BottomPanel lastPanel;
	private MyLabel profileLabel[] = new MyLabel[10];
	private String[] labelStr;

	protected BottomScrollPane scroll;
	protected BottomTable table;
	/** 赛季选择器 */
	protected SeasonInputPanel seasonInput;
	String[][] rowData ;

	public PlayerInfoPanel(String url, String name, BottomPanel lastPanel) {
		super(url);
		this.name = name;
		playerQuery = new PlayerQuery();
		seasonInput = new SeasonInputPanel(this);
		seasonInput.setLocation(515, GAME_Y);
		this.add(seasonInput);
		this.detailVO = playerQuery.getPlayerDetailByName(name, seasonInput.getSeason());
		this.profileVO = detailVO.getProfile();
		this.lastPanel = lastPanel;

		labelStr = new String[]{"姓名: " + profileVO.getName(),
									"球队: " + Constants.translateTeamAbbr(profileVO.getTeam()),
									"号码: " + profileVO.getNumber(), "位置: " + profileVO.getPosition(),
									"年龄: " + profileVO.getAge(), "球龄: " + profileVO.getExp(),
									"生日: " + profileVO.getBirth(), "身高: " + profileVO.getHeight(),
									"体重: " + profileVO.getWeight(), "毕业学校: " + profileVO.getSchool()};
		addButton();
		addPortrait();
		addActionImg();
		addBackButton();
		addLabel();
		addTotalTable();
		addContrastDiagram(); // 添加球员信息和联盟平均比较的柱状图
		
	}

	/**
	 * 添加球员和联盟平均的比较图
	 * @author cylong
	 * @version 2015年4月11日 上午1:14:43
	 */
	protected void addContrastDiagram() {
		/* 球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
		PlayerSeasonVO playerSeason = detailVO.getSeasonRecord();
		double[] fivePlayersData = {playerSeason.getScoreAvg(), playerSeason.getAssistAvg(),
										playerSeason.getTotalReboundAvg(), playerSeason.getFreeThrowPercent(),
										playerSeason.getThreePointPercent()};
		double[] fiveArgsAvg = playerQuery.getFiveArgsAvg(seasonInput.getSeason());
		double[] highestScoreReboundAssist = playerQuery.getHighestScoreReboundAssist(seasonInput.getSeason());
		cd = new ContrastDiagram(fivePlayersData, fiveArgsAvg, highestScoreReboundAssist, "球员平均");
		cd.setBounds(57, 260, 888, 160);
		this.add(cd);
		cd.updateUI();
		cd.repaint();
	}
	
	/**
	 * 更新柱状图
	 * @author cylong
	 * @version 2015年4月12日  下午8:20:20
	 */
	protected void updateContrastDiagram() {
		/* 球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
		PlayerSeasonVO playerSeason = detailVO.getSeasonRecord();
		double[] fivePlayersData = {playerSeason.getScoreAvg(), playerSeason.getAssistAvg(),
										playerSeason.getTotalReboundAvg(), playerSeason.getFreeThrowPercent(),
										playerSeason.getThreePointPercent()};
		double[] fiveArgsAvg = playerQuery.getFiveArgsAvg(seasonInput.getSeason());
		double[] highestScoreReboundAssist = playerQuery.getHighestScoreReboundAssist(seasonInput.getSeason());
		cd.setData(fivePlayersData, fiveArgsAvg, highestScoreReboundAssist);
	}

	private void addLabel() {
		for(int i = 0; i < 3; i++) {
			profileLabel[i] = new MyLabel(LABEL_X + i * INTER_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT, labelStr[i]);
		}
		for(int i = 3; i < 6; i++) {
			profileLabel[i] = new MyLabel(LABEL_X + (i - 3) * INTER_X, LABEL_Y + INTER_Y, LABEL_WIDTH, LABEL_HEIGHT, labelStr[i]);
		}
		for(int i = 6; i < 8; i++) {
			profileLabel[i] = new MyLabel(LABEL_X + (i - 6) * INTER_X, LABEL_Y + 2 * INTER_Y, LABEL_WIDTH, LABEL_HEIGHT, labelStr[i]);
		}
		for(int i = 8; i < 9; i++) {
			profileLabel[i] = new MyLabel(LABEL_X + (i - 8) * INTER_X, LABEL_Y + 3 * INTER_Y, LABEL_WIDTH, LABEL_HEIGHT, labelStr[i]);
		}
		for(int i = 9; i < 10; i++) {
			profileLabel[i] = new MyLabel(LABEL_X + (i - 8) * INTER_X, LABEL_Y + 3 * INTER_Y, LABEL_WIDTH + 150, LABEL_HEIGHT, labelStr[i]);
		}
		for(int i = 0; i < 10; i++) {
			profileLabel[i].setHorizontalAlignment(SwingConstants.LEFT);
			this.add(profileLabel[i]);
		}
	}

	public void refresh() {
		detailVO = playerQuery.getPlayerDetailByName(name, seasonInput.getSeason());
		updateContrastDiagram();
		setTotalTable();
		repaint();
	}

	/**
	 * 返回按钮
	 * @author lsy
	 * @version 2015年3月24日 下午4:20:16
	 */
	private void addBackButton() {
		backButton = new ImgButton(BACK_BUTTON_OFF, 50, 50, BACK_BUTTON_ON, BACK_BUTTON_CLICK);
		this.add(backButton);
		backButton.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				MainController.backToOnePanel(PlayerInfoPanel.this, lastPanel);
			}
		});
	}

	/**
	 * 添加头像
	 * @author lsy
	 * @version 2015年3月24日 上午11:17:35
	 */
	private void addPortrait() {
		portraitImg = profileVO.getPortrait();
		ImgLabel label = new ImgLabel(136, -10, 200, 160, portraitImg);
		this.add(label);
	}

	/**
	 * 添加全身像
	 * @author lsy
	 * @version 2015年3月24日 上午11:17:42
	 */
	public void addActionImg() {
		ActionPhotoPanel actionPhotoPanel = new ActionPhotoPanel(detailVO.getAction());
		actionPhotoPanel.setOpaque(true);
		actionPhotoPanel.setBounds(880, 6, 1000, 1000);
		this.add(actionPhotoPanel);
	}

	public void addButton() {
		totalButton = new GameDetailButton(TOTAL_X, GAME_Y, TOTAL_WIDTH, HEIGHT, "总数据");
		totalButton.setOpaque(true);
		totalButton.setBackground(UIConfig.BUTTON_COLOR);
		totalButton.setForeground(Color.white);
		gameButton = new GameDetailButton(GAME_X, GAME_Y, GAME_WIDTH, HEIGHT, "比赛数据");
		this.add(totalButton);
		this.add(gameButton);
		totalButton.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				gameButton.back();
				addContrastDiagram();
				setTotalTable();
				PlayerInfoPanel.this.repaint();
			}
		});
		gameButton.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				totalButton.back();
				if (cd != null) {
					PlayerInfoPanel.this.remove(cd);
				}
				updateGameTable();
				PlayerInfoPanel.this.repaint();
			}
		});
	}

	public void updateGameTable() {
		ArrayList<PlayerMatchPerformanceVO> playerMatch = detailVO.getMatchRecords();
		int lth = playerMatch.size();
		rowData = new String[lth][GAME_COLUMN_NAMES.length];
		table.setModel(new DefaultTableModel(rowData,GAME_COLUMN_NAMES));
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		scroll.setBounds(57, 260, 888, 270); // 表格的位置
		for(int i = 0; i < lth; i++) {
			PlayerMatchPerformanceVO vo = playerMatch.get(i);
			MatchPlayerVO player = vo.getMatchPlayerRecord();
			table.setValueAt(vo.getSeason(), i, 0);
			table.setValueAt(vo.getDate(), i, 1);
			table.setValueAt(vo.getTwoTeams(), i, 2);
			table.setValueAt(player.getPosition(), i, 3);
			table.setValueAt(player.getTime(), i, 4);
			table.setValueAt(player.getFieldGoal() + "", i, 5);
			table.setValueAt(player.getFieldAttempt() + "", i, 6);
			table.setValueAt(player.getThreePointGoal() + "", i, 7);
			table.setValueAt(player.getThreePointAttempt() + "", i, 8);
			table.setValueAt(player.getFreethrowGoal() + "", i, 9);
			table.setValueAt(player.getFreethrowAttempt() + "", i, 10);
			table.setValueAt(player.getOffensiveRebound() + "", i, 11);
			table.setValueAt(player.getDefensiveRebound() + "", i, 12);
			table.setValueAt(player.getTotalRebound() + "", i, 13);
			table.setValueAt(player.getAssist() + "", i, 14);
			table.setValueAt(player.getSteal() + "", i, 15);
			table.setValueAt(player.getBlock() + "", i, 16);
			table.setValueAt(player.getTurnover() + "", i, 17);
			table.setValueAt(player.getFoul() + "", i, 18);
			table.setValueAt(player.getPersonalGoal() + "", i, 19);
		}
	}
	public void addTotalTable() {
		rowData = new String[2][COLUMN_NAMES.length];
		rowData[0][0] = "总数据";
		rowData[1][0] = "平均数据";
		table = new BottomTable(rowData, COLUMN_NAMES);
		scroll = new BottomScrollPane(table);
		scroll.setBounds(57, 450, 888, 80); // 表格的位置
		this.add(scroll);
	}

	/**
	 * 更新表格数据
	 * @author cylong
	 * @version 2015年4月12日 下午6:56:20
	 */
	public void setTotalTable() {
		rowData = new String[2][COLUMN_NAMES.length];
		rowData[0][0] = "总数据";
		rowData[1][0] = "平均数据";
		scroll.setBounds(57, 450, 888, 80); // 表格的位置
		table.setModel(new DefaultTableModel(rowData,COLUMN_NAMES));
		PlayerSeasonVO playerSeason = detailVO.getSeasonRecord();
		DecimalFormat df = UIConfig.FORMAT;
		table.setValueAt(String.valueOf(playerSeason.getMatchCount()), 0, 1);
		table.setValueAt(String.valueOf(playerSeason.getFirstCount()), 0, 2);
		table.setValueAt(playerSeason.getTime(), 0, 3);
		table.setValueAt(String.valueOf(playerSeason.getFieldGoal()), 0, 4);
		table.setValueAt(String.valueOf(playerSeason.getFieldAttempt()), 0, 5);
		table.setValueAt(df.format(playerSeason.getFieldPercent()), 0, 6);
		table.setValueAt(String.valueOf(playerSeason.getThreePointGoal()), 0, 7);
		table.setValueAt(String.valueOf(playerSeason.getThreePointAttempt()), 0, 8);
		table.setValueAt(df.format(playerSeason.getThreePointPercent()), 0, 9);
		table.setValueAt(String.valueOf(playerSeason.getFreeThrowGoal()), 0, 10);
		table.setValueAt(String.valueOf(playerSeason.getFreeThrowAttempt()), 0, 11);
		table.setValueAt(df.format(playerSeason.getFreeThrowPercent()), 0, 12);
		table.setValueAt(String.valueOf(playerSeason.getOffensiveRebound()), 0, 13);
		table.setValueAt(String.valueOf(playerSeason.getDefensiveRebound()), 0, 14);
		table.setValueAt(String.valueOf(playerSeason.getTotalRebound()), 0, 15);
		table.setValueAt(String.valueOf(playerSeason.getAssist()), 0, 16);
		table.setValueAt(String.valueOf(playerSeason.getSteal()), 0, 17);
		table.setValueAt(String.valueOf(playerSeason.getBlock()), 0, 18);
		table.setValueAt(String.valueOf(playerSeason.getTurnover()), 0, 19);
		table.setValueAt(String.valueOf(playerSeason.getFoul()), 0, 20);
		table.setValueAt(String.valueOf(playerSeason.getScore()), 0, 21);
		table.setValueAt(String.valueOf(playerSeason.getDoubleDouble()), 0, 22);
		table.setValueAt(String.valueOf(playerSeason.getScoreReboundAssist()), 0, 23);
		table.setValueAt(String.valueOf(playerSeason.getEfficiency()), 0, 24);
		table.setValueAt(df.format(playerSeason.getGmSc()), 0, 25);
		table.setValueAt(df.format(playerSeason.getRealFieldPercent()), 0, 26);
		table.setValueAt(df.format(playerSeason.getFieldEff()), 0, 27);
		table.setValueAt(df.format(playerSeason.getOffensiveReboundPercent()), 0, 28);
		table.setValueAt(df.format(playerSeason.getDefensiveReboundPercent()), 0, 29);
		table.setValueAt(df.format(playerSeason.getTotalReboundPercent()), 0, 30);
		table.setValueAt(df.format(playerSeason.getAssistPercent()), 0, 31);
		table.setValueAt(df.format(playerSeason.getStealPercent()), 0, 32);
		table.setValueAt(df.format(playerSeason.getBlockPercent()), 0, 33);
		table.setValueAt(df.format(playerSeason.getTurnOverPercent()), 0, 34);
		table.setValueAt(df.format(playerSeason.getUsePercent()), 0, 35);

		table.setValueAt(String.valueOf(playerSeason.getMatchCount()), 1, 1);
		table.setValueAt(String.valueOf(playerSeason.getFirstCount()), 1, 2);
		table.setValueAt(playerSeason.getTimeAvg(), 1, 3);
		table.setValueAt(df.format(playerSeason.getFieldGoalAvg()), 1, 4);
		table.setValueAt(df.format(playerSeason.getFieldAttemptAvg()), 1, 5);
		table.setValueAt(df.format(playerSeason.getFieldPercent()), 1, 6);
		table.setValueAt(df.format(playerSeason.getThreePointGoalAvg()), 1, 7);
		table.setValueAt(df.format(playerSeason.getThreePointAttemptAvg()), 1, 8);
		table.setValueAt(df.format(playerSeason.getThreePointPercent()), 1, 9);
		table.setValueAt(df.format(playerSeason.getFreethrowGoalAvg()), 1, 10);
		table.setValueAt(df.format(playerSeason.getFreethrowAttemptAvg()), 1, 11);
		table.setValueAt(df.format(playerSeason.getFreeThrowPercent()), 1, 12);
		table.setValueAt(df.format(playerSeason.getOffensiveReboundAvg()), 1, 13);
		table.setValueAt(df.format(playerSeason.getDefensiveReboundAvg()), 1, 14);
		table.setValueAt(df.format(playerSeason.getTotalReboundAvg()), 1, 15);
		table.setValueAt(df.format(playerSeason.getAssistAvg()), 1, 16);
		table.setValueAt(df.format(playerSeason.getStealAvg()), 1, 17);
		table.setValueAt(df.format(playerSeason.getBlockAvg()), 1, 18);
		table.setValueAt(df.format(playerSeason.getTurnoverAvg()), 1, 19);
		table.setValueAt(df.format(playerSeason.getFoulAvg()), 1, 20);
		table.setValueAt(df.format(playerSeason.getScoreAvg()), 1, 21);
		table.setValueAt(df.format(playerSeason.getDoubleDoubleAvg()), 1, 22);
		table.setValueAt(df.format(playerSeason.getScoreReboundAssistAvg()), 1, 23);
		table.setValueAt(df.format(playerSeason.getGmSc()), 1, 24);
		table.setValueAt(df.format(playerSeason.getEfficiency()), 1, 25);
		table.setValueAt(df.format(playerSeason.getRealFieldPercent()), 1, 26);
		table.setValueAt(df.format(playerSeason.getFieldEff()), 1, 27);
		table.setValueAt(df.format(playerSeason.getOffensiveReboundPercent()), 1, 28);
		table.setValueAt(df.format(playerSeason.getDefensiveReboundPercent()), 1, 29);
		table.setValueAt(df.format(playerSeason.getTotalReboundPercent()), 1, 30);
		table.setValueAt(df.format(playerSeason.getAssistPercent()), 1, 31);
		table.setValueAt(df.format(playerSeason.getStealPercent()), 1, 32);
		table.setValueAt(df.format(playerSeason.getBlockPercent()), 1, 33);
		table.setValueAt(df.format(playerSeason.getTurnOverPercent()), 1, 34);
		table.setValueAt(df.format(playerSeason.getUsePercent()), 1, 35);
	}
}
