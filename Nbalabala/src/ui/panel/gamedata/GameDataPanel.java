package ui.panel.gamedata;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import ui.DateChooser;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.comboBox.MyComboBox;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import vo.MatchProfileVO;
import bl.matchbl.MatchQuery;
import blservice.MatchQueryBLService;

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
	private ImgButton confirmBtn1,confirmBtn2;
	/** 确认按钮图片路径 */
	private String confirmPath = gameImgPath + "confirm.png";
	/** 移动到确认按钮上的图片路径 */
	private String confirmOnPath = gameImgPath + "confirmOn.png";
	/** 点击确认按钮的图片路径 */
	private String confirmClickPath = gameImgPath + "confirmClick.png";
	/** 下拉框 */
	private MyComboBox box1,box2;
	
	String[] team = new String[]{"凯尔特人","篮网","尼克斯","76人","猛龙","公牛","骑士","活塞","步行者","雄鹿","老鹰","黄蜂","热火","魔术","奇才","勇士","快船","湖人","太阳","国王","掘金","森林狼","雷霆","开拓者","爵士"
			,"小牛","火箭","灰熊","鹈鹕","马刺"};
	String[] teamArr = new String[]{"BOS","BKN","NYK","PHI","TOR","CHI","CLE","DET","IND","MIL","ATL","CHA","MIA","ORL","WAS",
			"GSW","LAC","LAL","PHX","SAC","DEN","MIN","OKC","POR","UTA","DAL","HOU","MEM","NOP","SAS"};
	
	int box1X=629,box2X = 818,box1Y = 44, box2Y = 80,boxWidth = 153,boxHeight = 30;

	MatchQueryBLService matchQuery = new MatchQuery();
	DateChooser dateChooser;
	MainController controller;
	
	/**
	 * @param url 背景图片的url
	 */
	public GameDataPanel(MainController controller,String url) {
		super(controller, url);
		addComboBox();
		this.controller = controller;
		addDateChooser();
		addConfirmBtn();
	}
	
	public void addConfirmBtn(){
		confirmBtn1 = new ImgButton(confirmPath, 917, 123, confirmClickPath, confirmOnPath);
		confirmBtn2 =  new ImgButton(confirmPath, 450, box1Y, confirmClickPath, confirmOnPath);
		this.add(confirmBtn1);
		this.add(confirmBtn2);
		confirmBtn1.addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 int team1 =box1.getSelectedIndex();
				 int team2 = box2.getSelectedIndex();
				 ArrayList<MatchProfileVO> matchProfile = matchQuery.screenMatchByTeam(teamArr[team1],teamArr[team2]);
				 //TODO 设置表格
			 }
		});
		confirmBtn2.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				Date date = dateChooser.getDate();
				ArrayList<MatchProfileVO> matchProfile =  matchQuery.screenMatchByDate(date);
				//TODO 设置表格
			}
		});
	}
	
	public void addDateChooser(){
		dateChooser = new DateChooser();
		controller.addDateChooserPanel(this,dateChooser,257,box1Y);
	}
	
	public void addComboBox(){
		box1= new MyComboBox(team,box1X,box1Y,boxWidth,boxHeight);
		box2= new MyComboBox(team,box2X,box2Y,boxWidth,boxHeight);
		this.add(box1);
		this.add(box2);
	}
	
}
