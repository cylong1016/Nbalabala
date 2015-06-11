package ui.panel.gamedata.live;

import java.util.ArrayList;

import ui.common.panel.BottomPanel;
import ui.common.table.BottomTable;
import utility.Constants;
import vo.LivePlayerVO;

/**
 * 文字直播详情表格
 * 
 * @author lsy
 * @version 2015年6月12日 上午2:22:41
 */
public class LiveDetailTable extends BottomTable {

	/** serialVersionUID */
	private static final long serialVersionUID = 3368275554423060712L;

	private ArrayList<String> text;
	private BottomPanel panel;

	public LiveDetailTable(ArrayList<String> text, BottomPanel panel) {
		super(new String[text.size()][Constants.liveDetailHeaders.length + 1], Constants.liveDetailHeaders);
		this.text = text;
		this.panel = panel;
		setTable();
	}

	public void setTable() {

		for (int i = 0; i < text.size(); i++) {
			String mpVO = text.get(i);
			String[] textArr = mpVO.split(";");
			for (int j = 0; j < textArr.length; j++) {
				setValueAt(textArr[j], i, j);
			}

		}
	}
}
