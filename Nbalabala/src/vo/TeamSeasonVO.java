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

	public int oppoScore;

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
		return winning;
	}

	public int getFieldGoal() {
		return fieldGoal;
	}

	public double getFieldGoalAvg() {
		return fieldGoalAvg;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}

	public double getFieldAttemptAvg() {
		return fieldAttemptAvg;
	}

	public double getFieldPercent() {
		return fieldPercent;
	}

	public int getThreePointGoal() {
		return threePointGoal;
	}

	public double getThreePointGoalAvg() {
		return threePointGoalAvg;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}

	public double getThreePointAttemptAvg() {
		return threePointAttemptAvg;
	}

	public double getThreePointPercent() {
		return threePointPercent;
	}

	public int getFreethrowGoal() {
		return freethrowGoal;
	}

	public double getFreethrowGoalAvg() {
		return freethrowGoalAvg;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}

	public double getFreethrowAttemptAvg() {
		return freethrowAttemptAvg;
	}

	public double getFreeThrowPercent() {
		return freethrowPercent;
	}

	public int getOffensiveRebound() {
		return offensiveRebound;
	}

	public double getOffensiveReboundAvg() {
		return offensiveReboundAvg;
	}

	public int getDefensiveRebound() {
		return defensiveRebound;
	}

	public double getDefensiveReboundAvg() {
		return defensiveReboundAvg;
	}

	public int getTotalRebound() {
		return totalRebound;
	}

	public double getTotalReboundAvg() {
		return totalReboundAvg;
	}

	public double getOffensiveReboundEff() {
		return offensiveReboundEff;
	}

	public double getDefensiveReboundEff() {
		return defensiveReboundEff;
	}

	public int getAssist() {
		return assist;
	}

	public double getAssistAvg() {
		return assistAvg;
	}

	public int getSteal() {
		return steal;
	}

	public double getStealAvg() {
		return stealAvg;
	}

	public int getBlock() {
		return block;
	}

	public double getBlockAvg() {
		return blockAvg;
	}

	public int getTurnover() {
		return turnover;
	}

	public double getTurnoverAvg() {
		return turnoverAvg;
	}

	public int getFoul() {
		return foul;
	}

	public double getFoulAvg() {
		return foulAvg;
	}

	public int getScore() {
		return score;
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

	public double getOffensiveEff() {
		return offensiveEff;
	}

	public double getDefensiveRound() {
		return defensiveRound;
	}

	public double getDefensiveRoundAvg() {
		return defensiveRoundAvg;
	}

	public double getDefensiveEff() {
		return defensiveEff;
	}

	public double getStealEff() {
		return stealEff;
	}

	public double getAssistEff() {
		return assistEff;
	}
	
	public double winning;
	public double fieldGoalAvg;
	public double fieldAttemptAvg;
	public double fieldPercent;
	public double threePointGoalAvg;
	public double threePointAttemptAvg;
	public double threePointPercent;
	public double freethrowGoalAvg;
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
	
	public double offensiveRoundAvg;
	public double defensiveRoundAvg;
	public double offensiveEff;
	public double offensiveRound;
	public double defensiveRound;
	public double assistEff;
	public double stealEff;
	public double defensiveEff;
	
	
	public void update() {
		if (matchCount != 0) {
			
			winning = (double)wins / matchCount;
			
			int tmp1 = offensiveRebound + oppoDefensiveRebound;
			if ( tmp1 != 0 || offensiveRound != 0) {
				offensiveReboundEff = (double)offensiveRebound / (tmp1);
				assistEff = assist / offensiveRound * 100;
				offensiveEff = score / offensiveRound * 100;
			}
			
			int tmp2 = defensiveRebound + oppoOffensiveRebound;
			if (tmp2 != 0 || defensiveRound != 0) {
				defensiveReboundEff = (double)defensiveRebound / (tmp2);
				stealEff = steal / defensiveRound * 100;
				defensiveEff = oppoScore / defensiveRound * 100;
			}
			
			defensiveRoundAvg = defensiveRound / matchCount;
			offensiveRoundAvg = offensiveRound / matchCount;
			
			fieldGoalAvg = (double)fieldGoal / matchCount;
			fieldAttemptAvg = (double)fieldAttempt / matchCount;
			if (fieldAttempt != 0) fieldPercent = (double)fieldGoal / fieldAttempt;
			
			threePointGoalAvg = (double)threePointGoal / matchCount;
			threePointAttemptAvg = (double)threePointAttempt / matchCount;
			if (threePointAttempt != 0) threePointPercent = (double)threePointGoal / threePointAttempt;
			
			freethrowGoalAvg = (double)freethrowGoal / matchCount;
			freethrowAttemptAvg = (double)freethrowAttempt / matchCount;
			if (freethrowAttempt != 0) freethrowPercent = (double)freethrowGoal / freethrowAttempt;
			
			offensiveReboundAvg = (double) offensiveRebound / matchCount;
			defensiveReboundAvg = (double) defensiveRebound / matchCount;
			totalReboundAvg = (double) totalRebound / matchCount;
			assistAvg = (double) assist / matchCount;
			stealAvg = (double) steal / matchCount;
			blockAvg = (double) block / matchCount;
			foulAvg = (double) foul / matchCount;
			turnoverAvg = (double) turnover / matchCount;
			scoreAvg = (double) score / matchCount;
		}
	}
	
	public int hashCode() {
		return teamName.hashCode();
    }
}
