/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class KthSmallestElementInBST {

    //Recursive solution without indexing, O(n) time and O(h) space
    private TreeNode getKthNode(TreeNode root, int[] count) {
        if (root == null) {
            return null;
        }
        TreeNode foundNode = getKthNode(root.left, count);
        if (foundNode != null) {
            return foundNode;
        }
        count[0]--;
        if (count[0] == 0) {
            return root;
        }
        return getKthNode(root.right, count);
    }
    
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        TreeNode node = getKthNode(root, new int[]{k});
        if (node == null) {
            return 0;
        }
        return node.val;
    }

    //Iterative solution without indexing, O(n) time and O(h) space
    public int kthSmallest0(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int rank = 0;
        for(TreeNode cur = root; cur != null || !stack.isEmpty();) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode visited = stack.pop();
                rank++;
                if (rank == k) {
                    return visited.val;
                }
                cur = visited.right;
            }
        }
        return -1;
    }
    
    
    //Indexing -- store rank for each node, O(n) time
    private static void rankRecursive0(TreeNode root, int[] rank, Map<TreeNode, Integer> indexed) {
        if (root == null) {
            return;
        }
        rankRecursive0(root.left, rank, indexed);
        rank[0]++;
        indexed.put(root, rank[0]);
        rankRecursive0(root.right, rank, indexed);
    }
    
    //Iterative solution with indexing, O(h) time and O(n) space
    public int kthSmallest1(TreeNode root, int k) {
        Map<TreeNode, Integer> indexed = new HashMap<>();
        int[] rank = {0};
        rankRecursive0(root, rank, indexed);
        
        for(TreeNode cur = root; cur != null;) {
            int curRank = indexed.get(cur);
            if (k == curRank) {
                return cur.val;
            } else if (k < curRank) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return -1;
    }
    
    //Another way of indexing, O(n) time
    //Store the node number of left subtree of each node.
    //Though this way is no faster than the previous way, it is more efficient
    //when inserting or deleting a node. -- O(h) time.
    private static int rankRecursive(TreeNode root, Map<TreeNode, Integer> indexed) {
        if (root == null) {
            return 0;
        }
        int leftTotalNumber = rankRecursive(root.left, indexed);
        indexed.put(root, leftTotalNumber + 1);
        int rightTotalNumber = rankRecursive(root.right, indexed);
        return leftTotalNumber + rightTotalNumber + 1;
    }
    
    //Another way of looking up, O(h) time, O(n) space
    public int kthSmallest(TreeNode root, int k) {
        Map<TreeNode, Integer> indexed = new HashMap<>();
        rankRecursive(root, indexed);
        for(TreeNode cur = root; cur != null;) {
            int curRank = indexed.get(cur);
            if (k == curRank) {
                return cur.val;
            } else if (k < curRank){
                cur = cur.left;
            } else {
                k -= curRank;
                cur = cur.right;
            }
        }
        return -1;
    }
}