package autotest;

import bl.teamseasonbl.TeamAvgSorter;
import utility.Constants;
import data.seasondata.SeasonData;

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
			SeasonData seasonData = new SeasonData();
			String avgTotal = "avg";
			String [] options = new String[233];
			break;
		case "-team":
			TeamAvgSorter sorter = new TeamAvgSorter();
			if (args.length < 2) {
				sorter.sort(seasonData.ge, basis, order);
			}
			
		}

	}
	

}
