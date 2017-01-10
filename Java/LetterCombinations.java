public class LetterCombinations {
    
    //DFS solution. O(n*k^n) time and O(n) space, k is the average number of letters per digit
    private void dfs(String digits, int id, Map<Character, String> phoneMap, char[] combo, List<String> result) {
        if (id >= digits.length()) {
            result.add(new String(combo));
            return;
        }
        String letters = phoneMap.get(digits.charAt(id));
        for (int i = 0; i < letters.length(); ++i) {
            combo[id] = letters.charAt(i);
            dfs(digits, id+1, phoneMap, combo, result);
        }    
    }
    
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }
        Map<Character, String> phoneMap = new HashMap<>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
        phoneMap.put('0', " ");
        dfs(digits, 0, phoneMap, new char[digits.length()], result);
        return result;
    }

    //See the c++ source code for the iterative solution using base conversion.
    //Same time and space complexity as above solution, but memory usage should be 
    //slightly lower.
}