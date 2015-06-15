package ui.panel.analyse;

import ui.common.button.TextButton;
import enums.InferenceData;

/**
 * 分析界面的筛选按钮
 * @author lsy
 * @version 2015年6月15日  下午5:16:07
 */
public class SelectButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = -1625714034916406638L;

	/** 代表当前选中的按钮 */
	public static SelectButton current;
	/** 按钮上的字 */
	private String text;
	/** 枚举类型 */
	private InferenceData inferenceData;

	public SelectButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.text = text;
	}
	public InferenceData getInferenceData() {
		return inferenceData;
	}
	
	public void setInferenceData(InferenceData inferenceData) {
		this.inferenceData = inferenceData;
	}
	
	public String getText(){
		return text;
	}

}
