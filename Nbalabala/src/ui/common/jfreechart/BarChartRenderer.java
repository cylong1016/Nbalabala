/**
 * 
 */
package ui.common.jfreechart;

import java.awt.Color;
import java.awt.Paint;

import org.jfree.chart.renderer.category.BarRenderer;


/**
 *
 * @author Issac Ding
 * @since 2015年6月16日 下午11:17:01
 * @version 1.0
 */
public class BarChartRenderer extends BarRenderer{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -4176995666275652872L;

	private static Paint CHOSEN_COLOR;
	
	private static Paint OTHER_COLOR;
	
	private int index;
	
	public BarChartRenderer(int index) { 
		this.index = index;
        CHOSEN_COLOR = Color.decode("#AFD8F8");	//TODO　改颜色
        OTHER_COLOR = Color.decode("#008E8E");
    } 
	
	public Paint getItemPaint(int i , int j) {
		return j == index? CHOSEN_COLOR : OTHER_COLOR;
	}

}
