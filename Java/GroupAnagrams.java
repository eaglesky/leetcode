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
}