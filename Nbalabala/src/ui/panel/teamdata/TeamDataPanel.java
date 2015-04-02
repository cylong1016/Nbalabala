package ui.panel.teamdata;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.JTableHeader;

import ui.UIConfig;
import ui.common.UserMouseAdapter;
import ui.common.button.ImgButton;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import ui.panel.allteams.TeamButton;
import utility.Constants;
import vo.TeamSeasonVO;
import bl.teamseasonbl.TeamSeasonAnalysis;
import blservice.TeamSeasonBLService;
import enums.ScreenDivision;
import enums.SortOrder;
import enums.TeamAllSortBasis;
import enums.TeamAvgSortBasis;

/**
 * 球队数据界面
 * @author cylong
 * @version 2015年3月18日 上午11:40:37
 */
/**
 * @author lsy
 * @version 2015年3月19日 下午10:59:37
 */
public class TeamDataPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -4296014620804951285L;
	
	private static final Color BUTTON_SELECTED_BG = new Color(15, 24, 44);
	private static final Color BUTTON_SELECTED_FORE = Color.white;
	/** 枚举数组 */
	private static final ScreenDivision[] DIVISION_ARRAY = ScreenDivision.values();
	/** 宽 高 */
	private static final int WIDTH_X = 60, HEIGHT = 24, WIDTH_THREE = 75;
	/** 纵坐标及间隔 */
	private static final int Y = 66, INTER = 61, Y5 = 171;
	/** “所有”的横坐标 */
	private static final int ALL_X = 156;
	/** 东南 中央 大西洋 东部 西部 太平洋 西北 西南  等按钮的X坐标*/
	private static final int SOUTH_EAST_X = 227, CENTER_X = SOUTH_EAST_X + INTER, 
			ATLANTIC_X = CENTER_X + INTER, EAST_X = 440, WEST_X = EAST_X + INTER,
			PACIFIC_X = WEST_X + WIDTH_X + EAST_X - ATLANTIC_X - WIDTH_THREE, 
			NORTH_WEST_X = PACIFIC_X + INTER + WIDTH_THREE - WIDTH_X,
			SOUTH_WEST = NORTH_WEST_X + INTER;
	/** 总计 平均  按钮的X坐标*/
	private static final int TOTAL_X = 756, AVG_X = 823;
	/** 所有button的横坐标 */
	private static final int[] SELECT_BUTTON_X = new int[]{ALL_X, SOUTH_EAST_X, CENTER_X, 
		ATLANTIC_X, EAST_X, WEST_X, PACIFIC_X, NORTH_WEST_X, SOUTH_WEST};
	/** 总计 平均横坐标 */
	private static final int[] TOTAL_AVG_BUTTONS_X = new int[]{TOTAL_X, AVG_X};
	/** button上的文字 */
	private static final String[] DIVISION_SELECT_TEXT = new String[]{"所有", "东南", "中央", 
		"大西洋", "东部", "西部", "太平洋", "西北", "西南"};
	private static final String[] TOTAL_AVG_SELECT_TEXT = new String[]{"总计", "平均"};
	private static final int DIVISON_COUNT = 9, TOTAL_AVG_COUNT = 2;
	private static final String IMG_PATH = UIConfig.IMG_PATH + "teamData/";
	private static final String SEARCH_BUTTON_OFF = IMG_PATH + "search.png";
	private static final String SEARCH_BUTTON_ON = IMG_PATH + "searchOn.png";
	private static final String SEARCH_BUTTON_CLICK = IMG_PATH + "searchClick.png";
	
	/** 队伍数据表格 */
	private BottomTable teamDataTable;
	/** 放表格的scrollpane */
	private BottomScrollPane scroll;
	/** 赛区筛选条件按钮 */
	private TeamDivisionSelectButton[] divisionSelectButtons;
	/** 总计和平均按钮 */
	private TeamTotalAvgSelectButton[] totalAvgSelectButtons;
	/** 查询按钮 */
	private ImgButton findButton;

	private TeamSeasonBLService teamSeason = new TeamSeasonAnalysis();
	
	private ArrayList<TeamSeasonVO> seasonArray;

	public TeamDataPanel(String url) {
		super(url);
		addButton();
		// addFindButton(); // 不需要查询按钮，以后需要的时候再添加上去
		TeamDivisionSelectButton.current = divisionSelectButtons[0];
		TeamTotalAvgSelectButton.current = totalAvgSelectButtons[0];
		setEffect(divisionSelectButtons[0]);
		setEffect(totalAvgSelectButtons[0]);
		addListener();
		// 初始化表格和球队总数据
		seasonArray = teamSeason.getTeamDataSortedByName();
		createTable(seasonArray); // 设置表格数据
	}

	/**
	 * 添加查询按钮
	 * @author lsy
	 * @version 2015年3月19日 下午11:22:32
	 */
	public void addFindButton() {
		findButton = new ImgButton(SEARCH_BUTTON_OFF, 856, 124, SEARCH_BUTTON_ON, 
				SEARCH_BUTTON_CLICK);
		this.add(findButton);
		findButton.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				seasonArray = teamSeason.getScreenedTeamData(TeamDivisionSelectButton.current.division);
				createTable(seasonArray);
			}
		});
	}

	/**
	 * 添加全部button
	 * @author lsy
	 * @version 2015年3月19日 下午11:19:15
	 */
	public void addButton() {
		divisionSelectButtons = new TeamDivisionSelectButton[DIVISON_COUNT];
		totalAvgSelectButtons = new TeamTotalAvgSelectButton[TOTAL_AVG_COUNT];
		for(int i = 0; i < DIVISON_COUNT; i++) {
			if (i == 3 || i == 6) {
				divisionSelectButtons[i] = new TeamDivisionSelectButton(SELECT_BUTTON_X[i], Y, WIDTH_THREE, HEIGHT, DIVISION_SELECT_TEXT[i]);
			} else {
				divisionSelectButtons[i] = new TeamDivisionSelectButton(SELECT_BUTTON_X[i], Y, WIDTH_X, HEIGHT, DIVISION_SELECT_TEXT[i]);
			}
			divisionSelectButtons[i].division = DIVISION_ARRAY[i];
		}
		for(int i = 0; i < TOTAL_AVG_COUNT; i++) {
			totalAvgSelectButtons[i] = new TeamTotalAvgSelectButton(TOTAL_AVG_BUTTONS_X[i], Y5, WIDTH_X, HEIGHT, TOTAL_AVG_SELECT_TEXT[i]);
		}
		for(int i = 0; i < DIVISON_COUNT; i++) {
			this.add(divisionSelectButtons[i]);
		}
		for(int i = 0; i < TOTAL_AVG_COUNT; i++) {
			this.add(totalAvgSelectButtons[i]);
		}
	}

	/**
	 * 设置按钮被点击后的效果
	 * @param button
	 * @author cylong
	 * @version 2015年3月24日 下午8:14:49
	 */
	public void setEffect(TextButton button) {
		button.setOpaque(true);
		button.setBackground(BUTTON_SELECTED_BG);
		button.setForeground(BUTTON_SELECTED_FORE);
	}

	public void addListener() {
		DivisionSelectListener divisionListener = new DivisionSelectListener();
		TotalAvgListener totalAvgListener = new TotalAvgListener();
		for(int i = 0; i < DIVISON_COUNT; i++) {
			divisionSelectButtons[i].addMouseListener(divisionListener);
		}
		for(int i = 0; i < TOTAL_AVG_COUNT; i++) {
			totalAvgSelectButtons[i].addMouseListener(totalAvgListener);
		}
	}

	class DivisionSelectListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == TeamDivisionSelectButton.current) {
				return;
			}
			TeamDivisionSelectButton.current.back();
			TeamDivisionSelectButton.current = (TeamDivisionSelectButton)e.getSource();
			seasonArray = teamSeason.getScreenedTeamData(TeamDivisionSelectButton.current.division);

			createTable(seasonArray); // 添加筛选出的数据
		}
	}

	class TotalAvgListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == TeamTotalAvgSelectButton.current) {
				return;
			}
			TeamTotalAvgSelectButton.current.back();
			TeamTotalAvgSelectButton.current = (TeamTotalAvgSelectButton)e.getSource();
			seasonArray = teamSeason.getScreenedTeamData(TeamDivisionSelectButton.current.division);
			if (TeamTotalAvgSelectButton.current == totalAvgSelectButtons[0]) {
				updateTotalTeamDataTable(seasonArray); // 添加总数据
			} else if (TeamTotalAvgSelectButton.current == totalAvgSelectButtons[1]) {
				updateAvgTeamDataTable(seasonArray); // 添加平均数据
			}
		}
	}

	/**
	 * 添加球队总数据
	 * @param teamArr 逻辑层返回的球队数据
	 * @param rowData 表格中显示的数据
	 * @author cylong
	 * @version 2015年3月24日 下午8:48:03
	 */
	private void updateTotalTeamDataTable(ArrayList<TeamSeasonVO> teamArr) {
		for(int i = 0; i < teamArr.size(); i++) {
			TeamSeasonVO teamSeason = teamArr.get(i);
			teamDataTable.setValueAt(Integer.toString(i + 1),i,0);
			teamDataTable.setValueAt(Constants.translateTeamAbbr(teamSeason.getTeamName()),i,1);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getWins()),i,2);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getLoses()),i,3);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getMatchCount()),i,4);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getWinning()),i,5);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFieldGoal()),i,6);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFieldAttempt()),i,7);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getFieldPercent()),i,8);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getThreePointGoal()),i,9);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getThreePointAttempt()),i,10);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getThreePointPercent()),i,11);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFreethrowGoal()),i,12);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFreethrowAttempt()),i,13);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getFreeThrowPercent()),i,14);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getOffensiveRebound()),i,15);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getDefensiveRebound()),i,16);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getTotalRebound()),i,17);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getOffensiveReboundEff()),i,18);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getDefensiveReboundEff()),i,19);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getOffensiveRound()),i,20);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getOffensiveEff()),i,21);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getDefensiveRound()),i,22);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getDefensiveEff()),i,23);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getSteal()),i,24);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getStealEff()),i,25);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getAssist()),i,26);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getAssistEff()),i,27);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getBlock()),i,28);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getTurnover()),i,29);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFoul()),i,30);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getScore()),i,31);
		}
	}
	
	public void addListener(final BottomTable table) {
		try {
			table.addMouseListener(new UserMouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						String abbr = seasonArray.get(rowI).getTeamName();
						MainController.toTeamSeasonPanel(TeamDataPanel.this,TeamDataPanel.this,
								new TeamButton(abbr),0);
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 添加球队平均数据
	 * @param teamArr 逻辑层返回的球队数据
	 * @author cylong
	 * @version 2015年3月24日 下午9:03:08
	 */
	private void updateAvgTeamDataTable(ArrayList<TeamSeasonVO> teamArr) {
		for(int i = 0; i < teamArr.size(); i++) {
			TeamSeasonVO teamSeason = teamArr.get(i);
			teamDataTable.setValueAt(Integer.toString(i + 1),i,0);
			teamDataTable.setValueAt(Constants.translateTeamAbbr(teamSeason.getTeamName()),i,1);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getWins()),i,2);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getLoses()),i,3);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getMatchCount()),i,4);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getWinning()),i,5);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getFieldGoalAvg()),i,6);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getFieldAttemptAvg()),i,7);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getFieldPercent()),i,8);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getThreePointGoalAvg()),i,9);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getThreePointAttemptAvg()),i,10);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getThreePointPercent()),i,11);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getFreethrowGoalAvg()),i,12);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getFreethrowAttemptAvg()),i,13);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getFreeThrowPercent()),i,14);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getOffensiveReboundAvg()),i,15);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getDefensiveReboundAvg()),i,16);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getTotalReboundAvg()),i,17);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getOffensiveReboundEff()),i,18);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getDefensiveReboundEff()),i,19);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getOffensiveRoundAvg()),i,20);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getOffensiveEff()),i,21);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getDefensiveRoundAvg()),i,22);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getDefensiveEff()),i,23);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getStealAvg()),i,24);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getStealEff()),i,25);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getAssistAvg()),i,26);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getAssistEff()),i,27);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getBlockAvg()),i,28);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getTurnoverAvg()),i,29);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getFoulAvg()),i,30);
			teamDataTable.setValueAt(UIConfig.FORMAT.format(teamSeason.getScoreAvg()),i,31);
		}
	}

	
	/** 0表示下一次点击降序，1升序 */
	private int clickedNum = 0;
	/** 上一次点击的列数 */
	private int lastClickColumn = 0;
	/**
	 * 根据球队数据创建表格
	 * @param teamArr
	 * @return 表格数据的二维数组
	 * @author cylong
	 * @version 2015年3月29日 下午3:59:35
	 */
	private void createTable(ArrayList<TeamSeasonVO> teamArr) {
		String[][] rowData = new String[teamArr.size()][Constants.TEAM_SEASON_HEADERS.length];
		teamDataTable = new BottomTable(rowData, Constants.TEAM_SEASON_HEADERS);
		teamDataTable.getColumnModel().getColumn(18).setPreferredWidth(80);
		//表头太长，显示不出来
		teamDataTable.getColumnModel().getColumn(19).setPreferredWidth(80);
		addScrollPane(teamDataTable);
		
		if (TeamTotalAvgSelectButton.current == totalAvgSelectButtons[0]) {
			updateTotalTeamDataTable(teamArr); // 添加总数据
		} else if (TeamTotalAvgSelectButton.current == totalAvgSelectButtons[1]) {
			updateAvgTeamDataTable(teamArr); // 添加平均数据
		}
		
		final JTableHeader header = teamDataTable.getTableHeader();
		// 给表头添加监听，用来排序
		header.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int index = header.columnAtPoint(e.getPoint());
				if (index < 1) { // 确定所点击区域在第1列之后，第一列不需要排序
					return;
				}
				if (index == lastClickColumn) {
					clickedNum = 1 - clickedNum;
				}else{
					clickedNum = 0;
					lastClickColumn = index;
				}
				SortOrder sort = null; // 升序降序
				if(clickedNum == 1) {
					sort = SortOrder.AS;
				} else {
					sort = SortOrder.DE;
				}
				
				if (TeamTotalAvgSelectButton.current == totalAvgSelectButtons[0]) {
					TeamAllSortBasis[] basis = TeamAllSortBasis.values();
					seasonArray = teamSeason.getResortedTeamAllData(basis[index - 1], sort);
					TeamDataPanel.this.updateTotalTeamDataTable(seasonArray); // 重排总数据
				} else if (TeamTotalAvgSelectButton.current == totalAvgSelectButtons[1]) {
					TeamAvgSortBasis[] basis = TeamAvgSortBasis.values();
					seasonArray = teamSeason.getResortedTeamAvgData(basis[index - 1], sort);
					TeamDataPanel.this.updateAvgTeamDataTable(seasonArray); // 重排平均数据
				}
			}
		});
		addListener(teamDataTable);
	}
	/**
	 * 将表格添加到ScrollPane上面
	 * @param table
	 * @author cylong
	 * @version 2015年3月26日 下午7:27:37
	 */
	private void addScrollPane(BottomTable table) {
		if (scroll != null){
			this.remove(scroll);
		}
		scroll = new BottomScrollPane(table);
		scroll.setLocation(57, 239); // 表格的位置
		this.add(scroll);
	}

}
