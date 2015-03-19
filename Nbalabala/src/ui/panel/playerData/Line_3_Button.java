package ui.panel.playerData;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.common.button.TextButton;

/**
 * 
 * @author lsy
 * @version 2015年3月19日  下午6:56:16
 */
public class Line_3_Button extends TextButton{
	/** serialVersionUID */
	private static final long serialVersionUID = -4055004512100806741L;
	static Line_3_Button current;

	public Line_3_Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
}
