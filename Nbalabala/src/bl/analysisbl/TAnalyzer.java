/**
 * 
 */
package bl.analysisbl;

import java.text.DecimalFormat;
import java.util.ArrayList;

import ui.UIConfig;
import DistLib.t;
import enums.InferenceData;

/**
 *
 * @author Issac Ding
 * @since 2015年6月13日 下午10:32:57
 * @version 1.0
 */
public class TAnalyzer {
	
	private double left = 0;
	private double right = 0;
	private DecimalFormat format = UIConfig.FORMAT;
	
	
	public TAnalyzer(ArrayList<Double> former, ArrayList<Double> current,
			InferenceData inferenceData) {
		ArrayList<Double> smaller = null;
		ArrayList<Double> larger = null;
		if (inferenceData == InferenceData.FIELD_PERCENT || inferenceData == InferenceData.THREEPOINT_PERCENT
				||inferenceData == InferenceData.FREETHROW_PERCENT 
				|| inferenceData == InferenceData.REAL_FIELD_PERCENT) {
			format = UIConfig.PERCENT_FORMAT;
		}
		
		if (former.size() > current.size()) {
			larger = former;
			smaller = current;
		}else {
			larger = current;
			smaller = former;
		}
		ArrayList<Double> z = new ArrayList<Double>(smaller.size());
		int n1 = smaller.size();
		int n2 = larger.size();
		for (int i = 0;i < n1; i++) {
			z.add(smaller.get(i) - Math.sqrt(((double)n1) / n2) * larger.get(i) 
					+ sum(larger, n1) / Math.sqrt(n1 * n2) - avg(larger));
		}
		double a = avg(z);
		double b = Math.sqrt(var(z) / (n1 - 1)) * t.quantile(0.05, n1 - 1);
		
		left = a - b;
		right = a + b;	//到此为止，计算出来的置信区间是smaller - larger的
								//而我需要的是current - former的
		if (smaller == former) {
			left = - left;
			right = - right;
		}
	}
	
	public String getTransferConclusion() {
		String conclusion = null;
		if (left > 0 && right > 0) {
			conclusion = "有 有显著提升";
		}else if (left < 0 && right < 0) {
			conclusion = "有 有显著下降";
		}else {
			conclusion = "无 有显著变化";
		}
		return conclusion;
	}
	
	public String getCompareConclusion(String thisName, String thatName) {
		String leftStr = format.format(-right);
		String rightStr = format.format(-left);
		String interval = "(" + leftStr + ", " + rightStr + ")";
		String conclusion = thisName + "此项数据与" + thatName + 
				"之差的90%置信区间为" + interval;
		
		if (left > 0 && right > 0) {
			conclusion = conclusion + "，故可认为该球员此项数据显著低于" + thatName;
		}else if (left < 0 && right < 0) {
			conclusion = conclusion + "，故可认为该球员此项数据显著高于" + thatName;
		}else {
			conclusion = conclusion + "，故可认为两人此项数据无显著差异";
		}
		return conclusion;
	}
	
	private double avg(ArrayList<Double> data) {
		return sum(data, data.size())/ data.size();
	}
	
	private double sum(ArrayList<Double> data, int index) {
		double sum = 0;
		for (int i = 0; i< index; i++) {
			sum += data.get(i);
		}
		return sum;
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
