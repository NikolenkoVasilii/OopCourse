package ru.academits.nikolenko.list_main;

import ru.academits.nikolenko.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> namesList = new SinglyLinkedList<>();

        namesList.addFirst("Alex");

        namesList.addFirst("Mike");

        namesList.addFirst("Nikita");

        System.out.println("Текущий размер списка: " + namesList.getSize());
        System.out.println(namesList);

        namesList.reverse();
        System.out.println("Развернутый список: " + namesList);

        namesList.add(1, "Bob");
        int size = namesList.getSize();
        System.out.println("Текущий размер списка: " + size);
        System.out.println(namesList);

        System.out.println("Первый элемент списка: " + namesList.getFirst());

        String data = namesList.getData(2);
        System.out.println("Третий элемент списка: " + data);

        String oldData = namesList.getData(2);
        namesList.setData(2, "Nikolay");
        data = namesList.getData(2);
        System.out.println("Старое значение элемента списка: " + oldData);
        System.out.println("Третий элемент списка: " + data);
        System.out.println(namesList);

        namesList.setData(3, "Nikolay");
        data = namesList.getData(3);
        System.out.println("Четвертый элемент списка: " + data);
        System.out.println(namesList);

        namesList.add(1, "Pavel");
        System.out.println(namesList);

        if (namesList.remove("Pavel")) {
            System.out.println(namesList);
        } else {
            System.out.println("Нет такого элемента");
        }

        namesList.removeFirst();
        String firstData = namesList.removeFirst();
        System.out.println("Удаленный элемент = " + firstData);
        System.out.println(namesList);

        SinglyLinkedList<String> copyNamesList = namesList.copy();
        System.out.println("Копированный список: " + copyNamesList);

        namesList.remove(1);
        System.out.println("список без второго элемента: " + namesList);

        namesList.addFirst("Polina");
        namesList.addFirst("Vasilii");
        System.out.println(namesList);

        namesList.removeFirst();

        System.out.println(namesList);
        System.out.println(copyNamesList.removeFirst());

        SinglyLinkedList<String> names = new SinglyLinkedList<>();
        names.addFirst("pop");
        System.out.println(names);
        System.out.println("Текущий размер списка: " + names.getSize());

        names.add(0, "pops");
        System.out.println(names);
        System.out.println("Текущий размер списка: " + names.getSize());

        names.add(2, "pops");
        System.out.println(names);
        System.out.println("Текущий размер списка: " + names.getSize());

        names.add(0, "null");
        System.out.println(names);
        System.out.println("Текущий размер списка: " + names.getSize());
    }
}