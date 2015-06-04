package bl.hotquerybl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import po.MatchDetailPO;
import po.MatchPlayerPO;
import po.PlayerSeasonPO;
import po.TeamSeasonPO;
import utility.Constants;
import vo.HotFastestPlayerVO;
import vo.HotSeasonPlayerVO;
import vo.HotSeasonTeamVO;
import vo.HotTodayPlayerVO;
import bl.playerseasonbl.PlayerAvgSorter;
import bl.teamseasonbl.TeamAvgSorter;
import blservice.HotBLService;
import data.matchdata.MatchData;
import data.playerdata.PlayerData;
import data.seasondata.SeasonData;
import dataservice.MatchDataService;
import dataservice.PlayerDataService;
import dataservice.SeasonDataService;
import enums.HotFastestPlayerProperty;
import enums.HotSeasonPlayerProperty;
import enums.HotSeasonTeamProperty;
import enums.HotTodayPlayerProperty;
import enums.PlayerAvgSortBasis;
import enums.ScreenDivision;
import enums.SortOrder;
import enums.TeamAvgSortBasis;

/**
 * 查看热点的类
 * @author Issac Ding
 * @version 2015年4月8日  下午8:47:51
 */
public class HotQuery implements HotBLService{
	
	private static ArrayList<TempVO> players = new ArrayList<TempVO>();
	
	private SeasonDataService seasonService = new SeasonData();
	private PlayerDataService playerService = new PlayerData();
	
	// 至少这么多个投篮、三分、罚球，才能参与命中率排名
	private static final int MIN_DIVISOR = 4;
	
	public HotQuery() {
		load();
	}
	
	private void load() {
		MatchDataService matchDataService = new MatchData();
		ArrayList<MatchDetailPO> matches = matchDataService.getMatchDetailBySeason(Constants.LATEST_SEASON); 
		new TempCalculator().load(players, matches);
	}
	
