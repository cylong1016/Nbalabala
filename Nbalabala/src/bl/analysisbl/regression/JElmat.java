/**
 * 
 */
package bl.analysisbl.regression;

/**
 *
 * @author Issac Ding
 * @since 2015年6月12日 下午6:44:00
 * @version 1.0
 */
public class JElmat extends Matrix{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4293514550642933715L;
	public static final int GREATER_THAN = 100;
    public static final int GREATER_THAN_AND_EQUAL_TO = 101;
    public static final int LESS_THAN = 102;
    public static final int LESS_THAN_AND_EQUAL_TO = 103;
    public static final int EQUAL_TO = 104;
    public static final int NOT_EQUAL_TO = 105;
    
    public JElmat(final int numRows, final int numCols) {
        super(numRows, numCols);
    }
    
    public JElmat(final int numRows, final int numCols, final double value) {
        super(numRows, numCols, value);
    }
    
    public JElmat(final double[][] array) {
        super(array);
    }
    
    public JElmat(final double[][] array, final int numRows, final int numCols) {
        super(array, numRows, numCols);
    }
    
    public JElmat(final double[] arrayVals, final int numRows) {
        super(arrayVals, numRows);
    }
    
    public static double[][] convertTo2D(final double[] x) {
        final double[][] holder = { null };
        final double[] temp = x.clone();
        holder[0] = temp;
        return holder;
    }
    
    public static Matrix convertToMatrix(final double[] x) {
        return new JElmat(convertTo2D(x));
    }
    
    public double[] matrixArrayRow(final int rowNumber) {
        final double[][] result = this.getArrayCopy();
        if (rowNumber > result.length - 1) {
            throw new IllegalArgumentException(" matrixArrowRow : Row index is out-of-bound.");
        }
        return result[rowNumber];
    }
    
    public Matrix matrixRow(final int rowNumber) {
        return convertToMatrix(this.matrixArrayRow(rowNumber));
    }
    
    public Matrix matrixColumn(final int colNumber) {
        final int rows = this.getRowDimension();
        final int cols = this.getColumnDimension();
        if (colNumber > cols - 1) {
            throw new IllegalArgumentException("matrixColumn : Column index is out-of-bound.");
        }
        return this.getMatrix(0, rows - 1, colNumber, colNumber);
    }
    
    public static double[] flip(final double[] a) {
        final int len = a.length;
        final double[] result = new double[len];
        for (int i = 0; i < len; ++i) {
            result[i] = a[len - 1 - i];
        }
        return result;
    }
    
    public void flipLR() {
        final int row = this.getRowDimension();
        final double[][] temp = this.getArray();
        for (int i = 0; i < row; ++i) {
            final double[] singleRow = temp[i];
            temp[i] = flip(singleRow);
        }
    }
    
    public static Matrix flipLR(final Matrix mat) {
        final int row = mat.getRowDimension();
        final double[][] temp = mat.getArray();
        for (int i = 0; i < row; ++i) {
            final double[] singleRow = temp[i];
            temp[i] = flip(singleRow);
        }
        return mat;
    }
    
    public void flipUD() {
        final int row = this.getRowDimension();
        final double[][] result = this.getArray();
        final double[][] temp = result.clone();
        for (int i = 0; i < row; ++i) {
            result[i] = temp[row - 1 - i];
        }
    }
    
    public static Matrix flipUD(final Matrix mat) {
        final int row = mat.getRowDimension();
        final double[][] result = mat.getArray();
        final double[][] temp = result.clone();
        for (int i = 0; i < row; ++i) {
            result[i] = temp[row - 1 - i];
        }
        return mat;
    }
    
    public Matrix rot90() {
        return this.rot90(1);
    }
    
