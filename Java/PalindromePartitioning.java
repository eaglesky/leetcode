import java.util.*;

public class PalindromePartitioning {

    private boolean isPalindrome(String s) {
        for(int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    
    private void dfs(String s, List<String> partition, List<List<String>> result) {
        if (s.isEmpty()) {
            if (!partition.isEmpty()) {
                result.add(new ArrayList<>(partition));
            }
            return;
        }    
        for (int i = 0; i < s.length(); ++i) {
            String subStr = s.substring(0, i + 1);
            if (!isPalindrome(subStr)) {
                continue;
            }
            partition.add(subStr);
            dfs(s.substring(i+1), partition, result);
            partition.remove(partition.size()-1);
        }
    }
    
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        List<String> partition = new ArrayList<>();
        dfs(s, partition, result);
        return result;
    }
    
    public static void main(String[] args) {
        PalindromePartitioning pp = new PalindromePartitioning();
        String str = "aab";
        List<List<String>> result = pp.partition(str);
        System.out.println(result);
    }
}