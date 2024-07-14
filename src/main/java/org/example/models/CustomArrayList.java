package org.example.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<T> {
    private Object[] array;
    private int size;

    public CustomArrayList() {
        this.array = new Object[10];
    }

    public Boolean add(T obj) {
        increaseArr();
        array[size++] = obj;
        return true;
    }

    public void add(int index, T obj) throws IndexOutOfBoundsException {
        checkIndex(index);
        increaseArr();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = obj;
        size++;
    }

    public Boolean addAll(Collection<? extends T> collection) {
        if (collection.toArray().length == 0)
            return false;
        for (T item : collection) {
            add(item);
        }
        return true;
    }

    public void clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        @SuppressWarnings("unchecked") T res = (T) array[index];
        return res;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public T remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        @SuppressWarnings("unchecked") T oldValue = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return oldValue;
    }

    public boolean remove(T obj) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(obj)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void sort(Comparator<? super T> comparator) {
        quicksort(0, size - 1, comparator);
    }

    private void quicksort(int start, int end, Comparator<? super T> comparator) {
        if (start >= end) return;
        int rightStart = partOfSort(start, end, comparator);
        quicksort(start, rightStart - 1, comparator);
        quicksort(rightStart, end, comparator);
    }

    @SuppressWarnings("unchecked")
    private int partOfSort(int left, int right, Comparator<? super T> comparator) {
        T pivot = (T) array[(left + right) / 2];
        while (left <= right) {
            while (comparator.compare((T) array[left], pivot) < 0) left++;
            while (comparator.compare((T) array[right], pivot) > 0) right--;
            if (left <= right) {
                T temp = (T) array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index > size || index < 0) throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void increaseArr() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index " + index + " out of bounds for length " + size;
    }
}
