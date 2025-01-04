package ru.academits.nikolenko.tree.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<E> comparator;

    public BinaryTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinaryTree() {
        comparator = createComparator();
    }

    @SuppressWarnings("unchecked")
    private Comparator<E> createComparator() {
        return (node1, node2) -> {
            if (node1 == null && node2 == null) {
                return 0;
            }

            if (node1 == null) {
                return -1;
            }

            if (node2 == null) {
                return 1;
            }

            return ((Comparable<E>) node1).compareTo(node2);
        };
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
            int comparisonResult = comparator.compare(data, currentNode.getData());

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
            if (comparator.compare(data, currentNode.getData()) < 0) {
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

    @SuppressWarnings("unchecked")
    private TreeNode<E>[] getMinLeftNodeAndParent(TreeNode<E> node) {
        TreeNode<E> minLeftNode = node.getRight();
        TreeNode<E> minLeftParentNode = node;

        while (minLeftNode.getLeft() != null) {
            minLeftParentNode = minLeftNode;
            minLeftNode = minLeftNode.getLeft();

        }

        return (TreeNode<E>[]) new TreeNode[]{minLeftParentNode, minLeftNode};
    }

    public boolean removeNodeByData(E data) {

        if (size == 0) {
            return false;
        }

        int comparisonResult = comparator.compare(root.getData(), data);

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

                if (deleteNode == null) {
                    return false;
                }

                isLeftChild = true;
            } else {
                deleteNode = deleteNode.getRight();

                if (deleteNode == null) {
                    return false;
                }

                isLeftChild = false;
            }

            comparisonResult = comparator.compare(deleteNode.getData(), data);
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
        if (root == null) {
            return;
        }

        visitNode(root, consumer);
    }

    private void visitNode(TreeNode<E> currentNode, Consumer<E> consumer) {
        if (currentNode == null) {
            return;
        }

        consumer.accept(currentNode.getData());

        if (currentNode.getLeft() != null) {
            visitNode(currentNode.getLeft(), consumer);
        }

        if (currentNode.getRight() != null) {
            visitNode(currentNode.getRight(), consumer);
        }
    }

    public void bypassInDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<E>> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentNode = stack.removeLast();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.add(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                stack.add(currentNode.getLeft());
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