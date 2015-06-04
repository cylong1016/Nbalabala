package main;

import capture.Match;
import capture.Player;

/**
 * 程序入口
 * @author cylong
 * @version 2015年6月3日 下午1:32:56
 */
public class Launcher {

	public static void main(String[] args) {
		Match m = new Match();
		m.capture();
		Player p = new Player();
		p.capture();
	}

}
