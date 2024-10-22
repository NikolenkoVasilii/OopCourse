package ru.academits.nikolenko.lambdas;

public class Person {
    String name;
    int age;

    public Person(String name, int age) {
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
        if (name == null){
            return "()";
        }

        return String.format("{имя: %10s, возраст: %3d}%n", name, age);
    }
}
