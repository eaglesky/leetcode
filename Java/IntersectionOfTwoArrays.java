public class Solution {
    
    //Hashmap solution. O(n) time and O(n) space
    public int[] intersection0(int[] nums1, int[] nums2) {
        Set<Integer> uniqueNums = new HashSet<>();
        for (int num : nums1) {
            uniqueNums.add(num);
        }
        List<Integer> resultList = new ArrayList<>();
        for (int num : nums2) {
            if (uniqueNums.contains(num)) {
                resultList.add(num);
                uniqueNums.remove(num);
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = resultList.get(i);
        }
        return result;
    }
    
    //Two pointers solution when the input arrays are sorted.
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> resultList = new ArrayList<>();
        for (int i1 = 0, i2 = 0; i1 < nums1.length && i2 < nums2.length;) {
            if (i2 > 0 && nums2[i2] == nums2[i2-1]) {
                i2++;
                continue;
            }
            if (nums1[i1] == nums2[i2]) {
                resultList.add(nums2[i2]);
            }
            if (nums1[i1] >= nums2[i2]) {
                i2++;
            } else {
                i1++;
            }
        }
        int i = 0;
        int[] result = new int[resultList.size()];
        for (int num : resultList) {
            result[i++] = num;
        }
        return result;
    }
    
    //Third solution is using binary search for each element of nums2 in nums1.
}