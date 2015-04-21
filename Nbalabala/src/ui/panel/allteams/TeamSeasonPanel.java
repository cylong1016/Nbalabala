package ui.panel.allteams;

import java.text.DecimalFormat;

import javax.swing.table.DefaultTableModel;

import ui.common.SeasonInputPanel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.panel.allplayers.ContrastDiagram;
import utility.Constants;
import vo.TeamSeasonVO;

/**
 * 球队赛季数据界面
 * @author lsy
 * @version 2015年4月13日  下午8:31:55
 */
public class TeamSeasonPanel extends TeamFatherPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5523253635537412179L;
	private ContrastDiagram cd;
	/** 队伍数据表格 */
	private BottomTable playerTable;
	/** 放表格的滚动条 */
	private BottomScrollPane scroll;
	/** 表格中数据显示的小数点位数 */
	private DecimalFormat format = new DecimalFormat("0.000");
	private String[] columns;
	private String[][] rowData;
	/** 赛季选择器 */
	protected SeasonInputPanel seasonInput;
	
	public TeamSeasonPanel(BottomPanel panelFrom,String url, String abbr) {
		super(panelFrom,url, abbr);
		setEffect(0);
		FROM_PANEL = panelFrom;
		seasonInput = new SeasonInputPanel(this);
		seasonInput.setLocation(515, y);
		this.add(seasonInput);
		teamDetail = teamQuery.getTeamDetailByAbbr(abbr, seasonInput.getSeason());
		addContrastDiagram();
		updateContrastDiagram();
		addplayerTable(teamDetail.getSeasonRecord());
		updateSeasonTable(teamDetail.getSeasonRecord());
		
	}
	
	/**
	 * 添加柱状图
	 * @author lsy
	 * @version 2015年4月13日  下午8:44:12
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
	
	public void refresh(){
		teamDetail = teamQuery.getTeamDetailByAbbr(abbr, seasonInput.getSeason());
		updateContrastDiagram();
		updateSeasonTable(teamDetail.getSeasonRecord());
	}
	
	/**
	 * 更新柱状图
	 * @author lsy
	 * @version 2015年4月13日  下午8:44:22
	 */
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
	 * 更新赛季数据表格
	 * @param record
	 * @author lsy
	 * @version 2015年4月13日  下午8:46:11
	 */
	public void updateSeasonTable(TeamSeasonVO record) {
		columns = new String[]{ "", "球队名称", "胜场数", "负场数", "总场数", "胜率", "投篮命中", "投篮出手", "投篮命中率", "三分命中", "三分出手",
				"三分命中率", "罚球命中", "罚球出手", "罚球命中率", "进攻篮板数", "防守篮板数", "篮板总数", "进攻篮板效率", "防守篮板效率", "进攻回合", "进攻效率",
				"防守回合", "防守效率", "抢断", "抢断效率", "助攻", "助攻率", "盖帽", "失误", "犯规", "得分" };
		rowData = new String[2][columns.length];
		playerTable.setModel(new DefaultTableModel(rowData, columns));
		playerTable.getColumnModel().getColumn(18).setPreferredWidth(80);
		playerTable.getColumnModel().getColumn(19).setPreferredWidth(80);
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

	/**
	 * 添加表格
	 * @param record
	 * @author lsy
	 * @version 2015年4月13日  下午8:46:00
	 */
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
	

	public void setButton() {
		button[0] = new TeamSeasonButton(seasonX, y, width1, height, "赛季数据");
		button[1] = new TeamSeasonButton(lineupX, y, width2, height, "阵容");
		button[2] = new TeamSeasonButton(gameX, y, width1, height, "赛程数据");
	}


}
