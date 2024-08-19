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
        isEmpty();

        return head.getData();
    }

    public E getData(int index) {
        checkIndex(index);

        if (index == 0) {
            return getFirst();
        }

        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item.getData();
    }

    public E setData(int index, E data) {
        checkIndex(index);

        int i = 0;
        E oldData = head.getData();

        for (ListItem<E> item = head; item != null; item = item.getNext()) {
            if (i == index) {
                oldData = item.getData();
                item.setData(data);
                break;
            }

            i++;
        }

        return oldData;
    }

    public E remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        int i = 0;
        E oldData = null;

        for (ListItem<E> item = head.getNext(), previousItem = head;
             item != null;
             previousItem = item, item = item.getNext()) {
            if (i == index) {
                oldData = item.getData();
                previousItem.setNext(item.getNext());
                size--;
                break;
            }

            i++;
        }

        return oldData;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void add(int index, E data) {
        if (index < 0 || index >= size + 1) {
            throw new IndexOutOfBoundsException("индекс выходит за допустимые границы, допустимые границы от 0 до " + (size + 1) +
                    " а текущий индекс = " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        int i = 0;

        for (ListItem<E> item = head.getNext(), previousItem = head;
             previousItem != null;
             previousItem = item, item = item.getNext()) {
            if (i == index - 1) {
                item = new ListItem<>(data, item);
                previousItem.setNext(item);
                size++;
                break;
            }

            i++;
        }
    }

    public boolean remove(E data) {
        isEmpty();

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
        if (head == null) {
            throw new NullPointerException("Список пуст");
        }
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
            return "[ ]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<E> item = head;
        stringBuilder.append(item.getData());

        while (item.getNext() != null) {
            item = item.getNext();
            stringBuilder.append(", ").append(item.getData());
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public SinglyLinkedList<E> getCopyList() {
        isEmpty();

        SinglyLinkedList<E> copy = new SinglyLinkedList<>();
        ListItem<E> item = head;

        copy.head = new ListItem<>(item.getData());
        ListItem<E> copiedItem = copy.head;

        for (int i = 1; i < size; i++) {
            item = item.getNext();
            copiedItem.setNext(new ListItem<>(item.getData()));
            copiedItem = copiedItem.getNext();
        }

        copy.size = size;
        return copy;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("По данному индексу нет элемента, допустимые границы от 0 до " + size +
                    " а текущий индекс = " + index);
        }
    }

    private void isEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }
    }
}
