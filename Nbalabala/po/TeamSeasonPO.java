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

}
