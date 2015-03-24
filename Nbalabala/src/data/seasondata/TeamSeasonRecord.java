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
	int score; 
	
	/** 胜场数 */
	int wins;
	
	//以下记录对手的总数据，以计算各种复杂的率
	
	int oppoDefensiveRebound;
	
	int oppoOffensiveRebound;
	
	int oppoFieldAttempt;
	
	int oppoFreethrowAttempt;
	
	int oppoFieldGoal;
	
	int oppoTurnover;
	
	int oppoGoal;
	
	public TeamSeasonRecord(String name){
		this.teamName = name;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public int getWins() {
		return this.wins;
	}
	
	public int getLoses() {
		return matchCount - wins;
	}
	
	public int getMatchCount() {
		return matchCount;
	}
	
	public double getWinning(){
		return (double)wins / matchCount;
	}

	public int getFieldGoal() {
		return fieldGoal;
	}
	
	public double getFieldGoalAvg() {
		return (double)fieldGoal / matchCount;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}
	
	public double getFieldAttemptAvg() {
		return (double)fieldAttempt/matchCount;
	}
	
	public double getFieldPercent(){
		return (double)fieldGoal / fieldAttempt;
	}
	
	public int getThreePointGoal() {
		return threePointGoal;
	}
	
	public double getThreePointGoalAvg() {
		return (double)threePointGoal / matchCount;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}
	
	public double getThreePointAttemptAvg() {
		return (double)threePointAttempt / matchCount;
	}
	
	public double getThreePointPercent(){
		return (double)threePointGoal/threePointAttempt;
	}

	public int getFreethrowGoal() {
		return freethrowGoal;
	}
	
	public double getFreethrowGoalAvg() {
		return (double)freethrowGoal / matchCount;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}
	
	public double getFreethrowAttemptAvg() {
		return (double)freethrowAttempt / matchCount;
	}
	
	public double getFreeThrowPercent(){
		return (double)freethrowGoal/freethrowAttempt;
	}

	public int getOffensiveRebound() {
		return offensiveRebound;
	}
	
	public double getOffensiveReboundAvg() {
		return (double)offensiveRebound / matchCount;
	}

	public int getDefensiveRebound() {
		return defensiveRebound;
	}
	
	public double getDefensiveReboundAvg() { 
		return (double)defensiveRebound / matchCount;
	}

	public int getTotalRebound() {
		return totalRebound;
	}
	
	public double getTotalReboundAvg(){
		return (double)totalRebound / matchCount;
	}
	
	public double getOffensiveReboundEff() {
		return (double)offensiveRebound / (offensiveRebound + oppoDefensiveRebound);
	}
	
	public double getDefensiveReboundEff() {
		return (double)defensiveRebound / (defensiveRebound + oppoOffensiveRebound);
	}

	public int getAssist() {
		return assist;
	}
	
	public double getAssistAvg() {
		return (double)assist / matchCount;
	}

	public int getSteal() {
		return steal;
	}
	
	public double getStealAvg() {
		return (double)steal / matchCount;
	}

	public int getBlock() {
		return block;
	}
	
	public double getBlockAvg() {
		return (double)block / matchCount;
	}

	public int getTurnover() {
		return turnover;
	}
	
	public double getTurnoverAvg() {
		return (double)turnover / matchCount;
	}

	public int getFoul() {
		return foul;
	}
	
	public double getFoulAvg() {
		return (double)foul / matchCount;
	}

	public int getScore() {
		return score;
	}
	
	public double getScoreAvg() {
		return (double)score / matchCount;
	}
	
	public double getOffensiveRound() {
		return fieldAttempt + 0.4 * (freethrowAttempt) - 1.07 *
				((double)offensiveRebound / (offensiveRebound  + oppoDefensiveRebound) * 
						(fieldAttempt - fieldGoal))+ 1.07*turnover;
	}
	
	public double getOffensiveRoundAvg(){
		return getOffensiveRound() / matchCount;
	}
	
	public double getOffensiveEff() {
		return score / getOffensiveRound() * 100;
	}
	
	public double getDefensiveRound() {
		return oppoFieldAttempt + 0.4 * (oppoFreethrowAttempt) - 
				1.07 * ((double)oppoOffensiveRebound / (oppoOffensiveRebound  + defensiveRebound) * 
						(oppoFieldAttempt - oppoFieldGoal))+ 1.07*oppoTurnover;
	}
	
	public double getDefensiveRoundAvg() {
		return getDefensiveRound() / matchCount;
	}
	
	public double getDefensiveEff() {
		return oppoGoal / getDefensiveRound() * 100;
	}

	public double getStealEff() {
		return steal / getDefensiveRound() * 100;
	}

	public double getAssistEff() {
		return assist / getOffensiveRound() * 100;
	}
	
	
}
