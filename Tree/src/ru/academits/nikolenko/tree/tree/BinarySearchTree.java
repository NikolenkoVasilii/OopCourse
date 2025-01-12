package ru.academits.nikolenko.tree.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<? super E> comparator;

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        comparator = null;
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            }

            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        Comparable<E> comparableData1 = (Comparable<E>) data1;
        return comparableData1.compareTo(data2);
    }

    public int getSize() {
        return size;
    }

    public boolean contains(E data) {
        return getNodeAndParentByData(data) != null;
    }

    @SuppressWarnings("unchecked")
    private TreeNode<E>[] getNodeAndParentByData(E data) {
        TreeNode<E> currentNode = root;
        TreeNode<E> parentNode = null;

        while (currentNode != null) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return (TreeNode<E>[]) new TreeNode[]{parentNode, currentNode};
            }

            parentNode = currentNode;

            if (comparisonResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return null;
                }
            }
        }

        return null;
    }

    public void add(E data) {
        if (size == 0) {
            root = new TreeNode<>(data);
            size++;
            return;
        }
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    size++;
                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    size++;
                    return;
                }
            }
        }
    }

    public boolean removeNodeByData(E data) {
        if (size == 0) {
            return false;
        }

        int comparisonResult = compare(root.getData(), data);

        if (comparisonResult == 0) {
            root = getSuccessor(root);
            size--;
            return true;
        }

        TreeNode<E> deleteNodeParent = null;
        TreeNode<E> deleteNode = root;
        boolean isLeftChild = false;
        
        while (comparisonResult != 0) {
            deleteNodeParent = deleteNode;


            if (comparisonResult > 0) {
                deleteNode = deleteNode.getLeft();
                isLeftChild = true;

                if (deleteNode == null) {
                    return false;
                }
            } else {
                deleteNode = deleteNode.getRight();
                isLeftChild = false;

                if (deleteNode == null) {
                    return false;
                }
            }

            comparisonResult = compare(deleteNode.getData(), data);
        }

        if (isLeftChild) {
            deleteNodeParent.setLeft(getSuccessor(deleteNode));
        } else {
            deleteNodeParent.setRight(getSuccessor(deleteNode));
        }

        size--;
        return true;
    }

    private TreeNode<E> getSuccessor(TreeNode<E> deleteNode) {
        if (deleteNode.getLeft() == null) {
            return deleteNode.getRight();
        }

        if (deleteNode.getRight() == null) {
            return deleteNode.getLeft();
        }

        TreeNode<E> replaceableNode = deleteNode.getRight();

        if (replaceableNode.getLeft() == null) {
            replaceableNode.setLeft(deleteNode.getLeft());
            return replaceableNode;
        }

        TreeNode<E> replaceableNodeParent = deleteNode;

        while (replaceableNode.getLeft() != null) {
            replaceableNodeParent = replaceableNode;
            replaceableNode = replaceableNode.getLeft();
        }

        replaceableNodeParent.setLeft(replaceableNode.getRight());

        replaceableNode.setRight(deleteNode.getRight());
        replaceableNode.setLeft(deleteNode.getLeft());

        return replaceableNode;
    }

    public int size() {
        return size;
    }

    public void bypassInWidth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void bypassInDepthUsingRecursion(Consumer<E> consumer) {
        bypassInDepthUsingRecursion(root, consumer);
    }

    private void bypassInDepthUsingRecursion(TreeNode<E> currentNode, Consumer<E> consumer) {
        if (currentNode == null) {
            return;
        }

        consumer.accept(currentNode.getData());

        if (currentNode.getLeft() != null) {
            bypassInDepthUsingRecursion(currentNode.getLeft(), consumer);
        }

        if (currentNode.getRight() != null) {
            bypassInDepthUsingRecursion(currentNode.getRight(), consumer);
        }
    }

    public void bypassInDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<E>> deque = new LinkedList<>();
        deque.push(root);

        while (!deque.isEmpty()) {
            TreeNode<E> currentNode = deque.pop();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                deque.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                deque.push(currentNode.getLeft());
            }
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        bypassInWidth(e -> stringBuilder.append(e).append(", "));

        int stringLength = stringBuilder.length();
        stringBuilder.delete(stringLength - 2, stringLength);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}