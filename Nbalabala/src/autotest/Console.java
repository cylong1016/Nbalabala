package autotest;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import autotest.playertest.PlayerComparatorFactory;
import autotest.playertest.PlayerSimpleSeasonVO;
import autotest.playertest.PlayerSortHandler;
import autotest.teamtest.TeamComparatorFactory;
import autotest.teamtest.TeamSimpleSeasonVO;
import autotest.teamtest.TeamSortHandler;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月6日  下午9:12:51
 */
public class Console {
	
	private SeasonSimpleData seasonData;

	public void execute(java.io.PrintStream out, java.lang.String[] args) {

		try {
			if (args == null || args.length ==0) return;
			switch (args[0]) {
			case "--datasource":	//TODO 注意一下要改变什么东西
				SimpleConstants.sourcePath = args[1]+"\\";
				PlayerSimpleData.loadPlayers();
				SeasonSimpleData.reload();
				SimpleMonitor.startMonitor();
				return;
				
				// 球员的
			case "-player":
				if (seasonData == null) seasonData = new SeasonSimpleData();
				
				while (SimpleMonitor.matchesAppending) {
					try {
						Thread.sleep(1);
					} catch (Exception e) {
						continue;
					}
				}
				testPlayers(out, args);
				return;
				
			// 球队的
			case "-team": 
				if (seasonData == null) seasonData = new SeasonSimpleData();
				while (SimpleMonitor.matchesAppending) {
					System.out.println("waiting");
					try {
						Thread.sleep(1);
					} catch (Exception e) {
						continue;
					}
				}
				testTeams(out, args);
			}
		}catch(Exception e) {
			return;
		}
			


	}
//	
//	public static void main(String[]args) {
//		Console console= new Console();
//		try {
//			PrintStream pStream = new PrintStream(new File("st.txt"));
//			console.execute(pStream, new String[] {"--datasource", "E:\\autotest\\nba"});
//			console.execute(pStream, new String[] {"-team"});
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//	}
	
	private ArrayList<PlayerSimpleSeasonVO> getFilteredPlayers(String[]args) {
		for (int i=1;i<args.length;i++) {
			if (args[i].equals("-filter")) {
				String[] filterArgs = args[i+1].split(",|\\.");
				int length = filterArgs.length;
				
				String positionArg = "";
				String leagueArg = "";
				String ageArg = "";
				
				for (int j=0;j<length;j++) {
					if (filterArgs[j].equals("position")){
						j++;
						positionArg = filterArgs[j];
					}else if(filterArgs[j].equals("league")){
						j++;
						leagueArg = filterArgs[j];
					}else if(filterArgs[j].equals("age")) {
						j++;
						ageArg = filterArgs[j];
					}
				}
				return getFilteredPlayerSeasonData(positionArg, leagueArg, ageArg);
			}
		}
		return seasonData.getAllPlayerSeasonData();
	}
	
