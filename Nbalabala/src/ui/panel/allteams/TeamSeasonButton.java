package ui.panel.allteams;

import ui.common.button.TextButton;

/**
 * 球队赛季数据按钮
 * @author lsy
 * @version 2015年3月20日  下午11:47:11
 */
public class TeamSeasonButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = 8106302891957695746L;
	static TeamSeasonButton current;
	public TeamSeasonButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}

}
