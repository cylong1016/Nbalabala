package ui.panel.gamedata;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.common.table.BottomTable;
import utility.Constants;

/**
 * 赛程界面的直播表格
 * @author lsy
 * @version 2015年6月16日  下午7:28:50
 */
public class GameLiveTable extends BottomTable{

	/** serialVersionUID */
	private static final long serialVersionUID = -8218510168004184367L;
	private String teamAbbr1;
	private String teamAbbr2;
	
	public GameLiveTable(String teamAbbr1, String teamAbbr2, ArrayList<String> text) {
		super(new String[text.size()][4], new String[] { Constants.liveDetailHeaders[0],
				Constants.translateTeamAbbr(teamAbbr1), Constants.liveDetailHeaders[1],
				Constants.translateTeamAbbr(teamAbbr2) });
		this.teamAbbr1 = teamAbbr1;
		this.teamAbbr2 = teamAbbr2;
		setTable(text);
		setTableSize();
	}

	public void setTableSize() {
		this.columnModel.getColumn(0).setPreferredWidth(60);
		this.getColumnModel().getColumn(1).setPreferredWidth(300);
		this.getColumnModel().getColumn(2).setPreferredWidth(90);
		this.getColumnModel().getColumn(3).setPreferredWidth(315);
		
	}

	public void setTable(ArrayList<String> text) {
		this.setModel(new DefaultTableModel(text.size(), 4));
		DefaultTableModel tableModel = (DefaultTableModel)(this.getModel());
		tableModel.setColumnIdentifiers(new String[] { Constants.liveDetailHeaders[0],
				Constants.translateTeamAbbr(teamAbbr1), Constants.liveDetailHeaders[1],
				Constants.translateTeamAbbr(teamAbbr2) });
		setTableSize();
		for (int i = 0; i < text.size(); i++) {
			String mpVO = text.get(i);
			String[] textArr = mpVO.split(";");
			if(textArr.length > 1){
				setValueAt(textArr[0], i, 0);
//				if(textArr[1].equals(Constants.translateTeamAbbr(teamAbbr1))){
					setValueAt(textArr[1], i, 1);
//				}else{
					setValueAt(textArr[2], i, 2);
//				}
				setValueAt(textArr[3], i, 3);
			} else {
				setValueAt(textArr[0], i, 2);
			}
		}
	}
	
}
