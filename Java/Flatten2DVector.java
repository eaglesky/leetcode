/*
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned 
by next should be: [1,2,3,4,5,6].

Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
*/

//My post: https://discuss.leetcode.com/topic/92242/my-java-implementation-and-some-thoughts-about-what-an-iterator-should-look-like
//Similar: https://discuss.leetcode.com/topic/35398/share-my-simple-java-code-using-only-java-built-in-iterators-demanded-in-the-follow-up-and-beats-94-59-run-times
//Note that listIterator can't be null since vec2d is non-null.
//But sublistIterator could be null due to the possible empty vec2d.
public class Flatten2DVector implements Iterator<Integer> {

    //Always point to the sublist next to the current one.
    private final Iterator<List<Integer>> listIterator;

    //Always point to the next available element in the current sublist.
    private Iterator<Integer> sublistIterator;
    
    public Vector2D(List<List<Integer>> vec2d) {
        listIterator = vec2d.iterator();
        advanceListIterator();
    }

    private void advanceListIterator() {
        if (sublistIterator != null && sublistIterator.hasNext()) {
            return;
        }
        while (listIterator.hasNext()) {
            sublistIterator = listIterator.next().iterator();
            if (sublistIterator.hasNext()) {
                break;
            }
        }
    }
    
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        Integer nextElement = sublistIterator.next();
        advanceListIterator();
        return nextElement;
    }

    @Override
    public boolean hasNext() {
        return sublistIterator != null && sublistIterator.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */