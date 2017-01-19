public class Triangle {
    
    //Top-down solution, O(n^2) time and O(n) space
    public int minimumTotal0(List<List<Integer>> triangle) {
        int nr = triangle.size();
        if (nr == 0) {
            return 0;
        }
        int[] sums = new int[nr];
        sums[0] = triangle.get(0).get(0);
        for (int r = 1; r < nr; ++r) {
            sums[r] = triangle.get(r).get(r) + sums[r-1];
            for (int c = r - 1; c > 0; --c) {
                sums[c] = triangle.get(r).get(c) + Math.min(sums[c], sums[c-1]);
            }
            sums[0] = triangle.get(r).get(0) + sums[0];
        }
        int minSum = Integer.MAX_VALUE;
        for (int sum : sums) {
            minSum = Math.min(minSum, sum);
        }
        return minSum;
    }
    
    //Bottoem-up solution, O(n^2) time and O(n) space
    public int minimumTotal(List<List<Integer>> triangle) {
        int nr = triangle.size();
        if (nr == 0) {
            return 0;
        }
        int[] sums = new int[nr];
        for (int i = 0; i < nr; ++i) {
            sums[i] = triangle.get(nr-1).get(i);
        }
        for (int r = nr - 2; r >= 0; --r) {
            for (int c = 0; c <= r; ++c) {
                sums[c] = Math.min(sums[c], sums[c+1]) + triangle.get(r).get(c);
            }
        }
        return sums[0];
    }
    
}