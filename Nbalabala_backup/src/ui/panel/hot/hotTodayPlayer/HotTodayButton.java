package ui.panel.hot.hotTodayPlayer;

import ui.common.button.TextButton;
import enums.HotTodayPlayerProperty;

/**
 * 今日热点球员筛选条件的五个按钮
 * @author lsy
 * @version 2015年4月11日 下午12:34:48
 */
public class HotTodayButton extends TextButton {

	/** serialVersionUID */
	private static final long serialVersionUID = 2367848140830128726L;

	HotTodayPlayerProperty pro;
	/** 代表当前选中的按钮 */
	static HotTodayButton current;
	/** 按钮上的字 */
	String text;

	public HotTodayButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.text = text;
	}

}
