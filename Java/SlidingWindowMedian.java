public class SlidingWindowMedian {
    
    private static void addNum(TreeMap<Integer, Integer> map, int num) {
        int count = map.getOrDefault(num, 0);
        map.put(num, count + 1);
    }
    
    private static Integer removeNum(TreeMap<Integer, Integer> map, int num) {
        int count = map.getOrDefault(num, 0);
        if (--count == 0) {
            map.remove(num);
        } else if (count > 0) {
            map.put(num, count);
        }
        return (count >= 0) ? count : null;
    }
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new double[0];
        }
        double[] result = new double[nums.length - k + 1];
        TreeMap<Integer, Integer> largerHalf = new TreeMap<>();
        TreeMap<Integer, Integer> smallerHalf = new TreeMap<>();
        int numLarger = 0, numSmaller = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i >= k) {
                if (removeNum(largerHalf, nums[i-k]) != null) {
                    numLarger--;
                } else if (removeNum(smallerHalf, nums[i-k]) != null) {
                    numSmaller--;
                }
            }
            Map.Entry<Integer, Integer> smallestEntryInLarger = largerHalf.firstEntry();
            Map.Entry<Integer, Integer> largestEntryInSmaller = smallerHalf.lastEntry();
            int toBeAdded = nums[i];
            if (numLarger >= numSmaller) {
                if (!largerHalf.isEmpty() && nums[i] >= smallestEntryInLarger.getKey()) {
                    int smallestInLarger = smallestEntryInLarger.getKey();
                    removeNum(largerHalf, smallestInLarger);
                    toBeAdded = smallestInLarger;
                    addNum(largerHalf, nums[i]);
                }
                addNum(smallerHalf, toBeAdded);
                numSmaller++;
            } else {
                if (nums[i] <= largestEntryInSmaller.getKey()) {            
                    int largestInSmaller = largestEntryInSmaller.getKey();
                    removeNum(smallerHalf, largestInSmaller);
                    toBeAdded = largestInSmaller;
                    addNum(smallerHalf, nums[i]);
                }
                addNum(largerHalf, toBeAdded);
                numLarger++;
            }
            if (i >= k - 1) {
                if (numLarger > numSmaller) {
                    result[i - k + 1] = largerHalf.firstKey();
                } else if (numLarger < numSmaller) {
                    result[i - k + 1] = smallerHalf.lastKey();
                } else {
                    int smallestInLarger = largerHalf.firstKey();
                    int largestInSmaller = smallerHalf.lastKey();
                    result[i-k+1] = ((double)smallestInLarger + (double)largestInSmaller) * 0.5;
                }
            }
        }
        return result;
    }
}