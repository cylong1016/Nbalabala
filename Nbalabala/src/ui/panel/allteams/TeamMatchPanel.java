/**
 * 
 */
package ui.panel.allteams;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import utility.Utility;
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
	private ArrayList<MatchProfileVO> matches;
	
	public TeamMatchPanel(String teamAbbr) {
		this.teamAbbr = teamAbbr;
		
		//TODO 这里放设置表格外观的代码，表格外观待考究
		
	}
	
	public void updateContent(ArrayList<MatchProfileVO> matchProfiles) {
		if (scrollPane != null) {
			remove(scrollPane);
		}
		matches = matchProfiles;
		Object[][] content = new Object[matchProfiles.size()][4];
		for (int i=0;i<matchProfiles.size();i++) {
			MatchProfileVO vo = matchProfiles.get(i);
			content[i][0] = vo.getTime();
			
			String [] teams = vo.getTeam().split("-");
			String [] scoresStr = vo.getScore().split("-");
			int firstScore = Integer.parseInt(scoresStr[0]);
			int secondScore = Integer.parseInt(scoresStr[1]);
			
			String oldAbbr = Utility.getOldAbbr(vo.getSeason(), teamAbbr);
			
			if (teams[0].equals(oldAbbr)) {
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
		
		matchTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() < 2) return;
				int row = matchTable.rowAtPoint(e.getPoint());
				if (row >= 0) {
					int matchID = matches.get(row).getMatchID();
					MainController.toGameDetailPanel(matchID, (Panel)(getParent()));
				}
			}
		});
	}

}
