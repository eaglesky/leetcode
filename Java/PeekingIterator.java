// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {

    private final Iterator<Integer> iter;
    private Integer nextValue;
    boolean hasNext;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iter = iterator;
	    if (iter == null) {
	        throw new RuntimeException("Input iterator is null!");
	    }
	    this.hasNext = true;
	    next();
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return nextValue;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (!hasNext()) {
	        //Should uncomment the following, but OJ doesn't not accept it
	        //throw new java.util.NoSuchElementException();
	    }
	    Integer ret = nextValue;
	    nextValue = null;
	    if (iter.hasNext()) {
	        nextValue = iter.next();
	    } else {
	        hasNext = false;
	    }
	    return ret;
	}

	@Override
	public boolean hasNext() {
	    return hasNext;
	}
}