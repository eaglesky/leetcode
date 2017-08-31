class OnesAndZeros {
    
    //O(lmn) + O(l*ls) time, O(l) + O(mn) space, ls is the average length of the strings
    //d[l][n0][n1] is the max number of strings in first l strings, with limit of n0 zeros and n1 ones.
    //ns0 is the number of zeros in strs[l-1], ns1 is the number of ones in strs[l-1]
    //d[l][n0][n1] = max{d[l-1][n0][n1], d[l-1][n0- ns0][n1 - ns1] + 1, n0 >= ns0, n1 >= ns1}
    //d[0][x][y] = 0.
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || m < 0 || n < 0) {
            return 0;
        }
        int len = strs.length;
        int[] numZeros = new int[len];
        int[] numOnes = new int[len];
        for (int i = 0; i < strs.length; ++i) {
            for (int j = 0; j < strs[i].length(); ++j) {
                if (strs[i].charAt(j) == '0') {
                    numZeros[i]++;
                } else {
                    numOnes[i]++;
                }
            }
        }
        int[][] d = new int[m + 1][n + 1];
        for (int l = 1; l <= len; ++l) {
            for (int n0 = m; n0 >= numZeros[l-1]; --n0) {
                for (int n1 = n; n1 >= numOnes[l-1]; --n1) {
                    d[n0][n1] = Math.max(d[n0][n1], d[n0 - numZeros[l-1]][n1 - numOnes[l-1]] + 1);
                }
            }
        }
        return d[m][n];
    }
}