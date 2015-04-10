package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.SwingConstants;

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
import vo.PlayerDetailVO;
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
	private static final String BACK_BUTTON_ON = IMG_URL + "back.png";
	private static final String BACK_BUTTON_CLICK = IMG_URL + "back.png";
	private static final int LABEL_X = 350, LABEL_Y = 40, INTER_X = 220, INTER_Y = 30, LABEL_WIDTH = 200, LABEL_HEIGHT = 35;
	private static final String[] COLUMN_NAMES = new String[]{"", "参赛场数", "先发场数", "在场时间", "投篮命中数", "投篮出手数", "投篮命中率",
																"三分命中数", "三分出手数", "三分命中率", "罚球命中数", "罚球出手数", "罚球命中率",
																"进攻篮板", "防守篮板", "总篮板", "助攻数", "抢断数", "盖帽数", "失误数",
																"犯规数", "得分", "两双", "得分/篮板/助攻", "效率", "GmSc 效率值",
																"真实命中率", "投篮效率", "进攻篮板率", "防守篮板率", "篮板率", "助攻率", "抢断率",
																"盖帽率", "失误率", "使用率"};

	protected TextButton totalButton, gameButton;
	protected String name;
	protected PlayerProfileVO profileVO;
	private Image portraitImg;
	private PlayerQueryBLService playerQuery;
	protected PlayerDetailVO detailVO;
	private ImgButton backButton;

	protected BottomPanel lastPanel;
	private MyLabel profileLabel[] = new MyLabel[10];
	private String[] labelStr;

	protected BottomScrollPane scroll;
	protected BottomTable table;
	/** 赛季选择器 */
	protected SeasonInputPanel seasonInput;

	public PlayerInfoPanel(String url, String name, BottomPanel lastPanel) {
		super(url);
		playerQuery = new PlayerQuery();
		this.name = name;
		seasonInput = new SeasonInputPanel();
		seasonInput.setLocation(600, 40);
		this.add(seasonInput); // TODO 位置需要重新设定
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
		setTable();
		addPortrait();
		addActionImg();
		addBackButton();
		addLabel();
		addContrastDiagram();
	}

	/**
	 * 添加球员和联盟平均的比较图
	 * @author cylong
	 * @version 2015年4月11日 上午1:14:43
	 */
	private void addContrastDiagram() {
		/* 球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
		PlayerSeasonVO playerSeason = detailVO.getSeasonRecord();
		double[] fivePlayersData = {playerSeason.getScoreAvg(), playerSeason.getAssistAvg(),
										playerSeason.getTotalReboundAvg(), playerSeason.getFreeThrowPercent(),
										playerSeason.getThreePointPercent()};
		double[] fiveArgsAvg = playerQuery.getFiveArgsAvg();
		double[] highestScoreReboundAssist = playerQuery.getHighestScoreReboundAssist();
		ContrastDiagram cd = new ContrastDiagram(fivePlayersData, fiveArgsAvg, highestScoreReboundAssist);
		cd.setBounds(57, 380, 888, 165);
		this.add(cd);
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
		actionPhotoPanel.setBounds(885, 6, 1000, 1000);
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
			}
		});
		gameButton.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				MainController.toPlayerSeasonInfoPanel(PlayerInfoPanel.this, profileVO.getName(), lastPanel);
			}
		});
	}

	public void setTable() {
		PlayerSeasonVO playerSeason = detailVO.getSeasonRecord();
		String[][] rowData = new String[2][COLUMN_NAMES.length];
		DecimalFormat df = UIConfig.FORMAT;
		rowData[0][0] = "总数据";
		rowData[1][0] = "平均数据";
		rowData[0][1] = String.valueOf(playerSeason.getMatchCount());
		rowData[0][2] = String.valueOf(playerSeason.getFirstCount());
		rowData[0][3] = playerSeason.getTime();
		rowData[0][4] = String.valueOf(playerSeason.getFieldGoal());
		rowData[0][5] = String.valueOf(playerSeason.getFieldAttempt());
		rowData[0][6] = df.format(playerSeason.getFieldPercent());
		rowData[0][7] = String.valueOf(playerSeason.getThreePointGoal());
		rowData[0][8] = String.valueOf(playerSeason.getThreePointAttempt());
		rowData[0][9] = df.format(playerSeason.getThreePointPercent());
		rowData[0][10] = String.valueOf(playerSeason.getFreeThrowGoal());
		rowData[0][11] = String.valueOf(playerSeason.getFreeThrowAttempt());
		rowData[0][12] = df.format(playerSeason.getFreeThrowPercent());
		rowData[0][13] = String.valueOf(playerSeason.getOffensiveRebound());
		rowData[0][14] = String.valueOf(playerSeason.getDefensiveRebound());
		rowData[0][15] = String.valueOf(playerSeason.getTotalRebound());
		rowData[0][16] = String.valueOf(playerSeason.getAssist());
		rowData[0][17] = String.valueOf(playerSeason.getSteal());
		rowData[0][18] = String.valueOf(playerSeason.getBlock());
		rowData[0][19] = String.valueOf(playerSeason.getTurnover());
		rowData[0][20] = String.valueOf(playerSeason.getFoul());
		rowData[0][21] = String.valueOf(playerSeason.getScore());
		rowData[0][22] = String.valueOf(playerSeason.getDoubleDouble());
		rowData[0][23] = String.valueOf(playerSeason.getScoreReboundAssist());
		rowData[0][24] = String.valueOf(playerSeason.getEfficiency());
		rowData[0][25] = df.format(playerSeason.getGmSc());
		rowData[0][26] = df.format(playerSeason.getRealFieldPercent());
		rowData[0][27] = df.format(playerSeason.getFieldEff());
		rowData[0][28] = df.format(playerSeason.getOffensiveReboundPercent());
		rowData[0][29] = df.format(playerSeason.getDefensiveReboundPercent());
		rowData[0][30] = df.format(playerSeason.getTotalReboundPercent());
		rowData[0][31] = df.format(playerSeason.getAssistPercent());
		rowData[0][32] = df.format(playerSeason.getStealPercent());
		rowData[0][33] = df.format(playerSeason.getBlockPercent());
		rowData[0][34] = df.format(playerSeason.getTurnOverPercent());
		rowData[0][35] = df.format(playerSeason.getUsePercent());

		rowData[1][1] = String.valueOf(playerSeason.getMatchCount());
		rowData[1][2] = String.valueOf(playerSeason.getFirstCount());
		rowData[1][3] = playerSeason.getTimeAvg();
		rowData[1][4] = df.format(playerSeason.getFieldGoalAvg());
		rowData[1][5] = df.format(playerSeason.getFieldAttemptAvg());
		rowData[1][6] = df.format(playerSeason.getFieldPercent());
		rowData[1][7] = df.format(playerSeason.getThreePointGoalAvg());
		rowData[1][8] = df.format(playerSeason.getThreePointAttemptAvg());
		rowData[1][9] = df.format(playerSeason.getThreePointPercent());
		rowData[1][10] = df.format(playerSeason.getFreethrowGoalAvg());
		rowData[1][11] = df.format(playerSeason.getFreethrowAttemptAvg());
		rowData[1][12] = df.format(playerSeason.getFreeThrowPercent());
		rowData[1][13] = df.format(playerSeason.getOffensiveReboundAvg());
		rowData[1][14] = df.format(playerSeason.getDefensiveReboundAvg());
		rowData[1][15] = df.format(playerSeason.getTotalReboundAvg());
		rowData[1][16] = df.format(playerSeason.getAssistAvg());
		rowData[1][17] = df.format(playerSeason.getStealAvg());
		rowData[1][18] = df.format(playerSeason.getBlockAvg());
		rowData[1][19] = df.format(playerSeason.getTurnoverAvg());
		rowData[1][20] = df.format(playerSeason.getFoulAvg());
		rowData[1][21] = df.format(playerSeason.getScoreAvg());
		rowData[1][22] = df.format(playerSeason.getDoubleDoubleAvg());
		rowData[1][23] = df.format(playerSeason.getScoreReboundAssistAvg());
		rowData[1][25] = df.format(playerSeason.getGmSc());
		rowData[1][24] = df.format(playerSeason.getEfficiency());
		rowData[1][26] = df.format(playerSeason.getRealFieldPercent());
		rowData[1][27] = df.format(playerSeason.getFieldEff());
		rowData[1][28] = df.format(playerSeason.getOffensiveReboundPercent());
		rowData[1][29] = df.format(playerSeason.getDefensiveReboundPercent());
		rowData[1][30] = df.format(playerSeason.getTotalReboundPercent());
		rowData[1][31] = df.format(playerSeason.getAssistPercent());
		rowData[1][32] = df.format(playerSeason.getStealPercent());
		rowData[1][33] = df.format(playerSeason.getBlockPercent());
		rowData[1][34] = df.format(playerSeason.getTurnOverPercent());
		rowData[1][35] = df.format(playerSeason.getUsePercent());
		table = new BottomTable(rowData, COLUMN_NAMES);
		scroll = new BottomScrollPane(table);
		scroll.setBounds(57, 270, 888, 80); // 表格的位置
		this.add(scroll);
	}
}
