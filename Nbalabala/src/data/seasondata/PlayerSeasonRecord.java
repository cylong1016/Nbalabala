package data.seasondata;

/**
 * 用来记录计算球员赛季数据所需的各种指标
 * @author Issac Ding
 * @version 2015年3月14日 下午4:17:44
 */
public class PlayerSeasonRecord {

	/**
	 * attempt是出手
	 * goal命中
	 * avg场均
	 */

	/** 球员名字 */
	String name;

	/** 记录最后一次位置 */
	char position = '\0';

	/** 记录最后一次比赛时的队伍 */
	String teamName = "无记录";

	/** 上场数 */
	int matchCount;

	/** 首发数 */
	int firstCount;

	/** 时间，单位是秒 */
	double time;

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
	int score;

	/** 两双次数 */
	int doubleDoubleCount;

	//以下记录球队和对手数据以计算各种率

	/** 球队总上场时间 ，单位为秒 */
	double teamTime;

	int teamOffensiveRebound;

	int teamDefensiveRebound;

	/** 球队总篮板 */
	int teamTotalRebound;

	int teamFieldGoal;

	int teamFieldAttempt;

	int teamFreethrowAttempt;

	int teamTurnover;

	/** 对手总篮板 */
	int oppoTotalRebound;

	int oppoOffensiveRebound;

	int oppoDefensiveRebound;

	int oppoFieldGoal;

	int oppoFieldAttempt;

	int oppoFreethrowAttempt;

	int oppoTurnover;

	int oppoThreePointAttempt;

	/** 如果没有关于此球员的比赛记录，但是在球员信息里有这个球员的话，用这个构造方法产生一个空的赛季纪录 */
	public PlayerSeasonRecord(String name) {
		this.name = name;
	}

