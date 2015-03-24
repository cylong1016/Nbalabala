package ui.panel.playerData;

import ui.common.button.TextButton;
import enums.ScreenDivision;

/**
 * 第二行按钮（球员在的地区）
 * @author lsy
 * @version 2015年3月19日 下午6:56:08
 */
public class Line_2_Button extends TextButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -4055004512100806741L;
	static Line_2_Button current;
	ScreenDivision division;

	public Line_2_Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);

	}
}
