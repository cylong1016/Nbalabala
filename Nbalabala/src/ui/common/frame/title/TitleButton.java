package ui.common.frame.title;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

/**
 * 最小化按钮；关闭按钮
 * @author cylong
 * @version 2015年3月16日 上午12:49:34
 */
public class TitleButton extends JLabel {

	/** serialVersionUID */
	private static final long serialVersionUID = 7046202963400208918L;

	/*----------------------------标题按钮配置--------------------------------*/
	/** 标题按钮背景颜色 */
	public static Color TITLE_BUTTON_BACK_COLOR = Color.LIGHT_GRAY;
	/** 移动到button上的背景颜色 */
	public static Color ENTERED_BTN_BACK_COLOR = Color.GRAY;
	/*----------------------------标题按钮配置--------------------------------*/

	/** 按钮大小 */
	private Dimension size = new Dimension(32, 16);

	public TitleButton() {
		this.setPreferredSize(size);
		this.setOpaque(true); // 不透明
		this.addMouseListener(new ButtonListener());
		this.setBackground(TITLE_BUTTON_BACK_COLOR);
	}

	public TitleButton(String text) {
		this();
		this.setText(text);
		this.setHorizontalAlignment(JLabel.CENTER);
	}

	private class ButtonListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			TitleButton.this.setBackground(ENTERED_BTN_BACK_COLOR);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			TitleButton.this.setBackground(TITLE_BUTTON_BACK_COLOR);
		}
	}

}
