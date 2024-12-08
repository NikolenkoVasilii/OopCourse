package ru.academits.nikolenko.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount;

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Начальная вместимость должна быть >= 0, текущая вместимость " + INITIAL_CAPACITY);
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[INITIAL_CAPACITY];
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Вы достигли конца коллекции");
            }

            if (modCount != initialModCount) {
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
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        checkIndexToAdd(index);

        int capacity = items.length;

        if (size == capacity) {
            if (capacity == 0) {
                //noinspection unchecked
                items = (E[]) new Object[INITIAL_CAPACITY];
            } else {
                increaseCapacity();
            }
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;

        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[size - 1] = null;

        size--;
        modCount++;

        return removedItem;
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
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
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
            //noinspection unchecked
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
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndexToAdd(index);

        if (collection.isEmpty()) {
            return false;
        }

        int collectionSize = collection.size();
        ensureCapacity(collectionSize + size);

        System.arraycopy(items, index, items, index + collectionSize, size - index);

        int i = index;

        for (E item : collection) {
            items[i] = item;
            i++;
        }

        size += collection.size();
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не должна быть null");
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
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
            throw new NullPointerException("Коллекция равна null");
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
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

        modCount++;
        size = 0;
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            //noinspection unchecked
            items = (E[]) new Object[INITIAL_CAPACITY];
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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked")
        ArrayList<E> list = (ArrayList<E>) o;

        if (size != list.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(items[i], list.items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int hash = 1;
        hash = prime * hash + size;

        for (int i = 0; i < size; i++) {
            if (items[i] == null) {
                hash *= prime;
                continue;
            }

            hash = prime * hash + items[i].hashCode();
        }

        return hash;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданный индекс выходит за пределы коллекции, переданный индекс = " + index +
                    ". Индекс должен быть от 0 до " + (size - 1) + " включительно");
        }
    }

    private void checkIndexToAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " некорректный. Допустимый диапазон от 0 до " + size + " включительно");
        }
    }
}