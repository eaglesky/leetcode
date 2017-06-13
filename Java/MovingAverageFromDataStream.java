public class MovingAverageFromDataStream {
    private final Queue<Integer> q;
    private final int capacity;
    private int sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        q = new ArrayDeque<>(size);
        this.capacity = size;
    }
    
    //O(1) time
    public double next(int val) {
        if (capacity == 0) {
            return 0;
        }
        if (q.size() == capacity) {
            sum -= q.poll();
        }
        q.offer(val);
        sum += val;
        return (double)sum / q.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */