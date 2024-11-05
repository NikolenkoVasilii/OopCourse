package ru.academits.nikolenko.hash_table_main;

import ru.academits.nikolenko.hash_table.HashTable;

import java.util.ArrayList;

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
        System.out.println("Хэш-таблица:" + System.lineSeparator() + hashTable);
        System.out.println("Список:" + System.lineSeparator() + arrayList);

        if (hashTable.containsAll(arrayList)) {
            System.out.println("Хэш таблица содержит в себе все элементы списка");
        } else {
            System.out.println("Хэш таблица не содержит в себе все элементы списка");
        }

        hashTable.add(18);
        hashTable.add(19);

        System.out.println("Хэш-таблица:" + System.lineSeparator() + hashTable);
        System.out.println("Список:" + System.lineSeparator() + arrayList);

        if (hashTable.containsAll(arrayList)) {
            System.out.println("Хэш таблица содержит в себе все элементы списка");
        } else {
            System.out.println("Хэш таблица не содержит в себе все элементы списка");
        }

        hashTable.removeAll(arrayList);
        System.out.println("Хэш-таблица:" + System.lineSeparator() + hashTable);

        hashTable.add(18);
        hashTable.retainAll(arrayList);
        System.out.println(hashTable);
    }
}