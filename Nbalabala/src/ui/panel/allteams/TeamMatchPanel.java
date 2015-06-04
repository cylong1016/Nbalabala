/**
 * 
 */
package ui.panel.allteams;

import java.util.ArrayList;

import ui.common.SeasonInputPanel;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import utility.Constants;
import vo.MatchProfileVO;

/**
 *
 * @author Issac Ding
 * @since 2015年5月14日 下午9:59:50
 * @version 1.0
 */
public class TeamMatchPanel extends Panel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7575781820802938629L;
	private SeasonInputPanel seasonInputPanel;
	private BottomTable matchTable;
	private BottomScrollPane scrollPane;
	
	public TeamMatchPanel(SeasonInputPanel seasonInputPanel) {
		this.seasonInputPanel = seasonInputPanel;
		seasonInputPanel.setLocation(15, 0);
		this.add(seasonInputPanel);
		
		//TODO 表格外观待考究
		
	}
	
	public void updateContent(ArrayList<MatchProfileVO> matchProfiles) {
		if (scrollPane != null) {
			remove(scrollPane);
		}
		Object[][] content = new Object[matchProfiles.size()][4];
		for (int i=0;i<matchProfiles.size();i++) {
			MatchProfileVO vo = matchProfiles.get(i);
			//TODO 因为不知道爬下来的数据长什么样，表现形式待定
			content[i][0] = vo.getTime();
			content[i][1] = vo.getTeam();	
			content[i][2] = "不造哦";
			content[i][3] = vo.getScore();
		}
		matchTable = new BottomTable(content,Constants.teamMatchHeaders);
		scrollPane = new BottomScrollPane(matchTable);
		scrollPane.setBounds(28,37,888,278);
		this.add(scrollPane);
		repaint();
		//TODO 也不知道如何单击一场比赛然后跳过去。（比赛应该是有id的吧）
	}
	
	public void addSeasonChooser() {
		add(seasonInputPanel);
	}

}
