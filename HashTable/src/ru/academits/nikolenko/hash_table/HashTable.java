package ru.academits.nikolenko.hash_table;

import java.util.*;
import java.util.ArrayList;

public class HashTable<E> implements Collection<E> {
    private static final int INITIAL_CAPACITY = 20;

    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[INITIAL_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Начальная вместимость должна быть больше 0, а переданное значение:" + capacity);
        }

        lists = (ArrayList<E>[]) new ArrayList[capacity];
    }

    private int getListIndex(Object object) {
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
        int listIndex = getListIndex(object);
        return lists[listIndex] != null && lists[listIndex].contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private int visitedItemsCount;
        private int arrayIndex;
        private int listIndex;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return visitedItemsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (initialModCount != modCount) {
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
        Object[] result = new Object[size];
        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E e : list) {
                    result[i] = list;
                    i++;
                }
            }
        }

        return result;
    }

            @Override
            public <T > T[]toArray(T[]array){
                if (array == null) {
                    throw new NullPointerException("Переданный массив - null");
                }

                if (array.length < size) {
                    //noinspection unchecked
                    return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
                }

                //noinspection SuspiciousSystemArraycopy
                System.arraycopy(toArray(), 0, array, 0, size);

                if (array.length > size) {
                    array[size] = null;
                }

                return array;
            }

            @Override
            public boolean add (E item){
                int index = getListIndex(item);

                if (lists[index] == null) {
                    lists[index] = new ArrayList<>();
                }

                lists[index].add(item);
                modCount++;
                size++;

                return true;
            }

            @Override
            public boolean remove (Object object){
                int index = getListIndex(object);

                if (lists[index] != null && lists[index].remove(object)) {
                    modCount++;
                    size--;
                    return true;
                }

                return false;
            }

            @Override
            public boolean containsAll (Collection < ? > collection){
                for (Object object : collection) {
                    if (!contains(object)) {
                        return false;
                    }
                }

                return true;
            }

            @Override
            public boolean addAll (Collection < ? extends E > collection){
                if (collection.isEmpty()) {
                    return false;
                }

                for (E item : collection) {
                    add(item);
                }

                return true;
            }

            @Override
            public boolean removeAll (Collection < ? > collection){
                if (collection == null) {
                    throw new NullPointerException("Коллекция равна null!");
                }

                size = 0;
                boolean isChanged = false;

                for (ArrayList<E> list : lists) {
                    if (list == null || list.isEmpty()) {
                        continue;
                    }

                    if (list.removeAll(collection)) {
                        isChanged = true;
                    }

                    size += list.size();
                }

                if (isChanged) {
                    modCount++;
                }

                return isChanged;
            }

            @Override
            public boolean retainAll (Collection < ? > collection){
                if (collection == null) {
                    throw new IllegalArgumentException("Коллекция равна null!");
                }

                size = 0;
                boolean isModCountChanged = false;

                for (ArrayList<E> list : lists) {
                    if (list == null || list.isEmpty()) {
                        continue;
                    }

                    if (list.retainAll(collection)) {
                        isModCountChanged = true;
                    }

                    size += list.size();
                }

                if (isModCountChanged) {
                    modCount++;
                }

                return isModCountChanged;
            }

            @Override
            public void clear () {
                if (size == 0) {
                    return;
                }

                for (ArrayList<E> list : lists) {
                    if (list != null) {
                        list.clear();
                    }
                }

                modCount++;
                size = 0;
            }

            @Override
            public String toString () {
                StringBuilder stringBuilder = new StringBuilder("{");
                stringBuilder.append(lists[0]);

                for (int i = 1; i < lists.length; i++) {
                    stringBuilder.append(", ").append(lists[i]);
                }

                stringBuilder.append('}');
                return stringBuilder.toString();
            }
        }
