/**
 * 
 */
package po;

import java.sql.Date;

/**
 * 一场比赛中球员的表现记录的持久化对象
 * @author Issac Ding
 * @since 2015年6月3日 下午12:17:25
 * @version 1.0
 */
public class MatchPlayerPO {
	/** 比赛ID */
	public int matchID;
	/** 球员名 */	
	public String playerName;
	/** H代表主场，R代表客场 */
	public char homeOrRoad;
	/** 球队缩写 */
	public String teamAbbr;
	/** 是否为首发 */
	public boolean isStarter;
	/** 在场时间，形如25:59 */
	public String timePlayed;
	/** 投篮命中 */
	public int fieldMade;
	/** 投篮出手 */	
	public int fieldAttempt;
	/** 投篮命中率 */	
	public float fieldPercent;
	/** 三分命中 */
	public int threepointMade;
	/** 三分出手 */
	public int threepointAttempt;
	/** 三分命中率 */
	public float threepointPercent;
	/** 罚球命中 */
	public int freethrowMade;
	/** 罚球出手 */
	public int freethrowAttempt;
	/** 罚球命中率 */
	public float freethrowPercent;
	/** 进攻篮板数 */
	public int offensiveRebound;
	/** 防守篮板数 */
	public int defensiveRebound;
	/** 总篮板数 */
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
	/** 得分 */
	public int score;
	/** 该球员在场时球队净得分 */
	public int plusMinus;
	/** 对手缩写 */
	public String oppoAbbr;
	/** 日期 */
	public Date date;
	
	public int getMatchID() {
		return matchID;
	}
	public String getPlayerName() {
		return playerName;
	}
	public char getHomeOrRoad() {
		return homeOrRoad;
	}
	public String getTeamAbbr() {
		return teamAbbr;
	}
	public boolean isStarter() {
		return isStarter;
	}
	public String getTimePlayed() {
		return timePlayed;
	}
	public int getFieldMade() {
		return fieldMade;
	}
	public int getFieldAttempt() {
		return fieldAttempt;
	}
	public float getFieldPercent() {
		return fieldPercent;
	}
	public int getThreepointMade() {
		return threepointMade;
	}
	public int getThreepointAttempt() {
		return threepointAttempt;
	}
	public float getThreepointPercent() {
		return threepointPercent;
	}
	public int getFreethrowMade() {
		return freethrowMade;
	}
	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}
	public float getFreethrowPercent() {
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
	public int getAssist() {
		return assist;
	}
	public int getSteal() {
		return steal;
	}
	public int getBlock() {
		return block;
	}
	public int getTurnover() {
		return turnover;
	}
	public int getFoul() {
		return foul;
	}
	public int getScore() {
		return score;
	}
	public int getPlusMinus() {
		return plusMinus;
	}
	public String getOppoAbbr() {
		return oppoAbbr;
	}
	public Date getDate() {
		return date;
	}

}
