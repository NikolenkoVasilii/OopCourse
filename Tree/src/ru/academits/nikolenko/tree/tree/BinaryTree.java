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

        TreeNode<E>[] removingNodeAndParent = getNodeAndParentByData(data);


        if (removingNodeAndParent == null) {
            return false;
        }

        TreeNode<E> removedNode = removingNodeAndParent[1];
        TreeNode<E> removedNodeParent = removingNodeAndParent[0];
        TreeNode<E> replacementNode =  getReplacementNode(removedNode);

        if (removedNode.hasNoChildren()) {
            if (removedNodeParent == null) {
                root = null;
            } else if (removedNodeParent.getLeft() == removedNode) {
                removedNodeParent.setLeft(getReplacementNode(removingNodeAndParent[1]));
            } else {
                removedNodeParent.setRight(removingNodeAndParent[1]);
            }

            size--;
            return true;
        }
        //нет обоих детей
        if (removedNode.hasNoBothChildren()) {
            //удаляемый узел  - правый сын
            if (removedNodeParent != null && removedNodeParent.getRight() == removedNode) {
                // имеет левого сына
                if (removedNode.getLeft() != null) {
                    //если имеет леввого сына делаем его правым сыном родителя
                    removedNodeParent.setRight(removedNode.getLeft());
                } else {
                    // если имеет правого сына делаем его правым сыном родителя
                    removedNodeParent.setRight(removedNode.getRight());
                }
                // если удаляемый узел - левый сын
            } else if (removedNodeParent != null) {
                //если имеет левого сына делаем его левым сыном родителя
                if (removedNode.getLeft() != null) {
                    removedNodeParent.setLeft(removedNode.getLeft());
                    // если имеет правого то делаем его левым сыном родителя
                } else {
                    // если имеет левого то делаем его левым
                    removedNodeParent.setLeft(removedNode.getRight());
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
        //ставим минимальный левый э
        minLeftNode.setLeft(removedNode.getLeft());
        minLeftParentNode.setLeft(minLeftNode.getRight());

        //проверяем родителя на нал
        if (removedNodeParent != null) {
            //проверка на корень
            if (removedNodeParent.getRight() == removedNode) {
                removedNodeParent.setRight(minLeftNode);
            } else {
                removedNodeParent.setLeft(minLeftNode);
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

    private TreeNode<E> getReplacementNode(TreeNode<E> deleteNode) {
        if (deleteNode.getRight() == null && deleteNode.getLeft() == null){
        return null;
        } else if (deleteNode.getLeft() == null) {
            return deleteNode.getRight();
        }else {
            return deleteNode.getLeft();
        }
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
}