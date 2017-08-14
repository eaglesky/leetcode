public class LongestConsecutiveSequence {
    
    //O(n) time and O(n) space
    //Maintain a map of number to length of the consequtive sequence that contains it.
    //The length is only correct for max and min number in each consequtive sequence.
    //This is the loop invariant that is maintained in each iteration.
    //https://discuss.leetcode.com/topic/6148/my-really-simple-java-o-n-solution-accepted
    public int longestConsecutive0(int[] nums) {
        Map<Integer, Integer> num2Len = new HashMap<>();
        int result = 0;
        for (int num : nums) {
            if (!num2Len.containsKey(num)) {
                int len1 = num2Len.getOrDefault(num + 1, 0);
                int len2 = num2Len.getOrDefault(num - 1, 0);
                int newLen = len1 + len2 + 1;
                num2Len.put(num, newLen);
                num2Len.put(num + len1, newLen);
                num2Len.put(num - len2, newLen);
                result = Math.max(result, newLen);
            }
        }
        return result;
    }
    
    //Another solution. Easy to prove the correctness. O(n) time and O(n) space
    //https://discuss.leetcode.com/topic/15383/simple-o-n-with-explanation-just-walk-each-streak?page=1
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new LinkedHashSet<>(nums.length);
        for (int num : nums) {
            numSet.add(num);
        }
        int maxLen = 0;
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int curLen = 0;
                for (; numSet.contains(num); ++num, ++curLen);
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }
}