public class TwoSum {

    private final List<Integer> nums = new ArrayList<>();

    //Can use LinkedHashMap instead to save nums.
    private final Map<Integer, Integer> numCounts = new HashMap<>();
    
    /** Initialize your data structure here. */
    public TwoSum() {
        
    }
    
    //O(1) time
    public void add(int number) {
        nums.add(number);
        int preCount = numCounts.getOrDefault(number, 0);
        numCounts.put(number, preCount + 1);
    }
    

    //O(n) time
    public boolean find(int value) {
        for (int num : nums) {
            int other = value - num;
            int count = numCounts.getOrDefault(other, 0);
            if ((other == num && count > 1) || (other != num && count > 0)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */