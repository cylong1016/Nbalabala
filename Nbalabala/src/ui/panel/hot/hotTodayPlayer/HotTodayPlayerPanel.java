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
	String[] lbStr = new String[] { "得分", "篮板", "盖帽", "助攻", "抢断" };
	HotBLService hot = new HotQuery();
	String[] columns = new String[]{"编号","头像","姓名","球队","位置","得分",
			"赛季","日期","对阵" ,"在场时间", "投篮命中数", "投篮出手数", "三分命中数", "三分出手数", "罚球命中数", "罚球出手数",
			"进攻篮板数", "防守篮板数", "总篮板数", "助攻数", "抢断数", "盖帽数", "失误数", "犯规数", "个人得分" };
	
	BottomTable table;
	BottomScrollPane scroll;
	private static final int PORTRAIT_WIDTH = 70;
	private static final HotTodayPlayerProperty[] HOT_TODAY_ARRAY = HotTodayPlayerProperty.values();
	public HotTodayPlayerPanel(String url) {  
		super(url);
		addButton();
		HotTodayButton.current = button[0];
		setEffect(button[0]);
		addListener();
		ArrayList<HotTodayPlayerVO> playerVO = hot.getHotTodayPlayers(HOT_TODAY_ARRAY[0]);
		setTable(playerVO);
	}

	public void addButton() {
		for (int i = 0; i < 5; i++) {
			button[i] = new HotTodayButton(x + i * inter, y, width, height, lbStr[i]);
			button[i].setFont(new Font("微软雅黑", 0, 14));
			button[i].pro = HOT_TODAY_ARRAY[i];
			this.add(button[i]);
		}
	}

	public void addListener() {
		for (int i = 0; i < 5; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == HotTodayButton.current) {
						return;
					}
					HotTodayButton.current.back();
					HotTodayButton.current = (HotTodayButton)e.getSource();
					HotTodayButton button = (HotTodayButton) e.getSource();
					columns[5]=button.text;
					ArrayList<HotTodayPlayerVO> playerVO = hot.getHotTodayPlayers(button.pro);
					setTable(playerVO);
				}
			});
		}
	}

	public void setTable(final ArrayList<HotTodayPlayerVO> players){
		int size = 5;
		int lth = columns.length;
		
		if(scroll != null){
			this.remove(scroll);
		}
		Object [][] rowData = new String[size][lth];
		ArrayList<ImageIcon> iconArr = new ArrayList<ImageIcon>();
		table = new BottomTable(rowData, columns, new Color(215, 72, 72));
		for (int i = 0; i < size; i++) {
			HotTodayPlayerVO ppVO = players.get(i);
			PlayerMatchPerformanceVO matchVO = ppVO.getMatchPerformance();
			MatchPlayerVO matchPlayer = matchVO.getMatchPlayerRecord();
			Image protrait = PlayerImageCache.getPortraitByName(ppVO.getName());
		    int  height =  protrait.getHeight(null)*70/protrait.getWidth(null);//按比例，将高度缩减
		    Image smallImg =protrait.getScaledInstance(PORTRAIT_WIDTH, height, Image.SCALE_SMOOTH);
			ImageIcon ic = new ImageIcon(smallImg);
			iconArr.add(ic);
			rowData[i][0] = (i+1)+"";
			rowData[i][2] = ppVO.getName();
			rowData[i][3] = Constants.translateTeamAbbr(ppVO.getTeamAbbr());
			rowData[i][4] = ppVO.getPosition();
			rowData[i][5] = ppVO.getProperty()+"";
			rowData[i][6] = matchVO.getSeason();
			rowData[i][7] = matchVO.getDate();
			rowData[i][8] = matchVO.getTwoTeams();
			rowData[i][9] = matchPlayer.getTime();
			rowData[i][10] = matchPlayer.getFieldGoal() + "";
			rowData[i][11] = matchPlayer.getFieldAttempt() + "";
			rowData[i][12] = matchPlayer.getThreePointGoal() + "";
			rowData[i][13] = matchPlayer.getThreePointAttempt() + "";
			rowData[i][14] = matchPlayer.getFreethrowGoal() + "";
			rowData[i][15] = matchPlayer.getFieldAttempt() + "";
			rowData[i][16] = matchPlayer.getOffensiveRebound() + "";
			rowData[i][17] = matchPlayer.getDefensiveRebound() + "";
			rowData[i][18] = matchPlayer.getTotalRebound() + "";
			rowData[i][19] = matchPlayer.getAssist() + "";
			rowData[i][20] = matchPlayer.getSteal() + "";
			rowData[i][21] = matchPlayer.getBlock() + "";
			rowData[i][22] = matchPlayer.getTurnover() + "";
			rowData[i][23] = matchPlayer.getFoul() + "";
			rowData[i][24] = matchPlayer.getPersonalGoal() + "";
		}
		MyTableCellRenderer myRenderer = new MyTableCellRenderer();
		myRenderer.icon = iconArr;
//		iconArr.clear();
		table.getColumnModel().getColumn(1).setCellRenderer(myRenderer);
		
		try{
			table.addMouseListener(new UserMouseAdapter(){
				
				public void mouseClicked(MouseEvent e){
					if (e.getClickCount() < 2) return;
					int rowI  = table.rowAtPoint(e.getPoint());// 得到table的行号
					if ( rowI > -1){
						MainController.toPlayerInfoPanel(HotTodayPlayerPanel.this, 
								players.get(rowI).getName(),HotTodayPlayerPanel.this);
					}
					
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		table.setRowHeight(57);
		table.setForeground(Color.black);
		table.cancelVerticalLines();
		table.setRealOpaque();
		int []cells = new int[25];
		for (int j = 0; j < cells.length; j++) {
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
	}
	/**
	 * 设置按钮初始选中效果
	 * 
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
