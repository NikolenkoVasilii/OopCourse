package ru.academits.nikolenko.array_list_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<String> getFileLines(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static <T> ArrayList<T> getListWithoutDuplicates(ArrayList<T> list) {
        ArrayList<T> ListWithoutDuplicates = new ArrayList<>(list.size());

        for (T element : list) {
            if (!ListWithoutDuplicates.contains(element)) {
                ListWithoutDuplicates.add(element);
            }
        }

        return ListWithoutDuplicates;
    }

    public static void main(String[] args) {
        String fileName = "line.txt";
        try {
            ArrayList<String> fileLines = getFileLines(fileName);
            System.out.println("Строки из файла: " + fileLines);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(13, 42, 42, 14, 15, 13, 121));
        System.out.println("Список без повторений: " + getListWithoutDuplicates(list));

        removeEvenNumbers(list);
        System.out.println("Список без четных чисел: " + list);
    }
}