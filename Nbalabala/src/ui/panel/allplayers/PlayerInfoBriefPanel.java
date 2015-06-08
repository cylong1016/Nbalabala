package ui.panel.allplayers;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

import po.MatchPlayerPO;
import po.PlayerSeasonPO;
import ui.MyFont;
import ui.UIConfig;
import ui.common.label.NavLabel;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import utility.Constants;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月27日  下午6:07:28
 */
public class PlayerInfoBriefPanel extends Panel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -5772309236800084817L;
	private ContrastDiagram cd;
	
	public PlayerInfoBriefPanel(PlayerSeasonPO seasonVO, double[] fiveArgsAvg,
			double[] highestScoreReboundAssist, ArrayList<MatchPlayerPO> twoMatches) {
		addContrastDiagram(seasonVO, fiveArgsAvg, highestScoreReboundAssist);
		addLatestMatchesTable(twoMatches);
	}
	
	/**
	 * 最近两场比赛
	 * @param twoMatches
	 */
	private void addLatestMatchesTable(ArrayList<MatchPlayerPO> twoMatches) {
		// 表头
		JLabel two = new JLabel("2");
		two.setFont(MyFont.YT_L);
		two.setForeground(Color.white);
		two.setBounds(UIConfig.RELA_X + 58, 205, 150, 50);
		this.add(two);
		
		NavLabel navLabel = new NavLabel(Constants.lastestTwo);
		navLabel.setLocation(UIConfig.RELA_X, 214);
		this.add(navLabel);
		
		BottomScrollPane scrollPane = new OnePlayerMatchTableFactory(twoMatches).getTableScrollPane();
		scrollPane.setBounds(UIConfig.RELA_X, 254, UIConfig.TABLE_WID, 270); // 表格的位置 
		this.add(scrollPane);
	}
	
	public void updateContent(PlayerSeasonPO playerSeason, double[] fiveArgsAvg, 
			double[] highestScoreReboundAssist) {
		updateContrastDiagram(playerSeason, fiveArgsAvg, highestScoreReboundAssist);
	}
	
	
	/**
	 * 添加球员和联盟平均的比较图
	 * @author cylong
	 * @version 2015年4月11日 上午1:14:43
	 */
	private void addContrastDiagram(PlayerSeasonPO playerSeason, double[] fiveArgsAvg, 
			double[] highestScoreReboundAssist) {
		/* 球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
		double[] fivePlayersData = {playerSeason.getScoreAvg(), 
										playerSeason.getTotalReboundAvg(), playerSeason.getAssistAvg(),playerSeason.getFreethrowPercent(),
										playerSeason.getThreePointPercent()};
		cd = new ContrastDiagram(fivePlayersData, fiveArgsAvg, highestScoreReboundAssist, "球员平均");
		cd.setBounds(UIConfig.RELA_X, 16, UIConfig.TABLE_WID, 160);
		this.add(cd);
		cd.updateUI();
		cd.repaint();
	}

	/**
	 * 更新柱状图
	 * @author cylong
	 * @version 2015年4月12日 下午8:20:20
	 */
	private void updateContrastDiagram(PlayerSeasonPO playerSeason, double[] fiveArgsAvg, 
			double[] highestScoreReboundAssist) {
		/* 球员的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
		double[] fivePlayersData = {playerSeason.getScoreAvg(), playerSeason.getAssistAvg(),
										playerSeason.getTotalReboundAvg(), playerSeason.getFreethrowPercent(),
										playerSeason.getThreePointPercent()};
		cd.setData(fivePlayersData, fiveArgsAvg, highestScoreReboundAssist);
	}
	
}
