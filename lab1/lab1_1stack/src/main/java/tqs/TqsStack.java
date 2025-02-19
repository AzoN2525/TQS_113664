package tqs;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class TqsStack<Obj> {

    private List<Obj> stack;

    public TqsStack() {
        stack = new ArrayList<Obj>();
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public void push(Obj element) {
        stack.add(element);
    }

    public Object pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    public Object popTopN(int n){

        if (n > stack.size()) {
            throw new NoSuchElementException("Stack does not have enough elements");
        }
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        if (n == 0) {
            throw new NoSuchElementException("n must be greater than 0");
        }
        
        Obj top = null;

        for (int i = 0; i < n; i++) {

            top = stack.remove(stack.size() - 1);
        }
        return top;
    }

    public Object peek() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return stack.get(stack.size() - 1);
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}