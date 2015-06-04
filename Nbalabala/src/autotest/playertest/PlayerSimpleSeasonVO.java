package autotest.playertest;

import vo.RecentDataQueue;


/**
 * 用来记录计算球员赛季数据所需的各种指标
 * @author Issac Ding
 * @version 2015年3月14日 下午4:17:44
 */
public class PlayerSimpleSeasonVO {
	
	public int latestMatchID;
	
	public PlayerSimpleSeasonVO(String name) {
		this.name = name;
	}
	
	public String lastMonth;
	
	public String lastDay;
	
	public int latestScore;
	public int latestRebound;
	public int latestAssist;
	
	public int age;
	
	/**
	 * attempt是出手
	 * made命中
	 * avg场均
	 */

	/** 球员名字 */
	public String name;

	/** 记录最后一次位置 */
	public String position = "";

	/** 记录最后一次比赛时的队伍 */
	public String teamName = "";

	/** 上场数 */
	public int matchCount;

	/** 时间，单位是秒 */
	public int time;

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

	//以下记录球队和对手数据以计算各种率

	/** 球队总上场时间 ，单位为秒 */
	public int teamTime;

	public int teamOffensiveRebound;

	public int teamDefensiveRebound;

	/** 球队总篮板 */
	public int teamTotalRebound;

	public int teamFieldMade;

	public int teamFieldAttempt;

	public int teamFreethrowAttempt;

	public int teamTurnover;

	/** 对手总篮板 */
	public int oppoTotalRebound;

	public int oppoOffensiveRebound;

	public int oppoDefensiveRebound;

	public int oppoFieldMade;

	public int oppoFieldAttempt;

	public int oppoFreethrowAttempt;

	public int oppoTurnover;

	public int oppoThreePointAttempt;
	
	public double oppoAttack;

	public String getName() {
		return name;
	}

