package main;

import image.OperateImage;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import capture.UpdateMatch;

/**
 * 程序入口
 * @author cylong
 * @version 2015年6月3日 下午1:32:56
 */
public class Launcher {

	public static void main(String[] args) throws IOException {
		
//---------------------------------------------------
// 请新建数据库名为nbalabala，导入相应表，在执行下面代码的时候要清空除了player_season、team_season、team_profile以外的全部表
//		Match m = new Match();
//		m.capture();
//		
//		Player p = new Player();
//		p.capture();
//		
//		PlayerAdvance pa = new PlayerAdvance();
//		pa.capture();
//
//		PlayerClutch pc = new PlayerClutch();
//		pc.capture();
//---------------------------------------------------
		
		
//---------------------------------------------------
// 此代码用来更新最近的数据，保证运行过上面的代码		
		UpdateMatch um = new UpdateMatch();
		um.capture();
//---------------------------------------------------
		
//---------------------------------------------------
// 对抓取的球员头像进行裁剪
//		imgCut();
//---------------------------------------------------
	}
	
	/**
	 * 对抓取下来的球员头像进行裁剪
	 * @throws IOException
	 * @author cylong
	 * @version 2015年6月17日  下午1:25:48
	 */
	public static void imgCut() throws IOException {
		File portraitsDir = new File("images/portrait");
		File[] portraits = portraitsDir.listFiles();
		for(int i = 0; i < portraits.length; i++) {
			String filePath = portraits[i].getPath();
			String fileName = portraits[i].getName();
			if(fileName.endsWith(".jpg")) {
				double ratio = 140.0 / 113.0; // 宽 : 高
				Image img = ImageIO.read(portraits[i]);
				int width = img.getWidth(null);
				int height = (int)(width / ratio);
				OperateImage oi = new OperateImage(0, 0, width, height);
				oi.setSrcpath(filePath);
				String outPath = "images/portraitCut/" + fileName;
				oi.setSubpath(outPath);
				oi.cut();
				System.out.println(outPath);
			}
		}
	}

}
