/*
Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
*/

public class Logger {
    private static class TimeAndMessages {
        final int timestamp;
        final List<String> messages = new ArrayList<>();
        TimeAndMessages(int timestamp) {
            this.timestamp = timestamp;
        }
    }
    
    private final int DELAY = 10;
    private final Map<String, Integer> messageToLastPrintedTime = new HashMap<>();
    private final ArrayDeque<TimeAndMessages> timeToMessages = new ArrayDeque<>();
    /** Initialize your data structure here. */
    public Logger() {
        
    }
    
    private void evictOldData(int curTimestamp) {
        while (!timeToMessages.isEmpty() && timeToMessages.peek().timestamp <= curTimestamp - DELAY) {
            TimeAndMessages timeMessages = timeToMessages.poll();
            for (String mes : timeMessages.messages) {
                if (messageToLastPrintedTime.get(mes) == timeMessages.timestamp) {
                    messageToLastPrintedTime.remove(mes);
                }
            }
        }
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    //Data eviction can be done separately using an async cron job.
    public boolean shouldPrintMessage0(int timestamp, String message) {
        int lastPrintedTime = messageToLastPrintedTime.getOrDefault(message, -1);
        if (lastPrintedTime >= 0 && timestamp - lastPrintedTime < DELAY) {
            return false;
        }
        messageToLastPrintedTime.put(message, timestamp);
        final TimeAndMessages timeMessages;
        if (timeToMessages.isEmpty() || timeToMessages.peekLast().timestamp < timestamp) {
            timeMessages = new TimeAndMessages(timestamp);
        } else {
            timeMessages = timeToMessages.peekLast();
        }
        timeMessages.messages.add(message);
        evictOldData(timestamp);
        return true;
    }
    
    //We can also make use of LinkedHashMap's removeEldestEntry, and make sure it is access-ordered.
    //https://discuss.leetcode.com/topic/50776/java-with-a-linkedhashmap-and-using-removeeldestentry
    //This is the best solution!
    private int curTimestamp;
    private final Map<String, Integer> messageToLastPrintedTime2
            = new LinkedHashMap<String, Integer>(100, 0.8f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                    return curTimestamp - eldest.getValue() >= DELAY;
                }
            };

    public boolean shouldPrintMessage(int timestamp, String message) {
        int lastPrintedTime = messageToLastPrintedTime2.getOrDefault(message, -1);
        if (lastPrintedTime >= 0 && timestamp - lastPrintedTime < DELAY) {
            return false;
        }
        curTimestamp = timestamp;
        messageToLastPrintedTime2.put(message, timestamp);
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */