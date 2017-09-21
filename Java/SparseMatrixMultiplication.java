/*
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/
                  
public class SparseMatrixMultiplication {
    
    //Naive soltuion. TLE. If A is m x n, B is n x t, then it is O(mnt) time.
    public int[][] multiply0(int[][] A, int[][] B) {
        if (A.length == 0 || B.length == 0) {
            return null;
        }
        int[][] result = new int[A.length][B[0].length];
        for (int i = 0; i < result.length; ++i) {
            for (int j = 0; j < result[i].length; ++j) {
                for (int k = 0; k < A[i].length; ++k) {
                    if (A[i][k] != 0 && B[k][j] != 0) {
                        result[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return result;
    }
    
    //Better soltuion. If A is m x n, B is n x t, then it is O(mnt) time.
    //Change the order of computing products, so that when A[i][k] is zero,
    //skip the iteration of an entire row in B.
    //https://discuss.leetcode.com/topic/72871/54ms-detailed-summary-of-easiest-java-solutions-beating-99-9
    public int[][] multiply(int[][] A, int[][] B) {
        if (A.length == 0 || B.length == 0) {
            return null;
        }
        int[][] result = new int[A.length][B[0].length];
        for (int i = 0; i < result.length; ++i) {
            for (int k = 0; k < A[0].length; ++k) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < result[i].length; ++j) {
                        if (B[k][j] != 0) {
                            result[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return result;
    }
}