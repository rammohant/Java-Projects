package cmsc256;
import bridges.connect.Bridges;
import bridges.base.SLelement;
import java.util.EmptyStackException;

/** CustomStack
 * Tara Ram Mohan
 * Project 4
 * Purpose: Implement a singly-linked stack using the BRIDGES SLelement<E>
 * CMSC 256 Spring 2020
*/

public class CustomStack<E> implements StackInterface<E> {

   /**
   * Variables for CustomStack class
   */
   private SLelement topElement;  //Should not be static, not shared between all instances!!
   int size = 0;

    /**
    * Default constructor for CustomStack class
    */
   public CustomStack() {
        topElement = null;
        size = 0;
    }

     /** Adds a new entry to the top of this stack.
         @param newEntry  An object to be added to the stack. */
    @Override
    public void push(E newEntry) {
        String value = null; 
        if (newEntry == null) {
            throw new IllegalArgumentException("You cannot push null into this stack.");
        } else {
           value = newEntry.toString();
        }
        SLelement<E> newNode = (new SLelement<E>(value,newEntry));

        if (isEmpty()) {
            topElement = newNode;
        } else {
            newNode.setNext(topElement);
            topElement = newNode;
        }
        size++;
    }

    /** Removes and returns this stack's top entry.
     @return  The object at the top of the stack.
     @throws  EmptyStackException if the stack is empty before the operation. */
    @Override
    public E pop() {
        E top = null;  // Might throw EmptyStackException

        if (isEmpty()) {
            throw new cmsc256.EmptyStackException();

        } else {
            SLelement<E> nextElement = topElement.getNext();
            if (nextElement == null) {
                topElement = nextElement;
            } else {
                SLelement<E> current = topElement;
                top = current.getValue();
                topElement = nextElement;
            }
           size--;
        }
        return top;
    }

    /** Retrieves this stack's top entry.
     @return  The object at the top of the stack.
     @Override
     @throws  EmptyStackException if the stack is empty. */
    public E peek() {
        if (isEmpty())
            throw new cmsc256.EmptyStackException();
        else {
             SLelement<E> top = topElement;
            return top.getValue();
        }
    }

    /** Detects whether this stack is empty.
     @return  True if the stack is empty. */
    @Override
    public boolean isEmpty() {
        return topElement == null;
    }

    /** Removes all entries from this stack. */
    @Override
    public void clear() {
        topElement = null;
    }

    /** Displays the contents of the stack */
    public void display() {
        if(isEmpty()) {
            System.out.println("The stack is empty");
        }
        else {
            SLelement<E> current = topElement;
            StringBuffer output = new StringBuffer();
            output.append("Top of stack: " + current.getValue() + "\n");
            while(current.getNext() != null) {
                current = current.getNext();
                if(current.getNext() == null)
                    output.append("Stack bottom: ");
                else
                    output.append(" ");
                output.append(current.getValue() + "\n");
            }
            System.out.println(output.toString());
        }
    }

    public static void main(String[] args) {
        Bridges bridges = new Bridges(5, "rammohant", "247983952629");
        bridges.setTitle("CMSC 256 Project 4 Tara Ram Mohan");
        CustomStack<Integer> stack = new CustomStack<>();
        SLelement topOfStack = stack.topElement;         

        //Pushed 256, 10, 18, 20, and 2018
        stack.push(256);
        stack.push(10);
        stack.push(18);
        stack.push(20);
        stack.push(2018);

        //Called pop twice:
        stack.pop();
        stack.pop();

        //A call to peek returns 18
        stack.peek();

        //Called pop three times:
        stack.pop();
        stack.pop();
        stack.pop();

        stack.display();

        bridges.setDataStructure(topOfStack); 
        try {
            bridges.visualize();
        }                                               
        catch(Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
}
