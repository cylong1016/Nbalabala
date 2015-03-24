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
 * 
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
		lbstr = new String[] { "姓名: " + vo.getName(), "球队: " + vo.getTeam(), "号码: " + vo.getNumber(),
				"位置: " + vo.getPosition(), "年龄: " + vo.getAge(), "球龄: " + vo.getExp(), "生日: " + vo.getBirth(),
				"身高: " + vo.getHeight(), "体重: " + vo.getWeight(), "毕业学校: " + vo.getSchool() };
		addButton();
		setTable();
		addHead();
		addPicture();
		addBack();
		addLabel();
	}

	public void addLabel() {
		for (int i = 0; i < 3; i++) {
			profileLabel[i] = new MyLabel(lbX + i * interX, lbY, width, lbHgt, lbstr[i]);
		}
		for (int i = 3; i < 6; i++) {
			profileLabel[i] = new MyLabel(lbX + (i - 3) * interX, lbY + interY, width, lbHgt, lbstr[i]);
		}
		for (int i = 6; i < 8; i++) {
			profileLabel[i] = new MyLabel(lbX + (i - 6) * interX, lbY + 2 * interY, width, lbHgt, lbstr[i]);
		}
		for (int i = 8; i < 10; i++) {
			profileLabel[i] = new MyLabel(lbX + (i - 8) * interX, lbY + 3 * interY, width, lbHgt, lbstr[i]);
		}
		for (int i = 0; i < 10; i++) {
			profileLabel[i].setHorizontalAlignment(SwingConstants.LEFT);
			this.add(profileLabel[i]);
		}
	}

	/**
	 * 返回按钮
	 * 
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
	 * 
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
	 * 
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
		columns = new String[] { "球员名称", "所属球队", "参赛场数", "先发场数", "在场时间", "投篮命中数", "投篮出手数", "投篮命中率", "三分命中数",
				"三分出手数", "三分命中率", "罚球命中数", "罚球出手数", "罚球命中率", "进攻篮板", "防守篮板", "总篮板", "助攻数", "抢断数", "盖帽数", "失误数",
				"犯规数", "得分", "两双", "得分/篮板/助攻", "效率", "GmSc 效率值", "真实命中率", "投篮效率", "进攻篮板率", "防守篮板率", "篮板率", "助攻率",
				"抢断率", "盖帽率", "失误率", "使用率" };
		PlayerSeasonRecord playerSeason = detailVO.getSeasonRecord();
		rowData = new String[1][columns.length];
		rowData[0][0] = playerSeason.getName();
		rowData[0][1] = playerSeason.getTeam();
		rowData[0][2] = playerSeason.getMatchCount() + "";
		rowData[0][3] = playerSeason.getFirstCount() + "";
		rowData[0][4] = playerSeason.getTime();
		rowData[0][5] = playerSeason.getFieldGoal() + "";
		rowData[0][6] = playerSeason.getFieldAttempt() + "";
		rowData[0][7] = df.format(playerSeason.getFieldPercent());
		rowData[0][8] = playerSeason.getThreePointGoal() + "";
		rowData[0][9] = playerSeason.getThreePointAttempt() + "";
		rowData[0][10] = df.format(playerSeason.getThreePointPercent());
		rowData[0][11] = playerSeason.getFreethrowGoal() + "";
		rowData[0][12] = playerSeason.getFreethrowAttempt() + "";
		rowData[0][13] = df.format(playerSeason.getFreethrowPercent());
		rowData[0][14] = playerSeason.getOffensiveRebound() + "";
		rowData[0][15] = playerSeason.getDefensiveRebound() + "";
		rowData[0][16] = playerSeason.getTotalRebound() + "";
		rowData[0][17] = playerSeason.getAssist() + "";
		rowData[0][18] = playerSeason.getSteal() + "";
		rowData[0][19] = playerSeason.getBlock() + "";
		rowData[0][20] = playerSeason.getTurnover() + "";
		rowData[0][21] = playerSeason.getFoul() + "";
		rowData[0][22] = playerSeason.getScore() + "";
		rowData[0][23] = playerSeason.getDoubleDouble() + "";
		rowData[0][24] = playerSeason.getScoreReboundAssist() + "";
		rowData[0][25] = playerSeason.getEfficiency() + "";
		rowData[0][26] = playerSeason.getGmSc() + "";
		rowData[0][27] = df.format(playerSeason.getRealFieldPercent());
		rowData[0][28] = playerSeason.getFieldEff() + "";
		rowData[0][29] = df.format(playerSeason.getOffensiveReboundPercent());
		rowData[0][30] = df.format(playerSeason.getDefensiveReboundPercent());
		rowData[0][31] = df.format(playerSeason.getTotalReboundPercent());
		rowData[0][32] = df.format(playerSeason.getAssistPercent());
		rowData[0][33] = df.format(playerSeason.getStealPercent());
		rowData[0][34] = df.format(playerSeason.getBlockPercent());
		rowData[0][35] = df.format(playerSeason.getTurnOverPercent());
		rowData[0][36] = df.format(playerSeason.getUsePercent());
		table = new BottomTable(rowData, columns);
		table.setRowHeight(100);
		scroll = new BottomScrollPane(table);
		scroll.setLocation(57, 285);
		this.add(scroll);
	}
}
