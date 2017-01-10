public class RestoreIpAddresses {
    
    //DFS solution
    private void dfs(String s, int start, int level, List<String> address, List<String> result) {
        if (level > 4) {
            if (start >= s.length()) {
                String curAddress = String.join(".", address);
                result.add(curAddress);
            }
            return;
        }
        int maxI = s.charAt(start) == '0' ? start + 1 : Math.min(start + 3, s.length());
        for (int i = start; i < maxI; ++i) {
            String str = s.substring(start, i + 1);
            int strValue = Integer.parseInt(str);
            if (strValue >= 0 && strValue <= 255 && i <= (s.length() - 5 + level)
                && i >= (s.length() - 1 - 3*(4 - level))) {
                address.add(str);
                dfs(s, i+1, level+1, address, result);
                address.remove(address.size()-1);
            }
        }
    }
    
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4) {
            return result;
        }
        dfs(s, 0, 1, new ArrayList<String>(), result);
        return result;
    }

    //See c++ solution for the iterative approach, not recommended.
}