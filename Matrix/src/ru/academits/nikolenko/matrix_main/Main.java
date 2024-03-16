package ru.academits.nikolenko.matrix_main;


import ru.academits.nikolenko.matrix.Matrix;
import ru.academits.nikolenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2, 4);
        System.out.println(matrix1.getRowCount());
        System.out.println(matrix1.getColumnCount());

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println(matrix2.getRowCount());
        System.out.println(matrix2.getColumnCount());

        System.out.println(matrix1);

        double[][] array1 = {{2, 3}, {3, 4}};
        Matrix matrix3 = new Matrix(array1);
        System.out.println(matrix3);

        double[] array2 = {3, 4, 5};
        Vector vector1 = new Vector(array2);
        Vector vector2 = new Vector(array2);

        Vector[] array3 = {vector1, vector2};

        Matrix matrix4 = new Matrix(array3);
        System.out.println(matrix4);

        System.out.println(matrix4.getRow(1));
        System.out.println(matrix4.getColumn(1));

        double[] array4 = {2, 3, 5};
        Vector vector3 = new Vector(array4);

        matrix4.setRow(1, vector3);
        System.out.println(matrix4);

        matrix4.setRow(0, vector3);
        System.out.println(matrix4);

        matrix4.scalarMultiplication(4);
        System.out.println(matrix4);

        double[][] array5 = {{2, 3}, {3, 4}};
        Matrix matrix5 = new Matrix(array5);
        double determinant5 = matrix5.getDeterminant();
        System.out.println(determinant5);

        double[][] array6 = {{2, 3, 3}, {3, 4, 1}, {3, 3, 3}};
        Matrix matrix6 = new Matrix(array6);
        double determinant6 = matrix6.getDeterminant();
        System.out.println(determinant6);

       /* double[][] array7 = {{2, 3, 3, 2 }, {3, 4, 1, 5}, {3, 3, 3, 3}, {2,2,2,2}, };
        Matrix matrix7 = new Matrix(array7);
        double determinant7 = matrix7.getDeterminant();
        System.out.println(determinant7);

        */


    }
}