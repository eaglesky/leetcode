public class GroupAnagrams {

	//O(n*n_str*log(n_sr)) time and O(n) space
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> str2Id = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String keyStr = new String(chars);
            Integer idInt = str2Id.get(keyStr);
            if (idInt == null) {
                idInt = str2Id.size();
                str2Id.put(keyStr, idInt);
            }
            if (idInt >= result.size()) {
                result.add(new ArrayList<String>());
            }
            List<String> curGroup = result.get(idInt);
            curGroup.add(str);
        }
        return result;
    }

    public List<List<String>> groupAnagrams0(String[] strs) {
        Map<String, List<String>> anaMap = new LinkedHashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            //This is the costly part since each str could be very long,
            //and there could be many strings in the input array.
            //Using counting sort instead is better.
            //Or simply use the counting array list as key
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            
            //The following seems to be more costly on OJ than what it stands for.
            anaMap.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(anaMap.values());
    }
    
    //Convert counting array to a string, similar to using counting sort
    //https://discuss.leetcode.com/topic/5460/java-solution-use-hashmap-use-simple-string-uid-as-key
    //https://discuss.leetcode.com/topic/33030/java-22-ms-and-20-lines-36-ms-and-11-lines-172-ms-and-9-lines-d
    private String getUid(String s) {
        int k = 26;
        //Each char represents a count for the corresponding letter, assuming that
        //the number of occurrence for each letter can fit in 2 bytes.
        char[] countings = new char[k];
        for (int i = 0; i < s.length(); ++i) {
            countings[(int)(s.charAt(i) - 'a')]++;
        }
        return new String(countings);
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anaMap = new HashMap<>();
        for (String str : strs) {
            String uid = getUid(str);
            anaMap.computeIfAbsent(uid, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(anaMap.values());
    }
    
    //Another solution that map each letter to a prime number so that each string
    //can be uniquely represented by the product of those prime numbers. 
    //https://discuss.leetcode.com/topic/12509/o-m-n-algorithm-using-hash-without-sort
    //However it could easily overflow.
}