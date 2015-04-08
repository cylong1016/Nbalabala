package vo;

/**
 * 用来记录计算球员赛季数据所需的各种指标
 * @author Issac Ding
 * @version 2015年3月14日 下午4:17:44
 */
public class PlayerSeasonVO {
	
	/** 存储最近10场的得分、篮板、助攻、盖帽、抢断、三分命中、投篮命中、罚球命中、三分出手、投篮出手、罚球出手 */
	public RecentDataQueue scoreQueue = new RecentDataQueue();
	public RecentDataQueue reboundQueue = new RecentDataQueue();
	public RecentDataQueue assistQueue = new RecentDataQueue();
	public RecentDataQueue blockQueue = new RecentDataQueue();
	public RecentDataQueue stealQueue = new RecentDataQueue();
	public RecentDataQueue threePointGoalQueue = new RecentDataQueue();
	public RecentDataQueue threePointAttemptQueue = new RecentDataQueue();
	public RecentDataQueue fieldGoalQueue = new RecentDataQueue();
	public RecentDataQueue fieldAttemptQueue = new RecentDataQueue();
	public RecentDataQueue freethrowGoalQueue = new RecentDataQueue();
	public RecentDataQueue freethrowAttemptQueue = new RecentDataQueue();
	
	public double scorePromotion;
	public double reboundPromotion;
	public double assistPromotion;
	public double blockPromotion;
	public double stealPromotion;
	public double threePointPercentPromotion;
	public double fieldPercentPromotion;
	public double freethrowPercentPromotion;
	
	/**
	 * attempt是出手
	 * goal命中
	 * avg场均
	 */

	/** 球员名字 */
	public String name;

	/** 记录最后一次位置 */
	public char position = '\0';

	/** 记录最后一次比赛时的队伍 */
	public String teamName = "无记录";

	/** 上场数 */
	public int matchCount;

	/** 首发数 */
	public int firstCount;

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

	/** 个人得分 */
	public int score;

	/** 两双次数 */
	public int doubleDoubleCount;

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

	/** 如果没有关于此球员的比赛记录，但是在球员信息里有这个球员的话，用这个构造方法产生一个空的赛季纪录 */
	public PlayerSeasonVO(String name) {
		this.name = name;
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
		return firstCountAvg;
	}

	public String getTime() {
		int minutes = time / 60;
		int seconds = time % 60;
		return minutes + ":" + seconds;
	}

