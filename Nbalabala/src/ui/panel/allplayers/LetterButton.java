package ui.panel.allplayers;

import java.awt.Font;

import ui.UIConfig;
import ui.common.button.TextButton;

/**
 * 字母button
 * @author lsy
 * @version 2015年3月20日  下午4:43:16
 */
public class LetterButton extends TextButton{

	/** 当前按钮 */
	static LetterButton current;
	/** 当前按钮代表哪个字母 */
	char letter;
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 * @author lsy
	 * @version 2015年3月20日  下午4:43:30
	 */
	public LetterButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.setFont(new Font("微软雅黑", 1, 17));
	}

}
