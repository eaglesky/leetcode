public class HitCounter {

    //single-threaded implementation
    private static class TimestampCount {
        final int timestamp;
        private int count = 1;
        TimestampCount(int timestamp) {
            this.timestamp = timestamp;
        }
        void incCount() {
            count++;
        }
        int count() {
            return count;
        }
    }
    
    //Compared with storing timestamps directly, this way can save much space.
    private final ArrayDeque<TimestampCount> timeCounts = new ArrayDeque<>();
    private int totalCount = 0;
    
    /** Initialize your data structure here. */
    //public HitCounter() {
    //}
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    //O(1) time
    public void hit0(int timestamp) {
        TimestampCount lastTimeCount = timeCounts.peekLast();
        if (lastTimeCount == null || lastTimeCount.timestamp < timestamp) {
            timeCounts.add(new TimestampCount(timestamp));
        } else {
            lastTimeCount.incCount();
        }
        totalCount++;
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    //O(n) time. If hit is called many times long before getHits is called, this method may
    //need to iterate all the elements in the queue.
    //To compensate for it, we can use an asynchronous job to evict the old data in the queue
    //every 5 minutes. So when getHits is called, it only needs to remove at most 5 minutes'
    //data, which makes the running time O(1). getHits can then be changed to read-only, adding
    //the counts within 5 mins up, and thus totalCount variable can be removed too.
    //Downside of this approach is that the methods have to be synchronized and there could be
    //an overhead. 
    public int getHits0(int timestamp) {
        int expiredTimestamp = timestamp - 300;
        for (; !timeCounts.isEmpty() && timeCounts.peek().timestamp <= expiredTimestamp;) {
            TimestampCount timeCount = timeCounts.poll();
            totalCount -= timeCount.count();
        }
        return totalCount;
    }
    
    //Another implementation using fixed circular array. The good thing compared with a queue
    //is that no need to do data eviction explicitly. So a single thread can still achieve O(1)
    //time for both methods. However this requires iterating every second in getHits, which could
    //be not better if the hits are scattered. Also this approach is only suitable when timestamp
    //doesn't have very small granularity. Otherwise(like if the timestamp is in ms/ns), the space
    //usage could be too high.
    private static final int EXPIRATION_SECONDS = 300;
    private final TimestampCount[] timeCountsBuf = new TimestampCount[EXPIRATION_SECONDS];
    
    public HitCounter() {
    }
    
    //O(1) time
    public void hit(int timestamp) {
        int id = timestamp % EXPIRATION_SECONDS;
        if (timeCountsBuf[id] == null || timeCountsBuf[id].timestamp != timestamp) {
            timeCountsBuf[id] = new TimestampCount(timestamp);
        } else {
            timeCountsBuf[id].incCount();
        }
    }
    
    //O(EXPIRATION_SECONDS) == O(1) time
    public int getHits(int timestamp) {
        int hitCount = 0;
        int oldestTimestamp = timestamp - EXPIRATION_SECONDS;
        for (int i = 0; i < EXPIRATION_SECONDS; ++i) {
            if (timeCountsBuf[i] != null && timeCountsBuf[i].timestamp > oldestTimestamp) {
                hitCount += timeCountsBuf[i].count();
            }
        }
        return hitCount;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */