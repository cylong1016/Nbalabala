package ui.panel.gamedata;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ui.DateChooser;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.comboBox.MyComboBox;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomTable;
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
	int teamY_1 = 280, teamY_2 = 308, inter = 54;
	int teamX_1 = 123, score_1 = 249, score_2 = 305, score_3 = 361, score_4 = 417, addTime_1 = 478,
			addTime_2 = 562, addTime_3 = 646, score = 730;
	/** 技术统计 */
	int analyX = 825, analyY = 293;

	MatchQueryBLService matchQuery = new MatchQuery();
	DateChooser dateChooser;
	MainController controller;
	ArrayList<MatchProfileVO> matchProfile;
	/** 画线 */
	GameDataButton[] detailImg;
	/** 显示数据的panel */
	DataPanel dataPanel;
	int dataPanelX = 58, dataPanelY = 238, dataPanelWidth = 888, dataPanelHeight = 292;
	JScrollPane scroll;
	/** 比赛场数 */
	int gameSum;

	/**
	 * @param url
	 *            背景图片的url
	 */
	public GameDataPanel(MainController controller, String url) {
		super(controller, url);
		this.controller = controller;
		addDataPanel();
		addComboBox();
		addDateChooser();
		addConfirmBtn();
	}

	public void addDataPanel() {
		dataPanel = new DataPanel(dataPanelX, dataPanelY, dataPanelWidth, dataPanelHeight);
		dataPanel.setOpaque(true);
		dataPanel.setBackground(Color.white);
		dataPanel.setBorder(null);
		this.add(dataPanel);
		this.repaint();
	}

	/**
	 * 技术统计按钮
	 * 
	 * @param i
	 *            打了几节
	 * @author lsy
	 * @version 2015年3月21日 下午10:28:10
	 */
	public void addDetail(int gameSum) {
		int tableInter = 64;
		detailImg = new GameDataButton[gameSum];
		for (int i = 0; i < gameSum; i++) {
			detailImg[i] = new GameDataButton(700, 45+i*tableInter, 100, 40, "技术统计");
			dataPanel.add(detailImg[i]);
			final int temp=i;
			detailImg[i].addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					controller.toOneGamePanel(GameDataPanel.this,matchProfile.get(temp),GameDataPanel.this);
				}
			});
			this.repaint();
		}
	}

	/**
	 * 添加确认按钮
	 * 
	 * @author lsy
	 * @version 2015年3月21日 下午4:29:48
	 */
	int clickTime=0;
	public void addConfirmBtn() {
		confirmBtn1 = new ImgButton(confirmPath, 917, 123, confirmClickPath, confirmOnPath);
		confirmBtn2 = new ImgButton(confirmPath, 450, box1Y, confirmClickPath, confirmOnPath);
		this.add(confirmBtn1);
		this.add(confirmBtn2);
		confirmBtn1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(clickTime!=0){
					dataPanel.removeAll();;
				}
				clickTime++;
				int team1 = box1.getSelectedIndex();
				int team2 = box2.getSelectedIndex();
				matchProfile = matchQuery.screenMatchByTeam(teamArr[team1], teamArr[team2]);
				gameSum = matchProfile.size();
				addDetail(matchProfile.size());
				setTable();
				addTable();
			}
		});
		confirmBtn2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(clickTime!=0){
					dataPanel.removeAll();
				}
				clickTime++;
				Date date = dateChooser.getDate();
				matchProfile = matchQuery.screenMatchByDate(date);
				gameSum = matchProfile.size();
				addDetail(matchProfile.size());
				setTable();
				addTable();
			}
		});
	}

	public void paint(Graphics g){
		super.paint(g);
	}	
	/** 两个队伍每节的比分 */
	String[] score1 = {"0","0","0","0","0","0","0"};
	String[] score2 = {"0","0","0","0","0","0","0"};
	/** 两支球队缩写 */
	String[] teamShort;
	/** 两支球队全称 */
	String[] teamLong;
	/** 两支球队比赛总分 */
	String[] scoreAll;
	/** 每节比分 */
	String[] eachScore;
	
	/**
	 * 拆解传回来的vo
	 * @author lsy
	 * @version 2015年3月22日  上午12:04:52
	 */
	public void analyzeVO(MatchProfileVO proVOArray){
		scoreAll = proVOArray.getScore().split("-");//两支球队比赛总分
		eachScore = proVOArray.getEachSectionScore().split(";");
		int eachlth=eachScore.length;
		for(int i = 0;i<eachlth;i++){
			String[]scoreTemp=eachScore[i].split("-");
			score1[i]=scoreTemp[0];
			score2[i]=scoreTemp[1];
		}
		
	}
	
	/** 表格内容 */
	Object[][] rowData={};
	String []columns={};
	public void setTable(){
		columns=new String[]{"","1","2","3","4","加时一","加时二","加时三","总分"};
		rowData = new String[2*gameSum+1][columns.length];
		for(int i = 0;i<columns.length;i++){
			rowData[0][i]=columns[i];
		}
		for(int i = 1; i<gameSum*2+1;i=i+2){
			rowData[i][0] = (String) box1.getSelectedItem();
		}
		for(int i = 2;i<gameSum*2+1;i=i+2){
			rowData[i][0] = (String) box2.getSelectedItem();
		}
		for(int j =1;j<gameSum*2+1;j=j+2){
			MatchProfileVO pro = matchProfile.get(j/2);
			score1 = new String[]{"0","0","0","0","0","0","0"};
			score2 = new String[]{"0","0","0","0","0","0","0"};
			analyzeVO(pro);
			rowData[j][8] = scoreAll[0];
			rowData[j+1][8]=scoreAll[1];
			addScore(j/2);
		}
		
	}
	
	BottomTable table = new BottomTable(rowData,columns);
	public void addTable(){
		table = new BottomTable(rowData,columns);
		table.setRowHeight(32);
		table.setBounds(0, 0, 670, 292);
		makeFace(table);
		dataPanel.add(table);
		table.setBorder(null);
		this.repaint();
	}
	
	/**
	 * 设置table不同行的不同颜色
	 * @param table
	 * @author lsy
	 * @version 2015年3月22日  下午8:41:14
	 */
	private void makeFace(JTable table) {

	    try {
	      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
	        /** serialVersionUID */
			private static final long serialVersionUID = -4145147988893713337L;

			public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
	        		if (row== 0||(row%4==3||row%4==0)){
	        			
	        			setBackground(UIConfig.BUTTON_COLOR);
	        			setForeground(Color.white);
	        		}
	        		else {
	        			setBackground(Color.white);
	        			setForeground(Color.black);
	        		}
	          return super.getTableCellRendererComponent(table, value,
	              isSelected, hasFocus, row, column);
	        }
	      };
	      for (int i = 0; i < table.getColumnCount(); i++) {
	        table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
	      }
	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }

	  }
	
	public void addScore(int line){
		for(int i = 0;i<7;i++){
			rowData[2*line+1][i+1]=score1[i];
			rowData[2*line+2][i+1]=score2[i];
		}
	}
	
	/**
	 * 根据缩写返回球队全称
	 * @author lsy
	 * @version 2015年3月22日  上午12:01:45
	 */
	public String match(String abbr){
		for(int i = 0 ;i<30;i++){
			if(teamArr[i] == abbr){
				return team[i];
			}
		}
		return null;
	}

	/**
	 * 分析打了几节
	 * 
	 * @author lsy
	 * @version 2015年3月21日 下午5:15:29
	 */
	/** 每节比分 ，格式为“27-25;29-31;13-25;16-31;” */
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
