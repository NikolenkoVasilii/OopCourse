package ru.academits.nikolenko.hashTableMain;

import ru.academits.nikolenko.arrayList.ArrayList;
import ru.academits.nikolenko.hashTable.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();
        hashTable.add(5);
        hashTable.add(3);
        hashTable.add(3);
        hashTable.add(3);
        hashTable.add(21);
        hashTable.add(133);
        System.out.println(hashTable);

        hashTable.clear();
        hashTable.add(5);
        hashTable.add(3);
        System.out.println(hashTable);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(18);
        arrayList.add(19);

        hashTable.addAll(arrayList);
        System.out.println(hashTable);

        if (hashTable.contains(3)) {
            System.out.println("Хэш-таблица содержит 3");
        }

        hashTable.remove(18);
        System.out.println("Хэщ-таблица: " + System.lineSeparator() + hashTable);
        System.out.println("Список :" + System.lineSeparator() + arrayList);

        if (hashTable.containsAll(arrayList)) {
            System.out.println("Хэщ таблица содержит в себе все элементы списка");
        } else {
            System.out.println("Хэш таблица не содержит в себе все элементы списка");
        }

        hashTable.add(18);
        hashTable.add(19);

        System.out.println("Хэщ-таблица: " + System.lineSeparator() + hashTable);
        System.out.println("Список :" + System.lineSeparator() + arrayList);
        if (hashTable.containsAll(arrayList)) {
            System.out.println("Хэщ таблица содержит в себе все элементы списка");
        } else {
            System.out.println("Хэш таблица не содержит в себе все элементы списка");
        }

        hashTable.removeAll(arrayList);
        System.out.println("Хэщ-таблица: " + System.lineSeparator() + hashTable);

        hashTable.add(18);
        hashTable.retainAll(arrayList);
        System.out.println(hashTable);
    }
}