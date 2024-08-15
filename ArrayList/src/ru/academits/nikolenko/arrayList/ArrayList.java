package ru.academits.nikolenko.arrayList;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private final int capacity = 10;

    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IndexOutOfBoundsException("Начальная вместимость должна быть больше 0");
        }

        items = (T[]) new Object[initialCapacity];
    }

    public ArrayList() {
        items = (T[]) new Object[capacity];
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Вы достигли конца коллекции");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Введенный индекс выходит за границы коллекции");
        }

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Введенный индекс выходит за границы коллекции");
        }

        T removedElement = items[index];
        items[index] = element;
        return removedElement;
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Введенный индекс выходит за границы коллекции");
        }

        if (index == size) {
            addLast(element);
            return;
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        size++;
        items[index] = element;
    }

    @Override
    public void addFirst(T element) {
        if (size > items.length) {
            increaseCapacity();
        }

        for (int index = size; index >= 1; index--) {
            items[index] = items[index - 1];
        }

        items[0] = element;
        size++;
    }

    @Override
    public void addLast(T element) {
        if (size > items.length) {
            increaseCapacity();
        }

        items[size-1] = element;
    }

    @Override
    public T getFirst() {
        return items[0];
    }

    @Override
    public T getLast() {
        return items[size];
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Введенный индекс выходит за границы коллекции");
        }

        T removedElement = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        size--;
        return removedElement;
    }

    @Override
    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], object)) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(T element) {
        addLast(element);
        return true;
    }


    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NoSuchElementException("Коллекции не существует");
        }

        for (Object element : collection) {
            if (!this.contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            throw new NoSuchElementException("Коллекции не существует");
        }

        int i = 0;
        ensureCapacity(size + collection.size());

        for (T collectionsElemet : collection) {
            items[i + size] =collectionsElemet;
            i++;
        }

        size += collection.size();

        return !collection.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (collection == null) {
            throw new NoSuchElementException("Коллекции не существует");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Введенный индекс выходит за пределы коллекции, введенный индекс = " + index +
                    "Введите индекс от 0 до = " + size);
        }

        if (index == size) {
            return addAll(collection);
        }

        ensureCapacity(size + collection.size());
        System.arraycopy(items, index, items, index + collection.size(), size - index);

        for (T collectionElement : collection) {
            items[index] = collectionElement;
            index++;
        }

        size = collection.size();

        return !collection.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NoSuchElementException("Коллекции не существует");
        }

        boolean isRemoved = false;

        for (int i = size() - 1; i >= 0; i--) {
            if (collection.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NoSuchElementException("Коллекция null");
        }

        boolean wasRetainedAll = false;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
                wasRetainedAll = true;
                i--;
            }
        }

        return wasRetainedAll;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                items[i] = null;
            }
        }
    }

    @Override
    public List<T> reversed() {
        return List.super.reversed();
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (items.length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{ }";
        }
        StringBuilder result = new StringBuilder("{ ");

        for (int i = 0; i < size - 1; i++) {
            String string = String.format("%s, ", items[i]);
            result.append(string);
        }

        String string = String.format(" %s }", items[size - 1]);
        return result.append(string).toString();
    }

}
