package ru.academits.nikolenko.graphMain;

import ru.academits.nikolenko.myGraph.MyGraph;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
        };

        MyGraph myGraph = new MyGraph(edges);

        int[][] matrix = myGraph.getEdges();

        System.out.println("Матрица edges :");

        for (int j = 0; j < edges.length; j++) {
            for (int i = 0; i < edges.length; i++) {
                System.out.printf(" %2d ", matrix[j][i]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Результат метода (bypassInWidth) is : ");

        myGraph.bypassInWidth(item -> {
            System.out.printf("The vertex is: [%2d], verticesConnected are : { ", item);
            LinkedList<Integer> children = myGraph.getChildren(item);

            for (int index : children) {
                System.out.printf("[%d] ", index);
            }
            System.out.println("}");
        });
        System.out.println();

        System.out.println("Результат метода (bypassInDepth) is : ");

        myGraph.bypassInDepth(item -> {
            System.out.printf("The vertex is: [%2d], verticesConnected are : { ", item);
            LinkedList<Integer> children = myGraph.getChildren(item);

            for (int index : children) {
                System.out.printf("[%d] ", index);
            }
            System.out.println("}");
        });
        System.out.println();

        System.out.println("Результат метода (bypassInDepthWitRhecursion) is : ");

        myGraph.bypassInDepthWithRecursion(item -> {
            System.out.printf("The vertex is: [%2d], verticesConnected are : { ", item);
            LinkedList<Integer> children = myGraph.getChildren(item);

            for (int index : children) {
                System.out.printf("[%d] ", index);
            }
            System.out.println("}");
        });
    }
}
