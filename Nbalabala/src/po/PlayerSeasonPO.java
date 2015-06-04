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

	public int getLatestMatchID() {
		return latestMatchID;
	}

	public Date getLatestMatchDate() {
		return latestMatchDate;
	}

	public String getName() {
		return name;
	}

	public String getSeason() {
		return season;
	}

	public String getPosition() {
		return position;
	}

	public String getTeamAbbr() {
		return teamAbbr;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public int getFirstCount() {
		return firstCount;
	}

	public int getFieldMade() {
		return fieldMade;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}

	public int getThreePointMade() {
		return threePointMade;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}

	public int getFreethrowMade() {
		return freethrowMade;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}

	public int getOffensiveRebound() {
		return offensiveRebound;
	}

	public int getDefensiveRebound() {
		return defensiveRebound;
	}

	public int getTotalRebound() {
		return totalRebound;
	}

	public int getAssist() {
		return assist;
	}

	public int getSteal() {
		return steal;
	}

	public int getBlock() {
		return block;
	}

	public int getTurnover() {
		return turnover;
	}

	public int getFoul() {
		return foul;
	}

	public int getScore() {
		return score;
	}

	public int getDoubleDoubleCount() {
		return doubleDoubleCount;
	}

	public double getDoubleDoubleAvg() {
		return doubleDoubleAvg;
	}

	public double getFieldMadeAvg() {
		return fieldMadeAvg;
	}

	public double getFieldAttemptAvg() {
		return fieldAttemptAvg;
	}

	public double getFieldPercent() {
		return fieldPercent;
	}

	public double getThreePointMadeAvg() {
		return threePointMadeAvg;
	}

	public double getThreePointAttemptAvg() {
		return threePointAttemptAvg;
	}

	public double getThreePointPercent() {
		return threePointPercent;
	}

	public double getFreethrowMadeAvg() {
		return freethrowMadeAvg;
	}

	public double getFreethrowAttemptAvg() {
		return freethrowAttemptAvg;
	}

	public double getFreethrowPercent() {
		return freethrowPercent;
	}

	public double getFirstCountAvg() {
		return firstCountAvg;
	}

	public double getMinutes() {
		return minutes;
	}

	public double getMinutesAvg() {
		return minutesAvg;
	}

	public double getOffensiveReboundAvg() {
		return offensiveReboundAvg;
	}

	public double getDefensiveReboundAvg() {
		return defensiveReboundAvg;
	}

	public double getTotalReboundAvg() {
		return totalReboundAvg;
	}

	public double getAssistAvg() {
		return assistAvg;
	}

	public double getStealAvg() {
		return stealAvg;
	}

	public double getBlockAvg() {
		return blockAvg;
	}

	public double getFoulAvg() {
		return foulAvg;
	}

	public double getTurnoverAvg() {
		return turnoverAvg;
	}

	public double getScoreAvg() {
		return scoreAvg;
	}

	public int getEfficiency() {
		return efficiency;
	}

	public double getEfficiencyAvg() {
		return efficiencyAvg;
	}

	public int getScoreReboundAssist() {
		return scoreReboundAssist;
	}

	public double getScoreReboundAssistAvg() {
		return scoreReboundAssistAvg;
	}

	public double getGmsc() {
		return gmsc;
	}

	public double getGmscAvg() {
		return gmscAvg;
	}

	public double getRealFieldPercent() {
		return realFieldPercent;
	}

	public double getFieldEff() {
		return fieldEff;
	}

	public double getOffensiveReboundPercent() {
		return offensiveReboundPercent;
	}

	public double getDefensiveReboundPercent() {
		return defensiveReboundPercent;
	}

	public double getTotalReboundPercent() {
		return totalReboundPercent;
	}

	public double getStealPercent() {
		return stealPercent;
	}

	public double getBlockPercent() {
		return blockPercent;
	}

	public double getTurnOverPercent() {
		return turnOverPercent;
	}

	public double getFoulPercent() {
		return foulPercent;
	}

	public double getUsePercent() {
		return usePercent;
	}

	public double getAssistPercent() {
		return assistPercent;
	}
}
