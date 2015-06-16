package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.ClutchPO;
import ui.UIConfig;
import ui.common.chart.TwoDChart;
import ui.common.panel.Panel;
import ui.panel.analyse.button.LastFiveButton;
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
	private String name;
	private TwoDChart chart;
	private int bt_x = 31, bt_y = 16, inter_x = 100, width = 80, height = 30;
	private AnalysisBLService service = new ValueAnalysis();
	private ArrayList<ClutchPO> vo;
	
	public LastFivePanel(String name){
		this.name = name;
		vo = service.getClutchData(name);
		if (vo == null) {
			//TODO
		} else {
			chart = new TwoDChart(vo);
			chart.setBounds(130, 90, 700, 400);
			this.add(chart);
		}
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
	}
}
