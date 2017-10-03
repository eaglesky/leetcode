/*
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned 
by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does 
not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
*/

public class ZigzagIterator {
    private final List<Iterator<Integer>> iters0 = new LinkedList<>();
    private ListIterator<Iterator<Integer>> listIter;
    
    //My implementation using listIterator.
    /*public ZigzagIterator0(List<Integer> v1, List<Integer> v2) {
        if (v1 != null && !v1.isEmpty()) {
            iters0.add(v1.iterator());
        }
        if (v2 != null && !v2.isEmpty()) {
            iters0.add(v2.iterator());
        }
        listIter = iters0.listIterator();
    }
    
    public int next0() {
        Iterator<Integer> nextIter = listIter.next();
        int nextVal = nextIter.next();
        if (!nextIter.hasNext()) {
            listIter.remove();
        }
        if (!listIter.hasNext()) {
            listIter = iters0.listIterator();
        }
        return nextVal;
    }

    public boolean hasNext0() {
        return !iters0.isEmpty();
    }*/
    
    //Best implementation using Queue.
    private Queue<Iterator<Integer>> iters = new ArrayDeque<>();
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        if (v1 != null && !v1.isEmpty()) {
            iters.offer(v1.iterator());
        }
        if (v2 != null && !v2.isEmpty()) {
            iters.offer(v2.iterator());
        }
    }
    
    public int next() {
        Iterator<Integer> nextIter = iters.poll();
        int nextVal = nextIter.next();
        if (nextIter.hasNext()) {
            iters.offer(nextIter);
        }
        return nextVal;
    }

    public boolean hasNext() {
        return !iters.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */