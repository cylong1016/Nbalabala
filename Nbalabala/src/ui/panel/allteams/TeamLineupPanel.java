package ui.panel.allteams;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
public class TeamLineupPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 7928034817468828593L;
	BottomTable playerTable;
	ArrayList<PlayerProfileVO> players;
	
	//TODO 坐等云龙的数据
	public TeamLineupPanel(ArrayList<PlayerProfileVO> players) {
		super();
		this.players = players;
		String[]columns = new String[] {"姓名", "号码", "位置", "年龄","球龄","身高","体重","生日","毕业学校"};
		String[][]rowData = new String[players.size()][columns.length];
		playerTable = new BottomTable(rowData, columns);
		playerTable.setRowHeight(40);
		playerTable.setWidth(new int[]{140, 44, 44, 44, 44, 151, 118, 77, 209});
		BottomScrollPane scroll = new BottomScrollPane(playerTable);
		scroll.setBounds(25, 50, 888, 270);	//TODO Y坐标随便写的
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
	}
	
}

