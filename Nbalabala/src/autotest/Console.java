package autotest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.data.PlayerHotInfo;
import test.data.PlayerNormalInfo;
import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import utility.Constants;
import autotest.playertest.PlayerSimpleSeasonVO;
import autotest.playertest.PlayerTestSortHandler;
import autotest.playertest.SimplePlayerAvgSorter;
import autotest.teamtest.SimpleTeamAvgAndHighSorter;
import autotest.teamtest.TeamSimpleSeasonVO;
import autotest.teamtest.TeamTestSortHandler;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月6日  下午9:12:51
 */
public class Console {
	
	private SeasonSimpleData seasonData = new SeasonSimpleData();
	
	public static void main(String[]args) {
		String[]s = {"-team", "-avg" ,"-n", "5", "-sort", "shot.asc,winrate.desc"};
		
		try {
			PrintStream printStream = new PrintStream(new File("dxh.txt"));
			new Console().execute(printStream, s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void execute(java.io.PrintStream out, java.lang.String[] args) {
		if (args == null || args.length ==0) return;
		switch (args[0]) {
		case "--datasource":
			Constants.changeDataSourcePath(args[1]);
			return;
		case "-player":
			ArrayList<PlayerSimpleSeasonVO> playerVOs;
			int neededPlayerCount = 50;
			//默认情况下
			if (args.length < 2) {
				playerVOs = seasonData.getAllPlayerSeasonData();
				if (playerVOs.size() < 50)
					neededPlayerCount = playerVOs.size();
				Collections.sort(playerVOs, SimplePlayerAvgSorter.getPlayerAvgAndHighComparator("score", "desc"));
				outputPlayerNormalAvgInfo(out, playerVOs, neededPlayerCount);
			}else if(args[1].equals("-hot")) {
				playerVOs = seasonData.getAllPlayerSeasonData();
				Collections.sort(playerVOs, SimplePlayerAvgSorter.getPlayerAvgAndHighComparator(args[2], "desc"));
				outputPlayerHotInfo(args[2], out, playerVOs, getNeededPlayerCount(args));
			}else if(args[1].equals("-king")) {
				playerVOs = seasonData.getAllPlayerSeasonData();
//				outputPlayerKingInfo(args[2], args[3], );
			}
			
			else{
				neededPlayerCount = getNeededPlayerCount(args);
				playerVOs = getFilteredPlayers(args);
				PlayerTestSortHandler.playerSortHandle(args, playerVOs);
				
			}
			return;
		case "-team": //TODO hot king 的默认数量是多少
			ArrayList<TeamSimpleSeasonVO> teamVOs = seasonData.getAllTeamSeasonData();
			int neededTeamCount = 30;
			if (args.length < 2) {
				Comparator<TeamSimpleSeasonVO> comparator = 
						SimpleTeamAvgAndHighSorter.getTeamAvgAndHighComparator("point", "desc");
				Collections.sort(teamVOs, comparator);
				outputTeamNormalAvgInfo(out, teamVOs, 30);
			}else{
				
				neededTeamCount = getNeededTeamCount(args);
				TeamTestSortHandler.sortTeamByArgs(args, teamVOs);
				
				switch(args[1]) {
				case "-hot":
					outputTeamHotInfo(out, args[2], teamVOs, neededTeamCount);
					break;
				case "-high":
					outputTeamHighInfo(out, teamVOs, neededTeamCount);
					break;
				case "-total":
					outputTeamNormalTotalInfo(out, teamVOs, neededTeamCount);
					break;
				default:
					outputTeamNormalAvgInfo(out, teamVOs, neededTeamCount);
				}
			}
		}

	}
	
	private int getNeededTeamCount(String[]args) {
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("-n"))
				return Integer.parseInt(args[i + 1]);
		}
		return 30;
	}
	
	private int getNeededPlayerCount(String[]args) {
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("-n"))
				return Integer.parseInt(args[i + 1]);
		}
		return 50;
	}
	
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
				return seasonData.getFilteredPlayerSeasonData(positionArg, leagueArg, ageArg);
			}
		}
		return seasonData.getAllPlayerSeasonData();
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
			out.print(info);
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
			out.print(info);
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
			info.setOffendEfficient(vo.offensiveReboundEff);
			info.setOffendReboundEfficient(vo.offensiveReboundEff);
			info.setOffendRound(vo.offensiveRound);
			info.setStealEfficient(vo.stealEff);
			info.setTeamName(vo.teamName);
			info.setWinRate(vo.winning);
			out.print(info);
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
			out.print(info);
		}
	}
	
//	private void outputPlayerNormalTotalInfo(PrintStream out, PlayerSimpleSeasonVO vo) {
//		PlayerNormalInfo info = new PlayerNormalInfo();
//		info.setAge(vo.age);
//		info.setAssist(vo.assist);
//		info.setBlockShot(vo.block);
//		info.setDefend(vo.defensiveRebound);
//		info.setEfficiency(vo.efficiency);
//		info.setFault(vo.turnover);
//		info.setFoul(vo.foul);
//		info.setMinute(vo.minutes);
//		info.setName(vo.name);
//		info.setNumOfGame(vo.matchCount);
//		info.setOffend(vo.offensiveRebound);
//		info.setPenalty(vo.freethrowPercent);
//		info.setPoint(vo.score);
//		info.setRebound(vo.totalRebound);
//		info.setShot(vo.fieldPercent);
//		info.setStart(vo.firstCount);
//		info.setSteal(vo.steal);
//		info.setTeamName(vo.teamName);
//		info.setThree(vo.threePointPercent);
//	}
	
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
		}
		
	}
	
//	private void outputPlayerKingInfo(String[]field, String period, ArrayList<PlayerSimpleSeasonVO> vos) {
//		Comparator<PlayerSimpleSeasonVO> comparator = null;
//	}
	
	


}
