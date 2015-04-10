package autotest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bl.teamseasonbl.TeamAvgSorter;
import utility.Constants;
import data.seasondata.SeasonData;
import enums.SortOrder;
import enums.TeamAllSortBasis;
import enums.TeamAvgSortBasis;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月6日  下午9:12:51
 */
public class Console {
	
	private SeasonSimpleData seasonData = new SeasonSimpleData();
	
	public void execute(java.io.PrintStream out, java.lang.String[] args) {
		if (args == null || args.length ==0) return;
		switch (args[0]) {
		case "--datasource":
			Constants.changeDataSourcePath(args[1]);
			break;
		case "-player":
			break;
		case "-team":
			ArrayList<TeamSimpleSeasonVO> vos = seasonData.getAllTeamSeasonData();
			int neededTeamCount = 30;
			if (args.length < 2) {
				SimpleTeamAvgSorter.sort(vos, TeamAvgSortBasis.SCORE_AVG, SortOrder.DE);
			}else{
				neededTeamCount = getNeededTeamCount(args);
				TeamTestSortHandler.teamSortHandle(args, vos);
				if (neededTeamCount > vos.size())
					neededTeamCount = vos.size();
			}
		}

	}
	
	private int getNeededTeamCount(String[]args) {
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("-n"))
				return Integer.parseInt(args[i]+1);
		}
		return 30;
	}
	

}
