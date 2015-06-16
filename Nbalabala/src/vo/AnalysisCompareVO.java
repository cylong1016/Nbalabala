/**
 * 
 */
package vo;

import java.util.ArrayList;

/**
 *
 * @author Issac Ding
 * @since 2015年6月16日 下午6:47:55
 * @version 1.0
 */
public class AnalysisCompareVO {
	
	public String thisName;
	
	public String getThisName() {
		return thisName;
	}

	public String getThatName() {
		return thatName;
	}

	public ArrayList<Double> getThisData() {
		return thisData;
	}

	public ArrayList<Double> getThatData() {
		return thatData;
	}

	public String getStartSeason() {
		return startSeason;
	}

	public String getEndSeason() {
		return endSeason;
	}

	public String getConclusion() {
		return conclusion;
	}

	public String thatName;
	
	public ArrayList<Double> thisData;
	
	public ArrayList<Double> thatData;
	
	public String startSeason;
	
	public String endSeason;
	
	public String conclusion;

}
