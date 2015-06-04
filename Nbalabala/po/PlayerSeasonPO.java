/**
 * 
 */
package po;

import java.sql.Date;

/**
 *
 * @author Issac Ding
 * @since 2015年5月31日 下午8:29:25
 * @version 1.0
 */
public class PlayerSeasonPO {
	
	public int latestMatchID;
	public Date latestMatchDate;
	
	/** 球员名字 */
	public String name;
	
	public String season;
	
	/** 位置，形如C 或者C-F */
	public String position;

	/** 球队缩写 */
	public String teamAbbr;
	
	/** 上场数 */
	public int matchCount;

	/** 首发数 */
	public int firstCount;
	
	/** 投篮命中 */
	public int fieldMade;

	/** 投篮出手 */
	public int fieldAttempt;

	/** 三分命中 */
	public int threePointMade;

	/** 三分出手 */
	public int threePointAttempt;

	/** 罚球命中 */
	public int freethrowMade;

	/** 罚球出手 */
	public int freethrowAttempt;

	/** 进攻篮板 */
	public int offensiveRebound;

	/** 防守篮板 */
	public int defensiveRebound;

	/** 总篮板 */
	public int totalRebound;

	/** 助攻 */
	public int assist;

	/** 抢断 */
	public int steal;

	/** 盖帽 */
	public int block;

	/** 失误 */
	public int turnover;

	/** 犯规 */
	public int foul;

	/** 个人得分 */
	public int score;

	/** 两双次数 */
	public int doubleDoubleCount;
	public double doubleDoubleAvg;
	
	public double fieldMadeAvg;
	public double fieldAttemptAvg;
	public double fieldPercent;
	public double threePointMadeAvg;
	public double threePointAttemptAvg;
	public double threePointPercent;
	public double freethrowMadeAvg;
	public double freethrowAttemptAvg;
	public double freethrowPercent;
	
	public double firstCountAvg;
	public double minutes;
	public double minutesAvg;
	public double offensiveReboundAvg;
	public double defensiveReboundAvg;
	public double totalReboundAvg;
	public double assistAvg;
	public double stealAvg;
	public double blockAvg;
	public double foulAvg;
	public double turnoverAvg;
	public double scoreAvg;
	
	public int efficiency;
	public double efficiencyAvg;
	public int scoreReboundAssist;
	public double scoreReboundAssistAvg;
	public double gmsc;
	public double gmscAvg;
	
	public double realFieldPercent;
	public double fieldEff;
	public double offensiveReboundPercent;
	public double defensiveReboundPercent;
	public double totalReboundPercent;
	public double stealPercent;
	public double blockPercent;
	public double turnOverPercent;
	public double foulPercent;
	public double usePercent;
	public double assistPercent;
	
	public PlayerSeasonPO(String name) {
		this.name = name;
	}
	
	public PlayerSeasonPO() {
	}
}
