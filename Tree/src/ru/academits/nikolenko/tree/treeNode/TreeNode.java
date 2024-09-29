package ru.academits.nikolenko.tree.treeNode;

public class TreeNode<E> {
    private TreeNode<E> left;
    private TreeNode<E> right;
    private E data;

    public TreeNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public boolean hasNotChildren() {
        return getLeft() == null && getRight() == null;
    }

    public boolean hasNotBothChildren() {
        return getLeft() == null ||getRight() == null;
    }

    public void setData(E data) {
        this.data = data;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        if (left == null) {
            if (right == null) {
                return String.format("[DATA:%3s, L:   , R:    ]", data);
            } else {
                return String.format("[DATA:%3s, L:   , R:%3s ]", data, right.getData());
            }
        }
        if (right == null) {
            return String.format("[DATA:%3s, L:%3s, R:    ]", data, left.getData());
        }
        return String.format("[DATA:%3s, L:%3s, R:%3s ]", data, left.getData(), right.getData());
    }
}