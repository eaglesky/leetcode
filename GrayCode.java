public class GrayCode {
    
    //O(2^n) time and O(1) space
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        int add = 1;
        for (int i = 0; i < n; ++i) {
            int size = result.size();
            for (int j = size - 1; j >= 0; j--) {
                int cur = result.get(j) | add;
                result.add(cur);
            }
            add = add << 1;
        }
        return result;
    }
}