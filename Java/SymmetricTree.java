/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SymmetricTree {
    
    private static boolean nodesAreEqual(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null || rightNode == null) {
            return leftNode == rightNode;
        } else {
            return leftNode.val == rightNode.val;
        }
    }
    
    // Iterative solution using queue
    // O(n) time and O(n) space
    public boolean isSymmetric0(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!nodesAreEqual(root.left, root.right)) {
            return false;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root.left != null && root.right != null) {
           queue.offer(root.left); 
           queue.offer(root.right);
        }
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i += 2) {
                TreeNode leftNode = queue.poll();
                TreeNode rightNode = queue.poll();
                
                if (!nodesAreEqual(leftNode.left, rightNode.right)) {
                    return false;
                }
                if (!nodesAreEqual(leftNode.right, rightNode.left)) {
                    return false;
                }
                if (leftNode.left != null && rightNode.right != null) {
                    queue.offer(leftNode.left);
                    queue.offer(rightNode.right);
                }
                if (leftNode.right != null && rightNode.left != null) {
                    queue.offer(leftNode.right);
                    queue.offer(rightNode.left);
                }
            }
        }
        return true;
    }
    
    //Itertive solution using stack
    //O(n) time and O(n) space
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!nodesAreEqual(root.left, root.right)) {
            return false;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root.left != null && root.right != null) {
           stack.push(root.left); 
           stack.push(root.right);
        }
        
        while(!stack.isEmpty()) {
            TreeNode leftNode = stack.pop();
            TreeNode rightNode = stack.pop();
            
            if (!nodesAreEqual(leftNode.left, rightNode.right)) {
                return false;
            }
            if (!nodesAreEqual(leftNode.right, rightNode.left)) {
                return false;
            }
            if (leftNode.left != null && rightNode.right != null) {
                stack.push(leftNode.left);
                stack.push(rightNode.right);
            }
            if (leftNode.right != null && rightNode.left != null) {
                stack.push(leftNode.right);
                stack.push(rightNode.left);
            }
        }
        return true;
    }
    
    //Better implementation of the iterative solution using LinkedList
    //A little bit slower than ArrayDeque, but since it permits null element,
    //the implementation is much simpler
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> levelNodes = new LinkedList<>();
        levelNodes.offer(root.left);
        levelNodes.offer(root.right);
        //At the beginning of each iteration, levelNodes
        //contains the nodes on the current level, from outermost to innermost
        while (!levelNodes.isEmpty()) {
            int sz = levelNodes.size();
            for (int i = 0; i < sz; i += 2) {
                TreeNode leftNode = levelNodes.poll();
                TreeNode rightNode = levelNodes.poll();
                if (leftNode != null && rightNode != null) {
                    if (leftNode.val != rightNode.val) {
                        return false;
                    }
                    levelNodes.offer(leftNode.left);
                    levelNodes.offer(rightNode.right);
                    levelNodes.offer(leftNode.right);
                    levelNodes.offer(rightNode.left);
                } else if (leftNode != rightNode) {
                    return false;
                }
            }
        }
        return true;
    }

    // Recursive solution
    private static boolean isSymmetricRecursive(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null || rightNode == null) {
            return leftNode == rightNode;
        } else if (leftNode.val != rightNode.val) {
            return false;
        }
        return isSymmetricRecursive(leftNode.left, rightNode.right)
        && isSymmetricRecursive(leftNode.right, rightNode.left);
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricRecursive(root.left, root.right);
    }
}