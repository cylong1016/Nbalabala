package ui.panel.playerData;

import ui.common.button.TextButton;
import enums.ScreenBasis;

/**
 * 第三行按钮（球员的各项数据）
 * @author lsy
 * @version 2015年3月19日 下午6:56:16
 */
public class PlayerScreenSelectButton extends TextButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -4055004512100806741L;
	static PlayerScreenSelectButton current;
	ScreenBasis basis;

	public PlayerScreenSelectButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
}