	public String getTeam() {
		return teamName;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public double getMinutes() {
		return minutes;
	}
	
	public double getMinutesAvg() {
		return minutesAvg;
	}

	public int getFieldMade() {
		return fieldMade;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}

	public double getFieldPercent() {
		return fieldPercent;
	}

	public int getThreePointMade() {
		return threePointMade;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}

	public double getThreePointPercent() {
		return threePointPercent;
	}

	public int getFreethrowMade() {
		return freethrowMade;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}

	public double getFreethrowPercent() {
		return freethrowPercent;
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

	public double getTotalReboundAvg() {
		return totalReboundAvg;
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

	public int getEfficiency() {
		return efficiency;
	}

	public double getRealFieldPercent() {
		return realFieldPercent;
	}

	public double getFieldEff() {
		return fieldEff;
	}

	public double getOffensiveReboundPercent() {
		return offensiveReboundPercent;
	}

	public double getDefensiveReboundPercent() {
		return defensiveReboundPercent;
	}

	public double getTotalReboundPercent() {
		return totalReboundPercent;
	}

	public double getAssistPercent() {
		return assistPercent;
	}

	public double getStealPercent() {
		return stealPercent;
	}

	public double getBlockPercent() {
		return blockPercent;
	}

	public double getTurnOverPercent() {
		return turnOverPercent;
	}

	public double getFoulPercent() {
		return foulPercent;
	}

	public double getUsePercent() {
		return usePercent;
	}

	public String getPosition() {
		return position;
	}

	public int getDoubleDouble() {
		return doubleDoubleCount;
	}

	public double getDoubleDoubleAvg() {
		return doubleDoubleAvg;
	}

	public int firstCount;
	
	/** 下面是要测试的属性： */
	//基本数据有：
	public int score;
	public double scoreAvg;
	public int totalRebound;
	public double totalReboundAvg;
	public int assist;
	public double assistAvg;
	public int steal;
	public double stealAvg;
	public int block;
	public double blockAvg;
	public int turnover;
	public double turnoverAvg;
	public int foul;
	public double foulAvg;
	public double minutes;
	public double minutesAvg;
	public int efficiency;
	public double efficiencyAvg;
	public double fieldPercent;
	public double threePointPercent;
	public double freethrowPercent;
	public int doubleDoubleCount;
	public double doubleDoubleAvg;
	
	public double offensiveReboundAvg;
	public double defensiveReboundAvg;
	public double firstCountAvg;
	
	//高阶数据有：
	public double realFieldPercent;
	public double gmscAvg;
	public double fieldEff;
	public double totalReboundPercent;
	public double offensiveReboundPercent;
	public double defensiveReboundPercent;
	public double assistPercent;
	public double stealPercent;
	public double blockPercent;
	public double foulPercent;
	public double turnOverPercent;
	public double usePercent;
	
	public RecentDataQueue scoreQueue = new RecentDataQueue();
	public RecentDataQueue reboundQueue = new RecentDataQueue();
	public RecentDataQueue assistQueue = new RecentDataQueue();
	
	public double scorePromotion;
	public double reboundPromotion;
	public double assistPromotion;
	
	public void update() {
		if (matchCount != 0) {
			
			double timeFactor = time / ((double)teamTime / 5);
			
			usePercent = (fieldAttempt + 0.44 * freethrowAttempt + turnover) * ((double)teamTime / 5)/time
					/ (teamFieldAttempt + 0.44 * teamFreethrowAttempt + teamTurnover);
			assistPercent = assist / (timeFactor * teamFieldMade - fieldMade);
			
			doubleDoubleAvg = (double)doubleDoubleCount / matchCount;
			
			double temp = (fieldAttempt - threePointAttempt + 0.44 * freethrowAttempt + foul);
			if (temp != 0) foulPercent = foul / temp;
			
			temp = (fieldAttempt - threePointAttempt + 0.44 * freethrowAttempt + turnover);
			if(temp != 0) turnOverPercent = turnover / temp;
			
			temp = ((double)teamTime / 5) / time;
			offensiveReboundPercent=offensiveRebound * temp / (teamOffensiveRebound + oppoDefensiveRebound);
			defensiveReboundPercent=defensiveRebound * temp / (teamDefensiveRebound + oppoOffensiveRebound);
			totalReboundPercent = totalRebound * temp / (teamTotalRebound + oppoTotalRebound);
			if (oppoFieldAttempt - oppoThreePointAttempt != 0) 
				blockPercent = block * temp / (oppoFieldAttempt - oppoThreePointAttempt);
			
			if(oppoAttack != 0) 
				stealPercent = steal * temp / oppoAttack;
			
			if (fieldAttempt != 0) {
				fieldEff = (fieldMade + 0.5 * threePointMade) / fieldAttempt;
			}
			
			if (matchCount > 5) {
					double formerMatchCount = matchCount - 5;
					
					int recentScore = scoreQueue.getFiveSum();
					int recentRebound = reboundQueue.getFiveSum();
					int recentAssist = assistQueue.getFiveSum();
					
					double scoreDivisor = (score - recentScore) / formerMatchCount;
					double reboundDivisor = (totalRebound - recentRebound) / formerMatchCount;
					double assistDivisor = (assist - recentAssist) / formerMatchCount;
					
					if (scoreDivisor != 0)
						scorePromotion = recentScore / 5.0 / scoreDivisor - 1;
					if (reboundDivisor != 0)
						reboundPromotion = recentRebound / 5.0 / reboundDivisor - 1;
					if (assistDivisor != 0)
						assistPromotion = recentAssist / 5.0 / assistDivisor - 1;
					
			}
			
			double realDivisor = 2 * (fieldAttempt + 0.44 * freethrowAttempt);
			if (realDivisor != 0)
				realFieldPercent = score / realDivisor;
			
			if (fieldAttempt != 0) fieldPercent = (double)fieldMade / fieldAttempt;
			
			if (threePointAttempt != 0) threePointPercent = (double)threePointMade / threePointAttempt;
			
			if (freethrowAttempt != 0) freethrowPercent = (double)freethrowMade / freethrowAttempt;
			
			minutes = (double)time / 60;
			minutesAvg = minutes / matchCount;
			
			firstCountAvg = (double)firstCount / matchCount;
			defensiveReboundAvg = (double)defensiveRebound/ matchCount;
			offensiveReboundAvg = (double)offensiveRebound / matchCount;

			totalReboundAvg = (double) totalRebound / matchCount;
			assistAvg = (double) assist / matchCount;
			stealAvg = (double) steal / matchCount;
			blockAvg = (double) block / matchCount;
			foulAvg = (double) foul / matchCount;
			turnoverAvg = (double) turnover / matchCount;
			scoreAvg = (double) score / matchCount;
			
			efficiency =  score + totalRebound + assist + steal + block - fieldAttempt + fieldMade - freethrowAttempt
				+ freethrowMade - turnover;
			
			efficiencyAvg = (double) efficiency / matchCount;
			gmscAvg = (score + 0.4 * fieldMade - 0.7 * fieldAttempt - 0.4 * (freethrowAttempt - freethrowMade) + 0.7
					* offensiveRebound + 0.3 * defensiveRebound + steal + 0.7 * assist + 0.7 * block - 0.4 * foul
					- turnover) / matchCount;
			
			latestAssist = assistQueue.getLastData();
			latestScore = scoreQueue.getLastData();
			latestRebound = reboundQueue.getLastData();
		}
	}
	
	public int hashCode() {
		return name.hashCode();
    }
	
	public boolean equals(Object o) {
		return name.equals(((PlayerSimpleSeasonVO)o).name);
	}
}
