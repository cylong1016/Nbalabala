package vo;

/**
 * @author Issac Ding
 * @version 2015年3月14日 下午4:17:09
 */
public class TeamSeasonVO {
	
	/** 队名 */
	public String teamName;

	/** 比赛场数 */
	public int matchCount;

	/** 投篮命中 */
	public int fieldGoal;

	/** 投篮出手 */
	public int fieldAttempt;

	/** 三分命中 */
	public int threePointGoal;

	/** 三分出手 */
	public int threePointAttempt;

	/** 罚球命中 */
	public int freethrowGoal;

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

	/** 胜场数 */
	public int wins;

	//以下记录对手的总数据，以计算各种复杂的率

	public int oppoDefensiveRebound;

	public int oppoOffensiveRebound;

	public int oppoFieldAttempt;

	public int oppoFreethrowAttempt;

	public int oppoFieldGoal;

	public int oppoTurnover;

	public int oppoGoal;

	public TeamSeasonVO(String name) {
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

	public double getWinning() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)wins / matchCount;
	}

	public int getFieldGoal() {
		return fieldGoal;
	}

	public double getFieldGoalAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)fieldGoal / matchCount;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}

	public double getFieldAttemptAvg() {
		if (matchCount == 0) {
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
		if (matchCount == 0) {
			return 0;
		}
		return (double)threePointGoal / matchCount;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}

	public double getThreePointAttemptAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)threePointAttempt / matchCount;
	}

	public double getThreePointPercent() {
		if (threePointAttempt == 0) {
			return 0;
		}
		return (double)threePointGoal / threePointAttempt;
	}

	public int getFreethrowGoal() {
		return freethrowGoal;
	}

	public double getFreethrowGoalAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)freethrowGoal / matchCount;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}

	public double getFreethrowAttemptAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)freethrowAttempt / matchCount;
	}

	public double getFreeThrowPercent() {
		if (freethrowAttempt == 0) {
			return 0;
		}
		return (double)freethrowGoal / freethrowAttempt;
	}

	public int getOffensiveRebound() {
		return offensiveRebound;
	}

	public double getOffensiveReboundAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)offensiveRebound / matchCount;
	}

	public int getDefensiveRebound() {
		return defensiveRebound;
	}

	public double getDefensiveReboundAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)defensiveRebound / matchCount;
	}

	public int getTotalRebound() {
		return totalRebound;
	}

	public double getTotalReboundAvg() {
		if (matchCount == 0) {
			return 0;
		}
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
		if (matchCount == 0) {
			return 0;
		}
		return (double)assist / matchCount;
	}

	public int getSteal() {
		return steal;
	}

	public double getStealAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)steal / matchCount;
	}

	public int getBlock() {
		return block;
	}

	public double getBlockAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)block / matchCount;
	}

	public int getTurnover() {
		return turnover;
	}

	public double getTurnoverAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)turnover / matchCount;
	}

	public int getFoul() {
		return foul;
	}

	public double getFoulAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)foul / matchCount;
	}

	public int getScore() {
		return score;
	}

	public double getScoreAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return (double)score / matchCount;
	}

	public double getOffensiveRound() {
		return fieldAttempt + 0.4 * (freethrowAttempt) - 1.07
				* ((double)offensiveRebound / (offensiveRebound + oppoDefensiveRebound) * (fieldAttempt - fieldGoal))
				+ 1.07 * turnover;
	}

	public double getOffensiveRoundAvg() {
		if (matchCount == 0) {
			return 0;
		}
		return getOffensiveRound() / matchCount;
	}

	public double getOffensiveEff() {
		return score / getOffensiveRound() * 100;
	}

	public double getDefensiveRound() {
		return oppoFieldAttempt
				+ 0.4
				* (oppoFreethrowAttempt)
				- 1.07
				* ((double)oppoOffensiveRebound / (oppoOffensiveRebound + defensiveRebound) * (oppoFieldAttempt - oppoFieldGoal))
				+ 1.07 * oppoTurnover;
	}

	public double getDefensiveRoundAvg() {
		if (matchCount == 0) {
			return 0;
		}
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
