package ui.panel.teamdata;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import ui.UIConfig;
import ui.common.table.BottomTable;
import utility.Constants;
import vo.TeamSeasonVO;
import blservice.TeamSeasonBLService;
import enums.AllTeamSeasonTableCategory;
import enums.SortOrder;
import enums.TeamAllSortBasis;
import enums.TeamAvgSortBasis;
import enums.TotalOrAvg;

/**
 * 球队数据界面的表格分成基本、进攻、防守三类。
 * @author Issac Ding
 * @version 2015年4月24日  下午7:01:50
 */
public class AllTeamSeasonTable extends BottomTable{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -2321815521946267274L;

	private AllTeamSeasonTableCategory category = AllTeamSeasonTableCategory.BASIC;
	
	private TotalOrAvg totalOrAvg = TotalOrAvg.TOTAL;
	
	private ArrayList<TeamSeasonVO> vos;
	
	private TeamSeasonBLService service;
	
	/** service就是panel上用来筛选的那个service;当用户进行一次筛选后，筛选出来的vo列表;
	 * 界面当前的数据类别;总数/场均分别作为参数用来构造本表格
	 * 	用户不论怎么排序，都是本表格自己解决的事情，直到用户改变筛选条件再次筛选，由panel来remove本表格，new出下一表格 */
	public AllTeamSeasonTable(TeamSeasonBLService service, ArrayList<TeamSeasonVO> vos, 
			AllTeamSeasonTableCategory category, TotalOrAvg totalOrAvg) {
		super(new Object[vos.size()][Constants.basicTeamHeaders.length], getHeaders(category));
		this.service = service;
		this.vos = vos;
		this.category = category;
		this.totalOrAvg = totalOrAvg;
		addHeaderListener();
		refresh();
	}
	
	private static final String[] getHeaders(AllTeamSeasonTableCategory category) {
		switch (category) {
		case BASIC:
			return Constants.basicTeamHeaders;
		case OFFENSIVE:
			return Constants.offensiveTeamHeaders;
		default:
			return Constants.defensiveTeamHeaders;
		}
	}
	
	/**
	 * 改变数据类别，调用此方法，改变表头，调整列宽，并且刷新数据
	 * @param category
	 * @author Issac Ding
	 * @version 2015年4月25日  下午7:16:58
	 */
	public void changeCategory(AllTeamSeasonTableCategory category) {
		this.category = category;
		
		TableColumnModel model = getColumnModel();
		
		switch (category) {
		case BASIC:
			for (int i=0;i<Constants.basicTeamHeaders.length;i++) {
				model.getColumn(i).setHeaderValue(Constants.basicTeamHeaders[i]);
			}
			// setWidth(....)//TODO
			break;
		case OFFENSIVE:
			for (int i=0;i<Constants.offensiveTeamHeaders.length;i++) {
				model.getColumn(i).setHeaderValue(Constants.offensiveTeamHeaders[i]);
			}
			// setWidth(....)//TODO
			break;
		default:
			for (int i=0;i<Constants.defensiveTeamHeaders.length;i++) {
				model.getColumn(i).setHeaderValue(Constants.defensiveTeamHeaders[i]);
			}
			// setWidth(....)//TODO
			break;
		}
		
		clickedNum = 0;
		lastClickColumn = 0;
		refresh();
	}
	
	public void changeTotalOrAvg(TotalOrAvg totalOrAvg) {
		this.totalOrAvg = totalOrAvg;
		refresh();
	}
	
	private void refresh() {
		switch (category) {
		case BASIC:
			refreshBasic();
			break;
		case OFFENSIVE:
			refreshOffensive();
			break;
		default:
			refreshDefensive();
			break;
		}
	}
	
	/** 0表示下一次点击降序，1表示下一次点击升序排列 */
	private int clickedNum = 0;
	/** 记录上次点击的是哪一列 */
	private int lastClickColumn = 0;
	
