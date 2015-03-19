package ui.panel.playerData;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.common.button.TextButton;

/**
 * 第一行按钮
 * 
 * @author lsy
 * @version 2015年3月19日 下午2:47:26
 */
public class Line_1_Button extends TextButton {

	static TextButton current;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 * @author lsy
	 * @version 2015年3月19日 下午2:48:19
	 */
	public Line_1_Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				current = Line_1_Button.this;
			}

		});
	}

}
