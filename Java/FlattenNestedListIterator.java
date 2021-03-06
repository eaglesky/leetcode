/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    private final Deque<NestedInteger> stack = new ArrayDeque<>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        ListIterator<NestedInteger> iter = nestedList.listIterator(nestedList.size());
        while (iter.hasPrevious()) {
            stack.push(iter.previous());
        }
        advanceToNextInteger();
    }
    
    private void advanceToNextInteger() {
        while (!stack.isEmpty()) {
            NestedInteger top = stack.peek();
            if (top.isInteger()) {
                return;
            } else {
                stack.pop();
                List<NestedInteger> nestedList = top.getList();
                ListIterator<NestedInteger> iter = nestedList.listIterator(nestedList.size());
                while (iter.hasPrevious()) {
                    stack.push(iter.previous());
                }
            }
        }
    }

    @Override
    public Integer next() {
        Integer ret = stack.pop().getInteger();
        advanceToNextInteger();
        return ret;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}


//Best implementation. Without copying all the nodes. O(h) space
//This is what a real iterator should be implemented.
//https://discuss.leetcode.com/topic/41870/real-iterator-in-python-java-c
public class NestedIterator implements Iterator<Integer> {

    private final Deque<ListIterator<NestedInteger>> stack = new ArrayDeque<>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList != null) {
            stack.push(nestedList.listIterator());
        }
        advanceToNextInteger();
    }
    
    private void advanceToNextInteger() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else {
                ListIterator<NestedInteger> iter = stack.peek();
                NestedInteger cur = iter.next();
                if (cur.isInteger()) {
                    iter.previous();
                    return;
                }
                stack.push(cur.getList().listIterator());
            }
        }
    }

    @Override
    public Integer next() {
        Integer ret = stack.peek().next().getInteger();
        advanceToNextInteger();
        return ret;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    //Second try, using Iterator and cached next value:
    private final Deque<Iterator<NestedInteger>> stack;
    private Integer nextInteger = null;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>();
        stack.push(nestedList.iterator());
        advanceIterator();
    }

    private void advanceIterator() {
        if (!hasNext()) {
            for(; !stack.isEmpty(); ) {
                Iterator<NestedInteger> iter = stack.peek();
                if (!iter.hasNext()) {
                    stack.pop();
                } else {
                    NestedInteger ni = iter.next();
                    Integer nint = ni.getInteger();
                    if (nint != null) {
                        nextInteger = nint;
                        return;
                    }
                    stack.push(ni.getList().iterator());
                }
            }
        }    
    }
    
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        Integer ret = nextInteger;
        nextInteger = null;
        advanceIterator();
        return ret;
    }

    @Override
    public boolean hasNext() {
        return nextInteger != null;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */