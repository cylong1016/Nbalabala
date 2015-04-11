package autotest;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bl.teamseasonbl.TeamAvgSorter;
import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import utility.Constants;
import data.seasondata.SeasonData;
import enums.ScreenDivision;
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
	
	public static void main(String[]args) {
		String[]s = {"-team", "-avg" ,"-n", "5", "-sort", "shot.asc,winrate.desc"};
		new Console().execute(System.out, s );
	}
	
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
				for (TeamSimpleSeasonVO vo : vos) {
					outputTeamNormalAvgInfo(out, vo);
				}
			}else{
				neededTeamCount = getNeededTeamCount(args);
				TeamTestSortHandler.teamSortHandle(args, vos);
				if (neededTeamCount > vos.size())
					neededTeamCount = vos.size();
				for (int i = 1;i<args.length;i++) {
					switch (args[i]) {
					case "-total":
						for (int j = 0;j<neededTeamCount;j++) {
							outputTeamNormalTotalInfo(out, vos.get(j));
						}
						return;
					case "-high":
						for (int j = 0;j<neededTeamCount;j++) {
							outputTeamHighInfo(out, vos.get(j));
						}
						return;
					case "-hot":
						int index = i + 1;
						for (int j = 0;j<neededTeamCount;j++) {
							outputTeamHotInfo(out, args[index], vos.get(j));
						}
						return;
					default:
						break;
					}
				}
				// 如果没有发现这三个字段，就是-avg -all了
				for (int j = 0;j<neededTeamCount;j++) {
					outputTeamNormalAvgInfo(out, vos.get(j));
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
	
	private void outputTeamNormalAvgInfo(PrintStream out, TeamSimpleSeasonVO vo) {
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
	
	private void outputTeamNormalTotalInfo(PrintStream out, TeamSimpleSeasonVO vo) {
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
	
	private void outputTeamHighInfo(PrintStream out, TeamSimpleSeasonVO vo) {
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
	
	private void outputTeamHotInfo(PrintStream out, String field, TeamSimpleSeasonVO vo) {
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
