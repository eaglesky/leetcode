public class UniquePath {
    
    //DP solution. O(mn) time and O(n) space
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[] sums = new int[n];
        sums[0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                sums[j] += sums[j - 1];               
            }
        }
        return sums[n - 1];
    }
}