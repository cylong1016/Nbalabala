/**
 * 
 */
package vo;

import java.util.ArrayList;

/**
 *
 * @author Issac Ding
 * @since 2015年6月13日 下午10:27:03
 * @version 1.0
 */
public class AnalysisTransferVO {
	
	public String name;
	
	/** 之前球队缩写 */
	public String formerAbbr;
	/** 当前球队缩写 */
	public String currentAbbr;
	/** 转会的赛季 */
	public String transferSeason;
	
	public ArrayList<Double> formerData;	//转会前
	
	public ArrayList<Double> currentData;	//当前的数据
	/** 结论 */
	public String conclusion;
	public String getName() {
		return name;
	}
	public String getFormerAbbr() {
		return formerAbbr;
	}
	public String getCurrentAbbr() {
		return currentAbbr;
	}
	public String getTransferSeason() {
		return transferSeason;
	}
	public ArrayList<Double> getFormerData() {
		return formerData;
	}
	public ArrayList<Double> getCurrentData() {
		return currentData;
	}
	public String getConclusion() {
		return conclusion;
	}
	
}
