package data.teamdata;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月20日 下午6:59:33
 */
public class TeamLogoCache {

	private static HashMap<String, Image> logos = new HashMap<String, Image>();

	/** 先检查temp文件夹下有无转换好的png，如果没有，转换之 */
	public static Image getTeamLogo(String abbr) {
		if (abbr.equals("NOH"))
			abbr = "NOP";
		else if (abbr.equals("NJN"))
			abbr = "BKN";

		Image result = logos.get(abbr);
		if (result != null) {
			return result;
		}

		File file = new File("temp/" + abbr + ".png");
		try {
			logos.put(abbr, ImageIO.read(file));
			return logos.get(abbr);
		} catch (Exception e2) {
			try {
				return ImageIO.read(new File("images/nullTeam.png"));
			} catch (IOException e1) {
				return null;
			}
		}
	}

	public void loadLogos() {
		new CacheThread().start();
	}

	
	private class CacheThread extends Thread {
		public void start() {
			File file = new File("temp/");
			if (!file.exists()) {
				file.mkdirs();
			}
			File[] files = file.listFiles();
			for (File imgFile : files) {
				String fileName = imgFile.getName();
				try {
					logos.put(fileName.substring(0, 3), ImageIO.read(imgFile));
				} catch (IOException e) {
					continue;
				}
			}
		}
	}

}
