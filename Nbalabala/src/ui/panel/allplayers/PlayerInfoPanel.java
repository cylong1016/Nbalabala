package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.button.TextButton;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import ui.panel.gamedata.GameDetailButton;
import vo.PlayerDetailVO;
import vo.PlayerProfileVO;
import bl.playerquerybl.PlayerQuery;
import blservice.PlayerQueryBLService;
import data.seasondata.PlayerSeasonRecord;

/**
 * 具体球员信息界面
 * @author lsy
 * @version 2015年3月24日 上午10:26:48
 */
public class PlayerInfoPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2506795997614982399L;
	int totalX = 676, gameX = 765, y = 190, totalWidth = 63, gameWidth = 83, height = 25;
	TextButton total, game;
	PlayerProfileVO vo;
	MainController controller;
	Image headPicture, totalPicture;
	PlayerQueryBLService playerQuery;
	PlayerDetailVO detailVO;
	ImgButton back;
	String url = UIConfig.IMG_PATH + "players/";
	AllPlayersPanel allPlayers;
	MyLabel lbName, lbTeam, lbNumber, lbPosition, lbBirth, lbHeight, lbWeight, lbAge, lbexp, lbschool;
	MyLabel profileLabel[] = new MyLabel[10];
	int lbX = 350, lbY = 40, interX = 180, interY = 30, width = 200, lbHgt = 35;
	String[] lbstr;

	public PlayerInfoPanel(MainController controller, String url, PlayerProfileVO vo, AllPlayersPanel allPlayers) {
		super(controller, url);
		this.vo = vo;
		this.controller = controller;
		this.allPlayers = allPlayers;
		playerQuery = new PlayerQuery();
		detailVO = playerQuery.getPlayerDetailByName(vo.getName());
		lbstr = new String[]{"姓名: " + vo.getName(), "球队: " + vo.getTeam(), "号码: " + vo.getNumber(),
								"位置: " + vo.getPosition(), "年龄: " + vo.getAge(), "球龄: " + vo.getExp(),
								"生日: " + vo.getBirth(), "身高: " + vo.getHeight(), "体重: " + vo.getWeight(),
								"毕业学校: " + vo.getSchool()};
		addButton();
		setTable();
		addHead();
		addPicture();
		addBack();
		addLabel();
	}

	public void addLabel() {
		for(int i = 0; i < 3; i++) {
			profileLabel[i] = new MyLabel(lbX + i * interX, lbY, width, lbHgt, lbstr[i]);
		}
		for(int i = 3; i < 6; i++) {
			profileLabel[i] = new MyLabel(lbX + (i - 3) * interX, lbY + interY, width, lbHgt, lbstr[i]);
		}
		for(int i = 6; i < 8; i++) {
			profileLabel[i] = new MyLabel(lbX + (i - 6) * interX, lbY + 2 * interY, width, lbHgt, lbstr[i]);
		}
		for(int i = 8; i < 10; i++) {
			profileLabel[i] = new MyLabel(lbX + (i - 8) * interX, lbY + 3 * interY, width, lbHgt, lbstr[i]);
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
	public void addBack() {
		back = new ImgButton(url + "back.png", 50, 50, url + "back.png", url + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				controller.backToGameDataPanel(PlayerInfoPanel.this, allPlayers);
			}

		});
	}

	/**
	 * 添加头像
	 * @author lsy
	 * @version 2015年3月24日 上午11:17:35
	 */
	public void addHead() {
		headPicture = vo.getPortrait();
		ImgLabel label = new ImgLabel(136, -10, 200, 160, headPicture);
		this.add(label);
	}

	/**
	 * 添加全身像
	 * @author lsy
	 * @version 2015年3月24日 上午11:17:42
	 */
	public void addPicture() {
		totalPicture = detailVO.getAction();
		ImgLabel label = new ImgLabel(885, 6, 151, 240, totalPicture);
		this.add(label);
	}

	public void addButton() {
		total = new GameDetailButton(totalX, y, totalWidth, height, "总数据");
		total.setOpaque(true);
		total.setBackground(UIConfig.BUTTON_COLOR);
		total.setForeground(Color.white);
		game = new GameDetailButton(gameX, y, gameWidth, height, "比赛数据");
		this.add(total);
		this.add(game);
		total.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
			}
		});
		game.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				controller.toPlayerSeasonInfoPanel(PlayerInfoPanel.this, vo, allPlayers);
			}
		});
	}

	protected String[] columns;
	protected String[][] rowData;
	protected BottomScrollPane scroll;
	protected ImageIcon icon;
	protected ArrayList<Image> imgArr = new ArrayList<Image>();
	protected BottomTable table;
	protected DecimalFormat df = new DecimalFormat("0.000");

	public void setTable() {
		columns = new String[]{"", "参赛场数", "先发场数", "在场时间", "投篮命中数", "投篮出手数", "投篮命中率", "三分命中数", "三分出手数", "三分命中率",
									"罚球命中数", "罚球出手数", "罚球命中率", "进攻篮板", "防守篮板", "总篮板", "助攻数", "抢断数", "盖帽数", "失误数",
									"犯规数", "得分", "两双", "得分/篮板/助攻", "效率", "GmSc 效率值", "真实命中率", "投篮效率", "进攻篮板率", "防守篮板率",
									"篮板率", "助攻率", "抢断率", "盖帽率", "失误率", "使用率"};
		PlayerSeasonRecord playerSeason = detailVO.getSeasonRecord();
		rowData = new String[3][columns.length];
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
		rowData[0][25] = String.valueOf(playerSeason.getGmSc());
		rowData[0][26] = df.format(playerSeason.getRealFieldPercent());
		rowData[0][27] = String.valueOf(playerSeason.getFieldEff());
		rowData[0][28] = df.format(playerSeason.getOffensiveReboundPercent());
		rowData[0][29] = df.format(playerSeason.getDefensiveReboundPercent());
		rowData[0][30] = df.format(playerSeason.getTotalReboundPercent());
		rowData[0][31] = df.format(playerSeason.getAssistPercent());
		rowData[0][32] = df.format(playerSeason.getStealPercent());
		rowData[0][33] = df.format(playerSeason.getBlockPercent());
		rowData[0][34] = df.format(playerSeason.getTurnOverPercent());
		rowData[0][35] = df.format(playerSeason.getUsePercent());

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
		table = new BottomTable(rowData, columns);
		scroll = new BottomScrollPane(table);
		scroll.setLocation(57, 285);
		this.add(scroll);
	}
}
