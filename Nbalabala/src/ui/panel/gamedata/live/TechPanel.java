package ui.panel.gamedata.live;

import java.util.ArrayList;

import po.MatchPlayerPO;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import ui.panel.gamedata.TechTable;
import vo.LivePlayerVO;

/**
 * 技术统计界面
 * @author lsy
 * @version 2015年6月9日  上午12:04:04
 */
public class TechPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5091667442846836452L;
	private TechTable techTable;
	private ArrayList<LivePlayerVO> players;
	private BottomPanel panel;
	private LiveTechTable table;
	
	public TechPanel(ArrayList<LivePlayerVO> homeplayers,ArrayList<LivePlayerVO> roadplayers,BottomPanel panel){
		super("images2.0/games/techBG.png");
		this.setBounds(22, 292, 952, 309);
		this.players = homeplayers;
		this.panel = panel;
		players.addAll(roadplayers);
		table = new LiveTechTable(players,panel);
		setTable(homeplayers);
	}

	private BottomScrollPane scroll;

	public void setTable(ArrayList<LivePlayerVO> players) {
		scroll = new BottomScrollPane(table);
		scroll.setBounds(5,5,940,280);
		this.add(scroll);
	}
	
	public void updateTable(ArrayList<LivePlayerVO> players){
		table.setTable(players);
	}
	
}
