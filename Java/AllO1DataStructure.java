public class AllO1DataStructure {
    private static class CountNode {
        final int count;
        final Set<String> keys;
        CountNode prev;
        CountNode next;
        
        public CountNode(int count) {
            this.count = count;
            keys = new LinkedHashSet<>();
        }
        
    }
    
    private final Map<String, CountNode> keyToCountNode = new HashMap<>();
    private final CountNode dummyHead;
    private final CountNode dummyTail;
    
    /** Initialize your data structure here. */
    public AllOne() {
        dummyHead = new CountNode(-1);
        dummyTail = new CountNode(-1);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    
    private void removeKeyFromCountNode(CountNode countNode, String key) {
        countNode.keys.remove(key);
        if (countNode.keys.isEmpty()) {
            CountNode prevNode = countNode.prev;
            CountNode nextNode = countNode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        CountNode countNode = keyToCountNode.get(key);
        final int newCount;
        if (countNode != null) {
            newCount = countNode.count + 1;
            removeKeyFromCountNode(countNode, key);
            if (countNode.keys.isEmpty()) {
                countNode = countNode.prev;
            }
        } else {
            newCount = 1;
            countNode = dummyHead;
        }
        final CountNode newCountNode;
        if (countNode.next == dummyTail || countNode.next.count > newCount) {
            newCountNode = new CountNode(newCount);
            newCountNode.next = countNode.next;
            countNode.next.prev = newCountNode;
            countNode.next = newCountNode;
            newCountNode.prev = countNode;
        } else {
            newCountNode = countNode.next;
        }
        newCountNode.keys.add(key);
        keyToCountNode.put(key, newCountNode);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        CountNode countNode = keyToCountNode.get(key);
        if (countNode == null) {
            return;
        }
        int newCount = countNode.count - 1;
        removeKeyFromCountNode(countNode, key);
        if (countNode.keys.isEmpty()) {
            countNode = countNode.next;
        }
        if (newCount == 0) {
            keyToCountNode.remove(key);
        } else {
            final CountNode newCountNode;
            if (countNode.prev == dummyHead || countNode.prev.count < newCount) {
                newCountNode = new CountNode(newCount);
                newCountNode.prev = countNode.prev;
                countNode.prev.next = newCountNode;
                countNode.prev = newCountNode;
                newCountNode.next = countNode;
            } else {
                newCountNode = countNode.prev;
            }
            newCountNode.keys.add(key);
            keyToCountNode.put(key, newCountNode);
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        CountNode maxCountNode = dummyTail.prev;
        if (maxCountNode == dummyHead) {
            return "";
        }
        return maxCountNode.keys.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        CountNode minCountNode = dummyHead.next;
        if (minCountNode == dummyTail) {
            return "";
        }
        return minCountNode.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */