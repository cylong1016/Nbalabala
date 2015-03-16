package enums;

/**
 * 筛选依据
 * @author Issac Ding
 * @version 2015年3月15日  下午2:07:03
 */
public enum ScreenBasis {
	
	ALL,
	SCORE,
	REBOUND,
	ASSIST,
	SCORE_REBOUND_ASSIST,	//得分、篮板、助攻之和
	BLOCK,
	STEAL,
	FOUL,
	TURNOVER,
	TIME,
	EFFICIENCY,
	FIELD,
	THREE_POINT,
	FREE_THROW,
	DOUBLE_DOUBLE	//两双
}
