package ui.panel.gamedata;

import java.util.ArrayList;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import vo.MatchProfileVO;

/**
 * 某场比赛数据界面
 * @author lsy
 * @version 2015年3月21日  下午4:01:02
 */
public class GamePanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5789708998830911573L;
	String url = UIConfig.IMG_PATH+"game/";
	String timeURL;
	int section;
	ImgButton timeImg;
	
	public GamePanel(MainController controller, String url,ArrayList<MatchProfileVO> matchProfile,int section) {
		super(controller, url);
		this.section = section;
		addTime();
	}
	
	public void addTime(){
		timeURL = url+"time"+(section-4)+".png";
		timeImg = new ImgButton(timeURL,260,80,timeURL,timeURL);
		this.add(timeImg);
	}

}