	public PlayerSeasonRecord() {

	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return teamName;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public int getFirstCount() {
		return firstCount;
	}

	public double getFirstCountAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)firstCount / matchCount;
	}

	public String getTime() {
		int timeInt = (int)time;
		int minutes = timeInt / 60;
		int seconds = timeInt % 60;
		return minutes + ":" + seconds;
	}

	public String getTimeAvg() {
		int timeInt = (int)(time / matchCount);
		int minutes = timeInt / 60;
		int seconds = timeInt % 60;
		return minutes + ":" + seconds;
	}

	public int getFieldGoal() {
		return fieldGoal;
	}

	public double getFieldGoalAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)fieldGoal / matchCount;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}

	public double getFieldAttemptAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)fieldAttempt / matchCount;
	}

	public double getFieldPercent() {
		if (fieldAttempt == 0) {
			return 0;
		}
		return (double)fieldGoal / fieldAttempt;
	}

	public int getThreePointGoal() {
		return threePointGoal;
	}

	public double getThreePointGoalAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)threePointGoal / matchCount;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}

	public double getThreePointAttemptAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)threePointAttempt / matchCount;
	}

	public double getThreePointPercent() {
		if (threePointAttempt == 0) {
			return 0; // 防止除数为0
		}
		return (double)threePointGoal / threePointAttempt;
	}

	public int getFreeThrowGoal() {
		return freethrowGoal;
	}

	public double getFreethrowGoalAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)freethrowGoal / matchCount;
	}

	public int getFreeThrowAttempt() {
		return freethrowAttempt;
	}

	public double getFreethrowAttemptAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)freethrowAttempt / matchCount;
	}

	public double getFreeThrowPercent() {
		if(freethrowAttempt == 0) {
			return 0;
		}
		return (double)freethrowGoal / freethrowAttempt;
	}

	public int getOffensiveRebound() {
		return offensiveRebound;
	}

	public double getOffensiveReboundAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)offensiveRebound / matchCount;
	}

	public int getDefensiveRebound() {
		return defensiveRebound;
	}

	public double getDefensiveReboundAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)defensiveRebound / matchCount;
	}

	public int getTotalRebound() {
		return totalRebound;
	}

	public double getTotalReboundAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)totalRebound / matchCount;
	}

	public int getAssist() {
		return assist;
	}

	public double getAssistAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)assist / matchCount;
	}

	public int getSteal() {
		return steal;
	}

	public double getStealAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)steal / matchCount;
	}

	public int getBlock() {
		return block;
	}

	public double getBlockAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)block / matchCount;
	}

	public int getTurnover() {
		return turnover;
	}

	public double getTurnoverAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)turnover / matchCount;
	}

	public int getFoul() {
		return foul;
	}

	public double getFoulAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)foul / matchCount;
	}

	public int getScore() {
		return score;
	}

	public double getScoreAvg() {
		if(matchCount == 0) {
			return 0;
		}
		return (double)score / matchCount;
	}

	public int getEfficiency() {
		return score + totalRebound + assist + steal + block - fieldAttempt + fieldGoal - freethrowAttempt
				+ freethrowGoal - turnover;
	}

	public double getGmSc() {
		return score + 0.4 * fieldGoal - 0.7 * fieldAttempt - 0.4 * (freethrowAttempt - freethrowGoal) + 0.7
				* offensiveRebound + 0.3 * defensiveRebound + steal + 0.7 * assist + 0.7 * block - 0.4 * foul
				- turnover;
	}

	public double getRealFieldPercent() {
		if (fieldAttempt == 0) {
			return 0;
		}
		return score / (2 * (fieldAttempt + 0.44 * freethrowAttempt));
	}

	public double getFieldEff() {
		if (fieldAttempt == 0) {
			return 0;
		}
		return (fieldGoal + 0.5 * threePointGoal) / fieldAttempt;
	}

	public double getOffensiveReboundPercent() {
		if (matchCount == 0) {
			return 0;
		}
		return offensiveRebound * (teamTime / 5) / time / (teamOffensiveRebound + oppoOffensiveRebound);
	}

	public double getDefensiveReboundPercent() {
		if (matchCount == 0) {
			return 0;
		}
		return defensiveRebound * (teamTime / 5) / time / (teamDefensiveRebound + oppoDefensiveRebound);
	}

	public double getTotalReboundPercent() {
		if (matchCount == 0) {
			return 0;
		}
		return totalRebound * (teamTime / 5) / time / (teamTotalRebound + oppoTotalRebound);
	}

	public double getAssistPercent() {
		if (matchCount == 0) {
			return 0;
		}
		return assist / (time / (teamTime / 5) * teamFieldGoal - fieldGoal);
	}

	public double getStealPercent() {
		double oppoAttack = oppoFieldAttempt + 0.4 * oppoFreethrowAttempt - 1.07
									* ((double)oppoOffensiveRebound / (oppoOffensiveRebound + teamDefensiveRebound)
									* (oppoFieldAttempt - oppoFieldGoal)) + 1.07 * oppoTurnover;
		if(matchCount == 0 || oppoAttack == 0) {
			return 0;
		}
		return steal * (teamTime / 5) / time / oppoAttack;
	}

	public double getBlockPercent() {
		if (matchCount == 0) {
			return 0;
		}
		return block * (teamTime / 5) / time / (oppoFieldAttempt - oppoThreePointAttempt);
	}

	public double getTurnOverPercent() {
		double temp = (fieldAttempt - threePointAttempt + 0.44 * freethrowAttempt + turnover);
		if(temp == 0) {
			return 0;
		}
		return turnover / temp;
	}

	public double getFoulPercent() {
		if (matchCount == 0) {
			return 0;
		}
		double temp = (fieldAttempt - threePointAttempt + 0.44 * freethrowAttempt + foul);
		if (temp == 0) return 0;
		return foul / temp;
	}

	public double getUsePercent() {
		if (matchCount == 0) {
			return 0;
		}
		return (fieldAttempt + 0.44 * freethrowAttempt + turnover) * (teamTime / 5) / time
				/ (teamFieldAttempt + 0.44 * teamFreethrowAttempt + teamTurnover);
	}

	public char getPosition() {
		return position;
	}

	public int getDoubleDouble() {
		return doubleDoubleCount;
	}

	public double getDoubleDoubleAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)doubleDoubleCount / matchCount;
	}

	/** 三项相加 */
	public int getScoreReboundAssist() {
		return score + totalRebound + assist;
	}

	public double getScoreReboundAssistAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)getScoreReboundAssist() / matchCount;
	}

	public double getTimeDouble() {
		return time;
	}

	public double getTimeDoubleAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return time / matchCount;
	}
}
