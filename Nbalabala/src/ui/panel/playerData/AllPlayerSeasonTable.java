package ui.panel.playerData;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import po.PlayerSeasonPO;
import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import blservice.PlayerSeasonBLService;
import enums.AllPlayerSeasonTableCategory;
import enums.PlayerAllSortBasis;
import enums.PlayerAvgSortBasis;
import enums.SortOrder;
import enums.TotalOrAvg;

/**
 * 球员数据界面的表格分成基本、进攻、防守、高阶四类。
 * 这个表格的用法示例：如果一开始默认是按名字排序的场均总数据，那么：Panel上获得按名字排序的ArrayList，Category为Basic，
 * 	AvgOrTotal为Total，service就是Panel上的同一个panel，构造本表格，add上去就完了。表头排序之类的不需要Panel关心。
 * 在此之后，如果用户点击了场均，则调用setAvgOrTotal(avg)。
 * 如果用户点击了高阶数据，则调用setCategory(Advanced)
 * 如果用户进行了某种筛选，则Remove掉本表格，通过Panel上的那个service根据用户选择的筛选条件获取一个新的ArrayList，
 * 用当前的AvgOrTotal和Category new出一个新的本表格，add上去。
 * 
 * @author Issac Ding
 * @version 2015年4月24日  下午7:01:50
 */
public class AllPlayerSeasonTable extends BottomTable{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -2321815521946267274L;

	private AllPlayerSeasonTableCategory category = AllPlayerSeasonTableCategory.BASIC;
	
	private TotalOrAvg totalOrAvg = TotalOrAvg.TOTAL;
	
	private ArrayList<PlayerSeasonPO> vos;
	
	private PlayerSeasonBLService service;
	
	/**service就是panel上用来筛选的那个同一个service 
	 * 当用户进行一次筛选后，筛选出来的vo列表，界面当前的数据类别、总数/场均分别作为参数用来构造本表格
	 * 	用户不论怎么排序，都是本表格自己解决的事情，直到用户改变筛选条件再次筛选，remove本表格，new出下一表格 */
	public AllPlayerSeasonTable(PlayerSeasonBLService service, ArrayList<PlayerSeasonPO> vos, 
			AllPlayerSeasonTableCategory category, TotalOrAvg totalOrAvg) {
		super(new Object[vos.size()][Constants.basicPlayerHeaders.length], getHeaders(category));
		this.service = service;
		this.vos = vos;
		this.category = category;
		this.totalOrAvg = totalOrAvg;
		addHeaderListener();
		addRowListener();
		refresh();
	}
	
	private static final String[] getHeaders(AllPlayerSeasonTableCategory category) {
		switch (category) {
		case BASIC:
			return Constants.basicPlayerHeaders;
		case OFFENSIVE:
			return Constants.offensivePlayerHeaders;
		case DEFENSIVE:
			return Constants.defensivePlayerHeaders;
		default:
			return Constants.advancedPlayerHeaders;
		}
	}
	
