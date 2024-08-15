package ru.academits.nikolenko.treeNode;

public class TreeNode <T>{
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    public TreeNode (T data){
        this.data =data;
    }

    public T getData() {
        return data;
    }

    public boolean hasNotChildren() {
        return getLeft() == null && getRight() == null;
    }

    public boolean hasNotBothChildren() {
        return getLeft() == null || getRight() == null;
    }

    public  void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft(){
        return left;
    }

    public TreeNode<T> getRight(){
        return right;
    }

    public void setLeft(TreeNode<T> data){
        left = data;
    }

    public void setRight(TreeNode<T> data){
        right = data;
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
