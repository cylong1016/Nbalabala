package ui.common.jfreechart;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * 将图表转换成jpg格式的图
 * @author lsy
 * @version 2015年6月2日  下午11:58:42
 */
public class ChartToJPG {
	public static void main(String[] args) 
	{ 
	PieDataset dataset = getDataSet(); 
	JFreeChart chart = ChartFactory.createPieChart3D( 
	" 项目进度分布", // chart title 
	dataset,// data 
	true,// include legend 
	true, 
	false 
	); 
	PiePlot3D plot=(PiePlot3D)chart.getPlot(); 
	// 图片中显示百分比:默认方式 
	//plot.setLabelGenerator(new StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT)); 
	// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位 
	plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%"))); 
	// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例 
	plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})")); 
	// 设置背景色为白色 
	chart.setBackgroundPaint(Color.white); 
	// 指定图片的透明度(0.0-1.0) 
	plot.setForegroundAlpha(1.0f); 
	// 指定显示的饼图上圆形(false)还椭圆形(true) 
	plot.setCircular(true); 
	// 设置图标题的字体 
	Font font = new Font(" 黑体",Font.CENTER_BASELINE,20); 
	TextTitle title = new TextTitle(" 项目状态分布"); 
	title.setFont(font); 
	chart.setTitle(title); 
	FileOutputStream fos_jpg = null; 
	try { 
	fos_jpg=new FileOutputStream("D:\\ 项目状态分布.jpg"); 
	ChartUtilities.writeChartAsJPEG(fos_jpg,100,chart,640,480,null); 
	fos_jpg.close(); 
	} catch (Exception e) { 
	} 
	} 
	private static PieDataset getDataSet() { 
	DefaultPieDataset dataset = new DefaultPieDataset(); 
	dataset.setValue(" 市场前期", new Double(10)); 
	dataset.setValue(" 立项", new Double(15)); 
	dataset.setValue(" 计划", new Double(10)); 
	dataset.setValue(" 需求与设计", new Double(10)); 
	dataset.setValue(" 执行控制", new Double(35)); 
	dataset.setValue(" 收尾", new Double(10)); 
	dataset.setValue(" 运维",new Double(10)); 
	return dataset; 
	} 

}
