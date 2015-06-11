/**
 * 
 */
package blservice;

import java.util.ArrayList;

import enums.CareerData;
import vo.AnalysisCareerVO;
import vo.AnalysisClutchVO;
import vo.AnalysisDevotionVO;

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
	public ArrayList<AnalysisClutchVO> getClutchData(String name);
	
	/** 返回某一球员及其所属球队所有人的团队贡献值 */
	public ArrayList<AnalysisDevotionVO> getDevotionData(String name);

}
