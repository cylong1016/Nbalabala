package data.playerdata;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import utility.Constants;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月2日  上午10:37:09
 */
public class PlayerPortraitCache {
	
	private static HashMap<String, Image> portraits = new HashMap<String, Image>();
	
	/** 存储球员头像的文件夹 */
	private static final String PORTRAIT_PATH = Constants.dataSourcePath + "players/portrait/";
	
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
	
	private class CacheThread extends Thread{
		
		public void start() {
			File file = new File(PORTRAIT_PATH);
			File [] files = file.listFiles();
			for (File imgFile : files) {
				String fileName = imgFile.getName();
				
				portraits.put(fileName.substring(0, fileName.length() - 4), 
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
