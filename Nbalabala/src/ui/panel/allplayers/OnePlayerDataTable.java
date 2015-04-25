package ui.panel.allplayers;

import java.text.DecimalFormat;

import ui.UIConfig;
import ui.common.table.BottomTable;
import utility.Constants;
import vo.PlayerSeasonVO;

/**
 * 球员个人页面里面那个展示其赛季基本数据的表格
 * @author Issac Ding
 * @version 2015年4月24日  下午5:50:30
 */
public class OnePlayerDataTable extends BottomTable{
	
	public OnePlayerDataTable(){
		super(Constants.onePlayerDataTableEmptyContent, Constants.onePlayerDataHeaders);
		cancelVerticalLines();
	}
	
	public OnePlayerDataTable(String season, PlayerSeasonVO seasonVO) {
		this();
		setVO(season, seasonVO);
	}
	
	/** 切换赛季以后，使用此方法更新该表格 */
	public void setVO(String season, PlayerSeasonVO vo) {
		
		DecimalFormat percentDf = UIConfig.PERCENT_FORMAT;
		
		setValueAt(season, 1, 0);
		setValueAt(vo.matchCount, 1, 1);
		setValueAt(vo.firstCount, 1, 2);
		setValueAt(vo.getTime(), 1, 3);
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
		
		setValueAt(season, 2, 0);
		setValueAt(vo.matchCount, 2, 1);
		setValueAt(df.format(vo.firstCountAvg), 2, 2);
		setValueAt(vo.getTimeAvg(), 2, 3);
		setValueAt(percentDf.format(vo.fieldPercent), 2, 4);
		setValueAt(percentDf.format(vo.threePointPercent), 2, 5);
		setValueAt(percentDf.format(vo.freethrowPercent), 2, 6);
		setValueAt(df.format(vo.offensiveReboundAvg), 2, 7);
		setValueAt(df.format(vo.defensiveReboundAvg), 2, 8);
		setValueAt(df.format(vo.totalReboundAvg), 2, 9);
		setValueAt(df.format(vo.assistAvg), 2, 10);
		setValueAt(df.format(vo.stealAvg), 2, 11);
		setValueAt(df.format(vo.blockAvg), 2, 12);
		setValueAt(df.format(vo.turnoverAvg), 2, 13);
		setValueAt(df.format(vo.foulAvg), 2, 14);
		setValueAt(df.format(vo.scoreAvg), 2, 15);
	}
}
