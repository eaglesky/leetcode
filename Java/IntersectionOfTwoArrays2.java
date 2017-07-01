/* Answer to the 3rd follow-up:
If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that 
fit into the memory, and record the intersections.

If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort),
then read 2 elements from each array at a time in memory, record intersections.
https://discuss.leetcode.com/topic/45992/solution-to-3rd-follow-up-question
*/

public class IntersectionOfTwoArrays2 {
    
    //Hashmap solution, O(n) time and O(n) space
    public int[] intersect0(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> numCounts = new HashMap<>();
        for (int num : nums1) {
            int oldCount = numCounts.getOrDefault(num, 0);
            numCounts.put(num, oldCount + 1);
        }
        List<Integer> resultList = new ArrayList<>();
        for (int num : nums2) {
            int oldCount = numCounts.getOrDefault(num, 0);
            if (oldCount > 0) {
                numCounts.put(num, --oldCount);
                resultList.add(num);
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = resultList.get(i);
        }
        return result;
    }
    
    //Two pointers solution when the input arrays are sorted(1st follow-up)
    //Easier than the one for problem I, just remove the duplicate checks
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> resultList = new ArrayList<>();
        for (int i1 = 0, i2 = 0; i1 < nums1.length && i2 < nums2.length;) {
            if (nums1[i1] == nums2[i2]) {
                resultList.add(nums2[i2]);
                i1++;
                i2++;
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }
        int i = 0;
        int[] result = new int[resultList.size()];
        for (int num : resultList) {
            result[i++] = num;
        }
        return result;
    }
    
}