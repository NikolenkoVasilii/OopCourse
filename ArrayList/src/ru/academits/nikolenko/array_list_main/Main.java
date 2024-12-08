package ru.academits.nikolenko.array_list_main;

import ru.academits.nikolenko.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> namesList = new ArrayList<>(15);
        System.out.println("Текущий размер списка: " + namesList.size());
        namesList.addFirst("Alex");
        System.out.println(namesList);

        namesList.addFirst("Mike");
        System.out.println(namesList);

        namesList.addFirst("Nikita");
        System.out.println(namesList);

        System.out.println("Текущий размер списка: " + namesList.size());
        System.out.println(namesList);

        namesList.add(2, "Bob");
        int size = namesList.size();
        System.out.println("Текущий размер списка: " + size);
        System.out.println(namesList);

        System.out.println("Первый элемент списка: " + namesList.getFirst());

        String data = namesList.get(1);
        System.out.println("Второй элемент списка: " + data);

        System.out.println("Замененный элемент списка: " + namesList.set(2, "Antoshka"));
        System.out.println(namesList);

        namesList.add(1, "Pavel");
        System.out.println(namesList);

        System.out.println("Текущий размер списка: " + namesList.size());
        namesList.addLast("Agent");
        System.out.println(namesList);

        System.out.println("Удаленный элемент списка: " + namesList.remove(3));
        System.out.println(namesList);

        ArrayList<String> namesListForAddition = new ArrayList<>(15);

        namesListForAddition.addFirst("Alex");
        namesListForAddition.addFirst("Mike");
        namesListForAddition.addFirst("Nikita");
        System.out.println(namesListForAddition);

        namesList.addAll(namesListForAddition);
        System.out.println(namesList);

        namesList.addAll(0, namesListForAddition);
        System.out.println(namesList);

        namesList.addLast("Nikita");
        System.out.println(namesList);

        if (namesList.remove("Nikita")) {
            System.out.println(namesList);
        } else {
            System.out.println("Не существовала элемента Nikita");
        }

        ArrayList<String> namesListForRemoving = new ArrayList<>(15);
        namesListForRemoving.addFirst("Mike");
        namesListForRemoving.addFirst("Pavel");
        namesListForRemoving.addFirst("Evgenii");
        namesListForRemoving.addFirst("Evgenii");
        System.out.println(namesListForRemoving);

        namesList.removeAll(namesListForRemoving);
        System.out.println(namesList);

        System.out.println(namesListForRemoving.indexOf("Pavel"));
        System.out.println(namesListForRemoving.lastIndexOf("Evgenii"));

        ArrayList<String> names = new ArrayList<>();
        names.add("Alisa");
        names.add("Mira");
        System.out.println(names);
        namesList.addAll(names);
        System.out.println(namesList);

        if (namesList.containsAll(names)) {
            System.out.println("Все элементы коллекции names содержатся в коллекции namesList");
        } else {
            System.out.println("Не все элементы коллекции names содержатся в коллекции namesList");
        }

        if (names.containsAll(namesList)) {
            System.out.println("Все элементы коллекции names содержатся в коллекции namesList");
        } else {
            System.out.println("Не все элементы коллекции names содержатся в коллекции namesList");
        }

        namesList.retainAll(names);
        System.out.println(namesList);
    }
}
