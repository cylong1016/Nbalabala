package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.AdvancedDataPO;
import ui.UIConfig;
import ui.common.jfreechart.BarChart;
import ui.common.panel.Panel;
import ui.panel.analyse.button.ContriButton;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;

/**
 * 球员贡献
 * @author lsy
 * @version 2015年6月8日  下午8:40:39
 */
public class ContriPanel extends Panel{

	/** serialVersionUID */
	private static final long serialVersionUID = -4897422799964523061L;
	private String teamName;
	private BarChart chart;
	private ContriButton[] button;
	private int bt_x = 20, bt_y = 20, inter_x = 120, width = 80, height = 25;
	private AnalysisBLService service = new ValueAnalysis();
	private ArrayList<AdvancedDataPO> po;
	private int playerIndex;
	
	public ContriPanel(String teamName,int playerIndex){
		this.teamName = teamName;
		this.playerIndex = playerIndex;
		po = service.getDevotionData(teamName);
		if (po == null) {
			//TODO 显示没有走向分析
		} else {
			button = new ContriButton[6];
			addButton();
			setEffect();
			chart = new BarChart(po,ContriButton.current.getPlayerCurrent(),playerIndex);
			this.add(chart);
		}
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
	}

	public void addButton() {
		for (int i = 0; i < 6; i++) {
			button[i] = new ContriButton(bt_x + i * inter_x, bt_y, width, height, utility.Constants.BAR_SELECT[i]);
			this.add(button[i]);
			button[i].setPlayerCurrent(i);
		}
		ContriButton.current = button[0];
		addListener();
	}
	
	public void addListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == ContriButton.current) {
						return;
					}
					ContriButton.current.back();
					ContriButton.current = (ContriButton) e.getSource();
					ContriPanel.this.remove(chart);
					po = service.getDevotionData(teamName);
					chart = new BarChart(po,ContriButton.current.getPlayerCurrent(),playerIndex);
					ContriPanel.this.add(chart);
					ContriPanel.this.repaint();
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
