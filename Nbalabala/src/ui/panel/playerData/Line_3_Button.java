package ui.panel.playerData;

import ui.common.button.TextButton;
import enums.ScreenBasis;

/**
 * 第三行按钮（球员的各项数据）
 * @author lsy
 * @version 2015年3月19日 下午6:56:16
 */
public class Line_3_Button extends TextButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -4055004512100806741L;
	static Line_3_Button current;
	ScreenBasis basis;

	public Line_3_Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
}
