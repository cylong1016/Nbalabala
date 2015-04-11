package ui.panel.hot;

import ui.common.button.TextButton;
import enums.HotFastestPlayerProperty;
import enums.HotSeasonPlayerProperty;
import enums.HotSeasonTeamProperty;

/**
 * 赛季热点球队,赛季热点球员，进步最快球员统一筛选条件按钮
 * @author lsy
 * @version 2015年4月11日  下午4:46:05
 */
public class ThreeButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = -7282488217991359043L;
	
	/** 代表赛季热点球员界面的筛选条件 */
	public HotSeasonPlayerProperty player;
	/** 代表赛季热点球队界面的筛选条件 */
	public HotSeasonTeamProperty team;
	/** 代表进步最快球员的筛选条件 */
	public HotFastestPlayerProperty fast;
	/** 代表当前选中的按钮 */
	public static ThreeButton current;
	/** 按钮上的字 */
	String text;
	
	public ThreeButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}

}
