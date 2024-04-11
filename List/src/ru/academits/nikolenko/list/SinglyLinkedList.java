package ru.academits.nikolenko.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public T getFirst() {
        return head.getData();
    }

    public T getData(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Данные по этому индексу отсутствуют");
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
            throw new IllegalArgumentException("Данные по этому индексу отсутствуют");
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
            throw new IllegalArgumentException("Данные по этому индексу отсутствуют");
        }

        if (index == 0) {
            return removeFirst();
        }

        int counter = 0;
        T oldData = null;

        for (ListItem<T> p = head.getNext(), prev = head;
             p != null;
             prev = p, p = p.getNext()) {
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
        ListItem<T> p = new ListItem<>(data, head);
        head = p;
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
            if (counter == index) {
                p = new ListItem<>(data, p);
                prev.setNext(p);
                count++;
                break;
            } else {
                counter++;
            }
        }
    }

    public boolean remove(T data){
        boolean del = false;

        for (ListItem<T> p = head, prev = null;
             p != null;
             prev = p, p = p.getNext()) {
            if (p.getData().equals(data)){
                del = true;
                p= p.getNext();

                if (prev != null) {
                    prev.setNext(p);
                }

                count--;
                break;
            }
        }
        return del;
    }

    public T removeFirst(){
        T oldData = head.getData();
        head = head.getNext();
        count--;
        return oldData;
    }

    public void turn() {


    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        ListItem<T> p = head;
        for (; p.getNext() != null; p = p.getNext()) {
            stringBuilder.append(p.getData().toString()).append(", ");
        }

        stringBuilder.append(p.getData().toString()).append("}");
        return stringBuilder.toString();
    }
}
