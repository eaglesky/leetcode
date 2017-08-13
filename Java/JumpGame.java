public class JumpGame {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     */
     
    //DP solution, O(n^2) time and O(n) space
    public boolean canJump0(int[] A) {
        if (A == null || A.length <= 1) {
            return true;
        }
        boolean[] dp = new boolean[A.length];
        dp[0] = true;
        for (int i = 1; i < A.length; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if (dp[j] && j + A[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[A.length - 1];
    }
    
    //O(n) time and O(1) space.
    public boolean canJump(int[] A) {
        if (A == null || A.length <= 1) {
            return true;
        }
        int maxDis = A[0];
        //At the beginning of each iteration, maxDis is the furthest
        //id one can reach from the starting point, using only the previous
        //points.
        for (int i = 1; i < A.length && maxDis < A.length - 1; ++i) {
            if (i > maxDis) {
                return false;
            }
            maxDis = Math.max(maxDis, i + A[i]);
        }
        return true;
    }
}
