package data.seasondata;

import java.sql.Date;

import utility.Constants;
import vo.RecentDataQueue;

/**
 * 用来记录计算球员赛季数据所需的各种指标
 * @author Issac Ding
 * @version 2015年3月14日 下午4:17:44
 */
public class PlayerSeasonVOForCompute {
	
	public int latestMatchID;
	public Date latestMatchDate;
	
	/** 存储最近10场的得分、篮板、助攻、盖帽、抢断、三分命中、投篮命中、罚球命中、三分出手、投篮出手、罚球出手 */
	public RecentDataQueue scoreQueue = new RecentDataQueue();
	public RecentDataQueue reboundQueue = new RecentDataQueue();
	public RecentDataQueue assistQueue = new RecentDataQueue();
	public RecentDataQueue blockQueue = new RecentDataQueue();
	public RecentDataQueue stealQueue = new RecentDataQueue();
	public RecentDataQueue threePointMadeQueue = new RecentDataQueue();
	public RecentDataQueue threePointAttemptQueue = new RecentDataQueue();
	public RecentDataQueue fieldMadeQueue = new RecentDataQueue();
	public RecentDataQueue fieldAttemptQueue = new RecentDataQueue();
	public RecentDataQueue freethrowMadeQueue = new RecentDataQueue();
	public RecentDataQueue freethrowAttemptQueue = new RecentDataQueue();
	
	public float scorePromotion;
	public float reboundPromotion;//TODO
	public float assistPromotion;
	public float blockPromotion;
	public float stealPromotion;
	public float threePointPercentPromotion;
	public float fieldPercentPromotion;
	public float freethrowPercentPromotion;
	
//	public static void main(String[]args) {
//		try {
//			double i = 5/0;
//		}catch(Exception e) {
//			System.out.println("lala");
//		}
//	}
	
	/**
	 * attempt是出手
	 * made命中
	 * avg场均
	 */

	/** 球员名字 */
	public String name;
	
	public String season;

	/** 记录最后一次比赛时的队伍 */
	public String teamName = Constants.UNKNOWN;

	/** 上场数 */
	public int matchCount;

	/** 首发数 */
	public int firstCount;

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
	public float doubleDoubleAvg;

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

