package ui.panel.hot.hotFast;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.chart.Chart;
import ui.common.chart.Column;
import ui.common.label.HotFastestPlayerLabel;
import ui.panel.hot.HotThreeFatherPanel;
import ui.panel.hot.ThreeButton;
import vo.HotFastestPlayerVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import enums.HotFastestPlayerProperty;

/**
 * 进步最快球员界面
 * @author lsy
 * @version 2015年4月11日  下午4:04:49
 */
public class HotFastPanel extends HotThreeFatherPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 2077718178589844072L;

	ArrayList<HotFastestPlayerVO> fastVO;
	private static final HotFastestPlayerProperty[] HOT_FAST_ARRAY = HotFastestPlayerProperty.values();
	HotBLService hot = new HotQuery();
	HotFastestPlayerLabel label[] = new HotFastestPlayerLabel[5];
	Chart chart;
	
	public HotFastPanel(String url) {
		super(url);
		add_bt_Listener();
		addLabel();
		addChart();
	}
	
	

	private void addChart() {
		if(chart!=null){
			this.remove(chart);
		}
		if (fastVO.size() < 5) return;
		ArrayList<Column> columns = new ArrayList<Column>();
		double max = fastVO.get(0).getPromotion();
		double promotion = max;
		for (int i = 0; i < 5; i++) {
			promotion = fastVO.get(i).getPromotion();
			columns.add(new Column(fastVO.get(i).getName(), promotion, UIConfig.HIST_COLORS[i]));
			if (max < promotion) {
				max = promotion;
			}
		}
		chart = new Chart(text, columns, max);
		chart.setBounds(95, 103, 809, 145);
		this.add(chart);
		chart.updateUI();
		chart.repaint();
	}
	
	/**
	 * 添加监听
	 * @author lsy
	 * @version 2015年4月11日  下午6:09:11
	 */
	public void add_bt_Listener() {
		for (int i = 0; i < select.length; i++) {
			if (label[i] == null) return;
			hotButton[i].fast = HOT_FAST_ARRAY[i];
			hotButton[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int i = 0;i<label.length;i++){
						HotFastPanel.this.remove(label[i]);
					}
					addLabel();
					addChart();
				}

			});
		}
	}

	/**
	 * 添加每个球员显示的label
	 * @author lsy
	 * @version 2015年4月11日  下午6:09:19
	 */
	public void addLabel() {
		fastVO = hot.getHotFastestPlayers(ThreeButton.current.fast);
		if (fastVO.size() < 5) return;
		for (int j = 0; j < 5; j++) {
			label[j] = new HotFastestPlayerLabel(fastVO.get(j));
			this.add(label[j]);
		}
		this.repaint();
	}

}
