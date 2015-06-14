package ui.common.jfreechart;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ui.common.panel.Panel;



/**
 * 散点图
 * @author lsy
 * @version 2015年6月9日  下午11:28:34
 */
public class ScatterChart extends Panel{
	/** serialVersionUID */
	private static final long serialVersionUID = 2633383144443658841L;
	
	private double[] x_num,y_num;
	
	public ScatterChart(double[] x_num,double[] y_num){ 
		this.x_num = x_num;
		this.y_num = y_num;
		JPanel panel = new ChartPanel(createChart());
		panel.setSize(800, 400);
		this.add(panel); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		this.repaint();
	}
	
	 private XYDataset samplexydataset2() {
		  int cols = 10;
		  int rows = 10;
		  double[][] values = new double[cols][rows];
		  XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		  XYSeries series = new XYSeries("Random");
		  Random rand = new Random();
		  for (int i = 0; i < values.length; i++) {
		   for (int j = 0; j < values[i].length; j++) {
		    double x = Math.round(rand.nextDouble() * 500);
		    double y = Math.round(rand.nextDouble() * 500);
		    series.add(x, y);
		   }
		  }
//		  for(int i = 0; i < x_num.length; i++) {
//	 			series.add(x_num[i],y_num[i]);
//		  }
		  xySeriesCollection.addSeries(series);
		  return xySeriesCollection;
		 }
	
	//生成图表对象 
	public JFreeChart createChart() { 
	    JFreeChart scatterChart = ChartFactory.createScatterPlot("散点图", "可爱吧", "哈哈哈", samplexydataset2(), PlotOrientation.VERTICAL, true, false, false);  
	    drawScatterChart(scatterChart, "", "");
	    return scatterChart; 
	} 
	 
	public void drawScatterChart(JFreeChart scatterChart, String title, String noDataMsg) { 
	    //title, legend, plot 三个部分设置字体的方法分别如下:   
	       TextTitle textTitle = scatterChart.getTitle();   
	       textTitle.setFont(new Font("宋体", Font.BOLD, 20));   
	       LegendTitle legend = scatterChart.getLegend();   
	       if (legend != null) 
	       {   
	           legend.setItemFont(new Font("宋体", Font.BOLD, 20)); 
	       } 
	       XYPlot xyplot = scatterChart.getXYPlot();   
	       xyplot.setNoDataMessage(noDataMsg);
	       xyplot.setNoDataMessagePaint(Color.blue);  
	       
	       xyplot.setDomainGridlinePaint(Color.lightGray);//设置垂直网格线的颜色   
	       xyplot.setRangeGridlinePaint(Color.lightGray);//设置水平网格线的颜色 
//	       xyplot.setDomainGridlinesVisible(true);//设置垂直网格线是否显示   
//	       xyplot.setRangeGridlinesVisible(true);//设置水平网格线是否显示
	       
	        xyplot.setBackgroundPaint(new Color(255, 253, 246));  
	        xyplot.setForegroundAlpha((float) 0.5);
	        ValueAxis vaaxis = xyplot.getDomainAxis();  
	        ValueAxis va = xyplot.getDomainAxis(0);  
	        	        
	        va.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色  
	        va.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色  
	        va.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色  
  
	} 
}
