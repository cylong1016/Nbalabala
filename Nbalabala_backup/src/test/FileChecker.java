/**
 * 
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Issac Ding
 * @since 2015年6月16日 下午8:42:52
 * @version 1.0
 */
public class FileChecker {
	
	public static void main(String[] args) {
		File [] output = new File("output").listFiles();
		File [] standard = new File("standard").listFiles();
		
		int succline = 0;
		int failine = 0;
		
		for (int i=0;i<standard.length;i++) {

			try {
				BufferedReader myBr = new BufferedReader(new FileReader(output[i]));
				BufferedReader hisBr = new BufferedReader(new FileReader(standard[i]));
				
				String s;
				String t;
				int lineIndex = 0;
				while((s = myBr.readLine()) != null) {
					t = hisBr.readLine();
					lineIndex ++;
					if (!s.equals(t)) {

						if (s.length() == 0 && t.contains("null")) continue;
						
						try {
							double d1 = Double.parseDouble(s);
							double d2 = Double.parseDouble(t);
							if (Math.abs(d1-d2) < 0.0001) {
								continue;
							}else {
								System.out.println("____");
								System.out.println(output[i].getName());
								System.out.println(lineIndex);
								System.out.println(s);
								System.out.println(t);
								failine++;
							}
						}catch(Exception e) {
							System.out.println("____");
							System.out.println(output[i].getName());
							System.out.println(lineIndex);
							System.out.println(s);
							System.out.println(t);
							failine++;
						}
					}else {
						succline++;
					}
				}
				myBr.close();
				hisBr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(succline);
		System.out.println(failine);
	}

}
