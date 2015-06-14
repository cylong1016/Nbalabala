package ui.panel.hot.hotFast;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.chart.Chart;
import ui.common.chart.Column;
import ui.common.label.HotFastestPlayerLabel;
import ui.panel.hot.HotThreeFatherPanel;
import ui.panel.hot.ThreeButton;
import utility.Constants;
import utility.Utility;
import vo.HotFastestPlayerVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import enums.HotFastestPlayerProperty;

/**
 * 进步最快球员界面
 * @author lsy
 * @version 2015年4月11日 下午4:04:49
 */
public class HotFastPanel extends HotThreeFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2077718178589844072L;

	private ArrayList<HotFastestPlayerVO> fastVO;
	private static final HotFastestPlayerProperty[] HOT_FAST_ARRAY = HotFastestPlayerProperty.values();
	private HotBLService hot = new HotQuery();
	private HotFastestPlayerLabel label[] = new HotFastestPlayerLabel[5];
	private Chart chart;
	private ImgButton left, right;
	private String url = "images2.0/hot/";
	/** 当前的球员 */
	private  static int CURRENTI = 0;

	public HotFastPanel(String url) {
		super(url);
		addlrButton();
		add_bt_Listener();
		fastVO = hot.getHotFastestPlayers(ThreeButton.current.fast);
		addLabel();
		addChart(ThreeButton.current.index);
		repaint();
		
		setCurrent(3);
	}

	public void refresh() {
		// 重新获取数据
		fastVO = hot.getHotFastestPlayers(ThreeButton.current.fast);
		updateLabel();
		updateChart();
		repaint();
	}

	public void addlrButton() {
		left = new ImgButton(url+"left.png", 110, 194, url+"leftOn.png", url+"left.png");
		right = new ImgButton(url+"right.png", 855, 194, url+"rightOn.png", url+"right.png");
		this.add(left);
		this.add(right);
		left.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				CURRENTI--;
				addChart(ThreeButton.current.index);
				HotFastPanel.this.repaint();
			}
		});
		right.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				CURRENTI++;
				addChart(ThreeButton.current.index);
				HotFastPanel.this.repaint();
			}
		});
	}

	private void addChart(int index) {
		if (chart != null) {
			this.remove(chart);
		}

		if (CURRENTI == 5) {
			CURRENTI = 0;
		} else if (CURRENTI == -1) {
			CURRENTI = 4;
		}
		if (fastVO.size() < 5)
			return;
		chart = new Chart((CURRENTI + 1) + " " + Utility.trimName(fastVO.get(CURRENTI).getName()) + " " + text, getColumns(index), getMax());
		chart.setBounds(95, 103, 809, 200);
		this.add(chart);
		chart.updateUI();
		chart.repaint();
	}

	/**
	 * 更新柱状图数据
	 * @author cylong
	 * @version 2015年4月13日 下午8:18:32
	 */
	public void updateChart() {
		if (fastVO.size() < 5)
			return;
		if (chart != null) {
			chart.setData(getColumns(ThreeButton.current.index), getMax());
			chart.setTitle((CURRENTI + 1) + " " +Utility.trimName(fastVO.get(CURRENTI).getName()) + " " + text);
		}else {
			addChart(ThreeButton.current.index);
		}
	}

	/**
	 * 获得柱状图全部列
	 * @author cylong
	 * @version 2015年4月13日 下午8:48:00
	 */
	private ArrayList<Column> getColumns(int index) {
		ArrayList<Column> columns = new ArrayList<Column>();
		double formerFiveAvg = fastVO.get(CURRENTI).getFormerFiveAvg();
		if(index > 4){
			columns.add(new Column(Constants.formerFiveAvgText, formerFiveAvg, Color.GRAY,UIConfig.PERCENT_FORMAT));
		} else{
			columns.add(new Column(Constants.formerFiveAvgText, formerFiveAvg, Color.GRAY,UIConfig.FORMAT));
		}
		for(int i = 0; i < 5; i++) {
			double[] recentFive = fastVO.get(CURRENTI).getRecentFive();
			if(index > 4){
				columns.add(new Column(String.valueOf(i + 1), recentFive[i], UIConfig.HIST_COLORS[CURRENTI]
						,UIConfig.PERCENT_FORMAT));
			}else{
				columns.add(new Column(String.valueOf(i + 1), recentFive[i], UIConfig.HIST_COLORS[CURRENTI]
						,UIConfig.FORMAT));
			}
		}
		return columns;
	}

	/**
	 * @return 柱状图数据的最大值
	 * @author cylong
	 * @version 2015年4月13日 下午8:49:48
	 */
	private double getMax() {
		double max = fastVO.get(CURRENTI).getFormerFiveAvg();
		double promotion = max;
		for(int i = 0; i < 5; i++) {
			double[] recentFive = fastVO.get(CURRENTI).getRecentFive();
			promotion = recentFive[i];
			if (max < promotion) {
				max = promotion;
			}
		}
		return max;
	}

	/**
	 * 添加监听
	 * @author lsy
	 * @version 2015年4月11日 下午6:09:11
	 */
	public void add_bt_Listener() {
		for(int i = 0; i < select.length; i++) {
			hotButton[i].fast = HOT_FAST_ARRAY[i];
			hotButton[i].addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					for(int i = 0; i < label.length; i++) {
						if (label[i] != null)
							HotFastPanel.this.remove(label[i]);
					}
					addLabel();
					addChart(ThreeButton.current.index);
					refresh();
				}

			});
		}
	}

	/**
	 * 添加每个球员显示的label
	 * @author lsy
	 * @version 2015年4月11日 下午6:09:19
	 */
	public void addLabel() {
		if (fastVO.size() < 5)
			return;
		for(int j = 0; j < 5; j++) {
			label[j] = new HotFastestPlayerLabel(fastVO.get(j));
			this.add(label[j]);
		}
		this.repaint();
	}
	
	/**
	 * 为了解决重影问题，不再add label而是改变之
	 * @author Issac Ding
	 * @version 2015年4月14日  下午12:17:43
	 */
	public void updateLabel() {
		if (fastVO.size() < 5)
			return;
		for(int j = 0; j < 5; j++) {
			if (label[j] != null)
				label[j].updateContent(fastVO.get(j));
			else{
				label[j] = new HotFastestPlayerLabel(fastVO.get(j));
				this.add(label[j]);
			}
		}
		this.repaint();
	}
}
