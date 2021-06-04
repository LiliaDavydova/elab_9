package com.epam.test.automation.java.practice9;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class MatrixTest {

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionWhenTwoDimensionalArrayIsNull() throws MatrixException {
        new Matrix(null);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionWhenTwoDimensionalArrayRowsIs0() throws MatrixException {
        new Matrix(new double[0][3]);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionWhenTwoDimensionalArrayColumnsIs0() throws MatrixException {
        new Matrix(new double[3][0]);
    }

    @Test
    public void testReturnsTheNumberOfRows() {
        Matrix matrix = new Matrix(3, 4);
        Assert.assertEquals(matrix.rows(), 3);
    }

    @Test
    public void testReturnsTheNumberOfColumns() {
        Matrix matrix = new Matrix(5, 6);
        Assert.assertEquals(matrix.columns(), 6);
    }

    @Test
    public void testTwoDimensionalArrayOutOfMatrix() throws MatrixException {
        double[][] arr = {{1.1, -2.0}, {1.2, 4.0}};
        Matrix matrix = new Matrix(arr);
        Assert.assertEquals(matrix.twoDimensionalArrayOutOfMatrix(), arr);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionWhenGetValueIndexIsMoreThanRows() throws MatrixException {
        Matrix matrix = new Matrix(2, 2);
        matrix.getValue(2, 1);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionWhenGetValueIndexIsMoreThanColumns() throws MatrixException {
        Matrix matrix = new Matrix(2, 2);
        matrix.getValue(1, 2);
    }

    @Test
    public void testReturnsTheValueOfMatrixElement() throws MatrixException {
        double[][] arr = {{1.1, -2.0, 3.4}, {1.2, 5.7, 4.0}, {4.6, 5.7, 1.1}};
        Matrix matrix = new Matrix(arr);
        Assert.assertEquals(matrix.getValue(2, 1), 5.7);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionWhenSetValueIndexIsMoreThanRows() throws MatrixException {
        Matrix matrix = new Matrix(2, 2);
        matrix.setValue(2, 1, 1.2);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionWhenSetValueIndexIsMoreThanColumns() throws MatrixException {
        Matrix matrix = new Matrix(2, 2);
        matrix.setValue(1, 2, 1.4);
    }

    @Test
    public void testSetsTheValueOfMatrixElement() throws MatrixException {
        Matrix matrix = new Matrix(3, 3);
        matrix.setValue(2, 1, 1.2);
        Assert.assertEquals(matrix.getValue(2, 1), 1.2);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionInAdditionWhenRowsOfMatrixIsNotEquals() throws MatrixException {
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(3, 3);
        matrix1.addition(matrix2);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionInAdditionWhenColumnsOfMatrixIsNotEquals() throws MatrixException {
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(2, 2);
        matrix1.addition(matrix2);
    }

    @Test
    public void testReturnsMatrixAddition() throws MatrixException {
        double[][] array1 = {{1.1, -2.0, 3.4},
                {1.2, 5.7, 4.0},
                {4.6, 5.7, 1.1}};
        double[][] array2 = {{1.5, 2.0, 2.3},
                {1.0, 4.0, 2.7},
                {-2.6, 3.4, 1.9}};
        double[][] expectedArray = {{2.6, 0.0, 5.7},
                {2.2, 9.7, 6.7},
                {2.0, 9.1, 3.0}};
        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(array2);
        Matrix expectedMatrix = new Matrix(expectedArray);
        Assert.assertEquals(matrix1.addition(matrix2), expectedMatrix);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionInSubtractionWhenRowsOfMatrixIsNotEquals() throws MatrixException {
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(3, 3);
        matrix1.subtraction(matrix2);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testShouldThrowExceptionInSubtractionWhenColumnsOfMatrixIsNotEquals() throws MatrixException {
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(2, 2);
        matrix1.subtraction(matrix2);
    }

    @Test
    public void testReturnsMatrixSubtraction() throws MatrixException {
        double[][] array1 = {{1.1, -2.0, 3.4},
                {1.2, 5.7, 4.0},
                {4.6, 5.7, 1.1}};
        double[][] array2 = {{1.5, 2.0, 2.3},
                {1.0, 4.0, 2.7},
                {-2.6, 3.4, 1.9}};
        double[][] expectedArray = {{-0.4, -4.0, 1.1},
                {0.2, 1.7, 1.3},
                {7.2, 2.3, -0.8}};
        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(array2);
        Matrix expectedMatrix = new Matrix(expectedArray);
        Assert.assertEquals(matrix1.subtraction(matrix2), expectedMatrix);
    }

    @Test(expectedExceptions = MatrixException.class)
    public void testMultiplicationWithIncompatibleMatrixSizes() throws MatrixException {
        Matrix matrix1 = new Matrix(2, 3);
        Matrix matrix2 = new Matrix(5, 2);
        matrix1.multiplication(matrix2);
    }

    @Test
    public void testReturnsMatrixMultiplication() throws MatrixException {
        double[][] array1 = {{1.1, -2.0},
                {1.2, 4.0}};
        double[][] array2 = {{2.0, 2.3},
                {1.0, 2.6}};
        double[][] expectedArray = {{0.2, -2.67},
                {6.4, 13.16}};
        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(array2);
        Matrix expectedMatrix = new Matrix(expectedArray);
        Assert.assertEquals(matrix1.multiplication(matrix2), expectedMatrix);
    }

    @Test
    public void testToString() throws MatrixException {
        double[][] arr = {{1.1, -2.0}, {1.2, 4.0}};
        Matrix matrix = new Matrix(arr);
        Assert.assertEquals(matrix.toString(), "1,1 -2\n1,2 4\n");
    }

    @Test
    public void testEqualsTrue() throws MatrixException {
        Matrix matrix1 = new Matrix(new double[][]{{1.1, -2.0}, {1.2, 4.0}});
        Matrix matrix2 = new Matrix(new double[][]{{1.1, -2.0}, {1.2, 4.0}});
        Assert.assertTrue(matrix1.equals(matrix2));
    }

    @Test
    public void testEqualsFalse() throws MatrixException {
        Matrix matrix1 = new Matrix(new double[][]{{1.1, -2.0}, {1.2, 4.0}});
        Matrix matrix2 = new Matrix(new double[][]{{4.1, 3.0}, {1.0, -1.0}});
        Assert.assertFalse(matrix1.equals(matrix2));
    }

    @Test
    public void testHashCode() throws MatrixException {
        double[][] arr = {{1.1, -2.0}, {1.2, 4.0}};
        Matrix matrix = new Matrix(arr);
        Assert.assertEquals(matrix.hashCode(), Arrays.hashCode(arr));
    }
}