	private void testPlayers(PrintStream out, String[]args) {
		ArrayList<PlayerSimpleSeasonVO> playerVOs;
		
		//默认情况下
		if (args.length < 2) {
			playerVOs = seasonData.getAllPlayerSeasonData();
			Collections.sort(playerVOs, 
					PlayerComparatorFactory.getPlayerAvgAndHighComparator("score", "desc"));
			outputPlayerNormalAvgInfo(out, playerVOs, getRealCount(playerVOs, 50));
			return;
		}
		

		
		int state = 0;	// 0-avg 1-total 2-high 3-hot 4-king
		int countNeeded = 0;
		boolean isCountDefault = true;
		String firstArg = null;
		String secondArg = null;
		for (int i = 1;i<args.length;i++) {
			switch (args[i]) {
			case "-n":
				countNeeded = Integer.parseInt(args[i+1]);
				isCountDefault = false;
				break;
			case "-total":
				state = 1;
				break;
			case "-high":
				state = 2;
				break;
			case "-hot":
				state = 3;
				firstArg = args[i+1];
				break;
			case "-king":
				state = 4;
				firstArg = args[i+1];
				if (args[i+2].equals("-season")||args[i+2].equals("-daily")) {
					secondArg = args[i+2];
				}else {
					for (int k=i+3;k<args.length;k++) {
						if (args[k].equals("-season")||args[k].equals("-daily")) {
							secondArg = args[k];
						}
					}
				}
				
			default:
				break;
			}
		}
		
		if (isCountDefault) {
			if (state == 3 || state == 4) {
				countNeeded = 5;
			}else {
				countNeeded = 50;
			}
		}
		
		if(state == 3) {	//热点，没有sort和filter
			playerVOs = seasonData.getAllPlayerSeasonData();
			PlayerSortHandler.playerSortHandle(args, state, firstArg, secondArg,  playerVOs);
			outputPlayerHotInfo(firstArg, out, playerVOs, getRealCount(playerVOs, countNeeded));
		}else if(state == 4 && secondArg.equals("-daily")) { //数据王，没有sort和filter
			playerVOs = new ArrayList<PlayerSimpleSeasonVO>();
			HashMap<String, PlayerSimpleSeasonVO> players = SeasonSimpleData.playerRecords;
			Iterator<PlayerSimpleSeasonVO> itr = players.values().iterator();
			while(itr.hasNext()) {
				PlayerSimpleSeasonVO vo = itr.next();
				if (vo.lastDay.equals(SimpleConstants.lastDay) && vo.lastMonth.equals(SimpleConstants.lastMonth)) {
					playerVOs.add(vo);
				}
			}
			PlayerSortHandler.playerSortHandle(args, state, firstArg, secondArg, playerVOs);
			outputPlayerKingInfo(out, firstArg, secondArg, playerVOs, getRealCount(playerVOs, countNeeded));
		}else if (state == 4 && secondArg.equals("-season")) {
			playerVOs = seasonData.getAllPlayerSeasonData();
			PlayerSortHandler.playerSortHandle(args, state, firstArg, secondArg, playerVOs);
			outputPlayerKingInfo(out, firstArg, secondArg, playerVOs, getRealCount(playerVOs, countNeeded));
		}else if(state == 2) {	//高阶数据可能会有sort参数,但不会有filter
			playerVOs = seasonData.getAllPlayerSeasonData();
			PlayerSortHandler.playerSortHandle(args,state, firstArg, secondArg, playerVOs);
			outputPlayerHighInfo(out, playerVOs, getRealCount(playerVOs, countNeeded));
		}else{	//可能有sort和filter参数
			playerVOs = getFilteredPlayers(args);
			PlayerSortHandler.playerSortHandle(args, state, firstArg, secondArg,playerVOs);
			if (state == 1) {
				outputPlayerNormalTotalInfo(out, playerVOs, getRealCount(playerVOs, countNeeded));
			}else {
				outputPlayerNormalAvgInfo(out, playerVOs, getRealCount(playerVOs, countNeeded));
			}
		}
	}
	
	
	private void testTeams(PrintStream out, String[]args) {
		ArrayList<TeamSimpleSeasonVO> teamVOs = seasonData.getAllTeamSeasonData();
		
		if (args.length < 2) {
			Comparator<TeamSimpleSeasonVO> comparator = 
					TeamComparatorFactory.getTeamAvgAndHighComparator("score", "desc");
			Collections.sort(teamVOs, comparator);
			outputTeamNormalAvgInfo(out, teamVOs, getRealCount(teamVOs, 30));
			return;
		}
		
		int state = 0;	// 0-avg 1-total 2-high 3-hot
		int countNeeded = 0;
		boolean isCountDefault = true;
		String firstArg = null;
		
		for (int i = 1;i<args.length;i++) {
			switch (args[i]) {
			case "-n":
				countNeeded = Integer.parseInt(args[i+1]);
				isCountDefault = false;
				break;
			case "-total":
				state = 1;
				break;
			case "-high":
				state = 2;
				break;
			case "-hot":
				state = 3;
				firstArg = args[i+1];
				break;
			default:
				break;
			}
		}

		if (isCountDefault) {
			if (state == 3) {
				countNeeded = 5;
			} else {
				countNeeded = 30;
			}
		}

		TeamSortHandler.teamSortHandle(args, state, firstArg, teamVOs);
		
		if(state == 3) {	
			outputTeamHotInfo(out, firstArg,  teamVOs, getRealCount(teamVOs, countNeeded));
		}else if(state == 2) {	
			outputTeamHighInfo(out, teamVOs, getRealCount(teamVOs, countNeeded));
		}else if (state == 1){	
			outputTeamNormalTotalInfo(out, teamVOs, getRealCount(teamVOs, countNeeded));
		}else {
			outputTeamNormalAvgInfo(out, teamVOs, getRealCount(teamVOs, countNeeded));
		}
	}
	
	
	private void outputTeamNormalAvgInfo(PrintStream out, ArrayList<TeamSimpleSeasonVO> vos, int count) {
		if (vos.size() < count) count = vos.size();
		int i;
		for (i = 0; i<count;i++) {
			TeamSimpleSeasonVO vo = vos.get(i);
			TeamNormalInfo info = new TeamNormalInfo();
			info.setAssist(vo.assistAvg);
			info.setBlockShot(vo.blockAvg);
			info.setDefendRebound(vo.defensiveReboundAvg);
			info.setFault(vo.turnoverAvg);
			info.setFoul(vo.foulAvg);
			info.setNumOfGame(vo.matchCount);
			info.setOffendRebound(vo.offensiveReboundAvg);
			info.setPenalty(vo.freethrowPercent);
			info.setPoint(vo.scoreAvg);
			info.setRebound(vo.totalReboundAvg);
			info.setShot(vo.fieldPercent);
			info.setSteal(vo.stealAvg);
			info.setTeamName(vo.teamName);
			info.setThree(vo.threePointPercent);
			out.println(i+1);
			out.println(info);
		}
	}
	
