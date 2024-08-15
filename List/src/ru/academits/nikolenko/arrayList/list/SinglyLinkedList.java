package ru.academits.nikolenko.arrayList.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T getFirst() {

        return head.getData();
    }

    public T getData(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("По данному индексу нет элемента");
        }

        if (index == 0) {
            return getFirst();
        }

        int counter = 0;
        T data = head.getData();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (counter == index) {
                data = p.getData();
                break;
            } else {
                counter++;
            }
        }

        return data;
    }

    public T setData(int index, T data) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("По данному индексу нет элемента");
        }

        int counter = 0;
        T oldData = head.getData();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (counter == index) {
                oldData = p.getData();
                p.setData(data);
                break;
            } else {
                counter++;
            }
        }
        return oldData;
    }

    public T remove(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("По данному индексу нет элемента");
        }

        if (index == 0) {
            return removeFirst();
        }

        int counter = 0;
        T oldData = null;

        for (ListItem<T> p = head.getNext(), prev = head;
             p != null; prev = p, p = p.getNext()) {
            if (counter == index) {
                oldData = p.getData();
                prev.setNext(p.getNext());
                count--;
                break;
            } else {
                counter++;
            }
        }

        return oldData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void add(int index, T data) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Данные по этому индексу отсутствуют");
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        int counter = 0;

        for (ListItem<T> p = head.getNext(), prev = head;
             prev != null;
             prev = p, p = p.getNext()) {
            if (counter == index - 1) {
                p = new ListItem<>(data, p);
                prev.setNext(p);
                count++;
                break;
            } else {
                counter++;
            }
        }
    }

    public boolean remove(T data) {
        boolean isRemoved = false;

        for (ListItem<T> p = head, prev = null;
             p != null;
             prev = p, p = p.getNext()) {
            if (p.getData().equals(data)) {
                isRemoved = true;
                p = p.getNext();

                if (prev != null) {
                    prev.setNext(p);
                }

                count--;
                break;
            }
        }
        return isRemoved;
    }

    public T removeFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException("Cписок пуст");
        }
        T prev = head.getData();
        head = head.getNext();
        count--;
        return prev;
    }

    public void turn() {
        ListItem<T> p = head;
        ListItem<T> prev = null;
        for (ListItem<T> next = p.getNext(); next != null; prev = p, p = next, next = next.getNext()) {
            p.setNext(prev);
        }
        p.setNext(prev);
        head = p;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        ListItem<T> p = head;
        for (; p.getNext() != null; p = p.getNext()) {
            stringBuilder.append(p.getData().toString()).append(", ");
        }

        stringBuilder.append(p.getData().toString()).append('}');
        return stringBuilder.toString();
    }

    public SinglyLinkedList getCopy() {
        SinglyLinkedList copy = new SinglyLinkedList();

        if (head == null) {
            return copy;
        }

        ListItem<T> copyItem = new ListItem<>(head.getData());
        ListItem<T> next = head.getNext();
        copy.head = copyItem;

        int i = 1;
        while (i < getSize()) {
            copyItem.setNext(new ListItem<>(next.getData()));
            next = next.getNext();
            copyItem = copyItem.getNext();
            i++;
        }

        return copy;
    }
}
