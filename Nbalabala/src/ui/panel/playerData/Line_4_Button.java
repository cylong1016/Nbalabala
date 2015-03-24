package ui.panel.playerData;

import ui.common.button.TextButton;

/**
 * 第四行按钮（总计、平均）
 * @author lsy
 * @version 2015年3月19日 下午6:56:26
 */
public class Line_4_Button extends TextButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -4055004512100806741L;
	public static Line_4_Button current;

	public Line_4_Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
}
