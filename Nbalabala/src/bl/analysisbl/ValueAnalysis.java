/**
 * 
 */
package bl.analysisbl;

import java.util.ArrayList;

import po.MatchPlayerPO;
import po.PlayerProfilePO;
import data.matchdata.MatchData;
import data.playerdata.PlayerData;
import data.seasondata.SeasonData;
import ui.common.panel.ScorePanel;
import utility.Utility;
import vo.AnalysisCareerVO;
import vo.AnalysisClutchVO;
import vo.AnalysisDevotionVO;
import vo.AnalysisTransferVO;
import vo.ForecastVO;
import enums.CareerData;
import enums.InferenceData;
import DistLib.t;
import blservice.AnalysisBLService;

/**
 *
 * @author Issac Ding
 * @since 2015年6月10日 下午9:47:48
 * @version 1.0
 */
public class ValueAnalysis implements AnalysisBLService{
	
	private MatchData matchData = new MatchData();
	
	private SeasonData seasonData = new SeasonData();
	
	private PlayerData playerData = new PlayerData();

	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getCareerData(java.lang.String, enums.CareerData)
	 */
	@Override
	public ArrayList<AnalysisCareerVO> getCareerData(String name,
			CareerData careerData) {
		ArrayList<AnalysisCareerVO> result = new ArrayList<AnalysisCareerVO>();
		PlayerProfilePO profilePO = playerData.getPlayerProfileByName(name);
		if (profilePO == null) return result;
		int fromYear = profilePO.fromYear;
		int toYear = profilePO.toYear;
		for (int i = fromYear; i < toYear; i++) {
			String season = Utility.getRegularStringByStartYear(i);
			ArrayList<MatchPlayerPO> matches = matchData.getMatchRecordByPlayerName(name, season);
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
			result.add(new AnalysisCareerVO(season, oneSeasonData));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getClutchData(java.lang.String)
	 */
	@Override
	public ArrayList<AnalysisClutchVO> getClutchData(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getDevotionData(java.lang.String)
	 */
	@Override
	public ArrayList<AnalysisDevotionVO> getDevotionData(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getForecastData(java.lang.String, enums.ForecastData)
	 */
	@Override
	public ForecastVO getForecastData(String name, InferenceData forecastData) {
		ArrayList<MatchPlayerPO> matches = new ArrayList<MatchPlayerPO>();
		
		PlayerProfilePO profilePO = playerData.getPlayerProfileByName(name);
		// TODO if (profilePO == null) return result;
		int fromYear = profilePO.fromYear;
		int toYear = profilePO.toYear;
		for (int i = fromYear; i < toYear; i++) {
			String season = Utility.getRegularStringByStartYear(i);
			ArrayList<MatchPlayerPO> oneSeasonMatch = matchData.getMatchRecordByPlayerName(name, season);
			matches.addAll(oneSeasonMatch);
		}
	
		ArrayList<Double> data = new DivideHandler().divideData(matches, forecastData);
		new RegressionHandler().getNextValueByRegression(data);
		return null;
	}
	
	public static void main(String[]args) {
//		new ValueAnalysis().getForecastData("Kobe Bryant$01", ForecastData.REAL_FIELD_PERCENT);
		System.out.println(t.quantile(0.05, 18));
		
	}

	/* (non-Javadoc)
	 * @see blservice.AnalysisBLService#getTransferData(java.lang.String, enums.InferenceData)
	 */
	@Override
	public AnalysisTransferVO getTransferData(String name,
			InferenceData inferenceData) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

