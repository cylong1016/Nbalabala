package autotest.playertest;

import java.util.Comparator;

import autotest.TwoComparatorChain;

/**
 * 
 * @author cylong
 * @version 2015年3月30日 上午1:49:34
 */
public class PlayerComparatorFactory {

	private static int factor = 1;
	
	public static final Comparator<PlayerSimpleSeasonVO> PLAYER_NAME_COMPARATOR = 
			new Comparator<PlayerSimpleSeasonVO>() {
		@Override
		public int compare(PlayerSimpleSeasonVO o1, PlayerSimpleSeasonVO o2) {
			return o1.name.compareTo(o2.name);
		}
	};

	public static Comparator<PlayerSimpleSeasonVO> getPlayerAvgAndHighComparator(String basis, String order) {
		Comparator<PlayerSimpleSeasonVO> comparator = null;
		
		if (order.equals("desc"))
			factor = -1;
		else
			factor = 1;

		switch(basis) {
		case "score":
		case "point":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getScoreAvg() - p2.getScoreAvg()) * 10000000);
				}
			};
			break;
			
		case "rebound":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getTotalReboundAvg() - p2.getTotalReboundAvg()) * 10000000);
				}
			};
			break;

		case "assist":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getAssistAvg() - p2.getAssistAvg()) * 10000000);
				}
			};
			break;
		case "steal":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getStealAvg() - p2.getStealAvg()) * 10000000);
				}
			};
			break;

		case "blockShot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getBlockAvg() - p2.getBlockAvg()) * 10000000);
				}
			};
			break;

		case "fault":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getTurnoverAvg() - p2.getTurnoverAvg()) * 10000000);
				}
			};
			break;

		case "foul":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getFoulAvg() - p2.getFoulAvg()) * 10000000);
				}
			};
			break;

		case "minute":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getMinutesAvg() - p2.getMinutesAvg()) * 10000000);
				}
			};
			break;
			
		case "efficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.efficiencyAvg - p2.efficiencyAvg) * 10000000);
				}
			};
			break;
			
		case "shot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getFieldPercent() - p2.getFieldPercent()) * 10000000);
				}
			};
			break;
			
		case "three":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.threePointPercent - p2.threePointPercent) * 10000000);
				}
			};
			break;
		case "penalty":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.freethrowPercent - p2.freethrowPercent) * 10000000);
				}
			};
			break;
		case "doubleTwo":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.doubleDoubleAvg - p2.doubleDoubleAvg) * 10000000);
				}
			};
			break;
			
			
			// 以下为高阶:
			
		case "realShot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getRealFieldPercent() - p2.getRealFieldPercent()) * 10000000);
				}
			};
			break;
			
		case "GmSc":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.gmscAvg - p2.gmscAvg) * 10000000);
				}
			};
			break;
			
		case "shotEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getFieldEff() - p2.getFieldEff()) * 10000000);
				}
			};
			break;
			
		case "reboundEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getTotalReboundPercent() - p2.getTotalReboundPercent()) * 10000000);
				}
			};
			break;
			
		case "offendReboundEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getOffensiveReboundPercent() - p2.getOffensiveReboundPercent()) * 10000000);
				}
			};
			break;
			
		case "defendReboundEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getDefensiveReboundPercent() - p2.getDefensiveReboundPercent()) * 10000000);
				}
			};
			break;
			
		case "assistEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getAssistPercent() - p2.getAssistPercent()) * 10000000);
				}
			};
			break;
			
		case "stealEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getStealPercent() - p2.getStealPercent()) * 10000000);
				}
			};
			break;
			
		case "blockShotEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getBlockPercent() - p2.getBlockPercent()) * 10000000);
				}
			};
			break;
			
		case "faultEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getTurnOverPercent() - p2.getTurnOverPercent()) * 10000000);
				}
			};
			break;

		case "frequency":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getUsePercent() - p2.getUsePercent()) * 10000000);
				}
			};
			break;
		default:
			break;
		}
		
		return new TwoComparatorChain<PlayerSimpleSeasonVO>(PLAYER_NAME_COMPARATOR, comparator);
	}
	
	
	public static Comparator<PlayerSimpleSeasonVO> getPlayerTotalComparator(String basis, String order) {
		Comparator<PlayerSimpleSeasonVO> comparator = null;
		
		if (order.equals("desc"))
			factor = -1;
		else
			factor = 1;

		switch(basis) {
		case "score":
		case "point":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.score - p2.score));
				}
			};
			break;
			
		case "rebound":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.totalRebound - p2.totalRebound));
				}
			};
			break;

		case "assist":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.assist - p2.assist) );
				}
			};
			break;
		case "steal":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.steal - p2.steal) );
				}
			};
			break;

		case "blockShot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.block - p2.block) );
				}
			};
			break;

		case "fault":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.turnover - p2.turnover) );
				}
			};
			break;

		case "foul":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.foul - p2.foul) );
				}
			};
			break;

		case "minute":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.minutes - p2.minutes) * 10000000);
				}
			};
			break;
			
		case "efficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.efficiencyAvg - p2.efficiencyAvg) * 10000000);
				}//TODO 效率区分场均和总数吗
			};
			break;
			
		case "shot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getFieldPercent() - p2.getFieldPercent())*1000000000 );
				}
			};
			break;
			
		case "three":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.threePointPercent - p2.threePointPercent) *1000000000);
				}
			};
			break;
		case "penalty":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.freethrowPercent - p2.freethrowPercent)*1000000000 );
				}
			};
			break;
		case "doubleTwo":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = PlayerComparatorFactory.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.doubleDoubleCount - p2.doubleDoubleCount) );
				}
			};
			break;
		default:
			break;
		}
		
		return new TwoComparatorChain<PlayerSimpleSeasonVO>(PLAYER_NAME_COMPARATOR, comparator);
	}
	
	public static Comparator<PlayerSimpleSeasonVO> getHotComparator(String field) {
		Comparator<PlayerSimpleSeasonVO> comparator = null;
		switch (field) {
		case "score":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				public int compare(PlayerSimpleSeasonVO vo1,
						PlayerSimpleSeasonVO vo2) {
					return (int) ((vo2.scorePromotion - vo1.scorePromotion) * 10000000);
				}
			};
			break;
		case "rebound":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				public int compare(PlayerSimpleSeasonVO vo1,
						PlayerSimpleSeasonVO vo2) {
					return (int) ((vo2.reboundPromotion - vo1.reboundPromotion) * 10000000);
				}
			};
			break;
		default:
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				public int compare(PlayerSimpleSeasonVO vo1,
						PlayerSimpleSeasonVO vo2) {
					return (int) ((vo2.assistPromotion - vo1.assistPromotion) * 10000000);
				}
			};
		}
		return new TwoComparatorChain<PlayerSimpleSeasonVO>(PLAYER_NAME_COMPARATOR, comparator);
	}
	
	public static Comparator<PlayerSimpleSeasonVO> getKingComparator(String field, String period) {
		Comparator<PlayerSimpleSeasonVO> comparator = null;
		if (period.equals("-season")) {
			return getPlayerAvgAndHighComparator(field, "desc");
		}else if (field.equals("score")){
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				public int compare(PlayerSimpleSeasonVO vo1, PlayerSimpleSeasonVO vo2) {
					return vo2.latestScore - vo1.latestScore;
				}
			};
		}else if (field.equals("rebound")) {
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				public int compare(PlayerSimpleSeasonVO vo1, PlayerSimpleSeasonVO vo2) {
					return vo2.latestRebound - vo1.latestRebound;
				}
			};
		}else {
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				public int compare(PlayerSimpleSeasonVO vo1, PlayerSimpleSeasonVO vo2) {
					return vo2.latestAssist - vo1.latestAssist;
				}
			};
		}
		return new TwoComparatorChain<PlayerSimpleSeasonVO>(PLAYER_NAME_COMPARATOR, comparator);
	}
	
}
