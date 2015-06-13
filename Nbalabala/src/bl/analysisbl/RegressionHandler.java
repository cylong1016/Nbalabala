/**
 * 
 */
package bl.analysisbl;

import java.util.ArrayList;

import bl.analysisbl.regression.Polyfit;

/**
 *
 * @author Issac Ding
 * @since 2015年6月13日 下午9:24:12
 * @version 1.0
 */
public class RegressionHandler {
	
	public double getNextValueByRegression(ArrayList<Double> obs) {
		int next = obs.size() + 1;
		double [] x = new double[obs.size()];
		double [] y = new double[obs.size()];
		double result = 0;
		for (int i=0; i<obs.size(); i++) {
			x[i] = i + 1;
			y[i] = obs.get(i);
		}
	    Polyfit polyfit = null;
	    try {
	        //创建多项式拟合对象，其中的4表示是4次多项式拟合
	        polyfit = new Polyfit(x, y, 4);
	        double[]coes = polyfit.getPolynomialCoefficients();
	        for (int i = 0;i<coes.length;i++) {
	        	result += Math.pow(next, i) * coes[coes.length - 1 - i];	//计算出下一个值
	        }
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

}
