package bl.hotquerybl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import utility.Constants;
import vo.HotFastestPlayerVO;
import vo.HotSeasonPlayerVO;
import vo.HotSeasonTeamVO;
import vo.HotTodayPlayerVO;
import vo.PlayerMatchPerformanceVO;
import vo.PlayerSeasonVO;
import vo.TeamSeasonVO;
import bl.matchquerybl.MatchQuery;
import bl.playerseasonbl.PlayerAvgSorter;
import bl.teamseasonbl.TeamAvgSorter;
import blservice.HotBLService;
import data.playerdata.PlayerData;
import data.seasondata.SeasonData;
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
	
	private SeasonDataService seasonService = new SeasonData();
	private PlayerDataService playerService = new PlayerData();
	
	/**
	 * @see blservice.HotBLService#getHotTodayPlayers(enums.HotTodayPlayerProperty)
	 */
	@Override
	public ArrayList<HotTodayPlayerVO> getHotTodayPlayers(
			HotTodayPlayerProperty property) {
		Comparator<PlayerSeasonVO> comparator = null;
		MatchQuery matchQuery = new MatchQuery();
		switch (property) {
		case SCORE:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return vo2.latestScore - vo1.latestScore;
				}
			};
			break;
		case REBOUND:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return vo2.latestRebound - vo1.latestRebound;
				}
			};
			break;
		case ASSIST:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return vo2.latestAssist - vo1.latestAssist;
				}
			};
			break;
		case BLOCK:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return vo2.latestBlock - vo1.latestBlock;
				}
			};
			break;
		case STEAL:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return vo2.latestSteal - vo1.latestSteal;
				}
			};
			break;
		default:
			break;
		}
		ArrayList<PlayerSeasonVO> players = seasonService.getAllPlayerRecentSeasonData();
		Collections.sort(players, comparator);
		int size = players.size();
		if (size > 5) size = 5;
		int i;
		ArrayList<HotTodayPlayerVO> result = new ArrayList<HotTodayPlayerVO>();
		for (i=0; i<size; i++) {
			PlayerSeasonVO seasonVO = players.get(i);
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
			ArrayList<PlayerMatchPerformanceVO> performanceList = 
					matchQuery.getMatchRecordByPlayerName(name);
			if (performanceList.size() != 0) {
				result.add(new HotTodayPlayerVO(i+1, name, seasonVO.teamName, position,
						value, performanceList.get(performanceList.size() - 1)));
			}else{
				result.add(new HotTodayPlayerVO(i+1, name, seasonVO.teamName, position,
						value, new PlayerMatchPerformanceVO(name)));
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
		ArrayList<PlayerSeasonVO> players = seasonService.getAllPlayerRecentSeasonData();
		PlayerAvgSorter sorter = new PlayerAvgSorter();
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
		for (i=0; i<size; i++) {
			PlayerSeasonVO seasonVO = players.get(i);
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
			result.add(new HotSeasonPlayerVO(i+1, name, seasonVO.teamName, position, value));
		}
		return result;
	}

	/**
	 * @see blservice.HotBLService#getHotSeasonTeams(enums.HotSeasonTeamProperty)
	 */
	@Override
	public ArrayList<HotSeasonTeamVO> getHotSeasonTeams(
			HotSeasonTeamProperty property) {
		ArrayList<TeamSeasonVO> teams = seasonService.getAllTeamRecentSeasonData();
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
			TeamSeasonVO seasonVO = teams.get(i);
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
			String abbr = seasonVO.teamName;
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
		Comparator<PlayerSeasonVO> comparator = null;
		switch (property) {
		case SCORE_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return (int)((vo2.scorePromotion - vo1.scorePromotion)*10000);
				}
			};
			break;
		case REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return (int)((vo2.reboundPromotion - vo1.reboundPromotion)*10000);
				}
			};
			break;
		case ASSIST_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return (int)((vo2.assistPromotion - vo1.assistPromotion)*10000);
				}
			};
			break;
		case BLOCK_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return (int)((vo2.blockPromotion - vo1.blockPromotion)*10000);
				}
			};
			break;
		case STEAL_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return (int)((vo2.stealPromotion - vo1.stealPromotion)*10000);
				}
			};
			break;
		case FIELD_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return (int)((vo2.fieldPercentPromotion - vo1.fieldPercentPromotion)*10000);
				}
			};
			break;
		case THREE_POINT_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return (int)((vo2.threePointPercentPromotion - vo1.threePointPercentPromotion)*10000);
				}
			};
			break;
		case FREETHROW_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO vo1, PlayerSeasonVO vo2) {
					return (int)((vo2.freethrowPercentPromotion - vo1.freethrowPercentPromotion)*10000);
				}
			};
			break;
		default:
			break;
		}
		ArrayList<PlayerSeasonVO> players = seasonService.getAllPlayerRecentSeasonData();
		Collections.sort(players, comparator);
		int size = players.size();
		if (size > 5) size = 5;
		int i;
		ArrayList<HotFastestPlayerVO> result = new ArrayList<HotFastestPlayerVO>();
		for (i=1; i<=size; i++) {
			PlayerSeasonVO seasonVO = players.get(i);
			int tmp = 0;
			double promotion = 0;
			double formerFiveAvg = 0;
			double [] recentFive = null;
			double [] recentFiveDivisor = null;
			switch (property) {
			case SCORE_AVG:
				promotion = seasonVO.scorePromotion;
				formerFiveAvg = seasonVO.scoreQueue.getFormerFiveAvg();
				recentFive = seasonVO.scoreQueue.getRecentFive();
				break;
			case REBOUND_AVG:
				promotion = seasonVO.reboundPromotion;
				formerFiveAvg = seasonVO.reboundQueue.getFormerFiveAvg();
				recentFive = seasonVO.reboundQueue.getRecentFive();
				break;
			case ASSIST_AVG:
				promotion = seasonVO.assistPromotion;
				formerFiveAvg = seasonVO.assistQueue.getFormerFiveAvg();
				recentFive = seasonVO.assistQueue.getRecentFive();
				break;
			case BLOCK_AVG:
				promotion = seasonVO.blockPromotion;
				formerFiveAvg = seasonVO.blockQueue.getFormerFiveAvg();
				recentFive = seasonVO.blockQueue.getRecentFive();
				break;
			case STEAL_AVG:
				promotion = seasonVO.stealPromotion;
				formerFiveAvg = seasonVO.stealQueue.getFormerFiveAvg();
				recentFive = seasonVO.stealQueue.getRecentFive();
				break;
			case FIELD_PERCENT:
				promotion = seasonVO.fieldPercentPromotion;
				tmp = seasonVO.fieldAttemptQueue.getFormerFiveSum();
				if (tmp != 0) {
					formerFiveAvg = (seasonVO.fieldGoalQueue.getFormerFiveSum() / tmp);
				}
				recentFive = seasonVO.fieldGoalQueue.getRecentFive();
				recentFiveDivisor = seasonVO.fieldAttemptQueue.getRecentFive();
				break;
			case THREE_POINT_PERCENT:
				promotion = seasonVO.threePointPercentPromotion;
				tmp = seasonVO.threePointAttemptQueue.getFormerFiveSum();
				if (tmp != 0) {
					formerFiveAvg = (seasonVO.threePointGoalQueue.getFormerFiveSum() / tmp);
				}
				recentFive = seasonVO.threePointGoalQueue.getRecentFive();
				recentFiveDivisor = seasonVO.threePointAttemptQueue.getRecentFive();
				break;
			case FREETHROW_PERCENT:
				promotion = seasonVO.freethrowPercentPromotion;
				tmp = seasonVO.freethrowAttemptQueue.getFormerFiveSum();
				if (tmp != 0) {
					formerFiveAvg = (seasonVO.freethrowGoalQueue.getFormerFiveSum() / tmp);
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
				result.add(new HotFastestPlayerVO(i+1, name, seasonVO.teamName, position,
						formerFiveAvg, recentFive, promotion));
			}else {
				double [] recentPercent = new double[5];
				for(int j=0;i<5;j++) {
					if (recentFiveDivisor[j] != 0) {
						recentPercent[j] = recentFive[j] / recentFiveDivisor[j];
					}else{
						recentPercent[j] = 0;
					}
				}
				result.add(new HotFastestPlayerVO(i+1, name, seasonVO.teamName, position,
						formerFiveAvg, recentPercent, promotion));
			}
		}
		return result;
	}

}
