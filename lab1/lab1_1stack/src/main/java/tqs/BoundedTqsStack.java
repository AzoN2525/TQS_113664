package tqs;

public class BoundedTqsStack<Obj> extends TqsStack<Obj> {
    
    private final int maxSize;

    BoundedTqsStack(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    @Override
    public String toString() {
        return "BoundedTqsStack{" +
                "maxSize=" + maxSize +
                '}';
    }

    @Override
    public void push(Obj element) {
        
        if(this.size() < this.maxSize) {
            super.push(element);
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }
}