	private void outputTeamNormalTotalInfo(PrintStream out, ArrayList<TeamSimpleSeasonVO> vos, int count) {
		if (vos.size() < count) count = vos.size();
		int i;
		for (i=0;i<count;i++) {
			TeamSimpleSeasonVO vo = vos.get(i);
			TeamNormalInfo info = new TeamNormalInfo();
			info.setAssist(vo.assist);
			info.setBlockShot(vo.block);
			info.setDefendRebound(vo.defensiveRebound);
			info.setFault(vo.turnover);
			info.setFoul(vo.foul);
			info.setNumOfGame(vo.matchCount);
			info.setOffendRebound(vo.offensiveRebound);
			info.setPenalty(vo.freethrowPercent);
			info.setPoint(vo.score);
			info.setRebound(vo.totalRebound);
			info.setShot(vo.fieldPercent);
			info.setSteal(vo.steal);
			info.setTeamName(vo.teamName);
			info.setThree(vo.threePointPercent);
			out.println(i+1);
			out.println(info);
		}
	}
	
	private void outputTeamHighInfo(PrintStream out, ArrayList<TeamSimpleSeasonVO> vos, int count) {
		if (vos.size() < count) count = vos.size();
		int i;
		for (i=0;i<count;i++) {
			TeamSimpleSeasonVO vo = vos.get(i);
			TeamHighInfo info = new TeamHighInfo();
			info.setAssistEfficient(vo.assistEff);
			info.setDefendEfficient(vo.defensiveEff);
			info.setDefendReboundEfficient(vo.defensiveReboundEff);
			info.setOffendEfficient(vo.offensiveEff);
			info.setOffendReboundEfficient(vo.offensiveReboundEff);
			info.setOffendRound(vo.offensiveRound);
			info.setStealEfficient(vo.stealEff);
			info.setTeamName(vo.teamName);
			info.setWinRate(vo.winning);
			out.println(i+1);
			out.println(info);
		}
	}
	
	private void outputTeamHotInfo(PrintStream out, String field, ArrayList<TeamSimpleSeasonVO> vos, int count) {
		if (vos.size() < count) count = vos.size();
		int i;
		for (i=0;i<count;i++) {
			TeamSimpleSeasonVO vo = vos.get(i);
			TeamHotInfo info = new TeamHotInfo();
			info.setField(field);
			info.setLeague(SimpleConstants.getLeagueByAbbr(vo.teamName));
			info.setTeamName(vo.teamName);
			switch (field) {
			case "score":
				info.setValue(vo.scoreAvg);
				break;
			case "rebound":
				info.setValue(vo.totalReboundAvg);
				break;
			case "assist":
				info.setValue(vo.assistAvg);
				break;
			case "blockShot":
				info.setValue(vo.blockAvg);
				break;
			case "steal":
				info.setValue(vo.stealAvg);
				break;
			case "foul":
				info.setValue(vo.foulAvg);
				break;
			case "fault":
				info.setValue(vo.turnoverAvg);
				break;
			case "shot":
				info.setValue(vo.fieldPercent);
				break;
			case "three":
				info.setValue(vo.threePointPercent);
				break;
			case "penalty":
				info.setValue(vo.freethrowPercent);
				break;
			case "defendRebound":
				info.setValue(vo.defensiveReboundAvg);
				break;
			case "offendRebound":
				info.setValue(vo.offensiveReboundAvg);
				break;
			default:
				break;
			}
			out.println(i+1);
			out.println(info);
		}
	}
	
	private void outputPlayerHotInfo(String field, PrintStream out, ArrayList<PlayerSimpleSeasonVO> vos, int count) {
		int i;
		switch (field) {
		case "score":
			for (i=0; i< count;i++){
				PlayerSimpleSeasonVO vo = vos.get(i);
				PlayerHotInfo info = new PlayerHotInfo();
				info.setField(field);
				info.setName(vo.name);
				info.setPosition(vo.position);
				info.setTeamName(vo.teamName);
				info.setValue(vo.scoreAvg);
				info.setUpgradeRate(vo.scorePromotion);
				out.println(i+1);
				out.println(info);
			}
			return;
		case "assist":
			for (i=0; i< count;i++){
				PlayerSimpleSeasonVO vo = vos.get(i);
				PlayerHotInfo info = new PlayerHotInfo();
				info.setField(field);
				info.setName(vo.name);
				info.setPosition(vo.position);
				info.setTeamName(vo.teamName);
				info.setValue(vo.assistAvg);
				info.setUpgradeRate(vo.assistPromotion);
				out.println(i+1);
				out.println(info);
			}
			return;
		default:
			for (i=0; i< count;i++){
				PlayerHotInfo info = new PlayerHotInfo();
				PlayerSimpleSeasonVO vo = vos.get(i);
				info.setField(field);
				info.setName(vo.name);
				info.setPosition(vo.position);
				info.setTeamName(vo.teamName);
				info.setValue(vo.totalReboundAvg);
				info.setUpgradeRate(vo.reboundPromotion);
				out.println(i+1);
				out.println(info);
			}
		}
	}
	
	private void outputPlayerNormalAvgInfo(PrintStream out, ArrayList<PlayerSimpleSeasonVO> vos,int count) {
		int i;
		for (i=0;i<count;i++) {
			PlayerNormalInfo info = new PlayerNormalInfo();
			PlayerSimpleSeasonVO vo = vos.get(i);
			info.setAge(vo.age);
			info.setAssist(vo.assistAvg);
			info.setBlockShot(vo.blockAvg);
			info.setDefend(vo.defensiveReboundAvg);
			info.setEfficiency(vo.efficiencyAvg);
			info.setFault(vo.turnoverAvg);
			info.setFoul(vo.foulAvg);
			info.setMinute(vo.minutesAvg);
			info.setName(vo.name);
			info.setNumOfGame(vo.matchCount);
			info.setOffend(vo.offensiveReboundAvg);
			info.setPenalty(vo.freethrowPercent);
			info.setPoint(vo.scoreAvg);
			info.setRebound(vo.totalReboundAvg);
			info.setShot(vo.fieldPercent);
			info.setStart(vo.firstCount);
			info.setSteal(vo.stealAvg);
			info.setTeamName(vo.teamName);
			info.setThree(vo.threePointPercent);
			out.println(i+1);
			out.println(info);
		}
	}
	
	private void outputPlayerNormalTotalInfo(PrintStream out, ArrayList<PlayerSimpleSeasonVO> vos,int count) {
		int i;
		for (i=0;i<count;i++) {
			PlayerNormalInfo info = new PlayerNormalInfo();
			PlayerSimpleSeasonVO vo = vos.get(i);
			info.setAge(vo.age);
			info.setAssist(vo.assist);
			info.setBlockShot(vo.block);
			info.setDefend(vo.defensiveRebound);
			info.setEfficiency(vo.efficiencyAvg);	//TODO 效率区分场均和总数吗
			info.setFault(vo.turnover);
			info.setFoul(vo.foul);
			info.setMinute(vo.minutes);
			info.setName(vo.name);
			info.setNumOfGame(vo.matchCount);
			info.setOffend(vo.offensiveRebound);
			info.setPenalty(vo.freethrowPercent);
			info.setPoint(vo.score);
			info.setRebound(vo.totalRebound);
			info.setShot(vo.fieldPercent);
			info.setStart(vo.firstCount);
			info.setSteal(vo.steal);
			info.setTeamName(vo.teamName);
			info.setThree(vo.threePointPercent);
			out.println(i+1);
			out.println(info);
		}
	}
	