	// 给表头添加监听，用来排序
	private void addHeaderListener() {
		
		final JTableHeader header = getTableHeader();
		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int index = header.columnAtPoint(e.getPoint());
				if (index < 1) { // 确定所点击区域在第1列之后，第一列不需要排序
					return;
				}
				// 若跟上次点击同一列，升序变降序，降序变升序
				if (index == lastClickColumn) {
					clickedNum = 1 - clickedNum;
				} else {
					clickedNum = 0;
					lastClickColumn = index;
				}

				SortOrder order = null; // 升序降序
				if (clickedNum == 1) {
					order = SortOrder.AS;
				} else {
					order = SortOrder.DE;
				}
				
				if (totalOrAvg == TotalOrAvg.AVG) {
					TeamAvgSortBasis basis = getAvgSortBasis(index);
					vos = service.getResortedTeamAvgData(basis, order);
					refresh();
				}else{
					TeamAllSortBasis basis = getAllSortBasis(index);
					vos = service.getResortedTeamAllData(basis, order);
					refresh();
				}
			}
		});
	}
	
	private TeamAvgSortBasis getAvgSortBasis(int index) {
		switch (category) {
		//"序号","球队名称","胜场数","总场数","胜率","得分","篮板","助攻","盖帽","抢断","犯规","失误","进攻效率","防守效率"
		case BASIC:
			switch (index) {
			case 1:
				return TeamAvgSortBasis.NAME;
			case 2:
				return TeamAvgSortBasis.WINS;
			case 3:
				return TeamAvgSortBasis.MATCH_COUNT;
			case 4:
				return TeamAvgSortBasis.WINNING;
			case 5:
				return TeamAvgSortBasis.SCORE_AVG;
			case 6:
				return TeamAvgSortBasis.TOTAL_REBOUND_AVG;
			case 7:
				return TeamAvgSortBasis.ASSIST_AVG;
			case 8:
				return TeamAvgSortBasis.BLOCK_AVG;
			case 9:
				return TeamAvgSortBasis.STEAL_AVG;
			case 10:
				return TeamAvgSortBasis.FOUL_AVG;
			case 11:
				return TeamAvgSortBasis.TURNOVER_AVG;
			case 12:
				return TeamAvgSortBasis.OFFENSIVE_EFF;
			default:
				return TeamAvgSortBasis.DEFENSIVE_EFF;
			}
//{"序号","球队名称","投篮命中","投篮出手","投篮%","罚球命中","罚球出手","罚球%","三分命中","三分出手","三分%","助攻率","进攻回合","进攻效率"};
		case OFFENSIVE:
			switch (index) {
			case 1:
				return TeamAvgSortBasis.NAME;
			case 2:
				return TeamAvgSortBasis.FIELD_GOAL_AVG;
			case 3:
				return TeamAvgSortBasis.FIELD_ATTEMPT_AVG;
			case 4:
				return TeamAvgSortBasis.FIELD_PERCENT;
			case 5:
				return TeamAvgSortBasis.FREETHROW_GOAL_AVG;
			case 6:
				return TeamAvgSortBasis.FREETHROW_ATTEMPT_AVG;
			case 7:
				return TeamAvgSortBasis.FREETHROW_PERCENT;
			case 8:
				return TeamAvgSortBasis.THREE_POINT_GOAL_AVG;
			case 9:
				return TeamAvgSortBasis.THREE_POINT_ATTEMPT_AVG;
			case 10:
				return TeamAvgSortBasis.THREE_POINT_PERCENT;
			case 11:
				return TeamAvgSortBasis.ASSIST_EFF;
			case 12:
				return TeamAvgSortBasis.OFFENSIVE_ROUND_AVG;
			default:
				return TeamAvgSortBasis.OFFENSIVE_EFF;
			}
//{"序号","球队名称","进攻篮板","防守篮板","总篮板","进攻篮板效率","防守篮板效率","盖帽","抢断","抢断效率","防守回合","防守效率","对手得分","对手投篮%};
		default:
			switch (index) {
			case 1:
				return TeamAvgSortBasis.NAME;
			case 2:
				return TeamAvgSortBasis.OFFENSIVE_REBOUND_AVG;
			case 3:
				return TeamAvgSortBasis.DEFENSIVE_REBOUND_AVG;
			case 4:
				return TeamAvgSortBasis.TOTAL_REBOUND_AVG;
			case 5:
				return TeamAvgSortBasis.OFFENSIVE_REBOUND_EFF;
			case 6:
				return TeamAvgSortBasis.DEFENSIVE_REBOUND_EFF;
			case 7:
				return TeamAvgSortBasis.BLOCK_AVG;
			case 8:
				return TeamAvgSortBasis.STEAL_AVG;
			case 9:
				return TeamAvgSortBasis.STEAL_EFF;
			case 10:
				return TeamAvgSortBasis.DEFENSIVE_ROUND_AVG;
			case 11:
				return TeamAvgSortBasis.DEFENSIVE_EFF;
			case 12:
				return TeamAvgSortBasis.OPPO_SCORE_AVG;
			default:
				return TeamAvgSortBasis.OPPO_FIELD_PERCENT;
			}
		}
	}
	
	private TeamAllSortBasis getAllSortBasis(int index) {
		switch (category) {
		//"序号","球队名称","胜场数","总场数","胜率","得分","篮板","助攻","盖帽","抢断","犯规","失误","进攻效率","防守效率"
		case BASIC:
			switch (index) {
			case 1:
				return TeamAllSortBasis.NAME;
			case 2:
				return TeamAllSortBasis.WINS;
			case 3:
				return TeamAllSortBasis.MATCH_COUNT;
			case 4:
				return TeamAllSortBasis.WINNING;
			case 5:
				return TeamAllSortBasis.SCORE;
			case 6:
				return TeamAllSortBasis.TOTAL_REBOUND;
			case 7:
				return TeamAllSortBasis.ASSIST;
			case 8:
				return TeamAllSortBasis.BLOCK;
			case 9:
				return TeamAllSortBasis.STEAL;
			case 10:
				return TeamAllSortBasis.FOUL;
			case 11:
				return TeamAllSortBasis.TURNOVER;
			case 12:
				return TeamAllSortBasis.OFFENSIVE_EFF;
			default:
				return TeamAllSortBasis.DEFENSIVE_EFF;
			}
//{"序号","球队名称","投篮命中","投篮出手","投篮%","罚球命中","罚球出手","罚球%","三分命中","三分出手","三分%","助攻率","进攻回合","进攻效率"};
		case OFFENSIVE:
			switch (index) {
			case 1:
				return TeamAllSortBasis.NAME;
			case 2:
				return TeamAllSortBasis.FIELD_GOAL;
			case 3:
				return TeamAllSortBasis.FIELD_ATTEMPT;
			case 4:
				return TeamAllSortBasis.FIELD_PERCENT;
			case 5:
				return TeamAllSortBasis.FREETHROW_GOAL;
			case 6:
				return TeamAllSortBasis.FREETHROW_ATTEMPT;
			case 7:
				return TeamAllSortBasis.FREETHROW_PERCENT;
			case 8:
				return TeamAllSortBasis.THREE_POINT_GOAL;
			case 9:
				return TeamAllSortBasis.THREE_POINT_ATTEMPT;
			case 10:
				return TeamAllSortBasis.THREE_POINT_PERCENT;
			case 11:
				return TeamAllSortBasis.ASSIST_EFF;
			case 12:
				return TeamAllSortBasis.OFFENSIVE_ROUND;
			default:
				return TeamAllSortBasis.OFFENSIVE_EFF;
			}
//{"序号","球队名称","进攻篮板","防守篮板","总篮板","进攻篮板效率","防守篮板效率","盖帽","抢断","抢断效率","防守回合","防守效率","对手得分","对手投篮%};
		default:
			switch (index) {
			case 1:
				return TeamAllSortBasis.NAME;
			case 2:
				return TeamAllSortBasis.OFFENSIVE_REBOUND;
			case 3:
				return TeamAllSortBasis.DEFENSIVE_REBOUND;
			case 4:
				return TeamAllSortBasis.TOTAL_REBOUND;
			case 5:
				return TeamAllSortBasis.OFFENSIVE_REBOUND_EFF;
			case 6:
				return TeamAllSortBasis.DEFENSIVE_REBOUND_EFF;
			case 7:
				return TeamAllSortBasis.BLOCK;
			case 8:
				return TeamAllSortBasis.STEAL;
			case 9:
				return TeamAllSortBasis.STEAL_EFF;
			case 10:
				return TeamAllSortBasis.DEFENSIVE_ROUND;
			case 11:
				return TeamAllSortBasis.DEFENSIVE_EFF;
			case 12:
				return TeamAllSortBasis.OPPO_SCORE;
			default:
				return TeamAllSortBasis.OPPO_FIELD_PERCENT;
			}
		}
	}
	
	//"序号","球队名称","胜场数","总场数","胜率","得分","篮板","助攻","盖帽","抢断","犯规","失误","进攻效率","防守效率"};
	private void refreshBasic() {
		
		DecimalFormat df = UIConfig.FORMAT;
		
		if (totalOrAvg == TotalOrAvg.AVG) {
			for (int i=0;i<vos.size();i++) {
				TeamSeasonVO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(Constants.translateTeamAbbr(vo.teamName), i, 1);
				setValueAt(vo.wins, i, 2);
				setValueAt(vo.matchCount, i, 3);
				setValueAt(UIConfig.PERCENT_FORMAT.format(vo.winning), i, 4);
				setValueAt(df.format(vo.scoreAvg), i, 5);
				setValueAt(df.format(vo.totalReboundAvg), i, 6);
				setValueAt(df.format(vo.assistAvg), i, 7);
				setValueAt(df.format(vo.blockAvg), i, 8);
				setValueAt(df.format(vo.stealAvg), i, 9);
				setValueAt(df.format(vo.foulAvg), i, 10);
				setValueAt(df.format(vo.turnoverAvg), i, 11);
				setValueAt(UIConfig.PERCENT_FORMAT.format(vo.offensiveEff), i, 12);
				setValueAt(UIConfig.PERCENT_FORMAT.format(vo.defensiveEff), i, 13);
			}
		}else {
			for (int i=0;i<vos.size();i++) {
				TeamSeasonVO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(Constants.translateTeamAbbr(vo.teamName), i, 1);
				setValueAt(vo.wins, i, 2);
				setValueAt(vo.matchCount, i, 3);
				setValueAt(UIConfig.PERCENT_FORMAT.format(vo.winning), i, 4);
				setValueAt(vo.score, i, 5);
				setValueAt(vo.totalRebound, i, 6);
				setValueAt(vo.assist, i, 7);
				setValueAt(vo.block, i, 8);
				setValueAt(vo.steal, i, 9);
				setValueAt(vo.foul, i, 10);
				setValueAt(vo.turnover, i, 11);
				setValueAt(df.format(vo.offensiveEff), i, 12);
				setValueAt(df.format(vo.defensiveEff), i, 13);
			}
		}
	}
	
	//{"序号","球队名称","投篮命中","投篮出手","投篮%","罚球命中","罚球出手","罚球%","三分命中","三分出手","三分%","助攻率","进攻回合","进攻效率"};
	private void refreshOffensive() {
		
		DecimalFormat df = UIConfig.FORMAT;
		DecimalFormat percentDf = UIConfig.PERCENT_FORMAT;
		
		if (totalOrAvg == TotalOrAvg.AVG) {
			for (int i=0;i<vos.size();i++) {
				TeamSeasonVO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(Constants.translateTeamAbbr(vo.teamName), i, 1);
				setValueAt(df.format(vo.fieldGoalAvg), i, 2);
				setValueAt(df.format(vo.fieldAttemptAvg), i, 3);
				setValueAt(percentDf.format(vo.fieldPercent), i, 4);
				setValueAt(df.format(vo.freethrowGoalAvg), i, 5);
				setValueAt(df.format(vo.freethrowAttemptAvg), i, 6);
				setValueAt(percentDf.format(vo.freethrowPercent), i, 7);
				setValueAt(df.format(vo.threePointGoalAvg), i, 8);
				setValueAt(df.format(vo.threePointAttemptAvg), i, 9);
				setValueAt(percentDf.format(vo.threePointPercent), i, 10);
				setValueAt(percentDf.format(vo.assistEff), i, 11);
				setValueAt(df.format(vo.offensiveRoundAvg), i, 12);
				setValueAt(df.format(vo.offensiveEff), i, 13);
			}
		}else {
			for (int i=0;i<vos.size();i++) {
				TeamSeasonVO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(Constants.translateTeamAbbr(vo.teamName), i, 1);
				setValueAt(vo.fieldGoal, i, 2);
				setValueAt(vo.fieldAttempt, i, 3);
				setValueAt(percentDf.format(vo.fieldPercent), i, 4);
				setValueAt(vo.freethrowGoal, i, 5);
				setValueAt(vo.freethrowAttempt, i, 6);
				setValueAt(percentDf.format(vo.freethrowPercent), i, 7);
				setValueAt(vo.threePointGoal, i, 8);
				setValueAt(vo.threePointAttempt, i, 9);
				setValueAt(percentDf.format(vo.threePointPercent), i, 10);
				setValueAt(percentDf.format(vo.assistEff), i, 11);
				setValueAt(vo.offensiveRound, i, 12);
				setValueAt(df.format(vo.offensiveEff), i, 13);
			}
		}
	}
	
	//"序号","球队名称","进攻篮板","防守篮板","总篮板","进攻篮板效率","防守篮板效率","盖帽","抢断","抢断效率","防守回合","防守效率","对手得分","对手投篮%"
	private void refreshDefensive() {
		
		DecimalFormat df = UIConfig.FORMAT;
		DecimalFormat percentDf = UIConfig.PERCENT_FORMAT;
		
		if (totalOrAvg == TotalOrAvg.AVG) {
			for (int i=0;i<vos.size();i++) {
				TeamSeasonVO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(Constants.translateTeamAbbr(vo.teamName), i, 1);
				setValueAt(df.format(vo.offensiveReboundAvg), i, 2);
				setValueAt(df.format(vo.defensiveReboundAvg), i, 3);
				setValueAt(df.format(vo.totalReboundAvg), i, 4);
				setValueAt(percentDf.format(vo.offensiveReboundEff), i, 5);
				setValueAt(percentDf.format(vo.defensiveReboundEff), i, 6);
				setValueAt(df.format(vo.blockAvg), i, 7);
				setValueAt(df.format(vo.stealAvg), i, 8);
				setValueAt(df.format(vo.stealEff), i, 9);
				setValueAt(df.format(vo.defensiveRoundAvg), i, 10);
				setValueAt(df.format(vo.defensiveEff), i, 11);
				setValueAt(df.format(vo.oppoScoreAvg), i, 12);
				setValueAt(percentDf.format(vo.oppoFieldPercent), i, 13);
			}
		}else {
			for (int i=0;i<vos.size();i++) {
				TeamSeasonVO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(Constants.translateTeamAbbr(vo.teamName), i, 1);
				setValueAt(vo.offensiveRebound, i, 2);
				setValueAt(vo.defensiveRebound, i, 3);
				setValueAt(vo.totalRebound, i, 4);
				setValueAt(percentDf.format(vo.offensiveReboundEff), i, 5);
				setValueAt(percentDf.format(vo.defensiveReboundEff), i, 6);
				setValueAt(vo.block, i, 7);
				setValueAt(vo.steal, i, 8);
				setValueAt(df.format(vo.stealEff), i, 9);
				setValueAt(df.format(vo.defensiveRound), i, 10);
				setValueAt(df.format(vo.defensiveEff), i, 11);
				setValueAt(vo.oppoScore, i, 12);
				setValueAt(percentDf.format(vo.oppoFieldPercent), i, 13);
			}
		}
	}
}
