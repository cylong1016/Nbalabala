/**
 * 
 */
package bl.hotquerybl;

import java.sql.Date;

import vo.RecentDataQueue;

/**
 *
 * @author Issac Ding
 * @since 2015年6月4日 上午8:56:18
 * @version 1.0
 */
public class TempVO {
	
	public String name;
	
	public String team;
	
	public String oppo;
	
	public Date date;
	
	public int matchCount;
	
	public int matchID;
	
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
	
	public int totalRebound;
	
	/** 助攻 */
	public int assist;

	/** 抢断 */
	public int steal;

	/** 盖帽 */
	public int block;
	
	public int score;

	
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
	
	public int latestScore;
	public int latestRebound;
	public int latestAssist;
	public int latestBlock;
	public int latestSteal;
	
	public void update() {
		
		latestScore = scoreQueue.getLastData();
		latestRebound = reboundQueue.getLastData();
		latestAssist = assistQueue.getLastData();
		latestBlock = blockQueue.getLastData();
		latestSteal = stealQueue.getLastData();
		
		if (matchCount > 5) {
			double formerMatchCount = matchCount - 5;
			
			int recentScore = scoreQueue.getFiveSum();
			int recentRebound = reboundQueue.getFiveSum();
			int recentAssist = assistQueue.getFiveSum();
			int recentBlock  = blockQueue.getFiveSum();
			int recentSteal = stealQueue.getFiveSum();
			
			double scoreDivisor = (score - recentScore) / formerMatchCount;
			double reboundDivisor = (totalRebound - recentRebound) / formerMatchCount;
			double assistDivisor = (assist - recentAssist) / formerMatchCount;
			double blockDivisor = (block - recentBlock) / formerMatchCount;
			double stealDivisor = (steal - recentSteal) / formerMatchCount;
			
			if (scoreDivisor != 0)
				scorePromotion = recentScore / 5.0 / scoreDivisor - 1;
			if (reboundDivisor != 0)
				reboundPromotion = recentRebound / 5.0 / reboundDivisor - 1;
			if (assistDivisor != 0)
				assistPromotion = recentAssist / 5.0 / assistDivisor - 1;
			if (blockDivisor != 0)
				blockPromotion = recentBlock / 5.0 / blockDivisor - 1;
			if (stealDivisor != 0)
				stealPromotion = recentSteal / 5.0 / stealDivisor - 1;
			
			int formerFieldAttempt = fieldAttempt - fieldAttemptQueue.getFiveSum();
			int formerFieldGoal = fieldGoal - fieldGoalQueue.getFiveSum();
			if (formerFieldAttempt != 0 && formerFieldGoal != 0){
				double formerFieldPercent = (double)formerFieldGoal / formerFieldAttempt;
				int [] recentFieldGoal = fieldGoalQueue.getRecentFive();
				int [] recentFieldAttempt = fieldAttemptQueue.getRecentFive();
				double recentFivePercentSum = 0;
				for (int i=0;i<5;i++) {
					if (recentFieldAttempt[i] != 0)
						recentFivePercentSum += recentFieldGoal[i] / (double)recentFieldAttempt[i];
				}
				fieldPercentPromotion = recentFivePercentSum / 5 / formerFieldPercent- 1;
			}
			
			int formerThreePointAttempt = threePointAttempt - threePointAttemptQueue.getFiveSum();
			int formerThreePointGoal = threePointGoal - threePointGoalQueue.getFiveSum();
			if (formerThreePointAttempt != 0 && formerThreePointGoal != 0){
				double formerThreePointPercent = (double)formerThreePointGoal / formerThreePointAttempt;
				int [] recentThreePointGoal = threePointGoalQueue.getRecentFive();
				int [] recentThreePointAttempt = threePointAttemptQueue.getRecentFive();
				double recentFivePercentSum = 0;
				for (int i=0;i<5;i++) {
					if (recentThreePointAttempt[i] != 0)
						recentFivePercentSum += recentThreePointGoal[i] / (double)recentThreePointAttempt[i];
				}
				threePointPercentPromotion = recentFivePercentSum / 5 / formerThreePointPercent - 1;
			}
			
			int formerFreethrowAttempt = freethrowAttempt - freethrowAttemptQueue.getFiveSum();
			int formerFreethrowGoal = freethrowGoal - freethrowGoalQueue.getFiveSum();
			if (formerFreethrowAttempt != 0 && formerFreethrowGoal != 0){
				double formerFreethrowPercent = (double)formerFreethrowGoal / formerFreethrowAttempt;
				int [] recentFreethrowGoal = freethrowGoalQueue.getRecentFive();
				int [] recentFreethrowAttempt = freethrowAttemptQueue.getRecentFive();
				double recentFivePercentSum = 0;
				for (int i=0;i<5;i++) {
					if (recentFreethrowAttempt[i] != 0)
						recentFivePercentSum += recentFreethrowGoal[i] / (double)recentFreethrowAttempt[i];
				}
				freethrowPercentPromotion = recentFivePercentSum / 5 / formerFreethrowPercent- 1;
			}
		}
	}

}
