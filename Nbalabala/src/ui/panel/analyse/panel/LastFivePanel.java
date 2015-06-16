package ui.panel.analyse.panel;

import java.util.ArrayList;

import po.ClutchPO;
import ui.common.chart.TwoDChart;
import ui.common.panel.Panel;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;

/**
 * 最后五分钟底下的界面
 * @author lsy
 * @version 2015年6月8日  下午8:34:49
 */
public class LastFivePanel extends Panel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5007878982571604781L;
	private TwoDChart chart;
	private AnalysisBLService service = new ValueAnalysis();
	private ArrayList<ClutchPO> vo;
	
	public LastFivePanel(String name){
		vo = service.getClutchData(name);
		if (vo == null) {
			//TODO
		} else {
			chart = new TwoDChart(vo);
			chart.setBounds(100, 70, 700, 400);
			this.add(chart);
		}
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
	}
}
