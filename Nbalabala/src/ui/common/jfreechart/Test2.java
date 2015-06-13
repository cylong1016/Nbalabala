/**
 * 
 */
package ui.common.jfreechart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYBoxAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Log;

public class Test2 extends ApplicationFrame {
	/**
	 * 
	 * @param xydatalist
	 * @param bloods
	 * @return
	 */
	public static XYDataset createxydataset(List<PressureBean> xydatalist,
			String bloods) {
		DefaultXYDataset xydataset = new DefaultXYDataset();

		int size = xydatalist.size();
		double[][] datas = new double[2][size];
		for (int i = 0; i < size; i++) {
			PressureBean pres = xydatalist.get(i);
			int sys = pres.getSyspress();// 收缩压
			int dia = pres.getDiapress();// 舒张压

			datas[0][i] = sys;
			datas[1][i] = dia;
		}

		xydataset.addSeries(bloods, datas);

		return xydataset;

	}

	public static JFreeChart createChart(XYDataset xydataset,
			String bloodcattile, String shou, String shu, String nobloodData,
			String bloods, String nomal, String fore, String one, String two,
			List<PressureBean> list, Log log) {

		// 有可能用户在后面的版本中故意输入不正常数值，但是为了保证图片画图的完整，这里先计算
		// 用户血压值的最大值。
		int maxpress = 160;
		int addmax = 20;

		if (list != null && list.size() > 0) {

			Iterator<PressureBean> it = list.iterator();
			while (it.hasNext()) {
				PressureBean pres = it.next();

				if (maxpress < pres.getDiapress()) {
					maxpress = pres.getDiapress();
				}

				if (maxpress < pres.getSyspress()) {
					maxpress = pres.getSyspress();
				}
			}

			maxpress += addmax;

			log.info("high press value is =" + maxpress);

		}

		JFreeChart jfreechart = ChartFactory.createScatterPlot(bloodcattile,
				shou, shu, xydataset, PlotOrientation.VERTICAL, true, false,
				false);
		jfreechart.setBackgroundPaint(Color.white);
		jfreechart.setBorderPaint(Color.GREEN);
		jfreechart.setBorderStroke(new BasicStroke(1.5f));
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		xyplot.setNoDataMessage(nobloodData);
		xyplot.setNoDataMessageFont(new Font("", Font.BOLD, 14));
		xyplot.setNoDataMessagePaint(new Color(87, 149, 117));

		xyplot.setBackgroundPaint(new Color(255, 253, 246));
		ValueAxis vaaxis = xyplot.getDomainAxis();
		vaaxis.setAxisLineStroke(new BasicStroke(1.5f));

		ValueAxis va = xyplot.getDomainAxis(0);
		va.setAxisLineStroke(new BasicStroke(1.5f));

		va.setAxisLineStroke(new BasicStroke(1.5f)); // 坐标轴粗细
		va.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色
		xyplot.setOutlineStroke(new BasicStroke(1.5f)); // 边框粗细
		va.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色
		va.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色
		ValueAxis axis = xyplot.getRangeAxis();
		axis.setAxisLineStroke(new BasicStroke(1.5f));

		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
				.getRenderer();
		xylineandshaperenderer.setSeriesOutlinePaint(0, Color.WHITE);
		xylineandshaperenderer.setUseOutlinePaint(true);
		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		numberaxis.setTickMarkInsideLength(2.0F);
		numberaxis.setTickMarkOutsideLength(0.0F);
		numberaxis.setAxisLineStroke(new BasicStroke(1.5f));
		numberaxis.setUpperBound(maxpress);
		numberaxis.setLowerBound(60);// 最小值设置为60
		NumberAxis numberaxis1 = (NumberAxis) xyplot.getRangeAxis();
		numberaxis1.setTickMarkInsideLength(2.0F);
		numberaxis1.setTickMarkOutsideLength(0.0F);
		numberaxis1.setUpperBound(105d);
		numberaxis1.setLowerBound(35);
		numberaxis1.setAxisLineStroke(new BasicStroke(1.5f));

		// if (xydataset != null) {
		XYBoxAnnotation box = new XYBoxAnnotation(0, 0, 89, 59); // 正常血压所在区域内边界
		XYBoxAnnotation box1 = new XYBoxAnnotation(0, 0, 119, 79);// 高血压前期所在区域内边界
		XYBoxAnnotation box2 = new XYBoxAnnotation(0, 0, 139, 89);// 高血压一期所在区域内边界
		XYBoxAnnotation box3 = new XYBoxAnnotation(0, 0, 159, 99);// 高血压二期所在区域内边界
		XYTextAnnotation text1 = new XYTextAnnotation(nomal, 70, 62.5);// 标识“正常”
		XYTextAnnotation text = new XYTextAnnotation(fore, 70, 82.5);// “高血压前期”
		XYTextAnnotation text2 = new XYTextAnnotation(one, 70, 91.5);// “高血压一期”
		XYTextAnnotation text3 = new XYTextAnnotation(two, 70, 101.5);// “高血压二期”

		// 将上面的边界线条，说明文字加入到xyplot中。
		xyplot.addAnnotation(box);
		xyplot.addAnnotation(box1);
		xyplot.addAnnotation(box2);
		xyplot.addAnnotation(box3);

		xyplot.addAnnotation(text);
		xyplot.addAnnotation(text1);
		xyplot.addAnnotation(text2);
		xyplot.addAnnotation(text3);
		// }
		return jfreechart;
	}

	public static void drawScatterChart(IrisIoInterface io, Log log,
			XYDataset xydataSet, String title, String shou, String shu,
			String nodata, String boolds, String nomal, String fore,
			String one, String two, List<PressureBean> list) {

		JFreeChart chart = createChart(xydataSet, title, shou, shu, nodata,
				boolds, nomal, fore, one, two, list, log);

		HttpServletRequest request = io.getRequest();
		String filename = "";
		String graphURL = "";
		try {
			filename = ServletUtilities.saveChartAsPNG(chart, 400, 300, null,
					io.getSession());
			graphURL = request.getContextPath() + "/displayChart?filename="
					+ filename;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}

		io.setData("filename", filename, BeanShare.BEAN_SHARE_REQUEST);
		io.setData("scatterurl", graphURL, BeanShare.BEAN_SHARE_REQUEST);

	}
}
