package ru.academits.nikolenko.array_list_main;


import ru.academits.nikolenko.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> name = new ArrayList<>(15);
        System.out.println("Текущий размер списка: " + name.size());
        name.addFirst("Alex");
        System.out.println(name);

        name.addFirst("Mike");
        System.out.println(name);

        name.addFirst("Nikita");
        System.out.println(name);

        System.out.println("Текущий размер списка: " + name.size());
        System.out.println(name);

        name.add(2, "Bob");
        int size = name.size();
        System.out.println("Текущий размер списка: " + size);
        System.out.println(name);

        System.out.println("Первый элемент списка: " + name.getFirst());

        String data = name.get(1);
        System.out.println("Второй элемент списка: " + data);

        System.out.println("Замененный элемент списка: " + name.set(2, "Antoshka"));
        System.out.println(name);

        name.add(1, "Pavel");
        System.out.println(name);

        System.out.println("Текущий размер списка: " + name.size());
        name.addLast("Agent");
        System.out.println(name);

        System.out.println("Удаленный элемент списка: " + name.remove(3));
        System.out.println(name);

        ArrayList<String> nameForAdd = new ArrayList<>(15);

        nameForAdd.addFirst("Alex");
        nameForAdd.addFirst("Mike");
        nameForAdd.addFirst("Nikita");
        System.out.println(nameForAdd);

        name.addAll(nameForAdd);
        System.out.println(name);

        name.addAll(0, nameForAdd);
        System.out.println(name);

        name.addLast("Nikita");
        System.out.println(name);

        if (name.remove("Nikita")) {
            System.out.println(name);
        } else {
            System.out.println("Не существовала элемента Nikita");
        }

        ArrayList<String> nameForRemoved = new ArrayList<>(15);
        nameForRemoved.addFirst("Mike");
        nameForRemoved.addFirst("Pavel");
        nameForRemoved.addFirst("Evgenii");
        nameForRemoved.addFirst("Evgenii");
        System.out.println(nameForRemoved);

        name.removeAll(nameForRemoved);
        System.out.println(name);

        System.out.println(nameForRemoved.indexOf("Pavel"));
        System.out.println(nameForRemoved.lastIndexOf("Evgenii"));

        ArrayList<String> names = new ArrayList<>();
        names.add("Alisa");
        names.add("Mira");
        System.out.println(names);
        name.addAll(names);
        System.out.println(name);

        if (name.containsAll(names)) {
            System.out.println("Все элементы  коллекции names содержутся в коллекции name");
        } else {
            System.out.println("Не все эдементы  коллекции names содержатся в коллекции name");
        }

        if (names.containsAll(name)) {
            System.out.println("Все элементы  коллекции names содержутся в коллекции name");
        } else {
            System.out.println("Не все эдементы  коллекции names содержатся в коллекции name");
        }
    }
}
