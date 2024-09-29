package ru.academits.nikolenko.tree.treeNode;


import java.util.*;
import java.util.function.Consumer;

public class MyTree<E> {
    private TreeNode<E> root;
    private int size;
    private Comparator<E> comparator;

    public MyTree(E rootData) {
        size = 1;
        root = new TreeNode<>(rootData);
        comparator = createComparator();
    }

    private Comparator<E> createComparator() {
        return (item1, item2) -> {
            if (item1 == null || item2 == null) {
                if (item1 == null && item2 == null) {
                    return 0;
                } else if (item1 == null) {
                    return -1;
                }
                return 1;
            }

            return ((Comparable<E>) item1).compareTo(item2);
        };
    }

    public MyTree(E rootData, Comparator<E> comparator) {
        size = 1;
        root = new TreeNode<>(rootData);
        this.comparator = comparator;
    }

    public MyTree(Comparator<E> comparator) {
        size = 0;
        root = null;
        this.comparator = comparator;
    }

    public MyTree() {
        root = null;
        size = 0;
        comparator = createComparator();
    }

    public int getSize() {
        return this.size;
    }

    public boolean contains(E value) {
        TreeNode[] arrayNodeAndParent = getNodeAndParentByValue(value);
        return arrayNodeAndParent != null;
    }

    private TreeNode<E>[] getNodeAndParentByValue(E value) {
        if (size == 0) {
            throw new IllegalArgumentException("This tree is empty!");
        }

        TreeNode<E> currentNode = root;
        TreeNode<E> parentNode = null;

        while (currentNode != null) {
            int resultCompareValueAndCurrentNodeData = comparator.compare(value, currentNode.getData());

            if (resultCompareValueAndCurrentNodeData == 0) {
                if (parentNode == null) {
                    return (TreeNode<E>[]) new TreeNode[]{null, root};
                }

                return (TreeNode<E>[]) new TreeNode[]{parentNode, currentNode};
            }

            if (resultCompareValueAndCurrentNodeData < 0) {
                if (currentNode.getLeft() != null) {
                    parentNode = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRight() != null) {
                    parentNode = currentNode;
                    currentNode = currentNode.getRight();
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public void addNode(E value) {
        if (size == 0) {
            root = new TreeNode<>(value);
            size++;
            return;
        }
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (comparator.compare(value, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(value));
                    size++;
                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(value));
                    size++;
                    return;
                }
            }
        }
    }

    private TreeNode<E>[] getMinLeftNodeAndParent(TreeNode<E> node) {
        TreeNode<E> minLeftNode = node.getRight();
        TreeNode<E> minLeftParentNode = node;
        TreeNode<E> currentMinLeftNode;

        while (minLeftNode.getLeft() != null) {
            currentMinLeftNode = minLeftNode.getLeft();
            minLeftParentNode = minLeftNode;
            minLeftNode = currentMinLeftNode;
        }

        return (TreeNode<E>[]) new TreeNode[]{minLeftParentNode, minLeftNode};
    }

    public boolean removeNodeByValue(E value) {
        if (size == 0) {
            return false;
        }

        TreeNode<E>[] arrayRemoving = getNodeAndParentByValue(value);

        if (arrayRemoving == null) {
            return false;
        }

        TreeNode<E> removedNode = arrayRemoving[1];
        TreeNode<E> parentRemovedNode = arrayRemoving[0];


        if (removedNode.hasNotChildren()) {
            if (parentRemovedNode == null) {
                root = null;
                size = 0;
                return true;
            } else if (parentRemovedNode.getLeft() == removedNode) {
                parentRemovedNode.setLeft(null);
            } else {
                parentRemovedNode.setRight(null);
            }
            size--;
            return true;
        }


        if (removedNode.hasNotBothChildren()) {
            if (parentRemovedNode != null && parentRemovedNode.getRight() == removedNode) {
                if (removedNode.getLeft() != null) {
                    parentRemovedNode.setRight(removedNode.getLeft());
                } else {
                    parentRemovedNode.setRight(removedNode.getRight());
                }
            } else if (parentRemovedNode != null) {
                if (removedNode.getLeft() != null) {
                    parentRemovedNode.setLeft(removedNode.getLeft());
                } else {
                    parentRemovedNode.setLeft(removedNode.getRight());
                }
            } else if (root.getLeft() == null) {
                root = root.getRight();
            } else {
                root = root.getLeft();
            }
            size--;
            return true;
        }

        TreeNode<E>[] arrayMinLeft = getMinLeftNodeAndParent(removedNode);
        TreeNode<E> minLeftParentNode = arrayMinLeft[0];
        TreeNode<E> minLeftNode = arrayMinLeft[1];
        minLeftNode.setLeft(removedNode.getLeft());
        minLeftParentNode.setLeft(minLeftNode.getRight());

        if (parentRemovedNode != null) {
            if (parentRemovedNode.getRight() == removedNode) {
                parentRemovedNode.setRight(minLeftNode);
            } else {
                parentRemovedNode.setLeft(minLeftNode);
            }
            if (minLeftParentNode != removedNode) {
                minLeftNode.setRight(removedNode.getRight());
            }
        } else {
            root = minLeftNode;
            root.setLeft(removedNode.getLeft());
        }

        if (minLeftParentNode != removedNode) {
            minLeftNode.setRight(removedNode.getRight());
        }
        size--;
        return true;
    }

    public void bypassInWidth(Consumer<E> method) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentItem = queue.remove();
            method.accept(currentItem.getData());

            if (currentItem.getLeft() != null) {
                queue.add(currentItem.getLeft());
            }
            if (currentItem.getRight() != null) {
                queue.add(currentItem.getRight());
            }
        }
    }

    public void bypassInDepthUsingRecursion(Consumer<E> method) {
        if (root == null) {
            return;
        }
        visitNode(root, method);
    }

    private void visitNode(TreeNode<E> currentNode, Consumer<E> method) {
        if (currentNode == null) {
            return;
        }

        method.accept(currentNode.getData());

        if (currentNode.getLeft() != null) {
            visitNode(currentNode.getLeft(), method);
        }
        if (currentNode.getRight() != null) {
            visitNode(currentNode.getRight(), method);
        }
    }

    public void bypassInDepth(Consumer<E> method) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<E>> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentItem = stack.removeLast();
            method.accept(currentItem.getData());

            if (currentItem.getRight() != null) {
                stack.add(currentItem.getRight());
            }
            if (currentItem.getLeft() != null) {
                stack.add(currentItem.getLeft());
            }
        }
    }
}