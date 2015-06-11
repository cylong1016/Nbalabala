package ui.panel.playerData;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.PlayerSeasonPO;
import ui.Images;
import ui.UIConfig;
import ui.common.SeasonInputPanel;
import ui.common.button.TabButton;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import utility.Constants;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import blservice.PlayerSeasonBLService;
import enums.AllPlayerSeasonTableCategory;
import enums.Position;
import enums.ScreenBasis;
import enums.ScreenDivision;
import enums.TotalOrAvg;

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
	
	/** 将枚举类型赋值为数组 */
	private static final Position[] POSITION_ARRAY = Position.values();
	private static final ScreenDivision[] DIVISION_ARRAY = ScreenDivision.values();
	private static final ScreenBasis[] BASIS_ARRAY = ScreenBasis.values();

	/** 放表格的滚动条 */
	private BottomScrollPane scroll;
	/** 代表所有button集合 */
	private PlayerPositionSelectButton[] positionSelectButtons;
	private PlayerDivisionSelectButton[] divisionSelectButtons;
	private PlayerScreenSelectButton[] screenSelectButtons;
	private PlayerTotalAvgButton[] totalAvgButtons;

	private TabButton tab[];
	/** 通过接口调用方法 */
	private PlayerSeasonBLService playerSeason = new PlayerSeasonAnalysis();
	
	private AllPlayerSeasonTableCategory current;
	/** 赛季选择器 */
	private SeasonInputPanel seasonInput;
	
	private AllPlayerSeasonTable table;
	private PlayerSeasonBLService playerblService;
	private TotalOrAvg totalOrAvg;
	
	public PlayerDataPanel(String url) {
		super(url);
		setButton();
		addButton();

		iniSet();
		addTab();
		addListener();
		current = AllPlayerSeasonTableCategory.BASIC;
		totalOrAvg = TotalOrAvg.TOTAL;
		seasonInput = new SeasonInputPanel(this);
		seasonInput.setBounds(54, TOTAL_Y,210,ROW_HEIGHT);
		this.add(seasonInput); 
		playerblService = new PlayerSeasonAnalysis();
		// 初始化界面的表格
		ArrayList<PlayerSeasonPO> iniArray = playerSeason.getAllPlayersSortedByName();
		// set表格 
		table = new AllPlayerSeasonTable(playerblService,iniArray,AllPlayerSeasonTableCategory.BASIC,TotalOrAvg.TOTAL);
		table.setHeaderColorAndFont();
		table.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		table.setWidth(new int[] {33, 165, 98, 49, 49, 49, 49, 49, 49, 49, 49, 49, 53, 49, 49});
		addScrollPane(table);
	}
	
	private void addTab() {
		tab = new TabButton[4];
		for(int i = 0 ;i < 4; i++) {
			tab[i] = new TabButton(Constants.PLAYER_DATA_SORT[i],Images.TEAM_FIRST_LEVEL_TAB_MOVE_ON, Images.TEAM_FIRST_LEVEL_TAB_CHOSEN);
			tab[i].setLocation(24 + i * 237, 198);
			this.add(tab[i]);
			tab[0].setOn();
		}
		tab[0].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 current = AllPlayerSeasonTableCategory.BASIC;
				 tab[0].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[3].setOff();
				 table.changeCategory(AllPlayerSeasonTableCategory.BASIC);
				 scroll.setHead();
			 }
		});
		tab[1].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 current = AllPlayerSeasonTableCategory.OFFENSIVE;
				 tab[1].setOn();
				 tab[0].setOff();
				 tab[2].setOff();
				 tab[3].setOff();
				 table.changeCategory(AllPlayerSeasonTableCategory.OFFENSIVE);
				 scroll.setHead();
			 }
		});
		tab[2].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 current = AllPlayerSeasonTableCategory.DEFENSIVE;
				 tab[2].setOn();
				 tab[1].setOff();
				 tab[0].setOff();
				 tab[3].setOff();
				 table.changeCategory(AllPlayerSeasonTableCategory.DEFENSIVE);
				 scroll.setHead();
			 }
		});
		tab[3].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 current = AllPlayerSeasonTableCategory.ADVANCED;
				 tab[3].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[0].setOff();
				 table.changeCategory(AllPlayerSeasonTableCategory.ADVANCED);
				 scroll.setHead();
			 }
		});
	}

	public void refresh(){
		ArrayList<PlayerSeasonPO> iniArray = playerSeason.getScreenedPlayers
				(PlayerPositionSelectButton.current.position, PlayerDivisionSelectButton.current.division, 
						PlayerScreenSelectButton.current.basis, seasonInput.getSeason());
		table = new AllPlayerSeasonTable(playerblService,iniArray,current,totalOrAvg);
		addScrollPane(table);
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
					POSITION_SELECT_Y[i], WIDTH, HEIGHT, Constants.POSITION_SELECT_TEXT[i]);
			positionSelectButtons[i].position = POSITION_ARRAY[i];
		}
		for (int i = 0; i < DIVISION_COUNT; i++) {
			if (i == 3 || i == 6) {
				divisionSelectButtons[i] = new PlayerDivisionSelectButton(DIVISION_SELECT_X[i],
						DIVISION_SELECT_Y[i], WIDTH_THREE, HEIGHT, Constants.DIVISION_SELECT_TEXT[i]);
			} else {
				divisionSelectButtons[i] = new PlayerDivisionSelectButton(DIVISION_SELECT_X[i],
						DIVISION_SELECT_Y[i], WIDTH, HEIGHT, Constants.DIVISION_SELECT_TEXT[i]);
			}
			divisionSelectButtons[i].division = DIVISION_ARRAY[i];
		}
		for (int i = 0; i < BASIS_COUNT; i++) {
			if (i == 4) {
				screenSelectButtons[i] = new PlayerScreenSelectButton(BASIS_SELECT_X[i], BASIS_SELECT_Y[i], 
						WIDTH_LONG, HEIGHT, Constants.BASIS_SELECT_TEXT[i]);
			} else {
				screenSelectButtons[i] = new PlayerScreenSelectButton(BASIS_SELECT_X[i], BASIS_SELECT_Y[i], 
						WIDTH, HEIGHT, Constants.BASIS_SELECT_TEXT[i]);
			}
			screenSelectButtons[i].basis = BASIS_ARRAY[i];
		}
		for (int i = 0; i < TOTAL_AVG_COUNT; i++) {
			totalAvgButtons[i] = new PlayerTotalAvgButton(TOTAL_AVG_X[i], TOTAL_Y, 72, ROW_HEIGHT, 
					Constants.TOTAL_AVG_TEXT[i]);
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
		button.setBackground(UIConfig.BUTTON_COLOR);
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
			ArrayList<PlayerSeasonPO> playerRecords = playerSeason.getScreenedPlayers(
					PlayerPositionSelectButton.current.position, PlayerDivisionSelectButton.current.division, PlayerScreenSelectButton.current.basis, seasonInput.getSeason());
			table = new AllPlayerSeasonTable(playerblService,playerRecords,current,totalOrAvg);
			addScrollPane(table);
		}
	}

	class DivisionListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == PlayerDivisionSelectButton.current) {
				return;
			}
			PlayerDivisionSelectButton.current.back();
			PlayerDivisionSelectButton.current = (PlayerDivisionSelectButton) e.getSource();
			ArrayList<PlayerSeasonPO> playerRecords = playerSeason.getScreenedPlayers(
					PlayerPositionSelectButton.current.position, PlayerDivisionSelectButton.current.division, PlayerScreenSelectButton.current.basis, seasonInput.getSeason());
			table = new AllPlayerSeasonTable(playerblService,playerRecords,current,totalOrAvg);
			addScrollPane(table);
		}
	}

	class BasisListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == PlayerScreenSelectButton.current) {
				return;
			}
			PlayerScreenSelectButton.current.back();
			PlayerScreenSelectButton.current = (PlayerScreenSelectButton) e.getSource();
			ArrayList<PlayerSeasonPO> playerRecords = playerSeason.getScreenedPlayers(
					PlayerPositionSelectButton.current.position, PlayerDivisionSelectButton.current.division, PlayerScreenSelectButton.current.basis, seasonInput.getSeason());
			table = new AllPlayerSeasonTable(playerblService,playerRecords,current,totalOrAvg);
			addScrollPane(table);
		}
	}

	class TotalAvgListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == PlayerTotalAvgButton.current) {
				return;
			}
			PlayerTotalAvgButton.current.back();
			PlayerTotalAvgButton.current = (PlayerTotalAvgButton) e.getSource();
			if (PlayerTotalAvgButton.current == totalAvgButtons[0]) {
				totalOrAvg = TotalOrAvg.TOTAL;
				table.changeTotalOrAvg(totalOrAvg);
			} else if (PlayerTotalAvgButton.current == totalAvgButtons[1]) {
				totalOrAvg = TotalOrAvg.AVG;
				table.changeTotalOrAvg(totalOrAvg);
			}
		}
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
		scroll.setBounds(54, 290, table.setTableWidth(UIConfig.TABLE_H, table.getRowCount()), UIConfig.TABLE_H);
		this.add(scroll);
	}

}
