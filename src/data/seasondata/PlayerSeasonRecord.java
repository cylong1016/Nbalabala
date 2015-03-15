package data.seasondata;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月14日  下午4:17:44
 */
public class PlayerSeasonRecord {
	
	//在遍历比赛信息的过程中不记录球员的球队和位置
	//因为在查看球员赛季数据的时候不显示球队，而且位置以player文件夹中的为准

	
	/** 球员名字 */
	String name;
	
	int matchCount;
	
	int firstCount;

	/** 时间，单位是秒 */
	int time;
	
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
	
	/** 个人得分 */
	int personalGoal;
	
	
	//以下记录球队和对手数据以计算各种率
	
	/** 球队总上场时间 ，单位为秒*/
	int teamTime;
	
	int teamOffensiveRebound;
	
	int teamDefensiveRebound;
	
	
	/** 球队总篮板 */
	int teamTotalRebound;
	
	
	
	int teamFieldGoal;
	
	int teamFieldAttempt;
	
	int teamFreethrowAttempt;
	
	int teamFoul;
	
	/** 对手总篮板*/
	int oppoTotalRebound;
	
	int oppoOffensiveRebound;
	
	int oppoDefensiveRebound;
	
	int oppoFieldGoal;
	
	int oppoFieldAttempt;
	
	int oppoFreethrowAttempt;
	
	int oppoFoul;
	
	int oppoThreePointAttempt;

	public String getName() {
		return name;
	}

	public int getMatchCount() {
		return matchCount;
	}
	
	public int getFirstCount() {
		return firstCount;
	}
	
	public double getFirstCountAvg() {
		return firstCount / matchCount;
	}

	public int getTime() {
		return time;
	}
	
	public double getTimeAvg() {
		return time / matchCount;
	}

	public int getFieldGoal() {
		return fieldGoal;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}
	
	public double getFieldPercent() {
		return fieldGoal / fieldAttempt;
	}

	public int getThreePointGoal() {
		return threePointGoal;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}
	
	public double getThreePointPercent() {
		return threePointGoal / threePointAttempt;
	}

	public int getFreethrowGoal() {
		return freethrowGoal;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}
	
	public double getFreethrowPercent() {
		return freethrowGoal / freethrowAttempt;
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

	public int getPersonalGoal() {
		return personalGoal;
	}
	
	public int getEfficiency(){
		return personalGoal + totalRebound + assist + steal + block - fieldAttempt + fieldGoal 
				- freethrowAttempt + freethrowGoal - turnover;
	}
	
	public double getGmSc(){
		return personalGoal + 0.4 * fieldGoal - 0.7 * fieldAttempt - 0.4 * (freethrowAttempt - 
				freethrowGoal) + 0.7 * offensiveRebound + 0.3 * defensiveRebound + 
				steal + 0.7 * assist + 0.7 * block - 0.4 * foul - turnover; 
	}
	
	public double getRealFieldPercent() {
		return personalGoal / (2 * (fieldAttempt + 0.44 * freethrowAttempt)); 
	}
	
	public double getFieldEff() {
		return (fieldGoal + 0.5 * threePointGoal) / fieldAttempt;
	}
	
	public double getTotalReboundPercent() {
		return totalRebound * (teamTime / 5) / time / (teamTotalRebound + oppoTotalRebound);
	}
	
	public double getOffensiveReboundPercent() {
		return offensiveRebound * (teamTime / 5) / time / (teamOffensiveRebound + oppoOffensiveRebound);
	}
	
	public double getDefensiveReboundPercent() {
		return defensiveRebound * (teamTime / 5) / time / (teamDefensiveRebound + oppoDefensiveRebound);
	}
	
	public double getAssistPercent() {
		return assist / (time / (teamTime / 5) * teamFieldGoal - fieldGoal); 
	}
	
	public double getStealPercent() {
		double oppoAttack = oppoFieldAttempt + 0.4 * oppoFreethrowAttempt - 1.07 *(oppoOffensiveRebound /
				(oppoOffensiveRebound + teamDefensiveRebound) * (oppoFieldAttempt - oppoFieldGoal))
				+ 1.07 * oppoFoul; 
		return steal * (teamTime / 5) / time / oppoAttack; 
	}
	
	public double getBlockPercent() {
		return block * (teamTime / 5)/time / (oppoFieldAttempt - oppoThreePointAttempt);
	}
	
	public double getFouldPercent() {
		return foul / (fieldAttempt - threePointAttempt + 0.44 * freethrowAttempt + foul); 
	}
	
	public double getUsePercent() {
		return (fieldAttempt + 0.44 * freethrowAttempt + foul) * (teamTime / 5) / time /
				(teamFieldAttempt + 0.44 * teamFreethrowAttempt + teamFoul); 
	}
	
	
}
