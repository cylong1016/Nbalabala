/**
 * 
 */
package bl.analysisbl.regression;

/**
 *
 * @author Issac Ding
 * @since 2015年6月12日 下午6:44:51
 * @version 1.0
 */
public class RowColumnIndex {
	
	private int[] _$6065;
    private int[] _$6073;
    private double[] _$6081;
    private int _$6094;
    private Matrix _$6107;
    
    public RowColumnIndex() {
        super();
        this._$6065 = null;
        this._$6073 = null;
        this._$6081 = null;
        this._$6107 = null;
    }
    
    public RowColumnIndex(final int[] r_index, final int[] c_index, final double[] values) {
        super();
        this._$6065 = r_index;
        this._$6073 = c_index;
        this._$6081 = values;
        this._$6094 = r_index.length;
        this._$6107 = new Matrix(JElmat.convertTo2D(values));
    }
    
    public int[] getRowIndex() {
        return this._$6065;
    }
    
    public int[] getColumnIndex() {
        return this._$6073;
    }
    
    public double[] getElementValues() {
        return this._$6081;
    }
    
    public int getTotalElements() {
        return this._$6094;
    }
    
    public Matrix getElementValuesInMatrix() {
        return this._$6107;
    }

}
