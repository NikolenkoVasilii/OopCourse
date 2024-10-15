package ru.academits.nikolenko.tree.tree;

class TreeNode<E> {
    private TreeNode<E> left;
    private TreeNode<E> right;
    private E data;

    public TreeNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public boolean hasNoChildren() {
        return left == null && right == null;
    }

    public boolean hasNoBothChildren() {
        return left == null || right == null;
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
}