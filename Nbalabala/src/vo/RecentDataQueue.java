package vo;

/**
 * 存储最近10场比赛某项数据的数据结构
 * @author Issac Ding
 * @version 2015年4月8日  上午10:07:19
 */
public class RecentDataQueue {
	
	private int [] recent = new int[5];
	private int last = 0;
	private int start = 0;
	private int size = 0;
	
	public int getLastData() {
		if (last == 0) {
			return recent[4];
		}else {
			return recent[last - 1];
		}
	}
	
	public int[] getRecentFive() {
		int[] result = new int[5];
		int index = last;
		for (int i=4; i>=0; i--) {
			index --;
			if (index < 0) index = 4;
			result[i] = recent[i];
		}
		return result;
	}
	
	public void enqueue(int data) {
		recent[last] = data;
		if (size < 5) {
			size ++;
		}else{
			start ++;
			if (start > 4) start = 0;
		}
		last ++;
		if (last > 4) {
			last = 0;
		}
	}
	
	public int getFiveSum() {
		if (size < 5) {
			return 0;
		}else{
			int result = 0;
			for (Integer i : recent) {
				result += i;
			}
			return result;
		}
	}
	
	public double getFiveAvg() {
		return (double)getFiveSum() / 5;
	}
	
}
