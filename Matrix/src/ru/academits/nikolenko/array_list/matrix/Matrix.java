package ru.academits.nikolenko.array_list.matrix;

import ru.academits.nikolenko.array_list.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно быть больше 0, текущее количество строк: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        int rowsCount = matrix.getRowsCount();
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = matrix.getRow(i);
        }
    }

    public Matrix(double[][] array) {
        int rowsCount = array.length;

        if (rowsCount == 0) {
            throw new IllegalArgumentException("Входной массив не может быть пустым!");
        }

        int maxLength = 0;

        for (double[] row : array) {
            if (row.length > maxLength) {
                maxLength = row.length;
            }
        }

        if (maxLength == 0) {
            throw new IllegalArgumentException("Длина хотя бы одного элемента массива должна быть больше нуля");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(maxLength, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        int rowsCount = vectors.length;

        if (rowsCount == 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк: " + rowsCount);
        }

        rows = new Vector[rowsCount];
        int maxLength = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > maxLength) {
                maxLength = vector.getSize();
            }
        }

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(maxLength);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Строки по такому " + index + " индексу не существует допустимое значение индекса от 0 до " + (getRowsCount() - 1) + " включительно");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Строки по такому " + index + " индексу не существует, допустимое значение индекса от 0 до " + getRowsCount());
        }

        if (getColumnsCount() != row.getSize()) {
            throw new IllegalArgumentException("Размер передаваемого вектора " + row.getSize() + " не равен количеству столбцов " + getColumnsCount());
        }

        int size = row.getSize();

        for (int i = 0; i < size; ++i) {
            rows[index].setCoordinate(i, row.getCoordinate(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Столбца по такому " + index + " индексу не существует допустимое значение индекса от 0 до " + (getColumnsCount() - 1) + " включительно");
        }

        int rowsCount = getRowsCount();
        Vector vector = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; ++i) {
            vector.setCoordinate(i, rows[i].getCoordinate(index));
        }

        return vector;
    }

    public void transpose() {
        Vector[] vectorsArray = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            vectorsArray[i] = getColumn(i);
        }

        rows = vectorsArray;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiply(scalar);
        }
    }

    public double getDeterminant() {
        int rowsCount = getRowsCount();

        if (rowsCount != getColumnsCount()) {
            throw new IllegalStateException("Определитель можно получить только из квадратной матрицы, сейчас количество столбцов матрицы: " + rowsCount + ", а количество столбцов: " + getColumnsCount());
        }

        if (rowsCount == 1) {
            return rows[0].getCoordinate(0);
        }

        if (rowsCount == 2) {
            return rows[0].getCoordinate(0) * rows[1].getCoordinate(1) - rows[0].getCoordinate(1) * rows[1].getCoordinate(0);
        }

        double determinant = 0;

        for (int i = 0; i < rowsCount; i++) {
            determinant += Math.pow(-1, i) * rows[i].getCoordinate(0) * getMinor(this, i);
        }

        return determinant;
    }

    private static double getMinor(Matrix matrix, int removedRowIndex) {
        int rowsCount = matrix.getRowsCount() - 1;
        Matrix result = new Matrix(rowsCount, rowsCount);

        for (int rowIndex = 0, checkedRowIndex = 0; rowIndex <= rowsCount; rowIndex++) {
            for (int columnIndex = 0, checkedColumnIndex = 0; columnIndex <= rowsCount; columnIndex++) {
                if (rowIndex != removedRowIndex && columnIndex != 0) {
                    result.rows[checkedRowIndex].setCoordinate(checkedColumnIndex, matrix.rows[columnIndex].getCoordinate(columnIndex));
                    checkedColumnIndex++;

                    if (checkedColumnIndex == rowsCount) {
                        checkedColumnIndex = 0;
                        checkedRowIndex++;
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

        int rowsCountMinusOne = getRowsCount() - 1;

        for (int i = 0; i < rowsCountMinusOne; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[rowsCountMinusOne]).append('}');
        return stringBuilder.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество столбцов матрицы (" + getColumnsCount() + "), не равно размеру вектора: " + vector.getSize());
        }

        Vector product = new Vector(getRowsCount());

        for (int i = 0; i < rows.length; i++) {
            product.setCoordinate(i, Vector.getScalarProduct(vector, rows[i]));
        }

        return product;
    }

    public void add(Matrix matrix) {
        int rowsCount = getRowsCount();

        if (rowsCount != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Складывать можно только одноразмерные матрицы, сейчас количество строк первой матрицы " + getRowsCount() + ", количество столбцов первой матрицы " +
                    matrix.getColumnsCount() + ", а количество строк второй матрицы " + matrix.getRowsCount() + ", количество столбцов второй матрицы " + matrix.getColumnsCount());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        int rowsCount = getRowsCount();

        if (rowsCount != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Вычитать можно только одноразмерные матрицы, сейчас количество строк первой матрицы " + getRowsCount() + ", количество столбцов первой матрицы " +
                    matrix.getColumnsCount() + ", а количество строк второй матрицы " + matrix.getRowsCount() + ", количество столбцов второй матрицы " + matrix.getColumnsCount());
        }

        for (int i = 0; i < rowsCount; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    private static boolean areSizesEqual(Matrix matrix1, Matrix matrix2) {
        return matrix1.getRowsCount() == matrix2.getRowsCount() && matrix1.getColumnsCount() == matrix2.getColumnsCount();
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (!areSizesEqual(matrix1, matrix2)) {
            throw new IllegalArgumentException("Складывать можно только одноразмерные матрицы, сейчас количество строк первой матрицы " +
                    matrix1.getRowsCount() + ", количество столбцов первой матрицы " + matrix1.getColumnsCount() + ", " +
                    " а количество строк второй матрицы " + matrix2.getRowsCount() + ", количество столбцов второй матрицы" + matrix2.getColumnsCount());
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (!areSizesEqual(matrix1, matrix2)) {
            throw new IllegalArgumentException("Вычитать можно только одноразмерные матрицы, сейчас количество строк первой матрицы " +
                    matrix1.getRowsCount() + ", количество столбцов первой матрицы " + matrix1.getColumnsCount() + ", " +
                    " а количество строк второй матрицы " + matrix2.getRowsCount() + ", количество столбцов второй матрицы" + matrix2.getColumnsCount());
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);
        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int matrix1RowsCount = matrix1.getRowsCount();
        int matrix2ColumnsCount = matrix2.getColumnsCount();

        if (matrix1RowsCount != matrix2ColumnsCount) {
            throw new IllegalArgumentException("Умножать можно только матрицы, в которых количество строк первой матрицы = "
                    + matrix1RowsCount + " равно количеству столбцов второй = " + matrix2.getColumnsCount());
        }

        Matrix resultMatrix = new Matrix(matrix1RowsCount, matrix2ColumnsCount);

        for (int i = 0; i < matrix1RowsCount; ++i) {
            for (int j = 0; j < matrix2ColumnsCount; ++j) {
                resultMatrix.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return resultMatrix;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) o;

        if (rows.length != matrix.rows.length) {
            return false;
        }

        int rowsCount = getRowsCount();
        Vector[] rowsArray = new Vector[getRowsCount()];
        Vector[] matrixRowsArray = new Vector[matrix.getRowsCount()];

        for (int i = 0; i <= rowsCount; i++) {
            rowsArray[i] = rows[i];
            matrixRowsArray[i] = matrix.rows[i];
        }

        return Arrays.equals(rowsArray, matrixRowsArray);
    }

    @Override
    public int hashCode() {
        int rowsCount = getRowsCount();

        int hash = 0;

        Vector[] rowsArray = new Vector[getRowsCount()];

        for (int i = 0; i <= rowsCount; i++) {
            rowsArray[i] = rows[i];
        }

        return Arrays.hashCode(rowsArray);
    }
}