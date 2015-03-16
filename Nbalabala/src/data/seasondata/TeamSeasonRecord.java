/**
 * 
 * @author Issac Ding
 * @version 下午4:17:09
 */
package data.seasondata;


/**
 * 
 * @author Issac Ding
 * @version 2015年3月14日  下午4:17:09
 */
public class TeamSeasonRecord {
	
	/** 队名 */
	String teamName;
	
	/** 比赛场数 */
	int matchCount;
	
	/** 投篮命中 */
	int fieldGoal;
	
	/** 投篮出手 */
	int fieldAttempt;
	
	/** 三分命中 */
	int threePointGoal;
	
	/** 三分出手 */
	int threePointAttempt;
	
	/** 罚球命中 */
	int freethrowGoal;
	
	/** 罚球出手 */
	int freethrowAttempt;
	
	/** 进攻篮板 */
	int offensiveRebound;
	
	/** 防守篮板 */
	int defensiveRebound;
	
	/** 总篮板 */
	int totalRebound;
	
	/** 助攻 */
	int assist;
	
	/** 抢断 */
	int steal;
	
	/** 盖帽 */
	int block;
	
	/** 失误 */
	int turnover;
	
	/** 犯规 */
	int foul;
	
	/** 总得分 */
	int teamGoal; 
	
	/** 胜场数 */
	double wins;
	
	/** 失败场数 */
	double loses;

	//以下记录对手的总数据，以计算各种复杂的率
	
	int oppoDefensiveRebound;
	
	int oppoOffensiveRebound;
	
	int oppoFieldAttempt;
	
	int oppoFreethrowAttempt;
	
	int oppoFieldGoal;
	
	int oppoTurnover;
	
	int oppoGoal;
	
	private double oppoRound() {
		return oppoFieldAttempt + 0.4 * (oppoFreethrowAttempt) - 
				1.07 * (oppoOffensiveRebound / (oppoOffensiveRebound  + defensiveRebound) * 
						(oppoFieldAttempt - oppoFieldGoal))+ 1.07*oppoTurnover;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public int getFieldGoal() {
		return fieldGoal;
	}
	
	public double getFieldGoalAvg() {
		return fieldGoal / matchCount;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}
	
	public double getFieldAttemptAvg() {
		return fieldAttempt/matchCount;
	}
	
	public int getThreePointGoal() {
		return threePointGoal;
	}
	
	public double getThreePointGoalAvg() {
		return threePointGoal / matchCount;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}
	
	public double getThreePointAttemptAvg() {
		return threePointAttempt / matchCount;
	}

	public int getFreethrowGoal() {
		return freethrowGoal;
	}
	
	public double getFreethrowGoalAvg() {
		return freethrowGoal / matchCount;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}
	
	public double getFreethrowAttempAvg() {
		return freethrowAttempt / matchCount;
	}

	public int getOffensiveRebound() {
		return offensiveRebound;
	}
	
	public double getOffensiveReboundAvg() {
		return offensiveRebound / matchCount;
	}

	public int getDefensiveRebound() {
		return defensiveRebound;
	}
	
	public double getDefensiveReboundAvg() { 
		return defensiveRebound / matchCount;
	}

	public int getTotalRebound() {
		return totalRebound;
	}
	
	public double getTotalReboundAvg(){
		return totalRebound / matchCount;
	}

	public int getAssist() {
		return assist;
	}
	
	public double getAssistAvg() {
		return assist / matchCount;
	}

	public int getSteal() {
		return steal;
	}
	
	public double getStealAvg() {
		return steal / matchCount;
	}

	public int getBlock() {
		return block;
	}
	
	public double getBlockAvg() {
		return block / matchCount;
	}

	public int getTurnover() {
		return turnover;
	}
	
	public double getTurnoverAvg() {
		return turnover / matchCount;
	}

	public int getFoul() {
		return foul;
	}
	
	public double getFoulAvg() {
		return foul / matchCount;
	}

	public int getTeamGoal() {
		return teamGoal;
	}
	
	public double getTeamGoalAvg() {
		return teamGoal / matchCount;
	}
	
	public double getFieldPercent(){
		return fieldGoal / fieldAttempt;
	}
	
	public double getThreePointPercent(){
		return threePointGoal/threePointAttempt;
	}
	
	public double getFreeThrowPercent(){
		return freethrowGoal/freethrowAttempt;
	}
	
	public double getWinning(){
		return wins / loses;
	}

	public double getRound() {
		return fieldAttempt + 0.4 * (freethrowAttempt) - 1.07 *
				(offensiveRebound / (offensiveRebound  + oppoDefensiveRebound) * 
						(fieldAttempt - fieldGoal))+ 1.07*turnover;
	}
	
	public double getRoundAvg(){
		return getRound() / matchCount;
	}
	
	public double getOffensiveEff() {
		return teamGoal / getRound() * 100;
	}

	public double getDefensiveEff() {
		return oppoGoal / oppoRound() * 100;
	}

	public double getOffensiveReboundEff() {
		return offensiveRebound / (offensiveRebound + oppoDefensiveRebound);
	}
	
	public double getDefensiveReboundEff() {
		return defensiveRebound / (defensiveRebound + oppoOffensiveRebound);
	}

	public double getStealEff() {
		return steal / oppoRound() * 100;
	}

	public double getAssistEff() {
		return assist / getRound() * 100;
	}
}
