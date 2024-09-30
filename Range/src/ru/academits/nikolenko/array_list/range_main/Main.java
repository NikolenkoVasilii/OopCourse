package ru.academits.nikolenko.array_list.range_main;

import ru.academits.nikolenko.array_list.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1, 5);

        System.out.println("Первый интервал  = " + range1);
        System.out.println("Начало диапазона первого интервала = " + range1.getFrom());
        System.out.println("Конец диапазона первого интервала = " + range1.getTo());
        System.out.println("Длина первого диапазона  = " + range1.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число для проверки принадлежит ли оно диапазону:");
        double number = scanner.nextDouble();

        if (range1.isInside(number)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }

        Range range2 = new Range(3, 4);
        System.out.println("Второй интервал = " + range2);

        Range intersection = range2.getIntersection(range1);

        if (intersection == null) {
            System.out.println("У диапазонов 1 и 2 нет пересечения");
        } else {
            System.out.println("Пересечения диапазонов 1 и 2 = " + intersection);
        }

        Range[] rangesArrayUnion = range2.getUnion(range1);

        System.out.println("Объединения диапазонов 1 и 2 = " + Arrays.toString(rangesArrayUnion));

        Range[] rangesArrayDifference = range1.getDifference(range2);

        if (rangesArrayDifference == null) {
            System.out.println("Разность диапазонов пустая");
        } else {
            System.out.println("Диапазон разности первого и второго интервала = " + Arrays.toString(rangesArrayDifference));
        }

        range1.setFrom(3.5);
        range1.setTo(33.5);
        System.out.println("Начало измененного первого диапазона = " + range1.getFrom());
        System.out.println("Конец измененного первого диапазона = " + range1.getTo());
        System.out.println("Первый диапазон : " + range1);
    }
}