package ui.panel.playerData;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import ui.Images;
import ui.UIConfig;
import ui.common.SeasonInputPanel;
import ui.common.UserMouseAdapter;
import ui.common.button.TabButton;
import ui.common.button.TextButton;
import ui.common.comboBox.MyComboBox;
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
 * @version 2015年5月17日 下午2:43:36
 */
public class PlayerDataPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** button的宽度 高度  */
	private static final int WIDTH = 60, HEIGHT = 24, WIDTH_THREE = 75, WIDTH_LONG = 135;
	/** 每行的button个数 */
	private static final int POSITION_COUNT = 4, DIVISION_COUNT = 9, BASIS_COUNT = 15, 
			TOTAL_AVG_COUNT = 2;
	
	/** “所有”的横坐标 */
	private static final int ALL_X_1 = 202,ALL_X_2 = 430,ALL_X_3 = 702,ALL_Y = 14;
	
	/** 第i列的横坐标 */
	private static final int X1 = 140,X2 = 290,X3 = 355,X4 = 422,X5 = 470,X6 =560 ,X7 =635 ,X8 = 705,X9 =772
			,X10 = 845;
	
	/** 第i列的纵坐标 */
	private static final int Y1 = 55,Y2 = 94,Y3 = 133;
	
	/** 总计 平均 */
	private static final int TOTAL_X = 796, AVERAGE_X = 870,TOTAL_Y = 243,ROW_HEIGHT = 30;

	/** 所有button横坐标集合 */
	private static final int[] POSITION_SELECT_X = new int[] { ALL_X_1, X1, X1,X1};
	private static final int[] DIVISION_SELECT_X = new int[] { ALL_X_2, X2, X2, X2, X3, 
		X4, X5, X5, X5};
	private static final int[] BASIS_SELECT_X = new int[] { ALL_X_3, X6, X6, X6, X9, 
		X7, X7, X7, X8, X8, X8,X9, X9, X10, X10};
	private static final int[] TOTAL_AVG_X = new int[] { TOTAL_X, AVERAGE_X };
	private static final int[] POSITION_SELECT_Y = new int[]{ALL_Y,Y1,Y2,Y3};
	private static final int[] DIVISION_SELECT_Y = new int[]{ALL_Y,Y1,Y2,Y3,Y2,Y2,Y1,Y2,Y3};
	private static final int[] BASIS_SELECT_Y = new int[]{ALL_Y,Y1,Y2,Y3,Y3,Y1,Y2,Y3,Y1,Y2,Y3,Y1,Y2,Y1,Y2};
	
	
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

	private MyComboBox box;
	private TabButton tab[];
	/** 通过接口调用方法 */
	private PlayerSeasonBLService playerSeason = new PlayerSeasonAnalysis();
	
	/** 赛季选择器 */
	private SeasonInputPanel seasonInput;

	public PlayerDataPanel(String url) {
		super(url);
		setButton();
		addButton();

		iniSet();
		addCombobox();
		addTab();
		addListener();
		addFindButton();
		seasonInput = new SeasonInputPanel(this);
		seasonInput.setBounds(54, TOTAL_Y,115,ROW_HEIGHT);
		this.add(seasonInput); 
		// 初始化界面的表格
		ArrayList<PlayerSeasonVO> iniArray = playerSeason.getAllPlayersSortedByName();
		createTable(iniArray);
	}
	
	private void addTab() {
		tab = new TabButton[4];
		for(int i = 0 ;i < 4; i++) {
			tab[i] = new TabButton(Constants.PLAYER_DATA_SORT[i],Images.TEAM_FIRST_LEVEL_TAB_MOVE_ON, Images.TEAM_FIRST_LEVEL_TAB_CHOSEN);
			tab[i].setLocation(24 + i * 237, 198);
			this.add(tab[i]);
		}
		tab[0].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[0].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[3].setOff();
			 }
		});
		tab[1].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[1].setOn();
				 tab[0].setOff();
				 tab[2].setOff();
				 tab[3].setOff();
			 }
		});
		tab[2].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[2].setOn();
				 tab[1].setOff();
				 tab[0].setOff();
				 tab[3].setOff();
			 }
		});
		tab[3].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[3].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[0].setOff();
			 }
		});
	}

	private void addCombobox() {
		String[] list = {"常规赛","季后赛"};
		box = new MyComboBox(list,174,TOTAL_Y,115,ROW_HEIGHT);
		this.add(box);
	}

	public void refresh(){
		ArrayList<PlayerSeasonVO> iniArray = playerSeason.getScreenedPlayers
				(PlayerPositionSelectButton.current.position, PlayerDivisionSelectButton.current.division, 
						PlayerScreenSelectButton.current.basis, seasonInput.getSeason());
		createTable(iniArray);
	}

	public void addFindButton() {
//		findButton = new ImgButton("images/playerData/search.png", 856, 130, "images/playerData/searchOn.png",
//				"images/playerData/searchClick.png");
//		this.add(findButton);
//		findButton.addMouseListener(new MouseAdapter() {
//
//			public void mousePressed(MouseEvent e) {
//				ArrayList<PlayerSeasonVO> playerRecords = playerSeason.getScreenedPlayers(
//						PlayerPositionSelectButton.current.position, PlayerDivisionSelectButton.current.division, PlayerScreenSelectButton.current.basis, seasonInput.getSeason());
//				if (PlayerTotalAvgButton.current == totalAvgButtons[0]) {
//					createTable(playerRecords); // 添加球员总数居
//				} else if (PlayerTotalAvgButton.current == totalAvgButtons[1]) {
//					createTable(playerRecords); // 添加球员场均数居
//				}
//			}
//		});
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
					POSITION_SELECT_Y[i], WIDTH, HEIGHT, POSITION_SELECT_TEXT[i]);
			positionSelectButtons[i].position = POSITION_ARRAY[i];
		}
		for (int i = 0; i < DIVISION_COUNT; i++) {
			if (i == 3 || i == 6) {
				divisionSelectButtons[i] = new PlayerDivisionSelectButton(DIVISION_SELECT_X[i],
						DIVISION_SELECT_Y[i], WIDTH_THREE, HEIGHT, DIVISION_SELECT_TEXT[i]);
			} else {
				divisionSelectButtons[i] = new PlayerDivisionSelectButton(DIVISION_SELECT_X[i],
						DIVISION_SELECT_Y[i], WIDTH, HEIGHT, DIVISION_SELECT_TEXT[i]);
			}
			divisionSelectButtons[i].division = DIVISION_ARRAY[i];
		}
		for (int i = 0; i < BASIS_COUNT; i++) {
			if (i == 4) {
				screenSelectButtons[i] = new PlayerScreenSelectButton(BASIS_SELECT_X[i], BASIS_SELECT_Y[i], 
						WIDTH_LONG, HEIGHT, BASIS_SELECT_TEXT[i]);
			} else {
				screenSelectButtons[i] = new PlayerScreenSelectButton(BASIS_SELECT_X[i], BASIS_SELECT_Y[i], 
						WIDTH, HEIGHT, BASIS_SELECT_TEXT[i]);
			}
			screenSelectButtons[i].basis = BASIS_ARRAY[i];
		}
		for (int i = 0; i < TOTAL_AVG_COUNT; i++) {
			totalAvgButtons[i] = new PlayerTotalAvgButton(TOTAL_AVG_X[i], TOTAL_Y, 72, ROW_HEIGHT, 
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
					PlayerDivisionSelectButton.current.division, PlayerScreenSelectButton.current.basis, seasonInput.getSeason());
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
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFieldPercent()), i, 8);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getThreePointGoal()), i, 9);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getThreePointAttempt()), i, 10);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getThreePointPercent()), i, 11);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getFreeThrowGoal()), i, 12);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getFreeThrowAttempt()), i, 13);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFreeThrowPercent()), i, 14);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getOffensiveRebound()), i, 15);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getDefensiveRebound()), i, 16);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getTotalRebound()), i, 17);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getAssist()), i, 18);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getAssistPercent()), i, 19);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getBlock()), i, 20);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getBlockPercent()), i, 21);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getFoul()), i, 22);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFoulPercent()), i, 23);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getScore()), i, 24);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getDoubleDouble()), i, 25);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getScoreReboundAssist()), i, 26);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getEfficiency()), i, 27);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getGmSc()), i, 28);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getRealFieldPercent()), i, 29);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFieldEff()), i, 30);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getOffensiveReboundPercent()), i, 31);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getDefensiveReboundPercent()), i, 32);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getTotalReboundPercent()), i, 33);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getSteal()), i, 34);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getStealPercent()), i, 35);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getTurnover()), i, 36);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getTurnOverPercent()), i, 37);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getUsePercent()), i, 38);
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
						MainController.toPlayerInfoPanel(playerArr.get(rowI).getName(), PlayerDataPanel.this);
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
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFirstCountAvg()), i, 4);
			playerDataTable.setValueAt(playerSeason.getTimeAvg(), i, 5);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFieldGoalAvg()), i, 6);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFieldAttemptAvg()), i, 7);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFieldPercent()), i, 8);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getThreePointGoalAvg()), i, 9);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getThreePointAttemptAvg()), i, 10);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getThreePointPercent()), i, 11);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFreethrowGoalAvg()), i, 12);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFreethrowAttemptAvg()), i, 13);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFreeThrowPercent()), i, 14);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getOffensiveReboundAvg()), i, 15);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getDefensiveReboundAvg()), i, 16);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getTotalReboundAvg()), i, 17);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getAssistAvg()), i, 18);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getAssistPercent()), i, 19);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getBlockAvg()), i, 20);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getBlockPercent()), i, 21);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFoulAvg()), i, 22);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFoulPercent()), i, 23);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getScoreAvg()), i, 24);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getDoubleDoubleAvg()), i, 25);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getScoreReboundAssistAvg()), i, 26);
			playerDataTable.setValueAt(Integer.toString(playerSeason.getEfficiency()), i, 27);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getGmSc()), i, 28);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getRealFieldPercent()), i, 29);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getFieldEff()), i, 30);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getOffensiveReboundPercent()), i, 31);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getDefensiveReboundPercent()), i, 32);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getTotalReboundPercent()), i, 33);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getStealAvg()), i, 34);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getStealPercent()), i, 35);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getTurnoverAvg()), i, 36);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getTurnOverPercent()), i, 37);
			playerDataTable.setValueAt(UIConfig.FORMAT.format(playerSeason.getUsePercent()), i, 38);
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
	public void createTable(ArrayList<PlayerSeasonVO> playerRecords) {
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
		scroll.setLocation(50, 290); // 表格的位置
		this.add(scroll);
	}

}
