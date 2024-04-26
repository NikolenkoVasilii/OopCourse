package ru.academits.nikolenko.list_main;

import ru.academits.nikolenko.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> name = new SinglyLinkedList<>();

        name.addFirst("Alex");
        int size = name.getSize();
        System.out.println(size);

        name.addFirst("Mike");
        size = name.getSize();
        System.out.println(size);

        name.addFirst("Nikita");
        size = name.getSize();
        System.out.println(size);

        name.add(2,"Bob");
        size = name.getSize();
        System.out.println(size);
        System.out.println(name);

        name.turn();
        System.out.println(name);






    }
}
