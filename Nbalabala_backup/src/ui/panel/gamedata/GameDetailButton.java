package ui.panel.gamedata;

import ui.common.button.TextButton;

/**
 * 具体比赛信息界面代表两支球队的按钮
 * @author lsy
 * @version 2015年3月22日  下午5:19:20
 */
public class GameDetailButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = 1999005580290864822L;
	GameDetailButton current;

	public GameDetailButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}

}
