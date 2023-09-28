import java.util.Arrays;
import java.util.Collection;

public class DynamicArray<T> {

    private Object[] elements;

    private static final byte DEFAULT_SIZE = 10;

    private int size = 0;

    public DynamicArray(int size) {
        elements = new Object[size];
    }

    public DynamicArray() {
        elements = new Object[DEFAULT_SIZE];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(T element) {
        if (isArrayFull()) {
            resize();
        }

        elements[size++] = element;
        return true;
    }

    public boolean isArrayFull() {
        return size == elements.length;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        return (T) elements[index];
    }

    public boolean remove(T element) {
        if (isEmpty()) {
            throw new IllegalStateException("Can't remove from an empty list");
        }
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals(element)) {
                for (int j = i; j < elements.length - 1; j++) {
                    elements[j] = elements[j + 1];
                }

                elements[--size] = null;
                return true;
            }

        }
        return false;
    }


    private void resize() {
        Object[] temp = new Object[elements.length * 2];

        for (int i = 0; i < elements.length; i++) {
            temp[i] = elements[i];
        }

        elements = temp;
    }


    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        elements[index] = element;
        return element;
    }


    public int size() {
        return size;
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(T element) {

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        elements = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public void addAll(Collection<? extends T> collection) {

        for (T element : collection) {

            add(element);
        }

    }

    public boolean insert(int index, T element) {
        if (isArrayFull()) {
            resize();
        } else if (index < 0 || index > size - 1) {
            return false;
        }

        int i = size - 1;
        while (i >= index) {
            elements[i + 1] = elements[i];
            i--;
        }

        elements[index] = element;
        size++;
        return true;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
