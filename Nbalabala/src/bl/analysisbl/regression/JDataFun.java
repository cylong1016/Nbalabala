/**
 * 
 */
package bl.analysisbl.regression;

/**
 *
 * @author Issac Ding
 * @since 2015年6月12日 下午6:42:20
 * @version 1.0
 */
public class JDataFun {
	 public static Matrix min(final Matrix X, final Matrix Y) {
	        final int x_rows = X.getRowDimension();
	        final int x_cols = X.getColumnDimension();
	        final int y_rows = Y.getRowDimension();
	        final double[][] Xarray = X.getArray();
	        final double[][] Yarray = Y.getArray();
	        Matrix result = null;
	        if (x_rows != y_rows || x_cols != y_rows) {
	            throw new IllegalArgumentException("Error : Incompatible matrix dimensions.");
	        }
	        result = new Matrix(x_rows, x_cols);
	        for (int i = 0; i < x_rows; ++i) {
	            for (int j = 0; j < x_cols; ++j) {
	                result.set(i, j, (Xarray[i][j] < Yarray[i][j]) ? Xarray[i][j] : Yarray[i][j]);
	            }
	        }
	        return result;
	    }
	    
	    public static Matrix sum(final Matrix matrix) {
	        return sum(matrix, 1);
	    }
	    
	    public static Matrix sum(final Matrix matrix, int Dim) {
	        final double[][] internal = matrix.getArrayCopy();
	        double temp = 0.0;
	        Dim = Math.abs(Dim);
	        Dim %= 2;
	        final int row = matrix.getRowDimension();
	        final int col = matrix.getColumnDimension();
	        double[][] summing;
	        if (Dim == 1) {
	            summing = new double[1][col];
	            for (int j = 0; j < col; ++j) {
	                for (int i = 0; i < row; ++i) {
	                    temp += internal[i][j];
	                }
	                summing[0][j] = temp;
	                temp = 0.0;
	            }
	        }
	        else {
	            summing = new double[row][1];
	            for (int k = 0; k < row; ++k) {
	                for (int l = 0; l < col; ++l) {
	                    temp += internal[k][l];
	                }
	                summing[k][0] = temp;
	                temp = 0.0;
	            }
	        }
	        return new Matrix(summing);
	    }
}
