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
	public int width;	//组距	(每多少场比赛划分为一组)
	public ArrayList<Double> datas;// 离散点的纵坐标，横坐标是1,2,3,4...
	public String fromYear;	//起止年份
	public String toYear;	
	public String conclusion;	//预测结论
	public double nextY;	//预测的下一个点的Y值，其横坐标是datas.size() + 1.这个点要着重画出
	public double [] curveX;	//曲线上点的横坐标，间隔0.05
	public double [] curveY;	//曲线上点的纵坐标，间隔0.05
	
	public double getNextY() {
		return nextY;
	}
	public double [] getCurveX() {
		return curveX;
	}
	public double [] getCurveY() {
		return curveY;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Double> getDatas() {	
		return datas;
	}
	public double getWidth() {
		return width;
	}
	public String getFromYear() {
		return fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public String getConclusion() {
		return conclusion;
	}

}
