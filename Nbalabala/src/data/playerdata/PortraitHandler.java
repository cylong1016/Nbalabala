/**
 * 
 */
package data.playerdata;

import java.io.File;
import java.util.ArrayList;

import po.PlayerProfilePO;
import utility.Utility;

/**
 *
 * @author Issac Ding
 * @since 2015年6月15日 下午4:29:48
 * @version 1.0
 */
public class PortraitHandler {
	
	private static ArrayList<PlayerProfilePO> pos;
	
	public static void main(String[]args) {
		new PlayerData();
		pos = new ArrayList<PlayerProfilePO>(PlayerData.players.values());
		
		File folder = new java.io.File("NBAdata/current/");
		File[] files = folder.listFiles();
		for (File file : files) {
			String playerName = file.getName().substring(0, file.getName().length() - 4);
			int count = getNameCount(playerName);
			String newFileName = playerName + "$0" + count + ".png";
			file.renameTo(new File("NBAdata/current/" + newFileName));
		}
		
	}
	
	private static int getNameCount(String name) {
		int count = 0;
		for (PlayerProfilePO po : pos) { 
			if (name.equals(Utility.trimName(po.getName()))) {
				count++;
			}
		}
		return count;
	}

}
