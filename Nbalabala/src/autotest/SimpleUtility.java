/**
 * 
 */
package autotest;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Issac Ding
 * @since 2015年5月8日 下午4:35:24
 * @version 1.0
 */
public class SimpleUtility {
	
	public static File[] getSortedMatchFiles(){
		File dir = new File(SimpleConstants.sourcePath + "matches\\");
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
			}
		}
		File[] files = dir.listFiles();
		Arrays.sort(files, new FileComparator());
		return files;
	}

}
