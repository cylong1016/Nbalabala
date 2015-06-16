package ui.panel.analyse.button;

import ui.common.button.TextButton;

/**
 * 对球队的贡献panel
 * @author lsy
 * @version 2015年6月16日  上午11:31:48
 */
public class ContriButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = -1625714034916406638L;

	/** 代表当前选中的按钮 */
	public static ContriButton current;
	/** 按钮上的字 */
	private String text;
	
	public ContriButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.text = text;
	}
	
	public String getText(){
		return text;
	}

}
