package ru.academits.nikolenko.arrayList.list;

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
        ListItem<E> deletedItem = previousItem.getNext();
        previousItem.setNext(deletedItem.getNext());
        size--;

        return deletedItem.getData();
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void add(int index, E data) {
        size++;
        checkIndex(index);
        ListItem<E> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
    }


    public boolean remove(E data) {
        checkEmpty();

        if (Objects.equals(data, head.getData())) {
            removeFirst();
            return true;
        }

        for (ListItem<E> item = head, previousItem = null;
             item != null;
             previousItem = item, item = item.getNext()) {
            if (item.getData().equals(data)) {
                item = item.getNext();

                if (previousItem != null) {
                    previousItem.setNext(item);
                }
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
        ListItem<E> item = head;
        ListItem<E> previousItem = null;

        for (ListItem<E> next = item.getNext(); next != null; previousItem = item, item = next, next = next.getNext()) {
            item.setNext(previousItem);
        }

        item.setNext(previousItem);
        head = item;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        ListItem<E> item = head;
        stringBuilder.append(item.getData());

        while (item.getNext() != null) {
            item = item.getNext();
            stringBuilder.append(", ").append(item.getData());
        }

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
            E data = item.getData();
            copiedItem.setNext(new ListItem<>(data));
            copiedItem = copiedItem.getNext();
        }

        listCopy.size = size;
        return listCopy;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("По данному индексу нет элемента, допустимые границы от 0 до " + size + "включительно," +
                    " а текущий индекс = " + index);
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
