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
    
    //Using two BSTs. Similar idea from "Find Median From Data Stream"
    //https://discuss.leetcode.com/topic/74874/easy-to-understand-o-nlogk-java-solution-using-treemap
    //O(nlogk) time and O(k) space(not including the result)
    public double[] medianSlidingWindow0(int[] nums, int k) {
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

    //Another implementation of the above idea. Very similar logic, except that I use 
    //a customized class MyNum and TreeSet to handle duplicates instead of TreeMap.
    //This is preferable in interview since the implementation of MyNum can be skipped.
    private static class MyNum implements Comparable<MyNum> {
        final int num;
        final int id;
        MyNum(int num, int id) {
            this.num = num;
            this.id = id;
        }

        //It's okay to skip implementing equals and hashCode for this problem
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MyNum)) {
                return false;
            }
            MyNum that = (MyNum)other;
            return this.num == that.num && this.id == that.id;
        }
        public int hashCode() {
            int result = 0;
            result = 31 * result + num;
            result = 31 * result + id;
            return result;
        }
        
        public int compareTo(MyNum other) {
            if (this.num == other.num) {
                return Integer.compare(this.id, other.id);
            }
            return Integer.compare(this.num, other.num);
        }
    }
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new double[0];
        }
        double[] result = new double[nums.length - k + 1];
        TreeSet<MyNum> largerHalf = new TreeSet<>();
        TreeSet<MyNum> smallerHalf = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            MyNum curNum = new MyNum(nums[i], i);
            if (i >= k) {
                MyNum prevNum = new MyNum(nums[i-k], i-k);
                boolean removed = largerHalf.remove(prevNum) || smallerHalf.remove(prevNum);
            }
            MyNum toBeAdded = curNum;
            if (largerHalf.size() >= smallerHalf.size()) {
                MyNum smallestInLarger = largerHalf.isEmpty() ? null : largerHalf.first();
                if (smallestInLarger != null && (curNum.compareTo(smallestInLarger) > 0)) {
                    largerHalf.remove(smallestInLarger);
                    toBeAdded = smallestInLarger;
                    largerHalf.add(curNum);
                }
                smallerHalf.add(toBeAdded);
            } else {
                MyNum largestInSmaller = smallerHalf.last();
                if (curNum.compareTo(largestInSmaller) < 0) {            
                    smallerHalf.remove(largestInSmaller);
                    toBeAdded = largestInSmaller;
                    smallerHalf.add(curNum);
                }
                largerHalf.add(toBeAdded);
            }
            if (i >= k - 1) {
                if (largerHalf.size() > smallerHalf.size()) {
                    result[i - k + 1] = largerHalf.first().num;
                } else if (largerHalf.size() < smallerHalf.size()) {
                    result[i - k + 1] = smallerHalf.last().num;
                } else {
                    result[i-k+1] = ((double)largerHalf.first().num + (double)smallerHalf.last().num) * 0.5;
                }
            }
        }
        return result;
    }

    //Second try, more robust implementation than above, though might be slightly slower
    private void balance(TreeSet<MyNum> smallHalf, TreeSet<MyNum> largeHalf) {
        if (smallHalf.size() - largeHalf.size() > 1) {
            largeHalf.add(smallHalf.pollLast());
        } else if (largeHalf.size() - smallHalf.size() > 1) {
            smallHalf.add(largeHalf.pollFirst());
        }
    }
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0 || nums.length < k) {
            return new double[0];
        }
        double[] result = new double[nums.length - k + 1];
        TreeSet<MyNum> smallHalf = new TreeSet<>();
        TreeSet<MyNum> largeHalf = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            MyNum mn = new MyNum(nums[i], i);
            if (largeHalf.isEmpty() || largeHalf.first().compareTo(mn) <= 0) {
                largeHalf.add(mn);
            } else {
                smallHalf.add(mn);
            }
            if (i >= k) {
                MyNum removed = new MyNum(nums[i - k], i - k);
                if (smallHalf.size() > 0 && removed.compareTo(smallHalf.last()) <= 0) {
                    smallHalf.remove(removed);
                } else {
                    largeHalf.remove(removed);
                }
            }
            balance(smallHalf, largeHalf);
            balance(smallHalf, largeHalf);
            if (i >= k - 1) {
                if (smallHalf.size() < largeHalf.size()) {
                    result[i - k + 1] = largeHalf.first().num;
                } else if (smallHalf.size() > largeHalf.size()) {
                    result[i - k + 1] = smallHalf.last().num;
                } else {
                    //Pay attention to the possible overflow if adding the two numbers first
                    result[i - k + 1] = largeHalf.first().num * 0.5 + smallHalf.last().num * 0.5;
                }
            }

        }
        return result;
    }

    //Another algorithm is to use one BST but maintain two variables storing the middle
    //two elements. The maintainence logic is more complex and the performance is not
    //much better.

    //Or using one BST but each node stores the size of left subtree. All operations are
    //still O(logk), but the code is still more complex.


    //Using two heaps takes O(nk) time since the remove takes O(k) time.
    //https://discuss.leetcode.com/topic/79642/short-and-clear-o-nlogk-java-solutions

    //Using sorted list apparently takes O(nk) time too.
    //https://discuss.leetcode.com/topic/79167/java-beats-97-used-sorts-list/2
}