package ru.academits.nikolenko.list_main;

import ru.academits.nikolenko.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> name = new SinglyLinkedList<>();

        name.addFirst("Alex");

        name.addFirst("Mike");

        name.addFirst("Nikita");

        name.add(2, "Bob");
        int size = name.getSize();
        System.out.println("Текущий размер списка: " + size);
        System.out.println(name);

        name.turn();
        System.out.println(name);

        System.out.println("Первый элемент списка: " + name.getFirst());

        String data = name.getData(2);
        System.out.println("Второй элемент списка: " + data);

        name.setData(2,"Nikolay");
        data = name.getData(2);
        System.out.println("Второй элемент списка: " + data);
        System.out.println(name);

        name.add(1,"Pavel");
        System.out.println(name);

        name.remove("Pavel");
        System.out.println(name);

        name.removeFirst();
        System.out.println(name);


        SinglyLinkedList newName = new SinglyLinkedList<>();
        newName = name.getCopy();
        System.out.println("Новый список:" + newName);

    }
}
