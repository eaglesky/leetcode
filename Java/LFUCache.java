import java.util.*;

//Same idea as https://discuss.leetcode.com/topic/69137/java-o-1-accept-solution-using-hashmap-doublelinkedlist-and-linkedhashset
//Since minium count either does not change after an element reference, or increases 1 at a time,
//we can just maintain the minium count variable, and use map of count to LinkedHashMap instead of
//a doubly linked list.
//https://discuss.leetcode.com/topic/70200/short-java-o-1-solution-using-linkedhashmap-and-hashmap-with-explaination
public class LFUCache {
    
    private static class CountNode {
        private final int count;
        private final LinkedHashMap<Integer, Integer> keyValMap = new LinkedHashMap<>();
        public CountNode next;
        public CountNode prev;
        
        public CountNode(int count) {
            this.count = count;
        }
        
        public int getCount() {
            return count;
        }
        
        public LinkedHashMap<Integer, Integer> getKeyValMap() {
            return keyValMap;
        }
    }
    
    private final Map<Integer, CountNode> keyToCountNode = new HashMap<>();
    private final CountNode countNodeDummyHead; 
    private final CountNode countNodeDummyTail;
    private final int capacity;
    private int size = 0;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        countNodeDummyHead = new CountNode(-1);
        countNodeDummyTail = new CountNode(0);
        countNodeDummyHead.next = countNodeDummyTail;
        countNodeDummyTail.prev = countNodeDummyHead;
    }
    
    private void removeCountNode(CountNode node) {
        CountNode preNode = node.prev;
        CountNode nextNode = node.next;
        preNode.next = nextNode;
        nextNode.prev = preNode;
    }
    
    private void addCountNode(CountNode prevNode, CountNode node) {
        CountNode nextNode = prevNode.next;
        prevNode.next = node;
        node.prev = prevNode;
        node.next = nextNode;
        nextNode.prev = node;
    }
    
    //O(1) time
    public int get(int key) {
        CountNode countNode = keyToCountNode.get(key);
        if (countNode == null) {
            return -1;
        }
        LinkedHashMap<Integer, Integer> keyVals = countNode.getKeyValMap();
        int val = keyVals.get(key);
        int newCount = countNode.getCount() + 1;
        keyVals.remove(key);
        if (keyVals.isEmpty()) {
            removeCountNode(countNode);
            countNode = countNode.prev;
        }
        addKeyVal(countNode, newCount, key, val);
        return val;
    }
    
    private void addKeyVal(CountNode prevCountNode, int count, int key, int val) {
        CountNode newCountNode = null;
        if (prevCountNode.next == countNodeDummyTail || prevCountNode.next.getCount() > count) {
            newCountNode = new CountNode(count);
            addCountNode(prevCountNode, newCountNode);
        } else {
            newCountNode = prevCountNode.next;
        }
        newCountNode.getKeyValMap().put(key, val);
        keyToCountNode.put(key, newCountNode);
    }
    
    private void invalidate() {
        CountNode countNodeHead = countNodeDummyHead.next;
        if (countNodeHead != countNodeDummyTail) {
            LinkedHashMap<Integer, Integer> keyVals = countNodeHead.getKeyValMap();
            Integer key = keyVals.entrySet().iterator().next().getKey();
            keyVals.remove(key);
            keyToCountNode.remove(key);
            size--;
            if (keyVals.isEmpty()) {
                removeCountNode(countNodeHead);
            }
        }
    }
    
    //O(1) time
    public void put(int key, int value) {
        if (get(key) >= 0) {
            keyToCountNode.get(key).getKeyValMap().put(key, value);
        } else {
            if (size == capacity) {
                invalidate();
            }
            if (size < capacity) {
                addKeyVal(countNodeDummyHead, 1, key, value);
                size++;
            }
        }
    }

    public static void main(String[] args) {
    	String[] ops = new String[] {"LFUCache",
    		"put","put","put","put","put","get","put","get","get","put","get",
    		"put","put","put","get","put","get","get","get","get","put","put",
    		"get","get","get","put","put","get","put","get","put","get","get",
    		"get","put","put","put","get","put","get","get","put","put","get",
    		"put","put","put","put","get","put","put","get","put","put","get",
    		"put","put","put","put","put","get","put","put","get","put","get",
    		"get","get","put","get","get","put","put","put","put","get","put",
    		"put","put","put","get","get","get","put","put","put","get","put",
    		"put","put","get","put","put","put","get","get","get","put","put",
    		"put","put","get","put","put","put","put","put","put","put"
		};
    	int[][] params = new int[][] {{10},
    	{10,13},{3,17},{6,11},{10,5},{9,10},
    	{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},
    	{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},
    	{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},
    	{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},
    	{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},
    	{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},
    	{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},
    	{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},
    	{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};

    	if (params.length != ops.length) {
    		throw new RuntimeException("Input arrays do not have the same length!");
    	}
    	LFUCache lfuCache = new LFUCache(params[0][0]);
    	for (int i = 1; i < ops.length; ++i) {
    		String op = ops[i];
    		int[] param = params[i];
    		String commonStr = "After " + op + " ";
    		if (op.equals("put")) {
    			lfuCache.put(param[0], param[1]);
    			System.out.println(commonStr + param[0] + ", " + param[1]);
    		} else if (op.equals("get")) {
    			int result = lfuCache.get(param[0]);
    			System.out.println(commonStr + param[0] + ": " + result);
    		}
    	}
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */