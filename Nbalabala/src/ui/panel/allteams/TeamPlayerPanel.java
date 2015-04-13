package ui.panel.allteams;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.SeasonInputPanel;
import ui.common.UserMouseAdapter;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import vo.PlayerProfileVO;


/**
 * 球队阵容数据界面
 * @author lsy
 * @version 2015年4月13日  下午8:32:02
 */
public class TeamPlayerPanel extends TeamFatherPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 7928034817468828593L;
	/** 队伍数据表格 */
	private BottomTable playerTable;
	/** 放表格的滚动条 */
	private BottomScrollPane scroll;
	String[] columns;
	String[][] rowData;
	/** 赛季选择器 */
	protected SeasonInputPanel seasonInput;
	
	public TeamPlayerPanel(BottomPanel panelFrom,String url, String abbr) {
		super(panelFrom,url, abbr);
		setEffect(1);
		ArrayList<PlayerProfileVO> players = teamDetail.getPlayers();
		seasonInput = new SeasonInputPanel(this);
		seasonInput.setLocation(515, y);
		this.add(seasonInput);
		addplayerTable(players);
		updatePlayerTable(players);
	}

	public void addplayerTable(ArrayList<PlayerProfileVO> players){
		columns = new String[] {"姓名", "号码", "位置", "年龄","球龄","身高","体重","生日","毕业学校"};
		int size = players.size();
		int lth = columns.length;
		rowData = new String[size][lth];
		playerTable = new BottomTable(rowData, columns);
		playerTable.setRowHeight(40);
		playerTable.setWidth(new int[]{140, 44, 44, 44, 44, 151, 118, 77, 209});
		scroll = new BottomScrollPane(playerTable);
		scroll.setBounds(57, 260, 888, 270);
		this.add(scroll);
	}
	
	public void updatePlayerTable(final ArrayList<PlayerProfileVO> players) {
		for (int i = 0; i < players.size(); i++) {
			PlayerProfileVO ppVO = players.get(i);
			playerTable.setValueAt(ppVO.getName(), i, 0);
			playerTable.setValueAt(ppVO.getNumber(), i, 1);
			playerTable.setValueAt(ppVO.getPosition(), i, 2);
			playerTable.setValueAt(ppVO.getAge(), i, 3);
			playerTable.setValueAt(ppVO.getExp(), i, 4);
			playerTable.setValueAt(ppVO.getHeight(), i, 5);
			playerTable.setValueAt( ppVO.getWeight(), i, 6);
			playerTable.setValueAt(ppVO.getBirth(), i, 7);
			playerTable.setValueAt(ppVO.getSchool(), i, 8);
			
		}
		//将头像放入表格的第一列 监听已加好 双击球员某一信息进入下一界面
		try{
			playerTable.addMouseListener(new UserMouseAdapter(){
				
				public void mouseClicked(MouseEvent e){
					if (e.getClickCount() < 2) return;
					int rowI  = playerTable.rowAtPoint(e.getPoint());// 得到table的行号
					if ( rowI > -1){
						MainController.toPlayerInfoPanel(TeamPlayerPanel.this, 
								players.get(rowI).getName(),TeamPlayerPanel.this);
					}
					
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

