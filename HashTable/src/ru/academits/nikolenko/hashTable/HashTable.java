package ru.academits.nikolenko.hashTable;


import ru.academits.nikolenko.arrayList.ArrayList;

import java.util.*;


public class HashTable<E> implements Collection<E> {
    private ArrayList<E>[] lists;
    private int size;
    private final int capacity = 20;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[capacity];
    }

    public HashTable(int capacity) {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[capacity];
    }

    private int getIndexList(Object object) {
        if (object == null) {
            return 0;
        }
        return Math.abs(object.hashCode() % lists.length);
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
    public boolean contains(Object object) {
        int indexList = getIndexList(object);
        return lists[indexList] != null && lists[indexList].contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private int visitedItemsCount;
        private int arrayIndex;
        private int listIndex;
        private final int modCount = HashTable.this.modCount;

        @Override
        public boolean hasNext() {
            return visitedItemsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != HashTable.this.modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода");
            }

            while (lists[arrayIndex] == null || lists[arrayIndex].isEmpty()) {
                arrayIndex++;
            }

            E result = lists[arrayIndex].get(listIndex);

            if (listIndex == lists[arrayIndex].size() - 1) {
                ++arrayIndex;
                listIndex = -1;
            }

            listIndex++;
            visitedItemsCount++;

            return result;
        }
    }


    @Override
    public Object[] toArray() {
        Object[] result = new Object[lists.length];
        int i = 0;

        for (Object item : lists) {
            result[i] = item;
            i++;
        }

        return result;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < lists.length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(lists, lists.length);
        }

        System.arraycopy(toArray(), 0, array, 0, lists.length);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        int index = getIndexList(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(item);
        modCount++;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndexList(object);

        if (lists[index] != null && lists[index].remove(object)) {
            modCount++;
            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (size == 0 || collection.isEmpty()) {
            return false;
        }

        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (E item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        size = 0;
        boolean mod = false;

        for (int i = 0; i < capacity; i++) {
            if (lists[i] != null) {
                if (lists[i].removeAll(collection)) {
                    mod = true;
                }

                size += lists[i].size();
            }
        }

        modCount += size;
        return mod;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        size = 0;
        boolean mod = false;

        for (int i = 0; i < capacity; i++) {
            if (lists[i] != null) {
                if (lists[i].retainAll(collection)) {
                    mod = true;
                }

                size += lists[i].size();
            }
        }

        modCount += size;
        return mod;
    }

    @Override
    public void clear() {
        if (size != 0) {
            for (int i = 0; i < capacity; i++) {
                if (lists[i] != null) {
                    lists[i].clear();
                }
            }
        }

        modCount++;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append(lists[0]);

        for (int i = 1; i < lists.length; i++) {
            stringBuilder.append(",").append(lists[i]);
        }

        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
