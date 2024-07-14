package org.example.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;


public class CustomArrayList<T> {
    private Object[] array;
    private int size;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public CustomArrayList() {
        this.array = new Object[10];
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param obj the element to add
     * @return true (as specified by Collection.add(E))
     */
    public Boolean add(T obj) {
        increaseArr();
        array[size++] = obj;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right
     * (adds one to their indices).
     *
     * @param index – index at which the specified element is to be inserted element – element to be inserted
     * @param obj   the element to add
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index > size())
     */
    public void add(int index, T obj) throws IndexOutOfBoundsException {
        checkIndex(index);
        increaseArr();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = obj;
        size++;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are
     * returned by the specified collection's Iterator.
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the specified collection is this list,
     * and this list is nonempty.)
     *
     * @param collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    public Boolean addAll(Collection<? extends T> collection) {
        if (collection.toArray().length == 0) return false;
        for (T item : collection) {
            add(item);
        }
        return true;
    }

    /**
     * Removes all of the elements from this list. The list will be empty after this call returns.
     */
    public void clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public T get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        @SuppressWarnings("unchecked") T res = (T) array[index];
        return res;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public Boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        @SuppressWarnings("unchecked") T oldValue = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return oldValue;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param obj element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    public boolean remove(T obj) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(obj)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Sorts this list according to the order induced by the specified comparator.
     *
     * @param comparator the comparator to determine the order of the list
     */
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
