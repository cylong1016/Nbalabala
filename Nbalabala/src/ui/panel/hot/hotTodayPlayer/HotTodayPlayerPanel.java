package ui.panel.hot.hotTodayPlayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.JTableHeader;

import ui.UIConfig;
import ui.common.UserMouseAdapter;
import ui.common.button.TextButton;
import ui.common.chart.Chart;
import ui.common.chart.Column;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import ui.panel.allplayers.MyTableCellRenderer;
import ui.panel.hot.HotFatherPanel;
import utility.Constants;
import vo.HotTodayPlayerVO;
import vo.MatchPlayerVO;
import vo.PlayerMatchPerformanceVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import data.playerdata.PlayerImageCache;
import enums.HotTodayPlayerProperty;

/**
 * 今日热点球员热点界面
 * @author lsy
 * @version 2015年4月11日 下午12:12:09
 */
public class HotTodayPlayerPanel extends HotFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 4256548887751307664L;

	private int x = 103, y = 50, width = 55, height = 25, inter = 60, cellWidth = 70;
	HotTodayButton button[] = new HotTodayButton[5];
	String[] lbStr = new String[]{"得分", "篮板", "盖帽", "助攻", "抢断"};
	HotBLService hot = new HotQuery();
	String[] columns = new String[]{"编号", "头像", "姓名", "球队", "位置", "得分", "赛季", "日期", "对阵", "在场时间", "投篮命中数", "投篮出手数",
									"三分命中数", "三分出手数", "罚球命中数", "罚球出手数", "进攻篮板数", "防守篮板数", "总篮板数", "助攻数", "抢断数", "盖帽数",
									"失误数", "犯规数", "个人得分"};

	BottomTable table;
	BottomScrollPane scroll;
	private static final int PORTRAIT_WIDTH = 70;
	private static final HotTodayPlayerProperty[] HOT_TODAY_ARRAY = HotTodayPlayerProperty.values();
	ArrayList<HotTodayPlayerVO> playerVO;
	Chart chart;
	String text = "得分";

	public HotTodayPlayerPanel(String url) {
		super(url);
		addButton();
		HotTodayButton.current = button[0];
		setEffect(button[0]);
		addListener();
		playerVO = hot.getHotTodayPlayers(HOT_TODAY_ARRAY[0]);
		addTable();
		addChart();
	}

	public void refresh() {
		playerVO = hot.getHotTodayPlayers(HotTodayButton.current.pro);
		if (playerVO.size() < 5)
			return;
		setTable(playerVO);
		updateChart(); // 添加柱状图	
	}

	/**
	 * 添加数据柱状图
	 * @author cylong
	 * @version 2015年4月12日 上午1:12:06
	 */
	private void addChart() {
		if (chart != null) {
			this.remove(chart);
		}
		// 每一条柱子		
		if (playerVO.size() < 5)
			return;
		chart = new Chart(text, getColumns(), getMax());

		chart.setBounds(95, 103, 809, 145);
		this.add(chart);
		chart.updateUI();
		chart.repaint();
	}

	/**
	 * 更新柱状图数据
	 * @author cylong
	 * @version 2015年4月13日 下午10:34:21
	 */
	public void updateChart() {
		playerVO = hot.getHotTodayPlayers(HotTodayButton.current.pro);
		if (playerVO.size() < 5)
			return;
		if (chart != null)
			chart.setData(getColumns(), getMax());
	}

	/**
	 * @return 柱状图全部列
	 * @author cylong
	 * @version 2015年4月13日 下午10:39:29
	 */
	private ArrayList<Column> getColumns() {
		ArrayList<Column> columns = new ArrayList<Column>();
		for(int i = 0; i < 5; i++) {
			double property = playerVO.get(i).getProperty();
			columns.add(new Column(playerVO.get(i).getName(), property, UIConfig.HIST_COLORS[i]));
		}
		return columns;
	}

	/**
	 * @return 柱状图最大数据
	 * @author cylong
	 * @version 2015年4月13日 下午10:39:36
	 */
	private double getMax() {
		double max = playerVO.get(0).getProperty();
		double property = max;
		for(int i = 0; i < 5; i++) {
			property = playerVO.get(i).getProperty();
			if (max < property) {
				max = property;
			}
		}
		return max;
	}

	public void addButton() {
		for(int i = 0; i < 5; i++) {
			button[i] = new HotTodayButton(x + i * inter, y, width, height, lbStr[i]);
			button[i].setFont(new Font("微软雅黑", 0, 14));
			button[i].pro = HOT_TODAY_ARRAY[i];
			this.add(button[i]);
		}
	}

	public void addListener() {
		for(int i = 0; i < 5; i++) {
			button[i].addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					if (e.getSource() == HotTodayButton.current) {
						return;
					}
					HotTodayButton.current.back();
					HotTodayButton.current = (HotTodayButton)e.getSource();
					HotTodayButton currentButton = (HotTodayButton)e.getSource();
					text = currentButton.text;
					columns[5] = currentButton.text;
					playerVO = hot.getHotTodayPlayers(currentButton.pro);
					refresh();
					HotTodayPlayerPanel.this.repaint();
				}
			});
		}
	}

	int size = 5;
	int lth = columns.length;
	Object[][] rowData ;
	
	public void addTable(){
		rowData = new String[size][lth];
		table = new BottomTable(rowData, columns, new Color(215, 72, 72));
		
		table.setRowHeight(57);
		table.setForeground(UIConfig.TABLE_HEADER_BACK_COLOR);
		table.cancelVerticalLines();
		table.setRealOpaque();
		int[] cells = new int[25];
		for(int j = 0; j < cells.length; j++) {
			cells[j] = cellWidth;
		}
		cells[2] = cells[8] = 120;
		table.setWidth(cells);
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.red);
		
		scroll = new BottomScrollPane(table);
		scroll.setBounds(90, 260, 810, 320);
		scroll.cancelBgImage();
		this.add(scroll);
		setTable(playerVO);
		
		try {
			table.addMouseListener(new UserMouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(HotTodayPlayerPanel.this, playerVO.get(rowI).getName(), HotTodayPlayerPanel.this);
					}
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setTable(final ArrayList<HotTodayPlayerVO> players) {
		ArrayList<ImageIcon> iconArr = new ArrayList<ImageIcon>();
		if (players.size() < 5)
			size = players.size();
		for(int i = 0; i < size; i++) {
			HotTodayPlayerVO ppVO = players.get(i);
			PlayerMatchPerformanceVO matchVO = ppVO.getMatchPerformance();
			MatchPlayerVO matchPlayer = matchVO.getMatchPlayerRecord();
			Image protrait = PlayerImageCache.getPortraitByName(ppVO.getName());
			int height = protrait.getHeight(null) * 70 / protrait.getWidth(null);//按比例，将高度缩减
			Image smallImg = protrait.getScaledInstance(PORTRAIT_WIDTH, height, Image.SCALE_SMOOTH);
			ImageIcon ic = new ImageIcon(smallImg);
			iconArr.add(ic);
			
			table.setValueAt( (i + 1) + "", i, 0);
			table.setValueAt(ppVO.getName(), i, 2);
			table.setValueAt(Constants.translateTeamAbbr(ppVO.getTeamAbbr()), i, 3);
			table.setValueAt(ppVO.getPosition(), i, 4);
			table.setValueAt(ppVO.getProperty()+"", i, 5);
			table.setValueAt(matchVO.getSeason(), i, 6);
			table.setValueAt(matchVO.getDate(), i, 7);
			table.setValueAt(matchVO.getTwoTeams(), i, 8);
			table.setValueAt(matchPlayer.getTime(), i, 9);
			table.setValueAt(matchPlayer.getFieldGoal() + "", i, 10);
			table.setValueAt(matchPlayer.getFieldAttempt() + "", i, 11);
			table.setValueAt(matchPlayer.getThreePointGoal() + "", i, 12);
			table.setValueAt(matchPlayer.getThreePointAttempt() + "", i, 13);
			table.setValueAt(matchPlayer.getFreethrowGoal() + "", i, 14);
			table.setValueAt(matchPlayer.getFieldAttempt() + "", i, 15);
			table.setValueAt(matchPlayer.getOffensiveRebound() + "", i, 16);
			table.setValueAt(matchPlayer.getDefensiveRebound() + "", i, 17);
			table.setValueAt(matchPlayer.getTotalRebound() + "", i, 18);
			table.setValueAt(matchPlayer.getAssist() + "", i, 19);
			table.setValueAt(matchPlayer.getSteal() + "", i, 20);
			table.setValueAt(matchPlayer.getBlock() + "", i, 21);
			table.setValueAt(matchPlayer.getTurnover() + "", i, 22);
			table.setValueAt(matchPlayer.getFoul() + "", i, 23);
			table.setValueAt(matchPlayer.getPersonalGoal() + "", i, 24);
		}
		MyTableCellRenderer myRenderer = new MyTableCellRenderer();
		myRenderer.icon = iconArr;
		// iconArr.clear();
		table.getColumnModel().getColumn(1).setCellRenderer(myRenderer);
	}

	/**
	 * 设置按钮初始选中效果
	 * @param button
	 * @author lsy
	 * @version 2015年4月11日 下午12:39:45
	 */
	public void setEffect(TextButton button) {
		button.setOpaque(true);
		button.setBackground(UIConfig.BUTTON_COLOR);
		button.setForeground(Color.white);
	}
}
