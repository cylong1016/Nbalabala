package ui.panel.analyse;

import java.awt.Color;

import enums.InferenceData;
import ui.common.jfreechart.LineChart;
import ui.common.panel.Panel;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;

/**
 * 转会情况
 * @author lsy
 * @version 2015年6月8日  下午8:42:45
 */
public class TurnPanel extends Panel{

	/** serialVersionUID */
	private static final long serialVersionUID = 97048605908825431L;
	private AnalysisBLService service;
	private LineChart chart;
	
	public TurnPanel(){
		this.setBounds(41, 146,900, 374);
		chart = new LineChart("LeBron James$01",InferenceData.ASSIST);
		chart.setBounds(0,0,800,400);
		this.add(chart);
		this.repaint();
	}

}
