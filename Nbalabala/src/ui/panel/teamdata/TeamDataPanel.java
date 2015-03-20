package ui.panel.teamdata;

import java.awt.Graphics;

import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;

/**
 * 球队数据界面
 * @author cylong
 * @version 2015年3月18日 上午11:40:37
 */
public class TeamDataPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -4296014620804951285L;

	/** 球队数据表格 */
	private BottomTable teamDataTable;

	/**
	 * @param url 背景图片的url
	 * @author cylong
	 * @version 2015年3月18日 上午11:51:32
	 */
	public TeamDataPanel(String url) {
		super(url);
		addTeamDataTable();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	/**
	 * 添加当前登录的用户的信息table
	 * @author cylong
	 * @version 2014年12月15日 下午10:40:36
	 */
	private void addTeamDataTable() {
		String[] columnNames = {"球队名称1", "球队名称2", "球队名称3", "球队名称4", "球队名称5", "球队名称6", "球队名称7", "球队名称8", "球队名称9", "球队名称0", "球队名称11", "球队名称22", "球队名称33", "球队名称44", "球队名称55", "球队名称66", "球队名称", "球队名称"};
		String[][] rowData = new String[10][columnNames.length];
		teamDataTable = new BottomTable(columnNames, rowData);
		BottomScrollPane scroll = new BottomScrollPane(teamDataTable);
		scroll.setLocation(57, 239);
		this.add(scroll);
	}

}
