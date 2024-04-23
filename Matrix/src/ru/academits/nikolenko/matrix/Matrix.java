package ru.academits.nikolenko.matrix;


import ru.academits.nikolenko.vector.Vector;

import java.util.Arrays;

import static ru.academits.nikolenko.vector.Vector.*;

public class Matrix {
    private Vector[] row;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк:" + n);
        }

        row = new Vector[n];

        for (int i = 0; i < n; ++i) {
            row[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        int rowCount = matrix.getRowCount();
        row = new Vector[rowCount];

        for (int i = 0; i < rowCount; i++) {
            row[i] = new Vector(matrix.row[i]);
        }
    }

    public Matrix(double[][] matrix) {
        int rowCount = matrix.length;

        if (rowCount == 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк:" + rowCount);
        }

        row = new Vector[rowCount];

        int columnCount = 1;

        for (double[] aMatrix : matrix) {
            if (aMatrix.length == 0) {
                throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк:" + rowCount);
            }

            if (aMatrix.length > columnCount) {
                columnCount = aMatrix.length;
            }
        }

        for (int i = 0; i < rowCount; ++i) {
            row[i] = new Vector(Arrays.copyOf(matrix[i], columnCount));
        }
    }

    public Matrix(Vector[] vectors) {
        int rowCount = vectors.length;

        if (rowCount <= 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк:" + rowCount);
        }

        row = new Vector[rowCount];
        int columnCount = 1;

        for (Vector vector : vectors) {
            if (vector.getSize() == 0) {
                throw new IllegalArgumentException("Нельзя создать матрицу из вектора нулевой длины");
            }

            if (vector.getSize() > columnCount) {
                columnCount = vector.getSize();
            }
        }

        for (int i = 0; i < rowCount; ++i) {
            row[i] = new Vector(columnCount);
            row[i].add(vectors[i]);
        }
    }


    public int getRowCount() {
        return row.length;
    }

    public int getColumnCount() {
        int rowCount = getRowCount();
        int columnCount = 1;

        for (int i = 0; i < rowCount; ++i) {
            if (row[i].getSize() > columnCount) {
                columnCount = row[i].getSize();
            }
        }

        return columnCount;
    }

    public Vector getRow(int i) {
        if (i < 0 || i >= getRowCount()) {
            throw new IllegalArgumentException("Строки с таким номером не существует");
        }

        return new Vector(row[i]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= getRowCount()) {
            throw new IllegalArgumentException("Illegal index of row");
        }

        int size = vector.getSize();

        for (int j = 0; j < size; ++j) {
            row[index].setCoordinate(j, vector.getCoordinate(j));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= this.getColumnCount()) {
            throw new IllegalArgumentException("Illegal index of column");
        }

        int height = getRowCount();
        Vector vector = new Vector(height);

        for (int j = 0; j < height; ++j) {
            vector.setCoordinate(j, row[j].getCoordinate(index));
        }

        return vector;
    }

    public void transposition() {
        Vector[] tamp = new Vector[getColumnCount()];

        for (int i = 0; i < getColumnCount(); i++) {
            tamp[i] = getColumn(i);
        }

        row = new Vector[getColumnCount()];

        for (int i = 0; i < tamp.length; i++) {
            row[i] = new Vector(tamp[i]);
        }
    }

    public void multiplyByScalar (double scalar) {
        int rowCount = getRowCount();

        for (int i = 0; i < rowCount; i++) {
            row[i].multiply(scalar);
        }
    }

    public double getDeterminant() {
        int height = getRowCount();

        if (height != getColumnCount()) {
            throw new IllegalArgumentException("Определитель можно получить только из квадратной матрицы");
        }

        if (height == 1) {
            return getRow(0).getCoordinate(0);
        }

        if (height == 2) {
            return getRow(0).getCoordinate(0) * getRow(1).getCoordinate(1) -
                    getRow(0).getCoordinate(1) * getRow(1).getCoordinate(0);
        }

        if (height == 3) {
            return getRow(0).getCoordinate(0) * getRow(1).getCoordinate(1) *
                    getRow(2).getCoordinate(2) + getRow(2).getCoordinate(0) *
                    getRow(0).getCoordinate(1) * getRow(1).getCoordinate(2) +
                    getRow(0).getCoordinate(2) * getRow(1).getCoordinate(0) *
                            getRow(2).getCoordinate(1) - getRow(0).getCoordinate(2) *
                    getRow(1).getCoordinate(1) * getRow(2).getCoordinate(0) -
                    getRow(1).getCoordinate(0) * getRow(0).getCoordinate(1) *
                            getRow(2).getCoordinate(2) - getRow(0).getCoordinate(0) *
                    getRow(2).getCoordinate(1) * getRow(1).getCoordinate(2);
        }

        int decompositionIndex = 0;
        double determinant = 0;

        for (int i = 0; i < height; i++) {
            determinant += Math.pow(-1, i) * getRow(i).getCoordinate(decompositionIndex) *
                    getMinor(this, i, decompositionIndex);
        }

        return determinant;
    }

    private static double getMinor(Matrix matrix, int firstIndex, int secondIndex) {
        int length = matrix.getRowCount() - 1;
        Matrix result = new Matrix(length, length);

        for (int i = 0, i2 = 0; i < length + 1; i++) {
            for (int j = 0, j2 = 0; j < length + 1; j++) {
                if (i != firstIndex && j != secondIndex) {
                    result.row[i2].setCoordinate(j2, matrix.getRow(i).getCoordinate(j));
                    j2++;

                    if (j2 == length) {
                        j2 = 0;
                        i2++;
                    }
                }
            }
        }

        return result.getDeterminant();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        int size = getRowCount();

        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(row[i].toString()).append(",\n");
        }

        stringBuilder.append(row[size - 1]).append('}');
        return stringBuilder.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        int height = getRowCount();
        int width = getColumnCount();

        Vector mulResult = new Vector(height);

        for (int i = 0; i < height; ++i) {
            double temp = 0;

            for (int j = 0; j < width; ++j) {
                temp += getRow(i).getCoordinate(j) * vector.getCoordinate(j);
            }

            mulResult.setCoordinate(i, temp);
        }

        return mulResult;
    }

    public void add(Matrix matrix) {
        int height = getRowCount();

        if (height != matrix.getRowCount() || getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Складывать можно только одноразмерные матрицы");
        }

        for (int i = 0; i < row.length; i++) {
            row[i].add(matrix.row[i]);
        }
    }

    public void subtract(Matrix matrix) {
        int height = getRowCount();

        if (height != matrix.getRowCount() || getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Вычитать можно только одноразмерные матрицы");
        }

        for (int i = 0; i < height; ++i) {
            row[i].subtract(matrix.row[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Складывать можно только одноразмерные матрицы");
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.add(matrix2);
        return matrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Вычитать можно только одноразмерные матрицы");
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.subtract(matrix2);
        return matrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int rowCount = matrix1.getRowCount();

        if (rowCount != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }

        Matrix mul = new Matrix(rowCount, rowCount);

        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < rowCount; ++j) {
                double support = getScalarProduct(matrix1.getRow(i), matrix2.getColumn(j));
                mul.row[i].setCoordinate(j, support);
            }
        }

        return mul;
    }
}