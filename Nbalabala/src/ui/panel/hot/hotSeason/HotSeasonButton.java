package ui.panel.hot.hotSeason;

import ui.common.button.TextButton;
import enums.HotSeasonPlayerProperty;
import enums.HotSeasonTeamProperty;

/**
 * 赛季热点球队筛选条件按钮
 * @author lsy
 * @version 2015年4月11日  下午4:46:05
 */
public class HotSeasonButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = -7282488217991359043L;
	
	public HotSeasonPlayerProperty player;
	public HotSeasonTeamProperty team;
	/** 代表当前选中的按钮 */
	static HotSeasonButton current;
	/** 按钮上的字 */
	String text;
	
	public HotSeasonButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}

}
