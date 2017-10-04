class LRUCache {
    private static class MyNode {
        int key;
        int val;
        MyNode prev;
        MyNode next;
        MyNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    private final MyNode dummyHead;
    private final MyNode dummyTail; 
    
    private final Map<Integer, MyNode> lruMap = new HashMap<>();
    private final int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummyHead = new MyNode(-1, -1);
        dummyTail = new MyNode(-1, -1);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    
    private void removeNode(MyNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }
    
    private void addNodeToHead(MyNode node) {
        node.next = dummyHead.next;
        node.prev = dummyHead;
        dummyHead.next = node;
        node.next.prev = node;
    }
    
    public int get(int key) {
        MyNode node = lruMap.get(key);
        if (node == null) {
            return -1;
        }
        removeNode(node);
        addNodeToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (get(key) >= 0) {
            dummyHead.next.val = value;
            return;
        }
        int count = lruMap.size();
        if (count == capacity && count > 0) {
            MyNode lastNode = dummyTail.prev;
            removeNode(lastNode);
            lruMap.remove(lastNode.key);
        }
        MyNode newNode = new MyNode(key, value);
        lruMap.put(key, newNode);
        addNodeToHead(newNode);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */