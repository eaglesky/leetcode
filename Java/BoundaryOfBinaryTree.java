/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BoundaryOfBinaryTree {
    
    //Clear and naive multi-passes solution, O(n) time and O(n) space
    private static void addLeftBoundary(TreeNode node, List<Integer> result) {
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }
        result.add(node.val);
        if (node.left != null) {
            addLeftBoundary(node.left, result);
        } else if (node.right != null) {
            addLeftBoundary(node.right, result);
        }
    }
    
    private static void addLeaves(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }    
        if (node.left == null && node.right == null) {
            result.add(node.val);
        }
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }
    
    private static void addRightBoundary(TreeNode node, LinkedList<Integer> rightVals) {
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }
        rightVals.addFirst(node.val);
        if (node.right != null) {
            addRightBoundary(node.right, rightVals);
        } else if (node.left != null) {
            addRightBoundary(node.left, rightVals);
        }
    }
    
    public List<Integer> boundaryOfBinaryTree0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.val);
        if (root.left == null && root.right == null) {
            return result;
        }
        addLeftBoundary(root.left, result);
        addLeaves(root, result);
        LinkedList<Integer> rightVals = new LinkedList<>();
        addRightBoundary(root.right, rightVals);
        result.addAll(rightVals);
        return result;
    }
    
    //Better one-pass solution. O(n) time and O(h) space
    private static void preOrderLeftBranch(TreeNode node, List<Integer> result, boolean onLeft) {
        if (node == null) {
            return;
        }
        if (onLeft || (node.left == null && node.right == null)) {
            result.add(node.val);
        }
        preOrderLeftBranch(node.left, result, onLeft);
        preOrderLeftBranch(node.right, result, onLeft && (node.left == null));
    }
    
    private static void preOrderRightBranch(TreeNode node, List<Integer> result, 
            LinkedList<Integer> rightVals, boolean onRight) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.add(node.val);
        } else if (onRight) {
            rightVals.addFirst(node.val);
        }
        preOrderRightBranch(node.left, result, rightVals, onRight && (node.right == null));
        preOrderRightBranch(node.right, result, rightVals, onRight);
    }
    
    public List<Integer> boundaryOfBinaryTree1(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        result.add(root.val);
        preOrderLeftBranch(root.left, result, true);
        LinkedList<Integer> rightVals = new LinkedList<>();
        preOrderRightBranch(root.right, result, rightVals, true);
        result.addAll(rightVals);
        return result;
    }
    
    //Best and shorter one-pass solution. O(n) time and O(h) space, not counting the space of result
    //https://discuss.leetcode.com/topic/87069/java-recursive-solution-beats-94
    private static void dfsSubTree(TreeNode node, boolean onLeft, boolean onRight, List<Integer> result) {
        if (node == null) {
            return;
        }          
        if (onLeft || (node.left == null && node.right == null)) {
            result.add(node.val);
        } 
        dfsSubTree(node.left, onLeft, onRight && (node.right == null), result);
        dfsSubTree(node.right, onLeft && (node.left == null), onRight, result);
        if (onRight && (node.left != null || node.right != null)) {
            result.add(node.val);
        }
    }
            
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.val);
        dfsSubTree(root.left, true, false, result);
        dfsSubTree(root.right, false, true, result);
        return result;
    }

    //A small variable of above:
    private void traverseSubTree(TreeNode root, boolean isLeftBoundary, boolean isRightBoundary, List<Integer> result) {
        if (root == null) {
            return;
        }    
        if (root.left == null && root.right == null) {
            result.add(root.val);
        } else {
            if (isLeftBoundary) {
                result.add(root.val);
            }
            traverseSubTree(root.left, isLeftBoundary, isRightBoundary && root.right == null, result);
            traverseSubTree(root.right, isLeftBoundary && root.left == null, isRightBoundary, result);
            if (isRightBoundary) {
                result.add(root.val);
            }
        }
    }
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.val);
        traverseSubTree(root.left, true, false, result);
        traverseSubTree(root.right, false, true, result);
        return result;
    }
}