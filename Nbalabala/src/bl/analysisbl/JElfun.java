/**
 * 
 */
package bl.analysisbl;

/**
 *
 * @author Issac Ding
 * @since 2015年6月12日 下午6:43:08
 * @version 1.0
 */
public class JElfun {

    public static Matrix sign(final Matrix matrix) {
        final double[][] d = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (d[i][j] > 0.0) {
                    result.set(i, j, 1.0);
                }
                else if (d[i][j] == 0.0) {
                    result.set(i, j, 0.0);
                }
                else if (d[i][j] < 0.0) {
                    result.set(i, j, -1.0);
                }
            }
        }
        return result;
    }
    
    public static Matrix isInfinte(final Matrix matrix) {
        final double[][] d = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col, 1.0);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (!Double.isInfinite(d[i][j])) {
                    result.set(i, j, 0.0);
                }
            }
        }
        return result;
    }
    
    public static Matrix pow(final double base, final Matrix index) {
        final double[][] d = index.getArray();
        final int row = index.getColumnDimension();
        final int col = index.getRowDimension();
        final double[][] result = new double[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result[i][j] = Math.pow(base, d[i][j]);
            }
        }
        return new Matrix(result);
    }
    
    public static Matrix pow(final Matrix base, final double index) {
        final double[][] d = base.getArray();
        final int row = base.getColumnDimension();
        final int col = base.getRowDimension();
        final double[][] result = new double[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result[i][j] = Math.pow(d[i][j], index);
            }
        }
        return new Matrix(result);
    }
    
    public static Matrix exp(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result.set(i, j, Math.exp(internal[i][j]));
            }
        }
        return result;
    }
    
    public static Matrix sin(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result.set(i, j, Math.sin(internal[i][j]));
            }
        }
        return result;
    }
    
    public static Matrix asin(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (internal[i][j] <= 1.0 || internal[i][j] >= -1.0) {
                    result.set(i, j, Math.asin(internal[i][j]));
                }
                else {
                    result.set(i, j, Double.NaN);
                }
            }
        }
        return result;
    }
    
    public static Matrix cos(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result.set(i, j, Math.cos(internal[i][j]));
            }
        }
        return result;
    }
    
    public static Matrix acos(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (internal[i][j] <= 1.0 || internal[i][j] >= -1.0) {
                    result.set(i, j, Math.acos(internal[i][j]));
                }
                else {
                    result.set(i, j, Double.NaN);
                }
            }
        }
        return result;
    }
    
    public static Matrix sqrt(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (internal[i][j] < 0.0) {
                    result.set(i, j, Double.NaN);
                }
                else {
                    result.set(i, j, Math.sqrt(internal[i][j]));
                }
            }
        }
        return result;
    }
    
    public static Matrix tan(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result.set(i, j, Math.tan(internal[i][j]));
            }
        }
        return result;
    }
    
    public static Matrix atan(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result.set(i, j, Math.atan(internal[i][j]));
            }
        }
        return result;
    }
    
    public static Matrix sync(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (internal[i][j] != 0.0) {
                    result.set(i, j, Math.sin(internal[i][j]) / internal[i][j]);
                }
                else {
                    result.set(i, j, 1.0);
                }
            }
        }
        return result;
    }
    
    public static Matrix sinh(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result.set(i, j, 0.5 * (Math.exp(internal[i][j]) - Math.exp(-internal[i][j])));
            }
        }
        return result;
    }
    
    public static Matrix cosh(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result.set(i, j, 0.5 * (Math.exp(internal[i][j]) + Math.exp(-internal[i][j])));
            }
        }
        return result;
    }
    
    public static Matrix tanh(final Matrix matrix) {
        final double[][] internal = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                result.set(i, j, (Math.exp(internal[i][j]) - Math.exp(-internal[i][j])) / (Math.exp(internal[i][j]) + Math.exp(-internal[i][j])));
            }
        }
        return result;
    }
    
    public static Matrix log10(final Matrix matrix) {
        return logN(10.0, matrix);
    }
    
    public static Matrix logN(final double base, final Matrix matrix) {
        Matrix result = null;
        try {
            result = logN(matrix, base);
        }
        catch (Exception loc_0) {}
        return result;
    }
    
    public static Matrix logN(final Matrix matrix, final double base) throws Exception {
        double b = 1.0;
        final double[][] temp = matrix.getArray();
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        final double[][] result = new double[row][col];
        if (base <= 0) {
            throw new Exception("logN : Negative or zero base result in a Complex Number or negative Infinity.");
        }
        b = Math.log(base);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (temp[i][j] <= 0.0) {
                    throw new Exception("logN : Negative or zero base result in a Complex Number or negative Infinity.");
                }
                result[i][j] = Math.log(temp[i][j]) / b;
            }
        }
        return new Matrix(result);
    }
	
}
