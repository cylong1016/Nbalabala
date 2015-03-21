package bl.seasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import blservice.PlayerSeasonBLService;
import data.seasondata.PlayerSeasonRecord;
import data.seasondata.SeasonData;
import dataservice.SeasonDataService;
import enums.PlayerSortBasis;
import enums.Position;
import enums.ScreenBasis;
import enums.ScreenDivision;
import enums.SortOrder;

/**
 * 分析球员数据的类
 * @author Issac Ding
 * @version 2015年3月15日 下午2:46:44
 */
public class PlayerSeasonAnalysis implements PlayerSeasonBLService{

	private SeasonDataService seasonData = new SeasonData();
	
	/** 记录上一次返回给UI层，即UI层正在显示的球员列表 */
	private ArrayList<PlayerSeasonRecord> currentList = new ArrayList<PlayerSeasonRecord>();

	private int factor = 1;

	/** 刚进入界面时调用此方法，得到的是全部的以名字排序的球员数据 */
	public ArrayList<PlayerSeasonRecord> getAllPlayersSortedByName() {
		currentList = seasonData.getAllPlayerSeasonData();
		sortPlayersByName(currentList);
		return currentList;
	}
	
	/** 得到对当前表重新排序后的表 */
	public ArrayList<PlayerSeasonRecord> getResortedPlayers(PlayerSortBasis basis, SortOrder order) {
		sortPlayers(currentList, basis, order);
		return currentList;
	}
	
