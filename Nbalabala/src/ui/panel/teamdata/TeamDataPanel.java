package ui.panel.teamdata;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import ui.panel.playerData.Line_4_Button;
import bl.teamseasonbl.TeamSeasonAnalysis;
import blservice.TeamSeasonBLService;
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
	private JTable teamDataTable;

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
		//		ArrayList<TeamSeasonRecord> teamArr = teamSeason.getTeamDataSortedByName();
		//TODO setTable
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
				//				ArrayList<TeamSeasonRecord> seasonArray = teamSeason.getScreenedTeamData(SelectButton.current.division);
				//TODO changeTable
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
	 * @version 2015年3月20日  下午6:37:48
	 */
	private void addTeamDataTable() {
		String[] columnNames = {"名称", "胜", "负", "总", "命中", "球队名称6", "球队名称7", "球队名称8", "球队名称9",
									"球队名称10", "球队名称11", "球队名称22", "球队名称33", "球队名称44", "球队名称55", "球队名称66", "球队名称", "球队名称"};
		String[][] rowData = new String[20][columnNames.length];
		rowData[0][0] = "2333";
		
//		teamDataTable = new JTable(rowData, columnNames);
//		// teamDataTable.setSize(new Dimension(1000, 1000));
//		
//		JPanel panel = new JPanel();
//		panel.add(teamDataTable);
//		panel.setSize(2000, 2000);
//		BottomScrollPane scroll = new BottomScrollPane(panel);
//		scroll.setLocation(57, 239);
//		this.add(scroll);
		
		JTable table = new JTable(rowData, columnNames);
		table.setSize(1500, 1500);
		table.setLocation(57, 239);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table.getTableHeader().setPreferredSize(new Dimension(30, 30));
	
		scroll = new JScrollPane(table);
		scroll.setSize(888, 290);
		scroll.setLocation(57, 239);
		// scroll.setLayout(null);
		// scroll.setOpaque(false);
		this.add(scroll);
		
		JButton button = new JButton("换页");
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String[] columnNames = {"名称1", "胜1", "负1", "总1", "命中1", "球队名称6", "球队名称7", "球队名称8", "球队名称9",
										"球队名称10", "球队名称11", "球队名称22", "球队名称33", "球队名称44", "球队名称55", "球队名称66", "球队名称", "球队名称"};
				String[][] rowData = new String[20][columnNames.length];
				
				JTable table = new JTable(rowData, columnNames);
				table.setSize(1500, 1500);
				table.setLocation(57, 239);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getTableHeader().setPreferredSize(new Dimension(30, 30));
			
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(2000, 2000));
				panel.setLocation(50, 50);
				panel.setBackground(Color.BLUE);
				panel.setLayout(null);
				panel.add(table);
				
				int value = scroll.getVerticalScrollBar().getValue();
				TeamDataPanel.this.remove(scroll);
				scroll = new JScrollPane(table);
				scroll.setSize(888, 290);
				scroll.setLocation(57, 239);
				scroll.getVerticalScrollBar().setValue(value);
				System.out.println(value);
 				TeamDataPanel.this.add(scroll);
			}
		});
		button.setSize(100, 30);
		button.setLocation(500, 20);
		this.add(button);
	}
	
	JScrollPane scroll;

}
