package enums;

/**
 * 根据不同的字段将球队平均数据进行排序
 * @author cylong
 * @version 2015年3月29日 下午5:14:18
 */
public enum TeamAvgSortBasis {

	/** 球队名称 */
	NAME,

	/** 胜场数 */
	WINS,

	/** 负场数 */
	LOSES,

	/** 总场数 */
	MATCH_COUNT,

	/** 球队胜率 */
	WINNING,

	/** 平均投篮命中数 */
	FIELD_GOAL_AVG,

	/** 平均投篮出手数 */
	FIELD_ATTEMPT_AVG,

	/** 投篮命中率 */
	FIELD_PERCENT,

	/** 三分球平均出手数 */
	THREE_POINT_ATTEMPT_AVG,

	/** 三分球平均命中数 */
	THREE_POINT_GOAL_AVG,

	/** 三分球命中率 */
	THREE_POINT_PERCENT,

	/** 平均罚球命中数 */
	FREETHROW_GOAL_AVG,

	/** 平均罚球出手数 */
	FREETHROW_ATTEMPT_AVG,

	/** 罚球命中率 */
	FREETHROW_PERCENT,

	/** 平均进攻篮板数 */
	OFFENSIVE_REBOUND_AVG,

	/** 平均防守篮板数 */
	DEFENSIVE_REBOUND_AVG,

	/** 平均总篮板数 */
	TOTAL_REBOUND_AVG,

	/** 球队进攻篮板效率 */
	OFFENSIVE_REBOUND_EFF,

	/** 球队防守篮板效率 */
	DEFENSIVE_REBOUND_EFF,

	/** 球队平均进攻回合 */
	OFFENSIVE_ROUND_AVG,

	/** 球队进攻效率 */
	OFFENSIVE_EFF,

	/** 球队平均防守回合 */
	DEFENSIVE_ROUND_AVG,

	/** 球队防守效率 */
	DEFENSIVE_EFF,

	/** 平均抢断数 */
	STEAL_AVG,

	/** 球队抢断率 */
	STEAL_EFF,

	/** 平均助攻数 */
	ASSIST_AVG,

	/** 球队助攻率 */
	ASSIST_EFF,

	/** 平均盖帽数 */
	BLOCK_AVG,

	/** 平均失误数 */
	TURNOVER_AVG,

	/** 平均犯规数 */
	FOUL_AVG,

	/** 球队平均得分 */
	SCORE_AVG,
}
