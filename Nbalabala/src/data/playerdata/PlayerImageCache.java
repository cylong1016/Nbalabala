package data.playerdata;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import utility.Constants;
import utility.Utility;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月2日  上午10:37:09
 */
public class PlayerImageCache {
	
	private static HashMap<String, Image> portraits = new HashMap<String, Image>();
	
	private static Image nullPortrait;
	
	static {
		try {
			nullPortrait = ImageIO.read(new File("images/nullPortrait.png"));
		} catch (IOException e1) {
			nullPortrait = null;
		}
	}
	
	public void loadPortrait() {
		CacheThread cacheThread = new CacheThread();
		cacheThread.start();
	}
	
	public static Image getNullPortrait() {
		return nullPortrait;
	}
	
	public static Image getPortraitByName(String name) {
		Image result = portraits.get(name);
		if (result != null) {
			return result;
		}else {
			return nullPortrait;
		}
	}
	
	public static Image getActionImageByName(String name) {
		name = Utility.trimName(name);
		try {
			return  ImageIO.read(new File(Constants.dataSourcePath + "action//" + name + ".png"));
		} catch (IOException e) {
			try {
				return ImageIO.read(new File("images/nullAction.png"));
			} catch (IOException e1) {
				return null;
			}
		}
	}
	
	public static void reloadImages() {
		portraits.clear();
		File file = new File(Constants.dataSourcePath + "portrait//");
		if (!file.exists()) {
			return;
		}
		File [] files = file.listFiles();
		for (File imgFile : files) {
			String fileName = imgFile.getName();
			
			portraits.put(fileName.substring(0, fileName.length() - 4), 
					getPotraitImageByFile(imgFile));
		}
	}
	
	private class CacheThread extends Thread{
		
		public void start() {
			File file = new File(Constants.dataSourcePath + "portrait//");
			if (!file.exists()) {
				return;
			}
			File [] files = file.listFiles();
			for (File imgFile : files) {
				String fileName = imgFile.getName();
				portraits.put(fileName.substring(fileName.lastIndexOf('\\') + 1, fileName.length() - 4), 
						getPotraitImageByFile(imgFile));
			}
		}
	}
	
	private static Image getPotraitImageByFile(File file) {
		try {
			return  ImageIO.read(file);
		} catch (IOException e) {
			return nullPortrait;
		}
	}
	
	public static void clear() {
		portraits.clear();
	}
}
