package ru.academits.nikolenko.lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static List<String> getUniqueNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Person> getTeens(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.getAge() < 18)
                .toList();
    }

    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(
                new Person("Alexey", 28),
                new Person("Alexey", 28),
                new Person("Oleg", 54),
                new Person("Polina", 25),
                new Person("Vasilii", 16),
                new Person("Victor", 27),
                new Person("Maxim", 15),
                new Person("Maxim", 13),
                new Person("Vasilii", 38));

        List<String> uniqueNames = getUniqueNames(personsList);
        System.out.println(uniqueNames.stream().collect(Collectors.joining(", ", "Имена: ", ".")));

        List<Person> teens = getTeens(personsList);

        OptionalDouble teensAverageAge = teens.stream()
                .mapToInt(Person::getAge)
                .average();

        if (teensAverageAge.isPresent()) {
            System.out.printf("Средний возраст подростков: %.2f%n", teensAverageAge.getAsDouble());
        } else {
            System.out.println("Нет людей младше 18");
        }

        Map<String, Double> averageAgesByNames = personsList.stream().
                collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Результат группировки по имени:");
        averageAgesByNames.forEach((name, age) -> System.out.printf("Ключ (Имя)  : %10s, Значение (Средний возраст) : %.2f%n", name, age));
        System.out.println();

        List<Person> middleAgePersonsList = personsList.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .toList();
        System.out.println("Люди с возрастом от 20 до 45 в порядке убывания:");
        middleAgePersonsList.forEach(System.out::println);
    }
}