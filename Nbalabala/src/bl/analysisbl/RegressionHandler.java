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
	
	private double[]coes;
	
	private int size = 0;
	
	private double [] curveX;
	
	private double [] curveY;
	
	private static final double X_INTER = 0.02;	//曲线上两点之间间隔
	private static final int DOTS_BETWEEN = 50;
	
	public RegressionHandler(ArrayList<Double> obs) throws Exception{
		size = obs.size();
		double [] x = new double[size];
		double [] y = new double[size];
		for (int i=0; i<obs.size(); i++) {
			x[i] = i + 1;
			y[i] = obs.get(i);
		}
	    Polyfit polyfit = null;
	   
	    //创建多项式拟合对象，其中的4表示是4次多项式拟合
	    polyfit = new Polyfit(x, y, Math.min(4, obs.size() - 1));
	    coes = polyfit.getPolynomialCoefficients();
	    
	    curveX = new double[obs.size() * DOTS_BETWEEN];
	    curveY = new double[curveX.length];
	    
	    for (int i=0;i<curveX.length;i++) {
	    	curveX[i] = 1+ X_INTER * i;
	    	curveY[i] = getY(curveX[i]);
	    }
	}
	
	public double getNextValueByRegression() {
		double next = size + 1;
	    return getY(next);
	}
	
	public double [] getCurveX() {
		return curveX;
	}
	
	public double [] getCurveY() {
		return curveY;
	}
	
	private double getY(double x) {
		double result = 0;
		for (int i = 0;i<coes.length;i++) {
        	result += Math.pow(x, i) * coes[coes.length - 1 - i];	//计算出下一个值
        }
		return result;
	}
	

}
