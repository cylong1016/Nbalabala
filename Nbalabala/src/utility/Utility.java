package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

import enums.TeamState;

/**
 * 放一些在多处使用的算法
 * @author Issac Ding
 * @version 2015年3月18日  下午10:20:35
 */
public class Utility {
	
	/** 存储比赛信息的文件夹 */
	public static String matchPath = "NBAdata/matches/"; 
	
	public static File[] getSortedMatchFiles(){
		File dir = new File(matchPath);
		File[] files = dir.listFiles();
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				String name1 = f1.getName();
				String name2 = f2.getName();
				String[]s1 = name1.split("_|-");
				String[]s2 = name2.split("_|-"); 
				int month1 = Integer.parseInt(s1[2]);
				int month2 = Integer.parseInt(s2[2]);
				if (month1 < 8) month1 += 12;
				if (month2 < 8) month2 += 12;
				int deltaMonth = month1 - month2;
				if (deltaMonth != 0) return deltaMonth;
				else {
					int day1 = Integer.parseInt(s1[3]);
					int day2 = Integer.parseInt(s2[3]);
					return day1 - day2;
				}
			}
		});
		return files;
	}
	
	/** 修正得分，得分=（投篮数3-三分球数5）*2 + 三分球数 *3 + 罚球7 * 1 ，整理后即为下式 */
	//lineInt是对应比赛记录中的一行的数据
	public static int getModifiedScore(int [] lineInt) {
		return 2 * lineInt[3] + lineInt[5] + lineInt[7];
	}
	
	/** 修正时间为null的脏数据，方法是总时间减去其他球员上场时间，总时间为48分钟+5分钟*加时赛场数*5*/
	public static int getModifiedTime(File file, TeamState teamState) {
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			int totalSeconds = ((br.readLine().split(";").length - 4) * 5 + 48) * 300;
			br.readLine();
			//有记录的总分钟数和秒数之和
			int minutes = 0;
			int seconds = 0;
			String [] line;
			
			//如果是主场队，读取文件的上半部分
			if (teamState == TeamState.HOME) {
				while (true){
					line = br.readLine().split(";|:");
					if (line.length < 2) break;		//读到客场队缩写说明读完了主场队
					try{
						minutes += Integer.parseInt(line[2]);
						seconds += Integer.parseInt(line[3]);
					}catch (Exception e){
						continue;
					}
				}
				
			}else {	//如果要纠正的是客场队
				while (br.readLine().length() > 5) {
					//跳过主场队部分
				}
				String s = null;
				while ((s = br.readLine()) != null){
					line = s.split(";|:");
					try{
						minutes += Integer.parseInt(line[2]);
						seconds += Integer.parseInt(line[3]);
					}catch (Exception e){
						continue;
					}
				}
			}
			br.close();
			return totalSeconds - seconds - minutes * 60;
		}catch (Exception e){
			return 0;
		}
	}

}
