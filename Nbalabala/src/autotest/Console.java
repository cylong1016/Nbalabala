package autotest;

import data.seasondata.SeasonData;
import test.data.PlayerNormalInfo;
import utility.Constants;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月6日  下午9:12:51
 */
public class Console {
	
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
		}

	}
	

}
