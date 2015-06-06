package ui.panel.teamdata;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.TeamSeasonPO;
import ui.Images;
import ui.UIConfig;
import ui.common.SeasonInputPanel;
import ui.common.button.TabButton;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import bl.teamseasonbl.TeamSeasonAnalysis;
import blservice.TeamSeasonBLService;
import enums.AllTeamSeasonTableCategory;
import enums.ScreenDivision;
import enums.TotalOrAvg;

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

	private static final Color BUTTON_SELECTED_BG = UIConfig.BUTTON_COLOR;
	private static final Color BUTTON_SELECTED_FORE = Color.white;
	/** 枚举数组 */
	private static final ScreenDivision[] DIVISION_ARRAY = ScreenDivision.values();
	/** 宽 高 */
	private static final int WIDTH_X = 60, HEIGHT = 24, WIDTH_THREE = 75;

	private static final int ALL_X = 807, ALL_Y = 8;

	private static final int X1 = 670, X2 = 733, X3 = 798, X4 = 855;
	
	private static final int Y1 = 55,Y2 = 94,Y3 = 133;
	/** 总计 平均 */
	private static final int TOTAL_X = 796, AVG_X = 870,TOTAL_Y = 243,ROW_HEIGHT = 30;
	/** 所有button的横坐标 */
	private static final int[] SELECT_BUTTON_X = new int[] { ALL_X,X1,X1,X1,X2,X3,X4,X4,X4};
	
	private static final int[] SELECT_BUTTON_Y = new int[] {ALL_Y,Y1,Y2,Y3,Y2,Y2,Y1,Y2,Y3};
	/** 总计 平均横坐标 */
	private static final int[] TOTAL_AVG_BUTTONS_X = new int[] { TOTAL_X, AVG_X };
	/** button上的文字 */
	private static final String[] DIVISION_SELECT_TEXT = new String[] { "所有", "东南", "中央", "大西洋", "东部", "西部",
			"太平洋", "西北", "西南" };
	private static final String[] TOTAL_AVG_SELECT_TEXT = new String[] { "总计", "平均" };
	private static final int DIVISON_COUNT = 9, TOTAL_AVG_COUNT = 2;
	private TabButton tab[];

	/** 放表格的scrollpane */
	private BottomScrollPane scroll;
	/** 赛区筛选条件按钮 */
	private TeamDivisionSelectButton[] divisionSelectButtons;
	/** 总计和平均按钮 */
	private TeamTotalAvgSelectButton[] totalAvgSelectButtons;

	private TeamSeasonBLService teamSeason = new TeamSeasonAnalysis();

	private ArrayList<TeamSeasonPO> seasonArray;
	
	private AllTeamSeasonTable table;
	private TotalOrAvg totalOrAvg;
	private AllTeamSeasonTableCategory current;

	/** 赛季选择器 */
	private SeasonInputPanel seasonInput;

	public TeamDataPanel(String url) {
		super(url);
		addButton();
		addTab();
		TeamDivisionSelectButton.current = divisionSelectButtons[0];
		TeamTotalAvgSelectButton.current = totalAvgSelectButtons[0];
		setEffect(divisionSelectButtons[0]);
		setEffect(totalAvgSelectButtons[0]);
		addListener();
		current = AllTeamSeasonTableCategory.BASIC;
		totalOrAvg = TotalOrAvg.TOTAL;
		seasonInput = new SeasonInputPanel(this);
		seasonInput.setBounds(56, TOTAL_Y,205,ROW_HEIGHT);
		this.add(seasonInput);
		// 初始化表格和球队总数据
		seasonArray = teamSeason.getTeamDataSortedByName(seasonInput.getSeason());
		table = new AllTeamSeasonTable(teamSeason,seasonArray,AllTeamSeasonTableCategory.BASIC,TotalOrAvg.TOTAL);
		addRowListener();
		addScrollPane(table);
		
	}
	
	private void addTab() {
		tab = new TabButton[3];
		for(int i = 0 ;i < 3; i++) {
			tab[i] = new TabButton(Constants.TEAM_DATA_SORT[i],Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
			tab[i].setLocation(24 + i * 316, 198);
			this.add(tab[i]);
			tab[0].setOn();
		}
		tab[0].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[0].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 table.changeCategory(AllTeamSeasonTableCategory.BASIC);
				 scroll.setHead();
			 }
		});
		tab[1].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[1].setOn();
				 tab[0].setOff();
				 tab[2].setOff();
				 table.changeCategory(AllTeamSeasonTableCategory.OFFENSIVE);
				 scroll.setHead();
			 }
		});
		tab[2].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[2].setOn();
				 tab[1].setOff();
				 tab[0].setOff();
				 table.changeCategory(AllTeamSeasonTableCategory.DEFENSIVE);
				 scroll.setHead();
			 }
		});
	}

	/**
	 * 添加全部button
	 * 
	 * @author lsy
	 * @version 2015年3月19日 下午11:19:15
	 */
	public void addButton() {
		divisionSelectButtons = new TeamDivisionSelectButton[DIVISON_COUNT];
		totalAvgSelectButtons = new TeamTotalAvgSelectButton[TOTAL_AVG_COUNT];
		for (int i = 0; i < DIVISON_COUNT; i++) {
			if (i == 3 || i == 6) {
				divisionSelectButtons[i] = new TeamDivisionSelectButton(SELECT_BUTTON_X[i], SELECT_BUTTON_Y[i], WIDTH_THREE,
						HEIGHT, DIVISION_SELECT_TEXT[i]);
			} else {
				divisionSelectButtons[i] = new TeamDivisionSelectButton(SELECT_BUTTON_X[i], SELECT_BUTTON_Y[i], WIDTH_X, HEIGHT,
						DIVISION_SELECT_TEXT[i]);
			}
			divisionSelectButtons[i].division = DIVISION_ARRAY[i];
		}
		for (int i = 0; i < TOTAL_AVG_COUNT; i++) {
			totalAvgSelectButtons[i] = new TeamTotalAvgSelectButton(TOTAL_AVG_BUTTONS_X[i], TOTAL_Y, 72,ROW_HEIGHT,
					TOTAL_AVG_SELECT_TEXT[i]);
		}
		for (int i = 0; i < DIVISON_COUNT; i++) {
			this.add(divisionSelectButtons[i]);
		}
		for (int i = 0; i < TOTAL_AVG_COUNT; i++) {
			this.add(totalAvgSelectButtons[i]);
		}
	}

	/**
	 * 设置按钮被点击后的效果
	 * 
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
		for (int i = 0; i < DIVISON_COUNT; i++) {
			divisionSelectButtons[i].addMouseListener(divisionListener);
		}
		for (int i = 0; i < TOTAL_AVG_COUNT; i++) {
			totalAvgSelectButtons[i].addMouseListener(totalAvgListener);
		}
	}

	class DivisionSelectListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == TeamDivisionSelectButton.current) {
				return;
			}
			TeamDivisionSelectButton.current.back();
			TeamDivisionSelectButton.current = (TeamDivisionSelectButton) e.getSource();
			seasonArray = teamSeason.getScreenedTeamData(TeamDivisionSelectButton.current.division,
					seasonInput.getSeason());
			table = new AllTeamSeasonTable(teamSeason,seasonArray,current,totalOrAvg);
			addScrollPane(table);
		}
	}

	class TotalAvgListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == TeamTotalAvgSelectButton.current) {
				return;
			}
			TeamTotalAvgSelectButton.current.back();
			TeamTotalAvgSelectButton.current = (TeamTotalAvgSelectButton) e.getSource();
			if (TeamTotalAvgSelectButton.current == totalAvgSelectButtons[0]) {
				totalOrAvg = TotalOrAvg.TOTAL;
				table.changeTotalOrAvg(totalOrAvg);
			} else if (TeamTotalAvgSelectButton.current == totalAvgSelectButtons[1]) {
				totalOrAvg = TotalOrAvg.AVG;
				table.changeTotalOrAvg(totalOrAvg);
			}
		}
	}

	public void refresh(){
//		System.out.println(seasonInput.getSeason());
		seasonArray = teamSeason.getScreenedTeamData(TeamDivisionSelectButton.current.division, seasonInput.getSeason());
		table = new AllTeamSeasonTable(teamSeason,seasonArray,AllTeamSeasonTableCategory.BASIC,TotalOrAvg.TOTAL);
		addScrollPane(table);
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
	
	private void addRowListener() {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() < 2) return;
				int rowI  = table.rowAtPoint(e.getPoint());
				if (rowI >= 0) {
					MainController.toTeamBottomPanel(TeamDataPanel.this, seasonArray.get(rowI).abbr);
				}
			}
		});
	}

}
