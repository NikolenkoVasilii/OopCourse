package ru.academits.nikolenko.range_main;

import ru.academits.nikolenko.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1, 5);

        System.out.println("Начало диапазона первого интервалла = " + range1.getFrom());
        System.out.println("Конец диапазона первого интервалла = " + range1.getTo() + System.lineSeparator());
        System.out.println("Длина диапазона = " + range1.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число для проверки принадлежит ли оно диапазону:");
        double number = scanner.nextDouble();

        if (range1.isInside(number)) {
            System.out.println("Число принадлежит диапазону" + System.lineSeparator());
        } else {
            System.out.println("Число не принадлежит диапазону" + System.lineSeparator());
        }

        Range range2 = new Range(3, 4);
        System.out.println("Начало диапазона второго интервалла = " + range2.getFrom());
        System.out.println("Конец диапазона второго интервалла = " + range2.getTo() + System.lineSeparator());

        Range range3 = range2.getIntersection(range1);

        if (range3 == null) {
            System.out.println("У диапазонов нет пересечения");
        } else {
            System.out.println("Начало диапазона пересечения = " + range3.getFrom());
            System.out.println("Конец диапазона пересечения = " + range3.getTo() + System.lineSeparator());
        }

        Range[] rangeArraysCombining = range2.getCombining(range1);
        for (Range range : rangeArraysCombining) {
            System.out.println("Начало диапазона объединения = " + range.getFrom());
            System.out.println("Конец диапазона объединения= " + range.getTo() + System.lineSeparator());
        }

        Range[] rangeArraysDifference = range1.getDifference(range2);

        if (rangeArraysDifference == null) {
            System.out.println("разность диапазонов равна 0");
        } else {
            for (Range range : rangeArraysDifference) {
                System.out.println("Начало диапазона разности = " + range.getFrom());
                System.out.println("Конец диапазона разности = " + range.getTo() + System.lineSeparator());
            }
        }
        range1.setFrom(3.5);
        range1.setTo(33.5);
        System.out.println("Начало измененного диапазона = " + range1.getFrom());
        System.out.println("Конец измененного диапазона = " + range1.getTo());
    }
}