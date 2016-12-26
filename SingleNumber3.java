public class SingleNumber3 {
    
    //Two passes. First find out the different bits between the two targets.
    //Then find out the id of the rightmost '1' in the different bits,
    //and divide the nums into two groups, one has that bit at id as 1, the other does not.
    //There must be exactly one target in each group.
    //Finally use the approach in Single Number I to find out the two targets.
    //O(n) time and O(1) space
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        diff &= -diff; //0..1..0, 1 is the rightmost set bit
        //result[0] is the target with 1 set with the above mask
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & diff) != 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        return result;
    }
}