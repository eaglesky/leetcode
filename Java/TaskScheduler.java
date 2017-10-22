class Solution {
    
    //O(n) time and O(num_distinct_chars) space
    //https://leetcode.com/articles/task-scheduler/, Approach 3
    //This is the best solution for this problem
    /*Suppose 'A' is the most frequent task, n == 5, then there are 
      at least n tasks between adjacent 'A's('X' represents either idle or a task):
      AXXXXX
      AXXXXX
      AXXXXX
      A
      We need to find out a way to insert other tasks into it and minimize the number of idles.
      The best we can do is there is no idle and the total time equals to the number of tasks.
      The best strategy is the following greedy algorithm:
      1. Find out the most frequent tasks and schedule them first. There could be multiple most
      frequent tasks, and we need to schedule them one by one:
      ABCXXX
      ABCXXX
      ABCXXX
      ABC
      2. For the rest, we can fill the 'X' row by row:
      ABCDEF
      ABCDEX
      ABCDFX
      ABC
      3. If there is no 'X' left, then we can append new tasks to previous rows:
      ABCDEFHK
      ABCDEGI
      ABCDFGJ
      ABC
      As long as the tasks are distinct in each row, and same task appear in the same column,
      they must satisfy the cool-down constraint. In this way, no idle is needed, and thus the 
      result must be optimum.
      4. What if there is 'X' left(like in step 2)? How to prove this strategy leaves minimum 
      number of idles?
      Let's say we are in the initial state when only 'A' is scheduled. And say there is another 
      better solution that can acheive less number of idles. It must use either 'B' or 'C' to 
      replace 'X'. Say it is 'B'. Then that would mean there are two 'B's in the same row, 
      violating the cool-down constraint. So that better solution wouldn't be valid. 
      Therefore there is so solution that can do better than the greedy one.
    */
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0 || n < 0) {
            return 0;
        }
        Map<Character, Integer> charCounts = new HashMap<>();
        int maxCount = 0;
        int numMax = 0;
        for (char task : tasks) {
            int newCount = charCounts.getOrDefault(task, 0) + 1;
            charCounts.put(task, newCount);
            if (newCount > maxCount) {
                maxCount = newCount;
                numMax = 1;
            } else if (newCount == maxCount) {
                numMax++;
            }
        }
        int taskLeft = tasks.length - maxCount * numMax;
        int numIdle = (maxCount - 1) * Math.max(0, n + 1 - numMax);
        int numIdleLeft = Math.max(0, numIdle - taskLeft);
        return tasks.length + numIdleLeft;
    }
}