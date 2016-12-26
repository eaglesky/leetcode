public class SingleNumber2 {
    
    //Naive solution. This algorithm can be applied to other similar problems
    //Not sure if the element in the counts array could overflow
    //O(n) time and O(1) space
    public int singleNumber0(int[] nums) {
        int[] counts = new int[Integer.SIZE];
        for (int num : nums) {
            for (int i = 0; i < Integer.SIZE; ++i) {
                if ((num & (1 << i)) != 0) {
                    counts[i]++;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < Integer.SIZE; ++i) {
            if (counts[i] % 3 != 0) {
                result = result | (1 << i);
            }
        }
        return result;
    }
    
    //Similar idea but different implementation as above
    //O(n) time and O(1) space
    public int singleNumber1(int[] nums) {
        int result = 0;
        for (int i = 0; i < Integer.SIZE; ++i) {
            int sum = 0;
            for (int num : nums) {
                if ((num & (1 << i)) != 0) {
                    sum++;
                    if (sum % 3 == 0) {//Prevent overflow in sum
                        sum = 0;
                    }
                }
            }
            if (sum % 3 != 0) {
                result |= 1 << i;
            }
        }
        return result;
    }
    
    //Based on boolean expressions of state machine
    //States of x2x1: 00 -> 01 -> 10 -> 00
    //State transition matrix:
    //    x2 x1          input    newX2 newX1
    //    0 0            0          0 0
    //    0 1            0          0 1
    //    1 0            0          1 0
    //    0 0            1          0 1
    //    0 1            1          1 0
    //    1 0            1          0 0
    // newX1 depends on the values of x1 and x2, so does newX2
    // Reference: https://discuss.leetcode.com/topic/22821/an-general-way-to-handle-all-this-sort-of-questions
    //O(n) time and O(1) space, faster than above solutions
    public int singleNumber(int[] nums) {
        int x1 = 0, x2 = 0;
        for (int num : nums) {
            int tempX1 = (~x2 & x1 & ~num) | (~x2 & ~x1 & num);
            x2 = (x2 & ~x1 & ~num) | (~x2 & x1 & num);
            x1 = tempX1;
        }
        return x1 | x2;
    }
}