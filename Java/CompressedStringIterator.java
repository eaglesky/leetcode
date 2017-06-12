public class StringIterator {
    private final String str;
    private char curChar;
    private int curCharCount;
    private int nextId;
    
    public StringIterator(String compressedString) {
        this.str = compressedString;
        nextId = 0;
        moveToNextAvailable();
    }
    
    private void moveToNextAvailable() {
        for (; curCharCount == 0 && nextId < str.length();) {
            curChar = str.charAt(nextId++);
            for(; nextId < str.length() && Character.isDigit(str.charAt(nextId)); nextId++) {
                int d = str.charAt(nextId) - '0';
                curCharCount = 10 * curCharCount + d;
            }
        }
    }
    
    public char next() {
        if (!hasNext()) {
            return ' ';
        }
        char ret = curChar;
        curCharCount--;
        if (curCharCount == 0) {
            moveToNextAvailable();
        }
        return ret;
    }
    
    public boolean hasNext() {
        return curCharCount > 0;
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */