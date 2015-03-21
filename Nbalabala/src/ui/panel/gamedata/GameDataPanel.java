package ui.panel.gamedata;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;

import ui.DateChooser;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.comboBox.MyComboBox;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import vo.MatchProfileVO;
import bl.matchquerybl.MatchQuery;
import blservice.MatchQueryBLService;

/**
 * 比赛数据的主界面
 * 
 * @author cylong
 * @version 2015年3月19日 上午3:55:49
 */
public class GameDataPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -6986506405843542454L;

	private String gameImgPath = UIConfig.IMG_PATH + "gameData/";

	/** 确认按钮 */
	private ImgButton confirmBtn1, confirmBtn2;
	/** 确认按钮图片路径 */
	private String confirmPath = gameImgPath + "confirm.png";
	/** 移动到确认按钮上的图片路径 */
	private String confirmOnPath = gameImgPath + "confirmOn.png";
	/** 点击确认按钮的图片路径 */
	private String confirmClickPath = gameImgPath + "confirmClick.png";
	/** 下拉框 */
	private MyComboBox box1, box2;

	String[] team = new String[] { "凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂",
			"热火", "魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛", "火箭", "灰熊",
			"鹈鹕", "马刺" };
	String[] teamArr = new String[] { "BOS", "BKN", "NYK", "PHI", "TOR", "CHI", "CLE", "DET", "IND", "MIL", "ATL",
			"CHA", "MIA", "ORL", "WAS", "GSW", "LAC", "LAL", "PHX", "SAC", "DEN", "MIN", "OKC", "POR", "UTA",
			"DAL", "HOU", "MEM", "NOP", "SAS" };

	/** 下拉框的坐标 宽高 */
	int box1X = 629, box2X = 818, box1Y = 44, box2Y = 80, boxWidth = 153, boxHeight = 30;
	int teamY_1=280,teamY_2=308,inter=54;
	int teamX_1=123,score_1=249,score_2=305,score_3=361,score_4=417,
			addTime_1=478,addTime_2=562,addTime_3=646,score=730;
	/** 技术统计 */
	int analyX=825,analyY=293;
	
	MatchQueryBLService matchQuery = new MatchQuery();
	DateChooser dateChooser;
	MainController controller;
	ArrayList<MatchProfileVO> matchProfile;
	/** 画线 */
	ImgButton[] lineImg;
	/** 显示数据的panel */
	DataPanel dataPanel;
	int dataPanelX = 95,dataPanelY=276,dataPanelWidth=822,dataPanelHeight=253;
	JScrollPane scroll;

	/**
	 * @param url
	 * 背景图片的url
	 */
	public GameDataPanel(MainController controller, String url) {
		super(controller, url);
		addComboBox();
		this.controller = controller;
		addDateChooser();
		addConfirmBtn();
		addDataPanel();
	}

	public void addDataPanel(){
		dataPanel = new DataPanel(dataPanelX,dataPanelY,dataPanelWidth,dataPanelHeight);
		scroll = new JScrollPane(dataPanel);
		scroll.setBounds(dataPanelX, dataPanelY, dataPanelWidth, dataPanelHeight);
		scroll.setBorder(null);
		scroll.setOpaque(false);
		scroll.add(dataPanel);
		this.add(scroll);
	}
	
	public void addDetail(final int i){
		GameButton detail = new GameButton(400,250,75,25,"详细信息");
		this.add(detail);
		detail.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				controller.toOneGamePanel(GameDataPanel.this,matchProfile,analyzeSection(matchProfile.get(i)));
			}
		});
	}
	
	/**
	 * 添加确认按钮
	 * 
	 * @author lsy
	 * @version 2015年3月21日 下午4:29:48
	 */
	public void addConfirmBtn() {
		confirmBtn1 = new ImgButton(confirmPath, 917, 123, confirmClickPath, confirmOnPath);
		confirmBtn2 = new ImgButton(confirmPath, 450, box1Y, confirmClickPath, confirmOnPath);
		this.add(confirmBtn1);
		this.add(confirmBtn2);
		confirmBtn1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int team1 = box1.getSelectedIndex();
				int team2 = box2.getSelectedIndex();
				matchProfile = matchQuery.screenMatchByTeam(teamArr[team1], teamArr[team2]);
				addLine(matchProfile.size());
			}
		});
		confirmBtn2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Date date = dateChooser.getDate();
				matchProfile = matchQuery.screenMatchByDate(date);
				addLine(matchProfile.size());
			}
		});
	}

	public void addLine(int size){
		lineImg = new ImgButton[size];
		for(int i = 0;i<size;i++){
		 lineImg[i]=new ImgButton(gameImgPath+"line.png",0,54*(i+1),gameImgPath+"line.png",gameImgPath+"line.png");
		 scroll.add(lineImg[i]);
		}
		this.repaint();
	}
	
	/**
	 * 分析打了几节
	 * @author lsy
	 * @version 2015年3月21日  下午5:15:29
	 */
	/** 每节比分 ，格式为“27-25;29-31;13-25;16-31;”*/
	public int analyzeSection(MatchProfileVO pro) {
		String gameInfo = pro.getEachSectionScore();
		String[] eachSection = gameInfo.split(";");
		return eachSection.length;
	}

	/**
	 * 日期选择器
	 * 
	 * @author lsy
	 * @version 2015年3月21日 下午4:29:57
	 */
	public void addDateChooser() {
		dateChooser = new DateChooser();
		controller.addDateChooserPanel(this, dateChooser, 257, box1Y);
	}

	/**
	 * 下拉框
	 * 
	 * @author lsy
	 * @version 2015年3月21日 下午4:30:04
	 */
	public void addComboBox() {
		box1 = new MyComboBox(team, box1X, box1Y, boxWidth, boxHeight);
		box2 = new MyComboBox(team, box2X, box2Y, boxWidth, boxHeight);
		this.add(box1);
		this.add(box2);
	}

}
