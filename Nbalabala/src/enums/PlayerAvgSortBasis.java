package enums;

/**
 * 平均球员数据字段的排序依据
 * @author cylong
 * @version 2015年3月30日 上午1:23:37
 */
public enum PlayerAvgSortBasis {

	/** 球员名称 */
	NAME,

	/** 所属球队 */
	TEAM_NAME,

	/** 比赛场数 */
	MATCH_COUNT,

	/** 平均首发场数 */
	FIRST_COUNT_AVG,

	/** 平均在场时间 */
	TIME_AVG,

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

	/** 场均罚球命中次数 */
	FREETHROW_GOAL_AVG,

	/** 罚球场均出手次数 */
	FREETHROW_ATTEMPT_AVG,

	/** 罚球命中率 */
	FREETHROW_PERCENT,

	/** 平均进攻篮板数 */
	OFFENSIVE_REBOUND_AVG,

	/** 平均防守篮板数 */
	DEFENSIVE_REBOUND_AVG,

	/** 平均每场总篮板数 */
	TOTAL_REBOUND_AVG,

	/** 平均助攻数 */
	ASSIST_AVG,

	/** 助攻率 */
	ASSIST_PERCENT,

	/** 平均盖帽数 */
	BLOCK_AVG,

	/** 盖帽率 */
	BLOCK_PERCENT,

	/** 平均犯规数 */
	FOUL_AVG,

	/** 犯规率 */
	FOUL_PERCENT,

	/** 平均个人得分 */
	SCORE_AVG,

	/** 场均两双 */
	DOUBLE_DOUBLE_AVG,

	/** 平均得分/篮板/助攻 */
	SCORE_REBOUND_ASSIST_AVG,

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

	/** 平均抢断数 */
	STEAL_AVG,

	/** 抢断率 */
	STEAL_PERCENT,

	/** 平均失误数 */
	TURNOVER_AVG,

	/** 失误率 */
	TURNOVER_PERCENT,

	/** 使用率 */
	USE_PERCENT,

}
