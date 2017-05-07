public class RandomizedCollection {
    private final Map<Integer, Set<Integer>> valToIds = new HashMap<>();
    private final List<Integer> valList = new ArrayList<>();
    private final Random rnd = new Random();
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
                
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        valList.add(val);
        Set<Integer> ids = valToIds.get(val);
        if (ids == null) {
            ids = new LinkedHashSet<>();
            valToIds.put(val, ids);
        }
        ids.add(valList.size() - 1);
        return ids.size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> ids = valToIds.get(val);
        if (ids == null) {
            return false;
        }
        int id = ids.iterator().next();
        ids.remove(id);
        if (ids.isEmpty()) {
            valToIds.remove(val);
        }
        int lastVal = valList.get(valList.size() - 1);
        int lastId = valList.size() - 1;
        if (id < lastId) {
            valList.set(id, lastVal);
            Set<Integer> lastValIds = valToIds.get(lastVal);
            lastValIds.remove(lastId);
            lastValIds.add(id);
        }
        valList.remove(lastId);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return valList.get(rnd.nextInt(valList.size()));
    }


    //  -------------------
    // Another method, still use a hashmap of values to List of ids, 
    // but in addition, use list of <val, id_in_map_value_list> pair.
    // The second value in each pair can make changing the id of last element
    // in the value list much easier.
    // However this method is more error-prone than the previous one
    // https://discuss.leetcode.com/topic/54381/c-128m-solution-real-o-1-solution
    private final Map<Integer, List<Integer>> valToIdList = new HashMap<>();
    private final List<int[]> valAndIdList = new ArrayList<>();
    private final Random rnd = new Random();
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
                
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        List<Integer> ids = valToIdList.get(val);
        if (ids == null) {
            ids = new ArrayList<>();
            valToIdList.put(val, ids);
        }
        ids.add(valAndIdList.size());
        valAndIdList.add(new int[]{val, ids.size() - 1});
        return ids.size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        List<Integer> ids = valToIdList.get(val);
        if (ids == null) {
            return false;
        }
        int id = ids.get(ids.size() - 1);
        ids.remove(ids.size()-1);
        if (ids.isEmpty()) {
            valToIdList.remove(val);
        }
        int lastId = valAndIdList.size() - 1;
        int[] lastValAndId = valAndIdList.get(lastId);
        if (id < lastId) {
            valAndIdList.set(id, lastValAndId);
            List<Integer> lastValIds = valToIdList.get(lastValAndId[0]);
            lastValIds.set(lastValAndId[1], id);
        }
        valAndIdList.remove(lastId);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return valAndIdList.get(rnd.nextInt(valAndIdList.size()))[0];
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */