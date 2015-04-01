package ui.panel.playerData;

import ui.common.button.TextButton;
import enums.ScreenDivision;

/**
 * 第二行按钮（球员在的地区）
 * @author lsy
 * @version 2015年3月19日 下午6:56:08
 */
public class PlayerDivisionSelectButton extends TextButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -4055004512100806741L;
	static PlayerDivisionSelectButton current;
	ScreenDivision division;

	public PlayerDivisionSelectButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);

	}
}
