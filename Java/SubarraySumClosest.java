public class SubarraySumClosest {
    
    //O(nlogn) time and O(n) space
    public int[] subarraySumClosest(int[] nums) {
        int[][] sumAndIds = new int[nums.length+1][2];
        sumAndIds[0][1] = -1;
        for (int i = 1; i <= nums.length; ++i) {
            sumAndIds[i][0] = nums[i-1] + sumAndIds[i-1][0];
            sumAndIds[i][1] = i-1;
        }
        Arrays.sort(sumAndIds, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int minDiff = 0;
        int[] ids = new int[2];
        for (int i = 1; i < sumAndIds.length; ++i) {
            int curDiff = sumAndIds[i][0] - sumAndIds[i-1][0];
            if (i == 1 || curDiff < minDiff) {
                minDiff = curDiff;
                ids[0] = Math.min(sumAndIds[i][1], sumAndIds[i-1][1]) + 1;
                ids[1] = Math.max(sumAndIds[i][1], sumAndIds[i-1][1]);
            }
        }
        return ids;
    }

    //If the target is k instead of 0:
    //TreeMap is essentially a balanced BST, so floorKey, ceilingKey runs in O(logn).
    //So the total time is O(nlogn), space is O(n)
    //More: https://rafal.io/posts/subsequence-closest-to-t.html
    //If the questions asks for the minium length of subarray that has sum closest
    //to k, we can add a map of diff to minLen, and also maintain the minDiff,
    //and after the loop finishes, we can use the minDiff to get the minLen.
    public int[] subarraySumClosest(int[] nums, int k) {
        int[] result = new int[2];
        TreeMap<Integer, Integer> numToId = new TreeMap<>();
        numToId.put(0, -1);
        int sum = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            int target = sum - k;
            Integer floorKey = numToId.floorKey(target);
            if (floorKey != null && (target - floorKey) < minDiff) {
                minDiff = target - floorKey;
                result[0] = numToId.get(floorKey) + 1;
                result[1] = i;
            }
            Integer ceilingKey = numToId.ceilingKey(target);
            if (ceilingKey != null && (ceilingKey - target) < minDiff) {
                minDiff = ceilingKey - target;
                result[0] = numToId.get(ceilingKey) + 1;
                result[1] = i;
            }
            //Always update, so the result is shortest
            numToId.put(sum, i);
        }
        return result;
    }
}
