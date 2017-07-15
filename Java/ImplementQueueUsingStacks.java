class MyQueue {
    // Push element x to the back of queue.
    private final Deque<Integer> pushStack = new ArrayDeque<>();
    private final Deque<Integer> popStack = new ArrayDeque<>();
    
    public void push(int x) {
        pushStack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        peek();
        popStack.pop();
    }

    // Get the front element.
    // Amortized time is O(1)?
    public int peek() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                Integer element = pushStack.pop();
                popStack.push(element);
            }      
        }
        return popStack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }
}