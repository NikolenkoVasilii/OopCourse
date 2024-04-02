package ru.academits.nikolenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Нельзя создать вектор длины 0 и меньше, текущая длина " + size);
        }

        coordinates = new double[size];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] coordinates) {

        if (coordinates.length == 0) {
            throw new IllegalArgumentException("Размер переданного массива = 0");
        }

        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector(int size, double[] coordinates) {
        if (size <= 0) {
            throw new IllegalArgumentException("Нельзя создать вектор длины 0 и меньше, текущая длина " + size);
        }

        this.coordinates = Arrays.copyOf(coordinates, size);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (double coordinate : coordinates) {
            stringBuilder.append(coordinate).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");
        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        int size = coordinates.length;
        int vectorSize = vector.coordinates.length;

        if (size < vectorSize) {
            coordinates = Arrays.copyOf(coordinates, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtractVectors(Vector vector) {
        int size = coordinates.length;
        int vectorSize = vector.coordinates.length;

        if (size < vectorSize) {
            coordinates = Arrays.copyOf(coordinates, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < coordinates.length; i++) {
            this.coordinates[i] *= scalar;
        }
    }

    public void reverse() {
        multiply(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double coordinate : this.coordinates) {
            sum += coordinate * coordinate;
        }

        return Math.sqrt(sum);
    }

    public double getCoordinate(int index) {
        return coordinates[index];
    }

    public void setCoordinate(int index, double coordinate) {
        coordinates[index] = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.add(vector2);
        return vector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subtractVectors(vector2);
        return vector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double multiplication = 0;
        int size = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < size; i++) {
            multiplication += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return multiplication;
    }
}