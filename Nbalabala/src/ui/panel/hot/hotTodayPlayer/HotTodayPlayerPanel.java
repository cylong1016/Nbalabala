package ui.panel.hot.hotTodayPlayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import po.MatchPlayerPO;
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
import utility.Utility;
import vo.HotTodayPlayerVO;
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

	private int x = 90, y = 52, width = 80, height = 25, inter = 90, cellWidth = 60;
	private HotTodayButton button[] = new HotTodayButton[5];
	private HotBLService hot = new HotQuery();
	private BottomTable table;
	private BottomScrollPane scroll;
	private static final int PORTRAIT_WIDTH = 70;
	private static final HotTodayPlayerProperty[] HOT_TODAY_ARRAY = HotTodayPlayerProperty.values();
	private ArrayList<HotTodayPlayerVO> playerVO;
	private Chart chart;
	private String text = Constants.scoreText;

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
		if (chart != null){
			chart.setData(getColumns(), getMax());
			chart.setTitle(text);
		}
	}

	/**
	 * @return 柱状图全部列
	 * @author cylong
	 * @version 2015年4月13日 下午10:39:29
	 */
	private ArrayList<Column> getColumns() {
		ArrayList<Column> columnArray = new ArrayList<Column>();
		for(int i = 0; i < 5; i++) {
			double property = playerVO.get(i).getProperty();
			columnArray.add(new Column(Utility.trimName(playerVO.get(i).getName()), property, UIConfig.HIST_COLORS[i]));
		}
		return columnArray;
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
			button[i] = new HotTodayButton(x + i * inter, y, width, height, Constants.LBSTR[i]);
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
					playerVO = hot.getHotTodayPlayers(currentButton.pro);
					refresh();
					HotTodayPlayerPanel.this.repaint();
				}
			});
		}
	}

	int size = 5;
	int lth = Constants.HOT_COLUMNS.length;
	Object[][] rowData ;
	
	public void addTable(){
		rowData = new String[size][lth];
		table = new BottomTable(rowData, Constants.HOT_COLUMNS, new Color(215, 72, 72));
		
		table.setRowHeight(57);
		table.setHeaderColorAndFont();
		table.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		table.setForeground(UIConfig.TABLE_HEADER_BACK_COLOR);
//		table.setWidth(new int[] {});
		int[] cells = new int[12];
		for(int j = 0; j < cells.length; j++) {
			cells[j] = cellWidth;
		}
		cells[2] = 125; // 名字
		cells[1] = 80; // 头像
		cells[6] = 65; // 在场时间
		table.setWidth(cells);
		
		scroll = new BottomScrollPane(table);
		scroll.setBounds(90, 260, 810, 340);
		this.add(scroll);
		setTable(playerVO);
		
		try {
			table.addMouseListener(new UserMouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(playerVO.get(rowI).getName(), HotTodayPlayerPanel.this);
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
			MatchPlayerPO matchPlayer = ppVO.getMatchPerformance();
			Image protrait = PlayerImageCache.getPortraitByName(ppVO.getName());
			int height = protrait.getHeight(null) * 70 / protrait.getWidth(null);//按比例，将高度缩减
			Image smallImg = protrait.getScaledInstance(PORTRAIT_WIDTH, height, Image.SCALE_SMOOTH);
			ImageIcon ic = new ImageIcon(smallImg);
			iconArr.add(ic);
			table.setValueAt( (i + 1) + "", i, 0);
			table.setValueAt(Utility.shortenName(ppVO.getName()), i, 2); //不显示名字后面的编号
			table.setValueAt(Constants.translateTeamAbbr(ppVO.getTeamAbbr()), i, 3);
			table.setValueAt(Constants.translateTeamAbbr(ppVO.getOppoAbbr()), i, 4);
			table.setValueAt(ppVO.getPosition(), i, 5);
			table.setValueAt(matchPlayer.getTimePlayed(), i, 6);
			table.setValueAt(matchPlayer.getScore() + "", i, 7);
			table.setValueAt(matchPlayer.getTotalRebound() + "", i, 8);
			table.setValueAt(matchPlayer.getAssist() + "", i, 9);
			table.setValueAt(matchPlayer.getBlock() + "", i, 10);
			table.setValueAt(matchPlayer.getSteal() + "", i, 11);
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
