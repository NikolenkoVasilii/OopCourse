package ru.academits.nikolenko.tree.tree_main;

import ru.academits.nikolenko.tree.tree.BinarySearchTree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<String> stringBinarySearchTree = new BinarySearchTree<>();
        stringBinarySearchTree.add("a");
        stringBinarySearchTree.add("b");
        stringBinarySearchTree.add("c");
        stringBinarySearchTree.add("g");
        stringBinarySearchTree.add(null);
        stringBinarySearchTree.add("n");
        stringBinarySearchTree.add("e");
        stringBinarySearchTree.add("d");
        stringBinarySearchTree.add("z");

        System.out.println("Размер текстового дерева :" + stringBinarySearchTree.getSize());
        System.out.println();
        System.out.println("Результат метода (bypassInWidth):");

        stringBinarySearchTree.bypassInWidth(node -> System.out.printf("[ %s ]%n", node));
        System.out.println();

        System.out.println("Результат метода (bypassInDepth):");
        stringBinarySearchTree.bypassInDepth(nodeData -> System.out.printf("[ %s ]%n", nodeData));
        System.out.println();

        String x = "j";

        if (stringBinarySearchTree.removeNodeByData(x)) {
            System.out.printf("Значение[ %s ] было удалено из текстового дерева.%n", x);
        }

        System.out.printf("Размер текстового дерева после удаления [ %s ]: %s %n", x, stringBinarySearchTree.getSize());
        System.out.println();

        System.out.println("Результат метода (bypassInDepthUsingRecursion):");
        stringBinarySearchTree.bypassInDepthUsingRecursion(node -> System.out.printf("[ %s ]%n", node));
        System.out.println();

        Comparator<Integer> integerComparator = (data1, data2) -> {
            if (data1 == null && data2 == null) {
                return 0;
            }

            if (data1 == null) {
                return -1;
            }

            if (data2 == null) {
                return 1;
            }

            return Integer.compare(data1, data2);
        };

        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>(integerComparator);
        integerBinarySearchTree.add(12);
        integerBinarySearchTree.add(14);
        integerBinarySearchTree.add(15);
        integerBinarySearchTree.add(13);
        integerBinarySearchTree.add(20);
        integerBinarySearchTree.add(17);
        integerBinarySearchTree.add(null);
        integerBinarySearchTree.add(6);
        integerBinarySearchTree.add(7);

        System.out.println(integerBinarySearchTree);

        System.out.println("Размер дерева интов: " + integerBinarySearchTree.getSize() + System.lineSeparator());
        System.out.println();

        System.out.println("Результат использования метода (bypassInDepth):");
        integerBinarySearchTree.bypassInDepth(nodeData -> System.out.printf("[ %s ]%n", nodeData));
        System.out.println();

        if (integerBinarySearchTree.contains(12)) {
            integerBinarySearchTree.removeNodeByData(12);
            System.out.printf("Значение [ %s ] было удалено.%n", 8);
            System.out.printf("Размер дерева интов после удаления [ %s ]: %s%n", 8, integerBinarySearchTree.getSize());
        } else {
            System.out.printf("Дерево интов не содержит в себе [ %d ].", 8);
        }

        System.out.println();

        Integer y = 12;

        if (integerBinarySearchTree.contains(y)) {
            integerBinarySearchTree.removeNodeByData(y);
            System.out.printf("Значение [ %s ] было удалено из дерева.%n", y);
            System.out.printf("Размер дерева интов после удаления элемента y [ %s ] is : %s%n", y, integerBinarySearchTree.getSize());
        } else {
            System.out.printf("Дерево интов не содержит элемента y [ %d ].", y);
        }

        System.out.println();

        System.out.println("Результат метода (bypassInDepthUsingRecursion):");
        integerBinarySearchTree.bypassInDepthUsingRecursion(nodeData -> System.out.printf("[ %s ]%n", nodeData));
        System.out.println();

        System.out.println("Результат метода (bypassInWidth):");
        integerBinarySearchTree.bypassInWidth(nodeData -> System.out.printf(" [ %s ]%n", nodeData));
        System.out.println();
    }
}