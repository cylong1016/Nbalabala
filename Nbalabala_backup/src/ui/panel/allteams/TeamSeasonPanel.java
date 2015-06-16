package ui.panel.allteams;

import java.text.DecimalFormat;

import javax.swing.table.DefaultTableModel;

import ui.common.SeasonInputPanel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.panel.allplayers.ContrastDiagram;
import utility.Constants;
import vo.TeamSeasonVO;

/**
 * 球队赛季数据界面
 * @author lsy
 * @version 2015年4月13日  下午8:31:55
 */
public class TeamSeasonPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5523253635537412179L;
	private ContrastDiagram cd;
	/** 赛季选择器 */
	private SeasonInputPanel seasonInput;
	private OneTeamDataTable table;
	
	public TeamSeasonPanel(SeasonInputPanel seasonInput) {
		super();
		this.seasonInput = seasonInput;
		seasonInput.setLocation(25, 0);	//TODO y是随便写的
		this.add(seasonInput);
		
		table = new OneTeamDataTable();
		BottomScrollPane scrollPane = new BottomScrollPane(table);
		scrollPane.setBounds(25, 200, 888, 140);
		this.add(scrollPane);
	}
	
	
	public void updateContent(String season, TeamSeasonVO teamSeason, double [] fiveArgsAvg, double [] highestThree) {
		double[] fivePlayersData = {teamSeason.getScoreAvg(), teamSeason.getTotalReboundAvg(),
				teamSeason.getTotalReboundAvg(), teamSeason.getFreeThrowPercent(),
				teamSeason.getThreePointPercent()};
		if (cd == null) {
			/* 球隊的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
			cd = new ContrastDiagram(fivePlayersData, fiveArgsAvg, highestThree, Constants.teamAvgText);
			cd.setBounds(25, 80, 888, 160);		//TODO y坐标
			this.add(cd);
			cd.updateUI();
			cd.repaint();
		}else {
			cd.setData(fivePlayersData, fiveArgsAvg, highestThree);
		}
		
		table.setVO(season, teamSeason);
	}
	
	public void addSeasonChooser() {
		add(seasonInput);
	}

}
