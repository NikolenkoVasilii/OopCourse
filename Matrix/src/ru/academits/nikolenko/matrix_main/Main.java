package ru.academits.nikolenko.matrix_main;


import ru.academits.nikolenko.matrix.Matrix;
import ru.academits.nikolenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2, 4);
        System.out.println("количество строк матрицы 1 = " + matrix1.getRowCount());
        System.out.println("количество столбцов матрицы 1 = " + matrix1.getColumnCount());

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println("количество строк матрицы 2 = " + matrix2.getRowCount());
        System.out.println("количество столбцов матрицы 2 = " + matrix2.getColumnCount());

        System.out.println("матрица 1 = \n" + matrix1);

        double[][] array1 = {{2, 3}, {3, 4}};
        Matrix matrix3 = new Matrix(array1);
        System.out.println("матрица 3 = \n" + matrix3);

        double[] array2 = {3, 4, 5};
        Vector vector1 = new Vector(array2);
        Vector vector2 = new Vector(array2);

        Vector[] array3 = {vector1, vector2};

        Matrix matrix4 = new Matrix(array3);
        System.out.println("матрица 4 = \n" + matrix4);

        System.out.println("вторая строка матрицы4 = \n" + matrix4.getRow(1));
        System.out.println("второй стообец матрицы4 = \n" + matrix4.getColumn(1));

        double[] array4 = {2, 3, 5};
        Vector vector3 = new Vector(array4);

        matrix4.setRow(1, vector3);
        System.out.println("матрица 4 с измененной второй строкой равна \n" + matrix4);

        matrix4.setRow(0, vector3);
        System.out.println("матрица 4 с измененной ещё и первой строкой = \n" + matrix4);

        matrix4.scalarMultiplication(4);
        System.out.println("матрица 4 скалярно умноженная на 4 = \n" + matrix4);

        double[][] array5 = {{2, 3}, {3, 4}};
        Matrix matrix5 = new Matrix(array5);
        double determinant5 = matrix5.getDeterminant();
        System.out.println("определитель матрицы 5 = " + determinant5);

        double[][] array6 = {{2, 3, 3}, {3, 4, 1}, {3, 3, 3}};
        Matrix matrix6 = new Matrix(array6);
        double determinant6 = matrix6.getDeterminant();
        System.out.println("определитель матрицы 6 = " + determinant6);

       double[][] array15 = {{2, 3, 3, 2 }, {3, 4, 1, 5}, {3, 3, 3, 3}, {2,2,2,2}, };
        Matrix matrix15 = new Matrix(array15);
        double determinant15 = matrix15.getDeterminant();
        System.out.println(" Определитель матрицы 15 =  " + determinant15);


        double[][] array7 = {{2, 3, 3, 5}, {3, 4, 1, 6}};
        Matrix matrix7 = new Matrix(array7);
        matrix1.sum(matrix7);
        System.out.println("матрица 1, равная сумме матрицы 1 и матрицы 7 = \n" + matrix1);
        matrix1.sum(matrix7);
        System.out.println("матрица 1, равная сумме матрицы 1 и матрицы 7 = \n" + matrix1);

        matrix1.subtract(matrix7);
        System.out.println("матрица 1, равная разнице матрицы 1 и матрицы 7 = \n" + matrix1);

        double[][] array8 = {{2, 2, 2, 2}, {3, 3, 3, 3}};
        Matrix matrix8 = new Matrix(array7);
        Matrix matrix9 = new Matrix(array8);

        System.out.println("матрица 8 = \n" + matrix8);
        System.out.println("матрица 9 = \n" + matrix9);
        Matrix matrix10 = Matrix.sum(matrix8, matrix9);
        System.out.println("Матрица 10, равная сумме матрицы 8 и матрицы 9 = \n" + matrix10);

        Matrix matrix11 = Matrix.subtract(matrix8, matrix9);
        System.out.println("Матрица 11, равная разности матрицы 8 и матрицы 9 = \n" + matrix11);

        double[][] array9 = {{2,3},{3,4}, {4,5}, {5,6}};
        Matrix matrix12 = new Matrix(array9);
        System.out.println("Матрица 12 = \n" + matrix12);
        Matrix matrix13 = Matrix.mul(matrix9, matrix12);
        System.out.println("Матрица 13, равная умножению матрицы 8 и матрицы 12 = \n" + matrix13);

        matrix13.transposition();
        System.out.println("Транспонированная матрица 13 = \n" + matrix13);

    }
}