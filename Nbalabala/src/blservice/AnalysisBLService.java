/**
 * 
 */
package blservice;

import java.util.ArrayList;

import po.AdvancedDataPO;
import po.ClutchPO;
import vo.AnalysisCareerVO;
import vo.AnalysisTransferVO;
import vo.ForecastVO;
import enums.CareerData;
import enums.InferenceData;

/**
 *
 * @author Issac Ding
 * @since 2015年6月9日 上午10:17:24
 * @version 1.0
 */
public interface AnalysisBLService {
	
	/** 返回某一球员某一项数据，用于盒状图展示 */
	public ArrayList<AnalysisCareerVO> getCareerData(String name, CareerData careerData);
	
	/** 返回某一球员的最后5分钟决胜数据（同一个人，不同赛季） */
	public ArrayList<ClutchPO> getClutchData(String name);
	
	/** 返回某一球员及其所属球队所有人的团队贡献值 */
	public ArrayList<AdvancedDataPO> getDevotionData(String name);
	
	/** 返回某一球员的用于预测数据的样本点 */
	public ForecastVO getForecastData(String name, InferenceData inferenceData);
	
	/** 返回某一球员转会分析所需数据 */
	public AnalysisTransferVO getTransferData(String name, InferenceData inferenceData);

	public ArrayList<String> getLineupNamesByAbbr(String abbr);
}
