package ui.panel.gamedata.live;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.common.UserMouseAdapter;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import utility.Utility;
import vo.LivePlayerVO;

/**
 * 直播界面的技术统计表格
 * @author lsy
 * @version 2015年6月12日  上午12:32:04
 */
public class LiveTechTable extends BottomTable{

	/** serialVersionUID */
	private static final long serialVersionUID = -2414449802167589112L;

	private BottomPanel panel;
	private ArrayList<LivePlayerVO> players;
	
	public LiveTechTable(ArrayList<LivePlayerVO> players,BottomPanel panel){
		super(new String[players.size()][Constants.livePlayerHeaders.length],Constants.livePlayerHeaders);
		this.panel = panel;
		this.players = players;
		setTable(players);
		addListener(this);
	}
	
	public void setTable(ArrayList<LivePlayerVO> players){
		this.players = players;
		this.setModel(new DefaultTableModel(players.size(),Constants.livePlayerHeaders.length));
		DefaultTableModel tableModel = (DefaultTableModel)this.getModel();
		tableModel.setColumnIdentifiers(Constants.livePlayerHeaders);
		setTableSize();
		for (int i = 0; i < players.size(); i++) {
			LivePlayerVO mpVO = players.get(i);
			setValueAt((i+1)+"",i,0);
			setValueAt(mpVO.nameChn,i,1);
//			setValueAt(mpVO.nameEng,i,2);
			setValueAt(mpVO.position,i,2);
			if (mpVO.isStarter) {
				setValueAt("Y",i,3);
			}else {
				setValueAt("N",i,3);
			}
			setValueAt(mpVO.timePlayed,i,4);
			setValueAt(mpVO.fieldMade + "/" + mpVO.fieldAttempt,i,5);
//			setValueAt(mpVO.fieldAttempt + "",i,7);
			setValueAt(mpVO.threepointMade + "/" + mpVO.threepointAttempt,i,6);
//			setValueAt(mpVO.threepointAttempt + "",i,9);
			setValueAt(mpVO.freethrowMade + "/" + mpVO.freethrowAttempt,i,7);
//			setValueAt(mpVO.freethrowAttempt+ "",i,11);
			setValueAt(mpVO.totalRebound + "",i,8);
			setValueAt(mpVO.assist + "",i,9);
			setValueAt(mpVO.steal + "",i,10);
			setValueAt(mpVO.block + "",i,11);
			setValueAt(mpVO.turnover + "",i,12);
			setValueAt(mpVO.foul + "",i,13);
			setValueAt(mpVO.score + "",i,14);
			setValueAt(mpVO.plusMinus + "",i,15);
		}
	}
	
	private void setTableSize() {
//		table.setWidth(new int[] {33, 130, 51, 51, 62, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51});
		for (int i = 0; i < 16; i++) {
			this.getColumnModel().getColumn(i).setPreferredWidth(51);
		}
		this.getColumnModel().getColumn(0).setPreferredWidth(33);
		this.getColumnModel().getColumn(1).setPreferredWidth(130);
		this.getColumnModel().getColumn(4).setPreferredWidth(62);
		
		
	}

	public void addListener(final BottomTable table) {
		try {
			table.addMouseListener(new UserMouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(Utility.getPlayerIDByName(players.get(rowI).nameEng), panel);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
