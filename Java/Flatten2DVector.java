//My post: https://discuss.leetcode.com/topic/92242/my-java-implementation-and-some-thoughts-about-what-an-iterator-should-look-like
//Similar: https://discuss.leetcode.com/topic/35398/share-my-simple-java-code-using-only-java-built-in-iterators-demanded-in-the-follow-up-and-beats-94-59-run-times
public class Flatten2DVector implements Iterator<Integer> {

    private final Iterator<List<Integer>> listIterator;
    private Iterator<Integer> sublistIterator;
    
    public Vector2D(List<List<Integer>> vec2d) {
        listIterator = vec2d.iterator();
        advanceListIterator();
    }

    private void advanceListIterator() {
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
        Integer ret = sublistIterator.next();
        if (!sublistIterator.hasNext()) {
            advanceListIterator();
        }
        return ret;
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