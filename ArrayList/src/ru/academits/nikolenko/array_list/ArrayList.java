package ru.academits.nikolenko.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Начальная вместимость должна быть >= 0, текущая вместимость " + initialCapacity);
        }

        items = (E[]) new Object[initialCapacity];
    }

    public ArrayList() {
        int capacity = 10;
        items = (E[]) new Object[capacity];
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int modCountIterator = ArrayList.this.modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Вы достигли конца коллекции");
            }

            if (modCount != modCountIterator) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода");
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
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E OldElement = items[index];
        items[index] = element;

        return OldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index != size) {
            checkIndex(index);
        }

        int capacity = items.length;

        if (size == capacity) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;

        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[size - 1] = null;

        size--;
        modCount++;

        return removedElement;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
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
    public java.util.ListIterator listIterator() {
        return null;
    }

    @Override
    public java.util.ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Iterator<E> iterator() {
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

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }


    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекции не существует");
        }

        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection collection) {
        if (index != size) {
            checkIndex(index);
        }

        if (collection.isEmpty()) {
            return false;
        }

        int collectionSize = collection.size();
        ensureCapacity(collectionSize + size);

        System.arraycopy(items, index, items, index + collectionSize, size - index);

        for (Object item : collection) {
            items[index] = (E) item;
            index++;
        }

        size += collection.size();
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекции не существует");
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
            throw new NullPointerException("Коллекция null");
        }

        boolean isRemoved = false;

        for (int i = size; i >= 0; i--) {
            if (!collection.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, 0, size, null);

        modCount += size;
        size = 0;
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            items = Arrays.copyOf(items, 10);
        } else {
            items = Arrays.copyOf(items, items.length * 2);
        }
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
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(items[0]);

        for (int i = 1; i < size; i++) {
            stringBuilder.append(", ").append(items[i]);
        }

        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Введенный индекс выходит за пределы коллекции, введенный индекс = " + index +
                    ". Введите индекс от 0 до = " + size);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        ArrayList<E> list = (ArrayList<E>) o;

        if (size == list.size) {
            for (int i = 0; i <= size; i++) {
                if (!items[i].equals(list.items[i])) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 3;
        hash = prime * hash + size;
        hash = hash + Arrays.hashCode(items);
        return hash;
    }
}
