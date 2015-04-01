package enums;

/**
 * 总球员数据字段的排序依据
 * @author Issac Ding
 * @version 2015年3月15日 下午2:22:47
 */
public enum PlayerAllSortBasis {

	/** 球员名称 */
	NAME,

	/** 所属球队 */
	TEAM_NAME,

	/** 比赛场数 */
	MATCH_COUNT,

	/** 首发场数 */
	FIRST_COUNT,

	/** 在场时间 */
	TIME,

	/** 投篮命中数 */
	FIELD_GOAL,

	/** 投篮出手数 */
	FIELD_ATTEMPT,

	/** 投篮命中率 */
	FIELD_PERCENT,

	/** 三分球命中数 */
	THREE_POINT_GOAL,
	
	/** 三分球出手数 */
	THREE_POINT_ATTEMPT,

	/** 三分球命中率 */
	THREE_POINT_PERCENT,

	/** 罚球命中数 */
	FREETHROW_GOAL,

	/** 罚球出手次数 */
	FREETHROW_ATTEMPT,

	/** 罚球命中率 */
	FREETHROW_PERCENT,

	/** 进攻篮板数 */
	OFFENSIVE_REBOUND,

	/** 防守篮板数 */
	DEFENSIVE_REBOUND,

	/** 总篮板数 */
	TOTAL_REBOUND,

	/** 助攻数 */
	ASSIST,

	/** 助攻率 */
	ASSIST_PERCENT,

	/** 盖帽数 */
	BLOCK,

	/** 盖帽率 */
	BLOCK_PERCENT,

	/** 犯规数 */
	FOUL,

	/** 犯规率 */
	FOUL_PERCENT,

	/** 个人得分 */
	SCORE,

	/** 两双 */
	DOUBLE_DOUBLE,

	/** 得分/篮板/助攻 */
	SCORE_REBOUND_ASSIST,

	/** 效率 */
	EFFICIENCY,

	/** GmSc效率值 */
	GMSC,

	/** 真实投篮命中率 */
	REAL_FIELD_PERCENT,

	/** 投篮效率 */
	FIELD_EFF,

	/** 进攻篮板率 */
	OFFENSIVE_REBOUND_PERCENT,

	/** 防守篮板率 */
	DEFENSIVE_REBOUND_PERCENT,

	/** 篮板率 */
	TOTAL_REBOUND_PERCENT,

	/** 抢断数 */
	STEAL,

	/** 抢断率 */
	STEAL_PERCENT,

	/** 失误数 */
	TURNOVER,

	/** 失误率 */
	TURNOVER_PERCENT,

	/** 使用率 */
	USE_PERCENT,

}
