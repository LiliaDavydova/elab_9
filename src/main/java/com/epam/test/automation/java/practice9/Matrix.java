package com.epam.test.automation.java.practice9;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Matrix {
    private double[][] array;
    private static final String INCOMPATIBLE_MATRIX_SIZES_MSG = "Incompatible matrix sizes";


    /**
     * Implement a constructor that creates an empty matrix with a given number of rows
     * columns (all values in matrix equal 0.0)
     *
     * @param row    number of rows
     * @param column number of columns
     * @return Returns a new instance of the matrix with the specified parameters
     */
    public Matrix(int row, int column) {
        this.array = new double[row][column];
    }

    /**
     * Implement a constructor that creating of matrix based on existing two-dimensional array.
     *
     * @param twoDimensionalArray existing two-dimensional array
     * @return Returns a new instance of the matrix based on existing two-dimensional array
     * @throws MatrixException if the incoming array with zero number of rows returns the message "Array passed with zero number of rows",
     *                         if the incoming array with zero number of columns returns the message "Array passed with zero number of columns"
     */
    public Matrix(double[][] twoDimensionalArray) throws MatrixException {

        if (twoDimensionalArray == null) {
            throw new MatrixException("Matrix is null");
        }
        if (twoDimensionalArray.length == 0) {
            throw new MatrixException("Array passed with zero number of rows");
        }
        if (twoDimensionalArray[0].length == 0) {
            throw new MatrixException("Array passed with zero number of columns");
        }

        this.array = Arrays.copyOf(twoDimensionalArray, twoDimensionalArray.length);
    }

    /**
     * @return Returns the number of rows in a matrix
     */
    public final int rows() {
        return this.array.length;
    }

    /**
     * @return Returns the number of columns in a matrix
     */
    public final int columns() {
        return this.array[0].length;
    }

    /**
     * Receiving of standard two-dimensional array out of matrix.
     *
     * @return Standard two-dimensional array
     */
    public double[][] twoDimensionalArrayOutOfMatrix() {
        return this.array;
    }

    /**
     * Reading of elements via predetermined correct index
     *
     * @param row    number of rows
     * @param column number of columns
     * @return Returns the value of a matrix element <code>[row,column]</code>
     * @throws MatrixException if index incorrect, returns message "Incompatible matrix sizes"
     */
    public double getValue(int row, int column) throws MatrixException {
        if (row >= this.array.length || column >= this.array[0].length) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES_MSG);
        }
        return this.array[row][column];
    }

    /**
     * Recording value <code>newValue</code> of elements via predetermined correct index <code>[row,column]</code>     *
     *
     * @param row      number of rows
     * @param column   number of columns
     * @param newValue new value of a matrix element
     * @throws MatrixException if index incorrect, returns message "Incompatible matrix sizes"
     */
    public void setValue(int row, int column, double newValue) throws MatrixException {
        if (row >= this.array.length || column >= this.array[0].length) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES_MSG);
        }
        this.array[row][column] = newValue;
    }

    /**
     * Method of matrix's addition  <code>matrix</code>.
     * Result in the original matrix
     *
     * @param matrix matrix corresponding to the second term
     * @return Returns a new resulting matrix
     * @throws MatrixException if incompatible matrix sizes, returns message "Incompatible matrix sizes"
     */
    public Matrix addition(Matrix matrix) throws MatrixException {

        if (this.array.length != matrix.rows() || this.array[0].length != matrix.columns()) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES_MSG);
        }

        double[][] sum = new double[matrix.rows()][matrix.columns()];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum[i][j] = BigDecimal.valueOf(array[i][j] + matrix.getValue(i, j)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        }
        return new Matrix(sum);
    }


    /**
     * Method of matrix's deduction <code>matrix</code> from original.
     * Result in the original matrix
     *
     * @param matrix matrix corresponding to the subtracted
     * @return Returns a new resulting matrix
     * @throws MatrixException if incompatible matrix sizes, returns message "Incompatible matrix sizes"
     */
    public Matrix subtraction(final Matrix matrix) throws MatrixException {
        if (this.array.length != matrix.rows() || this.array[0].length != matrix.columns()) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES_MSG);
        }

        double[][] substr = new double[matrix.rows()][matrix.columns()];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                substr[i][j] = BigDecimal.valueOf(array[i][j] - matrix.getValue(i, j)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        }
        return new Matrix(substr);
    }


    /**
     * Method of matrix's multiplication <code>matrix</code>
     * Result in the original matrix
     *
     * @param matrix matrix corresponding to the second factor
     * @return Returns a new resulting matrix
     * @throws MatrixException if incompatible matrix sizes, returns message "Incompatible matrix sizes"
     */
    public Matrix multiplication(final Matrix matrix) throws MatrixException {
        if (columns() != matrix.rows()) {
            throw new MatrixException(INCOMPATIBLE_MATRIX_SIZES_MSG);
        }

        double[][] result = new double[array.length][matrix.array[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                for (int i = 0; i < matrix.array.length; i++) {
                    result[row][col] += array[row][i] * matrix.array[i][col];
                    result[row][col] = BigDecimal.valueOf(result[row][col]).setScale(2, RoundingMode.HALF_UP).doubleValue();
                }
            }
        }
        return new Matrix(result);
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                try {
                    if (j != this.columns() - 1) {
                        builder.append(decimalFormat.format(getValue(i, j)) + " ");
                    } else {
                        builder.append(decimalFormat.format(getValue(i, j)));
                    }
                } catch (MatrixException e) {
                    e.getMessage();
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        boolean isEqual = true;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != matrix.array[i][j]) {
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}