	/**
	 * @see blservice.HotBLService#getHotTodayPlayers(enums.HotTodayPlayerProperty)
	 */
	@Override
	public ArrayList<HotTodayPlayerVO> getHotTodayPlayers(
			HotTodayPlayerProperty property) {
		Comparator<TempVO> comparator = null;
		MatchData matchData = new MatchData();
		switch (property) {
		case SCORE:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return vo2.latestScore - vo1.latestScore;
				}
			};
			break;
		case REBOUND:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return vo2.latestRebound - vo1.latestRebound;
				}
			};
			break;
		case ASSIST:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return vo2.latestAssist - vo1.latestAssist;
				}
			};
			break;
		case BLOCK:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return vo2.latestBlock - vo1.latestBlock;
				}
			};
			break;
		case STEAL:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return vo2.latestSteal - vo1.latestSteal;
				}
			};
			break;
		default:
			break;
		}
		Collections.sort(players, comparator);
		int size = players.size();
		if (size > 5) size = 5;
		int i;
		ArrayList<HotTodayPlayerVO> result = new ArrayList<HotTodayPlayerVO>();
		for (i=0; i<size; i++) {
			TempVO seasonVO = players.get(i);
			int value = 0;
			switch (property) {
			case SCORE:
				value = seasonVO.latestScore;
				break;
			case REBOUND:
				value = seasonVO.latestRebound;
				break;
			case ASSIST:
				value = seasonVO.latestAssist;
				break;
			case BLOCK:
				value = seasonVO.latestBlock;
				break;
			case STEAL:
				value = seasonVO.latestSteal;
				break;
			default:
				break;
			}
			String name = seasonVO.name;
			String position = playerService.getPlayerProfileByName(name).getPosition();
			MatchDetailPO matchDetail =
					matchData.getMatchDetailByMatchID(seasonVO.matchID);
			ArrayList<MatchPlayerPO> matchPlayers = matchDetail.getMatchPlayers();
			for (MatchPlayerPO vo  : matchPlayers) {
				if (vo.playerName.equals(name)) {
					result.add(new HotTodayPlayerVO(i+1, name, seasonVO.team, position,
							value, vo));
					break;
				}
			}
		}
		return result;
	}

	/**
	 * @see blservice.HotBLService#getHotSeasonPlayers(enums.HotSeasonPlayerProperty)
	 */
	@Override
	public ArrayList<HotSeasonPlayerVO> getHotSeasonPlayers(
			HotSeasonPlayerProperty property) {
		ArrayList<PlayerSeasonPO> players = seasonService.getAllPlayerRecentSeasonData();
		PlayerAvgSorter sorter = new PlayerAvgSorter();
		if (property == null) return new ArrayList<HotSeasonPlayerVO>();
		switch (property) {
		case SCORE_AVG:
			sorter.sort(players, PlayerAvgSortBasis.SCORE_AVG, SortOrder.DE);
			break;
		case REBOUND_AVG:
			sorter.sort(players, PlayerAvgSortBasis.TOTAL_REBOUND_AVG, SortOrder.DE);
			break;
		case ASSIST_AVG:
			sorter.sort(players, PlayerAvgSortBasis.ASSIST_AVG, SortOrder.DE);
			break;
		case BLOCK_AVG:
			sorter.sort(players, PlayerAvgSortBasis.BLOCK_AVG, SortOrder.DE);
			break;
		case STEAL_AVG:
			sorter.sort(players, PlayerAvgSortBasis.STEAL_AVG, SortOrder.DE);
			break;
		case FIELD_PERCENT:
			sorter.sort(players, PlayerAvgSortBasis.FIELD_PERCENT, SortOrder.DE);
			break;
		case THREE_POINT_PERCENT:
			sorter.sort(players, PlayerAvgSortBasis.THREE_POINT_PERCENT, SortOrder.DE);
			break;
		case FREETHROW_PERCENT:
			sorter.sort(players, PlayerAvgSortBasis.FREETHROW_PERCENT, SortOrder.DE);
			break;
		default:
			break;
		}
		int size = players.size();
		if (size > 5) size = 5;
		int i;
		ArrayList<HotSeasonPlayerVO> result = new ArrayList<HotSeasonPlayerVO>();
		
		int index = 0;
		for (i=0; i<size; i++) {
			PlayerSeasonPO seasonVO = players.get(index);
			index ++;
			
			if (property == HotSeasonPlayerProperty.FIELD_PERCENT && seasonVO.fieldAttempt <MIN_DIVISOR) {
				i--;
				continue;
			}else if(property == HotSeasonPlayerProperty.FREETHROW_PERCENT && seasonVO.freethrowAttempt <MIN_DIVISOR){
				i--;
				continue;
			}else if (property == HotSeasonPlayerProperty.THREE_POINT_PERCENT && seasonVO.threePointAttempt <MIN_DIVISOR) {
				i--;
				continue;
			}
			
			double value = 0;
			switch (property) {
			case SCORE_AVG:
				value = seasonVO.scoreAvg;
				break;
			case REBOUND_AVG:
				value = seasonVO.totalReboundAvg;
				break;
			case ASSIST_AVG:
				value = seasonVO.assistAvg;
				break;
			case BLOCK_AVG:
				value = seasonVO.blockAvg;
				break;
			case STEAL_AVG:
				value = seasonVO.stealAvg;
				break;
			case FIELD_PERCENT:
				value = seasonVO.fieldPercent;
				break;
			case THREE_POINT_PERCENT:
				value = seasonVO.threePointPercent;
				break;
			case FREETHROW_PERCENT:
				value = seasonVO.freethrowPercent;
				break;
			default:
				break;
			}
			String name = seasonVO.name;
			String position = playerService.getPlayerProfileByName(name).getPosition();
			result.add(new HotSeasonPlayerVO(i+1, name, seasonVO.teamAbbr, position, value));
		}
		return result;
	}

	/**
	 * @see blservice.HotBLService#getHotSeasonTeams(enums.HotSeasonTeamProperty)
	 */
	@Override
	public ArrayList<HotSeasonTeamVO> getHotSeasonTeams(
			HotSeasonTeamProperty property) {
		ArrayList<TeamSeasonPO> teams = seasonService.getAllTeamRecentSeasonData();
		TeamAvgSorter sorter = new TeamAvgSorter();
		switch (property) {
		case SCORE_AVG:
			sorter.sort(teams, TeamAvgSortBasis.SCORE_AVG, SortOrder.DE);
			break;
		case REBOUND_AVG:
			sorter.sort(teams, TeamAvgSortBasis.TOTAL_REBOUND_AVG, SortOrder.DE);
			break;
		case ASSIST_AVG:
			sorter.sort(teams, TeamAvgSortBasis.ASSIST_AVG, SortOrder.DE);
			break;
		case BLOCK_AVG:
			sorter.sort(teams, TeamAvgSortBasis.BLOCK_AVG, SortOrder.DE);
			break;
		case STEAL_AVG:
			sorter.sort(teams, TeamAvgSortBasis.STEAL_AVG, SortOrder.DE);
			break;
		case FIELD_PERCENT:
			sorter.sort(teams, TeamAvgSortBasis.FIELD_PERCENT, SortOrder.DE);
			break;
		case THREE_POINT_PERCENT:
			sorter.sort(teams, TeamAvgSortBasis.THREE_POINT_PERCENT, SortOrder.DE);
			break;
		case FREETHROW_PERCENT:
			sorter.sort(teams, TeamAvgSortBasis.FREETHROW_PERCENT, SortOrder.DE);
			break;
		default:
			break;
		}
		int size = teams.size();
		if (size > 5) size = 5;
		int i;
		ArrayList<HotSeasonTeamVO> result = new ArrayList<HotSeasonTeamVO>();
		for (i=0; i<size; i++) {
			TeamSeasonPO seasonVO = teams.get(i);
			double value = 0;
			switch (property) {
			case SCORE_AVG:
				value = seasonVO.scoreAvg;
				break;
			case REBOUND_AVG:
				value = seasonVO.totalReboundAvg;
				break;
			case ASSIST_AVG:
				value = seasonVO.assistAvg;
				break;
			case BLOCK_AVG:
				value = seasonVO.blockAvg;
				break;
			case STEAL_AVG:
				value = seasonVO.stealAvg;
				break;
			case FIELD_PERCENT:
				value = seasonVO.fieldPercent;
				break;
			case THREE_POINT_PERCENT:
				value = seasonVO.threePointPercent;
				break;
			case FREETHROW_PERCENT:
				value = seasonVO.freethrowPercent;
				break;
			default:
				break;
			}
			String abbr = seasonVO.abbr;
			String league;
			ScreenDivision area = Constants.getAreaByAbbr(abbr);
			if (area == ScreenDivision.EAST) {
				league = "东部";
			}else {
				league = "西部";
			}
			result.add(new HotSeasonTeamVO(i+1, abbr, league, value));
		}
		return result;
	}

	/**
	 * @see blservice.HotBLService#getHotFastestPlayers(enums.HotFastestPlayerProperty)
	 */
	@Override
	public ArrayList<HotFastestPlayerVO> getHotFastestPlayers(
			HotFastestPlayerProperty property) {
		Comparator<TempVO> comparator = null;
		if (property == null) return new ArrayList<HotFastestPlayerVO>();
		switch (property) {
		case SCORE_AVG:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return (int)((vo2.scorePromotion - vo1.scorePromotion)*10000);
				}
			};
			break;
		case REBOUND_AVG:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return (int)((vo2.reboundPromotion - vo1.reboundPromotion)*10000);
				}
			};
			break;
		case ASSIST_AVG:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return (int)((vo2.assistPromotion - vo1.assistPromotion)*10000);
				}
			};
			break;
		case BLOCK_AVG:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return (int)((vo2.blockPromotion - vo1.blockPromotion)*10000);
				}
			};
			break;
		case STEAL_AVG:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return (int)((vo2.stealPromotion - vo1.stealPromotion)*10000000);
				}
			};
			break;
		case FIELD_PERCENT:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return (int)((vo2.fieldPercentPromotion - vo1.fieldPercentPromotion)*100000000);
				}
			};
			break;
		case THREE_POINT_PERCENT:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return (int)((vo2.threePointPercentPromotion - vo1.threePointPercentPromotion)*100000000);
				}
			};
			break;
		case FREETHROW_PERCENT:
			comparator = new Comparator<TempVO>() {
				public int compare(TempVO vo1, TempVO vo2) {
					return (int)((vo2.freethrowPercentPromotion - vo1.freethrowPercentPromotion)*10000);
				}
			};
			break;
		default:
			break;
		}
		Collections.sort(players, comparator);
		int size = players.size();
		if (size > 5) size = 5;
		int i;
		ArrayList<HotFastestPlayerVO> result = new ArrayList<HotFastestPlayerVO>();
		for (i=1; i<=size; i++) {
			TempVO seasonVO = players.get(i - 1);
			double promotion = 0;
			double formerAvg = 0;
			int [] recentFive = null;
			int [] recentFiveDivisor = null;
			double formerMatchCount = seasonVO.matchCount - 5;
			
			double formerAttempt = 0;
			
			if (formerMatchCount > 0) {
				switch (property) {
				case SCORE_AVG:
					promotion = seasonVO.scorePromotion;
					formerAvg = (seasonVO.score - seasonVO.scoreQueue.getFiveSum())/formerMatchCount;
					recentFive = seasonVO.scoreQueue.getRecentFive();
					break;
				case REBOUND_AVG:
					promotion = seasonVO.reboundPromotion;
					formerAvg = (seasonVO.totalRebound - seasonVO.reboundQueue.getFiveSum())/formerMatchCount;
					recentFive = seasonVO.reboundQueue.getRecentFive();
					break;
				case ASSIST_AVG:
					promotion = seasonVO.assistPromotion;
					formerAvg = (seasonVO.assist - seasonVO.assistQueue.getFiveSum())/formerMatchCount;
					recentFive = seasonVO.assistQueue.getRecentFive();
					break;
				case BLOCK_AVG:
					promotion = seasonVO.blockPromotion;
					formerAvg = (seasonVO.block - seasonVO.blockQueue.getFiveSum())/formerMatchCount;
					recentFive = seasonVO.blockQueue.getRecentFive();
					break;
				case STEAL_AVG:
					promotion = seasonVO.stealPromotion;
					formerAvg = (seasonVO.steal - seasonVO.stealQueue.getFiveSum())/formerMatchCount;
					recentFive = seasonVO.stealQueue.getRecentFive();
					break;
				case FIELD_PERCENT:
					promotion = seasonVO.fieldPercentPromotion;
					formerAttempt = seasonVO.fieldAttempt - seasonVO.fieldAttemptQueue.getFiveSum();
					if (formerAttempt != 0) {
						formerAvg = (seasonVO.fieldGoal - seasonVO.fieldGoalQueue.getFiveSum()) / formerAttempt;
					}
					recentFive = seasonVO.fieldGoalQueue.getRecentFive();
					recentFiveDivisor = seasonVO.fieldAttemptQueue.getRecentFive();
					break;
				case THREE_POINT_PERCENT:
					promotion = seasonVO.threePointPercentPromotion;
					formerAttempt = seasonVO.threePointAttempt - seasonVO.threePointAttemptQueue.getFiveSum();
					if (formerAttempt != 0) {
						formerAvg = (seasonVO.threePointGoal - seasonVO.threePointGoalQueue.getFiveSum()) / formerAttempt;
					}
					recentFive = seasonVO.threePointGoalQueue.getRecentFive();
					recentFiveDivisor = seasonVO.threePointAttemptQueue.getRecentFive();
					break;
				case FREETHROW_PERCENT:
					promotion = seasonVO.freethrowPercentPromotion;
					formerAttempt = seasonVO.freethrowAttempt - seasonVO.freethrowAttemptQueue.getFiveSum();
					if (formerAttempt != 0) {
						formerAvg = (seasonVO.freethrowGoal - seasonVO.freethrowGoalQueue.getFiveSum()) / formerAttempt;
					}
					recentFive = seasonVO.freethrowGoalQueue.getRecentFive();
					recentFiveDivisor = seasonVO.freethrowAttemptQueue.getRecentFive();
					break;
				default:
					break;
				}
				String name = seasonVO.name;
				String position = playerService.getPlayerProfileByName(name).getPosition();
				if (recentFiveDivisor == null) {
					double[]recentResult = new double[5];
					for (int k=0;k<5;k++) {
						recentResult[k] = (double)recentFive[k];
					}
					result.add(new HotFastestPlayerVO(i, name, seasonVO.team, position,
							formerAvg, recentResult, promotion));
				}else {
					double [] recentPercent = new double[5];
					for(int j=0;j<5;j++) {
						if (recentFiveDivisor[j] != 0) {
							recentPercent[j] = (double)recentFive[j] / recentFiveDivisor[j];
						}else{
							recentPercent[j] = 0;
						}
					}
					result.add(new HotFastestPlayerVO(i, name, seasonVO.team, position,
							formerAvg, recentPercent, promotion));
				}
			}
		}
		return result;
	}

}
