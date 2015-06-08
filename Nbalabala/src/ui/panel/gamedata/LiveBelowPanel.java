package ui.panel.gamedata;

import java.awt.Image;

import ui.common.panel.BottomPanel;
import vo.MatchDetailVO;

/**
 * 文字直播下半部分的panel
 * @author lsy
 * @version 2015年6月8日  下午9:23:42
 */
public class LiveBelowPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 3575553054783466861L;
	private MatchDetailVO vo;
	
	public LiveBelowPanel(Image bg,MatchDetailVO vo){
		super(bg);
		this.vo = vo;
		this.setBounds(22, 292, 952, 309);
	}
}
