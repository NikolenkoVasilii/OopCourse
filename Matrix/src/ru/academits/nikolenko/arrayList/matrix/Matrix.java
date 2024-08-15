package ru.academits.nikolenko.arrayList.matrix;

import ru.academits.nikolenko.arrayList.vector.Vector;

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
            throw new IndexOutOfBoundsException("Строки по такому " + index + "индексу не существует: ");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Строки по такому " + index + "индексу не существует: ");
        }

        if (getColumnsCount() != row.getSize()) {
            throw new IllegalArgumentException("Длина передаваемого вектора не равна длине строки матрицы");
        }

        int size = row.getSize();

        for (int i = 0; i < size; ++i) {
            rows[index].setCoordinate(i, row.getCoordinate(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Строки по такому " + index + "индексу не существует: ");
        }

        int rowCount = getRowsCount();
        Vector vector = new Vector(rowCount);

        for (int i = 0; i < rowCount; ++i) {
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
        int rowCount = getRowsCount();

        if (rowCount != getColumnsCount()) {
            throw new IllegalArgumentException("Определитель можно получить только из квадратной матрицы");
        }

        if (rowCount == 1) {
            return rows[0].getCoordinate(0);
        }

        if (rowCount == 2) {
            return rows[0].getCoordinate(0) * rows[1].getCoordinate(1) - rows[0].getCoordinate(1) * rows[1].getCoordinate(0);
        }

        double determinant = 0;

        for (int i = 0; i < rowCount; i++) {
            determinant += Math.pow(-1, i) * rows[i].getCoordinate(0) * getMinor(this, i);
        }

        return determinant;
    }

    private static double getMinor(Matrix matrix, int removedRow) {
        int rowCount = matrix.getRowsCount() - 1;
        Matrix result = new Matrix(rowCount, rowCount);

        for (int row = 0, i2 = 0; row <= rowCount; row++) {
            for (int column = 0, j2 = 0; column <= rowCount; column++) {
                if (row != removedRow && column != 0) {
                    result.rows[i2].setCoordinate(j2, matrix.rows[row].getCoordinate(column));
                    j2++;

                    if (j2 == rowCount) {
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

        int rowsCount = getRowsCount() - 1;

        for (int i = 0; i < rowsCount; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[rowsCount]).append('}');
        return stringBuilder.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размер матрицы (" + getColumnsCount() + "), не равен размеру вектора: " + vector.getSize());
        }

        int rowCount = getRowsCount();

        Vector product = new Vector(rowCount);

        for (int i = 0; i < rows.length; i++) {
            product.setCoordinate(i, Vector.getScalarProduct(vector, rows[i]));
        }

        return product;
    }

    public void add(Matrix matrix) {
        int rowCount = getRowsCount();

        if (rowCount != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Складывать можно только одноразмерные матрицы, сейчас количество строк первой матрицы " + getRowsCount() + ",количество столбцов первой матрицы " +
                    matrix.getColumnsCount() + ", а количество строк второй матрицы " + matrix.getRowsCount() + ", количество столбцов второй матрицы" + matrix.getColumnsCount());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        int rowCount = getRowsCount();

        if (rowCount != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Вычитать можно только одноразмерные матрицы, сейчас количество строк первой матрицы " + getRowsCount() + ",количество столбцов первой матрицы " +
                    matrix.getColumnsCount() + ", а количество строк второй матрицы " + matrix.getRowsCount() + ", количество столбцов второй матрицы" + matrix.getColumnsCount());
        }

        for (int i = 0; i < rowCount; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    private static boolean areSizesNotEqual(Matrix matrix1, Matrix matrix2) {
        return matrix1.getRowsCount() != matrix2.getRowsCount() || matrix2.getColumnsCount() != matrix2.getColumnsCount();
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (areSizesNotEqual(matrix1, matrix2)) {
            throw new IllegalArgumentException("Складывать можно только одноразмерные матрицы, сейчас размер первой матрицы " +
                    matrix1.getRowsCount() + "," + matrix1.getColumnsCount() + ", а размер второй " + matrix2.getRowsCount() + "," + matrix2.getColumnsCount());
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.add(matrix2);
        return matrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (areSizesNotEqual(matrix1, matrix2)) {
            throw new IllegalArgumentException("Вычитать можно только одноразмерные матрицы, сейчас размер первой матрицы " +
                    matrix1.getRowsCount() + "," + matrix1.getColumnsCount() + ", а размер второй " + matrix2.getRowsCount() + ", " + matrix2.getColumnsCount());
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.subtract(matrix2);
        return matrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int rowsCount = matrix1.getRowsCount();

        if (rowsCount != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Умножать можно только матрицы, в которых количество строк первой матрицы = " + rowsCount + " равно количеству столбцов второй = " + matrix2.getColumnsCount());
        }

        Matrix resultMatrix = new Matrix(rowsCount, rowsCount);

        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j < rowsCount; ++j) {
                resultMatrix.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return resultMatrix;
    }
}