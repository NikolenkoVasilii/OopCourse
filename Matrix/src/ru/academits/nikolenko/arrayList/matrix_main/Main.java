package ru.academits.nikolenko.arrayList.matrix_main;

import ru.academits.nikolenko.arrayList.matrix.Matrix;
import ru.academits.nikolenko.arrayList.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2, 4);
        System.out.println("количество строк матрицы 1 = " + matrix1.getRowsCount());
        System.out.println("количество столбцов матрицы 1 = " + matrix1.getColumnsCount());

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println("количество строк матрицы 2 = " + matrix2.getRowsCount());
        System.out.println("количество столбцов матрицы 2 = " + matrix2.getColumnsCount());

        System.out.println("матрица 1 = " + System.lineSeparator() + matrix1);

        double[][] array1 = {{2, 3}, {3, 4}};
        Matrix matrix3 = new Matrix(array1);
        System.out.println("матрица 3 =" + System.lineSeparator() + matrix3);

        double[] array2 = {3, 4, 5};
        Vector vector1 = new Vector(array2);
        Vector vector2 = new Vector(array2);

        Vector[] array3 = {vector1, vector2};

        Matrix matrix4 = new Matrix(array3);
        System.out.println("матрица 4 =" + System.lineSeparator() + matrix4);

        System.out.println("вторая строка матрицы4 =" + System.lineSeparator() + matrix4.getRow(1));
        System.out.println("второй стобец матрицы4 =" + System.lineSeparator() + matrix4.getColumn(1));

        double[] array4 = {2, 3, 5};
        Vector vector3 = new Vector(array4);

        matrix4.setRow(1, vector3);
        System.out.println("матрица 4 с измененной второй строкой =" + System.lineSeparator() + matrix4);

        matrix4.setRow(0, vector3);
        System.out.println("матрица 4 с измененной ещё и первой строкой =" + System.lineSeparator() + matrix4);

        matrix4.multiplyByScalar(4);
        System.out.println("матрица 4 скалярно умноженная на 4 =" + System.lineSeparator() + matrix4);

        double[][] array5 = {{2, 3}, {3, 4}};
        Matrix matrix5 = new Matrix(array5);
        double determinant5 = matrix5.getDeterminant();
        System.out.println("определитель матрицы 5 =" + determinant5);

        double[][] array6 = {{2, 3, 3}, {3, 4, 1}, {3, 3, 3}};
        Matrix matrix6 = new Matrix(array6);
        double determinant6 = matrix6.getDeterminant();
        System.out.println("определитель матрицы 6 =" + determinant6);

        double[][] array15 = {{2, 3, 3, 2}, {3, 4, 1, 5}, {3, 3, 3, 3}, {2, 2, 2, 2}};
        Matrix matrix15 = new Matrix(array15);
        double determinant15 = matrix15.getDeterminant();
        System.out.println("Определитель матрицы 15 =" + determinant15);

        double[][] array7 = {{2, 3, 3, 5}, {3, 4, 1, 6}};
        Matrix matrix7 = new Matrix(array7);
        matrix1.add(matrix7);
        System.out.println("матрица 1, равная сумме матрицы 1 и матрицы 7 =" + System.lineSeparator() + matrix1);
        matrix1.add(matrix7);
        System.out.println("матрица 1, равная сумме матрицы 1 и матрицы 7 =" + System.lineSeparator() + matrix1);

        matrix1.subtract(matrix7);
        System.out.println("матрица 1, равная разнице матрицы 1 и матрицы 7  = " + System.lineSeparator() + matrix1);

        double[][] array8 = {{2, 2, 2, 2}, {3, 3, 3, 3}};
        Matrix matrix8 = new Matrix(array7);
        Matrix matrix9 = new Matrix(array8);

        System.out.println("матрица 8 = " + System.lineSeparator() + matrix8);
        System.out.println("матрица 9 = " + System.lineSeparator() + matrix9);
        Matrix matrix10 = Matrix.getSum(matrix8, matrix9);
        System.out.println("Матрица 10, равная сумме матрицы 8 и матрицы 9 =" + System.lineSeparator() + matrix10);

        Matrix matrix11 = Matrix.getDifference(matrix8, matrix9);
        System.out.println("Матрица 11, равная разности матрицы 8 и матрицы 9 =" + System.lineSeparator() + matrix11);

        double[][] array9 = {{2, 3}, {3, 4}, {4, 5}, {5, 6}};
        Matrix matrix12 = new Matrix(array9);
        System.out.println("Матрица 12 =" + System.lineSeparator() + matrix12);
        Matrix matrix13 = Matrix.getProduct(matrix9, matrix12);
        System.out.println("Матрица 13, равная умножению матрицы 8 и матрицы 12  " + System.lineSeparator() + matrix13);

        matrix13.transpose();
        System.out.println("Транспонированная матрица 13 =" + System.lineSeparator() + matrix13);

        double[][] array10 = {{2, -1, 3}, {1, 2, 4}};
        Matrix matrix14 = new Matrix(array10);
        double[] array11 = {1, -2, 3};
        Vector vector11 = new Vector(array11);

        Vector vector12 = matrix14.multiplyByVector(vector11);
        System.out.println("Результат умножения матрицы14 на вектор12 =" + vector12);
    }
}