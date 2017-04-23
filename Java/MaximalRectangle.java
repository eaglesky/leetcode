import java.util.*;

public class MaximalRectangle {
    
    //Use the algorithm of largest rectangle in histogram.
    //O(mn) time and O(n) space
    public int maximalRectangle0(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length + 1];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; ++i) {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1);
            for (int j = 0; j <= matrix[i].length; ++j) {
                heights[j] = (j == matrix[i].length || matrix[i][j] == '0') ? 0 : heights[j] + 1;                
            }
            for (int j = 0; j <= matrix[i].length;) {
                int preId = stack.peek();
                if (preId == -1 || heights[j] >= heights[preId]) {
                    stack.push(j++);
                } else {
                    stack.pop();
                    int preId2 = stack.peek();
                    maxArea = Math.max(maxArea, heights[preId]*(j - preId2 - 1));
                }
            }
        }
        return maxArea;
    }

    //No better than the above solution. Same time and space complexities
    //https://discuss.leetcode.com/topic/6650/share-my-dp-solution
    //Also refer to the Local Maxima pdf.
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int[] leftIds = new int[matrix[0].length];
        int[] rightIds = new int[matrix[0].length];
        int maxArea = 0;
        leftIds[0] = -1;
        rightIds[rightIds.length-1] = matrix[0].length;
        for (int i = 0; i < matrix.length; ++i) {
            int preId = matrix[i].length;
            for (int j = matrix[i].length - 1; j >= 0; --j) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
                if (matrix[i][j] == '0') {
                    rightIds[j] = j;
                    preId = j;
                } else {
                    rightIds[j] = (heights[j] > 1) ? Math.min(preId, rightIds[j]) : preId;
                }
            }
            preId = -1;
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] == '0') {
                    leftIds[j] = j;
                    preId = j;
                } else {
                    leftIds[j] = (heights[j] > 1) ? Math.max(preId, leftIds[j]) : preId;
                    maxArea = Math.max(maxArea, heights[j] * (rightIds[j] - leftIds[j] - 1));
                }
            }

            System.out.println("Row = " + i);
            System.out.println(Arrays.toString(heights));
            System.out.println(Arrays.toString(leftIds));
            System.out.println(Arrays.toString(rightIds));
            System.out.println("");
        }
        return maxArea;
    }

    public static void main(String[] args) {
        MaximalRectangle sl = new MaximalRectangle();
        String[] strs = new String[] {
            "0110010101",
            "0010101010",
            "1000010110",
            "0111111010",
            "0011111110",
            "1101011110",
            "0001100010",
            "1101100111",
            "0101101011"
        };
        char[][] test = new char[strs.length][strs[0].length()];
        for (int i = 0; i < test.length; ++i) {
            test[i] = strs[i].toCharArray();
            //System.out.println(Arrays.toString(test[i]));
        }
        int result = sl.maximalRectangle(test);
        System.out.println("Result = " + result); //Expected = 10;
    }
}