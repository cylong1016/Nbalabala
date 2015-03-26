package ui.panel.teamdata;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import bl.teamseasonbl.TeamSeasonAnalysis;
import blservice.TeamSeasonBLService;
import data.seasondata.TeamSeasonRecord;
import enums.ScreenDivision;

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

	/** 队伍数据表格 */
	private BottomTable teamDataTable;
	/** 放表格的滚动条 */
	private BottomScrollPane scroll;

	/** 条件按钮 */
	private SelectButton[] button;
	/** 总计和平均按钮 */
	private Line_2_Button[] buttonLine2;
	
	private TextButton[] buttonArr;
	/** 宽 高 */
	private int width = 60, height = 24, widthThree = 75;
	/** 横纵坐标及间隔 */
	private int y = 66, x = 227, inter = 61, y5 = 171;
	/** “所有”的横坐标 */
	private int allX = 156;
	/** 东南 中央 大西洋 东部 西部 太平洋 西北 西南 */
	private int southEast = x, center = southEast + inter, atlantic = center + inter, east = 440, west = east + inter,
			pacific = west + width + east - atlantic - widthThree, northWest = pacific + inter + widthThree - width,
			southWest = northWest + inter;
	/** 总计 平均 */
	private int total = 756, average = 823;
	/** 所有button的横坐标 */
	private int[] selectButton = new int[]{allX, southEast, center, atlantic, east, west, pacific, northWest,
												southWest};
	/** 总计 平均横坐标 */
	private int[] buttonXLine2 = new int[]{total, average};
	/** button上的文字 */
	private String[] textSelect = new String[]{"所有", "东南", "中央", "大西洋", "东部", "西部", "太平洋", "西北", "西南"};
	private String[] textLine2 = new String[]{"总计", "平均"};
	/** 枚举数组 */
	ScreenDivision[] dArray = ScreenDivision.values();
	/** 查询按钮 */
	private ImgButton findButton;
	int sumSelectButton = 9, sumButton2 = 2;
	private TeamSeasonBLService teamSeason = new TeamSeasonAnalysis();
	private String imgURL = UIConfig.IMG_PATH + "teamData/";

	public TeamDataPanel(MainController controller, String url) {
		super(controller, url);
		addButton();
		// addFindButton(); // 不需要查询按钮，以后需要的时候再添加上去
		iniSet();
		setEffect(buttonArr);
		addListener();
		// 初始化表格和球队总数据
		ArrayList<TeamSeasonRecord> teamArr = teamSeason.getTeamDataSortedByName();
		addTotalTeamDataTable(teamArr); // 设置表格数据
	}

	/**
	 * 添加查询按钮
	 * @author lsy
	 * @version 2015年3月19日 下午11:22:32
	 */
	public void addFindButton() {
		findButton = new ImgButton(imgURL + "search.png", 856, 124, imgURL + "searchOn.png", imgURL + "searchClick.png");
		this.add(findButton);
		findButton.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				ArrayList<TeamSeasonRecord> seasonArray = teamSeason.getScreenedTeamData(SelectButton.current.division);
				// changeTable
				TeamDataPanel.this.remove(scroll); // 删除以前的Table
				TeamDataPanel.this.addTotalTeamDataTable(seasonArray);
			}
		});
	}

	/**
	 * 初始化按钮为选中状态
	 * @author lsy
	 * @version 2015年3月19日 下午11:44:50
	 */
	public void iniSet() {
		SelectButton.current = (SelectButton)buttonArr[0];
		Line_2_Button.current = (Line_2_Button)buttonArr[1];
	}

	/**
	 * 添加全部button
	 * @author lsy
	 * @version 2015年3月19日 下午11:19:15
	 */
	public void addButton() {
		button = new SelectButton[sumSelectButton];
		buttonLine2 = new Line_2_Button[sumButton2];
		for(int i = 0; i < sumSelectButton; i++) {
			if (i == 3 || i == 6) {
				button[i] = new SelectButton(selectButton[i], y, widthThree, height, textSelect[i]);
			} else {
				button[i] = new SelectButton(selectButton[i], y, width, height, textSelect[i]);
			}
			button[i].division = dArray[i];
		}
		for(int i = 0; i < sumButton2; i++) {
			buttonLine2[i] = new Line_2_Button(buttonXLine2[i], y5, width, height, textLine2[i]);
		}
		buttonArr = new TextButton[]{button[0], buttonLine2[0]};
		for(int i = 0; i < sumSelectButton; i++) {
			this.add(button[i]);
		}
		for(int i = 0; i < sumButton2; i++) {
			this.add(buttonLine2[i]);
		}
	}

	/**
	 * 设置按钮被点击后的效果
	 * @param button
	 * @author cylong
	 * @version 2015年3月24日  下午8:14:49
	 */
	public void setEffect(TextButton[] button) {
		for(int i = 0; i < button.length; i++) {
			button[i].setOpaque(true);
			button[i].setBackground(new Color(15, 24, 44));
			button[i].setForeground(Color.white);
		}
	}

	public void addListener() {
		MouListener1 mou1 = new MouListener1();
		MouListener2 mou2 = new MouListener2();
		for(int i = 0; i < sumSelectButton; i++) {
			button[i].addMouseListener(mou1);
		}
		for(int i = 0; i < sumButton2; i++) {
			buttonLine2[i].addMouseListener(mou2);
		}
	}

	class MouListener1 extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == SelectButton.current) {
				return;
			}
			SelectButton.current.back();
			SelectButton.current = (SelectButton)e.getSource();
			ArrayList<TeamSeasonRecord> seasonArray = teamSeason.getScreenedTeamData(SelectButton.current.division);
			// changeTable
			TeamDataPanel.this.remove(scroll); // 删除以前的Table
			if(Line_2_Button.current == buttonLine2[0]) {
				TeamDataPanel.this.addTotalTeamDataTable(seasonArray); // 添加总数据
			} else if(Line_2_Button.current == buttonLine2[1]) {
				TeamDataPanel.this.addAvgTeamDataTable(seasonArray); // 添加平均数据
			}
		}
	}

	class MouListener2 extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == Line_2_Button.current) {
				return;
			}
			Line_2_Button.current.back();
			Line_2_Button.current = (Line_2_Button)e.getSource();
			ArrayList<TeamSeasonRecord> seasonArray = teamSeason.getScreenedTeamData(SelectButton.current.division);
			TeamDataPanel.this.remove(scroll); // 删除以前的Table
			if(e.getSource() == buttonLine2[0]) {
				TeamDataPanel.this.addTotalTeamDataTable(seasonArray); // 添加总数据
			} else if(e.getSource() == buttonLine2[1]) {
				TeamDataPanel.this.addAvgTeamDataTable(seasonArray); // 添加平均数据
			}
		}
	}
	
	/**
	 * 添加球队总数据
	 * @param teamArr 逻辑层返回的球队数据
	 * @param rowData 表格中显示的数据
	 * @author cylong
	 * @version 2015年3月24日  下午8:48:03
	 */
	private void addTotalTeamDataTable(ArrayList<TeamSeasonRecord> teamArr) {
		String[] columnNames = getColumnNames();
		String[][] rowData = new String[teamArr.size()][columnNames.length];
		teamDataTable = new BottomTable(rowData, columnNames);
		for(int i = 0; i < teamArr.size(); i++) {
			TeamSeasonRecord teamSeason = teamArr.get(i);
			rowData[i][0] = Integer.toString(i + 1);
			rowData[i][1] = teamSeason.getTeamName();
			rowData[i][2] = Integer.toString(teamSeason.getWins());
			rowData[i][3] = Integer.toString(teamSeason.getLoses());
			rowData[i][4] = Integer.toString(teamSeason.getMatchCount());
			rowData[i][5] = UIConfig.format.format(teamSeason.getWinning());
			rowData[i][6] = Integer.toString(teamSeason.getFieldGoal());
			rowData[i][7] = Integer.toString(teamSeason.getFieldAttempt());
			rowData[i][8] = UIConfig.format.format(teamSeason.getFieldPercent());
			rowData[i][9] = Integer.toString(teamSeason.getThreePointGoal());
			rowData[i][10] = Integer.toString(teamSeason.getThreePointAttempt());
			rowData[i][11] = UIConfig.format.format(teamSeason.getThreePointPercent());
			rowData[i][12] = Integer.toString(teamSeason.getFreethrowGoal());
			rowData[i][13] = Integer.toString(teamSeason.getFreethrowAttempt());
			rowData[i][14] = UIConfig.format.format(teamSeason.getFreeThrowPercent());
			rowData[i][15] = Integer.toString(teamSeason.getOffensiveRebound());
			rowData[i][16] = Integer.toString(teamSeason.getDefensiveRebound());
			rowData[i][17] = Integer.toString(teamSeason.getTotalRebound());
			rowData[i][18] = UIConfig.format.format(teamSeason.getOffensiveReboundEff());
			rowData[i][19] = UIConfig.format.format(teamSeason.getDefensiveReboundEff());
			rowData[i][20] = UIConfig.format.format(teamSeason.getOffensiveRound());
			rowData[i][21] = UIConfig.format.format(teamSeason.getOffensiveEff());
			rowData[i][22] = UIConfig.format.format(teamSeason.getDefensiveRound());
			rowData[i][23] = UIConfig.format.format(teamSeason.getDefensiveEff());
			rowData[i][24] = Integer.toString(teamSeason.getSteal());
			rowData[i][25] = UIConfig.format.format(teamSeason.getStealEff());
			rowData[i][26] = Integer.toString(teamSeason.getAssist());
			rowData[i][27] = UIConfig.format.format(teamSeason.getAssistEff());
			rowData[i][28] = Integer.toString(teamSeason.getBlock());
			rowData[i][29] = Integer.toString(teamSeason.getTurnover());
			rowData[i][30] = Integer.toString(teamSeason.getFoul());
			rowData[i][31] = Integer.toString(teamSeason.getScore());
		}
		addScrollPane(teamDataTable);
	}
	
	/**
	 * 添加球队平均数据
	 * @param teamArr 逻辑层返回的球队数据
	 * @author cylong
	 * @version 2015年3月24日  下午9:03:08
	 */
	private void addAvgTeamDataTable(ArrayList<TeamSeasonRecord> teamArr) {
		String[] columnNames = getColumnNames();
		String[][] rowData = new String[teamArr.size()][columnNames.length];
		teamDataTable = new BottomTable(rowData, columnNames);
		for(int i = 0; i < teamArr.size(); i++) {
			TeamSeasonRecord teamSeason = teamArr.get(i);
			rowData[i][0] = Integer.toString(i + 1);
			rowData[i][1] = teamSeason.getTeamName();
			rowData[i][2] = Integer.toString(teamSeason.getWins());
			rowData[i][3] = Integer.toString(teamSeason.getLoses());
			rowData[i][4] = Integer.toString(teamSeason.getMatchCount());
			rowData[i][5] = UIConfig.format.format(teamSeason.getWinning());
			rowData[i][6] = UIConfig.format.format(teamSeason.getFieldGoalAvg());
			rowData[i][7] = UIConfig.format.format(teamSeason.getFieldAttemptAvg());
			rowData[i][8] = UIConfig.format.format(teamSeason.getFieldPercent());
			rowData[i][9] = UIConfig.format.format(teamSeason.getThreePointGoalAvg());
			rowData[i][10] = UIConfig.format.format(teamSeason.getThreePointAttemptAvg());
			rowData[i][11] = UIConfig.format.format(teamSeason.getThreePointPercent());
			rowData[i][12] = UIConfig.format.format(teamSeason.getFreethrowGoalAvg());
			rowData[i][13] = UIConfig.format.format(teamSeason.getFreethrowAttemptAvg());
			rowData[i][14] = UIConfig.format.format(teamSeason.getFreeThrowPercent());
			rowData[i][15] = UIConfig.format.format(teamSeason.getOffensiveReboundAvg());
			rowData[i][16] = UIConfig.format.format(teamSeason.getDefensiveReboundAvg());
			rowData[i][17] = UIConfig.format.format(teamSeason.getTotalReboundAvg());
			rowData[i][18] = UIConfig.format.format(teamSeason.getOffensiveReboundEff());
			rowData[i][19] = UIConfig.format.format(teamSeason.getDefensiveReboundEff());
			rowData[i][20] = UIConfig.format.format(teamSeason.getOffensiveRoundAvg());
			rowData[i][21] = UIConfig.format.format(teamSeason.getOffensiveEff());
			rowData[i][22] = UIConfig.format.format(teamSeason.getDefensiveRoundAvg());
			rowData[i][23] = UIConfig.format.format(teamSeason.getDefensiveEff());
			rowData[i][24] = UIConfig.format.format(teamSeason.getStealAvg());
			rowData[i][25] = UIConfig.format.format(teamSeason.getStealEff());
			rowData[i][26] = UIConfig.format.format(teamSeason.getAssistAvg());
			rowData[i][27] = UIConfig.format.format(teamSeason.getAssistEff());
			rowData[i][28] = UIConfig.format.format(teamSeason.getBlockAvg());
			rowData[i][29] = UIConfig.format.format(teamSeason.getTurnoverAvg());
			rowData[i][30] = UIConfig.format.format(teamSeason.getFoulAvg());
			rowData[i][31] = UIConfig.format.format(teamSeason.getScoreAvg());
		}
		addScrollPane(teamDataTable);
	}
	
	private String[] getColumnNames() {
		String[] columnNames = {"序号", "球队名称", "胜场数", "负场数", "总场数", "胜率", "投篮命中", "投篮出手", "投篮命中率", "三分命中", "三分出手", "三分命中率", "罚球命中",
								"罚球出手", "罚球命中率", "进攻篮板数", "防守篮板数", "篮板总数", "进攻篮板效率", "防守篮板效率", "进攻回合", "进攻效率", "防守回合", "防守效率",
								"抢断", "抢断效率", "助攻", "助攻率", "盖帽", "失误", "犯规", "得分"};
		return columnNames;
	}
	
	/**
	 * 将表格添加到ScrollPane上面
	 * @param table
	 * @author cylong
	 * @version 2015年3月26日  下午7:27:37
	 */
	private void addScrollPane(BottomTable table) {
		scroll = new BottomScrollPane(table);
		scroll.setLocation(57, 239); // 表格的位置
		this.add(scroll);
	}

}
