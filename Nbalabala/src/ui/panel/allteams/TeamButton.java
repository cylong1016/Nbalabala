package ui.panel.allteams;

import ui.Images;
import ui.UIConfig;
import ui.common.button.ImgButton;

/**
 * 
 * @author lsy
 * @version 2015年3月20日  下午9:10:56
 */
public class TeamButton extends ImgButton{

	/** serialVersionUID */
	private static final long serialVersionUID = 3789882441719172108L;
	/** 标记自己是哪知球队 */
	String team;
	private static final String MOUSE_ON_PATH = Images.TEAMS_BTN_ON;
	private static final String OFF_PATH = UIConfig.IMG_PATH + "teams/tran.png";
	
	public TeamButton(int x, int y) {
		super(OFF_PATH, x, y, MOUSE_ON_PATH, Images.TEAMS_BTN_CLICK);
	}

}
