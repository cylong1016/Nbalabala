package vo;

/**
 * 存储最近10场比赛某项数据的数据结构
 * @author Issac Ding
 * @version 2015年4月8日  上午10:07:19
 */
public class RecentDataQueue {
	
	private int [] recent = new int[10];
	private int last = 0;
	private int start = 0;
	private int size = 0;
	
	public int getLastData() {
		if (last == 0) {
			return recent[9];
		}else {
			return recent[last - 1];
		}
	}
	
	public double getFormerFiveAvg() {
		return getFormerFiveSum() / 5;
	}
	
	public double[] getRecentFive() {
		double[] result = new double[5];
		int index = last;
		for (int i=4; i>=0; i++) {
			index --;
			if (index < 0) index = 9;
			result[i] = recent[i];
		}
		return result;
	}
	
	public void enqueue(int data) {
		recent[last] = data;
		if (size < 10) {
			size ++;
		}else{
			start ++;
			if (start > 9) start = 0;
		}
		last ++;
		if (last == 10) {
			last = 0;
		}
	}
	
	public double getPromotion() {
		if (size < 10) {
			return 0;
		}else {
			int i;
			int index = start;
			int formerFiveSum = 0;
			int latterFiveSum = 0;
			for (i=0; i<5; i++) {
				formerFiveSum += recent[index];
				index ++;
				if (index == 10) index = 0;
			}
			if (formerFiveSum == 0) return 0;
			for (; i<10; i++) {
				latterFiveSum += recent[index];
				index ++;
				if (index == 10) index = 0;
			}
			return (latterFiveSum - formerFiveSum) / (double)formerFiveSum;
		}
	}
	
	public int getFormerFiveSum() {
		if (size < 10) {
			return 0;
		}else {
			int i;
			int index = start;
			int formerFiveSum = 0;
			for (i=0; i<5; i++) {
				formerFiveSum += recent[index];
				index ++;
				if (index == 10) index = 0;
			}
			return formerFiveSum;
		}
	}
	
	public int getLatterFiveSum() {
		if (size < 10) {
			return 0;
		}else {
			int i;
			int index = last;
			int latterFiveSum = 0;
			for (i=0; i<5; i++) {
				index --;
				if (index < 0) index = 9;
				latterFiveSum += recent[index];
			}
			return latterFiveSum;
		}
	}

}
