/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    //Recursive solution, O(n) time and O(log(n)) space
    private TreeNode createBSTRecursive(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = createBSTRecursive(nums, start, mid - 1);
        root.right = createBSTRecursive(nums, mid + 1, end);
        return root;
    }
    
    public TreeNode sortedArrayToBST0(int[] nums) {
        return createBSTRecursive(nums, 0, nums.length-1);
    }
    
    //Iterative solution
    private class MyNode {
        final TreeNode node;
        final int start;
        final int end;
        MyNode(TreeNode node, int start, int end) {
            this.node = node;
            this.start = start;
            this.end = end;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        Deque<MyNode> q = new ArrayDeque<>();
        int rootNodeId = (nums.length - 1) / 2;
        TreeNode root = new TreeNode(nums[rootNodeId]);
        q.offer(new MyNode(root, 0, nums.length-1));
        while(!q.isEmpty()) {
            MyNode curMyNode = q.poll();
            int rootId = curMyNode.start + (curMyNode.end - curMyNode.start) / 2;
            int leftId = curMyNode.start + (rootId - 1 - curMyNode.start) / 2;
            int rightId = rootId + 1 + (curMyNode.end  - rootId - 1) / 2;
            TreeNode leftNode = (curMyNode.start <= rootId - 1) ? new TreeNode(nums[leftId]) : null;
            TreeNode rightNode = (rootId + 1 <= curMyNode.end) ? new TreeNode(nums[rightId]) : null;
            curMyNode.node.left = leftNode;
            curMyNode.node.right = rightNode;
            if (leftNode != null) {
               q.offer(new MyNode(leftNode, curMyNode.start, rootId - 1)); 
            }
            if (rightNode != null) {
                q.offer(new MyNode(rightNode, rootId + 1, curMyNode.end));
            }
        }
        return root;
    }
}