package autotest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 监视比赛信息有无增删的类
 * @author Issac Ding
 * @version 2015年4月8日  下午12:26:44
 */
public class SimpleMonitor {
	
	private static int oldFilesCount;
	private static HashSet<String> oldFileNames = new HashSet<String>();
	
	public static boolean matchesAppending = false;
	
	public static void startMonitor(){
		File dirFile = new File(SimpleConstants.sourcePath + "matches//");
		if (!dirFile.exists()){
			dirFile.mkdirs();
		}
		File[] initialFiles = dirFile.listFiles();
		oldFilesCount = initialFiles.length;
		for (File file : initialFiles) {
			oldFileNames.add(file.getName());
		}
		new SimpleMonitor();
	}
	
	public SimpleMonitor(){
		new MonitorThread().start();
	}
	
	private static void reloadNames(File [] currentFiles) {
		oldFileNames.clear();
		for (File file : currentFiles) {
			oldFileNames.add(file.getName());
		}
	}
	
	private ArrayList<File> getNewFiles(File[]files) {
		ArrayList<File> newFiles = new ArrayList<File>();
		for (File file : files) {
			String name = file.getName();
			if (!oldFileNames.contains(name)) {
				oldFileNames.add(name);
				newFiles.add(file);
			}
		}
		return newFiles;
	}
	
	class MonitorThread extends Thread{
		public void run() {
			while (true) {
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					continue;
				}
				
				File dirFile = new File(SimpleConstants.sourcePath + "matches//");
				
				if (!dirFile.exists()){
					dirFile.mkdirs();
				}
				File[] files = dirFile.listFiles();
				int currentCount = files.length;
				
				if (currentCount > oldFilesCount) {
					
					while (SeasonSimpleData.isReading) {
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							continue;
						}
					} 
					
					matchesAppending = true;
					ArrayList<File> newFiles= getNewFiles(files);
					new SeasonSimpleData().appendMatches(newFiles);
					oldFilesCount = currentCount;
					matchesAppending = false;
				}else if (currentCount < oldFilesCount) {
					SeasonSimpleData.reload();
					reloadNames(files);
					oldFilesCount = currentCount;
				}
			}
		}
	}
	
}
