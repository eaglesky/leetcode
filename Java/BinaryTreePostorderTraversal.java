/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreePostorderTraversal {
    
    private static void postorderTraversalRecursive(TreeNode root, List<Integer> result) {
        if (root != null) {
            postorderTraversalRecursive(root.left, result);
            postorderTraversalRecursive(root.right, result);
            result.add(root.val);
        }
    }
    
    // Recursive solution
    public List<Integer> postorderTraversal0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversalRecursive(root, result);
        return result;
    }
    
    // Iterative solution with stack first trial
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        TreeNode pre = new TreeNode(-1);
        for(TreeNode cur = root; (cur != null);) {

            if (cur != stack.peek()) {
                pre = cur;
                stack.push(cur);
                if (cur.left != null) {
                    cur = cur.left;
                } else if (cur.right != null) {
                    cur = cur.right;
                } else {
                    result.add(cur.val);
                    stack.pop();
                    cur = stack.peek();
                }
            } else {
                if (cur.right == pre || cur.right == null) {
                    pre = cur;
                    result.add(cur.val);
                    stack.pop();
                    cur = stack.peek();
                } else {
                    cur = cur.right;
                }
            }
        }
        return result;
    }
    
    //Iterative solution with stack(applicable to n-ary tree)
    //This can be proved inductively -- for simple subtrees, it is able to 
    //visit the nodes in a correct order, prev points to the last visited node,
    //and all of the nodes get popped out of the stack. -- for entire tree,
    //all of them are maintained.
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode prev = null;
        for(; !stack.isEmpty();) {
            TreeNode cur = stack.peek();
            if ((cur.left == null && cur.right == null)
                || (prev != null && (prev == cur.left || prev ==  cur.right))) {
                prev = stack.pop();
                result.add(prev.val);  
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        return result;
    }

    // Best iterative solution with stack
    // When cur is null, there is no hurry to move it. Keeping it as null is a good idea!
    // This can also proved inductively like previous solution.
    // After each subtree is visited, cur is null and all of the nodes in it are popped
    // out of the stack. Pre node is the last visited node.
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        TreeNode pre = null;
        for (TreeNode cur = root; (cur != null) || (!stack.isEmpty());) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode top = stack.peek();
                if (top.right != null && (top.right != pre)) {
                    cur = top.right;
                } else {
                    pre = stack.pop();
                    result.add(pre.val);
                }
            }
        }
        return result;
    }

    // Morris traversal, O(n) time and O(1) space
    private static void reverseVisit(TreeNode root, List<Integer> result) {
        TreeNode newHead = null;
        TreeNode cur = root;
        for(; cur != null;) {
            TreeNode next = cur.right;
            cur.right = newHead;
            newHead = cur;
            cur = next;
        }
        cur = newHead;
        newHead = null;
        for (; cur != null;) {
            result.add(cur.val);
            TreeNode next = cur.right;
            cur.right = newHead;
            newHead = cur;
            cur = next;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        for(TreeNode cur = dummy; cur != null;) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode next = cur.left;
                for(; (next.right != null) && (next.right != cur); next = next.right);
                if (next.right == null) {
                    next.right = cur;
                    cur = cur.left;
                } else {
                    next.right = null;
                    reverseVisit(cur.left, result);
                    cur = cur.right;
                }
            }
        }
        return result;
    }
}