/**
 * 
 */
package autotest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 *
 * @author Issac Ding
 * @since 2015年5月10日 上午9:20:42
 * @version 1.0
 */
public class TestListFiles {

	@Test
	public void test() {
		File file = new File("NBAdata\\matches");
		int s;
		for (int i=0;i<1000;i++) {
			s = (file.listFiles().length);
		}
	}

}
