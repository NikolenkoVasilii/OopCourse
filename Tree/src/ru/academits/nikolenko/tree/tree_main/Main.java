package ru.academits.nikolenko.tree.tree_main;

import ru.academits.nikolenko.tree.tree.BinaryTree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        BinaryTree<String> stringBinaryTree = new BinaryTree<>();
        stringBinaryTree.add("a");
        stringBinaryTree.add("b");
        stringBinaryTree.add("c");
        stringBinaryTree.add("g");
        stringBinaryTree.add(null);
        stringBinaryTree.add("n");
        stringBinaryTree.add("e");
        stringBinaryTree.add("d");
        stringBinaryTree.add("z");

        System.out.println("Размер текстового дерева :" + stringBinaryTree.getSize());
        System.out.println();
        System.out.println("Результат метода (bypassInWidth):");

        stringBinaryTree.bypassInWidth(node -> System.out.printf("[ %s ]%n", node));
        System.out.println();

        System.out.println("Результат метода (bypassInDepth):");
        stringBinaryTree.bypassInDepth(nodeData -> System.out.printf("[ %s ]%n", nodeData));
        System.out.println();

        String x = "j";

        if (stringBinaryTree.removeNodeByData(x)) {
            System.out.printf("Значение[ %s ] было удалено из текстового дерева.%n", x);
        }
        System.out.printf("Размер текстового дерева после удаления [ %s ] : %s %n", x, stringBinaryTree.getSize());
        System.out.println();

        System.out.println("Результат метода (bypassInDepthUsingRecursion):");
        stringBinaryTree.bypassInDepthUsingRecursion(node -> System.out.printf("[ %s ]%n", node));
        System.out.println();

        Comparator<Integer> integerComparator = (node1, node2) -> {
            if (node1 == null && node2 == null) {
                return 0;
            }

            if (node1 == null) {
                return -1;
            }

            if (node2 == null) {
                return 1;
            }

            return Integer.compare(node1, node2);
        };

        BinaryTree<Integer> integerBinaryTree = new BinaryTree<>(integerComparator);
        integerBinaryTree.add(12);
        integerBinaryTree.add(14);
        integerBinaryTree.add(15);
        integerBinaryTree.add(13);
        integerBinaryTree.add(20);
        integerBinaryTree.add(17);
        integerBinaryTree.add(null);
        integerBinaryTree.add(6);
        integerBinaryTree.add(7);

        System.out.println(integerBinaryTree);


        System.out.println("Размер дерева интов: " + integerBinaryTree.getSize() + System.lineSeparator());
        System.out.println();

        System.out.println("Результат использования метода (bypassInDepth):");
        integerBinaryTree.bypassInDepth(nodeData -> System.out.printf("[ %s ]%n", nodeData));
        System.out.println();

        if (integerBinaryTree.contains(12)) {
            integerBinaryTree.removeNodeByData(12);
            System.out.printf("Значение [ %s ] было удалено.%n", 8);
            System.out.printf("Размер дерева интов после удаления  [ %s ]: %s%n", 8, integerBinaryTree.getSize());
        } else {
            System.out.printf("Дерево интов не содержит в себе [ %d ].", 8);
        }
        System.out.println();

        Integer y = 12;

        if (integerBinaryTree.contains(y)) {
            integerBinaryTree.removeNodeByData(y);
            System.out.printf("Значение [ %s ] было удалено из дерева.%n", y);
            System.out.printf("Размер дерева интов после удаления элемента y [ %s ] is : %s%n", y, integerBinaryTree.getSize());
        } else {
            System.out.printf("Дерево интов не содержит элемента y [ %d ].", y);
        }
        System.out.println();

        System.out.println("Результат метода (bypassInDepthUsingRecursion):");
        integerBinaryTree.bypassInDepthUsingRecursion(nodeData -> System.out.printf("[ %s ]%n", nodeData));
        System.out.println();

        System.out.println("Результат метода (bypassInWidth):");
        integerBinaryTree.bypassInWidth(nodeData -> System.out.printf(" [ %s ]%n", nodeData));
        System.out.println();
    }
}