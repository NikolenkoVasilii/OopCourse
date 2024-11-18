package ru.academits.nikolenko.lambdas;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        if (name == null) {
            throw new NullPointerException("Имя не должно быть null");
        }

        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("{имя: %10s, возраст: %3d}", name, age);
    }
}
