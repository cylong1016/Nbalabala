package ui.panel.analyse.button;

import enums.InferenceData;
import ui.common.button.TextButton;

/**
 * 
 * @author lsy
 * @version 2015年6月15日  下午11:40:42
 */
public class FutureSelectButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = -1625714034916406638L;

	/** 代表当前选中的按钮 */
	public static FutureSelectButton current;
	/** 按钮上的字 */
	private String text;
	/** 枚举类型 */
	private InferenceData inferenceData;
	
	public FutureSelectButton(int x, int y, int width, int height, String text) {
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
