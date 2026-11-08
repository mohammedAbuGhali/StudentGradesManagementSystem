package studentgradesmanagementsystem.model;

public class ArrayBag<T> {

    private T[] bag;
    private int size;

    public ArrayBag(int capacity) {
        bag = (T[]) new Object[capacity];
        size = 0;
    }

    public boolean add(T item) {

        if (size >= bag.length)
            return false;

        bag[size++] = item;
        return true;
    }

    public boolean contains(T item) {

        for (int i = 0; i < size; i++) {
            if (bag[i].equals(item))
                return true;
        }

        return false;
    }

    public int frequencyOf(T item) {

        int count = 0;

        for (int i = 0; i < size; i++) {
            if (bag[i].equals(item))
                count++;
        }

        return count;
    }

    public boolean remove(T item) {

        for (int i = 0; i < size; i++) {

            if (bag[i].equals(item)) {

                bag[i] = bag[size - 1];
                bag[size - 1] = null;
                size--;

                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}