	/** 根据位置、地区、筛选依据，返回含有前50个记录的表 */
	public ArrayList<PlayerSeasonRecord> getScreenedPlayers(Position position, ScreenDivision division, 
			ScreenBasis basis){
		
		ArrayList<PlayerSeasonRecord> players = seasonData.getScreenedPlayerSeasonData(position, division);
		
		// 如果没有排序指标依据，那么返回所有符合位置和分区的球员，按名字为序
		if (basis == ScreenBasis.ALL) {
			sortPlayersByName(players);
			currentList = players;
			return players;
		}
		
		// 否则，就按指标选出前50个人
		Comparator<PlayerSeasonRecord> comparator = null;
		switch(basis){
		case DOUBLE_DOUBLE:
			 comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return p1.getDoubleDouble() - p2.getDoubleDouble();
				}
			};
			Collections.sort(players, comparator);
			break;
		case SCORE_REBOUND_ASSIST:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return p1.getScoreReboundAssist() - p2.getScoreReboundAssist();
				}
			};
			Collections.sort(players, comparator);
			break;
		case REBOUND:
			sortPlayers(players, PlayerSortBasis.TOTAL_REBOUND, SortOrder.DE);
			break;
		case ASSIST:
			sortPlayers(players, PlayerSortBasis.ASSIST, SortOrder.DE);
			break;
		case BLOCK:
			sortPlayers(players, PlayerSortBasis.BLOCK, SortOrder.DE);
			break;
		case STEAL:
			sortPlayers(players, PlayerSortBasis.STEAL, SortOrder.DE);
			break;
		case FOUL:
			sortPlayers(players, PlayerSortBasis.FOUL, SortOrder.DE);
			break;
		case TURNOVER:
			sortPlayers(players, PlayerSortBasis.TURNOVER, SortOrder.DE);
			break;
		case TIME:
			sortPlayers(players, PlayerSortBasis.TIME, SortOrder.DE);
			break;
		case EFFICIENCY:
			sortPlayers(players, PlayerSortBasis.EFFICIENCY, SortOrder.DE);
			break;
		case FIELD:
			sortPlayers(players, PlayerSortBasis.FIELD_GOAL, SortOrder.DE);
			break;
		case THREE_POINT:
			sortPlayers(players, PlayerSortBasis.THREE_POINT_GOAL, SortOrder.DE);
			break;
		case FREE_THROW:
			sortPlayers(players, PlayerSortBasis.FREETHROW_GOAL, SortOrder.DE);
			break;
		case SCORE:
			sortPlayers(players, PlayerSortBasis.SCORE, SortOrder.DE);
			break;
		default:
			break;
		}
		
		ArrayList<PlayerSeasonRecord> result = new ArrayList<PlayerSeasonRecord>();
		int maxIndex = players.size() > 50 ? 50 : players.size();
		for (int i=0; i< maxIndex;i++) {
			result.add(players.get(i));
		}
		
		currentList = result;
		return result;
	}
	
	/** 根据球员名字返回所属球队缩写 */
	public String getTeamAbbrByPlayer(String playerName) {
		return seasonData.getTeamAbbrByPlayer(playerName);
	}
	
	/** 根据球员名字返回其赛季数据*/
	public PlayerSeasonRecord getPlayerSeasonDataByName(String playerName) {
		return seasonData.getPlayerSeasonDataByName(playerName);
	}
	
	/** 根据球队缩写返回其当前阵容包含的球员名单  */
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr) {
		return seasonData.getPlayerNamesByTeamAbbr(abbr);
	}
	
	/** 根据名字字典顺序为球员排序 */
	private void sortPlayersByName(ArrayList<PlayerSeasonRecord> players){
		Comparator<PlayerSeasonRecord> comparator = new Comparator<PlayerSeasonRecord>() {
			public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
				return p1.getName().compareTo(p2.getName());
			}
		};
		Collections.sort(players, comparator);
	}

	/** 对输入的球员记录列表按排序依据进行排序 */
	private void sortPlayers(ArrayList<PlayerSeasonRecord> players,
						PlayerSortBasis basis, SortOrder order) {
		
		Comparator<PlayerSeasonRecord> comparator = null;

		if (order == SortOrder.DE)
			factor = -1;
		else
			factor = 1;

		switch (basis) {
		case MATCH_COUNT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getMatchCount() - p2.getMatchCount());
				}
			};
			break;

		case FIRST_COUNT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFirstCount() - p2.getFirstCount());
				}
			};
			break;

		case FIRST_COUNT_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFirstCountAvg() - p2.getFirstCountAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case TIME:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTime() - p2.getTime()) < 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_GOAL:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldGoal() - p2.getFieldGoal());
				}
			};
			break;

		case FIELD_GOAL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldGoalAvg() - p2.getFieldGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_ATTEMPT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldAttempt() - p2.getFieldAttempt());
				}
			};
			break;

		case FIELD_ATTEMPT_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldAttemptAvg() - p2.getFieldAttemptAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldPercent() - p2.getFieldPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_ATTEMPT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointAttempt() - p2.getThreePointAttempt());
				}
			};
			break;

		case THREE_POINT_ATTEMPT_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointAttemptAvg()- p2.getThreePointAttemptAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_GOAL:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointGoal() - p2.getThreePointGoal());
				}
			};
			break;

		case THREE_POINT_GOAL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointGoalAvg() - p2.getThreePointGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointPercent() - p2.getThreePointPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_GOAL:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreethrowGoal() - p2.getFreethrowGoal());
				}
			};
			break;

		case FREETHROW_GOAL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreethrowGoalAvg() - p2.getFreethrowGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_ATTEMPT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreethrowAttempt() - p2.getFreethrowAttempt());
				}
			};
			break;

		case FREETHROW_ATTEMPT_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreethrowAttemptAvg() - p2.getFreethrowAttemptAvg())< 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreethrowPercent() - p2.getFreethrowPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case OFFENSIVE_REBOUND:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getOffensiveRebound() - p2.getOffensiveRebound());
				}
			};
			break;

		case OFFENSIVE_REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getOffensiveReboundAvg() - p2.getOffensiveReboundAvg()) < 0 ? -1
							: 1;
				}
			};
			break;
			
		case OFFENSIVE_REBOUND_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getOffensiveReboundPercent() - p2.getOffensiveReboundPercent()) < 0 ? -1
							: 1;
				}
			};
			break;

		case DEFENSIVE_REBOUND:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDefensiveRebound() - p2.getDefensiveRebound());
				}
			};
			break;

		case DEFENSIVE_REBOUND_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDefensiveReboundPercent() - p2.getDefensiveReboundPercent()) < 0 ? -1
							: 1;
				}
			};
			break;
		case DEFENSIVE_REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDefensiveReboundAvg() - p2.getDefensiveReboundAvg()) < 0 ? -1
							: 1;
				}
			};
			break;

		case TOTAL_REBOUND:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTotalRebound() - p2.getTotalRebound());
				}
			};
			break;

		case TOTAL_REBOUND_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTotalReboundPercent() - p2.getTotalReboundPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case TOTAL_REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTotalReboundAvg() - p2.getTotalReboundAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case ASSIST:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getAssist() - p2.getAssist());
				}
			};
			break;

		case ASSIST_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getAssistAvg() - p2.getAssistAvg()) < 0 ? -1 : 1;
				}
			};
			break;
			
		case ASSIST_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getAssistPercent() - p2.getAssistPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case STEAL:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getSteal() - p2.getSteal());
				}
			};
			break;

		case STEAL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getStealAvg() - p2.getStealAvg()) < 0 ? -1 : 1;
				}
			};
			break;
			
		case STEAL_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getStealPercent() - p2.getStealPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case BLOCK:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getBlock() - p2.getBlock());
				}
			};
			break;

		case BLOCK_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getBlockAvg() - p2.getBlockAvg()) < 0 ? -1 : 1;
				}
			};
			break;
			
		case BLOCK_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getBlockPercent() - p2.getBlockPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case TURNOVER:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTurnover() - p2.getTurnover());
				}
			};
			break;

		case TURNOVER_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTurnoverAvg() - p2.getTurnoverAvg())< 0 ? -1 : 1;
				}
			};
			break;
			
		case TURNOVER_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTurnOverPercent() - p2.getTurnOverPercent())< 0 ? -1 : 1;
				}
			};
			break;

		case FOUL:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFoul() - p2.getFoul());
				}
			};
			break;

		case FOUL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFoulAvg() - p2.getFoulAvg()) < 0 ? -1 : 1;
				}
			};
			break;
			
		case SCORE:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getPersonalGoal() - p2.getPersonalGoal());
				}
			};
			break;

		case SCORE_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getPersonalGoalAvg() - p2.getPersonalGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case EFFICIENCY:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getEfficiency() - p2.getEfficiency());
				}
			};
			break;

		case GMSC:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getGmSc() - p2.getGmSc()) < 0 ? -1 : 1;
				}
			};
			break;

		case REAL_FIELD_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getRealFieldPercent() - p2.getRealFieldPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_EFF:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldEff() - p2.getFieldEff()) < 0 ? -1 : 1;
				}
			};
			break;

		case USE_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getUsePercent() - p2.getUsePercent()) < 0 ? -1 : 1;
				}
			};
			break;
		
		case TIME_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTimeAvg() - p2.getTimeAvg()) < 0 ? -1 : 1;
				}
			};
			break;
			
		case DOUBLE_DOUBLE:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDoubleDouble() - p2.getDoubleDouble());
				}
			};
			break;
			
		case DOUBLE_DOUBLE_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDoubleDoubleAvg() - p2.getDoubleDoubleAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		default:
			break;
		}

		Collections.sort(players, comparator);
	}

}
