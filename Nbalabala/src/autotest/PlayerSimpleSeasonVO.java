package autotest;


/**
 * 用来记录计算球员赛季数据所需的各种指标
 * @author Issac Ding
 * @version 2015年3月14日 下午4:17:44
 */
public class PlayerSimpleSeasonVO {
	
	public PlayerSimpleSeasonVO(String name) {
		this.name = name;
	}
	
	public int latestScore;
	public int latestRebound;
	public int latestAssist;
	
	public int age;
	
	/**
	 * attempt是出手
	 * goal命中
	 * avg场均
	 */

	/** 球员名字 */
	public String name;

	/** 记录最后一次位置 */
	public String position = "";

	/** 记录最后一次比赛时的队伍 */
	public String teamName = "无记录";

	/** 上场数 */
	public int matchCount;

	/** 时间，单位是秒 */
	public int time;

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

	//以下记录球队和对手数据以计算各种率

	/** 球队总上场时间 ，单位为秒 */
	public int teamTime;

	public int teamOffensiveRebound;

	public int teamDefensiveRebound;

	/** 球队总篮板 */
	public int teamTotalRebound;

	public int teamFieldGoal;

	public int teamFieldAttempt;

	public int teamFreethrowAttempt;

	public int teamTurnover;

	/** 对手总篮板 */
	public int oppoTotalRebound;

	public int oppoOffensiveRebound;

	public int oppoDefensiveRebound;

	public int oppoFieldGoal;

	public int oppoFieldAttempt;

	public int oppoFreethrowAttempt;

	public int oppoTurnover;

	public int oppoThreePointAttempt;

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

	public int getFreeThrowGoal() {
		return freethrowGoal;
	}

	public int getFreeThrowAttempt() {
		return freethrowAttempt;
	}

	public double getFreeThrowPercent() {
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

	public double getGmSc() {
		return gmsc;
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
	
	//高阶数据有：
	public double realFieldPercent;
	public double gmsc;
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
	
	public void update() {
		if (matchCount != 0) {
			
			double timeFactor = time / ((double)teamTime / 5);
			
			usePercent = (fieldAttempt + 0.44 * freethrowAttempt + turnover) * timeFactor
					/ (teamFieldAttempt + 0.44 * teamFreethrowAttempt + teamTurnover);
			assistPercent = assist / (timeFactor * teamFieldGoal - fieldGoal);
			offensiveReboundPercent=offensiveRebound * timeFactor / (teamOffensiveRebound + oppoOffensiveRebound);
			defensiveReboundPercent=defensiveRebound * timeFactor / (teamDefensiveRebound + oppoDefensiveRebound);
			doubleDoubleAvg = (double)doubleDoubleCount / matchCount;
			
			double temp = (fieldAttempt - threePointAttempt + 0.44 * freethrowAttempt + foul);
			if (temp != 0) foulPercent = foul / temp;
			
			temp = (fieldAttempt - threePointAttempt + 0.44 * freethrowAttempt + turnover);
			if(temp != 0) turnOverPercent = turnover / temp;
			
			if (oppoFieldAttempt - oppoThreePointAttempt != 0) 
				blockPercent = block * timeFactor / (oppoFieldAttempt - oppoThreePointAttempt);
			
			double oppoAttack = oppoFieldAttempt + 0.4 * oppoFreethrowAttempt - 1.07
					* ((double)oppoOffensiveRebound / (oppoOffensiveRebound + teamDefensiveRebound)
					* (oppoFieldAttempt - oppoFieldGoal)) + 1.07 * oppoTurnover;
			if(oppoAttack != 0) 
				stealPercent = steal * timeFactor / oppoAttack;
			
			totalReboundPercent = totalRebound * timeFactor / (teamTotalRebound + oppoTotalRebound);
			
			if (fieldAttempt != 0) {
				fieldEff = (fieldGoal + 0.5 * threePointGoal) / fieldAttempt;
			}
			
			realFieldPercent = score / (2 * (fieldAttempt + 0.44 * freethrowAttempt));
			
			if (fieldAttempt != 0) fieldPercent = (double)fieldGoal / fieldAttempt;
			
			if (threePointAttempt != 0) threePointPercent = (double)threePointGoal / threePointAttempt;
			
			if (freethrowAttempt != 0) freethrowPercent = (double)freethrowGoal / freethrowAttempt;
			
			minutes = time / 60;
			minutesAvg = minutes / matchCount;

			totalReboundAvg = (double) totalRebound / matchCount;
			assistAvg = (double) assist / matchCount;
			stealAvg = (double) steal / matchCount;
			blockAvg = (double) block / matchCount;
			foulAvg = (double) foul / matchCount;
			turnoverAvg = (double) turnover / matchCount;
			scoreAvg = (double) score / matchCount;
			gmsc = score + 0.4 * fieldGoal - 0.7 * fieldAttempt - 0.4 * (freethrowAttempt - freethrowGoal) + 0.7
				* offensiveRebound + 0.3 * defensiveRebound + steal + 0.7 * assist + 0.7 * block - 0.4 * foul
				- turnover;
			efficiency =  score + totalRebound + assist + steal + block - fieldAttempt + fieldGoal - freethrowAttempt
				+ freethrowGoal - turnover;
		}
	}
	
	public int hashCode() {
		return teamName.hashCode();
    }
}
