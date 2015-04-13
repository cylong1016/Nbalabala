package ui.panel.allteams;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import ui.UIConfig;
import ui.common.SeasonInputPanel;
import ui.common.UserMouseAdapter;
import ui.common.button.ImgButton;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import ui.panel.allplayers.ContrastDiagram;
import utility.Constants;
import vo.PlayerProfileVO;
import vo.TeamDetailVO;
import vo.TeamSeasonVO;
import bl.matchquerybl.MatchQuery;
import bl.teamquerybl.TeamQuery;
import blservice.MatchQueryBLService;
import blservice.TeamQueryBLService;

/**
 * 球队赛季数据
 * 
 * @author lsy
 * @version 2015年3月20日 下午11:32:02
 */
public class TeamSeasonPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 4901174711583565164L;
	int seasonX = 672, lineupX = 782, gameX = 857, y = 190;
	int width1 = 83, width2 = 48, height = 25;
	TeamSeasonButton[] button = new TeamSeasonButton[3];
	TeamQueryBLService teamQuery = new TeamQuery();
	MainController controller;
	String abbr;
	MatchQueryBLService matchQuery = new MatchQuery();

	/** 队伍数据表格 */
	private BottomTable playerTable;
	/** 放表格的滚动条 */
	private BottomScrollPane scroll;
	/** 表格中数据显示的小数点位数 */
	private DecimalFormat format = new DecimalFormat("0.000");
	String[] columns;
	String[][] rowData;
	protected ImgButton back;
	protected String url = UIConfig.IMG_PATH + "players/";
	protected BottomPanel allteams;
	ImgLabel logo;
	MyLabel teamName;
	String[] teamPlace = { "波士顿", "布鲁克林", "纽约", "费城", "多伦多", "芝加哥", "克利夫兰", "底特律", "印第安纳", "密尔沃基", "亚特兰大", "夏洛特",
			"迈阿密", "奥兰多", "华盛顿", "金洲", "洛杉矶", "洛杉矶", "菲尼克斯", "萨克拉门托", "丹佛", "明尼苏达", "俄克拉荷马", "波特兰", "犹他", "达拉斯",
			"休斯敦", "孟菲斯", "新奥尔良", "圣安东尼奥" };
	String[] teamShort = new String[] { "BOS", "BKN", "NYK", "PHI", "TOR", "CHI", "CLE", "DET", "IND", "MIL",
			"ATL", "CHA", "MIA", "ORL", "WAS", "GSW", "LAC", "LAL", "PHX", "SAC", "DEN", "MIN", "OKC", "POR",
			"UTA", "DAL", "HOU", "MEM", "NOP", "SAS" };
	String[] team = new String[] { "凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂",
			"热火", "魔术", "奇才", "勇士", "快船", "湖人", "太阳", "国王", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "小牛", "火箭", "灰熊",
			"鹈鹕", "马刺" };
	/** 球队详细信息 */
	TeamDetailVO teamDetail;
	int order = 0;
	
	private int state = 0;
	private ContrastDiagram cd;
	
	/** 赛季选择器 */
	protected SeasonInputPanel seasonInput;

	// 用x来判断此时是赛季数据还是阵容 0代表赛季 1代表阵容
	public TeamSeasonPanel(BottomPanel allteams,String url, String abbr, int x) {
		super(url);
		this.allteams = allteams;
		this.abbr = abbr;
		setButton();
		addButton();
		setEffect(x);
		addListener();
		addBack();
		seasonInput = new SeasonInputPanel(this);
		seasonInput.setLocation(515, y);
		this.add(seasonInput);
		teamDetail = teamQuery.getTeamDetailByAbbr(abbr, seasonInput.getSeason());
		addLabel(teamDetail.getLogo());
		iniTable(x);
		addContrastDiagram();
	}
	
	/**
	 * 添加球员和联盟平均的比较图
	 * @author cylong
	 * @version 2015年4月11日 上午1:14:43
	 */
	protected void addContrastDiagram() {
		/* 球隊的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
		TeamSeasonVO teamSeason = teamDetail.getSeasonRecord();
		double[] fivePlayersData = {teamSeason.getScoreAvg(), teamSeason.getAssistAvg(),
										teamSeason.getTotalReboundAvg(), teamSeason.getFreeThrowPercent(),
										teamSeason.getThreePointPercent()};
		double[] fiveArgsAvg = teamQuery.getFiveArgsAvg(seasonInput.getSeason());
		double[] highestScoreReboundAssist = teamQuery.getHighestScoreReboundAssist(seasonInput.getSeason());
		cd = new ContrastDiagram(fivePlayersData, fiveArgsAvg, highestScoreReboundAssist, "球队平均");
		cd.setBounds(57, 260, 888, 160);
		this.add(cd);
		cd.updateUI();
		cd.repaint();
	}
	
	protected void updateContrastDiagram() {
		/* 球隊的场均得分、助攻、篮板、 罚球命中率、三分命中率的平均值 */
		TeamSeasonVO teamSeason = teamDetail.getSeasonRecord();
		double[] fivePlayersData = {teamSeason.getScoreAvg(), teamSeason.getAssistAvg(),
										teamSeason.getTotalReboundAvg(), teamSeason.getFreeThrowPercent(),
										teamSeason.getThreePointPercent()};
		double[] fiveArgsAvg = teamQuery.getFiveArgsAvg(seasonInput.getSeason());
		double[] highestScoreReboundAssist = teamQuery.getHighestScoreReboundAssist(seasonInput.getSeason());
		cd.setData(fivePlayersData, fiveArgsAvg, highestScoreReboundAssist);
	}
	
	
	/**
	 * 判断哪个表格应该被显示出来
	 * @param i
	 * @author lsy
	 * @version 2015年3月25日  下午1:15:54
	 */
	public void iniTable(int i) {
		addplayerTable(teamDetail.getSeasonRecord());
		state = i;
		if(i == 0) {
			updateSeasonTable(teamDetail.getSeasonRecord());
		} else if(i == 1) {
			ArrayList<PlayerProfileVO> players = teamDetail.getPlayers();
			updatePlayerTable(players);
		}
	}
	
	
	MyLabel[] teamInfo = new MyLabel[5];
	int lbWidth = 100,lbHeight = 50, lbx = 450, lby = 50;
	/**
	 * 添加logo及队名
	 * @author lsy
	 * @version 2015年3月25日  上午11:43:29
	 */
	public void addLabel(Image img){
		logo = new ImgLabel(130,6,150,150,img);
		teamName = new MyLabel(240,50,300,50,match());
		teamName.setForeground(UIConfig.BUTTON_COLOR);
		teamName.setFont(new Font("微软雅黑",0,30));
		this.add(logo);
		this.add(teamName);
		int label_x = 530;
		teamInfo[0] = new MyLabel(310,90,lbWidth,lbHeight,teamDetail.getProfile().getAbbr());
		teamInfo[1] = new MyLabel(label_x,15,lbWidth*4,lbHeight,"所在地:  "+teamDetail.getProfile().getLocation());
		teamInfo[2] = new MyLabel(label_x,50,lbWidth*4,lbHeight, "所属赛区： "+teamDetail.getProfile().getDivisionString());
		teamInfo[3] = new MyLabel(label_x,85,lbWidth*4,lbHeight,"主场:  "+teamDetail.getProfile().getHome());
		teamInfo[4] = new MyLabel(label_x,120,lbWidth*4,lbHeight,"建队时间:  "+teamDetail.getProfile().getSince());

		for(int i = 0;i<teamInfo.length;i++){
			teamInfo[i].setFont(new Font("微软雅黑",0,20));
			this.add(teamInfo[i]);
			if(i!=0){
			teamInfo[i].setHorizontalAlignment(JLabel.LEFT);
			}
		}
		teamInfo[0].setFont(new Font("微软雅黑",0,25));
	}
	
	public void refresh(){
		teamDetail = teamQuery.getTeamDetailByAbbr(abbr, seasonInput.getSeason());
		if (state == 0 && scroll != null) {
			remove(scroll);
			repaint();
			iniTable(0);
			updateContrastDiagram();
			repaint();
		}
		
	}
	
	public String match(){
		for(order = 0;order<30;order++){
			if(teamDetail.getProfile().getAbbr().equals(teamShort[order])){
				return teamPlace[order]+" "+team[order];
			}
		}
		return "";
	}

	/**
	 * 添加返回按钮
	 * @author lsy
	 * @version 2015年3月25日  上午11:02:11
	 */
	public void addBack() {
		back = new ImgButton(url + "back.png", 50, 50, url + "backOn.png", url + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				MainController.backToOnePanel(TeamSeasonPanel.this, allteams);
			}

		});
	}

	
	/**
	 * 球队赛季数据表格
	 * 
	 * @author lsy
	 * @version 2015年3月25日 上午10:34:45
	 */
	public void updateSeasonTable(TeamSeasonVO record) {
		columns = new String[]{ "", "球队名称", "胜场数", "负场数", "总场数", "胜率", "投篮命中", "投篮出手", "投篮命中率", "三分命中", "三分出手",
				"三分命中率", "罚球命中", "罚球出手", "罚球命中率", "进攻篮板数", "防守篮板数", "篮板总数", "进攻篮板效率", "防守篮板效率", "进攻回合", "进攻效率",
				"防守回合", "防守效率", "抢断", "抢断效率", "助攻", "助攻率", "盖帽", "失误", "犯规", "得分" };
		rowData = new String[2][columns.length];
		playerTable.setModel(new DefaultTableModel(rowData, columns));
		playerTable.getColumnModel().getColumn(18).setPreferredWidth(80);
		playerTable.getColumnModel().getColumn(19).setPreferredWidth(80);
		playerTable.setRowHeight(UIConfig.ROW_HEIGHT);
		scroll.setBounds(57, 450, 888, 80); // 表格的位置
		
		playerTable.setValueAt("总数据", 0, 0);
		playerTable.setValueAt(Constants.translateTeamAbbr(record.getTeamName()), 0, 1);
		playerTable.setValueAt(Integer.toString(record.getWins()), 0, 2);
		playerTable.setValueAt(Integer.toString(record.getLoses()), 0, 3);
		playerTable.setValueAt(Integer.toString(record.getMatchCount()), 0, 4);
		playerTable.setValueAt(format.format(record.getWinning()), 0, 5);
		playerTable.setValueAt(Integer.toString(record.getFieldGoal()), 0, 6);
		playerTable.setValueAt( Integer.toString(record.getFieldAttempt()), 0, 7);
		playerTable.setValueAt(format.format(record.getFieldPercent()), 0, 8);
		playerTable.setValueAt(Integer.toString(record.getThreePointGoal()), 0, 9);
		playerTable.setValueAt(Integer.toString(record.getThreePointAttempt()), 0, 10);
		playerTable.setValueAt(format.format(record.getThreePointPercent()), 0, 11);
		playerTable.setValueAt(Integer.toString(record.getFreethrowGoal()), 0, 12);
		playerTable.setValueAt(Integer.toString(record.getFreethrowAttempt()), 0, 13);
		playerTable.setValueAt(format.format(record.getFreeThrowPercent()), 0, 14);
		playerTable.setValueAt(Integer.toString(record.getOffensiveRebound()), 0, 15);
		playerTable.setValueAt(Integer.toString(record.getDefensiveRebound()), 0, 16);
		playerTable.setValueAt(Integer.toString(record.getTotalRebound()), 0, 17);
		playerTable.setValueAt(format.format(record.getOffensiveReboundEff()), 0, 18);
		playerTable.setValueAt(format.format(record.getDefensiveReboundEff()), 0, 19);
		playerTable.setValueAt(format.format(record.getOffensiveRound()), 0, 20);
		playerTable.setValueAt(format.format(record.getOffensiveEff()), 0, 21);
		playerTable.setValueAt(format.format(record.getDefensiveRound()), 0, 22);
		playerTable.setValueAt(format.format(record.getDefensiveEff()), 0, 23);
		playerTable.setValueAt(Integer.toString(record.getSteal()), 0, 24);
		playerTable.setValueAt(format.format(record.getStealEff()), 0, 25);
		playerTable.setValueAt(Integer.toString(record.getAssist()), 0, 26);
		playerTable.setValueAt(format.format(record.getAssistEff()), 0, 27);
		playerTable.setValueAt(Integer.toString(record.getBlock()), 0, 28);
		playerTable.setValueAt(Integer.toString(record.getTurnover()), 0, 29);
		playerTable.setValueAt(Integer.toString(record.getFoul()), 0, 30);
		playerTable.setValueAt(Integer.toString(record.getScore()), 0, 31);
		
		
		playerTable.setValueAt("平均数据", 1, 0);
		playerTable.setValueAt(Constants.translateTeamAbbr(record.getTeamName()), 1, 1);
		playerTable.setValueAt(format.format((record.getWinning())), 1, 2);
		playerTable.setValueAt(format.format((record.getLosing())), 1, 3);
		playerTable.setValueAt(Integer.toString(record.getMatchCount()), 1, 4);
		playerTable.setValueAt( format.format(record.getWinning()), 1, 5);
		playerTable.setValueAt(format.format(record.getFieldGoalAvg()), 1, 6);
		playerTable.setValueAt(format.format(record.getFieldAttemptAvg()), 1, 7);
		playerTable.setValueAt(format.format(record.getFieldPercent()), 1, 8);
		playerTable.setValueAt(format.format(record.getThreePointGoalAvg()), 1, 9);
		playerTable.setValueAt(format.format(record.getThreePointAttemptAvg()), 1, 10);
		playerTable.setValueAt(format.format(record.getThreePointPercent()), 1, 11);
		playerTable.setValueAt( format.format(record.getFreethrowGoalAvg()), 1, 12);
		playerTable.setValueAt(format.format(record.getFreethrowAttemptAvg()), 1, 13);
		playerTable.setValueAt(format.format(record.getFreeThrowPercent()), 1, 14);
		playerTable.setValueAt(format.format(record.getOffensiveReboundAvg()), 1, 15);
		playerTable.setValueAt(format.format(record.getDefensiveReboundAvg()), 1, 16);
		playerTable.setValueAt(format.format(record.getTotalReboundAvg()), 1, 17);
		playerTable.setValueAt(format.format(record.getOffensiveReboundEff()), 1, 18);
		playerTable.setValueAt(format.format(record.getDefensiveReboundEff()), 1, 19);
		playerTable.setValueAt(format.format(record.getOffensiveRoundAvg()), 1, 20);
		playerTable.setValueAt(format.format(record.getOffensiveEff()), 1, 21);
		playerTable.setValueAt(format.format(record.getDefensiveRoundAvg()), 1, 22);
		playerTable.setValueAt(format.format(record.getDefensiveEff()), 1, 23);
		playerTable.setValueAt( format.format(record.getStealAvg()), 1, 24);
		playerTable.setValueAt(format.format(record.getStealEff()), 1, 25);
		playerTable.setValueAt(format.format(record.getAssistAvg()), 1, 26);
		playerTable.setValueAt(format.format(record.getAssistEff()), 1, 27);
		playerTable.setValueAt(format.format(record.getBlockAvg()), 1, 28);
		playerTable.setValueAt(format.format(record.getTurnoverAvg()), 1, 29);
		playerTable.setValueAt(format.format(record.getFoulAvg()), 1, 30);
		playerTable.setValueAt(format.format(record.getScoreAvg()), 1, 31);
	}

	public void setButton() {
		button[0] = new TeamSeasonButton(seasonX, y, width1, height, "赛季数据");
		button[1] = new TeamSeasonButton(lineupX, y, width2, height, "阵容");
		button[2] = new TeamSeasonButton(gameX, y, width1, height, "赛程数据");
	}

	public void addButton() {
		for (int i = 0; i < 3; i++) {
			this.add(button[i]);
		}
	}

	public void addListener() {
		MouListener mou1 = new MouListener();
		for (int i = 0; i < 3; i++) {
			button[i].addMouseListener(mou1);
		}
	}

	class MouListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == TeamSeasonButton.current) {
				return;
			}
			TeamSeasonButton.current.back();
			
			TeamSeasonButton.current = (TeamSeasonButton) e.getSource();
			if (e.getSource() == button[0]) {
				state = 0;
				addContrastDiagram();
				updateContrastDiagram();
				teamDetail = teamQuery.getTeamDetailByAbbr(abbr, seasonInput.getSeason());
				updateSeasonTable(teamDetail.getSeasonRecord());
				seasonInput.setVisible(true);
				TeamSeasonPanel.this.repaint();
				return;
			} else if (e.getSource() == button[1]) {
				state = 1;
				if(cd != null) {
					TeamSeasonPanel.this.remove(cd);
				}
				seasonInput.setVisible(false);
				TeamDetailVO teamDetail = teamQuery.getTeamDetailByAbbr(abbr, seasonInput.getSeason());
				ArrayList<PlayerProfileVO> players = teamDetail.getPlayers();
				updatePlayerTable(players);
				TeamSeasonPanel.this.repaint();
			} else if (e.getSource() == button[2]) {
				state = 2;
				if(scroll != null){
					TeamSeasonPanel.this.remove(scroll);
				}
				if(cd != null){
					TeamSeasonPanel.this.remove(cd);
				}
				seasonInput.setVisible(false);
				MainController.toTeamGamePanel(allteams,TeamSeasonPanel.this, abbr);
				repaint();
			}
		}
	}
	
	
	public void addplayerTable(TeamSeasonVO record){
		columns = new String[]{ "", "球队名称", "胜场数", "负场数", "总场数", "胜率", "投篮命中", "投篮出手", "投篮命中率", "三分命中", "三分出手",
				"三分命中率", "罚球命中", "罚球出手", "罚球命中率", "进攻篮板数", "防守篮板数", "篮板总数", "进攻篮板效率", "防守篮板效率", "进攻回合", "进攻效率",
				"防守回合", "防守效率", "抢断", "抢断效率", "助攻", "助攻率", "盖帽", "失误", "犯规", "得分" };
		rowData = new String[2][columns.length];
		playerTable = new BottomTable(rowData, columns);
		playerTable.getColumnModel().getColumn(18).setPreferredWidth(80);
		playerTable.getColumnModel().getColumn(19).setPreferredWidth(80);
		scroll = new BottomScrollPane(playerTable);
		scroll.setBounds(57, 450, 888, 80); // 表格的位置
		
		this.add(scroll);
	}
	
	/**
	 * 球队阵容表格
	 * @author lsy
	 * @version 2015年3月25日  上午10:49:28
	 */
	public void updatePlayerTable(final ArrayList<PlayerProfileVO> players) {
		columns = new String[] {"姓名", "号码", "位置", "年龄","球龄","身高","体重","生日","毕业学校"};
		int size = players.size();
		int lth = columns.length;
		rowData = new String[size][lth];
		playerTable.setModel(new DefaultTableModel(rowData, columns));
		playerTable.setRowHeight(40);
		playerTable.setWidth(new int[]{140, 44, 44, 44, 44, 151, 118, 77, 209});
		scroll.setBounds(57, 260, 888, 270);
		for (int i = 0; i < players.size(); i++) {
			PlayerProfileVO ppVO = players.get(i);
			playerTable.setValueAt(ppVO.getName(), i, 0);
			playerTable.setValueAt(ppVO.getNumber(), i, 1);
			playerTable.setValueAt(ppVO.getPosition(), i, 2);
			playerTable.setValueAt(ppVO.getAge(), i, 3);
			playerTable.setValueAt(ppVO.getExp(), i, 4);
			playerTable.setValueAt(ppVO.getHeight(), i, 5);
			playerTable.setValueAt( ppVO.getWeight(), i, 6);
			playerTable.setValueAt(ppVO.getBirth(), i, 7);
			playerTable.setValueAt(ppVO.getSchool(), i, 8);
			
		}
		//将头像放入表格的第一列 监听已加好 双击球员某一信息进入下一界面
		try{
			playerTable.addMouseListener(new UserMouseAdapter(){
				
				public void mouseClicked(MouseEvent e){
					if (e.getClickCount() < 2) return;
					int rowI  = playerTable.rowAtPoint(e.getPoint());// 得到table的行号
					if ( rowI > -1){
						MainController.toPlayerInfoPanel(TeamSeasonPanel.this, 
								players.get(rowI).getName(),TeamSeasonPanel.this);
					}
					
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setEffect(int i) {
		button[i].setOpaque(true);
		button[i].setBackground(UIConfig.BUTTON_COLOR);
		button[i].setForeground(Color.white);
		TeamSeasonButton.current = button[i];
	}
}
