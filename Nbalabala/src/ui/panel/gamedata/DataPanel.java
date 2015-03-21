package ui.panel.gamedata;

import java.awt.Dimension;

import ui.common.panel.Panel;

/**
 * 比赛简况带有滚动条的panel
 * 
 * @author lsy
 * @version 2015年3月21日 下午8:03:12
 */
public class DataPanel extends Panel {
	/** serialVersionUID */
	private static final long serialVersionUID = -5211868747379960305L;
	
	public DataPanel(int x,int y,int width,int height) {
		this.setLocation(x, y);
		this.setPreferredSize(new Dimension(width, height));
	}
}
