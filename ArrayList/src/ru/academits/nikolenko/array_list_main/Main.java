package ru.academits.nikolenko.array_list_main;

import ru.academits.nikolenko.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> nameList = new ArrayList<>(15);
        System.out.println("Текущий размер списка: " + nameList.size());
        nameList.addFirst("Alex");
        System.out.println(nameList);

        nameList.addFirst("Mike");
        System.out.println(nameList);

        nameList.addFirst("Nikita");
        System.out.println(nameList);

        System.out.println("Текущий размер списка: " + nameList.size());
        System.out.println(nameList);

        nameList.add(2, "Bob");
        int size = nameList.size();
        System.out.println("Текущий размер списка: " + size);
        System.out.println(nameList);

        System.out.println("Первый элемент списка: " + nameList.getFirst());

        String data = nameList.get(1);
        System.out.println("Второй элемент списка: " + data);

        System.out.println("Замененный элемент списка: " + nameList.set(2, "Antoshka"));
        System.out.println(nameList);

        nameList.add(1, "Pavel");
        System.out.println(nameList);

        System.out.println("Текущий размер списка: " + nameList.size());
        nameList.addLast("Agent");
        System.out.println(nameList);

        System.out.println("Удаленный элемент списка: " + nameList.remove(3));
        System.out.println(nameList);

        ArrayList<String> nameListForAddition = new ArrayList<>(15);

        nameListForAddition.addFirst("Alex");
        nameListForAddition.addFirst("Mike");
        nameListForAddition.addFirst("Nikita");
        System.out.println(nameListForAddition);

        nameList.addAll(nameListForAddition);
        System.out.println(nameList);

        nameList.addAll(0, nameListForAddition);
        System.out.println(nameList);

        nameList.addLast("Nikita");
        System.out.println(nameList);

        if (nameList.remove("Nikita")) {
            System.out.println(nameList);
        } else {
            System.out.println("Не существовала элемента Nikita");
        }

        ArrayList<String> nameListForRemoving = new ArrayList<>(15);
        nameListForRemoving.addFirst("Mike");
        nameListForRemoving.addFirst("Pavel");
        nameListForRemoving.addFirst("Evgenii");
        nameListForRemoving.addFirst("Evgenii");
        System.out.println(nameListForRemoving);

        nameList.removeAll(nameListForRemoving);
        System.out.println(nameList);

        System.out.println(nameListForRemoving.indexOf("Pavel"));
        System.out.println(nameListForRemoving.lastIndexOf("Evgenii"));

        ArrayList<String> names = new ArrayList<>();
        names.add("Alisa");
        names.add("Mira");
        System.out.println(names);
        nameList.addAll(names);
        System.out.println(nameList);

        if (nameList.containsAll(names)) {
            System.out.println("Все элементы коллекции names содержатся в коллекции nameList");
        } else {
            System.out.println("Не все элементы коллекции names содержатся в коллекции nameList");
        }

        if (names.containsAll(nameList)) {
            System.out.println("Все элементы коллекции names содержатся в коллекции nameList");
        } else {
            System.out.println("Не все элементы коллекции names содержатся в коллекции nameList");
        }

        nameList.retainAll(names);
        System.out.println(nameList);
    }
}
