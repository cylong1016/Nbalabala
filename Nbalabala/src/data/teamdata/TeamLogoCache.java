package data.teamdata;

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
 * @version 2015年3月20日 下午6:59:33
 */
public class TeamLogoCache {

	private static HashMap<String, Image> logos = new HashMap<String, Image>();
	
	public static void reloadLogos() {
		logos.clear();
		
		File file = new File(Constants.dataSourcePath + "/logo");
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

	public static Image getTeamLogo(String abbr) {
		abbr = Utility.getCurrentAbbr(abbr);

		Image result = logos.get(abbr);
		if (result != null) {
			return result;
		}
		
		try {
			return ImageIO.read(new File("images/nullTeam.png"));
		} catch (IOException e1) {
			return null;
		}

	}

	public void loadLogos() {
		new CacheThread().start();
	}

	private class CacheThread extends Thread {
		public void start() {
			File file = new File(Constants.dataSourcePath + "/logo");
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
