public class KthLargestElementInArray {
    
    class MyInteger implements Comparable<MyInteger>{
        private int value;
        MyInteger(int value) {
            this.value = value;
        }
        public int value() {
            return value;
        }
        public int compareTo(MyInteger other) {
            return -Integer.compare(value(), other.value());
        }
    }
    
    //O(klogn) time and O(n) extra space(not including numList)
    public int findKthLargest0(int[] nums, int k) {
        if (nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        List<MyInteger> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(new MyInteger(num));
        }
        PriorityQueue<MyInteger> pq = new PriorityQueue<>(numList);
        MyInteger result = null;
        for(; k > 0; k--) {
            result = pq.poll();
        }
        return result.value();
    }
    
    //This solution is preferable when the input is a stream of data
    //O(nlogk) time and O(k) extra space, slower than the first solution
    public int findKthLargest1(int[] nums, int k) {
        if (nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; ++i) {
            if (i < k) {
                pq.offer(nums[i]);
            } else if (nums[i] > pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.peek();
    }
    

	private static Random rand = new Random();
	
    //Sort them from largest to smallest(descending)
    private int partition(int[] nums, int low, int high) {
        if (low >= high) {
			return low;
		}
		int id = rand.nextInt(high - low + 1) + low;
		//int id = (low + high) / 2;
		int pivot = nums[id];
		for(; low <= high;) {
		    for(; nums[low] > pivot; low++);
		    for(; nums[high] < pivot; high--);
		    if (low <= high) {
		        swap(nums, low, high);
		        low++;
		        high--;
		    }
		}
		return low;
    }
    
    private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	//Quick Select
	//Average time: O(n + n/2 + n/4 + ... + 1) = O(2n) = O(n) time
    private int findKthLargest(int[] nums, int k, int low, int high) {
        if (low >= high) {
            return nums[high];
        }
        int id = partition(nums, low, high);
        if (k < id + 1) {
            return findKthLargest(nums, k, low, id - 1);
        } else {
            return findKthLargest(nums, k, id, high);
        }
    }
    
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }        
        return findKthLargest(nums, k, 0, nums.length - 1);
    }
}