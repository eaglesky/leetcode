/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {

    private final Deque<TreeNode> stack = new ArrayDeque<>();
    
    public BSTIterator(TreeNode root) {
        advanceToNext(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        TreeNode node = stack.pop();
        advanceToNext(node.right);
        return node.val;
    }
    
    private void advanceToNext(TreeNode curNode) {
        while (curNode != null) {
            stack.push(curNode);
            curNode = curNode.left;
        }
    }
    
    //Another solution is using Morris traversal,
    //which has the same time complexity, but the space complexity is O(1).
    //https://discuss.leetcode.com/topic/8154/morris-traverse-solution
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */