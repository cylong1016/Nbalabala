package ui.panel.allteams;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.PlayerProfilePO;
import ui.common.UserMouseAdapter;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import utility.Utility;


/**
 * 球队阵容数据界面
 * @author lsy
 * @version 2015年4月13日  下午8:32:02
 */
public class TeamLineupPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 7928034817468828593L;
	private BottomTable playerTable;
	private BottomScrollPane scroll;
	private ArrayList<PlayerProfilePO> players;
	private static final Rectangle SCROLL_BOUNDS = new Rectangle(25, 50, 888, 270);//TODO Y坐标随便写的
	
	public TeamLineupPanel(ArrayList<PlayerProfilePO> players) {
		super();
		this.players = players;
		
		setTable();
	}
	
	public void updateContent(ArrayList<PlayerProfilePO> players) {
		this.players = players;
		if (scroll != null) {
			remove(scroll);
		}
		setTable();
	}
	
	private void setTable() {
		String[][]rowData = new String[players.size()][Constants.teamLineupHeaders.length];
		playerTable = new BottomTable(rowData, Constants.teamLineupHeaders);
		playerTable.setRowHeight(40);
		playerTable.setWidth(new int[]{140, 44, 44, 44, 151, 118, 77, 209});	//TODO列宽
		scroll = new BottomScrollPane(playerTable);
		scroll.setBounds(SCROLL_BOUNDS);	
		this.add(scroll);
		
		//将头像放入表格的第一列 监听已加好 双击球员某一信息进入下一界面
		try{
			playerTable.addMouseListener(new UserMouseAdapter(){
				public void mouseClicked(MouseEvent e){
					if (e.getClickCount() < 2) return;
					int rowI  = playerTable.rowAtPoint(e.getPoint());// 得到table的行号
					if ( rowI > -1){
						MainController.toPlayerInfoPanel(TeamLineupPanel.this.players.get(rowI).getName(),TeamLineupPanel.this);
					}
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (int i = 0; i < players.size(); i++) {
			PlayerProfilePO ppVO = players.get(i);
			playerTable.setValueAt(Utility.trimName(ppVO.getName()), i, 0);
			playerTable.setValueAt(ppVO.getPosition(), i, 1);
			playerTable.setValueAt(String.valueOf(Utility.getAgeByBirthday(ppVO.getBirthDate())), i, 2);
			playerTable.setValueAt(Constants.translateVeteran(ppVO.fromYear), i, 3);
			playerTable.setValueAt(Constants.translateHeight(ppVO.heightFoot + "-" + ppVO.heightInch), i, 4);
			playerTable.setValueAt(Constants.translateWeight(ppVO.getWeight()), i, 5);
			playerTable.setValueAt(Constants.translateDate(ppVO.birthDate), i, 6);
			playerTable.setValueAt(ppVO.getSchool(), i, 7);
		}
	}
	
}