	public String getTimeAvg() {
		int timeInt = (int)((double)time / matchCount);
		int minutes = timeInt / 60;
		int seconds = timeInt % 60;
		return minutes + ":" + seconds;
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

	public int getFreeThrowGoal() {
		return freethrowGoal;
	}

	public double getFreethrowGoalAvg() {
		return freethrowGoalAvg;
	}

	public int getFreeThrowAttempt() {
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

	public char getPosition() {
		return position;
	}

	public int getDoubleDouble() {
		return doubleDoubleCount;
	}

	public double getDoubleDoubleAvg() {
		return doubleDoubleAvg;
	}

	/** 三项相加 */
	public int getScoreReboundAssist() {
		return scoreReboundAssist;
	}
	
	public double getScoreReboundAssistAvg() {
		return scoreReboundAssistAvg;
	}

	public double fieldGoalAvg;
	public double fieldAttemptAvg;
	public double fieldPercent;
	public double threePointGoalAvg;
	public double threePointAttemptAvg;
	public double threePointPercent;
	public double freethrowGoalAvg;
	public double freethrowAttemptAvg;
	public double freethrowPercent;
	
	public double firstCountAvg;
	public double minutes;
	public double minutesAvg;
	public double offensiveReboundAvg;
	public double defensiveReboundAvg;
	public double totalReboundAvg;
	public double assistAvg;
	public double stealAvg;
	public double blockAvg;
	public double foulAvg;
	public double turnoverAvg;
	public double scoreAvg;
	
	public int efficiency;
	public double realFieldPercent;
	public double fieldEff;
	public double totalReboundPercent;
	public double stealPercent;
	public double blockPercent;
	public double turnOverPercent;
	public double foulPercent;
	public double doubleDoubleAvg;
	public int scoreReboundAssist;
	public double scoreReboundAssistAvg;
	public double usePercent;
	public double gmsc;
	public double assistPercent;
	public double offensiveReboundPercent;
	public double defensiveReboundPercent;
	
	public void update() {
		if (matchCount != 0) {
			
			double timeFactor = time / ((double)teamTime / 5);
			
			usePercent = (fieldAttempt + 0.44 * freethrowAttempt + turnover) * timeFactor
					/ (teamFieldAttempt + 0.44 * teamFreethrowAttempt + teamTurnover);
			assistPercent = assist / (timeFactor * teamFieldGoal - fieldGoal);
			offensiveReboundPercent=offensiveRebound * timeFactor / (teamOffensiveRebound + oppoOffensiveRebound);
			defensiveReboundPercent=defensiveRebound * timeFactor / (teamDefensiveRebound + oppoDefensiveRebound);
			scoreReboundAssistAvg = (double)scoreReboundAssist / matchCount;
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
			
			fieldGoalAvg = (double)fieldGoal / matchCount;
			fieldAttemptAvg = (double)fieldAttempt / matchCount;
			if (fieldAttempt != 0) fieldPercent = (double)fieldGoal / fieldAttempt;
			
			threePointGoalAvg = (double)threePointGoal / matchCount;
			threePointAttemptAvg = (double)threePointAttempt / matchCount;
			if (threePointAttempt != 0) threePointPercent = (double)threePointGoal / threePointAttempt;
			
			freethrowGoalAvg = (double)freethrowGoal / matchCount;
			freethrowAttemptAvg = (double)freethrowAttempt / matchCount;
			if (freethrowAttempt != 0) freethrowPercent = (double)freethrowGoal / freethrowAttempt;
			
			minutes = time / 60;
			minutesAvg = minutes / matchCount;

			firstCountAvg = (double) firstCount / matchCount;
			offensiveReboundAvg = (double) offensiveRebound / matchCount;
			defensiveReboundAvg = (double) defensiveRebound / matchCount;
			totalReboundAvg = (double) totalRebound / matchCount;
			assistAvg = (double) assist / matchCount;
			stealAvg = (double) steal / matchCount;
			blockAvg = (double) block / matchCount;
			foulAvg = (double) foul / matchCount;
			turnoverAvg = (double) turnover / matchCount;
			
			if (matchCount > 9) {
				scorePromotion = scoreQueue.getPromotion();
				reboundPromotion = reboundQueue.getPromotion();
				assistPromotion = assistQueue.getPromotion();
				blockPromotion = blockQueue.getPromotion();
				stealPromotion = stealQueue.getPromotion();
				
				int formerFieldAttempt = fieldAttemptQueue.getFormerFiveSum();
				int latterFieldAttempt = fieldAttemptQueue.getFormerFiveSum();
				if (formerFieldAttempt == 0 || latterFieldAttempt == 0) fieldPercentPromotion = 0;
				else{
					double formerFieldPercent = fieldGoalQueue.getFormerFiveSum() / formerFieldAttempt;
					double latterFieldPercent = fieldGoalQueue.getLatterFiveSum() / latterFieldAttempt;
					fieldPercentPromotion = (latterFieldPercent - formerFieldPercent) / formerFieldPercent;
				}
				
				int formerThreePointAttempt = threePointAttemptQueue.getFormerFiveSum();
				int latterThreePointAttempt = threePointAttemptQueue.getFormerFiveSum();
				if (formerThreePointAttempt == 0 || latterThreePointAttempt == 0) threePointPercentPromotion = 0;
				else{
					double formerThreePointPercent = threePointGoalQueue.getFormerFiveSum() / formerThreePointAttempt;
					double latterThreePointPercent = threePointGoalQueue.getLatterFiveSum() / latterThreePointAttempt;
					threePointPercentPromotion = (latterThreePointPercent - formerThreePointPercent) / formerThreePointPercent;
				}
				
				int formerFreethrowAttempt = freethrowAttemptQueue.getFormerFiveSum();
				int latterFreethrowAttempt = freethrowAttemptQueue.getFormerFiveSum();
				if (formerFreethrowAttempt == 0 || latterFreethrowAttempt == 0) freethrowPercentPromotion = 0;
				else{
					double formerFreethrowPercent = freethrowGoalQueue.getFormerFiveSum() / formerFreethrowAttempt;
					double latterFreethrowPercent = freethrowGoalQueue.getLatterFiveSum() / latterFreethrowAttempt;
					freethrowPercentPromotion = (latterFreethrowPercent - formerFreethrowPercent) / formerFreethrowPercent;
				}
			}
			
		}
		
		gmsc = score + 0.4 * fieldGoal - 0.7 * fieldAttempt - 0.4 * (freethrowAttempt - freethrowGoal) + 0.7
				* offensiveRebound + 0.3 * defensiveRebound + steal + 0.7 * assist + 0.7 * block - 0.4 * foul
				- turnover;
		scoreReboundAssist = score + totalRebound + assist;
		efficiency =  score + totalRebound + assist + steal + block - fieldAttempt + fieldGoal - freethrowAttempt
				+ freethrowGoal - turnover;
	}
	
	/** 记录最近一次比赛的日期，month以12代表12月，13代表1月，14代表2月，以此类推 */
	public int latestMonth;
	public int latestDay;
	
	public boolean isThisRecordLatest(int month, int day) {
		if (month < latestMonth) return false;
		else if (month > latestMonth) return true;
		else {
			if (day > latestDay) return true;
			else return false;
		}
	}
}
