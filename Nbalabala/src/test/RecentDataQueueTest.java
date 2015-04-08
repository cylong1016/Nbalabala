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
	@Before
	public void testEnqueue() {
		for (int i=1; i< 15; i++){
			queue.enqueue(i);
		}
//		queue.print();
	}
	
	public void print() {
//		queue.print();
	}

	/**
	 * Test method for {@link vo.RecentDataQueue#getPromotion()}.
	 */
	@Test
	public void testGetPromotion() {
		System.out.println(queue.getPromotion());
	}

	/**
	 * Test method for {@link vo.RecentDataQueue#getFormerFiveSum()}.
	 */
	@Test
	public void testGetFormerFiveSum() {
		System.out.println(queue.getFormerFiveSum());
	}

	/**
	 * Test method for {@link vo.RecentDataQueue#getLatterFiveSum()}.
	 */
	@Test
	public void testGetLatterFiveSum() {
		System.out.println(queue.getLatterFiveSum());
	}

}
