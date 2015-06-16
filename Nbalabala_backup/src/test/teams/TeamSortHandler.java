package test.teams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.players.TwoComparatorChain;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月10日  下午11:38:52
 */
public class TeamSortHandler {
	
	public static void teamSortHandle(String[]args, int state, String firstArg, ArrayList<TeamSimpleSeasonVO> teamVOs) {
		
		// 只有一个-team的情况在console里面处理
		
		if (state == 3) {
			Collections.sort(teamVOs, TeamComparatorFactory.getTeamAvgAndHighComparator(firstArg, "desc"));
			return;
		}
		for (int i = 1; i< args.length ; i++) {
			if (args[i].equals("-sort")) {
				int index = i+1;	// sort指令的参数就是args[index]
				String [] s = args[index].split("\\.");
				Comparator<TeamSimpleSeasonVO> comparator = null;
					if (state == 1) {
						comparator = TeamComparatorFactory.getTeamTotalComparator(s[0], s[1]);
					}else {
						comparator = TeamComparatorFactory.getTeamAvgAndHighComparator(s[0], s[1]);
					}
					Collections.sort(teamVOs, 
							new TwoComparatorChain<TeamSimpleSeasonVO>(TeamComparatorFactory.TEAM_NAME_COMPARATOR, 
									comparator));
					return;
				}
		}
		
		
		//默认排序：分为高阶、基础场均、基础总计
		Comparator<TeamSimpleSeasonVO> comparator = null;
		if (state == 2)
			comparator = TeamComparatorFactory.getTeamAvgAndHighComparator("winRate", "desc");
		else if (state == 1)
			comparator = TeamComparatorFactory.getTeamTotalComparator("score", "desc");
		else 
			comparator = TeamComparatorFactory.getTeamAvgAndHighComparator("score", "desc");
		Collections.sort(teamVOs, comparator);
	}
}
