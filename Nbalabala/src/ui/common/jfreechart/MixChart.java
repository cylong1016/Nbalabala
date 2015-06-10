package ui.common.jfreechart;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import ui.UIConfig;
import ui.common.panel.Panel;

/**
 * 混合图
 * 
 * @author lsy
 * @version 2015年6月10日 上午1:00:23
 */
public class MixChart extends Panel{

	/** serialVersionUID */
	private static final long serialVersionUID = -3854750644985796964L;

	public MixChart() {
		JPanel panel = new ChartPanel(createChart());
		panel.setSize(800, 400);
		this.add(panel); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		this.repaint();
	}

	public CategoryDataset createDataset1() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		String series1 = "First";
		String series2 = "Second";
		String type1 = "Type 1";
		String type2 = "Type 2";
		String type3 = "Type 3";
		String type4 = "Type 4";
		String type5 = "Type 5";
		String type6 = "Type 6";
		String type7 = "Type 7";
		String type8 = "Type 8";

		result.addValue(1.0, series1, type1);
		result.addValue(4.0, series1, type2);
		result.addValue(3.0, series1, type3);
		result.addValue(5.0, series1, type4);
		result.addValue(5.0, series1, type5);
		result.addValue(7.0, series1, type6);
		result.addValue(7.0, series1, type7);
		result.addValue(8.0, series1, type8);

		result.addValue(5.0, series2, type1);
		result.addValue(7.0, series2, type2);
		result.addValue(6.0, series2, type3);
		result.addValue(8.0, series2, type4);
		result.addValue(4.0, series2, type5);
		result.addValue(4.0, series2, type6);
		result.addValue(2.0, series2, type7);
		result.addValue(1.0, series2, type8);

		return result;
	}

	public CategoryDataset createDataset2() {

		DefaultCategoryDataset result = new DefaultCategoryDataset();

		String series1 = "Third";
		String series2 = "Fourth";

		String type1 = "Type 1";
		String type2 = "Type 2";
		String type3 = "Type 3";
		String type4 = "Type 4";
		String type5 = "Type 5";
		String type6 = "Type 6";
		String type7 = "Type 7";
		String type8 = "Type 8";

		result.addValue(11.0, series1, type1);
		result.addValue(14.0, series1, type2);
		result.addValue(13.0, series1, type3);
		result.addValue(15.0, series1, type4);
		result.addValue(15.0, series1, type5);
		result.addValue(17.0, series1, type6);
		result.addValue(17.0, series1, type7);
		result.addValue(18.0, series1, type8);

		result.addValue(15.0, series2, type1);
		result.addValue(17.0, series2, type2);
		result.addValue(16.0, series2, type3);
		result.addValue(18.0, series2, type4);
		result.addValue(14.0, series2, type5);
		result.addValue(14.0, series2, type6);
		result.addValue(12.0, series2, type7);
		result.addValue(11.0, series2, type8);

		return result;
	}
	
	public JFreeChart createChart() { 
		JFreeChart result = new JFreeChart(
	            "title", UIConfig.FONT, getPlot(), true
	        );
	    return result; 
	} 

	public Plot getPlot(){
		//创建数据源一的纵坐标对象。
		NumberAxis rangeAxis1 = new NumberAxis("Value");
		LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
		//我们在这里将横坐标设置为NULL，表示不使用自己的横坐标对象，只使用自己的纵坐标和图表渲染对象（折线图）
		CategoryPlot subplot1 = new CategoryPlot(createDataset1(), null, rangeAxis1, 
		                renderer1);
		 
		//纵坐标对象。
		NumberAxis rangeAxis2 = new NumberAxis("Value");
		//柱状图的图表渲染对象。
		BarRenderer renderer2 = new BarRenderer();
		//同样的不是用横坐标对象。
		CategoryPlot subplot2 = new CategoryPlot(createDataset2(), null, rangeAxis2, 
		                renderer2);
		CategoryAxis domainAxis = new CategoryAxis("Category");
		//先合并横坐标。
		CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(
		                domainAxis);
		//添加纵坐标。
		        plot.add(subplot1, 2);
		//添加纵坐标。
		        plot.add(subplot2, 1);
		        return plot;
	}
}
