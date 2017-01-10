/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class ValidateBinarySearchTree {
    
    private static boolean isValidBSTRecursive0(TreeNode root, int[] minAndMax) {
        int minVal = Integer.MIN_VALUE;
        int maxVal = Integer.MAX_VALUE;
        boolean leftIsBST = (root.left == null) || isValidBSTRecursive0(root.left, minAndMax);
        if (!leftIsBST) {
            return false;
        }
        if (root.left != null) {
            if (minAndMax[1] >= root.val) {
                return false;
            }
            minVal = minAndMax[0];
        } else {
            minVal = root.val;
        }
        minAndMax[1] = root.val;
        
        boolean rightIsBST = (root.right == null) || isValidBSTRecursive0(root.right, minAndMax);
        if (!rightIsBST) {
            return false;
        }
        if (root.right != null) {
            if (minAndMax[0] <= root.val) {
                return false;
            }
        }
        minAndMax[0] = minVal;
        return true;
    }
    
    public boolean isValidBST0(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBSTRecursive0(root, new int[]{0, 0});
    }
    
    // Another alogorithm using inorder traversal
    private static boolean isValidBSTRecursive1(TreeNode root, Integer[] preValue) {
        if (root != null) {
            return isValidBSTRecursive1(root.left, preValue)
                && (preValue[0] == null || preValue[0] < root.val)
                && ((preValue[0] = Integer.valueOf(root.val)) != null)
                && isValidBSTRecursive1(root.right, preValue);
        } else {
            return true;
        }
    }
    
    public boolean isValidBST1(TreeNode root) {
        return isValidBSTRecursive1(root, new Integer[]{null});
    }
    
    // Iterative implementation of above algorithm using stack
    // Can also use Morris traversal here
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode preNode = null;
        for(TreeNode cur = root; cur != null || !stack.isEmpty();) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode curNode = stack.pop();
                if (preNode != null && preNode.val >= curNode.val) {
                    return false;
                }
                preNode = curNode;
                cur = curNode.right;
            }
        }
        return true;
    }
    
    // Another recursive algorithm based on interval
    private static boolean isValidBSTRecursive(TreeNode root, Integer minVal, Integer maxVal) {
        if (root == null) {
            return true;
        }
        if ((maxVal != null && root.val >= maxVal) || (minVal != null && root.val <= minVal)) {
             return false;
        }
        return isValidBSTRecursive(root.left, minVal, Integer.valueOf(root.val))
            && isValidBSTRecursive(root.right, Integer.valueOf(root.val), maxVal);
    }
    
    public boolean isValidBST(TreeNode root) {
        return isValidBSTRecursive(root, null, null);
    }
    
}