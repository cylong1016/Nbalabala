package capture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抓取球员基本信息
 * @author cylong
 * @version 2015年5月25日 下午1:36:47
 */
public class Player extends NBAData {
	
	/** 保存球员头像的目录 */
	private String savePath = "/portrait/";
	
	public Player() {
		this.captureUrl = root + "/players/";
	}

	/**
	 * 抓取球员基本信息
	 * @see capture.NBAData#capture()
	 */
	@Override
	public void capture() {
		new Capture().start();
	}
	
	private void capturePlayerProfile() {
		InputStream input = null;
		BufferedReader reader = null;
		ArrayList<String> playerData = new ArrayList<String>(8);
		for(char c = 'a'; c <= 'z'; c++) {
			if(c == 'x') { // 没有x开头的球员名
				continue;
			}
			HttpURLConnection urlConn = getConn(captureUrl + c);
			try {
				input = urlConn.getInputStream();
				reader = new BufferedReader(new InputStreamReader(input, urlConn.getContentType().equals("text-html; charset=gb2312")?"UTF-8":"gb2312"));
				String temp = null;
				while((temp = reader.readLine()) != null) {
					String playerDataReg = "<td.*?>(<strong>)?(<a href=\"(?<portrait>/players/.*?(?<nameID>[0-9]{2})\\.html)\">)?(<a href=\"/friv/.*?>)?(?<data>.*?)(</a>)?(\\*)?(</strong>)?</td>";
					Pattern patternPlayer = Pattern.compile(playerDataReg);
					Matcher matcherPlayer = patternPlayer.matcher(temp);
					if(matcherPlayer.find()) {
						String nameID = matcherPlayer.group("nameID");
						String data = matcherPlayer.group("data");
						String portraitUrl = matcherPlayer.group("portrait");
						if(nameID != null) {
							data += ("$" + nameID);
						}
						if(portraitUrl != null) {
							// 抓取球员头像
							capturePlayerPortrait(root + portraitUrl, data);
						}
						playerData.add(data);
					}
					if(Pattern.matches(".*?</tr>", temp)) { // 一个球员数据读取完毕
						if(playerData.size() == 8) { // 8项基本数据
							insertIntoPlayerProfile(playerData); // 插入数据库
						}
						playerData.clear();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 抓取球员的头像
	 * @param portraitUrl 球员头像的url
	 * @param name 球员名
	 * @author cylong
	 * @version 2015年6月9日  下午8:12:23
	 */
	private void capturePlayerPortrait(String portraitUrl, String name) {
		HttpURLConnection conn = getConn(portraitUrl);
		InputStream is;
		try {
			is = conn.getInputStream();
			byte[] bs = new byte[1024];  
			// 读取到的数据长度  
			int len;  
			// 输出的文件流  
			File sf=new File(savePath);  
			if(!sf.exists()){  
				sf.mkdirs();  
			}  
			OutputStream os = new FileOutputStream(sf.getPath() + "/" + name);  
			// 开始读取  
			while ((len = is.read(bs)) != -1) {  
				os.write(bs, 0, len);  
			}  
			// 完毕，关闭所有链接  
			os.close();  
			is.close();  
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * @param playerData 球员数据
	 * @author cylong
	 * @version 2015年6月4日  上午1:24:08
	 */
	private void insertIntoPlayerProfile(ArrayList<String> playerData) {
		String fromYear = playerData.get(1).equals("") ? null : playerData.get(1);
		String toYear = playerData.get(2).equals("") ? null : playerData.get(2);
		String position = playerData.get(3).equals("") ? null : "'" + playerData.get(3) + "'";
		String heightStr = playerData.get(4);
		String[] height = null;
		if(heightStr.equals("")) {
			height = new String[2];
		} else {
			height = heightStr.split("-"); // 将身高分成两部分
		}
		String weight = playerData.get(5).equals("") ? null : "'" + playerData.get(5) + "'";
		String birthDate = dateFormat(playerData.get(6)); // 将读取下来的date转化成mysql标准格式
		if(birthDate != null) {
			birthDate = "'" + birthDate + "'";
		}
		String school = playerData.get(7).equals("") ? null : "'" + playerData.get(7).replaceAll("'", "\\\\'") + "'";
		String sql = "INSERT INTO player_profile("
				+ "name, "
				+ "from_year, "
				+ "to_year, "
				+ "position, "
				+ "height_foot, "
				+ "height_inch, "
				+ "weight, "
				+ "birth_date, "
				+ "school"
				+ ") VALUES ("
					+ "'" + playerData.get(0).replaceAll("'", "\\\\'") + "', " // 有单引号的姓名
					+ fromYear + ", "
					+ toYear + ", "
					+ position + ", "
					+ height[0] + ", "
					+ height[1] + ", "
					+ weight + ", "
					+ birthDate + ", "
					+ school
				+ ")";
		System.out.println(playerData);
		System.out.println(sql);
		try {
			Statement statement = mysqlConn.createStatement();
			statement.execute(sql); // 插入
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	class Capture extends Thread {

		@Override
		public void run() {
			capturePlayerProfile();
		}
	}
}
