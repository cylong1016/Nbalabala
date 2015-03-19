/**
 * 
 * @author Issac Ding
 * @version 下午3:08:56
 */
package bl.seasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import blservice.TeamSeasonBLService;
import data.seasondata.SeasonData;
import data.seasondata.TeamSeasonRecord;
import dataservice.SeasonDataService;
import enums.ScreenDivision;
import enums.SortOrder;
import enums.TeamSortBasis;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月15日 下午3:08:56
 */
public class TeamSeasonAnalysis implements TeamSeasonBLService{
	
	//记录上一次返回的，也就是界面正在展示的表
	private ArrayList<TeamSeasonRecord> currentList;

	private SeasonDataService seasonData = new SeasonData();

	private int factor = 1;
	
	public TeamSeasonAnalysis() {
		currentList = seasonData.getScreenedTeamSeasonData(ScreenDivision.ALL);
	}
	
	/** 刚进入界面时调用此方法，得到按名字排序的球队数据 */
	public ArrayList<TeamSeasonRecord> getTeamDataSortedByName() {
		sortTeamDataByName(currentList);
		return currentList;
	}
	
	/** 返回按地区筛选的 */
	public ArrayList<TeamSeasonRecord> getScreenedTeamData(ScreenDivision division) {
		currentList = seasonData.getScreenedTeamSeasonData(division);
		return currentList;
	}
	
	/** 排序时调用此方法，order的AS为升序，DE为降序 */
	public ArrayList<TeamSeasonRecord> getResortedTeamData(TeamSortBasis basis, SortOrder order) {
		sortTeamSeasonData(currentList, basis, order);
		return currentList;
	}
	
	public TeamSeasonRecord getTeamDataByAbbr(String abbr) {
		return seasonData.getTeamDataByAbbr(abbr);
	}
	
	/** 按球队名排序 */
	private void sortTeamDataByName(ArrayList<TeamSeasonRecord> teams) {
		Comparator<TeamSeasonRecord> comparator = new Comparator<TeamSeasonRecord>() {
			public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
				return t1.getTeamName().compareTo(t2.getTeamName());
			}
		};
		Collections.sort(teams, comparator);
	}

	/** 按排序指标排序 */
	private void sortTeamSeasonData(ArrayList<TeamSeasonRecord> teams,
			TeamSortBasis basis, SortOrder order) {

		Comparator<TeamSeasonRecord> comparator = null;

		if (order == SortOrder.DE)
			factor = -1;
		else
			factor = 1;

		switch (basis) {
		
		case MATCH_COUNT:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getMatchCount() - t2.getMatchCount());
				}
			};
			break;
			
		case FIELD_GOAL:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldGoal() - t2.getFieldGoal());
				}
			};
			break;

		case FIELD_GOAL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldGoalAvg()- t2.getFieldGoalAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_ATTEMPT:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldAttempt() - t2.getFieldAttempt());
				}
			};
			break;

		case FIELD_ATTEMPT_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldAttemptAvg() - t2.getFieldAttemptAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_PERCENT:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldPercent() - t2.getFieldPercent())< 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_ATTEMPT:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointAttempt() - t2.getThreePointAttempt());
				}
			};
			break;

		case THREE_POINT_ATTEMPT_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointAttemptAvg() - t2.getThreePointAttemptAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_GOAL:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointGoal() - t2.getThreePointGoal());
				}
			};
			break;

		case THREE_POINT_GOAL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointGoalAvg() - t2.getThreePointGoalAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_PERCENT:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointPercent()- t2.getThreePointPercent())< 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_GOAL:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreethrowGoal() - t2.getFreethrowGoal());
				}
			};
			break;

		case FREETHROW_GOAL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreethrowGoalAvg() - t2.getFreethrowGoalAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_ATTEMPT:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreethrowAttempt() - t2.getFreethrowAttempt());
				}
			};
			break;

		case FREETHROW_ATTEMPT_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreethrowAttempAvg() - t2.getFreethrowAttempAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_PERCENT:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreeThrowPercent()- t2.getFreeThrowPercent())< 0 ? -1 : 1;
				}
			};
			break;

		case OFFENSIVE_REBOUND:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveRebound()- t2.getOffensiveRebound());
				}
			};
			break;

		case OFFENSIVE_REBOUND_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveReboundAvg() - t2.getOffensiveReboundAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case DEFENSIVE_REBOUND:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveRebound()- t2.getDefensiveRebound());
				}
			};
			break;

		case DEFENSIVE_REBOUND_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveReboundAvg() - t2.getDefensiveReboundAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case TOTAL_REBOUND:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTotalRebound() - t2.getTotalRebound());
				}
			};
			break;

		case TOTAL_REBOUND_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTotalReboundAvg() - t2.getTotalReboundAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case ASSIST:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getAssist()- t2.getAssist());
				}
			};
			break;

		case ASSIST_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getAssistAvg()- t2.getAssistAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case STEAL:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getSteal()- t2.getSteal());
				}
			};
			break;

		case STEAL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getStealAvg() - t2.getStealAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case BLOCK:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getBlock()- t2.getBlock());
				}
			};
			break;

		case BLOCK_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getBlockAvg() - t2.getBlockAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case TURNOVER:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTurnover() - t2.getTurnover());
				}
			};
			break;

		case TURNOVER_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTurnoverAvg()- t2.getTurnoverAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case FOUL:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFoul() - t2.getFoul());
				}
			};
			break;

		case FOUL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFoulAvg() - t2.getFoulAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case SCORE:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTeamGoal()- t2.getTeamGoal());
				}
			};
			break;

		case SCORE_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTeamGoalAvg()- t2.getTeamGoalAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case WINNING:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getWinning() - t2.getWinning())< 0 ? -1 : 1;
				}
			};
			break;

		case ROUND:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getRound()- t2.getRound())< 0 ? -1 : 1;
				}
			};
			break;

		case ROUND_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getRoundAvg() - t2.getRoundAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case OFFENSIVE_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveEff() - t2.getOffensiveEff())< 0 ? -1 : 1;
				}
			};
			break;

		case DEFENSIVE_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveEff()- t2.getDefensiveEff())< 0 ? -1 : 1;
				}
			};
			break;

		case OFFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveReboundEff()- t2.getOffensiveReboundEff())< 0 ? -1 : 1;
				}
			};
			break;
			
		case DEFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveReboundEff() - t2.getDefensiveReboundEff())< 0 ? -1 : 1;
				}
			};
			break;

		case STEAL_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getStealEff() - t2.getStealEff())< 0 ? -1 : 1;
				}
			};
			break;

		case ASSIST_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getAssistEff() - t2.getAssistEff())< 0 ? -1 : 1;
				}
			};
			break;

		default:
			break;
		}

		Collections.sort(teams, comparator);
	}
}
