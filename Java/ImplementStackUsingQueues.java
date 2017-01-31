class MyStack {
    
    private final Deque<Integer> q = new ArrayDeque<>();
    
    // Push element x onto stack.
    public void push(int x) {
        q.offer(x);
        for (int i = 0; i < q.size() - 1; ++i) {
            q.offer(q.poll());
        }
    }

    // Removes the element on top of the stack.
    public int pop() {
        return q.poll();
    }

    // Get the top element.
    public int top() {
        return q.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q.isEmpty();
    }
}