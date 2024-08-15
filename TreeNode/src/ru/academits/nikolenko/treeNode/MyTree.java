package ru.academits.nikolenko.treeNode;

public class MyTree<T> {
    private TreeNode<T> root;
    private int size;
    private Comporator<T> comporator;

    private MyTree(T rootData) {
        size = 1;
        root = new TreeNode<>(rootData);
        comporator = createComporator();

    }

    private Comporator<T> createComporator(){
        return (item1,item2)
    }

}
