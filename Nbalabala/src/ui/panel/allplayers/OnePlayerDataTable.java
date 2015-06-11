package ui.panel.allplayers;

import java.text.DecimalFormat;

import po.PlayerSeasonPO;
import po.TeamSeasonPO;
import ui.UIConfig;
import ui.common.table.BottomTable;
import utility.Constants;

/**
 * 球员个人页面里面那个展示其赛季基本数据的表格
 * @author Issac Ding
 * @version 2015年4月24日  下午5:50:30
 */
public class OnePlayerDataTable extends BottomTable{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -3205098453283073458L;

	public OnePlayerDataTable(){
		super(Constants.onePlayerDataTableEmptyContent, Constants.onePlayerDataHeaders);
//		cancelVerticalLines();
	}
	
	public OnePlayerDataTable(String season, PlayerSeasonPO seasonPO, TeamSeasonPO teamPO) {
		this();
		setVO(season, seasonPO, teamPO);
	}
	
	/** 切换赛季以后，使用此方法更新该表格 */
	public void setVO(String season, PlayerSeasonPO vo, TeamSeasonPO team) {
		
		DecimalFormat percentDf = UIConfig.PERCENT_FORMAT;
		
		setValueAt(season, 1, 0);
		setValueAt(vo.matchCount, 1, 1);
		setValueAt(vo.firstCount, 1, 2);
		setValueAt(UIConfig.FORMAT.format(vo.minutes), 1, 3);
		setValueAt(percentDf.format(vo.fieldPercent), 1, 4);
		setValueAt(percentDf.format(vo.threePointPercent), 1, 5);
		setValueAt(percentDf.format(vo.freethrowPercent), 1, 6);
		setValueAt(vo.offensiveRebound, 1, 7);
		setValueAt(vo.defensiveRebound, 1, 8);
		setValueAt(vo.totalRebound, 1, 9);
		setValueAt(vo.assist, 1, 10);
		setValueAt(vo.steal, 1, 11);
		setValueAt(vo.block, 1, 12);
		setValueAt(vo.turnover, 1, 13);
		setValueAt(vo.foul, 1, 14);
		setValueAt(vo.score, 1, 15);
		
		DecimalFormat df = UIConfig.FORMAT;
		
		setValueAt(season, 3, 0);
		setValueAt(vo.matchCount, 3, 1);
		setValueAt(df.format(vo.firstCountAvg), 3, 2);
		setValueAt(UIConfig.FORMAT.format(vo.minutesAvg), 3, 3);
		setValueAt(percentDf.format(vo.fieldPercent), 3, 4);
		setValueAt(percentDf.format(vo.threePointPercent), 3, 5);
		setValueAt(percentDf.format(vo.freethrowPercent), 3, 6);
		setValueAt(df.format(vo.offensiveReboundAvg), 3, 7);
		setValueAt(df.format(vo.defensiveReboundAvg), 3, 8);
		setValueAt(df.format(vo.totalReboundAvg), 3, 9);
		setValueAt(df.format(vo.assistAvg), 3, 10);
		setValueAt(df.format(vo.stealAvg), 3, 11);
		setValueAt(df.format(vo.blockAvg), 3, 12);
		setValueAt(df.format(vo.turnoverAvg), 3, 13);
		setValueAt(df.format(vo.foulAvg), 3, 14);
		setValueAt(df.format(vo.scoreAvg), 3, 15);
		
		setValueAt(season, 5, 0);
		setValueAt(team.matchCount, 5, 1);
		setValueAt(" ", 5, 2);
		setValueAt(" ", 5, 3);
		setValueAt(percentDf.format(team.fieldPercent), 5, 4);
		setValueAt(percentDf.format(team.threePointPercent), 5, 5);
		setValueAt(percentDf.format(team.freethrowPercent), 5, 6);
		setValueAt(df.format(team.offensiveReboundAvg), 5, 7);
		setValueAt(df.format(team.defensiveReboundAvg), 5, 8);
		setValueAt(df.format(team.totalReboundAvg), 5, 9);
		setValueAt(df.format(team.assistAvg), 5, 10);
		setValueAt(df.format(team.stealAvg), 5, 11);
		setValueAt(df.format(team.blockAvg), 5, 12);
		setValueAt(df.format(team.turnoverAvg), 5, 13);
		setValueAt(df.format(team.foulAvg), 5, 14);
		setValueAt(df.format(team.scoreAvg), 5, 15);
	}
}
