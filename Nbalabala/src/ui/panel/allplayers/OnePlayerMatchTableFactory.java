/**
 * 
 */
package ui.panel.allplayers;

import java.util.ArrayList;

import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import utility.Constants;
import vo.MatchPlayerVO;
import vo.PlayerMatchPerformanceVO;

/**
 * 用来产生球员赛程记录的表格
 * @author Issac Ding
 * @since 2015年5月16日 上午10:18:52
 * @version 1.0
 */
public class OnePlayerMatchTableFactory {
	
	private BottomScrollPane scrollPane;
	
	public OnePlayerMatchTableFactory(ArrayList<PlayerMatchPerformanceVO> playerMatch) {
		int lth = playerMatch.size();
		Object[][] rowData = new String[lth][Constants.onePlayerMatchHeaders.length];
		for(int i = 0; i < lth; i++) {
			PlayerMatchPerformanceVO vo = playerMatch.get(i);
			MatchPlayerVO player = vo.getMatchPlayerRecord();
			rowData[i][0] = vo.getDate();
			rowData[i][1] = vo.getTwoTeams();
			rowData[i][2] = player.getPosition();
			rowData[i][3] = player.getTime();
			rowData[i][4] = player.getFieldGoal() + "";
			rowData[i][5] = player.getFieldAttempt() + "";
			rowData[i][6] = player.getThreePointGoal() + "";
			rowData[i][7] = player.getThreePointAttempt() + "";
			rowData[i][8] = player.getFreethrowGoal() + "";
			rowData[i][9] = player.getFreethrowAttempt() + "";
			rowData[i][10] = player.getOffensiveRebound() + "";
			rowData[i][11] = player.getDefensiveRebound() + "";
			rowData[i][12] = player.getTotalRebound() + "";
			rowData[i][13] = player.getAssist() + "";
			rowData[i][14] = player.getSteal() + "";
			rowData[i][15] = player.getBlock() + "";
			rowData[i][16] = player.getTurnover() + "";
			rowData[i][17] = player.getFoul() + "";
			rowData[i][18] = player.getPersonalGoal() + "";
		}
		BottomTable table = new BottomTable(rowData, Constants.onePlayerMatchHeaders);
		//TODO 还需要设置列宽
		scrollPane = new BottomScrollPane(table);
	}
	
	public BottomScrollPane getTableScrollPane() {
		return scrollPane;
	}

}
