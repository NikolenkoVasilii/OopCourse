package ru.academits.nikolenko.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public int getSize() {
        return size;
    }

    public E getFirst() {
        checkEmpty();

        return head.getData();
    }

    public E getData(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public E setData(int index, E data) {
        checkIndex(index);

        ListItem<E> item = getItem(index);
        E oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public E remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<E> previousItem = getItem(index - 1);
        ListItem<E> removedItem = previousItem.getNext();
        previousItem.setNext(removedItem.getNext());
        size--;

        return removedItem.getData();
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("По данному индексу нет элемента, допустимые границы от 0 до " + size + " включительно, а текущий индекс = " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
        size++;
    }

    public boolean remove(E data) {
        if (size == 0) {
            return false;
        }

        if (Objects.equals(data, head.getData())) {
            removeFirst();
            return true;
        }

        for (ListItem<E> previousItem = head, item = previousItem.getNext();
             item != null;
             previousItem = item, item = item.getNext()) {
            if (Objects.equals(data, item.getData())) {
                previousItem.setNext(item.getNext());
                size--;
                return true;
            }
        }

        return false;
    }

    public E removeFirst() {
        checkEmpty();

        E previousItem = head.getData();
        head = head.getNext();
        size--;

        return previousItem;
    }

    public void reverse() {
        if (size == 0) {
            return;
        }

        ListItem<E> item = head;
        ListItem<E> previousItem = null;

        while (item != null) {
            ListItem<E> nextItem = item.getNext();
            item.setNext(previousItem);
            previousItem = item;
            item = nextItem;
        }

        head = previousItem;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<E> item = head;

        while (item != null) {
            stringBuilder.append(item.getData()).append(", ");
            item = item.getNext();
        }

        int stringLength = stringBuilder.length();
        stringBuilder.delete(stringLength - 2, stringLength);

        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public SinglyLinkedList<E> copy() {
        if (size == 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<E> listCopy = new SinglyLinkedList<>();
        listCopy.addFirst(head.getData());
        ListItem<E> copiedItem = listCopy.head;

        for (ListItem<E> item = head.getNext(); item != null; item = item.getNext()) {
            copiedItem.setNext(new ListItem<>(item.getData()));
            copiedItem = copiedItem.getNext();
        }

        listCopy.size = size;
        return listCopy;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("По данному индексу нет элемента, допустимые границы от 0 до " + (size - 1) + " включительно, а текущий индекс = " + index);
        }
    }

    private void checkEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }
    }

    private ListItem<E> getItem(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }
}
