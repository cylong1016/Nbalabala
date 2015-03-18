package ui.panel.gamedata;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.panel.BottomPanel;

/**
 * 比赛数据的主界面
 * @author cylong
 * @version 2015年3月19日 上午3:55:49
 */
public class GameDataPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -6986506405843542454L;
	
	private String gameImgPath = UIConfig.IMG_PATH + "gameData/";
	
	/** 确认按钮 */
	private ImgButton confirmBtn;
	/** 确认按钮图片路径 */
	private String confirmPath = gameImgPath + "confirm.png";
	/** 移动到确认按钮上的图片路径 */
	private String confirmOnPath = gameImgPath + "confirmOn.png";
	/** 点击确认按钮的图片路径 */
	private String confirmClickPath = gameImgPath + "confirmClick.png";

	/**
	 * @param url 背景图片的url
	 */
	public GameDataPanel(String url) {
		super(url);
		confirmBtn = new ImgButton(confirmPath, 917, 123, confirmClickPath, confirmOnPath);
		this.add(confirmBtn);
	}
	
}
