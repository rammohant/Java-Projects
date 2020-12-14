/** William Coleman and Tara Ram Mohan
 * Lab 8
 */

package cmsc256;
import java.util.Arrays;

public class ArrayBasedStack<T> implements StackInterface<T> {
    private T[] data;
    private int topOfStack;
    private static final int INITIAL_CAPACITY = 5;

    public ArrayBasedStack(int capacity){
        if(capacity <= 0) {
            throw new IllegalArgumentException("Array initial size error.");
        }

        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[capacity];
        data = tempStack;
        topOfStack = -1;

    }

    public ArrayBasedStack() {
        //this(INITIAL_CAPACITY);
        clear();
    }


    private void expandArray() {
        int length = data.length;
        data = Arrays.copyOf(data,2*length);
    }

    private boolean isArrayFull(){
        return topOfStack == data.length;

    }


    @Override
    public void push(T newEntry) {
        topOfStack++;
        if (isArrayFull()){
            expandArray();
        }
        data[topOfStack] = newEntry;
    }

    @Override
    public T pop() {
        T top = peek();
        data[topOfStack] = null;
        topOfStack --;
        return top;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } return data[topOfStack];
    }

    @Override
    public boolean isEmpty() {
        return topOfStack < 0;
    }

    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[INITIAL_CAPACITY];
        data = tempStack;
        topOfStack = -1;

    }
}
