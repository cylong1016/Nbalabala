package ui.common.jfreechart;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import ui.MyFont;
import ui.UIConfig;
import ui.common.panel.Panel;
import utility.Constants;
import vo.AnalysisCompareVO;

/**
 * 球员对比界面的折线图
 * @author lsy
 * @version 2015年6月16日  下午9:16:55
 */
public class NewLineChart extends Panel{

	/** serialVersionUID */
	private static final long serialVersionUID = 5397535344571493107L;
	private AnalysisCompareVO vo;

	public NewLineChart(AnalysisCompareVO vo){
		if(vo == null) {
			return;
		}
		this.vo = vo;
//		formerData = vo.getFormerData();
//		currentData = vo.getCurrentData();
		JPanel panel = new ChartPanel(createLineChart());
		panel.setSize(UIConfig.CHAR_WIDTH, UIConfig.CHAR_HEIGHT); // 图表的大小 
		this.add(panel); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		this.setBounds(UIConfig.CHAR_X, UIConfig.CHAR_Y, 700, 400); // 图表的位置
		this.repaint();
	}
	
	public JFreeChart createLineChart() {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        int i = 0;
        
//        for(i = 0;i<formerData.size(); i++ ){
//        	dataset.addValue(formerData.get(i), Constants.translateTeamAbbr(vo.getFormerAbbr()), i+"");
//        }
//        
//        for(int j = 0;j<currentData.size(); j++ ){
//        	dataset.addValue(currentData.get(j), Constants.translateTeamAbbr(vo.getCurrentAbbr()), i+j+"");
//        }
        
        JFreeChart chart = ChartFactory.createLineChart(Constants.LINE_CHART[0], Constants.LINE_CHART[1],
        		Constants.LINE_CHART[2],dataset, PlotOrientation.VERTICAL, true, true, true);  
        
//        chart.getLegend().setPosition(RectangleEdge.RIGHT);  //  图例居右
        
        chart.setBackgroundPaint(UIConfig.CHAR_BG_COLOR); // 图表外的背景色
        chart.setBackgroundImageAlpha(0f);
        chart.getTitle().setFont( MyFont.YH_B); // 图表标题的字体
        
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
        lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        
        lineandshaperenderer.setDrawOutlines(true);  
        lineandshaperenderer.setUseFillPaint(true);  
        lineandshaperenderer.setBaseFillPaint(Color.white);
        
        // 设置折线加粗  
//        lineandshaperenderer.setSeriesStroke(1, new BasicStroke(3F));  
//        lineandshaperenderer.setSeriesOutlineStroke(1, new BasicStroke(2.0F));  
        // 设置折线拐点  
//        lineandshaperenderer.setSeriesShape(0,  
//                new java.awt.geom.Ellipse2D.Double(-5D, -5D, 10D, 10D)); 
        
//        categoryplot.setForegroundAlpha(0.5f);
        categoryplot.setRangeGridlinePaint(Color.gray);// 虚线色彩
        categoryplot.setDomainGridlinePaint(Color.gray);// 虚线色彩
        categoryplot.setBackgroundPaint(Color.white);//背景颜色
        
        CategoryAxis domainAxis = categoryplot.getDomainAxis();
        
        domainAxis.setLabelFont(UIConfig.FONT);// 轴标题
        domainAxis.setTickLabelFont(UIConfig.FONT);// 轴数值
        
        domainAxis.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色  
        domainAxis.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色  
        domainAxis.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色  
 
        return chart;
    }  
}
