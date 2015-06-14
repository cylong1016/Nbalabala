package ui.common.jfreechart;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import ui.UIConfig;
import ui.common.panel.Panel;

/**
 * 折线图
 * @author lsy
 * @version 2015年6月9日  下午11:31:09
 */
public class LineChart extends Panel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -238536006047910842L;
	private String[] name, team;
	private double[] num;
	
	public LineChart(String[] name,String[] team,double[] num){
		this.name = name;
		this.team = team;
		this.num = num;
		JPanel panel = new ChartPanel(createLineChart());
		panel.setSize(800, 400);
		this.add(panel); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		this.repaint();
	}
	
	public JFreeChart createLineChart() {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        
//        for(int i = 0;i<name.length; i++ ){
//        	dataset.addValue(num[i], team[i], name[i]);
//        }
        
        dataset.addValue(100, "北京", "苹果");  
        dataset.addValue(200, "上海", "苹果");  
        dataset.addValue(300, "广州", "苹果");  
        dataset.addValue(400, "北京", "梨子");  
        dataset.addValue(500, "上海", "梨子");  
        dataset.addValue(600, "广州", "梨子");  
        dataset.addValue(700, "北京", "葡萄");  
        dataset.addValue(800, "上海", "葡萄");  
        dataset.addValue(900, "广州", "葡萄");  
        dataset.addValue(800, "北京", "香蕉");  
        dataset.addValue(700, "上海", "香蕉");  
        dataset.addValue(600, "广州", "香蕉");  
        dataset.addValue(500, "北京", "荔枝");  
        dataset.addValue(400, "上海", "荔枝");  
        dataset.addValue(300, "广州", "荔枝");  
        JFreeChart chart = ChartFactory.createLineChart("水果产量图 ", "水果", "产量",  
                dataset, PlotOrientation.VERTICAL, true, true, true);  
        
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
        lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
 
        categoryplot.setRangeGridlinePaint(Color.BLUE);// 虚线色彩
        categoryplot.setDomainGridlinePaint(Color.blue);// 虚线色彩
        categoryplot.setBackgroundPaint(Color.lightGray);
        CategoryAxis domainAxis = categoryplot.getDomainAxis();
        
        domainAxis.setLabelFont(UIConfig.FONT);// 轴标题
 
        domainAxis.setTickLabelFont(UIConfig.FONT);// 轴数值
 
        return chart;
    }  
	
}
