/**
 * 
 */
package vo;

import java.util.ArrayList;

/**
 *
 * @author Issac Ding
 * @since 2015年6月12日 上午11:08:14
 * @version 1.0
 */
public class ForecastVO {
	
	public String name;
	public double width;	//组距	(每多少场比赛划分为一组)
	public ArrayList<Double> datas;
	public int fromYear;	//起始年份
	public int toYear;
	public String conclusion;
	
	public String getName() {
		return name;
	}
	public ArrayList<Double> getDatas() {
		return datas;
	}
	public double getWidth() {
		return width;
	}
	public int getFromYear() {
		return fromYear;
	}
	public int getToYear() {
		return toYear;
	}
	public String getConclusion() {
		return conclusion;
	}

}
