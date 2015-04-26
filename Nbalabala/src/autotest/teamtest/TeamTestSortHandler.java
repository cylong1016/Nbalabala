package autotest.teamtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月10日  下午11:38:52
 */
public class TeamTestSortHandler {
	
	//TODO 如果出现total就按总数排，否则是avg
	public static void sortTeamByArgs(String[]args, ArrayList<TeamSimpleSeasonVO> vos) {
		
		int i;
		
		//如果是热门，则按热门类型降序排序
		Comparator<TeamSimpleSeasonVO> comparator;
		if (args[1].equals("-hot")) {
			comparator = SimpleTeamTotalSorter.getTeamTotalComparator(args[2], "desc");
			Collections.sort(vos, comparator);
			return;
		}
		
		for (i=1;i<args.length;i++) {
			if (args[i].equals("-sort")) {
				String[] sortArgs = args[i+1].split("\\.");
				if (args[1].equals("-total")) {
					comparator = SimpleTeamTotalSorter.getTeamTotalComparator(sortArgs[0], sortArgs[1]);
				}else {
					comparator = SimpleTeamAvgAndHighSorter.getTeamAvgAndHighComparator(sortArgs[0], sortArgs[1]);
				}
				Collections.sort(vos, comparator);
				return;
			}
		}
		
		//TODO 到底是score还是point
		
		//默认排序：分为高阶、基础场均、基础总计
		if (args[1].equals("-high"))
			comparator = SimpleTeamAvgAndHighSorter.getTeamAvgAndHighComparator("winRate", "desc");
		else if (args[1].equals("-total"))
			comparator = SimpleTeamTotalSorter.getTeamTotalComparator("score", "desc");
		else 
			comparator = SimpleTeamAvgAndHighSorter.getTeamAvgAndHighComparator("score", "desc");
		Collections.sort(vos, comparator);
	}
//	
//	public static void teamSortHandle(String[]args, ArrayList<TeamSimpleSeasonVO> vos) {
//		switch (args[1]) {
//		case "-avg":
//			tryToSortAvgByOneComparator(args, vos);
//			break;
//		case "-total":
//			tryToSortTotalByOneComparator(args, vos);
//			break;
//		case "-hot":
//			sortHotTeam(args[2], vos);
//			break;
//		case "-high":
//			tryToSortHighByOneComparator(args, vos);
//			break;
//		default:
//			break;
//		}
//	}
//	
//	private static void tryToSortAvgByOneComparator(String[]args, ArrayList<TeamSimpleSeasonVO> list) {
//		for (int i = 1; i< args.length ; i++) {
//			if (args[i].contains("\\.")) {
//				if (args[i].contains(",")) {
//					sortHighAndAvgByComparatorChain(args[i], list);
//					return;
//				}else {
//					String[]s = args[i].split("\\.");
//					SortOrder order;
//					TeamAvgSortBasis basis;
//					if (s[1].equals("asc"))
//						order = SortOrder.AS;
//					else 
//						order = SortOrder.DE;
//					switch (s[0]) {
//					case "point":
//						basis = TeamAvgSortBasis.SCORE_AVG;
//						break;
//					case "rebound":
//						basis = TeamAvgSortBasis.TOTAL_REBOUND_AVG;
//						break;
//					case "assist":
//						basis = TeamAvgSortBasis.ASSIST_AVG;
//						break;
//					case "blockShot":
//						basis = TeamAvgSortBasis.BLOCK_AVG;
//						break;
//					case "steal":
//						basis = TeamAvgSortBasis.STEAL_AVG;
//						break;
//					case "foul":
//						basis = TeamAvgSortBasis.FOUL_AVG;
//						break;
//					case "fault":
//						basis = TeamAvgSortBasis.TURNOVER_AVG;
//						break;
//					case "shot":
//						basis = TeamAvgSortBasis.FIELD_PERCENT;
//						break;
//					case "three":
//						basis = TeamAvgSortBasis.THREE_POINT_PERCENT;
//						break;
//					case "penalty":
//						basis = TeamAvgSortBasis.FREETHROW_PERCENT;
//						break;
//					case "defendRebound":
//						basis = TeamAvgSortBasis.DEFENSIVE_REBOUND_AVG;
//						break;
//					default:
//						basis = TeamAvgSortBasis.OFFENSIVE_REBOUND_AVG;
//						break;
//					}
//					SimpleTeamAvgSorter.sort(list, basis, order);
//					return;
//				}
//			}
//		}
//		// 默认排序
//		SimpleTeamAvgSorter.sort(list, TeamAvgSortBasis.SCORE_AVG, SortOrder.DE);
//	}
//	
//	private static void tryToSortHighByOneComparator(String[]args, ArrayList<TeamSimpleSeasonVO> list) {
//		for (int i = 1; i< args.length ; i++) {
//			if (args[i].contains(".")) {
//				if (args[i].contains(",")) {
//					sortHighAndAvgByComparatorChain(args[i], list);
//					return;
//				}else {
//					String[]s = args[i].split("\\.");
//					SortOrder order;
//					TeamAllSortBasis basis;
//					if (s[1].equals("asc"))
//						order = SortOrder.AS;
//					else 
//						order = SortOrder.DE;
//					switch (s[0]) {
//					case "winRate":
//						basis = TeamAllSortBasis.WINNING;
//						break;
//					case "offendRound":
//						basis = TeamAllSortBasis.OFFENSIVE_ROUND;
//						break;
//					case "offendEfficient":
//						basis = TeamAllSortBasis.OFFENSIVE_EFF;
//						break;
//					case "defendEfficient":
//						basis = TeamAllSortBasis.DEFENSIVE_EFF;
//						break;
//					case "offendReboundEfficient":
//						basis = TeamAllSortBasis.OFFENSIVE_REBOUND_EFF;
//						break;
//					case "defendReboundEfficient":
//						basis = TeamAllSortBasis.DEFENSIVE_REBOUND_EFF;
//						break;
//					case "stealEfficient":
//						basis = TeamAllSortBasis.STEAL_EFF;
//						break;
//					default:
//						basis = TeamAllSortBasis.ASSIST_EFF;
//						break;
//					}
//					SimpleTeamTotalSorter.sort(list, basis, order);
//					return;
//				}
//			}
//		}
//		// 默认排序
//		SimpleTeamTotalSorter.sort(list, TeamAllSortBasis.WINNING, SortOrder.DE);
//	}
//	
//	private static void sortHighAndAvgByComparatorChain(String arg, ArrayList<TeamSimpleSeasonVO> list){
//		ArrayList<Comparator<TeamSimpleSeasonVO>> comparators = new ArrayList<Comparator<TeamSimpleSeasonVO>>();
//		String[]args = arg.split(",");
//		for (int i = 0; i<args.length ; i++) {
//			if (args[i].contains(".")) {
//				String[]s = args[i].split("\\.");
//				if (args[1].equals("desc"))
//					factor = -1;
//				else
//					factor = 1;
//				switch (s[0]) {
//				case "point":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.scoreAvg - vo2.scoreAvg) * factor * 10000;
//						}
//					});
//					break;
//				case "rebound":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.totalReboundAvg - vo2.totalReboundAvg) * factor * 10000;
//						}
//					});
//					break;
//				case "assist":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.assistAvg - vo2.assistAvg) * factor * 10000;
//						}
//					});
//					break;
//				case "blockShot":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.blockAvg - vo2.blockAvg) * factor * 10000;
//						}
//					});
//					break;
//				case "steal":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.stealAvg - vo2.stealAvg) * factor * 10000;
//						}
//					});
//					break;
//				case "foul":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.foulAvg - vo2.foulAvg) * factor * 10000;
//						}
//					});
//					break;
//				case "fault":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.turnoverAvg - vo2.turnoverAvg) * factor * 10000;
//						}
//					});
//					break;
//				case "shot":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.fieldPercent - vo2.fieldPercent) * factor * 10000;
//						}
//					});
//					break;
//				case "three":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.threePointPercent - vo2.threePointPercent) * factor * 10000;
//						}
//					});
//					break;
//				case "penalty":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.freethrowPercent - vo2.freethrowPercent) * factor * 10000;
//						}
//					});
//					break;
//				case "defendRebound":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.defensiveReboundAvg - vo2.defensiveReboundAvg) * factor * 10000;
//						}
//					});
//					break;
//				case "offendRebound":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.offensiveReboundAvg - vo2.offensiveReboundAvg) * factor * 10000;
//						}
//					});
//				case "winRate":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.winning - vo2.winning) * factor * 10000;
//						}
//					});
//					break;
//				case "offendRound":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.offensiveRound - vo2.offensiveRound) * factor * 10000;
//						}
//					});
//					break;
//				case "offendEfficient":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.offensiveEff - vo2.offensiveEff) * factor * 10000;
//						}
//					});
//					break;
//				case "defendEfficient":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.defensiveRound - vo2.defensiveRound) * factor * 10000;
//						}
//					});
//					break;
//				case "offendReboundEfficient":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.offensiveReboundEff - vo2.offensiveReboundEff) * factor * 10000;
//						}
//					});
//					break;
//				case "defendReboundEfficient":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.defensiveReboundEff - vo2.defensiveReboundEff) * factor * 10000;
//						}
//					});
//					break;
//				case "stealEfficient":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.stealEff - vo2.stealEff) * factor * 10000;
//						}
//					});
//					break;
//				default:
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return (int) (vo1.assistEff - vo2.assistEff) * factor * 10000;
//						}
//					});
//					break;
//				}
//			}
//		}
//		Collections.sort(list, new ComparatorChain<TeamSimpleSeasonVO>(comparators));
//	}
//	
//	private static void sortHotTeam(String field, ArrayList<TeamSimpleSeasonVO> list) {
//		TeamAvgSortBasis basis;
//		switch (field) {
//		case "score":
//			basis = TeamAvgSortBasis.SCORE_AVG;
//			break;
//		case "rebound":
//			basis = TeamAvgSortBasis.TOTAL_REBOUND_AVG;
//			break;
//		case "assist":
//			basis = TeamAvgSortBasis.ASSIST_AVG;
//			break;
//		case "blockShot":
//			basis = TeamAvgSortBasis.BLOCK_AVG;
//			break;
//		case "steal":
//			basis = TeamAvgSortBasis.STEAL_AVG;
//			break;
//		case "foul":
//			basis = TeamAvgSortBasis.FOUL_AVG;
//			break;
//		case "fault":
//			basis = TeamAvgSortBasis.TURNOVER_AVG;
//			break;
//		case "shot":
//			basis = TeamAvgSortBasis.FIELD_PERCENT;
//			break;
//		case "three":
//			basis = TeamAvgSortBasis.THREE_POINT_PERCENT;
//			break;
//		case "penalty":
//			basis = TeamAvgSortBasis.FREETHROW_PERCENT;
//			break;
//		case "defendRebound":
//			basis = TeamAvgSortBasis.DEFENSIVE_REBOUND_AVG;
//			break;
//		default:
//			basis = TeamAvgSortBasis.OFFENSIVE_REBOUND_AVG;
//			break;
//		}
//		SimpleTeamAvgSorter.sort(list, basis, SortOrder.DE);
//	}
//	
//	private static void tryToSortTotalByOneComparator(String[]args, ArrayList<TeamSimpleSeasonVO> list) {
//		int comparatorCount = 0;
//		int index = 0;
//		for (int i = 1; i< args.length ; i++) {
//			if (args[i].contains(".")) {
//				comparatorCount ++;
//				index = i;
//			}
//		}
//		if (comparatorCount == 0)
//			SimpleTeamTotalSorter.sort(list, TeamAllSortBasis.SCORE, SortOrder.DE);
//		else if (comparatorCount > 1)
//			sortTotalByComparatorChain(args, list);
//		else{
//			String[]s = args[index].split(".");
//			SortOrder order;
//			TeamAllSortBasis basis;
//			if (s[1].equals("asc"))
//				order = SortOrder.AS;
//			else 
//				order = SortOrder.DE;
//			switch (s[0]) {
//			case "point":
//				basis = TeamAllSortBasis.SCORE;
//				break;
//			case "rebound":
//				basis = TeamAllSortBasis.TOTAL_REBOUND;
//				break;
//			case "assist":
//				basis = TeamAllSortBasis.ASSIST;
//				break;
//			case "blockShot":
//				basis = TeamAllSortBasis.BLOCK;
//				break;
//			case "steal":
//				basis = TeamAllSortBasis.STEAL;
//				break;
//			case "foul":
//				basis = TeamAllSortBasis.FOUL;
//				break;
//			case "fault":
//				basis = TeamAllSortBasis.TURNOVER;
//				break;
//			case "shot":
//				basis = TeamAllSortBasis.FIELD_PERCENT;
//				break;
//			case "three":
//				basis = TeamAllSortBasis.THREE_POINT_PERCENT;
//				break;
//			case "penalty":
//				basis = TeamAllSortBasis.FREETHROW_PERCENT;
//				break;
//			case "defendRebound":
//				basis = TeamAllSortBasis.DEFENSIVE_REBOUND;
//				break;
//			default:
//				basis = TeamAllSortBasis.OFFENSIVE_REBOUND;
//				break;
//			}
//			SimpleTeamTotalSorter.sort(list, basis, order);
//		}
//	}
//	
//	private static void sortTotalByComparatorChain(String[]args, ArrayList<TeamSimpleSeasonVO> list) {
//		ArrayList<Comparator<TeamSimpleSeasonVO>> comparators = new ArrayList<Comparator<TeamSimpleSeasonVO>>();
//		for (int i = 1; i<args.length ; i++) {
//			if (args[i].contains(".")) {
//				String[]s = args[i].split(".");
//				if (args[1].equals("desc"))
//					factor = -1;
//				else
//					factor = 1;
//				switch (s[0]) {
//				case "point":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (vo1.score - vo2.score) * factor ;
//						}
//					});
//					break;
//				case "rebound":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (vo1.totalRebound - vo2.totalRebound) * factor ;
//						}
//					});
//					break;
//				case "assist":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (vo1.assist - vo2.assist) * factor ;
//						}
//					});
//					break;
//				case "blockShot":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (vo1.block - vo2.block) * factor ;
//						}
//					});
//					break;
//				case "steal":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (vo1.steal - vo2.steal) * factor ;
//						}
//					});
//					break;
//				case "foul":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (vo1.foul - vo2.foul) * factor ;
//						}
//					});
//					break;
//				case "fault":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (vo1.turnover - vo2.turnover) * factor ;
//						}
//					});
//					break;
//				case "shot":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (int)((vo1.fieldPercent - vo2.fieldPercent) * 10000* factor) ;
//						}
//					});
//					break;
//				case "three":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (int)((vo1.threePointPercent - vo2.threePointPercent) *10000* factor );
//						}
//					});
//					break;
//				case "penalty":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (int)((vo1.freethrowPercent - vo2.freethrowPercent) * factor );
//						}
//					});
//					break;
//				case "defendRebound":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (vo1.defensiveRebound - vo2.defensiveRebound) * factor ;
//						}
//					});
//					break;
//				case "offendRebound":
//					comparators.add(new Comparator<TeamSimpleSeasonVO>() {
//						public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
//							return  (vo1.offensiveRebound - vo2.offensiveRebound) * factor ;
//						}
//					});
//				}
//			}
//		}
//	}

}
