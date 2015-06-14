package ui.panel.gamedata.live;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.common.table.BottomTable;
import utility.Constants;

/**
 * 文字直播详情表格
 * 
 * @author lsy
 * @version 2015年6月12日 上午2:22:41
 */
public class LiveDetailTable extends BottomTable {

	/** serialVersionUID */
	private static final long serialVersionUID = 3368275554423060712L;

	private String teamAbbr1;

	public LiveDetailTable(String teamAbbr1, String teamAbbr2, ArrayList<String> text) {
		super(new String[text.size()][4], new String[] { Constants.liveDetailHeaders[0],
				Constants.translateTeamAbbr(teamAbbr1), Constants.liveDetailHeaders[1],
				Constants.translateTeamAbbr(teamAbbr2) });
		this.teamAbbr1 = teamAbbr1;
		setTable(text);
		setTableSize();
	}
	
	public void setTableSize() {
		for(int i = 0; i<4; i = i+2) {
			this.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		this.getColumnModel().getColumn(1).setPreferredWidth(300);
		this.getColumnModel().getColumn(3).setPreferredWidth(300);
		
	}

	public void setTable(ArrayList<String> text) {
		this.setModel(new DefaultTableModel(text.size(), 4));
		setTableSize();
		for (int i = 0; i < text.size(); i++) {
			String mpVO = text.get(i);
			String[] textArr = mpVO.split(";");
			if(textArr.length > 1){
				setValueAt(textArr[0], i, 0);
				if(textArr[1].equals(Constants.translateTeamAbbr(teamAbbr1))){
					setValueAt(textArr[2], i, 1);
				}else{
					setValueAt(textArr[2], i, 3);
				}
				setValueAt(textArr[3], i, 2);
			} else {
				setValueAt(textArr[0],i,2);
			}
		}
	}
}
