package ui.panel.gamedata;

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
	/** 时间地址 */
	String timeURL;
	ImgButton timeImg;
	GameDataPanel gameData;
	MatchProfileVO matchPro;
	
	String[] team={ "波士顿-凯尔特人", "布鲁克林-篮网", "纽约-尼克斯", "费城-76人", "多伦多-猛龙", "芝加哥-公牛", "克利夫兰-骑士", "底特律-活塞", "印第安纳-步行者", "密尔沃基-雄鹿", "亚特兰大-老鹰", "夏洛特-黄蜂",
			"迈阿密-热火", "奥兰多-魔术", "华盛顿-奇才", "金洲-勇士", "洛杉矶-快船", "洛杉矶湖人", "菲尼克斯-太阳", "萨克拉门托-国王", "丹佛-掘金", "明尼苏达-森林狼", "俄克拉荷马-雷霆", "波特兰-开拓者", "犹他-爵士", "达拉斯-小牛", "休斯敦-火箭", "孟菲斯-灰熊",
			"新奥尔良-鹈鹕", "圣安东尼奥-马刺"};
	
	public GamePanel(MainController controller, String url,MatchProfileVO matchProfile,GameDataPanel gameData) {
		super(controller, url);
		this.gameData = gameData;
		this.matchPro = matchProfile;
		addTime();
		getScore(matchProfile);
	}
	
	public void addTime(){
		timeURL = url+"time"+(gameData.analyzeSection(matchPro)-4)+".png";
		timeImg = new ImgButton(timeURL,260,80,timeURL,timeURL);
		this.add(timeImg);
	}
	
	String[] scoreAll,score1,score2,eachScore;
	public void getScore(MatchProfileVO proVOArray){
			scoreAll = proVOArray.getScore().split("-");//两支球队比赛总分
			eachScore = proVOArray.getEachSectionScore().split(";");
			int eachlth=eachScore.length;
			for(int i = 0;i<eachlth;i++){
				String[]scoreTemp=eachScore[i].split("-");
				score1[i]=scoreTemp[0];
				score2[i]=scoreTemp[1];
		}
	}

}
