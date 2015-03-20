package ui.panel.allteams;

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
	int which;
	public TeamButton(String imgPath, int x, int y, String stopImgPath, String pressOnImgPath) {
		super(imgPath, x, y, stopImgPath, pressOnImgPath);
	}

}