	/** 如果没有关于此球员的比赛记录，但是在球员信息里有这个球员的话，用这个构造方法产生一个空的赛季纪录 */
	public PlayerSeasonVOForCompute(String name) {
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

	public float getFirstCountAvg() {
		return firstCountAvg;
	}

	public String getTime() {
		int minutes = time / 60;
		int seconds = time % 60;
		return minutes + ":" + seconds;
	}

	public String getTimeAvg() {
		int timeInt = (int)((float)time / matchCount);
		int minutes = timeInt / 60;
		int seconds = timeInt % 60;
		return minutes + ":" + seconds;
	}
	
	public float getMinutes() {
		return minutes;
	}
	
	public float getMinutesAvg() {
		return minutesAvg;
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

	public int getEfficiency() {
		return efficiency;
	}

	public float getGmSc() {
		return gmsc;
	}

	public float getRealFieldPercent() {
		return realFieldPercent;
	}

	public float getFieldEff() {
		return fieldEff;
	}

	public float getOffensiveReboundPercent() {
		return offensiveReboundPercent;
	}

	public float getDefensiveReboundPercent() {
		return defensiveReboundPercent;
	}

	public float getTotalReboundPercent() {
		return totalReboundPercent;
	}

	public float getAssistPercent() {
		return assistPercent;
	}

	public float getStealPercent() {
		return stealPercent;
	}

	public float getBlockPercent() {
		return blockPercent;
	}

	public float getTurnOverPercent() {
		return turnOverPercent;
	}

	public float getFoulPercent() {
		return foulPercent;
	}

	public float getUsePercent() {
		return usePercent;
	}

	public int getfloatfloat() {
		return doubleDoubleCount;
	}

	public float getfloatfloatAvg() {
		return doubleDoubleAvg;
	}

	/** 三项相加 */
	public int getScoreReboundAssist() {
		return scoreReboundAssist;
	}
	
	public float getScoreReboundAssistAvg() {
		return scoreReboundAssistAvg;
	}

	public float fieldMadeAvg;
	public float fieldAttemptAvg;
	public float fieldPercent;
	public float threePointMadeAvg;
	public float threePointAttemptAvg;
	public float threePointPercent;
	public float freethrowMadeAvg;
	public float freethrowAttemptAvg;
	public float freethrowPercent;
	
	public float firstCountAvg;
	public float minutes;
	public float minutesAvg;
	public float offensiveReboundAvg;
	public float defensiveReboundAvg;
	public float totalReboundAvg;
	public float assistAvg;
	public float stealAvg;
	public float blockAvg;
	public float foulAvg;
	public float turnoverAvg;
	public float scoreAvg;
	
	public int efficiency;
	public float efficiencyAvg;
	public int scoreReboundAssist;
	public float scoreReboundAssistAvg;
	public float gmsc;
	public float gmscAvg;
	
	public float realFieldPercent;
	public float fieldEff;
	public float offensiveReboundPercent;
	public float defensiveReboundPercent;
	public float totalReboundPercent;
	public float stealPercent;
	public float blockPercent;
	public float turnOverPercent;
	public float foulPercent;
	public float usePercent;
	public float assistPercent;
	
	public int latestScore;	//TODO
	public int latestRebound;
	public int latestAssist;
	public int latestBlock;
	public int latestSteal;
	
	public float oppoAttack; //对手进攻回合
	
	
	public void update() {
		
		try {
			
		
		if (matchCount != 0) {
			
			float timeFactor = time / ((float)teamTime / 5);
			
			
			if ((time != 0) && ((teamFieldAttempt + 0.44 * teamFreethrowAttempt + teamTurnover) != 0))
			usePercent = (float)((fieldAttempt + 0.44 * freethrowAttempt + turnover) * ((float)teamTime / 5)/time
					/ (teamFieldAttempt + 0.44 * teamFreethrowAttempt + teamTurnover));
			
			if ((timeFactor * teamFieldMade - fieldMade) != 0)
				assistPercent = assist / (timeFactor * teamFieldMade - fieldMade);
			
			doubleDoubleAvg = (float)doubleDoubleCount / matchCount;
			
			
			float temp = (float)((fieldAttempt - threePointAttempt + 0.44 * freethrowAttempt + foul));
			if (temp != 0) foulPercent = foul / temp;
			
			temp = (float)((fieldAttempt - threePointAttempt + 0.44 * freethrowAttempt + turnover));
			if(temp != 0) turnOverPercent = turnover / temp;
			
			temp = ((float)teamTime / 5) / time;
			if ((temp != 0) && ((teamOffensiveRebound + oppoDefensiveRebound != 0) )) {
				offensiveReboundPercent=offensiveRebound * temp / (teamOffensiveRebound + oppoDefensiveRebound);
				defensiveReboundPercent=defensiveRebound * temp / (teamDefensiveRebound + oppoOffensiveRebound);
				totalReboundPercent = totalRebound * temp / (teamTotalRebound + oppoTotalRebound);
			}
			
			if (oppoFieldAttempt - oppoThreePointAttempt != 0) 
				blockPercent = block * temp / (oppoFieldAttempt - oppoThreePointAttempt);
			
			if(oppoAttack != 0) 
				stealPercent = steal * temp / oppoAttack;
			
			if (fieldAttempt != 0) {
				fieldEff = (float)((fieldMade + 0.5 * threePointMade) / fieldAttempt);
			}
			
			float realDivisor = (float)(2 * (fieldAttempt + 0.44 * freethrowAttempt));
			if (realDivisor != 0)
				realFieldPercent = score / realDivisor;
			
			fieldMadeAvg = (float)fieldMade / matchCount;
			fieldAttemptAvg = (float)fieldAttempt / matchCount;
			if (fieldAttempt != 0) fieldPercent = (float)fieldMade / fieldAttempt;
			
			threePointMadeAvg = (float)threePointMade / matchCount;
			threePointAttemptAvg = (float)threePointAttempt / matchCount;
			if (threePointAttempt != 0) threePointPercent = (float)threePointMade / threePointAttempt;
			
			freethrowMadeAvg = (float)freethrowMade / matchCount;
			freethrowAttemptAvg = (float)freethrowAttempt / matchCount;
			if (freethrowAttempt != 0) freethrowPercent = (float)freethrowMade / freethrowAttempt;
			
			minutes = (float)time / 60;
			minutesAvg = minutes / matchCount;

			firstCountAvg = (float) firstCount / matchCount;
			offensiveReboundAvg = (float) offensiveRebound / matchCount;
			defensiveReboundAvg = (float) defensiveRebound / matchCount;
			totalReboundAvg = (float) totalRebound / matchCount;
			assistAvg = (float) assist / matchCount;
			stealAvg = (float) steal / matchCount;
			blockAvg = (float) block / matchCount;
			foulAvg = (float) foul / matchCount;
			turnoverAvg = (float) turnover / matchCount;
			scoreAvg = (float) score / matchCount;
			
			gmsc = (float)(score + 0.4 * fieldMade - 0.7 * fieldAttempt - 0.4 * (freethrowAttempt - freethrowMade) + 0.7
					* offensiveRebound + 0.3 * defensiveRebound + steal + 0.7 * assist + 0.7 * block - 0.4 * foul
					- turnover);
			gmscAvg = gmsc / matchCount;
			scoreReboundAssist = score + totalRebound + assist;
			scoreReboundAssistAvg = (float)scoreReboundAssist / matchCount;
			efficiency =  score + totalRebound + assist + steal + block - fieldAttempt + fieldMade - freethrowAttempt
					+ freethrowMade - turnover;
			efficiencyAvg = efficiency / (float)matchCount;
			
			latestScore = scoreQueue.getLastData();
			latestRebound = reboundQueue.getLastData();
			latestAssist = assistQueue.getLastData();
			latestBlock = blockQueue.getLastData();
			latestSteal = stealQueue.getLastData();
			
//			if (matchCount > 5) {
//				float formerMatchCount = matchCount - 5;
//				
//				int recentScore = scoreQueue.getFiveSum();
//				int recentRebound = reboundQueue.getFiveSum();
//				int recentAssist = assistQueue.getFiveSum();
//				int recentBlock  = blockQueue.getFiveSum();
//				int recentSteal = stealQueue.getFiveSum();
//				
//				float scoreDivisor = (score - recentScore) / formerMatchCount;
//				float reboundDivisor = (totalRebound - recentRebound) / formerMatchCount;
//				float assistDivisor = (assist - recentAssist) / formerMatchCount;
//				float blockDivisor = (block - recentBlock) / formerMatchCount;
//				float stealDivisor = (steal - recentSteal) / formerMatchCount;
//				
//				if (scoreDivisor != 0)
//					scorePromotion = (float)(recentScore / 5.0 / scoreDivisor - 1);
//				if (reboundDivisor != 0)
//					reboundPromotion = (float)(recentRebound / 5.0 / reboundDivisor - 1);
//				if (assistDivisor != 0)
//					assistPromotion = (float)(recentAssist / 5.0 / assistDivisor - 1);
//				if (blockDivisor != 0)
//					blockPromotion = (float)(recentBlock / 5.0 / blockDivisor - 1);
//				if (stealDivisor != 0)
//					stealPromotion = (float)(recentSteal / 5.0 / stealDivisor - 1);
//				
//				int formerFieldAttempt = fieldAttempt - fieldAttemptQueue.getFiveSum();
//				int formerFieldMade = fieldMade - fieldMadeQueue.getFiveSum();
//				if (formerFieldAttempt != 0 && formerFieldMade != 0){
//					float formerFieldPercent = (float)formerFieldMade / formerFieldAttempt;
//					int [] recentFieldMade = fieldMadeQueue.getRecentFive();
//					int [] recentFieldAttempt = fieldAttemptQueue.getRecentFive();
//					float recentFivePercentSum = 0;
//					for (int i=0;i<5;i++) {
//						if (recentFieldAttempt[i] != 0)
//							recentFivePercentSum += recentFieldMade[i] / (float)recentFieldAttempt[i];
//					}
//					fieldPercentPromotion = recentFivePercentSum / 5 / formerFieldPercent- 1;
//				}
//				
//				int formerThreePointAttempt = threePointAttempt - threePointAttemptQueue.getFiveSum();
//				int formerThreePointMade = threePointMade - threePointMadeQueue.getFiveSum();
//				if (formerThreePointAttempt != 0 && formerThreePointMade != 0){
//					float formerThreePointPercent = (float)formerThreePointMade / formerThreePointAttempt;
//					int [] recentThreePointMade = threePointMadeQueue.getRecentFive();
//					int [] recentThreePointAttempt = threePointAttemptQueue.getRecentFive();
//					float recentFivePercentSum = 0;
//					for (int i=0;i<5;i++) {
//						if (recentThreePointAttempt[i] != 0)
//							recentFivePercentSum += recentThreePointMade[i] / (float)recentThreePointAttempt[i];
//					}
//					threePointPercentPromotion = recentFivePercentSum / 5 / formerThreePointPercent - 1;
//				}
//				
//				int formerFreethrowAttempt = freethrowAttempt - freethrowAttemptQueue.getFiveSum();
//				int formerFreethrowMade = freethrowMade - freethrowMadeQueue.getFiveSum();
//				if (formerFreethrowAttempt != 0 && formerFreethrowMade != 0){
//					float formerFreethrowPercent = (float)formerFreethrowMade / formerFreethrowAttempt;
//					int [] recentFreethrowMade = freethrowMadeQueue.getRecentFive();
//					int [] recentFreethrowAttempt = freethrowAttemptQueue.getRecentFive();
//					float recentFivePercentSum = 0;
//					for (int i=0;i<5;i++) {
//						if (recentFreethrowAttempt[i] != 0)
//							recentFivePercentSum += recentFreethrowMade[i] / (float)recentFreethrowAttempt[i];
//					}
//					freethrowPercentPromotion = recentFivePercentSum / 5 / formerFreethrowPercent- 1;
//				}
//			}
		}	
		}catch(Exception e) {
			System.out.println("lala");
		}
	}
	

	
	
	public int hashCode() {
		return teamName.hashCode();
    }
}
