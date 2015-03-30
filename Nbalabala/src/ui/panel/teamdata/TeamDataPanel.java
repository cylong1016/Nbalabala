package ui.panel.teamdata;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.JTableHeader;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import bl.teamseasonbl.TeamSeasonAnalysis;
import blservice.TeamSeasonBLService;
import data.seasondata.TeamSeasonRecord;
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
		createTable(teamArr); // 设置表格数据
	}

	/**
	 * 添加查询按钮
	 * @author lsy
	 * @version 2015年3月19日 下午11:22:32
	 */
	public void addFindButton() {
		findButton = new ImgButton(imgURL + "search.png", 856, 124, imgURL + "searchOn.png", imgURL
																								+ "searchClick.png");
		this.add(findButton);
		findButton.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				ArrayList<TeamSeasonRecord> seasonArray = teamSeason.getScreenedTeamData(SelectButton.current.division);
				createTable(seasonArray);
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
	 * @version 2015年3月24日 下午8:14:49
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
			ArrayList<TeamSeasonRecord> teamArr = teamSeason.getScreenedTeamData(SelectButton.current.division);
			if (Line_2_Button.current == buttonLine2[0]) {
				updateTotalTeamDataTable(teamArr); // 添加总数据
			} else if (Line_2_Button.current == buttonLine2[1]) {
				updateAvgTeamDataTable(teamArr); // 添加平均数据
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
			ArrayList<TeamSeasonRecord> teamArr = teamSeason.getScreenedTeamData(SelectButton.current.division);
			if (Line_2_Button.current == buttonLine2[0]) {
				updateTotalTeamDataTable(teamArr); // 添加总数据
			} else if (Line_2_Button.current == buttonLine2[1]) {
				updateAvgTeamDataTable(teamArr); // 添加平均数据
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
	private void updateTotalTeamDataTable(ArrayList<TeamSeasonRecord> teamArr) {
		for(int i = 0; i < teamArr.size(); i++) {
			TeamSeasonRecord teamSeason = teamArr.get(i);
			teamDataTable.setValueAt(Integer.toString(i + 1),i,0);
			teamDataTable.setValueAt(Constants.translateTeamAbbr(teamSeason.getTeamName()),i,1);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getWins()),i,2);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getLoses()),i,3);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getMatchCount()),i,4);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getWinning()),i,5);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFieldGoal()),i,6);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFieldAttempt()),i,7);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getFieldPercent()),i,8);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getThreePointGoal()),i,9);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getThreePointAttempt()),i,10);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getThreePointPercent()),i,11);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFreethrowGoal()),i,12);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFreethrowAttempt()),i,13);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getFreeThrowPercent()),i,14);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getOffensiveRebound()),i,15);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getDefensiveRebound()),i,16);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getTotalRebound()),i,17);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getOffensiveReboundEff()),i,18);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getDefensiveReboundEff()),i,19);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getOffensiveRound()),i,20);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getOffensiveEff()),i,21);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getDefensiveRound()),i,22);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getDefensiveEff()),i,23);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getSteal()),i,24);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getStealEff()),i,25);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getAssist()),i,26);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getAssistEff()),i,27);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getBlock()),i,28);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getTurnover()),i,29);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getFoul()),i,30);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getScore()),i,31);
		}
	}

	/**
	 * 添加球队平均数据
	 * @param teamArr 逻辑层返回的球队数据
	 * @author cylong
	 * @version 2015年3月24日 下午9:03:08
	 */
	private void updateAvgTeamDataTable(ArrayList<TeamSeasonRecord> teamArr) {
		for(int i = 0; i < teamArr.size(); i++) {
			TeamSeasonRecord teamSeason = teamArr.get(i);
			teamDataTable.setValueAt(Integer.toString(i + 1),i,0);
			teamDataTable.setValueAt(Constants.translateTeamAbbr(teamSeason.getTeamName()),i,1);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getWins()),i,2);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getLoses()),i,3);
			teamDataTable.setValueAt(Integer.toString(teamSeason.getMatchCount()),i,4);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getWinning()),i,5);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getFieldGoalAvg()),i,6);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getFieldAttemptAvg()),i,7);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getFieldPercent()),i,8);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getThreePointGoalAvg()),i,9);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getThreePointAttemptAvg()),i,10);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getThreePointPercent()),i,11);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getFreethrowGoalAvg()),i,12);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getFreethrowAttemptAvg()),i,13);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getFreeThrowPercent()),i,14);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getOffensiveReboundAvg()),i,15);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getDefensiveReboundAvg()),i,16);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getTotalReboundAvg()),i,17);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getOffensiveReboundEff()),i,18);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getDefensiveReboundEff()),i,19);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getOffensiveRoundAvg()),i,20);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getOffensiveEff()),i,21);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getDefensiveRoundAvg()),i,22);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getDefensiveEff()),i,23);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getStealAvg()),i,24);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getStealEff()),i,25);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getAssistAvg()),i,26);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getAssistEff()),i,27);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getBlockAvg()),i,28);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getTurnoverAvg()),i,29);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getFoulAvg()),i,30);
			teamDataTable.setValueAt(UIConfig.format.format(teamSeason.getScoreAvg()),i,31);
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
	private void createTable(ArrayList<TeamSeasonRecord> teamArr) {
		String[][] rowData = new String[teamArr.size()][Constants.TEAM_SEASON_HEADERS.length];
		teamDataTable = new BottomTable(rowData, Constants.TEAM_SEASON_HEADERS);
		teamDataTable.getColumnModel().getColumn(18).setPreferredWidth(80);
		//表头太长，显示不出来
		teamDataTable.getColumnModel().getColumn(19).setPreferredWidth(80);
		addScrollPane(teamDataTable);
		
		if (Line_2_Button.current == buttonLine2[0]) {
			updateTotalTeamDataTable(teamArr); // 添加总数据
		} else if (Line_2_Button.current == buttonLine2[1]) {
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
				
				if (Line_2_Button.current == buttonLine2[0]) {
					TeamAllSortBasis[] basis = TeamAllSortBasis.values();
					ArrayList<TeamSeasonRecord> seasonArray = teamSeason.getResortedTeamAllData(basis[index - 1], sort);
					TeamDataPanel.this.updateTotalTeamDataTable(seasonArray); // 重排总数据
				} else if (Line_2_Button.current == buttonLine2[1]) {
					TeamAvgSortBasis[] basis = TeamAvgSortBasis.values();
					ArrayList<TeamSeasonRecord> seasonArray = teamSeason.getResortedTeamAvgData(basis[index - 1], sort);
					TeamDataPanel.this.updateAvgTeamDataTable(seasonArray); // 重排平均数据
				}
			}
		});
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
