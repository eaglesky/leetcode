
// Using array as elements, essentially two stacks
public class MinStack0 {

    private final Deque<int[]> elementData;
    
    /** initialize your data structure here. */
    public MinStack() {
        this.elementData = new ArrayDeque<>();
    }
    
    public void push(int x) {
        int prevMin = (elementData.peek() == null) ? Integer.MAX_VALUE : elementData.peek()[1];
        elementData.push(new int[]{x, Math.min(x, prevMin)});
    }
    
    public void pop() {
        elementData.pop();
    }
    
    public int top() {
        return elementData.peek()[0];
    }
    
    public int getMin() {
        if (elementData.peek() == null) {
            return Integer.MAX_VALUE;
        } else {
            return elementData.peek()[1];
        }
    }
}

// Using two stacks. Less elements in mins than the above solution.
// Can be easily extended to store any type of elements
public class MinStack {

    private final Deque<Integer> elementData;
    private final Deque<Integer> mins;
    
    /** initialize your data structure here. */
    public MinStack() {
        this.elementData = new ArrayDeque<>();
        this.mins = new ArrayDeque<>();
    }
    
    public void push(int x) {
        int prevMin = (mins.peek() == null) ? Integer.MAX_VALUE : mins.peek();
        elementData.push(x);
        if (x <= prevMin) {
            mins.push(x);
        }
    }
    
    public void pop() {
        int popped = elementData.pop();
        if (popped <= mins.peek()) {
            mins.pop();
        }
    }
    
    public int top() {
        return elementData.peek();
    }
    
    public int getMin() {
        if (mins.peek() == null) {
            return Integer.MAX_VALUE;
        } else {
            return mins.peek();
        }
    }
}

// Store gap instead of element itself
// Hard to extend to all types, also not saving any memory than the previous solutions
public class MinStack1 {

    private final Deque<Long> gaps;
    private int min;
    
    /** initialize your data structure here. */
    public MinStack() {
        this.gaps = new ArrayDeque<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        long gap = (long)x - (long)min;
        gaps.push(gap);
        if (gap < 0) {
            min = x;
        }
    }
    
    public void pop() {
        long gap = gaps.pop();
        if (gap < 0) {
            min =(int)(min - gap);
        }
    }
    
    public int top() {
        long gap = gaps.peek();
        return (gap < 0) ? min : (int)(min + gap);
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */