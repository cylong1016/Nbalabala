package ui.common.label;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import ui.MyFont;
import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import utility.Constants;
import vo.HotSeasonTeamVO;
import data.teamdata.TeamLogoCache;
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
	private MyLabel propertyTxtLabel;
	private MyLabel propertyLabel;
	private MyLabel leagueLabel;
	private DecimalFormat df = UIConfig.FORMAT;
	
	public void updateContent(HotSeasonTeamVO vo, HotSeasonTeamProperty property) {
		if (property == HotSeasonTeamProperty.FIELD_PERCENT || property == HotSeasonTeamProperty.THREE_POINT_PERCENT
				|| property == HotSeasonTeamProperty.FREETHROW_PERCENT) {
			df = UIConfig.PERCENT_FORMAT;
		}else {
			df = UIConfig.FORMAT;
		}
		imgLabel.setImage(TeamLogoCache.getTeamLogo(teamAbbr));
		nameLabel.setText(Constants.translateTeamAbbr(teamAbbr));
		String propertyName = Constants.getPropertyName(property);
		String propertyStr = df.format(vo.getProperty());
		propertyLabel.setText(propertyName+"："+propertyStr);
		leagueLabel.setText( Constants.leagueText + vo.getLeague());
	}
	
	public HotSeasonTeamLabel(HotSeasonTeamVO vo, HotSeasonTeamProperty property,int index) {
		super(vo.getTop());
		if (property == HotSeasonTeamProperty.FIELD_PERCENT || property == HotSeasonTeamProperty.THREE_POINT_PERCENT
				|| property == HotSeasonTeamProperty.FREETHROW_PERCENT) {
			df = UIConfig.PERCENT_FORMAT;
		}else {
			df = UIConfig.FORMAT;
		}
		teamAbbr = vo.getAbbr();
		if (vo.getTop() == 1) {
			Image logo = TeamLogoCache.getTeamLogo(teamAbbr);
			int width = 210;
			int height = logo.getHeight(null) * width / logo.getWidth(null);//按比例，将高度缩减
			imgLabel = new ImgLabel(180,100,width,height,logo);
			this.add(imgLabel);
			
			int labelX = 90;
			
			nameLabel = new MyLabel(labelX, 40, 146, 40, Constants.translateTeamAbbr(teamAbbr));
			if (Constants.isEng) {
				nameLabel.setFont(MyFont.YT_XL);
			}
			else {
				nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
			}

			nameLabel.setLeft();
			
			String propertyName = Constants.getPropertyName(property);
			String propertyStr = df.format(vo.getProperty());
			if(index < 5){
				propertyStr = df.format(vo.getProperty());
			}
			propertyTxtLabel = new MyLabel(labelX, 85, 146, 26, propertyName+"：");
			propertyTxtLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
			propertyTxtLabel.setForeground(UIConfig.RED_WIN_COLOR);
			propertyTxtLabel.setLeft();
			
			propertyLabel = new MyLabel(labelX + 60, 75, 146, 40, propertyStr);
			propertyLabel.setFont(MyFont.YT_XL);
			propertyLabel.setForeground(UIConfig.RED_WIN_COLOR);
			propertyLabel.setLeft();
			
			leagueLabel = new MyLabel(labelX, 130, 146, 26, Constants.leagueText + vo.getLeague());
			leagueLabel.setForeground(MyFont.LIGHT_GRAY);
			leagueLabel.setLeft();
			
			this.add(propertyLabel);
			this.add(propertyTxtLabel);
			this.add(nameLabel);
			this.add(leagueLabel);
		}else{
			Image logo = TeamLogoCache.getTeamLogo(teamAbbr);
			int width = 75;
			int height = logo.getHeight(null) * width / logo.getWidth(null);//按比例，将高度缩减
			imgLabel = new ImgLabel(0, 46,width,height,logo);
			
			this.add(imgLabel);
			
			int labelWid = 175;
			
			nameLabel = new MyLabel(0,3,labelWid,30, Constants.translateTeamAbbr(teamAbbr));
			if (Constants.isEng) {
				nameLabel.setFont(MyFont.YT_S);
			}
			String propertyName = Constants.getPropertyName(property);
			String propertyStr = df.format(vo.getProperty());
			propertyLabel = new MyLabel(0, 37, labelWid, 20, propertyName+"："+propertyStr);
			
			leagueLabel = new MyLabel(0, 77, labelWid, 20, Constants.leagueText + vo.getLeague());
			leagueLabel.setForeground(MyFont.LIGHT_GRAY);
			
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
				MainController.toTeamBottomPanel(
						(BottomPanel)(HotSeasonTeamLabel.this.getParent()),teamAbbr);
			}
		});
	}
}
