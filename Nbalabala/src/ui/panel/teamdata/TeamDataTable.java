package ui.panel.teamdata;

import javax.swing.JPanel;

import ui.common.table.BottomTable;

/**
 * 球员数据的table，实际上是一个Panel上面放啦一个表格，然后把这个Panel放在主panel上面
 * @author cylong
 * @version 2015年3月20日 下午6:37:26
 */
public class TeamDataTable extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -7885076081223249890L;
	/** 显示数据的表格 */
	private BottomTable dataTable;

	public TeamDataTable(Object[][] rowData, String[] columnNames) {
		dataTable = new BottomTable(rowData, columnNames);
		dataTable.setLocation(50, 50);
		dataTable.setSize(500, 500);
		this.setLayout(null);
		this.add(dataTable);
	}

	public BottomTable getDataTable() {
		return this.dataTable;
	}

}
