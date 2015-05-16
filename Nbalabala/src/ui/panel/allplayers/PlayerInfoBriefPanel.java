package ui.panel.allplayers;

import java.util.ArrayList;

import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import vo.PlayerMatchPerformanceVO;
import vo.PlayerSeasonVO;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月27日  下午6:07:28
 */
public class PlayerInfoBriefPanel extends Panel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -5772309236800084817L;
	private ContrastDiagram cd;
	
	public PlayerInfoBriefPanel(PlayerSeasonVO seasonVO, double[] fiveArgsAvg,
			double[] highestScoreReboundAssist, ArrayList<PlayerMatchPerformanceVO> twoMatches) {
		addContrastDiagram(seasonVO, fiveArgsAvg, highestScoreReboundAssist);
		addLatestMatchesTable(twoMatches);
	}
	
	private void addLatestMatchesTable(ArrayList<PlayerMatchPerformanceVO> twoMatches) {
		BottomScrollPane scrollPane = new OnePlayerMatchTableFactory(twoMatches).getTableScrollPane();
		scrollPane.setBounds(25, 250, 888, 270); // 表格的位置 
		this.add(scrollPane);
	}
	
	public void updateContent(PlayerSeasonVO playerSeason, double[] fiveArgsAvg, 
			double[] highestScoreReboundAssist) {
		updateContrastDiagram(playerSeason, fiveArgsAvg, highestScoreReboundAssist);
	}
	
	
	/**
	 * 添加球员和联盟平均的比较图
	 * @author cylong
	 * @version 2015年4月11日 上午1:14:43
	 */
	private void addContrastDiagram(PlayerSeasonVO playerSeason, double[] fiveArgsAvg, 
			double[] highestScoreReboundAssist) {
		/* 球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
		double[] fivePlayersData = {playerSeason.getScoreAvg(), 
										playerSeason.getTotalReboundAvg(), playerSeason.getAssistAvg(),playerSeason.getFreeThrowPercent(),
										playerSeason.getThreePointPercent()};
		cd = new ContrastDiagram(fivePlayersData, fiveArgsAvg, highestScoreReboundAssist, "球员平均");
		cd.setBounds(25, 0, 888, 160);
		this.add(cd);
		cd.updateUI();
		cd.repaint();
	}

	/**
	 * 更新柱状图
	 * @author cylong
	 * @version 2015年4月12日 下午8:20:20
	 */
	private void updateContrastDiagram(PlayerSeasonVO playerSeason, double[] fiveArgsAvg, 
			double[] highestScoreReboundAssist) {
		/* 球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
		double[] fivePlayersData = {playerSeason.getScoreAvg(), playerSeason.getAssistAvg(),
										playerSeason.getTotalReboundAvg(), playerSeason.getFreeThrowPercent(),
										playerSeason.getThreePointPercent()};
		cd.setData(fivePlayersData, fiveArgsAvg, highestScoreReboundAssist);
	}
	
}
