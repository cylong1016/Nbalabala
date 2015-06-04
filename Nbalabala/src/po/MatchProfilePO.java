/**
 * 
 */
package po;

import java.sql.Date;

/**
 * 比赛简况持久化对象
 * @author Issac Ding
 * @since 2015年6月3日 下午12:07:53
 * @version 1.0
 */
public class MatchProfilePO {

	/** 比赛ID */
	public int matchID;
	/** 赛季 */
	public String season;
	/** 日期 */
	public Date date;
	/** 客场队缩写 */
	public String roadAbbr;
	/** 主场队缩写 */
	public String homeAbbr;
	/** 小节数 */
	public int section;
	/** 客场队总得分 */
	public int roadTotalScore;
	/** 主场队总得分 */
	public int homeTotalScore;
	/** 客场队第一小节得分 */
	public int roadSection1;
	/** 客场队第二小节得分 */	
	public int roadSection2;
	/** 客场队第三小节得分 */
	public int roadSection3;
	/** 客场队第四小节得分 */
	public int roadSection4;
	/** 主场队第一小节得分 */	
	public int homeSection1;
	/** 主场队第二小节得分 */	
	public int homeSection2;
	/** 主场队第三小节得分 */	
	public int homeSection3;
	/** 主场队第四小节得分 */	
	public int homeSection4;
	
	

}
