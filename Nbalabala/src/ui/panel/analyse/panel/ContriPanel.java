package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.AdvancedDataPO;
import ui.Images;
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
	private int bt_x = 31, bt_y = 16, inter_x = 100, width = 80, height = 30;
	private AnalysisBLService service = new ValueAnalysis();
	private ArrayList<AdvancedDataPO> po;
	private int playerIndex;
	
	private Image bgImg = Images.BG_IMAGE;
	
	public ContriPanel(String teamName,int playerIndex){
		this.teamName = teamName;
		this.playerIndex = playerIndex;
		po = service.getDevotionData(teamName);
		if (po == null) {
			bgImg = Images.NO_DATA;
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
					ContriPanel.this.getParent().repaint();
				}

			});
		}
	}

	public void setEffect() {
		button[0].setOpaque(true);
		button[0].setBackground(UIConfig.BUTTON_COLOR);
		button[0].setForeground(Color.white);
	}
	
	public void paint(Graphics g) {
		g.drawImage(bgImg, 0, 0, this);
		// g2d.drawImage(slider, 0, 0, this);
		super.paint(g);
	}

	
}
