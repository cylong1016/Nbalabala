/**
 * 
 */
package bl.analysisbl;

import java.util.ArrayList;

import po.MatchPlayerPO;
import enums.InferenceData;

/**
 * 用于分组汇总数据
 * @author Issac Ding
 * @since 2015年6月13日 下午9:01:36
 * @version 1.0
 */
public class DivideHandler {
	
	private int width = 0;
	
	public ArrayList<Double> divideData(ArrayList<MatchPlayerPO> matches,
			InferenceData forecastData) {
		ArrayList<Double> data = new ArrayList<Double>();
		
		int count = (int)(Math.log(matches.size()) / Math.log(2));	//经验分组公式
		width = Math.min(20, matches.size() / count);	//TODO 这个数字有待考量
		
		int fieldM = 0;
		int fieldA = 0;
		int threeM = 0;
		int threeA = 0;
		int ftM = 0;
		int ftA = 0;
		int rb = 0;
		int orb = 0;
		int drb = 0;
		int ast = 0;
		int blk = 0;
		int stl = 0;
		int pf = 0;
		int tov = 0;
		int pts = 0;
	
		int k = 0;
		double sum = 0;
		switch (forecastData) {
		case SCORE:
			for ( int i = 0 ;i < matches.size();i++) {
				sum += matches.get(i).score;
				k ++;
				if (k == width) {
					data.add(sum / k);
					sum = 0;
					k = 0;
				}
			}
			break;
		case ASSIST:
			for ( int i = 0 ;i < matches.size();i++) {
				sum += matches.get(i).assist;
				k ++;
				if (k == width) {
					data.add(sum / k);
					sum = 0;
					k = 0;
				}
			}
			break;
		case REBOUND:
			for ( int i = 0 ;i < matches.size();i++) {
				sum += matches.get(i).totalRebound;
				k ++;
				if (k == width) {
					data.add(sum / k);
					sum = 0;
					k = 0;
				}
			}
			break;
		case FIELD_PERCENT:
			for ( int i = 0 ;i < matches.size();i++) {
				fieldA += matches.get(i).fieldAttempt;
				fieldM += matches.get(i).fieldMade;
				k ++;
				if (k == width) {
					data.add((double)fieldM / fieldA);
					fieldA = 0;
					fieldM = 0;
					k = 0;
				}
			}
			break;
		case THREEPOINT_PERCENT:
			for ( int i = 0 ;i < matches.size();i++) {
				threeA += matches.get(i).threepointAttempt;
				threeM += matches.get(i).threepointMade;
				k ++;
				if (k == width) {
					data.add((double)threeM / threeA);
					threeA = 0;
					threeM = 0;
					k = 0;
				}
			}
			break;
		case FREETHROW_PERCENT:
			for ( int i = 0 ;i < matches.size();i++) {
				ftA += matches.get(i).freethrowAttempt;
				ftM += matches.get(i).freethrowMade;
				k ++;
				if (k == width) {
					data.add((double)ftM / ftA);
					ftA = 0;
					ftM = 0;
					k = 0;
				}
			}
			break;
		case EFFICIENCY:
			for ( int i = 0 ;i < matches.size();i++) {
				MatchPlayerPO po = matches.get(i);
				ftA += po.freethrowAttempt;
				ftM += po.freethrowMade;
				fieldA += po.fieldAttempt;
				fieldM += po.fieldMade;
				pts += po.score;
				rb += po.totalRebound;
				drb += po.defensiveRebound;
				stl += po.steal;
				ast += po.assist;
				tov += po.turnover;
				blk += po.block;
				k ++;
				if (k == width) {
					double effSum = pts + rb + ast + stl + blk - fieldA + fieldM - ftA + ftM - tov;
					data.add(effSum / k);
					k = 0;
					sum = 0;
					ftA =0;
					ftM =0;
					fieldA =0;
					fieldM =0;
					pts =0;
					rb =0;
					drb =0;
					stl =0;
					ast =0;
					tov =0;
					blk =0;
				}
			}
			break;
		case GMSC:
			for ( int i = 0 ;i < matches.size();i++) {
				MatchPlayerPO po = matches.get(i);
				ftA += po.freethrowAttempt;
				ftM += po.freethrowMade;
				fieldA += po.fieldAttempt;
				fieldM += po.fieldMade;
				pts += po.score;
				orb += po.offensiveRebound;
				drb += po.defensiveRebound;
				stl += po.steal;
				ast += po.assist;
				pf += po.foul;
				tov += po.turnover;
				k ++;
				if (k == width) {
					double gmscSum = (double)(pts + 0.4 * fieldM - 0.7 * fieldA - 
							0.4 * (ftA - ftM) + 0.7 * orb + 0.3 * drb + stl + 
							0.7 * ast + 0.7 * blk - 0.4 * pf - tov);
					data.add((double)gmscSum / k);
					k = 0;
					ftA = 0;
					ftM = 0;
					fieldA =0;
					fieldM = 0;
					pts =0;
					orb =0;
					drb =0;
					stl =0;
					ast =0;
					pf =0;
					tov =0;
				}
			}
			break;
		case REAL_FIELD_PERCENT:
			for ( int i = 0 ;i < matches.size();i++) {
				fieldA += matches.get(i).fieldAttempt;
				ftA += matches.get(i).freethrowAttempt;
				pts += matches.get(i).score;
				k ++;
				if (k == width) {
					double divisor = 2 * (fieldA + 0.44 * ftA);
					if (divisor != 0) {
						data.add(pts / divisor);
					}else {
						data.add(0.0);
					}
					fieldA = 0;
					ftA = 0;
					pts = 0;
					k = 0;
				}
			}
			break;
		case FIELD_EFF:
			for ( int i = 0 ;i < matches.size();i++) {
				fieldM += matches.get(i).fieldMade;
				fieldA += matches.get(i).fieldAttempt;
				threeM += matches.get(i).threepointMade;
				k ++;
				if (k == width) {
					if (fieldA != 0) {
						data.add((fieldM + 0.5 * threeM) / fieldA);
					}else {
						data.add(0.0);
					}
					fieldA = 0;
					fieldM = 0;
					threeM = 0;
					k = 0;
				}
			}
			break;
		default:
			break;
		}
		return data;
	}
	
	public int getWidth() {
		return width;
	}

}
