package ui.panel.playerData;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import ui.UIConfig;
import ui.common.UserMouseAdapter;
import ui.common.button.ImgButton;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import vo.PlayerSeasonVO;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import blservice.PlayerSeasonBLService;
import enums.PlayerAllSortBasis;
import enums.PlayerAvgSortBasis;
import enums.Position;
import enums.ScreenBasis;
import enums.ScreenDivision;
import enums.SortOrder;

/**
 * 球员数据界面
 * 
 * @author lsy
 * @version 2015年3月18日 下午6:28:36
 */
public class PlayerDataPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** button的宽度 高度  */
	private static final int WIDTH = 60, HEIGHT = 24, WIDTH_THREE = 75, WIDTH_LONG = 135;
	/** 每行的button个数 */
	private static final int POSITION_COUNT = 4, DIVISION_COUNT = 9, BASIS_COUNT = 15, 
			TOTAL_AVG_COUNT = 2;
	/** 分别代表第一行到第五行的纵坐标 */
	private static final int Y1 = 27, Y2 = 66, Y3 = 107, Y4 = 138, Y5 = 171;
	/** 代表所有列的前三个button的横坐标 */
	private static final int X1 = 227, X2 = 288, X3 = 349;
	/** “所有”的横坐标 */
	private static final int ALL_X = 156;
	/** 间隔 */
	private static final int INTER = 61;
	/** 东部 西部 太平洋 西北 西南 这些按钮的横坐标 */
	private static final int EAST_X = 440, WEST_X = EAST_X + INTER, 
			PACIFIC_X = WEST_X + WIDTH + EAST_X - X3 - WIDTH_THREE,
			NORTH_WEST_X = PACIFIC_X + INTER + WIDTH_THREE - WIDTH, 
			SOUTH_WEST_X = NORTH_WEST_X + INTER;
	/** 得分/篮板/助攻 盖帽 抢断 犯规 失误 分钟  的横坐标*/
	private static final int THREE_POINT_X = X3 + INTER, 
			BLOCK_X = THREE_POINT_X + INTER + WIDTH_LONG - WIDTH, 
			STEAL_X = BLOCK_X + INTER, FOUL_X = BLOCK_X + 2 * INTER, 
			TURNOVER_X = BLOCK_X + 3 * INTER, MINUTE_X = BLOCK_X + 4 * INTER;
	/** 罚球 两双 */
	private static final int  FREETHROW_X = X3 + INTER, DOUBLE_DOUBLE_X = FREETHROW_X + INTER;
	/** 总计 平均 */
	private static final int TOTAL_X = 756, AVERAGE_X = 823;

	/** 所有button横坐标集合 */
	private static final int[] POSITION_SELECT_X = new int[] { ALL_X, X1, X2, X3 };
	private static final int[] DIVISION_SELECT_X = new int[] { ALL_X, X1, X2, X3, EAST_X, 
		WEST_X, PACIFIC_X, NORTH_WEST_X, SOUTH_WEST_X };
	private static final int[] BASIS_SELECT_X = new int[] { ALL_X, X1, X2, X3, THREE_POINT_X, 
		BLOCK_X, STEAL_X, FOUL_X, TURNOVER_X, MINUTE_X, X1,X2, X3, FREETHROW_X, DOUBLE_DOUBLE_X };
	private static final int[] TOTAL_AVG_X = new int[] { TOTAL_X, AVERAGE_X };

	/** 所有button上文字部分横坐标集合 */
	private static final String[] POSITION_SELECT_TEXT = new String[] { "所有", "前锋", "中锋", "后卫" };
	private static final String[] DIVISION_SELECT_TEXT = new String[] { "所有", "东南", "中央", 
		"大西洋", "东部", "西部", "太平洋", "西北", "西南" };
	private static final String[] BASIS_SELECT_TEXT = new String[] { "所有", "得分", "篮板", "助攻", 
		"得分/篮板/助攻", "盖帽", "抢断", "犯规", "失误", "分钟","效率", "投篮", "三分", "罚球", "两双", "总计" };
	private static final String[] TOTAL_AVG_TEXT = new String[] { "总计", "平均" };
	/** 将枚举类型赋值为数组 */
	private static final Position[] POSITION_ARRAY = Position.values();
	private static final ScreenDivision[] DIVISION_ARRAY = ScreenDivision.values();
	private static final ScreenBasis[] BASIS_ARRAY = ScreenBasis.values();

	/** 球员数据表格 */
	private BottomTable playerDataTable;
	/** 放表格的滚动条 */
	private BottomScrollPane scroll;
	/** 代表所有button集合 */
	private PlayerPositionSelectButton[] positionSelectButtons;
	private PlayerDivisionSelectButton[] divisionSelectButtons;
	private PlayerScreenSelectButton[] screenSelectButtons;
	private PlayerTotalAvgButton[] totalAvgButtons;
	/** 查询按钮 */
	private ImgButton findButton;
	
	/** 通过接口调用方法 */
	private PlayerSeasonBLService playerSeason = new PlayerSeasonAnalysis();

	public PlayerDataPanel(String url) {
		super(url);
		setButton();
		addButton();

		iniSet();
		addListener();
		addFindButton();
		// 初始化界面的表格
		ArrayList<PlayerSeasonVO> iniArray = playerSeason.getAllPlayersSortedByName();
		createTable(iniArray);
	}

	public void addFindButton() {
		findButton = new ImgButton("images/playerData/search.png", 856, 124, "images/playerData/searchOn.png",
				"images/playerData/searchClick.png");
		this.add(findButton);
		findButton.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				ArrayList<PlayerSeasonVO> playerRecords = playerSeason.getScreenedPlayers(
						PlayerPositionSelectButton.current.position, PlayerDivisionSelectButton.current.division, PlayerScreenSelectButton.current.basis);
				if (PlayerTotalAvgButton.current == totalAvgButtons[0]) {
					createTable(playerRecords); // 添加球员总数居
				} else if (PlayerTotalAvgButton.current == totalAvgButtons[1]) {
					createTable(playerRecords); // 添加球员场均数居
				}
			}
		});
	}

	/**
	 * 初始化四个“所有”按钮，初始化为选中状态
	 * 
	 * @author lsy
	 * @version 2015年3月19日 下午8:44:58
	 */
	public void iniSet() {
		PlayerPositionSelectButton.current = positionSelectButtons[0];
		PlayerDivisionSelectButton.current = divisionSelectButtons[0];
		PlayerScreenSelectButton.current = screenSelectButtons[0];
		PlayerTotalAvgButton.current = totalAvgButtons[0];
		setEffect(PlayerPositionSelectButton.current);
		setEffect(PlayerDivisionSelectButton.current);
		setEffect(PlayerScreenSelectButton.current);
		setEffect(PlayerTotalAvgButton.current);
	}

	/**
	 * 设置条件按钮
	 * 
	 * @author lsy
	 * @version 2015年3月19日 下午9:00:34
	 */
	public void setButton() {
		positionSelectButtons = new PlayerPositionSelectButton[POSITION_COUNT];
		divisionSelectButtons = new PlayerDivisionSelectButton[DIVISION_COUNT];
		screenSelectButtons = new PlayerScreenSelectButton[BASIS_COUNT];
		totalAvgButtons = new PlayerTotalAvgButton[TOTAL_AVG_COUNT];
		for (int i = 0; i < POSITION_COUNT; i++) {
			positionSelectButtons[i] = new PlayerPositionSelectButton(POSITION_SELECT_X[i], 
					Y1, WIDTH, HEIGHT, POSITION_SELECT_TEXT[i]);
			positionSelectButtons[i].position = POSITION_ARRAY[i];
		}
		for (int i = 0; i < DIVISION_COUNT; i++) {
			if (i == 3 || i == 6) {
				divisionSelectButtons[i] = new PlayerDivisionSelectButton(DIVISION_SELECT_X[i],
						Y2, WIDTH_THREE, HEIGHT, DIVISION_SELECT_TEXT[i]);
			} else {
				divisionSelectButtons[i] = new PlayerDivisionSelectButton(DIVISION_SELECT_X[i],
						Y2, WIDTH, HEIGHT, DIVISION_SELECT_TEXT[i]);
			}
			divisionSelectButtons[i].division = DIVISION_ARRAY[i];
		}
		for (int i = 0; i < 10; i++) {
			if (i == 4) {
				screenSelectButtons[i] = new PlayerScreenSelectButton(BASIS_SELECT_X[i], Y3, 
						WIDTH_LONG, HEIGHT, BASIS_SELECT_TEXT[i]);
			} else {
				screenSelectButtons[i] = new PlayerScreenSelectButton(BASIS_SELECT_X[i], Y3, 
						WIDTH, HEIGHT, BASIS_SELECT_TEXT[i]);
			}
			screenSelectButtons[i].basis = BASIS_ARRAY[i];
		}
		for (int i = 10; i < BASIS_COUNT; i++) {
			screenSelectButtons[i] = new PlayerScreenSelectButton(BASIS_SELECT_X[i], Y4, WIDTH,
					HEIGHT, BASIS_SELECT_TEXT[i]);
			screenSelectButtons[i].basis = BASIS_ARRAY[i];
		}
		for (int i = 0; i < TOTAL_AVG_COUNT; i++) {
			totalAvgButtons[i] = new PlayerTotalAvgButton(TOTAL_AVG_X[i], Y5, WIDTH, HEIGHT, 
					TOTAL_AVG_TEXT[i]);
		}
	}

	/**
	 * 添加条件按钮
	 * 
	 * @author lsy
	 * @version 2015年3月19日 下午9:00:47
	 */
	public void addButton() {
		for (int i = 0; i < POSITION_COUNT; i++) {
			this.add(positionSelectButtons[i]);
		}
		for (int i = 0; i < DIVISION_COUNT; i++) {
			this.add(divisionSelectButtons[i]);
		}
		for (int i = 0; i < BASIS_COUNT; i++) {
			this.add(screenSelectButtons[i]);
		}
		for (int i = 0; i < TOTAL_AVG_COUNT; i++) {
			this.add(totalAvgButtons[i]);
		}
	}

	/**
	 * 设置按钮初始被选中的效果
	 * 
	 * @author lsy
	 * @version 2015年3月18日 下午11:43:53
	 */
	public void setEffect(TextButton button) {
		button.setOpaque(true);
		button.setBackground(new Color(15, 24, 44));
		button.setForeground(Color.white);
	}

	/**
	 * 按钮添加监听
	 * 
	 * @author lsy
	 * @version 2015年3月19日 下午9:00:59
	 */
	public void addListener() {
		PositionListener positionListener = new PositionListener();
		DivisionListener divisionListener = new DivisionListener();
		BasisListener basisListener = new BasisListener();
		TotalAvgListener totalAvgListener = new TotalAvgListener();
		for (int i = 0; i < POSITION_COUNT; i++) {
			positionSelectButtons[i].addMouseListener(positionListener);
		}
		for (int i = 0; i < DIVISION_COUNT; i++) {
			divisionSelectButtons[i].addMouseListener(divisionListener);
		}
		for (int i = 0; i < BASIS_COUNT; i++) {
			screenSelectButtons[i].addMouseListener(basisListener);
		}
		for (int i = 0; i < TOTAL_AVG_COUNT; i++) {
			totalAvgButtons[i].addMouseListener(totalAvgListener);
		}
	}

	class PositionListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == PlayerPositionSelectButton.current) {
				return;
			}
			PlayerPositionSelectButton.current.back();
			PlayerPositionSelectButton.current = (PlayerPositionSelectButton) e.getSource();
		}
	}

	class DivisionListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == PlayerDivisionSelectButton.current) {
				return;
			}
			PlayerDivisionSelectButton.current.back();
			PlayerDivisionSelectButton.current = (PlayerDivisionSelectButton) e.getSource();
		}
	}

	class BasisListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == PlayerScreenSelectButton.current) {
				return;
			}
			PlayerScreenSelectButton.current.back();
			PlayerScreenSelectButton.current = (PlayerScreenSelectButton) e.getSource();
		}
	}

	class TotalAvgListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == PlayerTotalAvgButton.current) {
				return;
			}
			PlayerTotalAvgButton.current.back();
			PlayerTotalAvgButton.current = (PlayerTotalAvgButton) e.getSource();
			ArrayList<PlayerSeasonVO> playerRecords = playerSeason.getScreenedPlayers(PlayerPositionSelectButton.current.position,
					PlayerDivisionSelectButton.current.division, PlayerScreenSelectButton.current.basis);
			if (PlayerTotalAvgButton.current == totalAvgButtons[0]) {
				updateTotalPlayerDataTable(playerRecords); // 添加球员总数居
			} else if (PlayerTotalAvgButton.current == totalAvgButtons[1]) {
				updateAvgPlayerDataTable(playerRecords); // 添加球员场均数居
			}
		}
	}

	/**
	 * 添加全部球员赛季总数据
	 * 
	 * @param playerArr
	 *            逻辑层返回的球员数据
	 * @author cylong
	 * @version 2015年3月24日 下午11:43:17
	 */
	private void updateTotalPlayerDataTable(ArrayList<PlayerSeasonVO> playerArr) {
		for (int i = 0; i < playerArr.size(); i++) {
			PlayerSeasonVO playerSeason = playerArr.get(i);
			playerDataTable.setValueAt(Integer.toString(i + 1), i, 0);
			playerDataTable.setValueAt(playerSeason.getName(), i, 1);
			playerDataTable.setValueAt(Constants.translateTeamAbbr(playerSeason.getTeam()), i, 2);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getMatchCount()), i, 3);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getFirstCount()), i, 4);
			playerDataTable.setValueAt(playerSeason.getTime(), i, 5);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getFieldGoal()), i, 6);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getFieldAttempt()), i, 7);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFieldPercent()), i, 8);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getThreePointGoal()), i, 9);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getThreePointAttempt()), i, 10);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getThreePointPercent()), i, 11);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getFreeThrowGoal()), i, 12);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getFreeThrowAttempt()), i, 13);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFreeThrowPercent()), i, 14);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getOffensiveRebound()), i, 15);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getDefensiveRebound()), i, 16);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getTotalRebound()), i, 17);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getAssist()), i, 18);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getAssistPercent()), i, 19);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getBlock()), i, 20);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getBlockPercent()), i, 21);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getFoul()), i, 22);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFoulPercent()), i, 23);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getScore()), i, 24);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getDoubleDouble()), i, 25);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getScoreReboundAssist()), i, 26);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getEfficiency()), i, 27);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getGmSc()), i, 28);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getRealFieldPercent()), i, 29);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFieldEff()), i, 30);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getOffensiveReboundPercent()), i, 31);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getDefensiveReboundPercent()), i, 32);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getTotalReboundPercent()), i, 33);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getSteal()), i, 34);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getStealPercent()), i, 35);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getTurnover()), i, 36);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getTurnOverPercent()), i, 37);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getUsePercent()), i, 38);
		}
		
	}

	public void addListener(final BottomTable table,final ArrayList<PlayerSeasonVO> playerArr) {
		try {
			table.addMouseListener(new UserMouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(PlayerDataPanel.this, 
								playerArr.get(rowI).getName(), PlayerDataPanel.this);
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加全部球员赛季平均数据
	 * 
	 * @param playerArr
	 *            逻辑层返回的球员数据
	 * @author cylong
	 * @version 2015年3月25日 上午2:02:02
	 */
	private void updateAvgPlayerDataTable(ArrayList<PlayerSeasonVO> playerArr) {
		for (int i = 0; i < playerArr.size(); i++) {
			PlayerSeasonVO playerSeason = playerArr.get(i);
			playerDataTable.setValueAt(Integer.toString(i + 1), i, 0);
			playerDataTable.setValueAt(playerSeason.getName(), i, 1);
			playerDataTable.setValueAt(Constants.translateTeamAbbr(playerSeason.getTeam()), i, 2);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getMatchCount()), i, 3);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFirstCountAvg()), i, 4);
			playerDataTable.setValueAt(playerSeason.getTimeAvg(), i, 5);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFieldGoalAvg()), i, 6);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFieldAttemptAvg()), i, 7);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFieldPercent()), i, 8);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getThreePointGoalAvg()), i, 9);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getThreePointAttemptAvg()), i, 10);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getThreePointPercent()), i, 11);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFreethrowGoalAvg()), i, 12);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFreethrowAttemptAvg()), i, 13);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFreeThrowPercent()), i, 14);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getOffensiveReboundAvg()), i, 15);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getDefensiveReboundAvg()), i, 16);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getTotalReboundAvg()), i, 17);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getAssistAvg()), i, 18);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getAssistPercent()), i, 19);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getBlockAvg()), i, 20);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getBlockPercent()), i, 21);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFoulAvg()), i, 22);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFoulPercent()), i, 23);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getScoreAvg()), i, 24);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getDoubleDoubleAvg()), i, 25);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getScoreReboundAssistAvg()), i, 26);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getEfficiency()), i, 27);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getGmSc()), i, 28);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getRealFieldPercent()), i, 29);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getFieldEff()), i, 30);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getOffensiveReboundPercent()), i, 31);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getDefensiveReboundPercent()), i, 32);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getTotalReboundPercent()), i, 33);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getStealAvg()), i, 34);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getStealPercent()), i, 35);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getTurnoverAvg()), i, 36);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getTurnOverPercent()), i, 37);
			playerDataTable.setValueAt(UIConfig.format.format(playerSeason.getUsePercent()), i, 38);
		}
	}

	/** 0表示下一次点击降序，1表示下一次点击升序排列 */
	private int clickedNum = 0;
	/** 记录上次点击的是哪一列 */
	private int lastClickColumn = 0;

	/**
	 * 根据球员数据创建表格
	 * 
	 * @param teamArr
	 * @return 表格数据的二维数组
	 * @author cylong
	 * @version 2015年3月29日 下午3:59:35
	 */
	private void createTable(ArrayList<PlayerSeasonVO> playerRecords) {
		String[][] rowData = new String[playerRecords.size()][Constants.PLAYER_SEASON_HEADERS.length];
		playerDataTable = new BottomTable(rowData, Constants.PLAYER_SEASON_HEADERS);
		playerDataTable.getColumnModel().getColumn(26).setPreferredWidth(95);
		addScrollPane(playerDataTable);
		TableColumn nameColom = playerDataTable.getColumnModel().getColumn(1); // 球员名称那列
		nameColom.setPreferredWidth(150); // 防止球员名称显示不出来

		if (PlayerTotalAvgButton.current == totalAvgButtons[0]) {
			updateTotalPlayerDataTable(playerRecords); // 添加球员总数居
		} else if (PlayerTotalAvgButton.current == totalAvgButtons[1]) {
			updateAvgPlayerDataTable(playerRecords); // 添加球员场均数居
		}

		// 给表头添加监听，用来排序
		final JTableHeader header = playerDataTable.getTableHeader();
		header.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int index = header.columnAtPoint(e.getPoint());
				if (index < 1) { // 确定所点击区域在第1列之后，第一列不需要排序
					return;
				}
				// 若跟上次点击同一列，升序变降序，降序变升序
				if (index == lastClickColumn) {
					clickedNum = 1 - clickedNum;
				} else {
					clickedNum = 0;
					lastClickColumn = index;
				}

				SortOrder sort = null; // 升序降序
				if (clickedNum == 1) {
					sort = SortOrder.AS;
				} else {
					sort = SortOrder.DE;
				}

				if (PlayerTotalAvgButton.current == totalAvgButtons[0]) {
					PlayerAllSortBasis[] basis = PlayerAllSortBasis.values();
					ArrayList<PlayerSeasonVO> seasonArray = playerSeason.getResortedPlayersAllData(
							basis[index - 1], sort);
					updateTotalPlayerDataTable(seasonArray); // 重排球员总数居
				} else if (PlayerTotalAvgButton.current == totalAvgButtons[1]) {
					PlayerAvgSortBasis[] basis = PlayerAvgSortBasis.values();
					ArrayList<PlayerSeasonVO> seasonArray = playerSeason.getResortedPlayersAvgData(
							basis[index - 1], sort);
					updateAvgPlayerDataTable(seasonArray); // 重排球员平均数据
				}

			}
		});
		addListener(playerDataTable, playerRecords);
	}

	/**
	 * 将表格添加到ScrollPane上面
	 * 
	 * @param table
	 * @author cylong
	 * @version 2015年3月26日 下午7:27:37
	 */
	private void addScrollPane(BottomTable table) {
		if (scroll != null) {
			this.remove(scroll);
		}
		scroll = new BottomScrollPane(table);
		scroll.setLocation(57, 239); // 表格的位置
		this.add(scroll);
	}

}
