package ru.academits.nikolenko.list_main;

import ru.academits.nikolenko.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> name = new SinglyLinkedList<>();

        name.addFirst("Alex");

        name.addFirst("Mike");

        name.addFirst("Nikita");

        System.out.println("Текущий размер списка: " + name.getSize());
        System.out.println(name);

        name.add(2, "Bob");
        int size = name.getSize();
        System.out.println("Текущий размер списка: " + size);
        System.out.println(name);

        name.turn();
        System.out.println(name);

        System.out.println("Первый элемент списка: " + name.getFirst());

        String data = name.getData(1);
        System.out.println("Второй элемент списка: " + data);

        String oldData = name.getData(2);
        name.setData(2, "Nikolay");
        data = name.getData(2);
        System.out.println("Старое значение элемента списка: " + oldData);
        System.out.println("Третий элемент списка: " + data);
        System.out.println(name);

        name.setData(3, "Nikolay");
        data = name.getData(3);
        System.out.println("Четвертый элемент списка: " + data);
        System.out.println(name);

        name.add(1, "Pavel");
        System.out.println(name);

        if (name.remove("Pavel")) {
            System.out.println(name);
        } else {
            System.out.println("Нет такого элемента");
        }


        name.removeFirst();
        String firstData = name.removeFirst();
        System.out.println("Удаленный элемент = " + firstData);
        System.out.println(name);


        SinglyLinkedList<String> copyName = new SinglyLinkedList<>();
        copyName = name.getCopy();
        System.out.println("Копированный список:" + copyName);

        name.remove(1);
        System.out.println("список без второго элемента:" + name);

        name.addFirst("Polina");
        name.addFirst("Vasilii");
        System.out.println(name);

        name.removeFirst();

        System.out.println(name);
        System.out.println(copyName.removeFirst());


    }
}
