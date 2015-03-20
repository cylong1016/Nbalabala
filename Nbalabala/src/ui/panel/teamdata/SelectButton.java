package ui.panel.teamdata;

import ui.common.button.TextButton;
import enums.ScreenDivision;

/**
 * 
 * @author lsy
 * @version 2015年3月19日  下午10:51:55
 */
public class SelectButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = -7406509530315629567L;
	ScreenDivision division;
	static SelectButton current;
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 * @author lsy
	 * @version 2015年3月19日  下午10:54:00
	 */
	public SelectButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		
	}
	
}
