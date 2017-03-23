public class MedianFinder {

    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;
    
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    }
    
    //O(logn) time
    //Always make sure that the size of two heaps differ no more than 1,
    //and the max of maxHeap is no larger than the min of minHeap
    public void addNum(int num) {
        int toBeAdded = num;
        if (minHeap.size() >= maxHeap.size()) {
            if (!minHeap.isEmpty() && minHeap.peek() < num) {
                toBeAdded = minHeap.poll();
                minHeap.offer(num);
            }
            maxHeap.offer(toBeAdded);
        } else {
            if (maxHeap.peek() > num) {
                toBeAdded = maxHeap.poll();
                maxHeap.offer(num);
            }
            minHeap.offer(toBeAdded);
        }
    }
    
    //O(1) time
    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else if (minHeap.size() < maxHeap.size()) {
            return maxHeap.peek();
        } else {
            return maxHeap.isEmpty() ? 0.0
            : ((double)maxHeap.peek() + (double)minHeap.peek()) * 0.5;
        }
    }

    //Other thought:
    //Use sorted array, O(n) to insert, and O(1) to get
    //Use BST like this: https://discuss.leetcode.com/topic/61789/22ms-java-solution-using-binary-tree-beats-99-82-of-submissions
    //I think the run time for insert is O(n) instead of O(logn)??
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */