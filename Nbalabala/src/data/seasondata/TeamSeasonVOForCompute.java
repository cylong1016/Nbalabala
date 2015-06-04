package data.seasondata;

/**
 * @author Issac Ding
 * @version 2015年3月14日 下午4:17:09
 */
public class TeamSeasonVOForCompute {
	
	/** 队名 */
	public String teamName;
	
	public String season;

	/** 比赛场数 */
	public int matchCount;

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

	/** 胜场数 */
	public int wins;

	//以下记录对手的总数据，以计算各种复杂的率

	public int oppoDefensiveRebound;

	public int oppoOffensiveRebound;

	public int oppoFieldAttempt;

	public int oppoFreethrowAttempt;

	public int oppoFieldMade;

	public int oppoTurnover;

	public int oppoScore;

	public TeamSeasonVOForCompute(String name) {
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

	public float getWinning() {
		return winning;
	}

	public int getFieldMade() {
		return fieldMade;
	}

	public float getFieldMadeAvg() {
		return fieldMadeAvg;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}

	public float getFieldAttemptAvg() {
		return fieldAttemptAvg;
	}

	public float getFieldPercent() {
		return fieldPercent;
	}

	public int getThreePointMade() {
		return threePointMade;
	}

	public float getThreePointMadeAvg() {
		return threePointMadeAvg;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}

	public float getThreePointAttemptAvg() {
		return threePointAttemptAvg;
	}

	public float getThreePointPercent() {
		return threePointPercent;
	}

	public int getFreethrowMade() {
		return freethrowMade;
	}

	public float getFreethrowMadeAvg() {
		return freethrowMadeAvg;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}

	public float getFreethrowAttemptAvg() {
		return freethrowAttemptAvg;
	}

	public float getFreethrowPercent() {
		return freethrowPercent;
	}

	public int getOffensiveRebound() {
		return offensiveRebound;
	}

	public float getOffensiveReboundAvg() {
		return offensiveReboundAvg;
	}

	public int getDefensiveRebound() {
		return defensiveRebound;
	}

	public float getDefensiveReboundAvg() {
		return defensiveReboundAvg;
	}

	public int getTotalRebound() {
		return totalRebound;
	}

	public float getTotalReboundAvg() {
		return totalReboundAvg;
	}

	public float getOffensiveReboundEff() {
		return offensiveReboundEff;
	}

	public float getDefensiveReboundEff() {
		return defensiveReboundEff;
	}

	public int getAssist() {
		return assist;
	}

	public float getAssistAvg() {
		return assistAvg;
	}

	public int getSteal() {
		return steal;
	}

	public float getStealAvg() {
		return stealAvg;
	}

	public int getBlock() {
		return block;
	}

	public float getBlockAvg() {
		return blockAvg;
	}

	public int getTurnover() {
		return turnover;
	}

	public float getTurnoverAvg() {
		return turnoverAvg;
	}

	public int getFoul() {
		return foul;
	}

	public float getFoulAvg() {
		return foulAvg;
	}

	public int getScore() {
		return score;
	}

	public float getScoreAvg() {
		return scoreAvg;
	}

	public float getOffensiveRound() {
		return offensiveRound;
	}

	public float getOffensiveRoundAvg() {
		return offensiveRoundAvg;
	}

	public float getOffensiveEff() {
		return offensiveEff;
	}

	public float getDefensiveRound() {
		return defensiveRound;
	}

	public float getDefensiveRoundAvg() {
		return defensiveRoundAvg;
	}

	public float getDefensiveEff() {
		return defensiveEff;
	}

	public float getStealEff() {
		return stealEff;
	}

	public float getAssistEff() {
		return assistEff;
	}
	
	public float winning;
	public float fieldMadeAvg;
	public float fieldAttemptAvg;
	public float fieldPercent;
	public float threePointMadeAvg;
	public float threePointAttemptAvg;
	public float threePointPercent;
	public float freethrowMadeAvg;
	public float freethrowAttemptAvg;
	public float freethrowPercent;
	
	public float offensiveReboundAvg;
	public float defensiveReboundAvg;
	public float offensiveReboundEff;
	public float defensiveReboundEff;
	public float totalReboundAvg;
	public float assistAvg;
	public float stealAvg;
	public float blockAvg;
	public float foulAvg;
	public float turnoverAvg;
	public float scoreAvg;
	
	public float offensiveRoundAvg;
	public float defensiveRoundAvg;
	public float offensiveEff;
	public float offensiveRound;
	public float defensiveRound;
	public float assistEff;
	public float stealEff;
	public float defensiveEff;
	
	public float oppoFieldPercent;
	public float oppoScoreAvg;
	
	
	public void update() {
		if (matchCount != 0) {
			
			winning = (float)wins / matchCount;
			
			int tmp1 = offensiveRebound + oppoDefensiveRebound;
			if ( tmp1 != 0 || offensiveRound != 0) {
				offensiveReboundEff = (float)offensiveRebound / (tmp1);
				assistEff = assist / offensiveRound * 100;
				offensiveEff = score / offensiveRound * 100;
			}
			
			int tmp2 = defensiveRebound + oppoOffensiveRebound;
			if (tmp2 != 0) {
				defensiveReboundEff = (float)defensiveRebound / (tmp2);
				stealEff = steal / defensiveRound * 100;
				defensiveEff = oppoScore / defensiveRound * 100;
			}
			
			defensiveRoundAvg = defensiveRound / matchCount;
			offensiveRoundAvg = offensiveRound / matchCount;
			
			fieldMadeAvg = (float)fieldMade / matchCount;
			fieldAttemptAvg = (float)fieldAttempt / matchCount;
			if (fieldAttempt != 0) fieldPercent = (float)fieldMade / fieldAttempt;
			
			threePointMadeAvg = (float)threePointMade / matchCount;
			threePointAttemptAvg = (float)threePointAttempt / matchCount;
			if (threePointAttempt != 0) threePointPercent = (float)threePointMade / threePointAttempt;
			
			freethrowMadeAvg = (float)freethrowMade / matchCount;
			freethrowAttemptAvg = (float)freethrowAttempt / matchCount;
			if (freethrowAttempt != 0) freethrowPercent = (float)freethrowMade / freethrowAttempt;
			
			offensiveReboundAvg = (float) offensiveRebound / matchCount;
			defensiveReboundAvg = (float) defensiveRebound / matchCount;
			totalReboundAvg = (float) totalRebound / matchCount;
			assistAvg = (float) assist / matchCount;
			stealAvg = (float) steal / matchCount;
			blockAvg = (float) block / matchCount;
			foulAvg = (float) foul / matchCount;
			turnoverAvg = (float) turnover / matchCount;
			scoreAvg = (float) score / matchCount;
			
			if (oppoScore != 0) {
				oppoScoreAvg = (float)oppoScore / matchCount;
			}
			
			if (oppoFieldAttempt != 0) {
				oppoFieldPercent = (float)oppoFieldMade / oppoFieldAttempt;
			}
		}
	}
	
	public int hashCode() {
		return teamName.hashCode();
    }
	
	public float getLosing() {
		if (matchCount == 0) return 0;
		else return 1 - getWinning();
	}
}
