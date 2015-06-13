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
import utility.Utility;
import vo.AnalysisCareerVO;
import vo.AnalysisClutchVO;
import vo.AnalysisDevotionVO;
import vo.ForecastVO;
import enums.CareerData;
import enums.ForecastData;
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
	public ForecastVO getForecastData(String name, ForecastData forecastData) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
	    //X轴
	    double[] x = { 1, 2, 3, 4,5,6,7,8,9,10};
	    //Y轴
	    double[] y = { 1,4,9,16,25,26,49,64,81,100};
	    Polyfit polyfit = null;
	    Polyval polyval;
	    try {
	        //创建多项式拟合对象，其中的4表示是4次多项式拟合
	        polyfit = new Polyfit(x, y, 4);
	        polyval = new Polyval(x, polyfit);
	        for (int i = 0; i <= polyval.getYout().length - 1; i++) {
	          double bd = polyval.getYout()[i];
	          System.out.println(i + 1 + "\t" + bd);
	        }
	        double[]coes = polyfit.getPolynomialCoefficients();
	        for (int i=0;i<coes.length;i++) {
	        	System.out.println(coes[i]);
	        }
	    }catch (Exception e) {
	        System.out.println("Error : " + e.getMessage() + "\n");
	        e.printStackTrace();
	    }
	}

}
