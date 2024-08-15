package ru.academits.nikolenko.arrayList.vector_main;

import ru.academits.nikolenko.arrayList.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(4);
        Vector vector2 = new Vector(vector1);
        double[] array1 = {3, 4, 5};
        Vector vector3 = new Vector(array1);
        Vector vector4 = new Vector(6, array1);
        System.out.println("Вектор1 = " + vector1);
        System.out.println("Вектор2 = " + vector2);
        System.out.println("Вектор3 = " + vector3);
        System.out.println("Вектор4 = " + vector4);

        vector3.add(vector4);
        System.out.println("Вектор3, равный сумме вектора 3 и вектора 4 = " + vector3);

        vector1.add(vector4);
        System.out.println("Вектор1, равный сумме вектора 1 и вектора 4 = " + vector1);

        vector1.subtract(vector4);
        System.out.println("Вектор1, равный разности вектора 1 и вектора 4 = " + vector1);

        Vector vector5 = new Vector(array1);
        System.out.println("Вектор5 = " + vector5);

        vector5.multiply(4);
        System.out.println("Вектор5, равный умножению вектора 5 на 4 = " + vector5);

        vector5.reverse();
        System.out.println("Вектор5, развернутый = " + vector5);

        double coordinate1 = vector5.getCoordinate(2);
        System.out.println("Третья координата вектора 5 = " + coordinate1);

        vector5.setCoordinate(2, 44);
        double coordinate2 = vector5.getCoordinate(2);
        System.out.println("Третья координата вектора 5 = " + coordinate2);

        Vector vector6 = new Vector(array1);
        Vector vector7 = new Vector(array1);
        Vector vector8 = new Vector(5, array1);
        System.out.println("Вектор8 = " + vector8);

        if (vector6.equals(vector7)) {
            System.out.println("Вектор6 и вектор7 равны");
        }

        double[] array2 = {1, 2, 3};
        double[] array3 = {3, 4, 6, 0, 0};
        Vector vector9 = new Vector(array2);
        Vector vector10 = new Vector(array3);
        System.out.println("Вектор9 =  " + vector9);
        System.out.println("Вектор10 =  " + vector10);
        Vector vector11 = Vector.getSum(vector9, vector10);
        System.out.println("Вектор11, равный сумме векторов 9 и 10 = " + vector11);

        Vector vector12 = Vector.getDifference(vector9, vector10);
        System.out.println("Вектор12, равный сумме векторов 9 и 10 = " + vector12);

        double scalarMultiplicationVectors = Vector.getScalarProduct(vector9, vector10);
        System.out.println("Скалярное произведение векторов 9  и 10 = " + scalarMultiplicationVectors);

        double length = vector9.getLength();
        System.out.println("Длина вектора 9 = " + length);

        try {
            Vector vector13 = new Vector(4);
            System.out.println(vector13);
        } catch (IllegalArgumentException e) {
            System.out.println("Нельзя создать вектор длины 0 и меньше");
        }

        Vector vector14 = new Vector(vector11);
        System.out.println("Вектор14, равный вектору 11 = " + vector14);
    }
}