package ru.academits.nikolenko.matrix;

import ru.academits.nikolenko.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowCount, int columnCount) {
        if (rowCount <= 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк:" + rowCount);
        }

        if (columnCount <= 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк:" + columnCount);
        }

        rows = new Vector[rowCount];

        for (int i = 0; i < rowCount; ++i) {
            rows[i] = new Vector(columnCount);
        }
    }

    public Matrix(Matrix matrix) {
        int rowCount = matrix.getRowCount();
        rows = new Vector[rowCount];

        for (int i = 0; i < rowCount; i++) {
            rows[i] = matrix.getRow(i);
        }
    }

    public Matrix(double[][] array) {
        int rowCount = array.length;

        if (rowCount == 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк:" + rowCount);
        }

        rows = new Vector[rowCount];

        int maxRowsCount = 0;

        for (double[] rows : array) {
            if (rows.length == 0) {
                throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк:" + rowCount);
            }

            if (rows.length > maxRowsCount) {
                maxRowsCount = rows.length;
            }
        }

        for (int i = 0; i < rowCount; ++i) {
            rows[i] = new Vector(array[i]);
        }

        if (maxRowsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы должно быть больше 0, текущее количество строк:" + maxRowsCount);
        }
    }

    public Matrix(Vector[] vectors) {
        int rowCount = vectors.length;

        if (rowCount == 0) {
            throw new IllegalArgumentException("Количество строк матрицы должно быть больше 0, текущее количество строк:" + rowCount);
        }

        rows = new Vector[rowCount];
        int maxRowsCount = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > maxRowsCount) {
                maxRowsCount = vector.getSize();
            }
        }

        if (maxRowsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы: " + vectors[0].getSize() + " должно быть больше 0");
        }

        for (int i = 0; i < rowCount; ++i) {
            rows[i] = new Vector(maxRowsCount);
            rows[i].add(vectors[i]);
        }
    }


    public int getRowCount() {
        return rows.length;
    }

    public int getColumnCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowCount()) {
            throw new IndexOutOfBoundsException("В матрице нет строки с индексом: " + index + System.lineSeparator() + "Допустимое значение от 0 по " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= getRowCount()) {
            throw new IndexOutOfBoundsException("В матрице нет строки с индексом: " + index + System.lineSeparator() + "Допустимое значение от 0 по " + index);
        }

        int size = row.getSize();

        for (int i = 0; i < size; ++i) {
            rows[index].setCoordinate(i, row.getCoordinate(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnCount()) {
            throw new IndexOutOfBoundsException("В матрице нет строки с индексом: " + index + System.lineSeparator() + "Допустимое значение от 0 по " + index);
        }

        int rowCount = getRowCount();
        Vector vector = new Vector(rowCount);

        for (int i = 0; i < rowCount; ++i) {
            vector.setCoordinate(i, rows[i].getCoordinate(index));
        }

        return vector;
    }

    public void transpose() {
        Vector[] vectorsArray = new Vector[getColumnCount()];

        for (int i = 0; i < getColumnCount(); i++) {
            vectorsArray[i] = getColumn(i);
        }

        System.arraycopy(vectorsArray, 0, rows, 0, vectorsArray.length);
    }

    public void multiplyByScalar(double scalar) {

        for (Vector row : rows) {
            row.multiply(scalar);
        }
    }

    public double getDeterminant() {
        int rowCount = getRowCount();

        if (rowCount != getColumnCount()) {
            throw new IllegalArgumentException("Определитель можно получить только из квадратной матрицы");
        }

        if (rowCount == 1) {
            return this.rows[0].getCoordinate(0);
        }

        if (rowCount == 2) {
            return getRow(0).getCoordinate(0) * this.rows[1].getCoordinate(1) - this.rows[0].getCoordinate(1) * this.rows[1].getCoordinate(0);
        }

        int decompositionIndex = 0;
        double determinant = 0;

        for (int i = 0; i < rowCount; i++) {
            determinant += Math.pow(-1, i) * getRow(i).getCoordinate(decompositionIndex) * getMinor(this, i, decompositionIndex);
        }

        return determinant;
    }

    private static double getMinor(Matrix matrix, int firstIndex, int secondIndex) {
        int rowCount = matrix.getRowCount() - 1;
        Matrix result = new Matrix(rowCount, rowCount);

        for (int i = 0, i2 = 0; i <= rowCount; i++) {
            for (int j = 0, j2 = 0; j <= rowCount; j++) {
                if (i != firstIndex && j != secondIndex) {
                    result.rows[i2].setCoordinate(j2, matrix.rows[i].getCoordinate(j));
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

        int size = getRowCount() - 1;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(rows[i]).append(",");
        }

        stringBuilder.append(rows[size]).append('}');
        return stringBuilder.toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnCount()) {
            throw new IllegalArgumentException("Размер матрицы (" + getColumnCount() + "), не равен размеру вектора: " + vector.getSize());
        }

        int rowCount = getRowCount();

        Vector multiplicationResult = new Vector(rowCount);

        for (int i = 0; i < rows.length; i++) {
            multiplicationResult.setCoordinate(i, Vector.getScalarProduct(vector, rows[i]));
        }

        return multiplicationResult;
    }

    public void add(Matrix matrix) {
        int rowCount = getRowCount();

        if (rowCount != matrix.getRowCount() || getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Складывать можно только одноразмерные матрицы, сейчас размер первой матрицы" +
                    getRowCount() + "," + matrix.getColumnCount() + ", а размер второй " + matrix.getRowCount() + "," + matrix.getColumnCount());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        int rowCount = getRowCount();

        if (rowCount != matrix.getRowCount() || getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Вычитать можно только одноразмерные матрицы, сейчас размер первой матрицы" +
                    getRowCount() + "," + matrix.getColumnCount() + ", а размер второй " + matrix.getRowCount() + "," + matrix.getColumnCount());
        }

        for (int i = 0; i < rowCount; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {

        if (!isSizeEquals(matrix1, matrix2)) {
            throw new IllegalArgumentException("Складывать можно только одноразмерные матрицы, сейчас размер первой матрицы" +
                    matrix1.getRowCount() + "," + matrix1.getColumnCount() + ", а размер второй " + matrix2.getRowCount() + "," + matrix2.getColumnCount());
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.add(matrix2);
        return matrix;
    }

    public static boolean isSizeEquals(Matrix matrix1, Matrix matrix2) {
        return (matrix1.getRowCount() != matrix2.getRowCount() || matrix2.getRowCount() != matrix2.getColumnCount());
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (!isSizeEquals(matrix1, matrix2)) {
            throw new IllegalArgumentException("Вычитать можно только одноразмерные матрицы, сейчас размер первой матрицы" +
                    matrix1.getRowCount() + "," + matrix1.getColumnCount() + ", а размер второй " + matrix2.getRowCount() + "," + matrix2.getColumnCount());
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.subtract(matrix2);
        return matrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int rowCount = matrix1.getRowCount();

        if (rowCount != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Умножать можно только матрицы в которыех количество строк первой матрицы = " + rowCount + " равно количеству столбцов второй" + matrix2.getColumnCount());
        }

        Matrix resultMatrix = new Matrix(rowCount, rowCount);

        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < rowCount; ++j) {
                Vector vectorRow = matrix1.rows[i];
                Vector vectorColumn = matrix2.getColumn(j);
                resultMatrix.rows[i].setCoordinate(j, Vector.getScalarProduct(vectorRow, vectorColumn));
            }
        }

        return resultMatrix;
    }
}