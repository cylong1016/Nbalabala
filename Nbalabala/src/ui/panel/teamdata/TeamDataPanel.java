package ui.panel.teamdata;

import java.awt.Color;
import java.awt.Graphics;
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
import ui.panel.playerData.Line_4_Button;
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

	/** 条件按钮 */
	SelectButton[] button;
	private Line_4_Button[] buttonLine4;
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
	private int[] buttonXLine2 = new int[]{allX, southEast, center, atlantic, east, west, pacific, northWest,
												southWest};
	/** 总计 平均横坐标 */
	private int[] buttonXLine4 = new int[]{total, average};
	/** button上的文字 */
	private String[] textLine2 = new String[]{"所有", "东南", "中央", "大西洋", "东部", "西部", "太平洋", "西北", "西南"};
	private String[] textLine4 = new String[]{"总计", "平均"};
	/** 枚举数组 */
	ScreenDivision[] dArray = ScreenDivision.values();
	/** 查询按钮 */
	private ImgButton findButton;
	int sum = 9, sum2 = 2;
	TeamSeasonBLService teamSeason = new TeamSeasonAnalysis();
	private String imgURL = UIConfig.IMG_PATH + "teamData/";

	public TeamDataPanel(MainController controller, String url) {
		super(controller, url);
		setButton();
		addButton();
		addFindButton();
		iniSet();
		setEffect(buttonArr);
		addListener();
		addTeamDataTable();
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
				// ArrayList<TeamSeasonRecord> seasonArray = teamSeason.getScreenedTeamData(SelectButton.current.division);
				// TODO changeTable
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
		Line_4_Button.current = (Line_4_Button)buttonArr[1];
	}

	/**
	 * 设置button
	 * @author lsy
	 * @version 2015年3月19日 下午11:19:05
	 */
	public void setButton() {
		button = new SelectButton[sum];
		buttonLine4 = new Line_4_Button[sum2];
		for(int i = 0; i < sum; i++) {
			if (i == 3 || i == 6) {
				button[i] = new SelectButton(buttonXLine2[i], y, widthThree, height, textLine2[i]);
			} else {
				button[i] = new SelectButton(buttonXLine2[i], y, width, height, textLine2[i]);
			}
			button[i].division = dArray[i];
		}
		for(int i = 0; i < sum2; i++) {
			buttonLine4[i] = new Line_4_Button(buttonXLine4[i], y5, width, height, textLine4[i]);
		}
		buttonArr = new TextButton[]{button[0], buttonLine4[0]};
	}

	/**
	 * 添加button
	 * @author lsy
	 * @version 2015年3月19日 下午11:19:15
	 */
	public void addButton() {
		for(int i = 0; i < sum; i++) {
			this.add(button[i]);
		}
		for(int i = 0; i < sum2; i++) {
			this.add(buttonLine4[i]);
		}
	}

	public void setEffect(TextButton[] button) {
		for(int i = 0; i < button.length; i++) {
			button[i].setOpaque(true);
			button[i].setBackground(new Color(15, 24, 44));
			button[i].setForeground(Color.white);
		}
	}

	public void addListener() {
		MouListener1 mou1 = new MouListener1();
		MouListener4 mou4 = new MouListener4();
		for(int i = 0; i < sum; i++) {
			button[i].addMouseListener(mou1);
		}
		for(int i = 0; i < sum2; i++) {
			buttonLine4[i].addMouseListener(mou4);
		}
	}

	class MouListener1 extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == SelectButton.current) {
				return;
			}
			SelectButton.current.back();
			SelectButton.current = (SelectButton)e.getSource();
		}
	}

	class MouListener4 extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == Line_4_Button.current) {
				return;
			}
			Line_4_Button.current.back();
			Line_4_Button.current = (Line_4_Button)e.getSource();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	/**
	 * 添加当前登录的用户的信息table
	 * @author cylong
	 * @version 2015年3月20日 下午6:37:48
	 */
	private void addTeamDataTable() {
		String[] columnNames = {"球队名称", "胜场数", "负场数", "总场数", "投篮命中", "投篮出手", "投篮命中率", "三分命中", "三分出手", "三分命中率", "罚球命中",
									"罚球出手", "罚球命中率", "进攻篮板数", "防守篮板数", "篮板总数", "篮板效率", "进攻回合", "进攻效率", "助攻次数", "助攻效率",
									"抢断", "盖帽", "失误", "犯规", "胜率", "防守", "得分"};
		ArrayList<TeamSeasonRecord> teamArr = teamSeason.getTeamDataSortedByName();
		String[][] rowData = new String[teamArr.size()][columnNames.length];
		teamDataTable = new BottomTable(rowData, columnNames);
		for(int i = 0; i < teamArr.size(); i++) {
			TeamSeasonRecord teamSeason = teamArr.get(i);
			rowData[i][0] = teamSeason.getTeamName();
			rowData[i][1] = Integer.toString(teamSeason.getWins());
			rowData[i][2] = teamSeason.getTeamName();
			rowData[i][3] = teamSeason.getTeamName();
			rowData[i][4] = teamSeason.getTeamName();
			rowData[i][5] = teamSeason.getTeamName();
			rowData[i][6] = teamSeason.getTeamName();
			rowData[i][7] = teamSeason.getTeamName();
			rowData[i][8] = teamSeason.getTeamName();
			rowData[i][9] = teamSeason.getTeamName();
			rowData[i][10] = teamSeason.getTeamName();
			rowData[i][11] = teamSeason.getTeamName();
			rowData[i][12] = teamSeason.getTeamName();
			rowData[i][13] = teamSeason.getTeamName();
			rowData[i][14] = teamSeason.getTeamName();
			rowData[i][15] = teamSeason.getTeamName();
			rowData[i][16] = teamSeason.getTeamName();
			rowData[i][17] = teamSeason.getTeamName();
			rowData[i][18] = teamSeason.getTeamName();
			rowData[i][19] = teamSeason.getTeamName();
			rowData[i][20] = teamSeason.getTeamName();
			rowData[i][21] = teamSeason.getTeamName();
			rowData[i][22] = teamSeason.getTeamName();
			rowData[i][23] = teamSeason.getTeamName();
			rowData[i][24] = teamSeason.getTeamName();
			rowData[i][25] = teamSeason.getTeamName();
			rowData[i][26] = teamSeason.getTeamName();
			rowData[i][27] = teamSeason.getTeamName();
		}
		
		BottomScrollPane scroll = new BottomScrollPane(teamDataTable);
		scroll.setLocation(57, 239);
		this.add(scroll);

	}

}
