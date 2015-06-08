package po;

/**
 * @author Issac Ding
 * @version 2015年3月14日 下午4:17:09
 */
public class TeamSeasonPO {
	
	/** 球队缩写 */
	public String abbr;
	
	/** 赛季 */
	public String season;

	/** 比赛场数 */
	public int matchCount;
	
	/** 胜场数 */
	public int wins;
	
	/** 胜率 */
	public double winning;

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

	/** 总得分 */
	public int score;

	
	public double fieldMadeAvg;
	public double fieldAttemptAvg;
	public double fieldPercent;
	public double threePointMadeAvg;
	public double threePointAttemptAvg;
	public double threePointPercent;
	public double freethrowMadeAvg;
	public double freethrowAttemptAvg;
	public double freethrowPercent;
	
	public double offensiveReboundAvg;
	public double defensiveReboundAvg;
	public double offensiveReboundEff;
	public double defensiveReboundEff;
	public double totalReboundAvg;
	public double assistAvg;
	public double stealAvg;
	public double blockAvg;
	public double foulAvg;
	public double turnoverAvg;
	public double scoreAvg;
	
	public double offensiveRound;
	public double offensiveRoundAvg;
	public double defensiveRound;
	public double defensiveRoundAvg;
	public double offensiveEff;
	public double defensiveEff;
	public double assistEff;
	public double stealEff;
	
	public double oppoFieldPercent;
	public double oppoScoreAvg;
	
	public int hashCode() {
		return abbr.hashCode();
    }
	
	public TeamSeasonPO(String abbr) {
		this.abbr = abbr;
	}
	
	public TeamSeasonPO() {
	}
	
	public TeamSeasonPO(String abbr, String season) {
		this.abbr = abbr;
		this.season = season;
	}

	public String getAbbr() {
		return abbr;
	}

	public String getSeason() {
		return season;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public int getWins() {
		return wins;
	}

	public double getWinning() {
		return winning;
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

	public double getOffensiveReboundAvg() {
		return offensiveReboundAvg;
	}

	public double getDefensiveReboundAvg() {
		return defensiveReboundAvg;
	}

	public double getOffensiveReboundEff() {
		return offensiveReboundEff;
	}

	public double getDefensiveReboundEff() {
		return defensiveReboundEff;
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

	public double getOffensiveRound() {
		return offensiveRound;
	}

	public double getOffensiveRoundAvg() {
		return offensiveRoundAvg;
	}

	public double getDefensiveRound() {
		return defensiveRound;
	}

	public double getDefensiveRoundAvg() {
		return defensiveRoundAvg;
	}

	public double getOffensiveEff() {
		return offensiveEff;
	}

	public double getDefensiveEff() {
		return defensiveEff;
	}

	public double getAssistEff() {
		return assistEff;
	}

	public double getStealEff() {
		return stealEff;
	}

	public double getOppoFieldPercent() {
		return oppoFieldPercent;
	}

	public double getOppoScoreAvg() {
		return oppoScoreAvg;
	}
	

}
