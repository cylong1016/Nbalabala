/**
 * 
 */
package vo;

import java.util.ArrayList;

/**
 * 生涯数据的每个赛季对应一个本VO
 * @author Issac Ding
 * @since 2015年6月9日 上午10:25:57
 * @version 1.0
 */
public class AnalysisCareerVO {
	

	private String season;
	
	/** 这一赛季的数据点 */
	private ArrayList<Double> datas;
	
	
	public AnalysisCareerVO(String season, ArrayList<Double> datas) {
		super();
		this.season = season;
		this.datas = datas;
	}

	public String getSeason() {
		return season;
	}

	public ArrayList<Double> getDatas() {
		return datas;
	}
	
	

}
