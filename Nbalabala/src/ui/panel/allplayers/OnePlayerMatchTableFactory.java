/**
 * 
 */
package ui.panel.allplayers;

import java.util.ArrayList;

import javax.swing.JScrollBar;

import po.MatchPlayerPO;
import ui.UIConfig;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import utility.Constants;

/**
 * 用来产生球员赛程记录的表格
 * @author Issac Ding
 * @since 2015年5月16日 上午10:18:52
 * @version 1.0
 */
public class OnePlayerMatchTableFactory {
	
	private BottomScrollPane scrollPane;
	private int rowCount;
	
	public OnePlayerMatchTableFactory(ArrayList<MatchPlayerPO> playerMatch) {
		int lth = playerMatch.size();
		Object[][] rowData = new String[lth][Constants.onePlayerMatchHeaders.length];
		// "日期","对手","首发","时间","投篮","三分","罚球",
		// "进攻篮板","防守篮板","篮板","助攻","抢断","盖帽","失误","犯规","得分"
		for(int i = 0; i < lth; i++) {
			
			MatchPlayerPO player = playerMatch.get(i);
			rowData[i][0] = Constants.translateDate(player.getDate());
			rowData[i][1] = Constants.translateTeamAbbr(player.getOppoAbbr());
			if (player.isStarter()) {
				rowData[i][2] = "Y";
			}else {
				rowData[i][2] = "N";
			}
			rowData[i][3] = player.getTimePlayed();
			rowData[i][4] = player.getFieldMade() + "/" + player.getFieldAttempt();
			// rowData[i][5] = player.getFieldAttempt() + "";
			rowData[i][5] = player.getThreepointMade() + "/" + player.getThreepointAttempt();
//			rowData[i][7] = player.getThreepointAttempt() + "";
			rowData[i][6] = player.getFreethrowMade() + "/" + player.getFreethrowAttempt();
//			rowData[i][9] = player.getFreethrowAttempt() + "";
			rowData[i][7] = player.getOffensiveRebound() + "";
			rowData[i][8] = player.getDefensiveRebound() + "";
			rowData[i][9] = player.getTotalRebound() + "";
			rowData[i][10] = player.getAssist() + "";
			rowData[i][11] = player.getSteal() + "";
			rowData[i][12] = player.getBlock() + "";
			rowData[i][13] = player.getTurnover() + "";
			rowData[i][14] = player.getFoul() + "";
			rowData[i][15] = player.getScore() + "";
		}
		BottomTable table = new BottomTable(rowData, Constants.onePlayerMatchHeaders);
		//TODO 还需要设置列宽
		table.setHeaderColorAndFont();
		table.setRowHeight(UIConfig.ROW_HEIGHT);
		table.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		table.setWidth(new int[] {80, 98, 50, 50, 50, 50, 50, 55, 55, 50, 50, 50, 50, 50, 50, 50});
		this.rowCount = table.getRowCount();
		scrollPane = new BottomScrollPane(table);
	}
	
	public BottomScrollPane getTableScrollPane() {
		return scrollPane;
	}
	
	/**
	 * 判断是否有滚动条来设置宽度
	 * @param height
	 * @return
	 */
	public int setTableWidth(int height){
		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
		int width = (int) scrollBar.getPreferredSize().getWidth();
		return height < (rowCount*UIConfig.ROW_HEIGHT)? UIConfig.TABLE_WID + width : UIConfig.TABLE_WID;
	}

}
