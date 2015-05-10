/**
 * 
 */
package autotest;

import java.io.File;
import java.util.Comparator;

/**
 *
 * @author Issac Ding
 * @since 2015年5月10日 上午10:06:32
 * @version 1.0
 */
public class FileComparator implements Comparator<File>{
	public int compare(File f1, File f2) {
		String name1 = f1.getName();
		String name2 = f2.getName();
		String[]s1 = name1.split("_|-");
		String[]s2 = name2.split("_|-");
		int startYear1 = Integer.parseInt(s1[0]);
		int startYear2 = Integer.parseInt(s2[0]);
		if (startYear1 != startYear2) {
			return startYear1 - startYear2;
		}
		int month1 = Integer.parseInt(s1[2]);
		int month2 = Integer.parseInt(s2[2]);
		if (month1 < 8) month1 += 12;
		if (month2 < 8) month2 += 12;
		int deltaMonth = month1 - month2;
		if (deltaMonth != 0) return deltaMonth;
		else {
			int day1 = Integer.parseInt(s1[3]);
			int day2 = Integer.parseInt(s2[3]);
			return day1 - day2;
		}
	}

}
