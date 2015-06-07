/**
 * 
 */
package ui.panel.allteams;

import java.util.ArrayList;

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
	private BottomTable matchTable;
	private BottomScrollPane scrollPane;
	private String teamAbbr;	//己方球队缩写
	
	public TeamMatchPanel(String teamAbbr) {
		this.teamAbbr = teamAbbr;
		
		//TODO 这里放设置表格外观的代码，表格外观待考究
		
	}
	
	public void updateContent(ArrayList<MatchProfileVO> matchProfiles) {
		if (scrollPane != null) {
			remove(scrollPane);
		}
		Object[][] content = new Object[matchProfiles.size()][4];
		for (int i=0;i<matchProfiles.size();i++) {
			MatchProfileVO vo = matchProfiles.get(i);
			content[i][0] = vo.getTime();
			
			String [] teams = vo.getTeam().split("-");
			String [] scoresStr = vo.getScore().split("-");
			int firstScore = Integer.parseInt(scoresStr[0]);
			int secondScore = Integer.parseInt(scoresStr[0]);
			
			if (teams[0].equals(teamAbbr)) {
				content[i][1] = Constants.translateTeamAbbr(teams[1]);	
				if (firstScore > secondScore) content[i][2] = Constants.winsText;
				else content[i][2] = Constants.losesText;
			}else {
				content[i][1] = Constants.translateTeamAbbr(teams[0]);	
				if (firstScore > secondScore) content[i][2] = Constants.losesText;
				else content[i][2] = Constants.winsText;
			}
			content[i][3] = vo.getScore();
		}
		matchTable = new BottomTable(content,Constants.teamMatchHeaders);
		scrollPane = new BottomScrollPane(matchTable);
		scrollPane.setBounds(28,37,888,278);	//表格在这个子页面中的坐标
		this.add(scrollPane);
		repaint();
		
		//TODO 如何单击一场比赛然后跳过去。根据比赛id。逻辑后面再做。
	}

}
