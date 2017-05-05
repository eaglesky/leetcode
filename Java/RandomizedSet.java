public class RandomizedSet {

    private final Map<Integer, Integer> valToId = new HashMap<>();
    private final List<Integer> valList = new ArrayList<>();
    private final Random rand = new Random();
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (valToId.containsKey(val)) {
            return false;
        }
        valToId.put(val, valList.size());
        valList.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer id = valToId.remove(val);
        if (id == null) {
            return false;
        }
        int last = valList.get(valList.size() - 1);
        if (val != last) {
            valList.set(id, last);
            valToId.put(last, id);
        }
        valList.remove(valList.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int randId = rand.nextInt(valList.size());
        return valList.get(randId);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */