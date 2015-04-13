package test;

import org.junit.Before;
import org.junit.Test;

import vo.RecentDataQueue;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月8日  上午10:27:46
 */
public class RecentDataQueueTest {
	
	private RecentDataQueue queue = new RecentDataQueue();

	/**
	 * Test method for {@link vo.RecentDataQueue#enqueue(int)}.
	 */
	@Test
	public void testEnqueue() {
		for (int i=1; i< 16; i++){
			queue.enqueue(i);
		}
		System.out.println(queue.getFiveSum());
	}
	
	public void print() {
//		queue.print();
	}


	/**
	 * Test method for {@link vo.RecentDataQueue#getFormerFiveSum()}.
	 */
	@Test
	public void testGetFormerFiveSum() {
	}

	/**
	 * Test method for {@link vo.RecentDataQueue#getLatterFiveSum()}.
	 */
	@Test
	public void testGetLatterFiveSum() {
	}

}
