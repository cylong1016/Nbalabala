package ui.panel.gamedata;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.MatchPlayerPO;
import ui.common.UserMouseAdapter;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import utility.Utility;

/**
 * 技术统计表格
 * @author lsy
 * @version 2015年6月8日  下午11:30:08
 */
public class TechTable extends BottomTable{

	/** serialVersionUID */
	private static final long serialVersionUID = 7610320773153941476L;
	private ArrayList<MatchPlayerPO> players;
	private BottomPanel panel;
	
	
	public TechTable(ArrayList<MatchPlayerPO> players,BottomPanel panel){
		super(new String[players.size()][Constants.matchPlayerHeaders.length+1],Constants.matchPlayerHeaders);
		this.players = players;
		this.panel = panel;
		setTable();
	}
	
	public void setTable(){
		
		// { "序号","球员名", "首发", "时间", "投篮", "三分", "罚球",
		// "进攻篮板", "防守篮板", "总篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "个人得分", "+/-" };
		for (int i = 0; i < players.size(); i++) {
			MatchPlayerPO mpVO = players.get(i);
			setValueAt((i+1)+"",i,0);
			setValueAt(Utility.trimName(mpVO.getPlayerName()),i,1);
			if (mpVO.isStarter()) {
				setValueAt("Y",i,2);
			}else {
				setValueAt("N",i,2);
			}
			setValueAt(mpVO.getTimePlayed(),i,3);
			setValueAt(mpVO.getFieldMade() + "/" + mpVO.getFieldAttempt(),i,4);
//			setValueAt(mpVO.getFieldAttempt() + "",i,5);
			setValueAt(mpVO.getThreepointMade() + "/" + mpVO.getThreepointAttempt(),i,5);
//			setValueAt(mpVO.getThreepointAttempt() + "",i,7);
			setValueAt(mpVO.getFreethrowMade() + "/" + mpVO.getFreethrowAttempt(),i,6);
//			setValueAt(mpVO.getFreethrowAttempt() + "",i,9);
			setValueAt(mpVO.getOffensiveRebound() + "",i,7);
			setValueAt(mpVO.getDefensiveRebound() + "",i,8);
			setValueAt(mpVO.getTotalRebound() + "",i,9);
			setValueAt(mpVO.getAssist() + "",i,10);
			setValueAt(mpVO.getSteal() + "",i,11);
			setValueAt(mpVO.getBlock() + "",i,12);
			setValueAt(mpVO.getTurnover() + "",i,13);
			setValueAt(mpVO.getFoul() + "",i,14);
			setValueAt(mpVO.getScore() + "",i,15);
			setValueAt(mpVO.getPlusMinus() + "",i,16);
		}
		addListener(this,players);
	}
	
	public void setLine(String teamName){
		for (int i = 0; i < players.size(); i++) {
			setValueAt(teamName,i,0);
		}
	}

	public void addListener(final BottomTable table,final ArrayList<MatchPlayerPO> players) {
		try {
			table.addMouseListener(new UserMouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(players.get(rowI).getPlayerName(), panel);
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
