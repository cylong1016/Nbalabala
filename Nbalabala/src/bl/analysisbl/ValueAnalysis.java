/**
 * 
 */
package bl.analysisbl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import po.AdvancedDataPO;
import po.ClutchPO;
import po.MatchPlayerPO;
import po.PlayerProfilePO;
import ui.UIConfig;
import utility.Constants;
import utility.Utility;
import vo.AnalysisCareerVO;
import vo.AnalysisCompareVO;
import vo.AnalysisTransferVO;
import vo.ForecastVO;
import vo.YearMatchesVO;
import blservice.AnalysisBLService;
import data.advanceddata.AdvancedData;
import data.matchdata.MatchData;
import data.playerdata.PlayerData;
import data.seasondata.SeasonData;
import enums.CareerData;
import enums.InferenceData;

/**
 *
 * @author Issac Ding
 * @since 2015年6月10日 下午9:47:48
 * @version 1.0
 */
public class ValueAnalysis implements AnalysisBLService{
	
	private MatchData matchData = new MatchData();
	
	private SeasonData seasonData = new SeasonData();
	private AdvancedData advancedData = new AdvancedData();
	
	private PlayerData playerData = new PlayerData();
	
	private ArrayList<MatchPlayerPO> matches = new ArrayList<MatchPlayerPO>();
	private String lastPlayer = null;
	