	private void outputPlayerKingInfo(PrintStream out, String field, String period, ArrayList<PlayerSimpleSeasonVO> vos, int count) {
		for (int i=0;i<count;i++) {
			PlayerKingInfo info = new PlayerKingInfo();
			PlayerSimpleSeasonVO vo = vos.get(i);
			info.setName(vo.name);
			info.setField(field);
			info.setPosition(vo.position);	//TODO 位置到底是什么
			info.setTeamName(vo.teamName);
			if (period.equals("-season") && field.equals("score")) {
				info.setValue(vo.scoreAvg);
			}else if (period.equals("-season") && field.equals("rebound")) {
				info.setValue(vo.totalReboundAvg);
			}else if (period.equals("-season") && field.equals("assist")) {
				info.setValue(vo.assistAvg);
			}else if (field.equals("score")){
				info.setValue(vo.latestScore);
			}else if (field.equals("rebound")) {
				info.setValue(vo.latestRebound);
			}else {
				info.setValue(vo.latestAssist);
			}
			out.println(i+1);
			out.println(info);
		}
	}
	
	private void outputPlayerHighInfo(PrintStream out, ArrayList<PlayerSimpleSeasonVO> vos, int count) {
		for (int i=0;i<count;i++) {
			PlayerSimpleSeasonVO vo = vos.get(i);
			PlayerHighInfo info = new PlayerHighInfo();
			info.setAssistEfficient(vo.assistPercent);
			info.setBlockShotEfficient(vo.blockPercent);
			info.setDefendReboundEfficient(vo.defensiveReboundPercent);
			info.setFaultEfficient(vo.turnOverPercent);
			info.setFrequency(vo.usePercent);
			info.setGmSc(vo.gmscAvg);
			info.setLeague(SimpleConstants.getLeagueByAbbr(vo.teamName));
			info.setName(vo.name);
			info.setOffendReboundEfficient(vo.offensiveReboundPercent);
			info.setPosition(vo.position);
			info.setRealShot(vo.realFieldPercent);
			info.setReboundEfficient(vo.totalReboundPercent);
			info.setShotEfficient(vo.fieldEff);
			info.setStealEfficient(vo.stealPercent);
			info.setTeamName(vo.teamName);
			out.println(i+1);
			out.println(info);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private int getRealCount(ArrayList list, int needed) {	
		int sizeHad  = list.size();
		if (needed < sizeHad) return needed;
		else return sizeHad;
	}
	
	private ArrayList<PlayerSimpleSeasonVO> getFilteredPlayerSeasonData(
			String position, String league, String age) {
		HashMap<String, PlayerSimpleSeasonVO> playerRecords = SeasonSimpleData.playerRecords;
		/** position = F G C表示按位置筛选，""表示无要求。 league为"East或West" */

		if (position.equals("All"))
			position = "";
		if (league.equals("All"))
			league = "";

		int ageMin;
		int ageMax;
		switch (age) {
		case "<=22":
			ageMin = 0;
			ageMax = 22;
			break;
		case "22< X <=25":
			ageMin = 22;
			ageMax = 25;
			break;
		case "25< X <=30":
			ageMin = 25;
			ageMax = 30;
			break;
		case ">30":
			ageMin = 30;
			ageMax = 128;
			break;
		default:
			ageMin = -1;
			ageMax = 128;
		}

		Iterator<Map.Entry<String, PlayerSimpleSeasonVO>> itr = playerRecords
				.entrySet().iterator();
		ArrayList<PlayerSimpleSeasonVO> result = new ArrayList<PlayerSimpleSeasonVO>();

		while (itr.hasNext()) {
			PlayerSimpleSeasonVO vo = itr.next().getValue(); // TODO 这个条件可以优化
			if (vo.position.contains(position)
					&& SimpleConstants.getLeagueByAbbr(vo.teamName).equals(league)
					&& vo.age > ageMin && vo.age <= ageMax) { 
				result.add(vo);
			}
		}
		return result;
	}

}
