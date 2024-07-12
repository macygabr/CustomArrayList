package org.example.models;

public class CustomArrayList<T> {
    private Object[] array;
    private int size;

    public CustomArrayList() {
        this.array = new Object[10];
    }


    public void add(T obj) {
        array[size] = obj;
        size++;
    }

    public void addAll(T obj) {
    }

    public void clear(T obj) {
    }

    public T get(int index) {
        return (T) array[index];
    }

    public Boolean isEmpty() {
        return false;
    }

    public void remove(int index) {
        size--;
    }

    public void remove(T obj) {
        size--;
    }

    public void sort() {

    }

    public int size() {
        return size;
    }
}
