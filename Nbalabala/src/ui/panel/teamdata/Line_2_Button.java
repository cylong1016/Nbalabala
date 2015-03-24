package ui.panel.teamdata;

import ui.common.button.TextButton;
import enums.Position;

/**
 * 总计和平均按钮
 * @author cylong
 * @version 2015年3月24日  下午8:34:49
 */
public class Line_2_Button extends TextButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -4055004512100806741L;
	/** 当前选中button */
	static Line_2_Button current;
	Position p;

	public Line_2_Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
}
