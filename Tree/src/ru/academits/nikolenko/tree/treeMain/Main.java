package ru.academits.nikolenko.tree.treeMain;

import ru.academits.nikolenko.tree.treeNode.MyTree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        MyTree<String> stringMyTree = new MyTree<>();
        stringMyTree.addNode("a");
        stringMyTree.addNode("b");
        stringMyTree.addNode("c");
        stringMyTree.addNode("g");
        stringMyTree.addNode(null);
        stringMyTree.addNode("n");
        stringMyTree.addNode("e");
        stringMyTree.addNode("d");
        stringMyTree.addNode("z");

        System.out.println("Размер текстового дерева : " + stringMyTree.getSize());
        System.out.printf(System.lineSeparator());
        System.out.println("Результат метода (bypassInWidth): ");

        stringMyTree.bypassInWidth(node -> System.out.printf("[ %s ]%n", node));
        System.out.printf(System.lineSeparator());

        System.out.println("Результат метода (bypassInDepth):");
        stringMyTree.bypassInDepth(nodeData -> System.out.printf("[ %s ] %n", nodeData));
        System.out.printf(System.lineSeparator());

        String x = "j";

        if (stringMyTree.removeNodeByValue(x)) {
            System.out.printf("Значение[ %s ] было удалено из текстового дерева.%n", x);
        }
        System.out.printf("Размер текстового дерева после удаления [ %s ] : %s %n", x, stringMyTree.getSize());
        System.out.printf(System.lineSeparator());

        System.out.println("Результат метода (bypassInDepthUsingRecursion) is : ");
        stringMyTree.bypassInDepthUsingRecursion((node) -> System.out.printf("[ %s ]%n", node));
        System.out.println();

        Comparator<Integer> comparatorInteger = (item1, item2) -> {
            if (item1 == null || item2 == null) {
                if (item1 == null && item2 == null) {
                    return 0;
                } else if (item1 == null) {
                    return -1;
                }
                return 1;
            }
            return Integer.compare(item1, item2);
        };

        MyTree<Integer> integerMyTree = new MyTree<>(comparatorInteger);
        integerMyTree.addNode(12);
        integerMyTree.addNode(14);
        integerMyTree.addNode(15);
        integerMyTree.addNode(13);
        integerMyTree.addNode(20);
        integerMyTree.addNode(17);
        integerMyTree.addNode(null);
        integerMyTree.addNode(6);
        integerMyTree.addNode(7);


        System.out.println("Размер дерева интов: " + integerMyTree.getSize() + System.lineSeparator());
        System.out.printf(System.lineSeparator());

        System.out.println("Результат исмпользования метода (bypassInDepth):");
        integerMyTree.bypassInDepth(nodeData -> System.out.printf("[ %s ] %n", nodeData));
        System.out.printf(System.lineSeparator());

        if (integerMyTree.contains(12)) {
            integerMyTree.removeNodeByValue(12);
            System.out.printf("Значение [ %s ] было удалено.%n", 8);
            System.out.printf("Размер дерева интов после удаления  [ %s ]: %s %n", 8, integerMyTree.getSize());
        } else {
            System.out.printf("Дерево интов не содержит в себе [ %d ].", 8);
        }
        System.out.printf(System.lineSeparator());

        Integer y = 12;

        if (integerMyTree.contains(y)) {
            integerMyTree.removeNodeByValue(y);
            System.out.printf("Значение [ %s ] было удалено из дерева.%n", y);
            System.out.printf("Размер дерева интов после удаления элемента y [ %s ] is : %s %n", y, integerMyTree.getSize());
        } else {
            System.out.printf("Дерево интов не содеожит элемента y [ %d ].", y);
        }
        System.out.printf(System.lineSeparator());

        System.out.println("Результат метода (bypassInDepthUsingRecursion):");
        integerMyTree.bypassInDepthUsingRecursion(nodeData -> System.out.printf("[ %s ] %n", nodeData));
        System.out.printf(System.lineSeparator());

        System.out.println("Результат метода (bypassInWidth):");
        integerMyTree.bypassInWidth(nodeData -> System.out.printf(" [ %s ] %n", nodeData));
        System.out.printf(System.lineSeparator());
    }
}