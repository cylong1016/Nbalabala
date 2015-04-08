package utility;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import ui.controller.MainController;
import data.seasondata.SeasonData;

/**
 * 监视比赛信息有无增删的类
 * @author Issac Ding
 * @version 2015年4月8日  下午12:26:44
 */
public class DataSourceMonitor {
	
	private static int oldFilesCount;
	private static HashSet<String> oldFileNames = new HashSet<String>();
	
	static{
		File[] initialFiles = new File(Constants.dataSourcePath + "matches/").listFiles();
		oldFilesCount = initialFiles.length;
		for (File file : initialFiles) {
			oldFileNames.add(file.getName());
		}
	}
	
	public DataSourceMonitor(){
		new MonitorThread().start();
	}
	
	private static void reloadNames(File [] currentNames) {
		oldFileNames.clear();
		for (File file : currentNames) {
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
		public void start() {
			while (true) {
				File[] files = new File(Constants.dataSourcePath + "matches/").listFiles();
				int currentCount = files.length;
				if (currentCount > oldFilesCount) {
					ArrayList<File> newFiles= getNewFiles(files);
					new SeasonData().appendMatches(newFiles);
					oldFilesCount = currentCount;
					MainController.refreshUI();
				}else if (currentCount < oldFilesCount) {
					new SeasonData().reloadMatches();
					reloadNames(files);
					oldFilesCount = currentCount;
					MainController.refreshUI();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					continue;
				}
			}
		}
	}
	
}
