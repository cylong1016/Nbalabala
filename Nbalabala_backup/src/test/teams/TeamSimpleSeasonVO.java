package test.teams;


/**
 * @author Issac Ding
 * @version 2015年3月14日 下午4:17:09
 */
public class TeamSimpleSeasonVO {
	
	/** 队名 */
	public String teamName;

	

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

	//以下记录对手的总数据，以计算各种复杂的率

	public int oppoDefensiveRebound;

	public int oppoOffensiveRebound;

	public int oppoFieldAttempt;

	public int oppoFreethrowAttempt;

	public int oppoFieldGoal;

	public int oppoTurnover;

	public int oppoScore;

	public TeamSimpleSeasonVO(String name) {
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

	public int getFieldAttempt() {
		return fieldAttempt;
	}

	public double getFieldPercent() {
		return fieldPercent;
	}

	public int getThreePointGoal() {
		return threePointGoal;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}

	public double getThreePointPercent() {
		return threePointPercent;
	}

	public int getFreethrowGoal() {
		return freethrowGoal;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
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

	public double getOffensiveEff() {
		return offensiveEff;
	}

	public double getDefensiveRound() {
		return defensiveRound;
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
	
	/** 要测试的数据 */
	public int matchCount;
	public int wins;
	
	//普通数据
	public int score;
	public double scoreAvg;
	public int totalRebound;
	public double totalReboundAvg;
	public int assist;
	public double assistAvg;
	public int block;
	public double blockAvg;
	public int steal;
	public double stealAvg;
	public int foul;
	public double foulAvg;
	public int turnover;
	public double turnoverAvg;
	public double fieldPercent;
	public double threePointPercent;
	public double freethrowPercent;
	public int offensiveRebound;
	public double offensiveReboundAvg;
	public int defensiveRebound;
	public double defensiveReboundAvg;
	
	//以下是高阶数据
	public double winning;
	public double offensiveRound;
	public double offensiveEff;
	public double defensiveEff;
	public double offensiveReboundEff;
	public double defensiveReboundEff;
	public double stealEff;
	public double assistEff;
	

	

	public double defensiveRound;
	
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
			if (tmp2 != 0) {
				defensiveReboundEff = (double)defensiveRebound / (tmp2);
				stealEff = steal / defensiveRound * 100;
				defensiveEff = oppoScore / defensiveRound * 100;
			}
			
			if (fieldAttempt != 0) fieldPercent = (double)fieldGoal / fieldAttempt;
			if (threePointAttempt != 0) threePointPercent = (double)threePointGoal / threePointAttempt;
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
	
	public boolean equals(Object o) {
		return teamName.equals(((TeamSimpleSeasonVO)o).teamName);
	}
}
