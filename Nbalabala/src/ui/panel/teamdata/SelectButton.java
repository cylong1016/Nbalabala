package ui.panel.teamdata;

import ui.common.button.TextButton;
import enums.ScreenDivision;

/**
 * 选择条件的按钮
 * @author lsy
 * @version 2015年3月19日  下午10:51:55
 */
public class SelectButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = -7406509530315629567L;
	ScreenDivision division;
	static SelectButton current;
	
	public SelectButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
	
}
