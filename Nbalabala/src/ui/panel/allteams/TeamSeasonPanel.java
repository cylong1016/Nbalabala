package ui.panel.allteams;

import po.TeamSeasonPO;
import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.panel.allplayers.ContrastDiagram;
import utility.Constants;

/**
 * 球队赛季数据界面
 * @author lsy
 * @version 2015年4月13日  下午8:31:55
 */
public class TeamSeasonPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5523253635537412179L;
	private ContrastDiagram cd;
	private OneTeamDataTable table;
	
	public TeamSeasonPanel() {
		super();
		setTable();
//		NavLabel navLabel = new NavLabel(Constants.titleAvgData);
//		navLabel.setLocation(UIConfig.RELA_X, 190);
//		this.add(navLabel);
		
	}
	
	
	private void setTable() {
		table = new OneTeamDataTable();
		BottomScrollPane scrollPane = new BottomScrollPane(table);
		
		table.setHeaderColorAndFont();
		table.setWidth(new int[] {73, 50, 50, 50, 50, 55, 55, 55, 50, 50, 50, 50, 50, 50, 50, 50, 50});
		table.setRowHeight(35);
		table.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		
		scrollPane.setBounds(UIConfig.RELA_X, 180, UIConfig.TABLE_WID, 190);
		this.add(scrollPane);
	}


	public void updateContent(String season, TeamSeasonPO teamSeason, double [] fiveArgsAvg, double [] highestThree) {
		double[] fivePlayersData = new double[5];
		if (teamSeason == null) {
			fivePlayersData[0] = 0;
			fivePlayersData[1] = 0;
			fivePlayersData[2] = 0;
			fivePlayersData[3] = 0;
			fivePlayersData[4] = 0;
		}else {
			fivePlayersData[0] = teamSeason.getScoreAvg();
			fivePlayersData[1] = teamSeason.getTotalReboundAvg();
			fivePlayersData[2] = teamSeason.getTotalReboundAvg();
			fivePlayersData[3] = teamSeason.getFreethrowPercent();
			fivePlayersData[4] = teamSeason.getThreePointPercent();
		}
		
		if (cd == null) {
			/* 球隊的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
			cd = new ContrastDiagram(fivePlayersData, fiveArgsAvg, highestThree, Constants.teamAvgText);
			cd.setBounds(UIConfig.RELA_X, 16, UIConfig.TABLE_WID, 160);		//TODO y坐标
			this.add(cd);
			cd.updateUI();
			cd.repaint();
		}else {
			cd.setData(fivePlayersData, fiveArgsAvg, highestThree);
		}
		
		table.setVO(season, teamSeason);
	}
	
}
