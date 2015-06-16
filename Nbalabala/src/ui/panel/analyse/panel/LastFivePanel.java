package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.ClutchPO;
import ui.Images;
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
	private LastFiveButton[] button;
	private int bt_x = 31, bt_y = 16, inter_x = 100, width = 80, height = 30;
	private AnalysisBLService service = new ValueAnalysis();
	private ArrayList<ClutchPO> vo;
	
	public LastFivePanel(String name){
		this.name = name;
		vo = service.getClutchData("Kobe Bryant$01");
		System.out.println(vo.size());
//		for(int i = 0 ; i < vo.size() ; i ++) {
//			System.out.println("time"+vo.get(i).getClutchTime());
//		System.out.println("score"+vo.get(i).getClutchScore());
//		}
		if (vo == null) {
			//TODO
		} else {
			chart = new TwoDChart(vo);
			this.add(chart);
			button = new LastFiveButton[6];
			addButton();
			setEffect();
		}
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
	}
	
	public void addButton() {
		for (int i = 0; i < 6; i++) {
			button[i] = new LastFiveButton(bt_x + i * inter_x, bt_y, width, height, utility.Constants.ANY_SELECT[i]);
			this.add(button[i]);
		}
		LastFiveButton.current = button[0];
		addListener();
	}
	
	public void addListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == LastFiveButton.current) {
						return;
					}
					LastFiveButton.current.back();
					LastFiveButton.current = (LastFiveButton) e.getSource();
					LastFivePanel.this.remove(chart);
					vo = service.getClutchData(name);
					chart = new TwoDChart(vo);
					LastFivePanel.this.add(chart);
					LastFivePanel.this.repaint();
				}

			});
		}
	}

	public void setEffect() {
		button[0].setOpaque(true);
		button[0].setBackground(UIConfig.BUTTON_COLOR);
		button[0].setForeground(Color.white);
	}

}
