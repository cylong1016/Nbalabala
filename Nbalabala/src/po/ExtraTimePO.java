/**
 * 
 */
package po;

/**
 * 加时赛记录的持久化对象
 * @author Issac Ding
 * @since 2015年6月3日 下午12:14:27
 * @version 1.0
 */
public class ExtraTimePO {

	/** 比赛ID */
	public int matchID;
	/** 加时赛序号，比如第一场加时赛就是1 */	
	public int extraOrder;
	/** 客场队得分 */	
	public int roadScore;
	/** 主场队得分 */	
	public int homeScore;
}
