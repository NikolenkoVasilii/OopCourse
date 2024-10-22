package ru.academits.nikolenko.lambas_main;

import ru.academits.nikolenko.lambdas.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = new LinkedList<>();
        personsList.add(new Person("Alexey", 28));
        personsList.add(new Person("Oleg", 54));
        personsList.add(new Person("Polina", 25));
        personsList.add(new Person("Vasilii", 30));
        personsList.add(new Person("Victor", 27));
        personsList.add(new Person("Maxim", 31));
        personsList.add(new Person("Maxim", 30));
        personsList.add(new Person("Vasilii", 38
        ));

        List<String> namesList = personsList.stream()
                .map(Person::getName)
                .sorted()
                .toList();
        System.out.println("Сортированный список имен:  " + namesList);

        String namesListUnique = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", "));
        System.out.println("Имена:  " + namesListUnique);

        Stream<Person> teensStream = personsList.stream().filter(x -> x.getAge() < 18);

        OptionalDouble ageAverage = teensStream
                .mapToDouble(Person::getAge)
                .average();
        if (ageAverage.isPresent()) {
            System.out.printf("Средний возраст подростков : %.2f %n", ageAverage.getAsDouble());
            System.out.println();
        }

        Map<String, Double> map = personsList.stream().
                collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Результат группировки по имени:");
        map.forEach((name, age) -> System.out.printf("Ключ (Имя)  : %10s, Значение (Средний возраст) : %.2f%n", name, age));
        System.out.println();

        List<Person> listPersonMiddleAge = personsList.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .toList();
        System.out.println("Люди с возрастом от 20 до 45 в порядке убывания: ");
        listPersonMiddleAge.forEach(name -> System.out.printf("%s", name));
    }
}
