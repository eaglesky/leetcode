public class FourSum2 {
    
    //O(n^2) time and O(n^2) space
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> sumCounts = new HashMap<>();
        int result = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < B.length; ++j) {
                int sum = A[i] + B[j];
                int count = sumCounts.getOrDefault(sum, 0);
                sumCounts.put(sum, count + 1);
            }
        }
        for (int i = 0; i < C.length; ++i) {
            for (int j = 0; j < D.length; ++j) {
                int countInAB = sumCounts.getOrDefault(-C[i] - D[j], 0);
                result += countInAB;
            }
        }
        return result;
    }
}