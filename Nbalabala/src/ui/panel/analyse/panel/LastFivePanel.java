package ui.panel.analyse.panel;

import java.util.ArrayList;

import po.ClutchPO;
import ui.common.chart.TwoDChart;
import ui.common.panel.Panel;
import utility.Utility;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;

/**
 * 决胜时刻底下的界面
 * @author lsy
 * @version 2015年6月8日  下午8:34:49
 */
public class LastFivePanel extends Panel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5007878982571604781L;
	private TwoDChart chart;
	private AnalysisBLService service = new ValueAnalysis();
	private ArrayList<ClutchPO> pos;
	
//	private Image bgImg = ;
	
	public LastFivePanel(String teamName,String playerName){
		pos = service.getClutchData(teamName);
		for(ClutchPO po : pos) {
			po.name = Utility.trimName(po.name);
		}
		if (pos == null) {
			// TODO
		} else {
			chart = new TwoDChart(pos,Utility.trimName(playerName));
			chart.setBounds(50, 40, 900, 420);
			this.add(chart);
		}
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
	}
}
