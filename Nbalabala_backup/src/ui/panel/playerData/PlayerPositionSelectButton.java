package ui.panel.playerData;

import ui.common.button.TextButton;
import enums.Position;

/**
 * 第一行按钮（前锋，中锋，后卫）
 * @author lsy
 * @version 2015年3月19日 下午2:47:26
 */
public class PlayerPositionSelectButton extends TextButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -4055004512100806741L;
	/** 当前选中button */
	static PlayerPositionSelectButton current;
	Position position;

	public PlayerPositionSelectButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
}
