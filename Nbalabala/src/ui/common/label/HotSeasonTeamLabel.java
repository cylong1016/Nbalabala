package ui.common.label;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import ui.panel.allteams.TeamButton;
import utility.Constants;
import vo.HotSeasonTeamVO;
import data.teamdata.SVGHandler;
import enums.HotSeasonTeamProperty;

/**
 * 热点赛季球队所用的Label
 * 使用时只需要传入参数并且add到panel上即可
 * @author Issac Ding
 * @version 2015年4月9日  下午6:35:34
 */
public class HotSeasonTeamLabel extends HotSeasonLabel{
	
	private String teamAbbr;

	/**
	 * @param vo 从BL层得到的VO 
	 * @param property 热点的类型，如得分、篮板、助攻等
	 * @author Issac Ding
	 * @version 2015年4月9日  下午6:24:16
	 */
	public HotSeasonTeamLabel(HotSeasonTeamVO vo, HotSeasonTeamProperty property) {
		super(vo.getTop());
		teamAbbr = vo.getAbbr();
		if (vo.getTop() == 1) {
			Image logo = SVGHandler.getTeamLogo(teamAbbr);
			ImgLabel imgLabel = new ImgLabel(262,0,107,295,logo);
			this.add(imgLabel);
			
			MyLabel nameLabel = new MyLabel(108, 59, 146, 26, "队名：" + Constants.translateTeamAbbr(teamAbbr));
			this.add(nameLabel);
			
			String propertyName = getPropertyName(property);
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			MyLabel propertyLabel = new MyLabel(108, 110, 146, 26, propertyName+"："+propertyStr);
			this.add(propertyLabel);
			
			MyLabel leagueLabel = new MyLabel(108, 161, 146, 26, "联盟：" + vo.getLeague());
			this.add(leagueLabel);
		}else{
			Image logo = SVGHandler.getTeamLogo(teamAbbr);
			ImgLabel imgLabel = new ImgLabel(0, 36, 92, 75, logo);
			this.add(imgLabel);
			
			MyLabel nameLabel = new MyLabel(30,7,132,20,"队名：" + Constants.translateTeamAbbr(teamAbbr));
			this.add(nameLabel);
			
			String propertyName = getPropertyName(property);
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			MyLabel propertyLabel = new MyLabel(70, 37, 87, 20, propertyName+"："+propertyStr);
			this.add(propertyLabel);
			
			MyLabel leagueLabel = new MyLabel(80, 67, 87, 20, "联盟：" + vo.getLeague());
			this.add(leagueLabel);
		}
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() < 2) return;
//				这个跳转方法。。好奇怪哦
				MainController.toTeamSeasonPanel((BottomPanel)HotSeasonTeamLabel.this.getParent(), 
						(BottomPanel)HotSeasonTeamLabel.this.getParent(), teamAbbr,0);
			}
		});
	}
	
	private String getPropertyName(HotSeasonTeamProperty property) {
		switch (property) {
		case SCORE_AVG:
			return "得分";
		case REBOUND_AVG:
			return "篮板";
		case ASSIST_AVG:
			return "助攻";
		case BLOCK_AVG:
			return "盖帽";
		case STEAL_AVG:
			return "抢断";
		case FIELD_PERCENT:
			return "投篮";
		case THREE_POINT_PERCENT:
			return "三分";
		default:
			return "罚球";
		}
	}

}
