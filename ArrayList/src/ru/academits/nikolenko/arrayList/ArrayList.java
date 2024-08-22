package ru.academits.nikolenko.arrayList;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int capacity;
    private int modCount;


    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IndexOutOfBoundsException("Начальная вместимость должна быть больше 0");
        }

        //noinspection unchecked
        items = (E[]) new Object[initialCapacity];
    }

    public ArrayList() {
        capacity = 10;
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int modCount = ArrayList.this.modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Вы достигли конца коллекции");
            }

            if (modCount != ArrayList.this.modCount) {
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

        E removedElement = items[index];
        items[index] = element;

        modCount++;
        return removedElement;
    }

    @Override
    public void add(int index, E element) {
        checkPreviousIndex(index);

        if (size == items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;

        size++;
        modCount++;
    }

    @Override
    public void addFirst(E element) {
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
    public E getFirst() {
        return items[0];
    }

    @Override
    public E getLast() {
        return items[size];
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);

        size--;
        modCount++;

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
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            throw new NoSuchElementException("Коллекции не существует");
        }

        int i = 0;
        ensureCapacity(size + collection.size());

        for (E collectionsElement : collection) {
            items[i + size] = collectionsElement;
            i++;
        }

        size += collection.size();
        modCount += collection.size();

        return !collection.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (collection == null) {
            throw new NoSuchElementException("Коллекции не существует");
        }

        checkIndex(index);

        if (index == size) {
            return addAll(collection);
        }

        ensureCapacity(size + collection.size());
        System.arraycopy(items, index, items, index + collection.size(), size - index);

        for (E collectionElement : collection) {
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
                modCount--;
            }
        }

        return wasRetainedAll;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                items[i] = null;
            }
        }

        modCount += size;
        size = 0;
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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Введенный индекс выходит за пределы коллекции, введенный индекс = " + index +
                    ". Введите индекс от 0 до = " + size);
        }
    }

    private void checkPreviousIndex(int index) {
        if (index < 0 || index - 1 >= size) {
            throw new IndexOutOfBoundsException("Индекс " + (index - 1) + " некорректный. Допустимый диапазон от 0 до " + (size - 2) + " включительно");
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

        //noinspection rawtypes
        ArrayList list = (ArrayList) o;
        return size == list.size &&
                Arrays.equals(Arrays.copyOf(items, size), Arrays.copyOf(list.items, list.size));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 3;
        hash = prime * hash + Arrays.hashCode(Arrays.copyOf(items, size));
        return hash;
    }
}
