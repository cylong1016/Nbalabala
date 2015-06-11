package ui.panel.gamedata.live;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.UserMouseAdapter;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import utility.Constants;
import vo.LivePlayerVO;

/**
 * 直播界面的技术统计表格
 * @author lsy
 * @version 2015年6月12日  上午12:32:04
 */
public class LiveTechTable extends BottomTable{

	/** serialVersionUID */
	private static final long serialVersionUID = -2414449802167589112L;

	private ArrayList<LivePlayerVO> players;
	private BottomPanel panel;
	
	public LiveTechTable(ArrayList<LivePlayerVO> players,BottomPanel panel){
		super(new String[players.size()][Constants.livePlayerHeaders.length+1],Constants.livePlayerHeaders);
		this.players = players;
		this.panel = panel;
		setTable();
	}
	
	public void setTable(){
		
		for (int i = 0; i < players.size(); i++) {
			LivePlayerVO mpVO = players.get(i);
			setValueAt((i+1)+"",i,0);
//			setValueAt(Utility.trimName(mpVO.nameChn),i,1);
//			setValueAt(Utility.trimName(mpVO.nameEng),i,2);
			setValueAt(mpVO.nameChn,i,1);
			setValueAt(mpVO.nameEng,i,2);
			setValueAt(mpVO.position,i,3);
			if (mpVO.isStarter) {
				setValueAt("Y",i,4);
			}else {
				setValueAt("N",i,4);
			}
			setValueAt(mpVO.timePlayed,i,5);
			setValueAt(mpVO.fieldMade + "",i,6);
			setValueAt(mpVO.fieldAttempt + "",i,7);
			setValueAt(mpVO.threepointMade + "",i,8);
			setValueAt(mpVO.threepointAttempt + "",i,9);
			setValueAt(mpVO.freethrowMade + "",i,10);
			setValueAt(mpVO.freethrowAttempt+ "",i,11);
			setValueAt(mpVO.totalRebound + "",i,12);
			setValueAt(mpVO.assist + "",i,13);
			setValueAt(mpVO.steal + "",i,14);
			setValueAt(mpVO.block + "",i,15);
			setValueAt(mpVO.turnover + "",i,16);
			setValueAt(mpVO.foul + "",i,17);
			setValueAt(mpVO.score + "",i,18);
			setValueAt(mpVO.plusMinus + "",i,19);
		}
		addListener(this,players);
	}
	
	public void addListener(final BottomTable table,final ArrayList<LivePlayerVO> players) {
		try {
			table.addMouseListener(new UserMouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() < 2)
						return;
					int rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1) {
						MainController.toPlayerInfoPanel(players.get(rowI).nameChn, panel);
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
