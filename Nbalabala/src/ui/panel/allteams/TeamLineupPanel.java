package ui.panel.allteams;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.PlayerProfilePO;
import ui.UIConfig;
import ui.common.UserMouseAdapter;
import ui.common.label.NavLabel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import utility.Utility;

/**
 * 球队阵容数据界面
 * 
 * @author lsy
 * @version 2015年4月13日 下午8:32:02
 */
public class TeamLineupPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 7928034817468828593L;
	private BottomTable playerTable;
	private BottomScrollPane scroll;
	private ArrayList<PlayerProfilePO> players;

	public TeamLineupPanel(ArrayList<PlayerProfilePO> players) {
		super();
		this.players = players;

		NavLabel navLabel = new NavLabel(Constants.lineUp);
		navLabel.setLocation(UIConfig.RELA_X, 16);
		this.add(navLabel);
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
		String[][] rowData = new String[players.size()][Constants.teamLineupHeaders.length];
		playerTable = new BottomTable(rowData, Constants.teamLineupHeaders);
		playerTable.setRowHeight(UIConfig.ROW_HEIGHT);
		playerTable.setWidth(new int[] { 156, 44, 44, 44, 118, 118, 77, 287 }); 
		playerTable.setHeaderColorAndFont();
		playerTable.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		
		Rectangle bounds = new Rectangle(
				UIConfig.RELA_X, 16 + 40, playerTable.setTableWidth(UIConfig.TABLE_H, playerTable.getRowCount()), UIConfig.TABLE_H);
		scroll = new BottomScrollPane(playerTable);
		scroll.setBounds(bounds);
		this.add(scroll);

		// 将头像放入表格的第一列 监听已加好 双击球员某一信息进入下一界面
		try {
			playerTable.addMouseListener(new UserMouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = playerTable.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(
								TeamLineupPanel.this.players.get(rowI)
										.getName(), TeamLineupPanel.this);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < players.size(); i++) {
			PlayerProfilePO ppVO = players.get(i);
			playerTable.setValueAt(Utility.trimName(ppVO.getName()), i, 0);
			playerTable.setValueAt(ppVO.getPosition(), i, 1);
			playerTable.setValueAt(String.valueOf(Utility.getAgeByBirthday(ppVO
					.getBirthDate())), i, 2);
			playerTable.setValueAt(Constants.translateVeteran(ppVO.fromYear),
					i, 3);
			playerTable.setValueAt(
					Constants.translateHeight(ppVO.heightFoot + "-"
							+ ppVO.heightInch), i, 4);
			playerTable.setValueAt(Constants.translateWeight(ppVO.getWeight()),
					i, 5);
			playerTable.setValueAt(Constants.translateDate(ppVO.birthDate), i,
					6);
			playerTable.setValueAt(ppVO.getSchool(), i, 7);
		}
	}

}
