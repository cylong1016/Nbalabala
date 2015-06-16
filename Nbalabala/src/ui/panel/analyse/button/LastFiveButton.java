package ui.panel.analyse.button;

import ui.common.button.TextButton;

/**
 * 最后五分钟按钮
 * @author lsy
 * @version 2015年6月16日  上午11:39:59
 */
public class LastFiveButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = 856678704563764728L;

	/** 代表当前选中的按钮 */
	public static LastFiveButton current;
	/** 按钮上的字 */
	private String text;
	
	public LastFiveButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.text = text;
	}
	
	public String getText(){
		return text;
	}

}
