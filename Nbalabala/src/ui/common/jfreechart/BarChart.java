package ui.common.jfreechart;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import ui.common.panel.Panel;

/**
 * 柱状图
 * @author lsy
 * @version 2015年6月2日 下午11:56:19
 */
public class BarChart extends Panel {
	/** serialVersionUID */
	private static final long serialVersionUID = -6096004426282934590L;
	private String[] name;
	private double[] num;
	private String[] color;

	public BarChart(String[] name, double[] num) {
		this.name = name;
		this.num = num;
		color = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k" };
		JFreeChart chart = createChart(createDataset());
		JPanel panel = new ChartPanel(chart);
		panel.setSize(800, 400);
		this.add(panel); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		this.repaint();
	}

	public CategoryDataset createDataset() // 创建柱状图数据集
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < name.length; i++) {
			dataset.setValue(num[i], color[i], name[i]);
		}
		return dataset;
	}

	public JFreeChart createChart(CategoryDataset dataset) // 用数据集创建一个图表
	{
		JFreeChart chart = ChartFactory.createBarChart("hi", "球员姓名", "球队贡献", dataset, PlotOrientation.VERTICAL,
				true, true, false); // 创建一个JFreeChart
		BarRenderer mRenderer = new BarRenderer();
		// mRenderer.setItemLabelGenerator(new
		// StandardCategoryItemLabelGenerator());
		// mRenderer.setItemLabelsVisible(true);
		mRenderer.setMaximumBarWidth(0.08);// 设置柱子宽度
		mRenderer.setMinimumBarLength(0.1);// 设置柱子高度
		mRenderer.setItemMargin(0.03);//组内柱子间隔为组宽的10%

		// chart.setTitle(new TextTitle("", new Font("宋体", Font.BOLD +
		// Font.ITALIC, 20)));// 可以重新设置标题，替换“hi”标题
		CategoryPlot plot = (CategoryPlot) chart.getPlot();// 获得图标中间部分，即plot
		CategoryAxis categoryAxis = plot.getDomainAxis();// 获得横坐标
		categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));// 设置横坐标字体
		// plot.getRangeAxis().setUpperMargin(0.1);//设置最高的一个柱与图片顶端的距离(最高柱的10%)

		CategoryAxis domainAxis = plot.getDomainAxis();

		// domainAxis.setLowerMargin(0.1);//设置距离图片左端距离此时为10%
		// domainAxis.setUpperMargin(0.1);//设置距离图片右端距离此时为百分之10
		// domainAxis.setCategoryLabelPositionOffset(10);//图表横轴与标签的距离(10像素)
		domainAxis.setCategoryMargin(0.2);// 横轴标签之间的距离20%
		plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);// 学校显示在下端(柱子竖直)或左侧(柱子水平)
		plot.setBackgroundAlpha((float) 0.1);// 设置透明度
		plot.setBackgroundPaint(Color.green);// 设置颜色
		// 设置网格竖线颜色
		plot.setDomainGridlinePaint(Color.blue);
		// plot.setDomainGridlinesVisible(true);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.blue);
		// plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		// //人数显示在下端(柱子水平)或左侧(柱子竖直)

		plot.setRenderer(mRenderer);
		return chart;
	}

	public JPanel createPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
	}
}