	/**
	 * 改变数据类别，调用此方法，改变表头，调整列宽，并且刷新数据
	 * @param category
	 * @author Issac Ding
	 * @version 2015年4月24日  下午7:16:58
	 */
	public void changeCategory(AllPlayerSeasonTableCategory category) {
		this.category = category;
		
		TableColumnModel model = getColumnModel();
		
		switch (category) {
		case BASIC:
			for (int i=0;i<Constants.basicPlayerHeaders.length;i++) {
				model.getColumn(i).setHeaderValue(Constants.basicPlayerHeaders[i]);
			}
			// setWidth(....)//TODO
			break;
		case OFFENSIVE:
			for (int i=0;i<Constants.offensivePlayerHeaders.length;i++) {
				model.getColumn(i).setHeaderValue(Constants.offensivePlayerHeaders[i]);
			}
			// setWidth(....)//TODO
			break;
		case DEFENSIVE:
			for (int i=0;i<Constants.defensivePlayerHeaders.length;i++) {
				model.getColumn(i).setHeaderValue(Constants.defensivePlayerHeaders[i]);
			}
			// setWidth(....)//TODO
			break;
		default:
			for (int i=0;i<Constants.advancedPlayerHeaders.length;i++) {
				model.getColumn(i).setHeaderValue(Constants.advancedPlayerHeaders[i]);
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
		case DEFENSIVE:
			refreshDefensive();
			break;
		default:
			refreshAdvanced();
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
					PlayerAvgSortBasis basis = getAvgSortBasis(index);
					vos = service.getResortedPlayersAvgData(basis, order);
					refresh();
				}else{
					PlayerAllSortBasis basis = getAllSortBasis(index);
					vos = service.getResortedPlayersAllData(basis, order);
					refresh();
				}
				
			}
		});
	}
	
	//"球员名称","所属球队","参赛","先发","在场时间","得分","篮板","助攻","盖帽","抢断","两双","得分篮板助攻","犯规","失误"
	private PlayerAvgSortBasis getAvgSortBasis(int index) {
		switch (category) {
		case BASIC:
			switch (index) {
			case 1:
				return PlayerAvgSortBasis.NAME;
			case 2:
				return PlayerAvgSortBasis.TEAM_NAME;
			case 3:
				return PlayerAvgSortBasis.MATCH_COUNT;
			case 4:
				return PlayerAvgSortBasis.FIRST_COUNT_AVG;
			case 5:
				return PlayerAvgSortBasis.TIME_AVG;
			case 6:
				return PlayerAvgSortBasis.SCORE_AVG;
			case 7:
				return PlayerAvgSortBasis.TOTAL_REBOUND_AVG;
			case 8:
				return PlayerAvgSortBasis.ASSIST_AVG;
			case 9:
				return PlayerAvgSortBasis.BLOCK_AVG;
			case 10:
				return PlayerAvgSortBasis.STEAL_AVG;
			case 11:
				return PlayerAvgSortBasis.DOUBLE_DOUBLE_AVG;
			case 12:
				return PlayerAvgSortBasis.SCORE_REBOUND_ASSIST_AVG;
			case 13:
				return PlayerAvgSortBasis.FOUL_AVG;
			case 14:
				return PlayerAvgSortBasis.TURNOVER_AVG;
			default:
				return null;
			}
//{"序号","球员名称","投篮命中","投篮出手","投篮%","罚球命中","罚球出手","罚球%","三分命中","三分出手","三分%","助攻","助攻率","真实命中率","投篮效率"};
		case OFFENSIVE:
			switch (index) {
			case 1:
				return PlayerAvgSortBasis.NAME;
			case 2:
				return PlayerAvgSortBasis.FIELD_MADE_AVG;
			case 3:
				return PlayerAvgSortBasis.FIELD_ATTEMPT_AVG;
			case 4:
				return PlayerAvgSortBasis.FIELD_PERCENT;
			case 5:
				return PlayerAvgSortBasis.FREETHROW_MADE_AVG;
			case 6:
				return PlayerAvgSortBasis.FREETHROW_ATTEMPT_AVG;
			case 7:
				return PlayerAvgSortBasis.FREETHROW_PERCENT;
			case 8:
				return PlayerAvgSortBasis.THREE_POINT_MADE_AVG;
			case 9:
				return PlayerAvgSortBasis.THREE_POINT_ATTEMPT_AVG;
			case 10:
				return PlayerAvgSortBasis.THREE_POINT_PERCENT;
			case 11:
				return PlayerAvgSortBasis.ASSIST_AVG;
			case 12:
				return PlayerAvgSortBasis.ASSIST_PERCENT;
			case 13:
				return PlayerAvgSortBasis.REAL_FIELD_PERCENT;
			case 14:
				return PlayerAvgSortBasis.FIELD_EFF;
			default:
				return null;
			}
//{"序号","球员名称","所属球队","参赛","先发","进攻篮板","防守篮板","总篮板","盖帽","抢断","进攻篮板率","防守篮板率","总篮板率","盖帽率","抢断率"};
		case DEFENSIVE:
			switch (index) {
			case 1:
				return PlayerAvgSortBasis.NAME;
			case 2:
				return PlayerAvgSortBasis.TEAM_NAME;
			case 3:
				return PlayerAvgSortBasis.MATCH_COUNT;
			case 4:
				return PlayerAvgSortBasis.FIRST_COUNT_AVG;
			case 5:
				return PlayerAvgSortBasis.OFFENSIVE_REBOUND_AVG;
			case 6:
				return PlayerAvgSortBasis.DEFENSIVE_REBOUND_AVG;
			case 7:
				return PlayerAvgSortBasis.TOTAL_REBOUND_AVG;
			case 8:
				return PlayerAvgSortBasis.BLOCK_AVG;
			case 9:
				return PlayerAvgSortBasis.STEAL_AVG;
			case 10:
				return PlayerAvgSortBasis.OFFENSIVE_REBOUND_PERCENT;
			case 11:
				return PlayerAvgSortBasis.DEFENSIVE_REBOUND_PERCENT;
			case 12:
				return PlayerAvgSortBasis.TOTAL_REBOUND_PERCENT;
			case 13:
				return PlayerAvgSortBasis.BLOCK_PERCENT;
			case 14:
				return PlayerAvgSortBasis.STEAL_PERCENT;
			default:
				return null;
			}
//{"序号","球员名称","效率","GmSc","使用率","进攻篮板率","防守篮板率","总篮板率","助攻率","真实命中率","投篮效率","盖帽率","抢断率","犯规率","失误率"};
		case ADVANCED:
			switch (index) {
			case 1:
				return PlayerAvgSortBasis.NAME;
			case 2:
				return PlayerAvgSortBasis.EFFICIENCY_AVG;
			case 3:
				return PlayerAvgSortBasis.GMSC_AVG;
			case 4:
				return PlayerAvgSortBasis.USE_PERCENT;
			case 5:
				return PlayerAvgSortBasis.OFFENSIVE_REBOUND_PERCENT;
			case 6:
				return PlayerAvgSortBasis.DEFENSIVE_REBOUND_PERCENT;
			case 7:
				return PlayerAvgSortBasis.TOTAL_REBOUND_PERCENT;
			case 8:
				return PlayerAvgSortBasis.ASSIST_PERCENT;
			case 9:
				return PlayerAvgSortBasis.REAL_FIELD_PERCENT;
			case 10:
				return PlayerAvgSortBasis.FIELD_EFF;
			case 11:
				return PlayerAvgSortBasis.BLOCK_PERCENT;
			case 12:
				return PlayerAvgSortBasis.STEAL_PERCENT;
			case 13:
				return PlayerAvgSortBasis.FOUL_PERCENT;
			case 14:
				return PlayerAvgSortBasis.TURNOVER_PERCENT;
			default:
				return null;
			}
			default:
				return null;
		}
	}
	
	//"球员名称","所属球队","参赛","先发","在场时间","得分","篮板","助攻","盖帽","抢断","两双","得分篮板助攻","犯规","失误"
	private PlayerAllSortBasis getAllSortBasis(int index) {
		switch (category) {
		case BASIC:
			switch (index) {
			case 1:
				return PlayerAllSortBasis.NAME;
			case 2:
				return PlayerAllSortBasis.TEAM_NAME;
			case 3:
				return PlayerAllSortBasis.MATCH_COUNT;
			case 4:
				return PlayerAllSortBasis.FIRST_COUNT;
			case 5:
				return PlayerAllSortBasis.TIME;
			case 6:
				return PlayerAllSortBasis.SCORE;
			case 7:
				return PlayerAllSortBasis.TOTAL_REBOUND;
			case 8:
				return PlayerAllSortBasis.ASSIST;
			case 9:
				return PlayerAllSortBasis.BLOCK;
			case 10:
				return PlayerAllSortBasis.STEAL;
			case 11:
				return PlayerAllSortBasis.DOUBLE_DOUBLE;
			case 12:
				return PlayerAllSortBasis.SCORE_REBOUND_ASSIST;
			case 13:
				return PlayerAllSortBasis.FOUL;
			case 14:
				return PlayerAllSortBasis.TURNOVER;
			default:
				return null;
			}
//{"序号","球员名称","投篮命中","投篮出手","投篮%","罚球命中","罚球出手","罚球%","三分命中","三分出手","三分%","助攻","助攻率","真实命中率","投篮效率"};
		case OFFENSIVE:
			switch (index) {
			case 1:
				return PlayerAllSortBasis.NAME;
			case 2:
				return PlayerAllSortBasis.FIELD_MADE;
			case 3:
				return PlayerAllSortBasis.FIELD_ATTEMPT;
			case 4:
				return PlayerAllSortBasis.FIELD_PERCENT;
			case 5:
				return PlayerAllSortBasis.FREETHROW_MADE;
			case 6:
				return PlayerAllSortBasis.FREETHROW_ATTEMPT;
			case 7:
				return PlayerAllSortBasis.FREETHROW_PERCENT;
			case 8:
				return PlayerAllSortBasis.THREE_POINT_MADE;
			case 9:
				return PlayerAllSortBasis.THREE_POINT_ATTEMPT;
			case 10:
				return PlayerAllSortBasis.THREE_POINT_PERCENT;
			case 11:
				return PlayerAllSortBasis.ASSIST;
			case 12:
				return PlayerAllSortBasis.ASSIST_PERCENT;
			case 13:
				return PlayerAllSortBasis.REAL_FIELD_PERCENT;
			case 14:
				return PlayerAllSortBasis.FIELD_EFF;
			default:
				return null;
			}
//{"序号","球员名称","所属球队","参赛","先发","进攻篮板","防守篮板","总篮板","盖帽","抢断","进攻篮板率","防守篮板率","总篮板率","盖帽率","抢断率"};
		case DEFENSIVE:
			switch (index) {
			case 1:
				return PlayerAllSortBasis.NAME;
			case 2:
				return PlayerAllSortBasis.TEAM_NAME;
			case 3:
				return PlayerAllSortBasis.MATCH_COUNT;
			case 4:
				return PlayerAllSortBasis.FIRST_COUNT;
			case 5:
				return PlayerAllSortBasis.OFFENSIVE_REBOUND;
			case 6:
				return PlayerAllSortBasis.DEFENSIVE_REBOUND;
			case 7:
				return PlayerAllSortBasis.TOTAL_REBOUND;
			case 8:
				return PlayerAllSortBasis.BLOCK;
			case 9:
				return PlayerAllSortBasis.STEAL;
			case 10:
				return PlayerAllSortBasis.OFFENSIVE_REBOUND_PERCENT;
			case 11:
				return PlayerAllSortBasis.DEFENSIVE_REBOUND_PERCENT;
			case 12:
				return PlayerAllSortBasis.TOTAL_REBOUND_PERCENT;
			case 13:
				return PlayerAllSortBasis.BLOCK_PERCENT;
			case 14:
				return PlayerAllSortBasis.STEAL_PERCENT;
			default:
				return null;
			}
//{"序号","球员名称","效率","GmSc","使用率","进攻篮板率","防守篮板率","总篮板率","助攻率","真实命中率","投篮效率","盖帽率","抢断率","犯规率","失误率"};
		case ADVANCED:
			switch (index) {
			case 1:
				return PlayerAllSortBasis.NAME;
			case 2:
				return PlayerAllSortBasis.EFFICIENCY;
			case 3:
				return PlayerAllSortBasis.GMSC;
			case 4:
				return PlayerAllSortBasis.USE_PERCENT;
			case 5:
				return PlayerAllSortBasis.OFFENSIVE_REBOUND_PERCENT;
			case 6:
				return PlayerAllSortBasis.DEFENSIVE_REBOUND_PERCENT;
			case 7:
				return PlayerAllSortBasis.TOTAL_REBOUND_PERCENT;
			case 8:
				return PlayerAllSortBasis.ASSIST_PERCENT;
			case 9:
				return PlayerAllSortBasis.REAL_FIELD_PERCENT;
			case 10:
				return PlayerAllSortBasis.FIELD_EFF;
			case 11:
				return PlayerAllSortBasis.BLOCK_PERCENT;
			case 12:
				return PlayerAllSortBasis.STEAL_PERCENT;
			case 13:
				return PlayerAllSortBasis.FOUL_PERCENT;
			case 14:
				return PlayerAllSortBasis.TURNOVER_PERCENT;
			default:
				return null;
			}
			default:
				return null;
		}
	}
	
	//basicPlayerHeaders = {"序号","球员名称","所属球队","参赛","先发","在场时间","得分","篮板","助攻","盖帽","抢断","两双","得分篮板助攻","犯规","失误"};
	private void refreshBasic() {
		
		DecimalFormat df = UIConfig.FORMAT;
		
		if (totalOrAvg == TotalOrAvg.AVG) {
			for (int i=0;i<vos.size();i++) {
				PlayerSeasonPO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(vo.getShortName(), i, 1);
				setValueAt(Constants.translateTeamAbbr(vo.teamAbbr), i, 2);
				setValueAt(vo.matchCount, i, 3);
				setValueAt(df.format(vo.firstCountAvg), i, 4);
				setValueAt(df.format(vo.minutesAvg), i, 5);
				setValueAt(df.format(vo.scoreAvg), i, 6);
				setValueAt(df.format(vo.totalReboundAvg), i, 7);
				setValueAt(df.format(vo.assistAvg), i, 8);
				setValueAt(df.format(vo.blockAvg), i, 9);
				setValueAt(df.format(vo.stealAvg), i, 10);
				setValueAt(df.format(vo.doubleDoubleAvg), i, 11);
				setValueAt(df.format(vo.scoreReboundAssistAvg), i, 12);
				setValueAt(df.format(vo.foulAvg), i, 13);
				setValueAt(df.format(vo.turnoverAvg), i, 14);
			}
		}else {
			for (int i=0;i<vos.size();i++) {
				PlayerSeasonPO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(vo.getShortName(), i, 1);
				setValueAt(Constants.translateTeamAbbr(vo.teamAbbr), i, 2);
				setValueAt(vo.matchCount, i, 3);
				setValueAt(vo.firstCount, i, 4);
				setValueAt(df.format(vo.minutes), i, 5);
				setValueAt(vo.score, i, 6);
				setValueAt(vo.totalRebound, i, 7);
				setValueAt(vo.assist, i, 8);
				setValueAt(vo.block, i, 9);
				setValueAt(vo.steal, i, 10);
				setValueAt(vo.doubleDoubleCount, i, 11);
				setValueAt(vo.scoreReboundAssist, i, 12);
				setValueAt(vo.foul, i, 13);
				setValueAt(vo.turnover, i, 14);
			}
		}
	}
	
	//{"序号","球员名称","效率","GmSc","使用率","进攻篮板率","防守篮板率","总篮板率","助攻率","真实命中率","投篮效率","盖帽率","抢断率","犯规率","失误率"};
	private void refreshAdvanced() {
		
		DecimalFormat df = UIConfig.FORMAT;
		DecimalFormat percentDf = UIConfig.PERCENT_FORMAT;
		
		if (totalOrAvg == TotalOrAvg.AVG) {
			for (int i=0;i<vos.size();i++) {
				PlayerSeasonPO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(vo.getShortName(), i, 1);
				setValueAt(df.format(vo.efficiencyAvg), i, 2);
				setValueAt(df.format(vo.gmscAvg), i, 3);
				setValueAt(percentDf.format(vo.usePercent), i, 4);
				setValueAt(percentDf.format(vo.offensiveReboundPercent), i, 5);
				setValueAt(percentDf.format(vo.defensiveReboundPercent), i, 6);
				setValueAt(percentDf.format(vo.totalReboundPercent), i, 7);
				setValueAt(percentDf.format(vo.assistPercent), i, 8);
				setValueAt(percentDf.format(vo.realFieldPercent), i, 9);
				setValueAt(percentDf.format(vo.fieldEff), i, 10);
				setValueAt(percentDf.format(vo.blockPercent), i, 11);
				setValueAt(percentDf.format(vo.stealPercent), i, 12);
				setValueAt(percentDf.format(vo.foulPercent), i, 13);
				setValueAt(percentDf.format(vo.turnOverPercent), i, 14);
			}
		}else {
			for (int i=0;i<vos.size();i++) {
				PlayerSeasonPO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(vo.getShortName(), i, 1);
				setValueAt(df.format(vo.efficiency), i, 2);
				setValueAt(df.format(vo.gmsc), i, 3);
				setValueAt(percentDf.format(vo.usePercent), i, 4);
				setValueAt(percentDf.format(vo.offensiveReboundPercent), i, 5);
				setValueAt(percentDf.format(vo.defensiveReboundPercent), i, 6);
				setValueAt(percentDf.format(vo.totalReboundPercent), i, 7);
				setValueAt(percentDf.format(vo.assistPercent), i, 8);
				setValueAt(percentDf.format(vo.realFieldPercent), i, 9);
				setValueAt(percentDf.format(vo.fieldEff), i, 10);
				setValueAt(percentDf.format(vo.blockPercent), i, 11);
				setValueAt(percentDf.format(vo.stealPercent), i, 12);
				setValueAt(percentDf.format(vo.foulPercent), i, 13);
				setValueAt(percentDf.format(vo.turnOverPercent), i, 14);
			}
		}
	}
	
	//{"序号","球员名称","投篮命中","投篮出手","投篮%","罚球命中","罚球出手","罚球%","三分命中","三分出手","三分%","助攻","助攻率","真实命中率","投篮效率"};
	private void refreshOffensive() {
		
		DecimalFormat df = UIConfig.FORMAT;
		DecimalFormat percentDf = UIConfig.PERCENT_FORMAT;
		
		if (totalOrAvg == TotalOrAvg.AVG) {
			for (int i=0;i<vos.size();i++) {
				PlayerSeasonPO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(vo.getShortName(), i, 1);
				setValueAt(df.format(vo.fieldMadeAvg), i, 2);
				setValueAt(df.format(vo.fieldAttemptAvg), i, 3);
				setValueAt(percentDf.format(vo.fieldPercent), i, 4);
				setValueAt(df.format(vo.freethrowMadeAvg), i, 5);
				setValueAt(df.format(vo.freethrowAttemptAvg), i, 6);
				setValueAt(percentDf.format(vo.freethrowPercent), i, 7);
				setValueAt(df.format(vo.threePointMadeAvg), i, 8);
				setValueAt(df.format(vo.threePointAttemptAvg), i, 9);
				setValueAt(percentDf.format(vo.threePointPercent), i, 10);
				setValueAt(df.format(vo.assistAvg), i, 11);
				setValueAt(percentDf.format(vo.assistPercent), i, 12);
				setValueAt(percentDf.format(vo.realFieldPercent), i, 13);
				setValueAt(percentDf.format(vo.fieldEff), i, 14);
			}
		}else {
			for (int i=0;i<vos.size();i++) {
				PlayerSeasonPO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(vo.getShortName(), i, 1);
				setValueAt(vo.fieldMade, i, 2);
				setValueAt(vo.fieldAttempt, i, 3);
				setValueAt(percentDf.format(vo.fieldPercent), i, 4);
				setValueAt(vo.threePointMade, i, 5);
				setValueAt(vo.threePointAttempt, i, 6);
				setValueAt(percentDf.format(vo.threePointPercent), i, 7);
				setValueAt(vo.freethrowMade, i, 8);
				setValueAt(vo.freethrowAttempt, i, 9);
				setValueAt(percentDf.format(vo.freethrowPercent), i, 10);
				setValueAt(vo.assist, i, 11);
				setValueAt(percentDf.format(vo.assistPercent), i, 12);
				setValueAt(percentDf.format(vo.realFieldPercent), i, 13);
				setValueAt(percentDf.format(vo.fieldEff), i, 14);
			}
		}
	}
	
	//{"序号","球员名称","所属球队","参赛","先发","进攻篮板","防守篮板","总篮板","盖帽","抢断","进攻篮板率","防守篮板率","总篮板率","盖帽率","抢断率"};
	private void refreshDefensive() {
		
		DecimalFormat df = UIConfig.FORMAT;
		DecimalFormat percentDf = UIConfig.PERCENT_FORMAT;
		
		if (totalOrAvg == TotalOrAvg.AVG) {
			for (int i=0;i<vos.size();i++) {
				PlayerSeasonPO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(vo.getShortName(), i, 1);
				setValueAt(vo.matchCount, i, 3);
				setValueAt(df.format(vo.firstCountAvg), i, 4);
				setValueAt(df.format(vo.offensiveReboundAvg), i, 5);
				setValueAt(df.format(vo.defensiveReboundAvg), i, 6);
				setValueAt(df.format(vo.totalReboundAvg), i, 7);
				setValueAt(df.format(vo.blockAvg), i, 8);
				setValueAt(df.format(vo.stealAvg), i, 9);
				setValueAt(percentDf.format(vo.offensiveReboundPercent), i, 10);
				setValueAt(percentDf.format(vo.defensiveReboundPercent), i, 11);
				setValueAt(percentDf.format(vo.totalReboundPercent), i, 12);
				setValueAt(percentDf.format(vo.blockPercent), i, 13);
				setValueAt(percentDf.format(vo.stealPercent), i, 14);
			}
		}else {
			for (int i=0;i<vos.size();i++) {
				PlayerSeasonPO vo = vos.get(i);
				setValueAt(i + 1, i, 0);
				setValueAt(vo.getShortName(), i, 1);
				setValueAt(Constants.translateTeamAbbr(vo.teamAbbr), i, 2);
				setValueAt(vo.matchCount, i, 3);
				setValueAt(vo.firstCount, i, 4);
				setValueAt(vo.offensiveRebound, i, 5);
				setValueAt(vo.defensiveRebound, i, 6);
				setValueAt(vo.totalRebound, i, 7);
				setValueAt(vo.block, i, 8);
				setValueAt(vo.steal, i, 9);
				setValueAt(percentDf.format(vo.offensiveReboundPercent), i, 10);
				setValueAt(percentDf.format(vo.defensiveReboundPercent), i, 11);
				setValueAt(percentDf.format(vo.totalReboundPercent), i, 12);
				setValueAt(percentDf.format(vo.blockPercent), i, 13);
				setValueAt(percentDf.format(vo.stealPercent), i, 14);
			}
		}
	}
	
	private void addRowListener() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() < 2) return;
				int rowI  = AllPlayerSeasonTable.this.rowAtPoint(e.getPoint());
				if (rowI >= 0) {
					MainController.toPlayerInfoPanel
					(vos.get(rowI).name, (BottomPanel)(AllPlayerSeasonTable.this.getParent()));
				}
			}
		});
	}
}
