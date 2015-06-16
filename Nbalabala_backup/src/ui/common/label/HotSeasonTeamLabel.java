package ui.common.label;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.controller.MainController;
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
	
	/** serialVersionUID */
	private static final long serialVersionUID = -8406263855199884283L;
	private String teamAbbr;

	/**
	 * @param vo 从BL层得到的VO 
	 * @param property 热点的类型，如得分、篮板、助攻等
	 * @author Issac Ding
	 * @version 2015年4月9日  下午6:24:16
	 */
	
	private ImgLabel imgLabel;
	private MyLabel nameLabel;
	private MyLabel propertyLabel;
	private MyLabel leagueLabel;
	
	public void updateContent(HotSeasonTeamVO vo, HotSeasonTeamProperty property) {
		imgLabel.setImage(SVGHandler.getTeamLogo(teamAbbr));
		nameLabel.setText(Constants.translateTeamAbbr(teamAbbr));
		String propertyName = getPropertyName(property);
		String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
		propertyLabel.setText(propertyName+"："+propertyStr);
		leagueLabel.setText( "联盟：" + vo.getLeague());
	}
	
	public HotSeasonTeamLabel(HotSeasonTeamVO vo, HotSeasonTeamProperty property) {
		super(vo.getTop());
		teamAbbr = vo.getAbbr();
		if (vo.getTop() == 1) {
			Image logo = SVGHandler.getTeamLogo(teamAbbr);
			int width = 210;
			int height = logo.getHeight(null) * width / logo.getWidth(null);//按比例，将高度缩减
			imgLabel = new ImgLabel(180,100,width,height,logo);
			this.add(imgLabel);
			
			int labelX = 90;
			
			nameLabel = new MyLabel(labelX, 40, 146, 33, Constants.translateTeamAbbr(teamAbbr));
			nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
			nameLabel.setLeft();
			
			String propertyName = getPropertyName(property);
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			propertyLabel = new MyLabel(labelX, 85, 146, 26, propertyName+"："+propertyStr);
			propertyLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
			propertyLabel.setForeground(UIConfig.HIST_FIRST_COLOR);
			propertyLabel.setLeft();
			
			leagueLabel = new MyLabel(labelX, 130, 146, 26, "联盟：" + vo.getLeague());
			leagueLabel.setLeft();
			
			this.add(propertyLabel);
			this.add(nameLabel);
			this.add(leagueLabel);
		}else{
			Image logo = SVGHandler.getTeamLogo(teamAbbr);
			int width = 75;
			int height = logo.getHeight(null) * width / logo.getWidth(null);//按比例，将高度缩减
			imgLabel = new ImgLabel(0, 46,width,height,logo);
			
			this.add(imgLabel);
			
			int labelWid = 175;
			
			nameLabel = new MyLabel(0,7,labelWid,20, Constants.translateTeamAbbr(teamAbbr));
			
			String propertyName = getPropertyName(property);
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			propertyLabel = new MyLabel(0, 37, labelWid, 20, propertyName+"："+propertyStr);
			
			leagueLabel = new MyLabel(0, 67, labelWid, 20, "联盟：" + vo.getLeague());
			
			MyLabel labels[] = {nameLabel, propertyLabel, leagueLabel};
			for (int i = 0; i < labels.length; i++) {
				labels[i].setRight();
			}
			
			this.add(propertyLabel);
			this.add(nameLabel);
			this.add(leagueLabel);
		}
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() < 2) return;
//				这个跳转方法。。好奇怪哦
				MainController.toTeamSeasonPanel(teamAbbr);
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
