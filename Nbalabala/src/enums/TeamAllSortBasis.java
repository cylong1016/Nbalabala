package enums;

/**
 * @author Issac Ding
 * @version 2015年3月15日 下午2:06:28
 */
/**
 * 根据不同的字段将球队总数据进行排序
 * @author cylong
 * @version 2015年3月29日 下午5:11:54
 */
public enum TeamAllSortBasis {
	
	//AS升序，DE降序
	/** 球队名称 */
	NAME,

	/** 胜场数 */
	WINS,

	/** 总场数 */
	MATCH_COUNT,

	/** 球队胜率 */
	WINNING,

	/** 投篮命中数 */
	FIELD_MADE,

	/** 投篮出手数 */
	FIELD_ATTEMPT,

	/** 投篮命中率 */
	FIELD_PERCENT,

	/** 三分球出手数 */
	THREE_POINT_ATTEMPT,

	/** 三分球命中数 */
	THREE_POINT_MADE,

	/** 三分球命中率 */
	THREE_POINT_PERCENT,

	/** 罚球命中数 */
	FREETHROW_MADE,

	/** 罚球出手数 */
	FREETHROW_ATTEMPT,

	/** 罚球命中率 */
	FREETHROW_PERCENT,

	/** 进攻篮板数 */
	OFFENSIVE_REBOUND,

	/** 防守篮板数 */
	DEFENSIVE_REBOUND,

	/** 总篮板数 */
	TOTAL_REBOUND,

	/** 球队进攻篮板效率 */
	OFFENSIVE_REBOUND_EFF,

	/** 球队防守篮板效率 */
	DEFENSIVE_REBOUND_EFF,

	/** 球队进攻回合 */
	OFFENSIVE_ROUND,

	/** 球队进攻效率 */
	OFFENSIVE_EFF,

	/** 球队防守回合 */
	DEFENSIVE_ROUND,

	/** 球队防守效率 */
	DEFENSIVE_EFF,

	/** 抢断数 */
	STEAL,

	/** 球队抢断率 */
	STEAL_EFF,

	/** 助攻数 */
	ASSIST,

	/** 球队助攻率 */
	ASSIST_EFF,

	/** 盖帽数 */
	BLOCK,

	/** 失误数 */
	TURNOVER,

	/** 犯规数 */
	FOUL,

	/** 球队总得分 */
	SCORE,
	
	/** 对手场均得分 */
	OPPO_SCORE,
	
	/** 对手投篮命中率 */
	OPPO_FIELD_PERCENT

}
