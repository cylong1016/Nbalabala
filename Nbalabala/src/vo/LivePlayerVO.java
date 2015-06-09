/**
 * 
 */
package vo;

/**
 * 用于直播中球员技术统计的VO
 * @author Issac Ding
 * @since 2015年6月9日 下午8:29:48
 * @version 1.0
 */
public class LivePlayerVO {
	
	public String nameChn;
	
	public String nameEng;
	
	/** 是否为首发 */
	public boolean isStarter;
	/** 在场时间，形如25:59 */
	public String timePlayed;
	
	/** 投篮命中 */
	public int fieldMade;
	/** 投篮出手 */	
	public int fieldAttempt;
	
	public int threepointMade;
	/** 三分出手 */
	public int threepointAttempt;
	
	public int freethrowMade;
	/** 罚球出手 */
	public int freethrowAttempt;
	
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

}
