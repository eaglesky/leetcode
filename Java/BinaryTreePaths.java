/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreePaths {
    
    /*O(n^2) time as for each path it needs to copy each characters in the path.
    Worst case is like:
                   1
                  / \
				 2   3
				    / \
				   4   5
				      / \
				     6   7
	*/ 
    private static void dfs(TreeNode cur, StringBuilder sb, List<String> result) {
        if (cur == null) {
            return;
        }
        int sz = sb.length();
        sb.append(cur.val);
        if (cur.left == null && cur.right == null) {
            result.add(sb.toString());
        } else {
            sb.append("->");
            dfs(cur.left, sb, result);
            dfs(cur.right, sb, result);
        }
        sb.setLength(sz);
    }
    
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root, new StringBuilder(), result);
        return result;
    }
}