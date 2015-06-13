/**
 * 
 */
package bl.analysisbl;

import java.util.ArrayList;

/**
 *
 * @author Issac Ding
 * @since 2015年6月13日 下午10:32:57
 * @version 1.0
 */
public class TransferAnalyzer {
	
	public String giveConclusion(ArrayList<Double> former, ArrayList<Double> current) {
		
	}
	
	private double avg(ArrayList<Double> data) {
		double sum = 0;
		for (double d : data) {
			sum += d;
		}
		return sum / data.size();
	}
	
	private double var(ArrayList<Double> data) {
		if (data.size() < 2) return 0;
		double avg = avg(data);
		double tmp = 0;
		for (double d : data) {
			tmp += (d - avg) * (d - avg);
		}
		return tmp / (data.size() - 1);
	}

}