    public Matrix rot90(final int quadrant) {
        int K = Math.abs(quadrant);
        K %= 4;
        final Matrix internal = new Matrix(this.getArrayCopy());
        Matrix temp = null;
        switch (K) {
            case 0: {
                temp = internal;
                break;
            }
            case 1: {
                if (quadrant > 0) {
                    temp = flipLR(internal).transpose();
                    break;
                }
                temp = flipUD(internal).transpose();
                break;
            }
            case 2: {
                if (quadrant > 0) {
                    temp = flipLR(internal).transpose();
                    temp = flipLR(temp).transpose();
                    break;
                }
                temp = flipUD(internal).transpose();
                temp = flipUD(temp).transpose();
                break;
            }
            case 3: {
                if (quadrant > 0) {
                    temp = flipUD(internal).transpose();
                    break;
                }
                temp = flipLR(internal).transpose();
                break;
            }
        }
        return temp;
    }
    
    public double singleIndex(final int ind) {
        final double[] temp = this.getColumnPackedCopy();
        final int len = temp.length;
        if (ind >= len) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(String.valueOf(new StringBuffer("singleIndex : Index = ").append(ind).append(", should be less than ").append(len).append(" ."))));
        }
        return temp[ind];
    }
    
    public static Matrix rot90(final Matrix mat) {
        return rot90(mat, 1);
    }
    
    public static Matrix rot90(final Matrix matrix, final int quadrant) {
        int K = Math.abs(quadrant);
        K %= 4;
        Matrix temp = null;
        final Matrix internal = new Matrix(matrix.getArrayCopy());
        switch (K) {
            case 0: {
                temp = internal;
                break;
            }
            case 1: {
                if (quadrant > 0) {
                    temp = flipLR(internal).transpose();
                    break;
                }
                temp = flipUD(internal).transpose();
                break;
            }
            case 2: {
                if (quadrant > 0) {
                    temp = flipLR(internal).transpose();
                    temp = flipLR(temp).transpose();
                    break;
                }
                temp = flipUD(internal).transpose();
                temp = flipUD(temp).transpose();
                break;
            }
            case 3: {
                if (quadrant > 0) {
                    temp = flipUD(internal).transpose();
                    break;
                }
                temp = flipLR(internal).transpose();
                break;
            }
        }
        return temp;
    }
    
    public static Matrix eye(final int m, final int n) throws ArrayIndexOutOfBoundsException {
        if (m < 1 || n < 1) {
            throw new ArrayIndexOutOfBoundsException("eye : Index is less than 1.");
        }
        final double[][] temp = new double[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    temp[i][j] = 1.0;
                }
                else {
                    temp[i][j] = 0.0;
                }
            }
        }
        return new Matrix(temp);
    }
    
    public static Matrix eye(final int n) {
        return eye(n, n);
    }
    
    public static Matrix ones(final int n) throws ArrayIndexOutOfBoundsException {
        return ones(n, n);
    }
    
    public static Matrix ones(final int m, final int n) throws ArrayIndexOutOfBoundsException {
        if (m < 1 || n < 1) {
            throw new ArrayIndexOutOfBoundsException("ones : Index is less than 1.");
        }
        return new Matrix(m, n, 1.0);
    }
    
    public static Matrix zeros(final int n) throws ArrayIndexOutOfBoundsException {
        return zeros(n, n);
    }
    
    public static Matrix zeros(final int m, final int n) throws ArrayIndexOutOfBoundsException {
        if (m < 1 || n < 1) {
            throw new ArrayIndexOutOfBoundsException("zeros : Index is less than 1.");
        }
        return new Matrix(m, n, 0.0);
    }
    
    public static RowColumnIndex find(final double[][] A) {
        return find(A, 105, 0.0);
    }
    
    public static RowColumnIndex find(final double[][] A, final int relational, final double value) {
        int row = 0;
        int col = 0;
        int count = 0;
        if (A == null) {
            return new RowColumnIndex();
        }
        row = A.length;
        col = A[0].length;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                switch (relational) {
                    case 100: {
                        if (A[i][j] > value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 101: {
                        if (A[i][j] >= value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 102: {
                        if (A[i][j] < value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 103: {
                        if (A[i][j] <= value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 104: {
                        if (A[i][j] == value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 105: {
                        if (A[i][j] != value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        if (count != 0) {
            final int[] row_ind = new int[count];
            final int[] col_ind = new int[count];
            final double[] arrayValues = new double[count];
            count = 0;
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    switch (relational) {
                        case 100: {
                            if (A[i][j] > value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 101: {
                            if (A[i][j] >= value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 102: {
                            if (A[i][j] < value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 103: {
                            if (A[i][j] <= value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 104: {
                            if (A[i][j] == value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 105: {
                            if (A[i][j] != value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                    }
                }
            }
            return new RowColumnIndex(row_ind, col_ind, arrayValues);
        }
        return new RowColumnIndex();
    }
    
    public static RowColumnIndex find(final Matrix mat) {
        return find(mat, 105, 0.0);
    }
    
    public static RowColumnIndex find(final Matrix mat, final int relational, final double value) {
        int row = 0;
        int col = 0;
        int count = 0;
        if (mat == null) {
            return new RowColumnIndex();
        }
        row = mat.getRowDimension();
        col = mat.getColumnDimension();
        final double[][] A = mat.getArray();
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                switch (relational) {
                    case 100: {
                        if (A[i][j] > value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 101: {
                        if (A[i][j] >= value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 102: {
                        if (A[i][j] < value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 103: {
                        if (A[i][j] <= value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 104: {
                        if (A[i][j] == value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                    case 105: {
                        if (A[i][j] != value) {
                            ++count;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        if (count != 0) {
            final int[] row_ind = new int[count];
            final int[] col_ind = new int[count];
            final double[] arrayValues = new double[count];
            count = 0;
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    switch (relational) {
                        case 100: {
                            if (A[i][j] > value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 101: {
                            if (A[i][j] >= value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 102: {
                            if (A[i][j] < value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 103: {
                            if (A[i][j] <= value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 104: {
                            if (A[i][j] == value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                        case 105: {
                            if (A[i][j] != value) {
                                row_ind[count] = i;
                                col_ind[count] = j;
                                arrayValues[count] = A[i][j];
                                ++count;
                                break;
                            }
                            break;
                        }
                    }
                }
            }
            return new RowColumnIndex(row_ind, col_ind, arrayValues);
        }
        return new RowColumnIndex();
    }
    
    public static Matrix vander(final double[] a) throws Exception {
        return vander(a, 0);
    }
    
    public static Matrix vander(final double[] a, final int numColumns) throws Exception {
        final int arrayLength = a.length;
        int col = 0;
        double temp = 0.0;
        if (numColumns < 0) {
            throw new Exception("Vander :- Column dimensions is out-of-bound, index limits boundries is >= 0 ");
        }
        if (numColumns == 0) {
            col = arrayLength;
        }
        else {
            col = numColumns;
        }
        final Matrix vanderMatrix = new Matrix(arrayLength, col, 1.0);
        for (int i = 0; i < arrayLength; ++i) {
            for (int j = 0; j < col - 1; ++j) {
                temp = Math.pow(a[i], col - 1 - j);
                vanderMatrix.set(i, j, temp);
            }
        }
        return vanderMatrix;
    }
    
    public static double[][] repmat(final double[][] a, final int m, final int n) throws Exception {
        final int row = a.length;
        final int col = a[0].length;
        int countRow = 0;
        int countColumn = 0;
        if (m < 1 || n < 1) {
            throw new Exception("Repmat :- Index should be at least 1.");
        }
        final int newRowDim = row * m;
        final int newColDim = col * n;
        final double[][] result = new double[newRowDim][];
        double[] tempHolder = new double[newColDim];
        for (int i = 0; i < newRowDim; ++i) {
            for (int j = 0; j < newColDim; ++j) {
                tempHolder[j] = a[countRow][countColumn++];
                if (countColumn == col) {
                    countColumn = 0;
                }
            }
            if (++countRow == row) {
                countRow = 0;
            }
            result[i] = tempHolder;
            tempHolder = new double[newColDim];
        }
        return result;
    }
    
    public static Matrix repmat(final Matrix matrix, final int m, final int n) throws Exception {
        final double[][] a = matrix.getArrayCopy();
        final int row = a.length;
        final int col = a[0].length;
        int countRow = 0;
        int countColumn = 0;
        if (m < 1 || n < 1) {
            throw new Exception("Repmat :- Index should be at least 1.");
        }
        final int newRowDim = row * m;
        final int newColDim = col * n;
        final double[][] result = new double[newRowDim][];
        double[] tempHolder = new double[newColDim];
        for (int i = 0; i < newRowDim; ++i) {
            for (int j = 0; j < newColDim; ++j) {
                tempHolder[j] = a[countRow][countColumn++];
                if (countColumn == col) {
                    countColumn = 0;
                }
            }
            if (++countRow == row) {
                countRow = 0;
            }
            result[i] = tempHolder;
            tempHolder = new double[newColDim];
        }
        return new Matrix(result);
    }
    
    public static double[][] reshape(final double[][] a, final int newrow, final int newcol) throws Exception {
        final int row = a.length;
        final int col = a[0].length;
        int count = 0;
        final double[][] columnVector = toColumnVector(a);
        if (row * col != newrow * newcol || newrow < 1 || newcol < 1) {
            throw new Exception("Exception : reshape - OutofBoundIndex.");
        }
        final double[][] result = new double[newrow][newcol];
        for (int i = 0; i < newrow; ++i) {
            for (int j = 0; j < newcol; ++j) {
                result[i][j] = columnVector[count++][0];
            }
        }
        return result;
    }
    
    public static Matrix reshape(final Matrix matrix, final int newrow, final int newcol) throws Exception {
        final int row = matrix.getRowDimension();
        final int col = matrix.getColumnDimension();
        int count = 0;
        final Matrix columnVector = toColumnVector(matrix);
        if (row * col != newrow * newcol || newrow < 1 || newcol < 1) {
            throw new Exception("Exception : reshape - OutofBoundIndex.");
        }
        final Matrix result = new Matrix(newrow, newcol);
        for (int i = 0; i < newrow; ++i) {
            for (int j = 0; j < newcol; ++j) {
                result.set(i, j, columnVector.get(count++, 0));
            }
        }
        return result;
    }
    
    public static double[][] toColumnVector(final double[][] a) {
        final int row = a.length;
        final int col = a[0].length;
        double[][] result;
        if (col == 1) {
            result = a.clone();
        }
        else {
            result = new double[row][1];
            int count = 0;
            for (int j = 0; j < col; ++j) {
                for (int i = 0; i < row; ++i) {
                    result[count++][0] = a[i][j];
                }
            }
        }
        return result;
    }
    
    public static Matrix toColumnVector(final Matrix matrix) {
        final double[] a = matrix.getColumnPackedCopy();
        final double[][] result = { a };
        final Matrix matResult = new Matrix(result);
        return matResult.transpose();
    }
    
    public Matrix toMatrixColumnVector() {
        final double[] a = this.getColumnPackedCopy();
        final double[][] result = { a };
        final Matrix matResult = new Matrix(result);
        return matResult.transpose();
    }
    
    public static double[][] toRowVector(final double[][] a) {
        final int row = a.length;
        final int col = a[0].length;
        double[][] result;
        if (row == 1) {
            result = a.clone();
        }
        else {
            result = new double[1][col];
            int count = 0;
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    result[0][count++] = a[i][j];
                }
            }
        }
        return result;
    }
    
    public static Matrix toRowVector(final Matrix matrix) {
        final double[] a = matrix.getRowPackedCopy();
        final double[][] result = { a };
        return new Matrix(result);
    }
    
    public Matrix toMatrixRowVector() {
        final double[] a = this.getRowPackedCopy();
        final double[][] result = { a };
        return new JElmat(result);
    }
    
    public Matrix tril() {
        return this.tril(0);
    }
    
    public Matrix tril(final int diagonal) {
        final double[][] temp = this.getArrayCopy();
        final int rows = this.getRowDimension();
        final int cols = this.getColumnDimension();
        if (-(rows - 1) <= diagonal && diagonal <= cols - 1) {
            if (diagonal == 0) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < cols; ++j) {
                        if (j > i) {
                            temp[i][j] = 0.0;
                        }
                    }
                }
            }
            else if (diagonal < 0) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < cols; ++j) {
                        if (j > i + diagonal) {
                            temp[i][j] = 0.0;
                        }
                    }
                }
            }
            else if (diagonal > 0) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < cols; ++j) {
                        if (j > i + diagonal) {
                            temp[i][j] = 0.0;
                        }
                    }
                }
            }
            return new JElmat(temp);
        }
        if (diagonal >= cols) {
            return new JElmat(temp);
        }
        if (diagonal <= -rows) {
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    temp[i][j] = 0.0;
                }
            }
        }
        return new JElmat(temp);
    }
    
    public Matrix triu() {
        return this.triu(0);
    }
    
    public Matrix triu(final int diagonal) {
        final double[][] temp = this.getArrayCopy();
        final int rows = this.getRowDimension();
        final int cols = this.getColumnDimension();
        if (-(rows - 1) <= diagonal && diagonal <= cols - 1) {
            if (diagonal == 0) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < cols; ++j) {
                        if (i > j) {
                            temp[i][j] = 0.0;
                        }
                    }
                }
            }
            else if (diagonal < 0) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < cols; ++j) {
                        if (i + diagonal > j) {
                            temp[i][j] = 0.0;
                        }
                    }
                }
            }
            else if (diagonal > 0) {
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < cols; ++j) {
                        if (i + diagonal > j) {
                            temp[i][j] = 0.0;
                        }
                    }
                }
            }
            return new Matrix(temp);
        }
        if (diagonal <= -rows) {
            return new Matrix(temp);
        }
        if (diagonal >= cols) {
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    temp[i][j] = 0.0;
                }
            }
        }
        return new Matrix(temp);
    }
    
    public boolean isRowVector() {
        final int rows = this.getRowDimension();
        return rows == 1;
    }
    
    public boolean isColumnVector() {
        final int cols = this.getColumnDimension();
        return cols == 1;
    }
    
    public Matrix diag() {
        return this.diag(0);
    }
    
    public Matrix diag(final int K) {
        final double[][] arrayCopy = this.getArrayCopy();
        final int rows = this.getRowDimension();
        final int cols = this.getColumnDimension();
        final int smallDim = (rows < cols) ? rows : cols;
        JElmat temp = new JElmat(arrayCopy);
        JElmat result = null;
        if (temp.isRowVector()) {
            result = new JElmat(cols, cols);
            for (int i = 0; i < cols; ++i) {
                result.set(i, i, temp.get(0, i));
            }
            return result;
        }
        if (temp.isColumnVector()) {
            result = new JElmat(rows, rows);
            for (int i = 0; i < rows; ++i) {
                result.set(i, i, temp.get(i, 0));
            }
            return result;
        }
        if (-(rows - 1) <= K && K <= cols - 1) {
            if (K == 0) {
                result = new JElmat(smallDim, 1);
                for (int i = 0; i < smallDim; ++i) {
                    result.set(i, 0, temp.get(i, i));
                }
            }
            else {
                if (K > 0) {
                    return this.diag();
                }
                if (K < 0) {
                    return this.diag();
                }
            }
            temp = result;
            return temp;
        }
        return null;
    }
    
    public boolean isEqual(final JElmat jmat) {
        final int thisSizeRow = this.getRowDimension();
        final int thisSizeCol = this.getColumnDimension();
        final int rowSizeJmat = jmat.getRowDimension();
        final int colSizeJmat = jmat.getColumnDimension();
        double[][] thisArray = null;
        double[][] jmatArray = null;
        boolean result = true;
        if (thisSizeRow != rowSizeJmat || thisSizeCol != colSizeJmat) {
            return false;
        }
        thisArray = this.getArray();
        jmatArray = jmat.getArray();
    Label_0113:
        for (int i = 0; i < thisSizeRow; ++i) {
            for (int j = 0; j < thisSizeCol; ++j) {
                if (thisArray[i][j] != jmatArray[i][j]) {
                    result = false;
                    break Label_0113;
                }
            }
        }
        return result;
    }
    
    public static Matrix linspace(final double leftBound, final double rightBound) {
        return linspace(leftBound, rightBound, 100);
    }
    
    public static Matrix linspace(final double leftBound, final double rightBound, final int nPoints) {
        double startX = 0.0;
        double endX = 0.0;
        boolean flip = false;
        if (nPoints < 1) {
            throw new IllegalArgumentException("linspace : Number of points should be at least 1 .");
        }
        if (nPoints == 1) {
            final double[] linVector = { rightBound };
            return new Matrix(convertTo2D(linVector));
        }
        if (leftBound == rightBound) {
            final double[] linVector = new double[nPoints];
            for (int i = 0; i < nPoints; ++i) {
                linVector[i] = leftBound;
            }
            return new Matrix(convertTo2D(linVector));
        }
        if (rightBound < leftBound) {
            startX = rightBound;
            endX = leftBound;
            flip = true;
        }
        else {
            startX = leftBound;
            endX = rightBound;
        }
        final double[] temp = new double[nPoints];
        double[] result = new double[nPoints];
        for (int p = 0; p < nPoints; ++p) {
            if (p == 0) {
                temp[p] = 0.0;
                result[p] = startX;
            }
            else if (p == nPoints - 1) {
                result[p] = endX;
            }
            else {
                temp[p] = temp[p - 1] + 1.0;
                result[p] = startX + temp[p] * (endX - startX) / (nPoints - 1);
            }
        }
        if (flip) {
            result = flip(result);
        }
        return new Matrix(convertTo2D(result));
    }
    
    public static Matrix logspace(final double leftBound, final double rightBound) {
        return logspace(leftBound, rightBound, 50);
    }
    
    public static Matrix logspace(final double leftBound, final double rightBound, final int nPoints) {
        double startX = 0.0;
        double endX = 0.0;
        boolean flip = false;
        if (nPoints < 1) {
            throw new IllegalArgumentException("logspace : Number of points should be at least 1 .");
        }
        if (nPoints == 1) {
            final double[] linVector = { Math.pow(10.0, rightBound) };
            return new Matrix(convertTo2D(linVector));
        }
        if (leftBound == rightBound) {
            final double[] linVector = new double[nPoints];
            for (int i = 0; i < nPoints; ++i) {
                linVector[i] = Math.pow(10.0, leftBound);
            }
            return new Matrix(convertTo2D(linVector));
        }
        if (rightBound < leftBound) {
            startX = rightBound;
            endX = leftBound;
            flip = true;
        }
        else {
            startX = leftBound;
            endX = rightBound;
        }
        final double[] temp = new double[nPoints];
        double[] result = new double[nPoints];
        for (int p = 0; p < nPoints; ++p) {
            if (p == 0) {
                temp[p] = 0.0;
                result[p] = Math.pow(10.0, startX);
            }
            else if (p == nPoints - 1) {
                if (endX == 3.141592653589793) {
                    endX = Math.log(3.141592653589793) / Math.log(10.0);
                }
                result[p] = Math.pow(10.0, endX);
            }
            else {
                temp[p] = temp[p - 1] + 1.0;
                result[p] = Math.pow(10.0, startX + temp[p] * (endX - startX) / (nPoints - 1));
            }
        }
        if (flip) {
            result = flip(result);
        }
        return new Matrix(convertTo2D(result));
    }
}
