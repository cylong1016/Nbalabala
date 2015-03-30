package ui.panel.allteams;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;

import ui.UIConfig;
import ui.common.UserMouseAdapter;
import ui.common.button.ImgButton;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import vo.PlayerProfileVO;
import vo.TeamDetailVO;
import bl.matchquerybl.MatchQuery;
import bl.teamquerybl.TeamQuery;
import blservice.MatchQueryBLService;
import blservice.TeamQueryBLService;
import data.seasondata.TeamSeasonRecord;

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
	TeamButton teamButton;
	MatchQueryBLService matchQuery = new MatchQuery();

	/** 队伍数据表格 */
	private BottomTable table;
	/** 放表格的滚动条 */
	private BottomScrollPane scroll;
	/** 表格中数据显示的小数点位数 */
	private DecimalFormat format = new DecimalFormat("0.000");
	String[] columns;
	String[][] rowData;
	protected ImgButton back;
	protected String url = UIConfig.IMG_PATH + "players/";
	protected AllTeamsPanel allteams;
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

	// 用x来判断此时是赛季数据还是阵容 0代表赛季 1代表阵容
	public TeamSeasonPanel(AllTeamsPanel allteams,MainController controller, String url, TeamButton teamButton, int x) {
		super(controller, url);
		this.allteams = allteams;
		this.controller = controller;
		this.teamButton = teamButton;
		setButton();
		addButton();
		setEffect(x);
		addListener();
		addBack();
		teamDetail = teamQuery.getTeamDetailByAbbr(teamButton.team);
		addLabel(teamDetail.getLogo());
		iniTable(x);
	}
	
	/**
	 * 判断哪个表格应该被显示出来
	 * @param i
	 * @author lsy
	 * @version 2015年3月25日  下午1:15:54
	 */
	public void iniTable(int i){
		if(i==0){
			addSeasonTable(teamDetail.getSeasonRecord());
		}else{
			ArrayList<PlayerProfileVO> players = teamDetail.getPlayers();
			setPlayerTable(players);
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
		teamName = new MyLabel(280,50,300,50,match());
		teamName.setForeground(UIConfig.BUTTON_COLOR);
		teamName.setFont(new Font("微软雅黑",0,30));
		this.add(logo);
		this.add(teamName);
		teamInfo[0] = new MyLabel(350,100,lbWidth,lbHeight,teamDetail.getProfile().getAbbr());
		teamInfo[1] = new MyLabel(600,15,lbWidth*4,lbHeight,"所在地:  "+teamDetail.getProfile().getLocation());
		teamInfo[2] = new MyLabel(600,50,lbWidth*4,lbHeight, "所属赛区： "+teamDetail.getProfile().getDivisionString());
		teamInfo[3] = new MyLabel(600,85,lbWidth*4,lbHeight,"主场:  "+teamDetail.getProfile().getHome());
		teamInfo[4] = new MyLabel(600,120,lbWidth*4,lbHeight,"建队时间:  "+teamDetail.getProfile().getSince());

		for(int i = 0;i<teamInfo.length;i++){
			teamInfo[i].setFont(new Font("微软雅黑",0,20));
			this.add(teamInfo[i]);
			if(i!=0){
			teamInfo[i].setHorizontalAlignment(JLabel.LEFT);
			}
		}
		teamInfo[0].setFont(new Font("微软雅黑",0,25));
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
		back = new ImgButton(url + "back.png", 50, 50, url + "back.png", url + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				controller.backToGameDataPanel(TeamSeasonPanel.this, allteams);
			}

		});
	}
	
	/**
	 * 球队赛季数据表格
	 * 
	 * @author lsy
	 * @version 2015年3月25日 上午10:34:45
	 */
	public void addSeasonTable(TeamSeasonRecord record) {
		columns = new String[]{ "", "球队名称", "胜场数", "负场数", "总场数", "胜率", "投篮命中", "投篮出手", "投篮命中率", "三分命中", "三分出手",
				"三分命中率", "罚球命中", "罚球出手", "罚球命中率", "进攻篮板数", "防守篮板数", "篮板总数", "进攻篮板效率", "防守篮板效率", "进攻回合", "进攻效率",
				"防守回合", "防守效率", "抢断", "抢断效率", "助攻", "助攻率", "盖帽", "失误", "犯规", "得分" };
		rowData = new String[2][columns.length];
		table = new BottomTable(rowData, columns);
		for(int i = 0; i < 1; i++) {
			rowData[i][0] = "总数据";
			rowData[i][1] = record.getTeamName();
			rowData[i][2] = Integer.toString(record.getWins());
			rowData[i][3] = Integer.toString(record.getLoses());
			rowData[i][4] = Integer.toString(record.getMatchCount());
			rowData[i][5] = format.format(record.getWinning());
			rowData[i][6] = Integer.toString(record.getFieldGoal());
			rowData[i][7] = Integer.toString(record.getFieldAttempt());
			rowData[i][8] = format.format(record.getFieldPercent());
			rowData[i][9] = Integer.toString(record.getThreePointGoal());
			rowData[i][10] = Integer.toString(record.getThreePointAttempt());
			rowData[i][11] = format.format(record.getThreePointPercent());
			rowData[i][12] = Integer.toString(record.getFreethrowGoal());
			rowData[i][13] = Integer.toString(record.getFreethrowAttempt());
			rowData[i][14] = format.format(record.getFreeThrowPercent());
			rowData[i][15] = Integer.toString(record.getOffensiveRebound());
			rowData[i][16] = Integer.toString(record.getDefensiveRebound());
			rowData[i][17] = Integer.toString(record.getTotalRebound());
			rowData[i][18] = format.format(record.getOffensiveReboundEff());
			rowData[i][19] = format.format(record.getDefensiveReboundEff());
			rowData[i][20] = format.format(record.getOffensiveRound());
			rowData[i][21] = format.format(record.getOffensiveEff());
			rowData[i][22] = format.format(record.getDefensiveRound());
			rowData[i][23] = format.format(record.getDefensiveEff());
			rowData[i][24] = Integer.toString(record.getSteal());
			rowData[i][25] = format.format(record.getStealEff());
			rowData[i][26] = Integer.toString(record.getAssist());
			rowData[i][27] = format.format(record.getAssistEff());
			rowData[i][28] = Integer.toString(record.getBlock());
			rowData[i][29] = Integer.toString(record.getTurnover());
			rowData[i][30] = Integer.toString(record.getFoul());
			rowData[i][31] = Integer.toString(record.getScore());}
			
			for(int i = 1; i < 2; i++) {
				rowData[i][0] = "平均数据";
				rowData[i][1] = record.getTeamName();
				rowData[i][2] = Integer.toString(record.getWins());
				rowData[i][3] = Integer.toString(record.getLoses());
				rowData[i][4] = Integer.toString(record.getMatchCount());
				rowData[i][5] = format.format(record.getWinning());
				rowData[i][6] = format.format(record.getFieldGoalAvg());
				rowData[i][7] = format.format(record.getFieldAttemptAvg());
				rowData[i][8] = format.format(record.getFieldPercent());
				rowData[i][9] = format.format(record.getThreePointGoalAvg());
				rowData[i][10] = format.format(record.getThreePointAttemptAvg());
				rowData[i][11] = format.format(record.getThreePointPercent());
				rowData[i][12] = format.format(record.getFreethrowGoalAvg());
				rowData[i][13] = format.format(record.getFreethrowAttemptAvg());
				rowData[i][14] = format.format(record.getFreeThrowPercent());
				rowData[i][15] = format.format(record.getOffensiveReboundAvg());
				rowData[i][16] = format.format(record.getDefensiveReboundAvg());
				rowData[i][17] = format.format(record.getTotalReboundAvg());
				rowData[i][18] = format.format(record.getOffensiveReboundEff());
				rowData[i][19] = format.format(record.getDefensiveReboundEff());
				rowData[i][20] = format.format(record.getOffensiveRoundAvg());
				rowData[i][21] = format.format(record.getOffensiveEff());
				rowData[i][22] = format.format(record.getDefensiveRoundAvg());
				rowData[i][23] = format.format(record.getDefensiveEff());
				rowData[i][24] = format.format(record.getStealAvg());
				rowData[i][25] = format.format(record.getStealEff());
				rowData[i][26] = format.format(record.getAssistAvg());
				rowData[i][27] = format.format(record.getAssistEff());
				rowData[i][28] = format.format(record.getBlockAvg());
				rowData[i][29] = format.format(record.getTurnoverAvg());
				rowData[i][30] = format.format(record.getFoulAvg());
				rowData[i][31] = format.format(record.getScoreAvg());
			}
		
		scroll = new BottomScrollPane(table);
		scroll.setBounds(57, 270, 888, 80); // 表格的位置
		
		this.add(scroll);
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
				TeamSeasonPanel.this.remove(scroll);
				teamDetail = teamQuery.getTeamDetailByAbbr(teamButton.team);
				addSeasonTable(teamDetail.getSeasonRecord());
				TeamSeasonPanel.this.repaint();
				return;
			} else if (e.getSource() == button[1]) {
				TeamSeasonPanel.this.remove(scroll);
				TeamDetailVO teamDetail = teamQuery.getTeamDetailByAbbr(teamButton.team);
				ArrayList<PlayerProfileVO> players = teamDetail.getPlayers();
				setPlayerTable(players);
				TeamSeasonPanel.this.repaint();
			} else if (e.getSource() == button[2]) {
				TeamSeasonPanel.this.remove(scroll);
				controller.toTeamGamePanel(allteams,TeamSeasonPanel.this, teamButton);
			}
		}
	}

	/**
	 * 球队阵容表格
	 * @author lsy
	 * @version 2015年3月25日  上午10:49:28
	 */
	public void setPlayerTable(final ArrayList<PlayerProfileVO> players) {
		columns = new String[] {"姓名", "号码", "位置", "年龄","球龄","身高","体重","生日","毕业学校"};
		int size = players.size();
		int lth = columns.length;
		rowData = new String[size][lth];
		for (int i = 0; i < size; i++) {
			PlayerProfileVO ppVO = players.get(i);
			rowData[i][0] = ppVO.getName();
			rowData[i][1] = ppVO.getNumber();
			rowData[i][2] = ppVO.getPosition();
			rowData[i][3] = ppVO.getAge();
			rowData[i][4] = ppVO.getExp();
			rowData[i][5] = ppVO.getHeight();	//身高和体重换行显示
			rowData[i][6] = ppVO.getWeight();
			rowData[i][7] = ppVO.getBirth();
			rowData[i][8] = ppVO.getSchool();
		}
		table = new BottomTable(rowData, columns);
		//TODO 将头像放入表格的第一列 监听已加好 双击球员某一信息进入下一界面
		try{
			table.addMouseListener(new UserMouseAdapter(){
				
				public void mouseClicked(MouseEvent e){
					int rowI  = table.rowAtPoint(e.getPoint());// 得到table的行号
					if ( rowI > -1){
						controller.toPlayerInfoPanel(TeamSeasonPanel.this, players.get(rowI),TeamSeasonPanel.this);
					}
					
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		table.setRowHeight(40);
		table.setWidth(new int[]{140, 45, 45, 45, 55, 151, 125, 98, 167});
		scroll = new BottomScrollPane(table);
		scroll.setBounds(57, 250, 888, 280);
		this.add(scroll);
	}
	
	public void setEffect(int i) {
		button[i].setOpaque(true);
		button[i].setBackground(UIConfig.BUTTON_COLOR);
		button[i].setForeground(Color.white);
		TeamSeasonButton.current = button[i];
	}
}