	private ArrayList<YearMatchesVO> yearMatchList = new ArrayList<YearMatchesVO>();
	private String lastPlayerByYear = null;

	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getCareerData(java.lang.String, enums.CareerData)
	 */
	@Override
	public ArrayList<AnalysisCareerVO> getCareerData(String name,
			CareerData careerData) {
		loadMatchesByYear(name);
		ArrayList<AnalysisCareerVO> result = new ArrayList<AnalysisCareerVO>();
		for (YearMatchesVO vo : yearMatchList) {
			String season = Utility.getOverallSeason(vo.year);
			ArrayList<MatchPlayerPO> matches = vo.matchList;
			ArrayList<Double> oneSeasonData = new ArrayList<Double>();
			if (careerData == CareerData.SCORE) {
				for (MatchPlayerPO po : matches) {
					oneSeasonData.add((double) po.score);
				}
			}else if (careerData == CareerData.REBOUND) {
				for (MatchPlayerPO po : matches) {
					oneSeasonData.add((double) po.totalRebound);
				}
			}else if (careerData == CareerData.ASSIST) {
				for (MatchPlayerPO po : matches) {
					oneSeasonData.add((double) po.assist);
				}
			}else if (careerData == CareerData.FIELD_PERCENT) {
				for (MatchPlayerPO po : matches) {
					oneSeasonData.add((double) po.fieldPercent);
				}
			}else {
				for (MatchPlayerPO po : matches) {
					oneSeasonData.add((double) po.threepointPercent);
				}
			}
			AnalysisCareerVO analysisCareerVO = new AnalysisCareerVO(season, oneSeasonData);
			result.add(analysisCareerVO);
		}
		return result;
	}


	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getClutchData(java.lang.String)
	 */
	@Override
	public ArrayList<ClutchPO> getClutchData(String teamAbbr) {
		ArrayList<String> lineup = getLineupNamesByAbbr(teamAbbr);
		ArrayList<ClutchPO> result = new ArrayList<ClutchPO>();
		for (String name : lineup) {
			result.add(advancedData.getClutchData(name, Constants.LATEST_SEASON_REGULAR));
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getDevotionData(java.lang.String)
	 */
	@Override
	public ArrayList<AdvancedDataPO> getDevotionData(String teamAbbr) {
		ArrayList<String> lineup = getLineupNamesByAbbr(teamAbbr);
		ArrayList<AdvancedDataPO> result = new ArrayList<AdvancedDataPO>();
		for (String name : lineup) {
			result.add(advancedData.getAdvancedData(name, Constants.LATEST_SEASON_REGULAR));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getForecastData(java.lang.String, enums.ForecastData)
	 */
	@Override
	public ForecastVO getForecastData(String name, InferenceData inferenceData) {
		loadMatches(name);
		DivideHandler divideHandler = new DivideHandler();
		ArrayList<MatchPlayerPO> partMatches = new ArrayList<MatchPlayerPO>(matches.subList(matches.size()/4, matches.size())); //TODO
		ArrayList<Double> data = divideHandler.divideData(partMatches, inferenceData, matches.size());
		RegressionHandler regression;
		try {
			regression = new RegressionHandler(data);
		} catch (Exception e) {
			return null;
		}
		ForecastVO result = new ForecastVO();
		result.width = divideHandler.getWidth();
		
		PlayerProfilePO profilePO = playerData.getPlayerProfileByName(name);
		result.toSeason = Utility.getOverallSeason(profilePO.toYear - 1);
		result.fromSeason = Utility.getOverallSeason(profilePO.fromYear);
		result.nextY = regression.getNextValueByRegression();
		result.name = name;
		result.datas = data;
		result.curveX = regression.getCurveX();
		result.curveY = regression.getCurveY();
		
		DecimalFormat format = UIConfig.FORMAT;
		if (inferenceData == InferenceData.FIELD_PERCENT || inferenceData == InferenceData.THREEPOINT_PERCENT
				||inferenceData == InferenceData.FREETHROW_PERCENT 
				|| inferenceData == InferenceData.REAL_FIELD_PERCENT) {
			format = UIConfig.PERCENT_FORMAT;
		}
		String valueString = format.format(result.nextY);
		
		result.conclusion = "通过多项式回归分析，认为该球员此项数据在短期内将为" + valueString;
		return result;
	}
	
	public static void main(String[]args) {
	}
	//TODO 明显下降的球员：Steve Francis

	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getTransferData(java.lang.String, enums.InferenceData)
	 */
	@Override
	public AnalysisTransferVO getTransferData(String name,
			InferenceData inferenceData) {
		PlayerProfilePO profilePO = playerData.getPlayerProfileByName(name);
		int fromYear = profilePO.fromYear;
		int toYear = profilePO.toYear;
		String team = null;
		String currentTeam = null;
		int transferYear = 0;
		int startYear = 0;		// 从哪一年开始在上一支球队服役
		boolean hasTransfer = false;
		for (int i = toYear - 1; i >= fromYear; i--) {
			String season = Utility.getRegularStringByStartYear(i);
			String thatTeam = seasonData.getPlayerSeasonDataByName(name, season).teamAbbr;
			if (thatTeam.equals(Constants.UNKNOWN)) continue;
			if (team == null) {
				team = thatTeam;
				currentTeam = team;
			}else if ((!team.equals(thatTeam)) && (!hasTransfer)) {
				team = thatTeam;
				transferYear = i + 1;
				hasTransfer = true;
			}else if ((!team.equals(thatTeam)) && (hasTransfer)) {
				startYear = i + 1;
				break;
			}
		}
		if (!hasTransfer) return null;	//没有转会过
		if (startYear == 0) startYear = fromYear;
		
		loadMatchesByYear(name);
		ArrayList<MatchPlayerPO> formerMatches = new ArrayList<MatchPlayerPO>();
		ArrayList<MatchPlayerPO> currentMatches = new ArrayList<MatchPlayerPO>();
		for (YearMatchesVO vo : yearMatchList) {
			if (vo.year >= startYear && vo.year < transferYear) {
				formerMatches.addAll(vo.matchList);
			}else if (vo.year >= transferYear) {
				currentMatches.addAll(vo.matchList);
			}
		}
		
		DivideHandler divideHandler = new DivideHandler();
		int smallerSize = Math.min(formerMatches.size(), currentMatches.size());
		ArrayList<Double> formerData = divideHandler.divideData(formerMatches, inferenceData, smallerSize);
		ArrayList<Double> currentData = divideHandler.divideData(currentMatches, inferenceData, smallerSize);
		String conclusion = new TAnalyzer(formerData, currentData, inferenceData).getTransferConclusion();
		
		AnalysisTransferVO result = new AnalysisTransferVO();
		result.conclusion = conclusion;
		result.currentAbbr = currentTeam;
		result.formerAbbr = team;
		result.currentData = currentData;
		result.formerData = formerData;
		result.name = name;
		result.width = divideHandler.getWidth();
		result.startSeason = Utility.getOverallSeason(startYear);
		result.transferSeason = Utility.getOverallSeason(transferYear);
		return result;
	}
	
	private void loadMatches(String name) {
		if (lastPlayer != null && lastPlayer.equals(name) && matches.size() != 0) {
			return;
		}else {
			matches.clear();
			lastPlayer = name;
			PlayerProfilePO profilePO = playerData.getPlayerProfileByName(name);
			if (profilePO == null) return ;
			int fromYear = profilePO.fromYear;
			int toYear = profilePO.toYear;
			for (int i = fromYear; i < toYear; i++) {
				String season = Utility.getRegularStringByStartYear(i);
				matches.addAll(matchData.getMatchRecordByPlayerName(name, season));
			}
		}
	}
	
	private void loadMatchesByYear(String name) {
		if (lastPlayerByYear != null && lastPlayerByYear.equals(name) && yearMatchList.size() != 0) {
			return;
		}else {
			yearMatchList.clear();
			lastPlayerByYear = name;
			PlayerProfilePO profilePO = playerData.getPlayerProfileByName(name);
			if (profilePO == null) return ;
			int fromYear = profilePO.fromYear;
			int toYear = profilePO.toYear;
			for (int i = fromYear; i < toYear; i++) {
				String season = Utility.getRegularStringByStartYear(i);
				YearMatchesVO year= new YearMatchesVO();
				year.matchList = matchData.getMatchRecordByPlayerName(name, season);
				year.year = i;
				yearMatchList.add(year);
			}
		}
	}


	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getLineupNamesByAbbr(java.lang.String)
	 */
	@Override
	public ArrayList<String> getLineupNamesByAbbr(String abbr) {
		ArrayList<String> list = new SeasonData().getPlayerNamesByTeamAbbr(abbr, Constants.LATEST_SEASON_REGULAR);
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		};
		java.util.Collections.sort(list, comparator);
		return list;
	}


	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getCompareData(java.lang.String, java.lang.String)
	 */
	@Override
	public AnalysisCompareVO getCompareData(String thisName, String thatName, InferenceData inferenceData) throws Exception{
		loadMatches(thisName);
		ArrayList<MatchPlayerPO> thatMatches = new ArrayList<MatchPlayerPO>();
		PlayerProfilePO profilePO = playerData.getPlayerProfileByName(thatName);
		int fromYear = profilePO.fromYear;
		int toYear = profilePO.toYear;
		for (int i = fromYear; i < toYear; i++) {
			String season = Utility.getRegularStringByStartYear(i);
			thatMatches.addAll(matchData.getMatchRecordByPlayerName(thatName, season));
		}
		int smaller = Math.min(matches.size(), thatMatches.size());
		ArrayList<MatchPlayerPO> smallerList = matches.size() < thatMatches.size()? matches: thatMatches;
		DivideHandler divideHandler = new DivideHandler();
		ArrayList<MatchPlayerPO> partThis = new ArrayList<MatchPlayerPO>
			(matches.subList(matches.size() - smaller, matches.size()));
		ArrayList<MatchPlayerPO> partThat = new ArrayList<MatchPlayerPO>
			(thatMatches.subList(thatMatches.size() - smaller, thatMatches.size()));
		ArrayList<Double> thisData = divideHandler.divideData(partThis, inferenceData, smaller);
		ArrayList<Double> thatData = divideHandler.divideData(partThat, inferenceData, smaller);
		if (thisData.size() < 2 && smallerList == matches ) return null;
		if (thatData.size() < 2 && smallerList == thatMatches ) throw new Exception();
		String conclusion = new TAnalyzer(thisData, thatData, inferenceData)
			.getCompareConclusion(Utility.trimName(thisName), Utility.trimName(thatName));
		AnalysisCompareVO result = new AnalysisCompareVO();
		result.thisName = Utility.trimName(thisName);
		result.thatName = Utility.trimName(thatName);
		result.thisData = thisData;
		result.thatData = thatData;
		int start1 = Integer.parseInt(partThis.get(0).season.substring(0,4));
		int start2 = Integer.parseInt(partThat.get(0).season.substring(0,4));
		result.startSeason = Utility.getOverallSeason(Math.min(start1, start2));
		result.endSeason = Constants.LATEST_SEASON_OVERALL;
		result.conclusion = conclusion;
		return result;
	}
	

}

